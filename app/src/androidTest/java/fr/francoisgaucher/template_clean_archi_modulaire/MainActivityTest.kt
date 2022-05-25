package fr.francoisgaucher.template_clean_archi_modulaire

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_lifecycle() {
        Espresso.onView(ViewMatchers.withId(R.id.refresh_action)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.air_quality_content))
            .check(ViewAssertions.matches(withText("AirQuality(co=1.777, n02=25.9999, ozone=9.99999, pm10=52.24, pm25=21.943, s02=1.898, city=Bangalore, countryCode=IN, lat=11.98625, lng=77.550478, placeName=Race course road, postalCode=560020, state=Karnataka, updatedAt=Sat May 29 15:00:00 GMT+02:00 2021, division=Bangalore, aqi=72, aqiInfo=AqiInfo(pollutant=PM2.5, concentration=21.943, category=Moderate))")))
    }

    @Test
    fun test_init_screen() {
        Espresso.onView(ViewMatchers.withId(R.id.air_quality_content))
            .check(ViewAssertions.matches(withText("Hello World!")))
    }
}
