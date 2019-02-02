package com.meng.stg.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.meng.stg.R;

public class onStartActivity extends Activity{
    Button btn;
    RadioGroup player;
    RadioGroup diff;
    RadioGroup stage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_start2);
        Intent i=new Intent(onStartActivity.this,MainActivity.class);
        startActivity(i);
        finish();
        if(true) return;
        btn=(Button)findViewById(R.id.btn);
        player=(RadioGroup)findViewById(R.id.radioplayer);
        diff=(RadioGroup)findViewById(R.id.radiodifficulty);
        stage=(RadioGroup)findViewById(R.id.radiostage);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i=new Intent(onStartActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
