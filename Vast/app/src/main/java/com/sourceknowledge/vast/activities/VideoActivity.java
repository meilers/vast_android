package com.sourceknowledge.vast.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.sourceknowledge.vast.R;
import com.sourceknowledge.vast.models.Trailer;
import com.sourceknowledge.vast.models.vast.Vast;

import java.io.IOException;

/**
 * Created by omegatai on 15-01-16.
 */
public class VideoActivity extends BaseActivity implements MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener,View.OnTouchListener {

    private static final String TAG = VideoActivity.class.getSimpleName();

    public final static class EXTRAS
    {
        public static final String IN_VAST = "inVast";
        public static final String IN_TRAILER = "inTrailer";
    }

    private enum ViewingState
    {
        AD, TRAILER
    }


    private Vast mVast;
    private Trailer mTrailer;
    private ViewingState mViewingState = ViewingState.AD;

    private VideoView mVV;
    private MediaController mMediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVast = getIntent().getExtras().getParcelable(EXTRAS.IN_VAST);
        mTrailer = getIntent().getExtras().getParcelable(EXTRAS.IN_TRAILER);

        mVV = (VideoView)findViewById(R.id.activity_video_videoview);
        mVV.setOnCompletionListener(this);
        mVV.setOnPreparedListener(this);
        mVV.setOnTouchListener(this);



        mVV.setVideoURI(Uri.parse(mVast.getAd().getInLine().getCreatives().getCreative().getLinear().getMediaFiles().getMediaFiles().get(0).getValue()));
        mVV.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if( mVV != null )
        {
            mVV.stopPlayback();
            mVV = null;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

        switch (mViewingState)
        {

            case AD:
                mViewingState = ViewingState.TRAILER;

                // Let user control trailer
                mMediaController = new MediaController(this)
                {
                    public boolean dispatchKeyEvent(KeyEvent event)
                    {
                        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                            ((Activity) getContext()).finish();

                        return super.dispatchKeyEvent(event);
                    }

                    @Override
                    public void hide() {
//                super.hide();
                    }
                };
                mMediaController.setMediaPlayer(mVV);
                mMediaController.setAnchorView(mVV);
                mVV.setMediaController(mMediaController);

                // No listening on click
                mVV.setOnTouchListener(null);

                // Trailer source
                mVV.setVideoURI(Uri.parse(mTrailer.getUrl()));
                mVV.start();


                break;

            case TRAILER:
                finish();
                break;
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int position = mVV.getCurrentPosition();
        Toast.makeText(this, "Position: " + position + ", Duration: " + mVV.getDuration(), Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
//        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
//            @Override
//            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//                                                        /*
//                                                         *  add media controller
//                                                         */
//                mMediaController = new MediaController(VideoActivity.this);;
//                mVV.setMediaController(mMediaController);
//                                                        /*
//                                                         * and set its position on screen
//                                                         */
//                mMediaController.setAnchorView(mVV);
//            }
//        });
    }
}
