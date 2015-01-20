package com.sourceknowledge.vast.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.sourceknowledge.vast.models.Trailer;
import com.sourceknowledge.vast.models.spec.AppContent;
import com.sourceknowledge.vast.models.spec.RtbSpec;
import com.sourceknowledge.vast.models.vast.Vast;
import com.sourceknowledge.vast.rest.DownloadTrailerClient;
import com.sourceknowledge.vast.rest.DownloadVastTagClient;
import com.sourceknowledge.vast.rest.DownloadVastTagUriClient;
import com.sourceknowledge.vast.rest.GenericClientManager;
import com.sourceknowledge.vast.rest.MovieChickApiClientManager;
import com.sourceknowledge.vast.rest.VastUriApiClientManager;
import com.sourceknowledge.vast.rest.requests.VastRequest;

import java.util.ArrayList;

import retrofit.RetrofitError;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

/**
 * Created by omegatai on 14-11-27.
 */
public class FetchVastAndTrailerService extends IntentService {

    private static final String TAG = FetchVastAndTrailerService.class.getSimpleName();


    public final static class EXTRAS
    {
        public static final String IN_SPEC = "inSpec";

        public static final String OUT_VAST = "outVast";
        public static final String OUT_VAST_URI = "outVastUri";
        public static final String OUT_TRAILER = "outTrailer";

    }

    public final static class ACTIONS
    {
        public static final String FETCH_VAST_AND_TRAILER = "com.nimonik.EHSQ.services.FETCH_VAST_AND_TRAILER";
        public static final String FETCH_VAST_AND_TRAILER_COMPLETED = "com.nimonik.EHSQ.services.FETCH_VAST_AND_TRAILER_COMPLETED";

    }

    public FetchVastAndTrailerService()
    {
        super(TAG);

        setIntentRedelivery(false);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        String action = intent.getAction();
        Context c = getApplicationContext();
        RtbSpec spec = intent.getExtras().getParcelable(EXTRAS.IN_SPEC);

        if (ACTIONS.FETCH_VAST_AND_TRAILER.equals(action) && spec != null ) {


            DownloadVastTagUriClient vastUriClient = VastUriApiClientManager.INSTANCE.getClient(c, DownloadVastTagUriClient.class);
            DownloadVastTagClient vastClient;
            DownloadTrailerClient trailerClient = MovieChickApiClientManager.INSTANCE.getClient(c, DownloadTrailerClient.class);


            try {
                ArrayList<Trailer> trailers = trailerClient.downloadTrailer("json", 1, "mp4", "high");
                Trailer trailer = trailers.get(0);
                AppContent content = new AppContent();
                content.setId(trailer.getTitle().hashCode()+"");
                content.setTitle(trailer.getTitle());

                VastRequest productsRequest = new VastRequest(spec);
                String json = new Gson().toJson(productsRequest);
                TypedInput in = new TypedByteArray("application/json", json.getBytes("UTF-8"));

                Vast vastUri = vastUriClient.downloadVastClientUri(in);
                vastClient= GenericClientManager.INSTANCE.getClient(c, DownloadVastTagClient.class, vastUri.getAd().getWrapper().getVASTAdTagURI());
                Vast vast = vastClient.downloadVastClient();

                Intent resultIntent = new Intent(ACTIONS.FETCH_VAST_AND_TRAILER_COMPLETED);
                resultIntent.putExtra(EXTRAS.OUT_VAST_URI, vastUri);
                resultIntent.putExtra(EXTRAS.OUT_VAST, vast);
                resultIntent.putParcelableArrayListExtra(EXTRAS.OUT_TRAILER, trailers);
                sendBroadcast(resultIntent);
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
