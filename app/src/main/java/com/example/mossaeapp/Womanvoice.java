package com.example.mossaeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class Womanvoice extends Activity {

    MediaPlayer mPlayer;

    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_womanvoice);

        final Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.start();

        mPlayer = MediaPlayer.create(this, R.raw.womanvoice);
        mPlayer.start();

        //도움말버튼
        ImageButton help = (ImageButton) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Helpvoice.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_right,R.anim.hold);    //오른쪽에서 들어오는 애니메이션
            }

        });

        //통화종료
        ImageButton finishcall= (ImageButton) findViewById(R.id.finishcall);
        finishcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

        });

    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.pause();
    }
}