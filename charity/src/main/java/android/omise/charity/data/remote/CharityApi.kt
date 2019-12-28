package dev.fb.android.tamboon.data.remote

import dev.fb.android.tamboon.domain.model.Charity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CharityApi {

    @GET("charities")
    fun getCharityList(): Deferred<List<Charity>>
}