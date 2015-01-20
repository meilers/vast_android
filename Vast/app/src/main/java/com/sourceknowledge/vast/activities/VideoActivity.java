package com.sourceknowledge.vast.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.sourceknowledge.vast.R;
import com.sourceknowledge.vast.models.Trailer;
import com.sourceknowledge.vast.models.vast.MediaFile;
import com.sourceknowledge.vast.models.vast.Tracking;
import com.sourceknowledge.vast.models.vast.Vast;
import com.sourceknowledge.vast.services.TrackService;
import com.sourceknowledge.vast.util.UiUtil;

/**
 * Created by omegatai on 15-01-16.
 */
public class VideoActivity extends BaseActivity implements MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener,View.OnTouchListener {

    private static final String TAG = VideoActivity.class.getSimpleName();

    public final static class EXTRAS
    {
        public static final String IN_VAST = "inVast";
        public static final String IN_VAST_URI = "inVastUri";
        public static final String IN_TRAILER = "inTrailer";
    }

    private enum ViewingState
    {
        AD, TRAILER
    }


    private Vast mVastUri;
    private Vast mVast;
    private Trailer mTrailer;
    private ViewingState mViewingState = ViewingState.AD;

    private VideoView mVV;
    private MediaController mMediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVastUri = getIntent().getExtras().getParcelable(EXTRAS.IN_VAST_URI);
        mVast = getIntent().getExtras().getParcelable(EXTRAS.IN_VAST);
        mTrailer = getIntent().getExtras().getParcelable(EXTRAS.IN_TRAILER);

        mVV = (VideoView)findViewById(R.id.activity_video_videoview);
        mVV.setOnCompletionListener(this);
        mVV.setOnPreparedListener(this);
        mVV.setOnTouchListener(this);

        int screenWidth = UiUtil.getScreenMetrics(this).heightPixels; // we're in landscape
        int adWidth;
        int lastSavedWidth = 0;
        String adUrl = "";

        for( MediaFile file : mVast.getAd().getInLine().getCreatives().getCreative().getLinear().getMediaFiles().getMediaFiles() )
        {
            adWidth = Integer.valueOf(file.getWidth());

            if( adWidth <= screenWidth && adWidth > lastSavedWidth && file.getType().equals("video/mp4") ) {
                adUrl = file.getValue();
                lastSavedWidth = adWidth;
            }
        }

        mVV.setVideoURI(Uri.parse(adUrl));
        mVV.start();


        // Track impressions
        for( String impression : mVastUri.getAd().getWrapper().getImpressions() )
        {
            track(impression);
        }

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
                // Track events
                String startUrl = "";
                String firstQuartile = "";
                String midpointUrl = "";
                String thirdQuartile = "";
                String completeUrl = "";

                for( Tracking track : mVastUri.getAd().getWrapper().getCreatives().getCreative().getLinear().getTrackingEvents().getTrackings() )
                {
                    if( track.getEvent().equals("start"))
                    {
                        startUrl = track.getValue();
                    }
                    else if( track.getEvent().equals("firstQuartile"))
                    {
                        firstQuartile = track.getValue();
                    }
                    else if( track.getEvent().equals("midpoint"))
                    {
                        midpointUrl = track.getValue();
                    }
                    else if( track.getEvent().equals("thirdQuartile"))
                    {
                        thirdQuartile = track.getValue();
                    }
                    else if( track.getEvent().equals("complete"))
                    {
                        completeUrl = track.getValue();
                    }
                }

                track(startUrl);
                track(firstQuartile);
                track(midpointUrl);
                track(thirdQuartile);
                track(completeUrl);

                // Change state
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

        // Track progress
        float progress =  (float)(mVV.getCurrentPosition() / mVV.getDuration());

        String startUrl = "";
        String firstQuartile = "";
        String midpointUrl = "";
        String thirdQuartile = "";

        for( Tracking track : mVastUri.getAd().getWrapper().getCreatives().getCreative().getLinear().getTrackingEvents().getTrackings() )
        {
            if( track.getEvent().equals("start"))
            {
                startUrl = track.getValue();
            }
            else if( track.getEvent().equals("firstQuartile"))
            {
                firstQuartile = track.getValue();
            }
            else if( track.getEvent().equals("midpoint"))
            {
                midpointUrl = track.getValue();
            }
            else if( track.getEvent().equals("thirdQuartile"))
            {
                thirdQuartile = track.getValue();
            }
        }

        track(startUrl);

        if( progress >= 0.25 )
        {
            track(firstQuartile);

            if( progress >= 0.5 )
            {
                track(midpointUrl);

                if( progress >= 0.75 )
                {
                    track(thirdQuartile);
                }
            }
        }

        // Track clicks
        for( String click : mVastUri.getAd().getWrapper().getCreatives().getCreative().getLinear().getVideoClicks().getClickTrackings() )
        {
            track(click);
        }


        String url = mVast.getAd().getInLine().getCreatives().getCreative().getLinear().getVideoClicks().getClickThrough();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        finish();

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

    private void track( String url )
    {
        Intent intent = new Intent(this, TrackService.class);
        intent.setAction(TrackService.ACTIONS.TRACK);
        intent.putExtra(TrackService.EXTRAS.IN_TRACK_URL, url);
        startService(intent);
    }
}
