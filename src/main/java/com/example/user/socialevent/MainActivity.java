package com.example.user.socialevent;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import socialevent.model.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private static Button btn;
    private static Button btn_view;
    private static Button btn_cal;
    Button test;
    DatabaseHelper db;
    public static final String MY_TAG = "the_custom_message";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(MY_TAG,"onCreate");
        onClickButtonListener();
        db = new DatabaseHelper(this);
        test = (Button)findViewById(R.id.test);
        viewAll();
    }

    public void viewAll(){
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getEvent();
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Latitude:"+res.getDouble(7)+"\n");
                    buffer.append("Longtitude:"+res.getDouble(8)+"\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void onClickButtonListener(){
        btn = (Button)findViewById(R.id.button);
        btn_view = (Button)findViewById(R.id.button6);
        btn_cal = (Button)findViewById(R.id.button9);
        btn.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.user.socialevent.Editing");
                        startActivity(intent);
                    }
                }
        );
        btn_view.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v1){
                        Intent intent1 = new Intent(getApplicationContext(),EventLists.class);
                        startActivity(intent1);
                    }
                }
        );
        btn_cal.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v1){
                        Intent intent1 = new Intent(getApplicationContext(),Calendar.class);
                        startActivity(intent1);
                    }
                }
        );
    }

    protected void onStart() {
        super.onStart();
        Log.i(MY_TAG,"onStart");
    }

    protected void onResume() {
        super.onResume();
        Log.i(MY_TAG,"onResume");
    }

    protected void onPause() {
        super.onPause();
        Log.i(MY_TAG,"onPause");
    }

    protected void onStop() {
        super.onStop();
        Log.i(MY_TAG,"onStop");
    }

    protected void onRestart() {
        super.onRestart();
        Log.i(MY_TAG,"onRestart");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(MY_TAG,"onDestory");
    }
}
