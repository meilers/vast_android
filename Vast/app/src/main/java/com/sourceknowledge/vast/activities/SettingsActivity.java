package com.sourceknowledge.vast.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.sourceknowledge.vast.R;
import com.sourceknowledge.vast.models.Trailer;
import com.sourceknowledge.vast.models.spec.App;
import com.sourceknowledge.vast.models.spec.Device;
import com.sourceknowledge.vast.models.spec.DeviceGeo;
import com.sourceknowledge.vast.models.spec.RtbSpec;
import com.sourceknowledge.vast.models.spec.User;
import com.sourceknowledge.vast.models.spec.Video;
import com.sourceknowledge.vast.models.vast.Vast;
import com.sourceknowledge.vast.services.FetchVastAndTrailerService;
import com.sourceknowledge.vast.util.AppUtil;
import com.sourceknowledge.vast.util.CarrierUtil;
import com.sourceknowledge.vast.util.DeviceUtil;

import java.util.ArrayList;
import java.util.List;


public class SettingsActivity extends BaseActivity {

    private ImageView mPlayBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayBtn = (ImageView)findViewById(R.id.activity_main_play_btn);
        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog("Loading trailer...", "");
                fetchVastAndTrailer();
            }
        });
    }


}
