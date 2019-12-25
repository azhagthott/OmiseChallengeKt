package dev.fb.android.tamboon.data.repository

import com.google.gson.JsonObject
import dev.fb.android.tamboon.base.UseCase
import dev.fb.android.tamboon.data.remote.CharityApi
import dev.fb.android.tamboon.domain.model.Charity
import okhttp3.internal.wait

class CharityRepositoryImp(private val api: CharityApi) : CharityRepository {
    override suspend fun getCharityList(): UseCase<List<Charity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        return try {
            val result = api.getCharityList().wait()
            UseCase.Success(result)
        }
    }

    override suspend fun makeCheckout(): UseCase<JsonObject> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}