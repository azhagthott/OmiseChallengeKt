package dev.fb.android.tamboon

import android.omise.charity.data.repository.CharityRepository
import com.google.gson.JsonObject
import dev.fb.android.tamboon.viewmodel.CheckoutActivityViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class CheckoutActivityTest {

    @Mock
    lateinit var repository: CharityRepository
    @InjectMocks
    lateinit var viewModel: CheckoutActivityViewModel

    lateinit var jsonMakeDonation: JsonObject
    lateinit var jsonSuccessTrue: JsonObject

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = CheckoutActivityViewModel(repository)

        jsonMakeDonation = JsonObject()
        jsonMakeDonation.addProperty("name", "Francisco Barrios")
        jsonMakeDonation.addProperty("token", "tokn_test_5idke4evfaehki26hwa")
        jsonMakeDonation.addProperty("name", 123123123)

        jsonSuccessTrue = JsonObject()
        jsonSuccessTrue.addProperty("success", true)

    }

    @Test
    fun `Get charity list`() {
        val result = runBlocking { repository.getCharityList() }
        assertEquals(null, result)
    }

    @Test
    fun `Make donation`() {
        val result = runBlocking { repository.makeCheckout(jsonMakeDonation) }
        assertEquals(null, result)
    }
}