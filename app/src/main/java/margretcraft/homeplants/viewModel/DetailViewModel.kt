package margretcraft.homeplants.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import margretcraft.homeplants.model.Plant
import margretcraft.homeplants.ui.ListViewState

class DetailViewModel(val plant: Plant) : ViewModel() {
    private val viewStateLiveData: MutableLiveData<DetailViewModel> = MutableLiveData()

    init {
        viewStateLiveData.value = DetailViewModel(plant)
    }

    fun viewState(): LiveData<DetailViewModel> = viewStateLiveData
}