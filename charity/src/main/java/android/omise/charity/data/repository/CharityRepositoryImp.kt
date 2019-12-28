package dev.fb.android.tamboon.data.repository

import com.google.gson.JsonObject
import android.omise.core.ui.BaseUseCase
import dev.fb.android.tamboon.data.remote.CharityApi
import dev.fb.android.tamboon.domain.model.Charity

class CharityRepositoryImp(private val api: CharityApi) : CharityRepository {
    override suspend fun getCharityList(): BaseUseCase<List<Charity>> {
        return try {
            val result = api.getCharityList().await()
            BaseUseCase.Success(result)
        } catch (ex: Exception) {
            BaseUseCase.Error(ex)
        }
    }

    override suspend fun makeCheckout(): BaseUseCase<JsonObject> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}