package android.omise.core.ui

sealed class BaseUseCase<out T : Any> {

    class Success<out T : Any>(val data: T) : BaseUseCase<T>()
    class Error(val exception: Throwable) : BaseUseCase<Nothing>()
}