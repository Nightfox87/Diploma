package ru.iteco.fmhandroid.ui.utils;

import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class RecyclerViewAssertions {

    public static ViewAssertion withRowContaining(final Matcher<View> viewMatcher) {
        assertNotNull(viewMatcher);

        return new ViewAssertion() {

            @Override
            public void check(View view, NoMatchingViewException noViewException) {
                if (noViewException != null) {
                    throw noViewException;
                }

                assertTrue(view instanceof RecyclerView);

                RecyclerView recyclerView = (RecyclerView) view;
                final RecyclerView.Adapter adapter = recyclerView.getAdapter();
                for (int position = 0; position < adapter.getItemCount(); position++) {
                    int itemType = adapter.getItemViewType(position);
                    RecyclerView.ViewHolder viewHolder = adapter.createViewHolder(recyclerView, itemType);
                    adapter.bindViewHolder(viewHolder, position);

                    if (viewHolderMatcher(hasDescendant(viewMatcher)).matches(viewHolder)) {
                        return; // Found a matching row
                    }
                }

                fail("No match found");
            }
        };
    }


    private static Matcher<RecyclerView.ViewHolder> viewHolderMatcher(final Matcher<View> itemViewMatcher) {
        return new TypeSafeMatcher<RecyclerView.ViewHolder>() {

            @Override
            public boolean matchesSafely(RecyclerView.ViewHolder viewHolder) {
                return itemViewMatcher.matches(viewHolder.itemView);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("holder with view: ");
                itemViewMatcher.describeTo(description);
            }
        };
    }

}
