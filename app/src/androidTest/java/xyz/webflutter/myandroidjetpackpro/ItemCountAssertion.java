package xyz.webflutter.myandroidjetpackpro;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ItemCountAssertion implements ViewAssertion {
    private final int expectedCount;
    public ItemCountAssertion (int expectedCount){
        this.expectedCount = expectedCount;
    }
    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null){
            throw noViewFoundException;
        }
        RecyclerView rv = (RecyclerView) view;
        RecyclerView.Adapter adapter = rv.getAdapter();
        assertNotNull(adapter);
        assertThat(adapter.getItemCount(), is(expectedCount));
    }
}
