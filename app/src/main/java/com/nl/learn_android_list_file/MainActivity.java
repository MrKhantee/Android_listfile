package com.nl.learn_android_list_file;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    File[] files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file;

        if(!getIntent().hasExtra("path")){
            file= Environment.getExternalStorageDirectory();
        }
        else{
            String path=getIntent().getStringExtra("path");
            file=new File(path);
        }
        files=file.listFiles();
        GridView gridView=findViewById(R.id.grdView);
        FileViewAdapter fileViewAdapter=new FileViewAdapter(MainActivity.this,R.layout.item_file,files);
        gridView.setAdapter(fileViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(files[position].isDirectory()){
                    Intent intent=new Intent(MainActivity.this,MainActivity.class);
                    intent.putExtra("path",files[position].getAbsolutePath());
                    startActivity(intent);
                }
            }
        });
    }
}
