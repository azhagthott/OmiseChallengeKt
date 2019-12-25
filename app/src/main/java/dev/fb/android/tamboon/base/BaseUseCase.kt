package dev.fb.android.tamboon.base

import dagger.internal.Preconditions
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<T> {

    private val compositeDisposable = CompositeDisposable()

    fun execue(disposableObserver: UseCaseObserver<T>) {
        Preconditions.checkNotNull(disposableObserver)

        var observable: Observable<T> = createObservableUseCase()
            .subscribeOn(getSubscribeOn())
            .observeOn(getObserveOn())

        val observer: UseCaseObserver<T> = observable.subscribeWith(disposableObserver)

        compositeDisposable.add(observer)
    }

    open fun getObservable(): Observable<T> {
        return createObservableUseCase()
    }

    protected open fun getSubscribeOn(): Scheduler {
        return Schedulers.io()
    }

    protected open fun getObserveOn(): Scheduler? {
        return AndroidSchedulers.mainThread()
    }

    open fun dispose() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    protected abstract fun createObservableUseCase(): Observable<T>

}