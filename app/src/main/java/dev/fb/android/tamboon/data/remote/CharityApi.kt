package dev.fb.android.tamboon.data.remote

import dev.fb.android.tamboon.data.entity.CharityResponseEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface CharityApi {

    @Headers("Content-Type: application/json")
    @GET("charities")
    fun getCharityList(): Observable<CharityResponseEntity>
}