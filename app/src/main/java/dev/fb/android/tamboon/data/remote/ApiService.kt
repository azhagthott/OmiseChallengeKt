package dev.fb.android.tamboon.data.remote

import dev.fb.android.tamboon.App.API_VERSION
import dev.fb.android.tamboon.App.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ApiService {

    fun charityApiCall() = Retrofit.Builder()
        .baseUrl(BASE_URL + API_VERSION)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ApiClient.gsonConverter)
        .client(ApiClient.client)
        .build()
        .create(CharityApi::class.java)!!
}