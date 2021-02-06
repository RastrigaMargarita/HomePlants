package margretcraft.homeplants.ui

import margretcraft.homeplants.model.Plant

class DetailViewState(val plant: Plant? = null, error: Throwable? = null) :
        BaseViewState<Plant?>(plant, error)