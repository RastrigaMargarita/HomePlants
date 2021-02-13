package margretcraft.homeplants.model

object Repository {

    private val remoteDataProvider: RemoteDataProvider = FireStoreProvider()

    fun getPlants() = remoteDataProvider.subscribeToAllRecords()
    fun getPlantByID(id: String) = remoteDataProvider.getPlantByID(id)
    fun savePlant(plant: Plant) = remoteDataProvider.savePlant(plant)
    fun getCurrentUser() = remoteDataProvider.getCurrentUser()
}