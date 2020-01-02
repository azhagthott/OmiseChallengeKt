package dev.fb.android.tamboon

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import dev.fb.android.tamboon.ui.activity.CharityListActivity
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CharityListViewToCheckoutViewTest {

    @get:Rule
    var activityRule: ActivityTestRule<CharityListActivity> =
        ActivityTestRule(CharityListActivity::class.java)

    @Test
    fun callCheckoutActivityBySelectingItemTest() {
        if (onView(withId(R.id.lv_charities)).check(matches(isDisplayed())) != null) {
            onData(CoreMatchers.anything())
                .inAdapterView(withId(R.id.lv_charities))
                .atPosition(0)
                .perform(ViewActions.click())
        }
    }
}