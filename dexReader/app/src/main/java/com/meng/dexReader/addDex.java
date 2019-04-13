package com.meng.dexReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;


/**
 * Created by Administrator on 2018/5/4.
 */

public class addDex extends Activity {
    Button btnSelect;
    Button btnYes;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dex);
        btnSelect = (Button) findViewById(R.id.select_dex_button);
        et = (EditText) findViewById(R.id.add_dex_textview);
        btnYes = (Button) findViewById(R.id.add_dex_button);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileText = fileTools.openTextFile(xmlParser.xmlPath);
                String textToInsert = et.getText().toString();
                if (textToInsert.endsWith(".dex") || textToInsert.endsWith(".mm")) {
                    fileText = fileTools.insertText(fileText, textToInsert);
                    if (fileTools.saveTextFile(fileText, xmlParser.xmlPath)) {
                        Toast.makeText(addDex.this, "添加成功", Toast.LENGTH_SHORT).show();
                        et.setText("");
                    } else {
                        Toast.makeText(addDex.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(addDex.this,"添加失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            File file = new File(fileTools.getPath(this, data.getData()));
            et.setText(file.getAbsolutePath());
        }
    }


}
