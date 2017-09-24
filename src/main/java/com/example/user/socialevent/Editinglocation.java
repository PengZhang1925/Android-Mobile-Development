package com.example.user.socialevent;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import socialevent.model.DatabaseHelper;

import static java.lang.Double.parseDouble;

public class Editinglocation extends AppCompatActivity {
    private static Button btn_voice;
    private static Button btn_invite;
    public static EditText venue,latitude,longtitude,notes;
    private MediaRecorder mRecorder;
    private String mFileName = null;
    private static final String LOG_TAG = "Record_log";
    MediaPlayer mp=new MediaPlayer();
//    private String[] arr;
//    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinglocation);
//        db = new DatabaseHelper(this);
        venue = (EditText)findViewById(R.id.venue);
        latitude = (EditText)findViewById(R.id.latitude);
        longtitude = (EditText)findViewById(R.id.longtitude);
        notes = (EditText)findViewById(R.id.notes);
//        Bundle b = this.getIntent().getExtras();
//        arr = b.getStringArray("info");
        onClickRecordListener();
    }

    public void onClickRecordListener(){
        btn_voice = (Button)findViewById(R.id.button_voice);
        btn_invite = (Button)findViewById(R.id.button8);
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/recorded_audio.3gp";

        btn_invite.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                db.saveID(arr[0]);
//                boolean check = db.insertData(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],venue.getText().toString(),parseDouble(latitude.getText().toString()),parseDouble(longtitude.getText().toString()),notes.getText().toString());
//                if(check == true){
//                    Toast.makeText(Editinglocation.this, "Event Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Editinglocation.this,contacts.class);
                    startActivity(intent);
//                }else{
//                    Toast.makeText(Editinglocation.this, "Event build failed", Toast.LENGTH_SHORT).show();
//                }

            }
        });

        btn_voice.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View view, MotionEvent motionEvent){
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    startRecording();
                    Toast.makeText(Editinglocation.this,"Recording Started ...", Toast.LENGTH_LONG).show();
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    stopRecording();
                    Toast.makeText(Editinglocation.this, "Recoding Stopped ...", Toast.LENGTH_LONG).show();
                    try{
                        mp.setDataSource(mFileName);//Write your location here
                        mp.prepare();
                        mp.start();

                    }catch(Exception e){e.printStackTrace();}
                }
                return false;
            }
        }
        );
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }
}
