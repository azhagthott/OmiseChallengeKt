package dev.fb.android.tamboon.viewmodel

import android.omise.charity.data.repository.CharityRepository
import android.omise.charity.domain.model.Charity
import android.omise.core.domain.usecase.BaseUseCase
import android.omise.core.ui.viewmodel.BaseViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val repository: CharityRepository) : BaseViewModel() {

    val showLoading = MutableLiveData<Boolean>()
    val charityList = MutableLiveData<List<Charity>>()
    val showError = MutableLiveData<String>()

    init {
        loadCharities()
    }

    fun loadCharities() {
        showLoading.value = true

        launch {
            val result = withContext(Dispatchers.IO) { repository.getCharityList() }
            showLoading.value = false
            when (result) {
                is BaseUseCase.Success -> charityList.value = result.data
                is BaseUseCase.Error -> showError.value = result.exception.message
            }
        }
    }
}
