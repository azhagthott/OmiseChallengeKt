package dev.fb.android.tamboon.viewmodel

import androidx.lifecycle.MutableLiveData
import dev.fb.android.tamboon.base.BaseUseCase
import dev.fb.android.tamboon.data.repository.CharityRepository
import dev.fb.android.tamboon.domain.model.Charity
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

    private fun loadCharities() {
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
