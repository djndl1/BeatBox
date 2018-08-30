package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;

/**
 * Created by djn on 8/30/18.
 */

public class BeatBox {
    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";

    private AssetManager mAssests;

    public BeatBox(Context context) {
        mAssests = context.getAssets(); //returns an AssetManager instance for the application's package
        loadSounds();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = mAssests.list(SOUNDS_FOLDER); //returns a list of filenames under the path
            Log.i(TAG, "Found " + soundNames.length + " sounds ");
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list assets", ioe);
            return;
        }
    }
}
