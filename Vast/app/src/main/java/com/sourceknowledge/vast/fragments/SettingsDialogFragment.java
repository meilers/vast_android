package com.sourceknowledge.vast.fragments;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.sourceknowledge.vast.R;
import com.sourceknowledge.vast.managers.DebugManager;
import com.sourceknowledge.vast.managers.ZoneIdManager;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by omegatai on 2014-09-08.
 */
public class SettingsDialogFragment extends DialogFragment
{
    private EditText mZoneIdEt;
    private TextView mBackTv;
    private Button mYesBtn;
    private Button mNoBtn;

    public static SettingsDialogFragment newInstance() {
        SettingsDialogFragment f = new SettingsDialogFragment();

        f.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Dialog);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_settings, container, false);

        mZoneIdEt = (EditText)view.findViewById(R.id.fragment_dialog_settings_zone_id_et);
        mBackTv = (TextView)view.findViewById(R.id.fragment_dialog_settings_back_tv);
        mYesBtn = (Button)view.findViewById(R.id.fragment_dialog_settings_yes_btn);
        mNoBtn = (Button)view.findViewById(R.id.fragment_dialog_settings_no_btn);

        mBackTv.setText("< BACK");
        mBackTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        mYesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYesBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_black_rectangle_white_border));
                mNoBtn.setBackgroundColor(getResources().getColor(android.R.color.black));

                DebugManager.INSTANCE.setDebug(true);
            }
        });

        mNoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNoBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_black_rectangle_white_border));
                mYesBtn.setBackgroundColor(getResources().getColor(android.R.color.black));

                DebugManager.INSTANCE.setDebug(false);

            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        if( ZoneIdManager.INSTANCE.getZoneId() != null )
        {
            mZoneIdEt.setText(ZoneIdManager.INSTANCE.getZoneId());
        }

        if( DebugManager.INSTANCE.isDebug() )
        {
            mYesBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_black_rectangle_white_border));
            mNoBtn.setBackgroundColor(getResources().getColor(android.R.color.black));
        }
        else
        {
            mNoBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_black_rectangle_white_border));
            mYesBtn.setBackgroundColor(getResources().getColor(android.R.color.black));
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if( !mZoneIdEt.getText().toString().isEmpty() )
        {
            ZoneIdManager.INSTANCE.setZoneId(mZoneIdEt.getText().toString());
        }
    }
}
