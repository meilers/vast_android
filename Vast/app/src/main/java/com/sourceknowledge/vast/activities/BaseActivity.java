package com.sourceknowledge.vast.activities;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.sourceknowledge.vast.util.NetworkUtil;


public class BaseActivity extends ActionBarActivity {

    private ProgressDialog mProgressDialog;


    @Override
    protected void onResume() {
        super.onResume();

        if(!NetworkUtil.isCurrentlyConnected(this))
        {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    public void showProgressDialog(int titleResource, int msgResource) {
        showProgressDialog(getString(titleResource), getString(msgResource));
    }

    public void showProgressDialog(String title, String message) {
        if( !this.isFinishing() ) {
            mProgressDialog = ProgressDialog.show(this, title, message);
            mProgressDialog.setCancelable(false);
        }
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
