package com.example.launcher.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class EspressoTest  extends ActivityInstrumentationTestCase2<MainActivity> {
    public EspressoTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }
    AudioManager audioManager;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();

        audioManager = (AudioManager)
                getActivity().getSystemService(Context.AUDIO_SERVICE);
        // Make sure the ringer mode is reset to normal
        audioManager.setRingerMode(
                AudioManager.RINGER_MODE_NORMAL);
    }

}
