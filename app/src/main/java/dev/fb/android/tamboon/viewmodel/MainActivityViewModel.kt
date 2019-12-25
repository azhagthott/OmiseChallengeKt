package dev.fb.android.tamboon.viewmodel

import androidx.lifecycle.ViewModel
import dev.fb.android.tamboon.base.UseCaseObserver
import dev.fb.android.tamboon.domain.model.Charity
import dev.fb.android.tamboon.domain.usecase.CharityListUseCase

class MainActivityViewModel : ViewModel() {

    private val charityListUseCase: CharityListUseCase? = null

    private val list = listOf<Charity>()

    fun getCharityList(): List<Charity> {

        charityListUseCase?.execue(UseCaseObserver())

        return list
    }
}
