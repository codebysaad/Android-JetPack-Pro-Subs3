package xyz.webflutter.myandroidjetpackpro.ui.detail.tvshow;

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
import xyz.webflutter.myandroidjetpackpro.ui.tvshow.TvShowModels;
import xyz.webflutter.myandroidjetpackpro.utils.DummyData;
import xyz.webflutter.myandroidjetpackpro.utils.ExpressoIdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class DetailTvShowActivityTest {
    private final TvShowModels tvShowModels = DummyData.getTvShowModels();

    @Rule
    public ActivityTestRule<DetailTvShowActivity> activityTestRule = new ActivityTestRule<DetailTvShowActivity>(DetailTvShowActivity.class){
        @Override
        protected Intent getActivityIntent(){
            Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent = new Intent(context,DetailTvShowActivity.class);
            intent.putExtra(DetailTvShowActivity.EXTRA_ID, tvShowModels.getId());
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
    public void loadTvShow(){
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()));
        onView(withId(R.id.title_movie_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.release_date)).check(matches(isDisplayed()));
        onView(withId(R.id.overview_movie_detail)).check(matches(isDisplayed()));
    }
}