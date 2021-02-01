package margretcraft.homeplants.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import margretcraft.homeplants.model.Plant
import margretcraft.homeplants.model.Repository
import margretcraft.homeplants.ui.ListViewState

class ListViewModel(private val repository: Repository = Repository) : ViewModel() {
    private val viewStateLiveData: MutableLiveData<ListViewState> = MutableLiveData()

    init {
        Repository.getPlants().observeForever { plants ->
            viewStateLiveData.value = ListViewState(plants)
        }
    }

    fun viewState(): LiveData<ListViewState> = viewStateLiveData

    fun saveChanges(plant: Plant) {
        repository.savePlant(plant)
    }
}