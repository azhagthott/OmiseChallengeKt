package android.omise.charity.domain.model

import com.google.gson.annotations.SerializedName

data class Donation(

    @SerializedName("name") var donationName: String,
    @SerializedName("token") var donationToken: String,
    @SerializedName("amount") var donationAmount: Long
)