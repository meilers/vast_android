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
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
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

    private Vast mVast;
    private Trailer mTrailer;

    private VideoView mVV;

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

        mVV.setVideoURI(Uri.parse(mTrailer.getUrl()));

        mVV.start();
    }

    public void stopPlaying() {
        mVV.stopPlayback();
        this.finish();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        finish();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        stopPlaying();
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setLooping(true);
    }
}
