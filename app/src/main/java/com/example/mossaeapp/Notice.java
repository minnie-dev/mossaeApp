package com.example.mossaeapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;


public class Notice extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        final ToggleButton notice1 = (ToggleButton) findViewById(R.id.notice1);
        final ToggleButton notice2 = (ToggleButton) findViewById(R.id.notice2);
        final ToggleButton notice3 = (ToggleButton) findViewById(R.id.notice3);
        final ToggleButton notice4 = (ToggleButton) findViewById(R.id.notice4);
        final ToggleButton notice5 = (ToggleButton) findViewById(R.id.notice5);
        final LinearLayout notice1_ex = (LinearLayout) findViewById(R.id.notice1_ex);
        final LinearLayout notice2_ex = (LinearLayout) findViewById(R.id.notice2_ex);
        final LinearLayout notice3_ex = (LinearLayout) findViewById(R.id.notice3_ex);
        final LinearLayout notice4_ex = (LinearLayout) findViewById(R.id.notice4_ex);
        final LinearLayout notice5_ex = (LinearLayout) findViewById(R.id.notice5_ex);

        notice1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(notice1.isChecked() == true) {
                    notice1_ex.setVisibility(View.VISIBLE);
                }
                else if(notice1.isChecked() == false){
                    notice1_ex.setVisibility(View.GONE);
                }
            }
        });
        notice2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(notice2.isChecked() == true) {
                    notice2_ex.setVisibility(View.VISIBLE);
                }
                else if(notice2.isChecked() == false){
                    notice2_ex.setVisibility(View.GONE);
                }
            }
        });
        notice3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(notice3.isChecked() == true) {
                    notice3_ex.setVisibility(View.VISIBLE);
                }
                else if(notice3.isChecked() == false){
                    notice3_ex.setVisibility(View.GONE);
                }
            }
        });
        notice4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(notice4.isChecked() == true) {
                    notice4_ex.setVisibility(View.VISIBLE);
                }
                else if(notice4.isChecked() == false){
                    notice4_ex.setVisibility(View.GONE);
                }
            }
        });
        notice5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(notice5.isChecked() == true) {
                    notice5_ex.setVisibility(View.VISIBLE);
                }
                else if(notice5.isChecked() == false){
                    notice5_ex.setVisibility(View.GONE);
                }
            }
        });
    }
}