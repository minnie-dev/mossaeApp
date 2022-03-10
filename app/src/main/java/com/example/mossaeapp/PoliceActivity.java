package com.example.mossaeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mossaeapp.police.MapAypolice;
import com.example.mossaeapp.police.MapBdpolice;
import com.example.mossaeapp.police.MapBspolice;
import com.example.mossaeapp.police.MapDapolice;
import com.example.mossaeapp.police.MapGspolice;
import com.example.mossaeapp.police.MapIdwpolice;
import com.example.mossaeapp.police.MapMapolice;
import com.example.mossaeapp.police.MapMhpolice;
import com.example.mossaeapp.police.MapSspolice;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;


public class PoliceActivity extends Activity{

    GoogleMap gMap;
    MapFragment mapFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);

        //도움말버튼
        ImageButton help = (ImageButton) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Helppolice.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_right,R.anim.hold);    //오른쪽에서 들어오는 애니메이션
            }
        });

        //안양만안경찰서
        ImageButton mapolicecall = (ImageButton) findViewById(R.id.mapolicecall);
        mapolicecall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:182")));
            }
        });
        //안양지구대
        ImageButton aypolicecall = (ImageButton) findViewById(R.id.aypolicecall);
        aypolicecall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:031-449-2515")));
            }
        });
        //명학지구대
        ImageButton mhpolicecall = (ImageButton) findViewById(R.id.mhpolicecall);
        mhpolicecall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:031-446-0112")));
            }
        });
        //석수지구대
        ImageButton sspolicecall = (ImageButton) findViewById(R.id.sspolicecall);
        sspolicecall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:031-472-0112")));
            }
        });
        //박달지구대
        ImageButton bdpolicecall = (ImageButton) findViewById(R.id.bdpolicecall);
        bdpolicecall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:031-466-0112")));
            }
        });
        //안양동안경찰서

        ImageButton dapolicecall = (ImageButton) findViewById(R.id.dapolicecall);
        dapolicecall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:182")));
            }
        });
        //비산지구대
        ImageButton bspolicecall = (ImageButton) findViewById(R.id.bspolicecall);
        bspolicecall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:031-447-0112")));
            }
        });
        //인덕원지구대
        ImageButton idwpolicecall = (ImageButton) findViewById(R.id.idwpolicecall);
        idwpolicecall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:031-423-0112")));
            }
        });
        //갈산지구대
        ImageButton gspolicecall = (ImageButton) findViewById(R.id.gspolicecall);
        gspolicecall.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:031-456-1113")));
            }
        });



        //안양만안경찰서
        ImageButton mapolicemap = (ImageButton) findViewById(R.id.mapolicemap);
        mapolicemap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapMapolice.class);
                startActivity(intent);
            }
        });

        //안양 지구대
        ImageButton aypolicemap = (ImageButton) findViewById(R.id.aypolicemap);
        aypolicemap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapAypolice.class);
                startActivity(intent);
            }
        });
        //명학 지구대
        ImageButton mhpolicemap = (ImageButton) findViewById(R.id.mhpolicemap);
        mhpolicemap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapMhpolice.class);
                startActivity(intent);
            }
        });
        //석수 지구대
        ImageButton sspolicemap = (ImageButton) findViewById(R.id.sspolicemap);
        sspolicemap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapSspolice.class);
                startActivity(intent);
            }
        });
        //박달 지구대
        ImageButton bdpolicemap = (ImageButton) findViewById(R.id.bdpolicemap);
        bdpolicemap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapBdpolice.class);
                startActivity(intent);
            }
        });
        //안양동안경찰서
        ImageButton dapolicemap = (ImageButton) findViewById(R.id.dapolicemap);
        dapolicemap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapDapolice.class);
                startActivity(intent);
            }
        });

        //비산
        ImageButton bspolicemap = (ImageButton) findViewById(R.id.bspolicemap);
        bspolicemap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapBspolice.class);
                startActivity(intent);
            }
        });

        //인덕원
        ImageButton idwpolicemap = (ImageButton) findViewById(R.id.idwpolicemap);
        idwpolicemap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapIdwpolice.class);
                startActivity(intent);
            }
        });

        //갈산
        ImageButton gspolicemap = (ImageButton) findViewById(R.id.gspolicemap);
        gspolicemap.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapGspolice.class);
                startActivity(intent);
            }
        });
    }

    /*
    public boolean onClick(){
        switch(item.getItemId()){

            case 2:
                gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case 3:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( 37.388054, 126.926455),15));
                return true;
            case 4:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( 37.389579, 126.930556),15));
                return true;
            case 5:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.405822, 126.923180),15));
                return true;
            case 6:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( 37.402245, 126.907181),15));
                return true;
            case 7:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( 37.414696, 126.918405),15));
                return true;
            case 8:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( 37.391174, 126.948804),15));
                return true;
            case 9:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( 37.399087, 126.938028),15));
                return true;
            case 10:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.401167, 126.977252),15));
                return true;
            case 11:
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( 37.376313, 126.966336),15));
                return true;

        }
        return false;
    }
    */
    /*******볼륨버튼 길게 눌렀을때 긴급통화 연결*******/
    public boolean onKeyLongPress(int keycode, KeyEvent event){
        if(keycode == android.view.KeyEvent.KEYCODE_VOLUME_UP
                || keycode == android.view.KeyEvent.KEYCODE_VOLUME_DOWN)
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
        if( keycode == android.view.KeyEvent.KEYCODE_VOLUME_UP ||
                keycode == android.view.KeyEvent.KEYCODE_VOLUME_DOWN)
        {
            event.startTracking();
            return true;
        }
        return super.onKeyDown(keycode, event);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event)
    {

        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        if((event.getFlags() & android.view.KeyEvent.FLAG_CANCELED_LONG_PRESS) == 0){
            if(keyCode == android.view.KeyEvent.KEYCODE_VOLUME_UP){
                Toast.makeText(this, "길게 눌러주세요!!", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(1000);
                return true;
            }
            else if(keyCode == android.view.KeyEvent.KEYCODE_VOLUME_DOWN){
                Toast.makeText(this, "길게 눌러주세요!!", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(1000);
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }
    /********볼륨버튼 비상전화 끝*********/
}
