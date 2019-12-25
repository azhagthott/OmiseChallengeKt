package dev.fb.android.tamboon.data.repository

import dev.fb.android.tamboon.domain.model.Charity
import io.reactivex.Observable

interface CharityRepository {

    val getCharityList: Observable<List<Charity>>

}