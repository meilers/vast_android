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
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.sourceknowledge.vast.R;
import com.sourceknowledge.vast.models.Trailer;
import com.sourceknowledge.vast.models.vast.Vast;

import java.io.IOException;

/**
 * Created by omegatai on 15-01-16.
 */
public class VideoActivity extends BaseActivity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnSeekCompleteListener, android.view.SurfaceHolder.Callback {

    private static final String TAG = VideoActivity.class.getSimpleName();

    public final static class EXTRAS
    {
        public static final String IN_VAST = "inVast";
        public static final String IN_TRAILER = "inTrailer";
    }

    private Vast mVast;
    private Trailer mTrailer;

    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() {

        @Override
        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {

            setFitToFillAspectRatio(mp, width, height);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVast = getIntent().getExtras().getParcelable(EXTRAS.IN_VAST);
        mTrailer = getIntent().getExtras().getParcelable(EXTRAS.IN_TRAILER);

        mSurfaceView = (SurfaceView)findViewById(R.id.video_activity_surfaceview);
        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(this);
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnCompletionListener(this);

        mMediaPlayer.setOnSeekCompleteListener(this);
        mMediaPlayer.setScreenOnWhilePlaying(true);
//        mMediaPlayer.setOnVideoSizeChangedListener(mOnVideoSizeChangedListener);
    }


    @Override
    protected void onStop() {
        if( mMediaPlayer != null )
        {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if( mMediaPlayer != null )
        {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }


    @Override
     public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            // setContentView(R.layout.view_lang);
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;
            android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) mSurfaceView.getLayoutParams();
            params.width = width;
            params.height=height;// -80 for android controls
            params.setMargins(0, 0, 0, 0);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            //setContentView(R.layout.view);
            DisplayMetrics displaymetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;
            android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) mSurfaceView.getLayoutParams();
            params.width = width;
            params.height=height / 3;
            params.setMargins(0, 0, 0, 0);
        }
    }


    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        playVideo(mTrailer.getUrl());
        mMediaPlayer.setDisplay(mSurfaceHolder);
    }

    public void surfaceDestroyed(SurfaceHolder holder)
    {

    }

    public void onPrepared(MediaPlayer mp)
    {
        if (!mMediaPlayer.isPlaying())
        {
            int surfaceView_Width = mSurfaceView.getWidth();
            int surfaceView_Height = mSurfaceView.getHeight();

            float video_Width = mMediaPlayer.getVideoWidth();
            float video_Height = mMediaPlayer.getVideoHeight();

            float ratio_width = surfaceView_Width/video_Width;
            float ratio_height = surfaceView_Height/video_Height;
            float aspectratio = video_Width/video_Height;

            ViewGroup.LayoutParams layoutParams = mSurfaceView.getLayoutParams();

            if (ratio_width > ratio_height){
                layoutParams.width = (int) (surfaceView_Height * aspectratio);
                layoutParams.height = surfaceView_Height;
            }else{
                layoutParams.width = surfaceView_Width;
                layoutParams.height = (int) (surfaceView_Width / aspectratio);
            }

            mSurfaceView.setLayoutParams(layoutParams);


            mMediaPlayer.start();
        }

    }

    public void onCompletion(MediaPlayer mp)
    {
        mp.stop();

        finish();
    }


    public void onSeekComplete(MediaPlayer mp)
    {

    }


    private void playVideo(final String url)
    {

        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {

                    mMediaPlayer.setDataSource(VideoActivity.this, Uri.parse(url) );
                    mMediaPlayer.prepareAsync();
                }
                catch (IllegalArgumentException e)
                {
                    Log.d("admin", "Error while playing video");
                    e.printStackTrace();
                    Log.i(TAG,"tag"+ e.getMessage());
                }
                catch (IllegalStateException e)
                {
                    Log.d("admin","Error1 while playing video");
                    e.printStackTrace();
                    Log.i(TAG, "tag"+e.getMessage());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    Log.d("admin","Error while playing video.Please, check your network connection");
                    Log.i(TAG, "tag"+e.getLocalizedMessage());
                }
            }
        }).start();
    }

    private void setFitToFillAspectRatio(MediaPlayer mp, int videoWidth, int videoHeight)
    {
        if(mp != null)
        {
            Integer screenWidth = getWindowManager().getDefaultDisplay().getWidth();
            Integer screenHeight = getWindowManager().getDefaultDisplay().getHeight();
            android.view.ViewGroup.LayoutParams videoParams = mSurfaceView.getLayoutParams();


            if (videoWidth > videoHeight)
            {
                videoParams.width = screenWidth;
                videoParams.height = screenWidth * videoHeight / videoWidth;
            }
            else
            {
                videoParams.width = screenHeight * videoWidth / videoHeight;
                videoParams.height = screenHeight;
            }


            mSurfaceView.setLayoutParams(videoParams);
        }
    }
}
