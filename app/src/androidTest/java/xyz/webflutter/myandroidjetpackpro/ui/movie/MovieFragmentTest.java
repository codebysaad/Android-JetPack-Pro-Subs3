package xyz.webflutter.myandroidjetpackpro.ui.movie;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import xyz.webflutter.myandroidjetpackpro.ItemCountAssertion;
import xyz.webflutter.myandroidjetpackpro.R;
import xyz.webflutter.myandroidjetpackpro.testing.SingleFragment;
import xyz.webflutter.myandroidjetpackpro.utils.ExpressoIdlingResource;

public class MovieFragmentTest {
    @Rule
    public final ActivityTestRule<SingleFragment> activityTestRule = new ActivityTestRule<>(SingleFragment.class);
    private final MovieFragment movieFragment = new MovieFragment();

    @Before
    public void setUp(){
        IdlingRegistry.getInstance().register(ExpressoIdlingResource.getIdlingResource());
        activityTestRule.getActivity().setFragment(movieFragment);
    }

    @After
    public void tearDown(){
        IdlingRegistry.getInstance().unregister(ExpressoIdlingResource.getIdlingResource());
    }

    @Test
    public void loadMovie(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).check(new ItemCountAssertion(20));
    }
}