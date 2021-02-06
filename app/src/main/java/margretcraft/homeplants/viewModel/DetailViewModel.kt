package margretcraft.homeplants.viewModel

import androidx.lifecycle.Observer
import margretcraft.homeplants.model.BaseResult
import margretcraft.homeplants.model.Plant
import margretcraft.homeplants.model.Repository
import margretcraft.homeplants.ui.detail.DetailViewState

class DetailViewModel(private val repository: Repository = Repository) : BaseViewModel<Plant?, DetailViewState>() {

    private var currentPlant: Plant? = null

    fun saveChanges(plant: Plant) {
        currentPlant = plant
        if (currentPlant != null) {
            repository.savePlant(currentPlant!!)
        }
    }

    fun loadPlant(plantID: String) {
        repository.getPlantByID(plantID).observeForever(object : Observer<BaseResult> {
            override fun onChanged(t: BaseResult?) {
                if (t == null) return
                when (t) {
                    is BaseResult.Success<*> -> {
                        viewStateLiveData.value = DetailViewState(plant = t.data as? Plant)
                    }
                    is BaseResult.Error -> {
                        viewStateLiveData.value = DetailViewState(error = t.error)
                    }
                }
            }

        })
    }
}