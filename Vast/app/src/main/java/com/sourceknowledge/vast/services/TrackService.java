package com.sourceknowledge.vast.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.sourceknowledge.vast.managers.DebugManager;
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

        if(!DebugManager.INSTANCE.isDebug())
        {

            try {
                String action = intent.getAction();
                Context c = getApplicationContext();
                String trackUrl = intent.getExtras().getString(EXTRAS.IN_TRACK_URL);

                // TODO: get auction price and auction id
                trackUrl = trackUrl.replace("${AUCTION_ID}", "1");
                trackUrl = trackUrl.replace("${AUCTION_PRICE}", "test-auction-123");
                trackUrl = java.net.URLDecoder.decode(trackUrl, "UTF-8");

                String[] splits = trackUrl.split("/", -1);
                String path = splits[splits.length-1];

                String baseTrackUrl = trackUrl.substring(0,trackUrl.length()-path.length()-1);

                if (ACTIONS.TRACK.equals(action) ) {

                    TrackClient vastClient = GenericClientManager.INSTANCE.getClient(c, TrackClient.class, baseTrackUrl);
                    vastClient.trackAdProgress(path);
                }
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
