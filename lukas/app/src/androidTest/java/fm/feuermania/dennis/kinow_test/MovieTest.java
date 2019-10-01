package fm.feuermania.dennis.kinow_test;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MovieTest {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void movieTest() {
        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.tab_movies), withContentDescription("Movies"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Movies"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Movies")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.image),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.testmovieOne),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.title), withText("Test Movie One"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.testmovieOne),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Test Movie One")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.description), withText("This movie is very very goooood and so nice omg"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.testmovieOne),
                                        0),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("This movie is very very goooood and so nice omg")));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.image2),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.testmovieTwo),
                                        0),
                                0),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.title2), withText("Test Movie Two"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.testmovieTwo),
                                        0),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("Test Movie Two")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.description2), withText("This movie is not as good as the other one but who cares"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.testmovieTwo),
                                        0),
                                2),
                        isDisplayed()));
        textView5.check(matches(withText("This movie is not as good as the other one but who cares")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.description2), withText("This movie is not as good as the other one but who cares"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.testmovieTwo),
                                        0),
                                2),
                        isDisplayed()));
        textView6.check(matches(withText("This movie is not as good as the other one but who cares")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
