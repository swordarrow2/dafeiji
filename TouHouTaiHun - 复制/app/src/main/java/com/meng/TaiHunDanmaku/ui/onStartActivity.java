package com.meng.TaiHunDanmaku.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.meng.TaiHunDanmaku.R;
import android.widget.CompoundButton.*;
import android.widget.*;

public class onStartActivity extends Activity{
    Button btn;
 RadioGroup player;
    public static String pl="a";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_start2);
        btn=(Button)findViewById(R.id.btn);
        player=(RadioGroup)findViewById(R.id.radioplayer);
		player.setOnCheckedChangeListener(new RadioGroup. OnCheckedChangeListener(){

			  @Override
			  public void onCheckedChanged(RadioGroup p1,int p2){
				  selectRadioBtn();
				}
			});
        btn.setOnClickListener(new View.OnClickListener(){
			  @Override
			  public void onClick(View view){
				  Intent i=new Intent(onStartActivity.this,MainActivity.class);
				  startActivity(i);
				}
			});
			}
	private void selectRadioBtn(){
		RadioButton radioButton = (RadioButton)findViewById(player.getCheckedRadioButtonId());
		pl=radioButton.getText().toString();
	  }
  }
