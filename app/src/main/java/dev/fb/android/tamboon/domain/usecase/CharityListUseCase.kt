package dev.fb.android.tamboon.domain.usecase

import dev.fb.android.tamboon.base.BaseUseCase
import dev.fb.android.tamboon.data.repository.CharityRepository
import dev.fb.android.tamboon.domain.model.Charity
import io.reactivex.Observable

class CharityListUseCase(private var repository: CharityRepository) : BaseUseCase<List<Charity>>() {

    fun charityListUseCase(): CharityListUseCase {
        return this
    }

    open override fun createObservableUseCase(): Observable<List<Charity>> {
        return repository.getCharityList
    }
}