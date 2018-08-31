package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
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
 * mSoundPool - A SoundPool for loading and playing audio
 */
public class BeatBox {
    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssests;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        mAssests = context.getAssets(); //returns an AssetManager instance for the application's package
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
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

        /*
         generates a Sound object for each soundfile, sets its soundId, adds to mSounds
          */
        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound" + filename, ioe);
            }
        }
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssests.openFd(sound.getAssetPath());
        // gets a FileDescriptor for reading the file
        int soundId = mSoundPool.load(afd, 1);
        //1: priority
        sound.setSoundId(soundId);
    }

    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
        //volumeRange = 0.0 to 1.0; no loop; normal rate
    }

    /**
     * Release all memory and native resources used by the SoundPool object
     */
    public void release() {
        mSoundPool.release();
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
