package com.example.mossaeapp;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {

    Context context;


    @Override

    public void onReceive(final Context context, Intent intent) {
        this.context = context;
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "");

        wakeLock.acquire();
        Log.d("alarm", "gogo");


        PendingIntent pendingIntent;


        Toast toast = Toast.makeText(context, "알람이 울립니다.", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();

        wakeLock.release();


        try {

            intent = new Intent(context, removeActivity.class);
            pendingIntent = PendingIntent.getActivity(context, 111, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            Log.d("ServicePending++ : ", "" + pendingIntent.toString());
            pendingIntent.send();

        } catch (PendingIntent.CanceledException e) {

            e.printStackTrace();

        }

    }


}



