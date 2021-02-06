package margretcraft.homeplants.model

import androidx.lifecycle.LiveData

interface RemoteDataProvider {
    fun subscribeToAllRecords(): LiveData<BaseResult>
    fun getPlantByID(id: String): LiveData<BaseResult>
    fun savePlant(plant: Plant): LiveData<BaseResult>
}