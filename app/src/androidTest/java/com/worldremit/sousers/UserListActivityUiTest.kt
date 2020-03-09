package com.worldremit.sousers

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.worldremit.sousers.ui.userlist.UserListActivity
import org.junit.Rule
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule
import org.junit.Test


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    @Rule
    @JvmField
    var userListActivityTestRule = ActivityTestRule(UserListActivity::class.java)

    @Test
    fun dataIsDisplayedOnRecyclerView() {
        Espresso.onView(ViewMatchers.withId(R.id.users_list)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }
}