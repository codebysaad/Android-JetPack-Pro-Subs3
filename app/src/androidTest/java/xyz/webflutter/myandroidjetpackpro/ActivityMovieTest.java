package xyz.webflutter.myandroidjetpackpro;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import xyz.webflutter.myandroidjetpackpro.utils.ExpressoIdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ActivityMovieTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(ExpressoIdlingResource.getIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(ExpressoIdlingResource.getIdlingResource());
    }

    @Test
    public void toDetailActivityTest() {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        onView(withId(R.id.title_movie_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.title_movie_detail)).check(matches(withText("Joker")));
        onView(withId(R.id.release_date)).check(matches(withText("2019-10-02")));
    }
}
