package dev.fb.android.tamboon

import android.omise.charity.data.repository.CharityRepository
import dev.fb.android.tamboon.viewmodel.MainActivityViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock


@RunWith(Parameterized::class)
class CharityListActivityTest {

    @Mock
    lateinit var viewModel: MainActivityViewModel
    @Mock
    lateinit var repository: CharityRepository

    @Before
    fun setUp() {
        viewModel = MainActivityViewModel(repository)
    }

    @Test
    fun `select item from charity list`() {
        //val activity: CharityListActivity = Robolectric.setupActivity(CharityListActivity::class.java)
    }
}
