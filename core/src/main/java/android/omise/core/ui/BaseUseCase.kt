package dev.fb.android.tamboon.base

sealed class BaseUseCase<out T : Any> {

    class Success<out T : Any>(val data: T) : BaseUseCase<T>()
    class Error(val exception: Throwable) : BaseUseCase<Nothing>()
}