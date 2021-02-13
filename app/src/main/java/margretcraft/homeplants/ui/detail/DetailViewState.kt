package margretcraft.homeplants.ui.detail

import margretcraft.homeplants.model.Plant
import margretcraft.homeplants.ui.base.BaseViewState

class DetailViewState(val plant: Plant? = null, error: Throwable? = null) :
        BaseViewState<Plant?>(plant, error)