package com.example.mossaeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;


public class Helpvoice extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpvoice);

        //도움말버튼
        ImageButton help = (ImageButton) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "현재 화면입니다.", Toast.LENGTH_SHORT).show();
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

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.hold,R.anim.anim_out_right);
    }
}
