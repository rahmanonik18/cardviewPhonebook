package com.tahmidrasif.phonebook.Activity;

import android.Manifest;
import android.content.Context;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tahmidrasif.phonebook.Adapter.CustomAdapter;
import com.tahmidrasif.phonebook.Model.DataModel;

import java.util.ArrayList;

import com.tahmidrasif.phonebook.Model.MyData;
import com.tahmidrasif.phonebook.R;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    public static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;
    Button call_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //myOnClickListener = new MyOnClickListener(this);
        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;
        Button call_user,btn_email,btn_sms;

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewVersion = (TextView) findViewById(R.id.textViewVersion);
        imageViewIcon = (ImageView)findViewById(R.id.imageView);
        call_user = findViewById(R.id.call_user);
        btn_email = findViewById(R.id.btn_email);
        btn_sms = findViewById(R.id.btn_sms);

/*
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
*/

        call_user.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                onCall();
            }
        });

        btn_email.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "onik@opsonin.net","email@email.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Test");
                intent.putExtra(Intent.EXTRA_TEXT, "Test mail");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }
        });

        btn_sms.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                String smsNumber = "+8801725171809";
                String smsText ="My test SMS";

                Uri uri = Uri.parse("smsto:" + smsNumber);
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", smsText);
                startActivity(intent);
            }
        });





    }

    public void onCall() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    Integer.parseInt("123"));
        } else {
            //startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:12345678901")));
            String number="+8801730034567";
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+number));
            startActivity(callIntent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    onCall();
                } else {
                    Log.d("TAG", "Call Permission Not Granted");
                }
                break;

            default:
                break;
        }
    }

}