package margretcraft.homeplants.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*

private const val COLLECTION = "plants";

class FireStoreProvider : RemoteDataProvider {

    private val db = FirebaseFirestore.getInstance()
    private val plantsReference = db.collection(COLLECTION)

    override fun subscribeToAllRecords(): LiveData<BaseResult> {
        val result = MutableLiveData<BaseResult>()

        plantsReference.addSnapshotListener((object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    result.value = BaseResult.Error(error)
                } else if (value != null) {
                    val plants = mutableListOf<Plant>()

                    for (doc: QueryDocumentSnapshot in value) {
                        plants.add(doc.toObject((Plant::class.java)))
                    }

                    result.value = BaseResult.Success(plants)
                }
            }

        }))
        return result
    }

    override fun getPlantByID(id: String): LiveData<BaseResult> {
        val result = MutableLiveData<BaseResult>()

        plantsReference.document(id)
                .get()
                .addOnSuccessListener { snapshot -> result.value = BaseResult.Success(snapshot.toObject(Plant::class.java)) }
                .addOnFailureListener { exception -> result.value = BaseResult.Error(exception) }
        return result
    }

    override fun savePlant(plant: Plant): LiveData<BaseResult> {
        val result = MutableLiveData<BaseResult>()

        plantsReference.document(plant.id)
                .set(plant)
                .addOnSuccessListener { OnSuccessListener<Void> { result.value = BaseResult.Success(plant) } }
                .addOnFailureListener { OnFailureListener { exception -> result.value = BaseResult.Error(exception) } }
        return result
    }

}