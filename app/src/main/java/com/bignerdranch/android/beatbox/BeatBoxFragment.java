package com.bignerdranch.android.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.android.beatbox.databinding.FragmentBeatBoxBinding;
import com.bignerdranch.android.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

/**
 * Created by djn on 8/29/18.
 */

public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance() {
        return new BeatBoxFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflates a binding layout and returns the newly-created binding for that layout.
        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_beat_box, container, false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        //RecyclerView delegates the job of positioning items on the screen to a LayoutManager.
        binding.recyclerView.setAdapter(new SoundAdapter(mBeatBox.getSounds()));

        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBeatBox = new BeatBox(getActivity());
    }

    /**SoundHolder holds the View object to be displayer in a RecyclerView
     *
     */
    private class SoundHolder extends RecyclerView.ViewHolder {
        private ListItemSoundBinding mBinding;

        /**
         * Inflates the held view through binding, sets the ViewModle of the binding
         * @param binding
         */
        private SoundHolder(ListItemSoundBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.setViewModel(new SoundViewModel(mBeatBox));
        }

        public void bind(Sound sound) {
            mBinding.getViewModel().setSound(sound);
            mBinding.executePendingBindings();
        }
    }

    /**
     * getItemCount() is first called to return an item count. Then onCreateViewHolder is called
     * to create a new SoundHolder, inflating the view held by SoundHolder, then return it.
     * Finally, the RecyclerView calls onBindViewHolder, binding the model data for that position to
     * the ViewHolder's view (wiring up mSounds to SoundHolder).
     */
    private class SoundAdapter extends RecyclerView.Adapter<SoundHolder> {
        private List<Sound> mSounds;

        private SoundAdapter(List<Sound> sounds) {
            mSounds = sounds;
        }

        /**
         * Inflates the viewholder, gets the corresponding binding, creates a SoundHolder
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item_sound,
                    parent, false);
            return new SoundHolder(binding);
        }

        /**
         * binds the sound at a certain position to that SoundHolder
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(SoundHolder holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }
}
