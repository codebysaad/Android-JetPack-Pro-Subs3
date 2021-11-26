package xyz.webflutter.myandroidjetpackpro.ui.detail.movie;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import xyz.webflutter.myandroidjetpackpro.R;
import xyz.webflutter.myandroidjetpackpro.ui.movie.MovieModels;
import xyz.webflutter.myandroidjetpackpro.utils.DummyData;
import xyz.webflutter.myandroidjetpackpro.utils.ExpressoIdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DetailMovieActivityTest {

    private final MovieModels movieModels = DummyData.getMovieModels();

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityTestRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class){
        @Override
        protected Intent getActivityIntent(){
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(context,DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_ID, movieModels.getId());
            return intent;
        }
    };

    @Before
    public void setUp() throws Exception {
        IdlingRegistry.getInstance().register(ExpressoIdlingResource.getIdlingResource());
    }

    @After
    public void tearDown() throws Exception {
        IdlingRegistry.getInstance().unregister(ExpressoIdlingResource.getIdlingResource());
    }

    @Test
    public void loadMovie(){
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.title_movie_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.overview_movie_detail)).check(matches(isDisplayed()));
    }
}