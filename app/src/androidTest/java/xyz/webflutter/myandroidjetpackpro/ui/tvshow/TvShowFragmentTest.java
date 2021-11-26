package xyz.webflutter.myandroidjetpackpro.ui.tvshow;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import xyz.webflutter.myandroidjetpackpro.ItemCountAssertion;
import xyz.webflutter.myandroidjetpackpro.R;
import xyz.webflutter.myandroidjetpackpro.testing.SingleFragment;
import xyz.webflutter.myandroidjetpackpro.utils.ExpressoIdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class TvShowFragmentTest {
    @Rule
    public final ActivityTestRule<SingleFragment> activityTestRule = new ActivityTestRule<>(SingleFragment.class);
    private final TvShowFragment tvShowFragment = new TvShowFragment();

    @Before
    public void setUp(){
        activityTestRule.getActivity().setFragment(tvShowFragment);
        IdlingRegistry.getInstance().register(ExpressoIdlingResource.getIdlingResource());
    }

    @After
    public void tearDown(){
        IdlingRegistry.getInstance().unregister(ExpressoIdlingResource.getIdlingResource());
    }

    @Test
    public void loadTvShow(){
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).check(new ItemCountAssertion(20));
    }
}