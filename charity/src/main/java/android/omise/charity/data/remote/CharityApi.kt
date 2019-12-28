package android.omise.charity.data.remote

import android.omise.charity.domain.model.Charity
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface CharityApi {

    @GET("/charities")
    fun getCharityList(): Deferred<List<Charity>>

    @POST("/donations")
    fun makeDonation(@Body json: JsonObject): Deferred<JsonObject>
}