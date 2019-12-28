package dev.fb.android.tamboon.data.repository

import com.google.gson.JsonObject
import android.omise.core.ui.BaseUseCase
import dev.fb.android.tamboon.domain.model.Charity

interface CharityRepository {
    suspend fun getCharityList(): BaseUseCase<List<Charity>>
    suspend fun makeCheckout(): BaseUseCase<JsonObject>
}