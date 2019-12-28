package dev.fb.android.tamboon.viewmodel

import android.omise.charity.data.repository.CharityRepository
import android.omise.charity.domain.model.Donation
import android.omise.core.ui.BaseUseCase
import android.omise.core.ui.BaseViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CheckoutActivityViewModel(private val repository: CharityRepository) : BaseViewModel() {

    val showLoading = MutableLiveData<Boolean>()
    val checkoutSuccess = MutableLiveData<Boolean>()
    val showError = MutableLiveData<String>()

    fun makeCheckout(donation: Donation) {
        showLoading.value = true

        launch {

            val json = JsonObject()

            json.addProperty("name", donation.donationName)
            json.addProperty("token", donation.donationToken)
            json.addProperty("amount", donation.donationAmount)

            val result = withContext(Dispatchers.IO) { repository.makeCheckout(json) }

            showLoading.value = false
            when (result) {
                is BaseUseCase.Success -> checkoutSuccess.value = true
                is BaseUseCase.Error -> showError.value = result.exception.message
            }
        }
    }
}
