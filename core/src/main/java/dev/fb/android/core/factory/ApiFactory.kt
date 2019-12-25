package dev.fb.android.core.factory

import dev.fb.android.core.data.CharityApi

 object ApiFactory {
     public val charityApi: CharityApi =
        RetrofitFactory.retrofit("https://virtserver.swaggerhub.com/chakritw/tamboon-api/1.0.0")
            .create(CharityApi::class.java)
}