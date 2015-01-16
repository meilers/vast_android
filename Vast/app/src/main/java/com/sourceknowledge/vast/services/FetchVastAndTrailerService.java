package com.sourceknowledge.vast.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.google.gson.Gson;
import com.sourceknowledge.vast.models.Trailer;
import com.sourceknowledge.vast.models.spec.App;
import com.sourceknowledge.vast.models.spec.AppContent;
import com.sourceknowledge.vast.models.spec.Device;
import com.sourceknowledge.vast.models.spec.DeviceGeo;
import com.sourceknowledge.vast.models.spec.RtbSpec;
import com.sourceknowledge.vast.models.spec.User;
import com.sourceknowledge.vast.models.spec.Video;
import com.sourceknowledge.vast.models.vast.Vast;
import com.sourceknowledge.vast.rest.DownloadTrailerClient;
import com.sourceknowledge.vast.rest.DownloadVastTagClient;
import com.sourceknowledge.vast.rest.DownloadVastTagUriClient;
import com.sourceknowledge.vast.rest.MovieChickApiClientManager;
import com.sourceknowledge.vast.rest.VastApiClientManager;
import com.sourceknowledge.vast.rest.VastUriApiClientManager;
import com.sourceknowledge.vast.rest.requests.VastRequest;

import java.util.ArrayList;
import java.util.List;

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

        if (ACTIONS.FETCH_VAST_AND_TRAILER.equals(action) ) {


            DownloadVastTagUriClient vastUriClient = VastUriApiClientManager.INSTANCE.getClient(getApplicationContext(), DownloadVastTagUriClient.class);
            DownloadVastTagClient vastClient;
            DownloadTrailerClient trailerClient = MovieChickApiClientManager.INSTANCE.getClient(getApplicationContext(), DownloadTrailerClient.class);


            try {
                List<Video> videos = new ArrayList<>();
                List<String> mimes = new ArrayList<>();

                mimes.add("video/mp4");
                mimes.add("video/3gpp");
                mimes.add("video/x-m4v");

                Video video = new Video(900,900, mimes);
                videos.add(video);

                AppContent content = new AppContent("02752885df8d50fc2f489ad17fcab9e8", "Fury");
                App app = new App("88201","Bidder Test Mobile App","com.foo.mobileapp",content);

                DeviceGeo geo = new DeviceGeo(42.37899,-71.22799);
                Device device = new Device(0, "Apple", "iPhone", "iOS", "3.1.2", "ATT", 3,"166.137.138.18","Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_2_1 like Mac OS X; el-gr) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5",geo);

                User user = new User("ASDFJKL");

                RtbSpec spec = new RtbSpec(videos, app, device, user);

                VastRequest productsRequest = new VastRequest(spec);
                String json = new Gson().toJson(productsRequest);
                TypedInput in = new TypedByteArray("application/json", json.getBytes("UTF-8"));

                Vast vastUri = vastUriClient.downloadVastClientUri(in);
                vastClient= VastApiClientManager.INSTANCE.getClient(getApplicationContext(), DownloadVastTagClient.class, vastUri.getAd().getWrapper().getVASTAdTagURI());
                Vast vast = vastClient.downloadVastClient();
                ArrayList<Trailer> trailers = trailerClient.downloadTrailer("json", 1, "mp4", "high");

                Intent resultIntent = new Intent(ACTIONS.FETCH_VAST_AND_TRAILER_COMPLETED);
                resultIntent.putExtra(EXTRAS.OUT_VAST_URI, vastUri);
                resultIntent.putExtra(EXTRAS.OUT_VAST, vast);
                resultIntent.putParcelableArrayListExtra(EXTRAS.OUT_TRAILER, trailers);
                sendBroadcast(intent);
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
