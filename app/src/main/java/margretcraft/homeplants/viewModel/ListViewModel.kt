package margretcraft.homeplants.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import margretcraft.homeplants.model.MockData
import margretcraft.homeplants.ui.ListViewState

class ListViewModel : ViewModel(){
    private val viewStateLiveData: MutableLiveData<ListViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = ListViewState(MockData.PLANTS)
    }

    fun viewState(): LiveData<ListViewState> = viewStateLiveData
}