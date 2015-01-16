package com.sourceknowledge.vast;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.sourceknowledge.vast.models.spec.App;
import com.sourceknowledge.vast.models.spec.AppContent;
import com.sourceknowledge.vast.models.spec.Device;
import com.sourceknowledge.vast.models.spec.DeviceGeo;
import com.sourceknowledge.vast.models.spec.RtbSpec;
import com.sourceknowledge.vast.models.spec.User;
import com.sourceknowledge.vast.models.spec.Video;
import com.sourceknowledge.vast.services.FetchVastAndTrailerService;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onStart() {
        super.onStart();

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


        Intent intent = new Intent(this, FetchVastAndTrailerService.class);
        intent.setAction(FetchVastAndTrailerService.ACTIONS.FETCH_VAST_AND_TRAILER);
        intent.putExtra(FetchVastAndTrailerService.EXTRAS.IN_SPEC, spec);
        startService(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
