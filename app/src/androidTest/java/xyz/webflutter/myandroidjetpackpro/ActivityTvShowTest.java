package xyz.webflutter.myandroidjetpackpro;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import xyz.webflutter.myandroidjetpackpro.testing.SingleFragment;
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowFragment;
import xyz.webflutter.myandroidjetpackpro.utils.ExpressoIdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ActivityTvShowTest {

    private final TvShowFragment tvShowFragment = new TvShowFragment();

    @Rule
    public final ActivityTestRule<SingleFragment> activityTestRule = new ActivityTestRule<>(SingleFragment.class);

    @Before
    public void setUp() throws Exception {
        activityTestRule.getActivity().setFragment(tvShowFragment);
        IdlingRegistry.getInstance().register(ExpressoIdlingResource.getIdlingResource());
    }

    @After
    public void tearDown() throws Exception {
        IdlingRegistry.getInstance().unregister(ExpressoIdlingResource.getIdlingResource());
    }
    @Test
    public void toDetailActivityTest() {
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        onView(withId(R.id.title_movie_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.title_movie_detail)).check(matches(withText("Rick and Morty")));
        onView(withId(R.id.release_date)).check(matches(withText("2013-12-02")));
    }
}