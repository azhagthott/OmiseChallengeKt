package dev.fb.android.tamboon.ui.adapter

import dev.fb.android.core.model.Charity
import org.junit.Test
import org.mockito.Mockito.mock

class CharityListAdapterTest {


    val list = listOf(Charity(1, "charity name", "logo_url"))
    val adapter: CharityListAdapter = mock(CharityListAdapter::class.java)


    @Test
    fun getView() {
    }

    @Test
    fun getItem() {

    }

    @Test
    fun getItemId() {
    }

    @Test
    fun getCount() {

    }
}