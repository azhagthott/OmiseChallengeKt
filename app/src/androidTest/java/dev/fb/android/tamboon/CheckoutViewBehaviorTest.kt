package dev.fb.android.tamboon

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import dev.fb.android.tamboon.ui.activity.CheckoutActivity
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class CheckoutViewBehaviorTest {

    @get:Rule
    var checkoutViewRule: ActivityTestRule<CheckoutActivity> =
        ActivityTestRule(CheckoutActivity::class.java, true, false)

    @Before
    fun initValidView() {

        Intents.init()

        val resultData = Intent()
        val name = "Ban Khru Noi"
        val url = "http://rkdretailiq.com/news/img-corporate-baankrunoi.jpg"

        resultData.putExtra("CHARITY_NAME", name)
        resultData.putExtra("CHARITY_URL", url)

        checkoutViewRule.launchActivity(resultData)
    }

    /* If amount is lower than 19 -> donate button NOT enable*/
    @Test
    fun checkAmountLowerThan20Test() {
        onView(withId(R.id.et_amount))
            .perform(clearText())
            .perform(typeText("19"))
        onView(withId(R.id.btn_donate_now)).check(matches(not(isEnabled())))
    }

    /* If amount is greater than 19 -> donate button enable*/
    @Test
    fun checkAmountGreaterThan19Test() {
        onView(withId(R.id.et_amount))
            .perform(clearText())
            .perform(typeText("20"))
        onView(withId(R.id.btn_donate_now)).check(matches(isEnabled()))
    }

    /* If amount length is lower than 1 -> donate button NOT enable*/
    @Test
    fun checkEditTextAmountLengthLowerThan1Test() {
        onView(withId(R.id.et_amount))
            .perform(clearText())
            .perform(typeText(""))
        onView(withId(R.id.btn_donate_now)).check(matches(not(isEnabled())))
    }

    /* If amount length is greater than 1000000 -> donate button enable*/
    @Test
    fun checkEditTextAmountLengthGreaterThan1000000Test() {
        onView(withId(R.id.et_amount))
            .perform(clearText())
            .perform(typeText("1000001"))
        onView(withId(R.id.btn_donate_now)).check(matches(not(isEnabled())))
    }

    @After
    fun releaseIntent() {
        Intents.release()
    }
}