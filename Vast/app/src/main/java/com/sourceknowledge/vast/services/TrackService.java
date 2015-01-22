package com.sourceknowledge.vast.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.sourceknowledge.vast.rest.GenericClientManager;
import com.sourceknowledge.vast.rest.TrackClient;

import retrofit.RetrofitError;

/**
 * Created by omegatai on 14-11-27.
 */
public class TrackService extends IntentService {

    private static final String TAG = TrackService.class.getSimpleName();


    public final static class EXTRAS
    {
        public static final String IN_TRACK_URL = "inTrackUrl";

    }

    public final static class ACTIONS
    {
        public static final String TRACK = "com.nimonik.EHSQ.services.TRACK";
    }

    public TrackService()
    {
        super(TAG);

        setIntentRedelivery(false);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        String action = intent.getAction();
        Context c = getApplicationContext();
        String trackUrl = intent.getExtras().getString(EXTRAS.IN_TRACK_URL);

        // TODO: get auction price and auction id
        trackUrl = trackUrl.replace("${AUCTION_ID}", "1");
        trackUrl = trackUrl.replace("${AUCTION_PRICE}", "test-auction-123");

        if (ACTIONS.TRACK.equals(action) && trackUrl != null ) {

            TrackClient vastClient = GenericClientManager.INSTANCE.getClient(c, TrackClient.class, trackUrl);


            try {
                vastClient.trackAdProgress();
            }
            catch (RetrofitError e)
            {
                e.printStackTrace();
            }
            catch(Exception e) {
                e.printStackTrace();
            }




        }
    }
}
