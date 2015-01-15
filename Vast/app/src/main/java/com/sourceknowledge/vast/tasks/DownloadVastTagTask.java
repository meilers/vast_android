package com.sourceknowledge.vast.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.sourceknowledge.vast.VSTApplication;
import com.sourceknowledge.vast.models.App;
import com.sourceknowledge.vast.models.AppContent;
import com.sourceknowledge.vast.models.Device;
import com.sourceknowledge.vast.models.DeviceGeo;
import com.sourceknowledge.vast.models.RtbSpec;
import com.sourceknowledge.vast.models.User;
import com.sourceknowledge.vast.models.Video;
import com.sourceknowledge.vast.models.vast.Vast;
import com.sourceknowledge.vast.rest.VastApiClientManager;
import com.sourceknowledge.vast.rest.DownloadVastTagClient;
import com.sourceknowledge.vast.rest.requests.VastRequest;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

/**
 * Created by omegatai on 14-11-26.
 */
public class DownloadVastTagTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        Context context = VSTApplication.getContext();

        DownloadVastTagClient client = VastApiClientManager.INSTANCE.getClient(context, DownloadVastTagClient.class);


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

            Vast v = client.downloadVastClient(in);

            return null;
        }
        catch (RetrofitError e)
        {
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}