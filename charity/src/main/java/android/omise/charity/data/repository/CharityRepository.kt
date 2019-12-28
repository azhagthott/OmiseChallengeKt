package android.omise.charity.data.repository

import android.omise.charity.domain.model.Charity
import android.omise.core.ui.BaseUseCase
import com.google.gson.JsonObject

interface CharityRepository {
    suspend fun getCharityList(): BaseUseCase<List<Charity>>
    suspend fun makeCheckout(): BaseUseCase<JsonObject>
}