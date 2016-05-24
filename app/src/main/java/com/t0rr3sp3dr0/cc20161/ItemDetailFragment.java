package com.t0rr3sp3dr0.cc20161;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.t0rr3sp3dr0.cc20161.dummy.DummyContent;

import java.lang.reflect.Field;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    private FloatingActionButton fab;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
                appBarLayout.setBackgroundResource(getResources().getIdentifier(mItem.id, "drawable", "com.t0rr3sp3dr0.cc20161"));

                AppBarLayout appBar = (AppBarLayout) activity.findViewById(R.id.app_bar);
                ViewGroup.LayoutParams params = appBar.getLayoutParams();
                params.width = getResources().getDisplayMetrics().widthPixels;
                params.height = params.width;
                appBar.setLayoutParams(params);

                fab = (FloatingActionButton) activity.findViewById(R.id.fab);
                mediaPlayer = MediaPlayer.create(getContext(), getResources().getIdentifier(mItem.id, "raw", "com.t0rr3sp3dr0.cc20161"));

                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mediaPlayer.isPlaying()) {
                            fab.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                            mediaPlayer.pause();
                        } else {
                            fab.setImageResource(R.drawable.ic_pause_white_48dp);
                            mediaPlayer.start();
                        }
                    }
                });

                if (mediaPlayer.isPlaying())
                    fab.setImageResource(R.drawable.ic_pause_white_48dp);
                else
                    fab.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        fab.setImageResource(R.drawable.ic_play_arrow_white_48dp);
                    }
                });
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }
}
