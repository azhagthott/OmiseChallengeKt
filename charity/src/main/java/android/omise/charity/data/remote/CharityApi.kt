package android.omise.charity.data.remote

import android.omise.charity.domain.model.Charity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CharityApi {

    @GET("charities")
    fun getCharityList(): Deferred<List<Charity>>
}