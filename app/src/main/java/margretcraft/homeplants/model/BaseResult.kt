package margretcraft.homeplants.model

sealed class BaseResult {
    data class Success<out T>(val data: T) : BaseResult()
    data class Error(val error: Throwable) : BaseResult()
}