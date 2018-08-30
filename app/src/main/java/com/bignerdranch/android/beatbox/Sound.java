package com.bignerdranch.android.beatbox;

/**
 * Created by djn on 8/30/18.
 */

/**
 * Records the filename and the pathname of a sound file
 */
public class Sound {
    private String mAssetPath;
    private String mName;

    /**
     * Constructor from the pathname.
     * gets the file name from the path name
     * @param assetPath pathname
     */
    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length-1];
        mName = filename.replace(".wav","");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }
}
