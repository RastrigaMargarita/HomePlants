package margretcraft.homeplants.viewModel

import margretcraft.homeplants.model.NoAuthException
import margretcraft.homeplants.model.Repository
import margretcraft.homeplants.ui.splash.SplashViewState

class SplashViewModel(private val repository: Repository = Repository) :
        BaseViewModel<Boolean?, SplashViewState>() {
    fun requestUser() {
        repository.getCurrentUser().observeForever { user ->
            viewStateLiveData.value = user?.let {
                SplashViewState(isAuth = true)
            } ?: SplashViewState(error = NoAuthException())

        }
    }
}