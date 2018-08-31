package com.bignerdranch.android.beatbox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

/**
 * Created by djn on 8/31/18.
 */
public class SoundViewModelTest {

    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        mBeatBox = mock(BeatBox.class); //mocks a BeatBox, not a real one
        mSound = new Sound("assetPath");
        mSubject = new SoundViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }

    @Test
    public void exposeSoundNameAsTitle() {
        assertThat(mSubject.getTitle(), is(mSound.getName()));
    }
}