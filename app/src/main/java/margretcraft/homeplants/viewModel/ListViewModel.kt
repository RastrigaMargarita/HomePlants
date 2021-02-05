package margretcraft.homeplants.viewModel


import androidx.lifecycle.Observer
import margretcraft.homeplants.model.BaseResult
import margretcraft.homeplants.model.Plant
import margretcraft.homeplants.model.Repository
import margretcraft.homeplants.ui.BaseViewModel
import margretcraft.homeplants.ui.ListViewState


class ListViewModel(val repository: Repository = Repository) : BaseViewModel<List<Plant>?, ListViewState>() {

    private val repositoryPlants = repository.getPlants()

    private val plantsObserver = object : Observer<BaseResult> {
        override fun onChanged(t: BaseResult?) {
            if (t == null) return

            when (t) {
                is BaseResult.Success<*> -> {
                    viewStateLiveData.value = ListViewState(plants = t.data as? List<Plant>)
                }
                is BaseResult.Error -> {
                    viewStateLiveData.value = ListViewState(error = t.error)
                }
            }
        }
    }

    init {
        viewStateLiveData.value = ListViewState()
        repositoryPlants.observeForever(plantsObserver)
    }

    override fun onCleared() {
        repositoryPlants.removeObserver(plantsObserver)
    }

}