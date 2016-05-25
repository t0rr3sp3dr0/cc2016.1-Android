package com.t0rr3sp3dr0.cc20161;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.t0rr3sp3dr0.cc20161.dummy.DummyContent;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

                try {
                    mediaPlayer = MediaPlayer.create(getContext(), getResources().getIdentifier(mItem.id, "raw", "com.t0rr3sp3dr0.cc20161"));
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
                } catch (Exception e) {
                    fab.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            final RelativeLayout call = (RelativeLayout) rootView.findViewById(R.id.call);
            final RelativeLayout message = (RelativeLayout) rootView.findViewById(R.id.message);
            final RelativeLayout mail = (RelativeLayout) rootView.findViewById(R.id.mail);
            final RelativeLayout web = (RelativeLayout) rootView.findViewById(R.id.web);
            final RelativeLayout contact = (RelativeLayout) rootView.findViewById(R.id.contact);

            if (mItem.details.equals("")) {
                call.setVisibility(View.GONE);
                message.setVisibility(View.GONE);
            } else {
                ((TextView) rootView.findViewById(R.id.callField)).setText(mItem.details);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + mItem.details));
                        startActivity(intent);
                    }
                });

                ((TextView) rootView.findViewById(R.id.messageField)).setText(mItem.details);
                message.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("smsto:" + mItem.details));
                        startActivity(intent);
                    }
                });
            }

            ((TextView) rootView.findViewById(R.id.mailField)).setText(mItem.id + "@cin.ufpe.br");
            mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("message/rfc822");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mItem.id + "@cin.ufpe.br"});
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                        mail.setVisibility(View.GONE);
                        Snackbar.make(getView(), "Aplicativo de email não encontrado", Snackbar.LENGTH_LONG).show();
                    }
                }
            });

            ((TextView) rootView.findViewById(R.id.webField)).setText("https://cin.ufpe.br/~" + mItem.id);
            web.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://cin.ufpe.br/~" + mItem.id));
                    startActivity(intent);
                }
            });

            ((TextView) rootView.findViewById(R.id.contactField)).setText(mItem.content);
            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, mItem.content)
                            .putExtra(ContactsContract.Intents.Insert.PHONE, mItem.details)
                            .putExtra(ContactsContract.Intents.Insert.EMAIL, mItem.id + "@cin.ufpe.br")
                            .putExtra(ContactsContract.Data.IS_SUPER_PRIMARY, 1)
                            .putExtra(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
                            .putExtra(ContactsContract.CommonDataKinds.Photo.PHOTO, contactPicture(getResources().getIdentifier(mItem.id, "drawable", "com.t0rr3sp3dr0.cc20161")));
                    startActivity(intent);
                }
            });
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    public byte[] contactPicture(int drawableId){
        Drawable drawable = ContextCompat.getDrawable(getContext(), drawableId);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] bitMapData = stream.toByteArray();
        return bitMapData;
    }
}
