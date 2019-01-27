package com.meng.stg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class onStartActivity extends Activity {
    Button btn;
    RadioGroup player;
    RadioGroup diff;
    RadioGroup stage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_start2);
      btn = (Button) findViewById(R.id.btn);
        player= (RadioGroup) findViewById(R.id.radioplayer);
        diff=(RadioGroup)findViewById(R.id.radiodifficulty);
        stage=(RadioGroup)findViewById(R.id.radiostage);
        /*
        myPlane.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                ((RadioButton)diff.getChildAt(3)).setChecked(true);
                ((RadioButton)stage.getChildAt(6)).setChecked(true);

                for(int ii=0;ii<radioGroup.getChildCount();ii++){
                    if(radioGroup.getChildAt(ii).getId()==i){
                        ((RadioButton)radioGroup.getChildAt(ii)).setChecked(true);
                    }else {
                        ((RadioButton)radioGroup.getChildAt(ii)).setChecked(false);
                    }
                }
            }
        });
        diff.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                ((RadioButton)stage.getChildAt(6)).setChecked(true);
                ((RadioButton)myPlane.getChildAt(0)).setChecked(true);
                for(int ii=0;ii<radioGroup.getChildCount();ii++){
                    if(radioGroup.getChildAt(ii).getId()==i){
                        ((RadioButton)radioGroup.getChildAt(ii)).setChecked(true);
                    }else {
                        ((RadioButton)radioGroup.getChildAt(ii)).setChecked(false);
                    }
                }

            }
        });
        stage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                ((RadioButton)myPlane.getChildAt(0)).setChecked(true);
                ((RadioButton)diff.getChildAt(3)).setChecked(true);
                for(int ii=0;ii<radioGroup.getChildCount();ii++){
                    if(radioGroup.getChildAt(ii).getId()==i){
                        ((RadioButton)radioGroup.getChildAt(ii)).setChecked(true);
                    }else {
                        ((RadioButton)radioGroup.getChildAt(ii)).setChecked(false);
                    }
                }
            }
        });
        */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((!((RadioButton) findViewById(R.id.l)).isChecked())||(!((RadioButton) findViewById(R.id.rm)).isChecked())||(!((RadioButton) findViewById(R.id.e)).isChecked()) ) {
                    Toast.makeText(onStartActivity.this, "哈哈哈真的以为能选吗", Toast.LENGTH_SHORT).show();
					Thread t=new start(1000);
					t.start();
                }else{
					Thread t=new start(0);
					t.start();
				}
                ((RadioButton)diff.getChildAt(3)).setChecked(true);
                ((RadioButton)player.getChildAt(0)).setChecked(true);
                ((RadioButton)stage.getChildAt(6)).setChecked(true);

               
            }
        });
    }
    public class start extends Thread{
		int i=0;
		public start(int i){
			this.i=i;
		}
        @Override
        public void run() {
            try {
                sleep(i);
                Intent i=new Intent(onStartActivity.this,MainActivity.class);
                startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
