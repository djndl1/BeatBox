package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by djn on 8/30/18.
 */

/**
 * A BeatBox class, loading sounds from the SOUNDS_FOLDER. A getter method is here to retrieve these
 * sounds.
 * mAssests - an AssetManager
 * mSounds - A list of sound objects
 */
public class BeatBox {
    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";

    private AssetManager mAssests;
    private List<Sound> mSounds = new ArrayList<>();

    public BeatBox(Context context) {
        mAssests = context.getAssets(); //returns an AssetManager instance for the application's package
        loadSounds();
    }

    /**
     * Loads sounds into mSounds by their soundnames
     */
    private void loadSounds() {
        String[] soundNames;

        //Puts the names of all sound files into soundNames
        try {
            soundNames = mAssests.list(SOUNDS_FOLDER); //returns a list of filenames under the path
            Log.i(TAG, "Found " + soundNames.length + " sounds ");
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }

        //Obtaining a list of sound objects from the pathnames
        for (String filename : soundNames) {
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            mSounds.add(sound);
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
