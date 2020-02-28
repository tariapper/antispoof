package com.example.antispoof;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    final Handler handler_interact = new Handler();
    View bkg;// = findViewById(R.id.screen);
    View half;// = findViewById(R.id.half);
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBrightness(bkg, 255);

        bkg = findViewById(R.id.screen);
        half = findViewById(R.id.half);

        final Button button = findViewById(R.id.start);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clrs();
                // Code here executes on main thread after user presses button

            }
        });

        // The request code used in ActivityCompat.requestPermissions()
        // and returned in the Activity's onRequestPermissionsResult()
        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA,
                Manifest.permission.WRITE_SETTINGS
        };

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }

    public void clrs(){
        Timer timer_interact = new Timer();
        timer_interact.schedule(new TimerTask() {
            @Override
            public void run() {
                handler_interact.post(g_r);;
            }
        }, 1000);
        timer_interact.schedule(new TimerTask() {
            @Override
            public void run() {
                handler_interact.post(g_b);;
            }
        }, 2000);
        timer_interact.schedule(new TimerTask() {
            @Override
            public void run() {
                handler_interact.post(r_b);;
            }
        }, 3000);
        timer_interact.schedule(new TimerTask() {
            @Override
            public void run() {
                handler_interact.post(re);;
            }
        }, 4000);
        timer_interact.schedule(new TimerTask() {
            @Override
            public void run() {
                handler_interact.post(bl);;
            }
        }, 5000);
        timer_interact.schedule(new TimerTask() {
            @Override
            public void run() {
                handler_interact.post(gr);;
            }
        }, 6000);
    }


    //creating runnable
    final Runnable g_r = new Runnable() {
        public void run() {
            bkg.setBackgroundColor(Color.GREEN);
            half.setBackgroundColor(Color.RED);
        }
    };
    final Runnable g_b = new Runnable() {
        public void run() {
            bkg.setBackgroundColor(Color.GREEN);
            half.setBackgroundColor(Color.BLUE);
        }
    };
    final Runnable r_b = new Runnable() {
        public void run() {
            bkg.setBackgroundColor(Color.RED);
            half.setBackgroundColor(Color.BLUE);
        }
    };
    final Runnable re = new Runnable() {
        public void run() {
            bkg.setBackgroundColor(Color.RED);
            half.setBackgroundColor(Color.RED);
        }
    };
    final Runnable bl = new Runnable() {
        public void run() {
            bkg.setBackgroundColor(Color.BLUE);
            half.setBackgroundColor(Color.BLUE);
        }
    };
    final Runnable gr = new Runnable() {
        public void run() {
            bkg.setBackgroundColor(Color.GREEN);
            half.setBackgroundColor(Color.GREEN);
        }
    };



    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void setBrightness(View view,int brightness) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.System.canWrite(getApplicationContext())) {
                brightness = 255;

                ContentResolver cResolver = this.getApplicationContext().getContentResolver();
                Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);

            } else {
                Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + this.getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }

    }



}
