package android.omise.charity.data.repository

import android.omise.charity.data.remote.CharityApi
import android.omise.charity.domain.model.Charity
import android.omise.core.ui.BaseUseCase
import com.google.gson.JsonObject

class CharityRepositoryImp(private val api: CharityApi) : CharityRepository {

    override suspend fun getCharityList(): BaseUseCase<List<Charity>> {
        return try {
            val result = api.getCharityList().await()
            BaseUseCase.Success(result)
        } catch (ex: Exception) {
            BaseUseCase.Error(ex)
        }
    }

    override suspend fun makeCheckout(json: JsonObject): BaseUseCase<JsonObject> {
        return try {
            val result = api.makeDonation(json).await()
            BaseUseCase.Success(result)
        } catch (ex: Exception) {
            BaseUseCase.Error(ex)
        }
    }
}