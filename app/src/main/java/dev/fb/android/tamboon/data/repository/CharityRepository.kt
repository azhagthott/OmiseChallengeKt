package dev.fb.android.tamboon.data.repository

import com.google.gson.JsonObject
import dev.fb.android.tamboon.base.UseCase
import dev.fb.android.tamboon.domain.model.Charity

interface CharityRepository {
    suspend fun getCharityList(): UseCase<List<Charity>>
    suspend fun makeCheckout(): UseCase<JsonObject>
}