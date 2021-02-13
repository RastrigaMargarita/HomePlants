package margretcraft.homeplants.ui.list

import margretcraft.homeplants.model.Plant
import margretcraft.homeplants.ui.base.BaseViewState

class ListViewState(val plants: List<Plant>? = null, error: Throwable? = null) :
        BaseViewState<List<Plant>?>(plants, error)