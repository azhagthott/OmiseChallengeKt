package dev.fb.android.core.data

import dev.fb.android.core.model.Charities
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CharityApi {

    @GET("/charities")
    fun getPopularMovie(): Deferred<Response<Charities>>

}