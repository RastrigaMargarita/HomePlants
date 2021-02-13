package margretcraft.homeplants.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

private const val COLLECTION = "plants";
private const val USERS_COLLECTION = "users";

class FireStoreProvider : RemoteDataProvider {

    private val db = FirebaseFirestore.getInstance()
    private val currentUser
        get() = FirebaseAuth.getInstance().currentUser

    private fun getUserPlantsCollection() = currentUser?.let { firebaseUser ->
        db.collection(USERS_COLLECTION)
                .document(firebaseUser.uid)
                .collection(COLLECTION)
    } ?: throw NoAuthException()


    override fun subscribeToAllRecords(): LiveData<BaseResult> =
            MutableLiveData<BaseResult>().apply {

                try {
                    getUserPlantsCollection().addSnapshotListener { snapshot, error ->
                        value = error?.let { BaseResult.Error(it) }
                                ?: snapshot?.let { snapshotDocument ->
                                    val plants = snapshotDocument.documents.map {
                                        it.toObject(Plant::class.java)
                                    }
                                    BaseResult.Success(plants)
                                }
                    }
                } catch (e: Throwable) {
                    value = BaseResult.Error(e)
                }
            }

    override fun getPlantByID(id: String): LiveData<BaseResult> =
            MutableLiveData<BaseResult>().apply {
                try {

                    getUserPlantsCollection().document(id)
                            .get()
                            .addOnSuccessListener { snapshot ->
                                value = BaseResult.Success(snapshot.toObject(Plant::class.java))
                            }
                            .addOnFailureListener { exception ->
                                value = BaseResult.Error(exception)
                            }
                } catch (e: Throwable) {
                    value = BaseResult.Error(e)
                }
            }

    override fun savePlant(plant: Plant): LiveData<BaseResult> =
            MutableLiveData<BaseResult>().apply {
                try {
                    getUserPlantsCollection().document(plant.id)
                            .set(plant)
                            .addOnSuccessListener {
                                OnSuccessListener<Void> {
                                    value = BaseResult.Success(plant)
                                }
                            }
                            .addOnFailureListener {
                                OnFailureListener { exception -> value = BaseResult.Error(exception) }
                            }

                } catch (e: Throwable) {
                    value = BaseResult.Error(e)
                }
            }

    override fun getCurrentUser(): LiveData<User?> =
            MutableLiveData<User?>().apply {
                value = currentUser?.let {
                    User(it.displayName ?: "", it.email ?: "")
                }
            }
}
