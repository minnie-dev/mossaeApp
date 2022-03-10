package com.example.mossaeapp;

import android.app.Activity;

import android.app.AlarmManager;

import android.app.PendingIntent;

import android.content.Intent;

import android.media.MediaPlayer;

import android.os.Bundle;

import android.util.Log;

import android.view.View;

import android.view.WindowManager;
import android.widget.Button;

import android.widget.TextView;

import android.widget.Toast;




public class removeActivity extends Activity {

    private Intent intent;
    private PendingIntent ServicePending;
    private AlarmManager alarmManager;
    TextView textView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        mediaPlayer.start(); // prepare(); 나 create() 를 호출할 필요 없음

        //알람 설정, 해제 버튼

        Button.OnClickListener bClickListener = new View.OnClickListener() {

 public void onClick(View v) {

     switch (v.getId()) {

                    case R.id.removeAlarm:
                        mediaPlayer.stop();
                        removeAlarm();
                        Intent intent = new Intent(getApplicationContext(), FakecallActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        break;

                     case R.id.removeAlarm2:


                         mediaPlayer.stop();
                         removeAlarm();
                         Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                         intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                         startActivity(intent2);
                         break;
                }
            }
        };

        findViewById(R.id.removeAlarm).setOnClickListener(bClickListener);
        findViewById(R.id.removeAlarm2).setOnClickListener(bClickListener);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);


    }

    void removeAlarm(){

        intent = new Intent("AlarmReceiver");
        //PendingIntent.getBroadcast(Context context, int requestCod, Intent intent, int flag);
        ServicePending = PendingIntent.getBroadcast(
                removeActivity.this, 111, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d("ServicePending : ",""+ServicePending.toString());
        Toast.makeText(getBaseContext(), "알람 해제", Toast.LENGTH_SHORT).show();
        alarmManager.cancel(ServicePending);
        textView.setText("");

    }

}
