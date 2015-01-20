package com.sourceknowledge.vast.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.location.Location;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import com.google.android.gms.location.LocationServices;
import com.sourceknowledge.vast.R;
import com.sourceknowledge.vast.models.Trailer;
import com.sourceknowledge.vast.models.spec.App;
import com.sourceknowledge.vast.models.spec.AppContent;
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
import com.sourceknowledge.vast.util.UiUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements LocationListener {

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;

    private boolean mUpdatesRequested = true;

    private Location mLocation;

    private GoogleApiClient.ConnectionCallbacks mServicesConnectedListener = new GoogleApiClient.ConnectionCallbacks() {

        @Override
        public void onConnected(Bundle connectionHint) {
            if (mUpdatesRequested) {
                mLocationRequest = LocationRequest.create();
                mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
                mLocationRequest.setInterval(1000); // Update location every second

                LocationServices.FusedLocationApi.requestLocationUpdates(
                        mGoogleApiClient, mLocationRequest, MainActivity.this);
            }
        }

        @Override
        public void onConnectionSuspended(int i) {

        }

    };

    private GoogleApiClient.OnConnectionFailedListener mServicesConnectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(ConnectionResult result) {
            if (result.hasResolution()) {
                try {
                    // Start an Activity that tries to resolve the error
                    result.startResolutionForResult(
                            MainActivity.this,
                            CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
                } catch (IntentSender.SendIntentException e) {
                    // Log the error
                    e.printStackTrace();
                }
            } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
//                showGpsAlert();
            }
        }
    };

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(FetchVastAndTrailerService.ACTIONS.FETCH_VAST_AND_TRAILER_COMPLETED)) {


                hideProgressDialog();

                Vast vastUri = intent.getExtras().getParcelable(FetchVastAndTrailerService.EXTRAS.OUT_VAST_URI);
                Vast vast = intent.getExtras().getParcelable(FetchVastAndTrailerService.EXTRAS.OUT_VAST);
                List<Trailer> trailers = intent.getExtras().getParcelableArrayList(FetchVastAndTrailerService.EXTRAS.OUT_TRAILER);

                Trailer trailer = trailers.get(0);
                Intent videoIntent = new Intent(MainActivity.this, VideoActivity.class);
                videoIntent.putExtra(VideoActivity.EXTRAS.IN_VAST_URI, vastUri);
                videoIntent.putExtra(VideoActivity.EXTRAS.IN_VAST, vast);
                videoIntent.putExtra(VideoActivity.EXTRAS.IN_TRAILER, trailer);
                startActivity(videoIntent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(mServicesConnectedListener)
                .addOnConnectionFailedListener(mServicesConnectionFailedListener)
                .build();
    }



    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        mGoogleApiClient.disconnect();
        super.onStop();
    }



    @Override
    public void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(FetchVastAndTrailerService.ACTIONS.FETCH_VAST_AND_TRAILER_COMPLETED);

        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();

        unregisterReceiver(mBroadcastReceiver);

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
            showProgressDialog("Loading trailer...", "");
            fetchVastAndTrailer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void fetchVastAndTrailer()
    {
        Context c = getApplicationContext();
        List<Video> videos = new ArrayList<>();
        List<String> mimes = new ArrayList<>();

        mimes.add("video/mp4");
        mimes.add("video/3gpp");
        mimes.add("video/x-m4v");

        Video video = new Video(900,900, mimes);
        videos.add(video);

        App app = new App( AppUtil.getAppId(), AppUtil.getAppName(c),AppUtil.getPackageName(c), null);
        DeviceGeo geo = null;

        if( mLocation != null )
            geo = new DeviceGeo(mLocation.getLatitude(), mLocation.getLongitude());

        Device device = new Device( DeviceUtil.getDnt(),
                DeviceUtil.getMake(),
                DeviceUtil.getModel(),
                DeviceUtil.getOs(),
                DeviceUtil.getOsVersion(),
                CarrierUtil.getCarrier(c),
                DeviceUtil.getConnectionType(c),
                DeviceUtil.getIpAddress(c),
                DeviceUtil.getUserAgent(c),
                geo);

        User user = new User(DeviceUtil.getDeviceId(this));

        RtbSpec spec = new RtbSpec(videos, app, device, user);



        Intent intent = new Intent(this, FetchVastAndTrailerService.class);
        intent.setAction(FetchVastAndTrailerService.ACTIONS.FETCH_VAST_AND_TRAILER);
        intent.putExtra(FetchVastAndTrailerService.EXTRAS.IN_SPEC, spec);
        startService(intent);
    }

    @Override
    public void onLocationChanged(Location location) {

        mLocation = location;
    }
}
