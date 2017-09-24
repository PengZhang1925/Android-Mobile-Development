package com.example.user.socialevent;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;

public class CalendarEvent extends AppCompatActivity {
    Button btn_test;
    Button btn_test1;
    private static EditText wc;
    private static EditText sd;
    private static EditText ed;
    private static EditText ts;
    private static EditText te;
    private static Button button_start;
    private static Button time_start;
    private static Button button_end;
    private static Button time_end;
    int year_x,month_x,day_x;
    int hour_x,minute_x;
    static final int DILOG_ID = 0;
    static final int DILOG_ID1 = 1;
    int index;
    private static Button btn_voice;
    private static Button btn_invite;
    private MediaRecorder mRecorder;
    private String mFileName = null;
    private static final String LOG_TAG = "Record_log";
    MediaPlayer mp=new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event);
        onClickListener();
        final java.util.Calendar cal = java.util.Calendar.getInstance();
        year_x = cal.get(java.util.Calendar.YEAR);
        month_x = cal.get(java.util.Calendar.MONTH);
        day_x = cal.get(java.util.Calendar.DAY_OF_MONTH);

        showDateOnButtonClick();
        onClickRecordListener();
    }

    public void onClickListener(){
        btn_test = (Button)findViewById(R.id.test);
        btn_test1 = (Button)findViewById(R.id.cancelevent);
        wc = (EditText)findViewById(R.id.editText10);
        wc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

            }
        });
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK,resultIntent);
                finish();
            }
        });
        btn_test1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                Intent resultIntent = new Intent();
                setResult(RESULT_CANCELED,resultIntent);
                finish();
            }
        });
    }

    public void showDateOnButtonClick(){

        button_start = (Button)findViewById(R.id.button11);
        button_end = (Button)findViewById(R.id.button13);
        time_start = (Button)findViewById(R.id.button12);
        time_end = (Button)findViewById(R.id.button14);

        button_start.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        showDialog(DILOG_ID);
                        index = 1;

                    }
                }
        );
        button_end.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        showDialog(DILOG_ID);
                        index = 2;

                    }
                }
        );
        time_start.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        showDialog(DILOG_ID1);
                        index = 3;
                    }
                }
        );

        time_end.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        showDialog(DILOG_ID1);
                        index = 4;
                    }
                }
        );
    }

    protected Dialog onCreateDialog(int id){
        if(id == DILOG_ID)
            return new DatePickerDialog(this,dpickListener, year_x,month_x,day_x);
        if(id == DILOG_ID1)
            return new TimePickerDialog(CalendarEvent.this, kTimePickerListener,hour_x,minute_x,false);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            if(index == 1){
                EditText editText = (EditText)findViewById(R.id.editText11);
                editText.setText(year_x+"/"+month_x+"/"+day_x, TextView.BufferType.EDITABLE);
            }else if(index == 2){
                EditText editText = (EditText)findViewById(R.id.editText13);
                editText.setText(year_x+"/"+month_x+"/"+day_x, TextView.BufferType.EDITABLE);
            }
        }
    };

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener = new TimePickerDialog.OnTimeSetListener(){
        public void onTimeSet(TimePicker view, int hourOfDay, int minute){
            hour_x = hourOfDay;
            minute_x = minute;
            if(index == 3){
                EditText editText = (EditText)findViewById(R.id.editText12);
                editText.setText(hour_x +":"+minute_x, TextView.BufferType.EDITABLE);
            }else if(index == 4){
                EditText editText = (EditText)findViewById(R.id.editText14);
                editText.setText(hour_x +":"+minute_x, TextView.BufferType.EDITABLE);
            }
        }
    };

    public void onClickRecordListener(){
        btn_voice = (Button)findViewById(R.id.button15);
        btn_invite = (Button)findViewById(R.id.button16);
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/recorded_audio.3gp";

        btn_invite.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(CalendarEvent.this,contacts.class);
                startActivity(intent);
            }
        });

        btn_voice.setOnTouchListener(new View.OnTouchListener(){
                                         public boolean onTouch(View view, MotionEvent motionEvent){
                                             if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                                                 startRecording();
                                                 Toast.makeText(CalendarEvent.this,"Recording Started ...", Toast.LENGTH_LONG).show();
                                             }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                                                 stopRecording();
                                                 Toast.makeText(CalendarEvent.this, "Recoding Stopped ...", Toast.LENGTH_LONG).show();
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
