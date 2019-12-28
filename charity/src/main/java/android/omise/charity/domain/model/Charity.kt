package android.omise.charity.domain.model

import com.google.gson.annotations.SerializedName

data class Charity(

    @SerializedName("id") var charityId: Int,
    @SerializedName("name") var charityName: String,
    @SerializedName("logo_url") var charityLogoUrl: String
)