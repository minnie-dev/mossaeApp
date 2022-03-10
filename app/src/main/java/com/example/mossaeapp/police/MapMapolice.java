package com.example.mossaeapp.police;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import androidx.core.app.ActivityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mossaeapp.HelpActivity;
import com.example.mossaeapp.MainActivity;
import com.example.mossaeapp.PoliceActivity;
import com.example.mossaeapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapMapolice extends Activity implements OnMapReadyCallback {
    GoogleMap gMap;
    MapFragment mapFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapolice);

        mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        //도움말버튼
        ImageButton help = (ImageButton) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        //홈버튼
        Button home = (Button) findViewById(R.id.homebtn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        //뒤로가기버튼
        Button police = (Button) findViewById(R.id.backbtn);
        police.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PoliceActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    /*******볼륨버튼 길게 눌렀을때 긴급통화 연결*******/
    public boolean onKeyLongPress(int keycode, KeyEvent event){
        if(keycode == KeyEvent.KEYCODE_VOLUME_UP
                || keycode == KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            Toast.makeText(this, "긴급통화가 연결됩니다.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:01089378971")));
            return true;
        }
        return super.onKeyLongPress(keycode, event);
    }
    //짧게 눌렀을경우 트랙킹주기
    public boolean onKeyDown(int keycode, KeyEvent event)
    {
        if( keycode == KeyEvent.KEYCODE_VOLUME_UP ||
                keycode == KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keycode, event);
    }
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {

        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        if((event.getFlags() & KeyEvent.FLAG_CANCELED_LONG_PRESS) == 0){
            if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
                Toast.makeText(this, "길게 눌러주세요!!", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(1000);
                return true;
            }
            else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
                Toast.makeText(this, "길게 눌러주세요!!", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(1000);
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }
    /********볼륨버튼 비상전화 끝*********/

    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        gMap.setMyLocationEnabled(true);


        MarkerOptions marker=new MarkerOptions();
        marker.position(new LatLng(37.387300, 126.925959)).title("안양 만안 경찰서").snippet("182");
        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.policemark));

        gMap.addMarker(marker).showInfoWindow();


    }
}
