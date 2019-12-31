package dev.fb.android.tamboon

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import dev.fb.android.tamboon.ui.activity.CharityListActivity
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CharityListViewBehaviorTest {

    @get:Rule
    var activityRule: ActivityTestRule<CharityListActivity> =
        ActivityTestRule(CharityListActivity::class.java)

    @Before
    fun initValidView() {
        //validate view
    }

    @Test
    fun checkAppNameTitleTest() {
        onView(withId(R.id.tv_toolbar)).check(matches(withText(R.string.app_name)))
    }

    @Test
    fun showCharityListTest() {
        onView(withId(R.id.lv_charities)).check(matches(isDisplayed()))
    }

    /**
     * if have internet connection user shouldn't see this TextView
     */
    @Test
    fun textViewErrorTest() {
        onView(withId(R.id.tv_error)).check(
            matches(
                withEffectiveVisibility(
                    Visibility.GONE
                )
            )
        )
    }

    @Test
    fun charityListSelectItemTest() {
        onView(withId(R.id.lv_charities)).perform(click())
    }

    @Test
    fun selectItemTest() {
        if (onView(withId(R.id.lv_charities)).check(matches(isDisplayed())) != null) {
            onData(anything()).inAdapterView(withId(R.id.lv_charities)).atPosition(0)
                .perform(click())
        }
    }
}