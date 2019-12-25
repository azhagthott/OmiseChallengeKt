package dev.fb.android.tamboon.base

sealed class UseCase<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCase<T>()
    class Error(val exception: Throwable) : UseCase<Nothing>()
}