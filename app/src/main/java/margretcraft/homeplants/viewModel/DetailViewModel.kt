package margretcraft.homeplants.viewModel

import androidx.lifecycle.ViewModel
import margretcraft.homeplants.model.Plant
import margretcraft.homeplants.model.Repository

class DetailViewModel(private val repository: Repository = Repository) : ViewModel() {

    fun saveChanges(plant: Plant) {
        repository.savePlant(plant)
    }

}