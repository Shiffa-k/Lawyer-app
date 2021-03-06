package com.eweblog.fragment;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eweblog.MainAcitivity;
import com.eweblog.R;

/*
ABOUT US SCREEN
 */
public class AboutUS extends Fragment {

    LinearLayout llContactUs;


    public AboutUS() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_about_us, container, false);
        MainAcitivity.txtTitle.setText("About Us");

        llContactUs=(LinearLayout)rootview.findViewById(R.id.ll_call);

        llContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               dialog();
            }
        });
        return  rootview;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void dialog()
    {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_contact_us);
        TextView txtMobile=(TextView)dialog.findViewById(R.id.textView_mobile);
        TextView txtLandline=(TextView)dialog.findViewById(R.id.textView_landline);
        TextView txtCancel=(TextView)dialog.findViewById(R.id.textView_cancel);

        txtMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel:"+"+91-977-913-5856";
                intent.setData(Uri.parse(temp));
                startActivity(intent);
            }
        });
        txtLandline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "viableinfotech2016@gmail.com" });
                startActivity(Intent.createChooser(intent, ""));
            }
        });
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    if(getFragmentManager().getBackStackEntryCount() > 0) {

                            //CorporateUserMainActivity.txtTitle.setText("Home");
                        MainAcitivity.txtTitle.setText("Home");
                            getFragmentManager().popBackStack();

                    }

                    return true;

                }

                return false;
            }
        });
    }

}
