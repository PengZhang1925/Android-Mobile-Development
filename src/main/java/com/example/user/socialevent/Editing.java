package com.example.user.socialevent;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Random;

import socialevent.model.DatabaseHelper;

public class Editing extends AppCompatActivity {
    private static Button button_sbm;
    private static Button next;
    public static EditText plaintext;
    public static EditText sd;
    public static EditText ed;
    public static EditText ts;
    public static EditText te;
    private static Button button_start;
    private static Button time_start;
    private static Button button_end;
    private static Button time_end;
    public static String ID = "";
    int year_x,month_x,day_x;
    int hour_x,minute_x;
    static final int DILOG_ID = 0;
    static final int DILOG_ID1 = 1;
    int index;
//    DatabaseHelper myDb;
    String Num = "0123456789";
    String alpha = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing);
//        myDb = new DatabaseHelper(this);
        plaintext = (EditText)findViewById(R.id.eventName);
        sd = (EditText)findViewById(R.id.sdate);
        ed = (EditText)findViewById(R.id.edate);
        ts = (EditText)findViewById(R.id.stime);
        te = (EditText)findViewById(R.id.etime);
        addListenerOnButton();
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        showDateOnButtonClick();
    }

    public void addListenerOnButton(){

        next = (Button)findViewById(R.id.button2);
        next.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Toast.makeText(
                                Editing.this, plaintext.getText(),
                                Toast.LENGTH_SHORT
                        ).show();
                        Intent intentBundle = new Intent(Editing.this,Editinglocation.class);
                        Random r = new Random();
                        for(int i=0;i<2;i++){
                            ID += alpha.charAt(r.nextInt(alpha.length()));
                            ID += Num.charAt(r.nextInt(Num.length()));
                        }
//                        String[] info = new String[]{ID,plaintext.getText().toString(),sd.getText().toString(),ed.getText().toString(),ts.getText().toString(),te.getText().toString()};
//                        intentBundle.putExtra("info",info);
                        startActivity(intentBundle);
                    }
                }
        );
    }

    public void showDateOnButtonClick(){

        button_start = (Button)findViewById(R.id.button3);
        button_end = (Button)findViewById(R.id.button4);
        time_start = (Button)findViewById(R.id.button5);
        time_end = (Button)findViewById(R.id.button7);

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
            return new TimePickerDialog(Editing.this, kTimePickerListener,hour_x,minute_x,false);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            if(index == 1){
                EditText editText = (EditText)findViewById(R.id.sdate);
                editText.setText(year_x+"/"+month_x+"/"+day_x, TextView.BufferType.EDITABLE);
            }else if(index == 2){
                EditText editText = (EditText)findViewById(R.id.edate);
                editText.setText(year_x+"/"+month_x+"/"+day_x, TextView.BufferType.EDITABLE);
            }
        }
    };

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener = new TimePickerDialog.OnTimeSetListener(){
        public void onTimeSet(TimePicker view,int hourOfDay, int minute){
            hour_x = hourOfDay;
            minute_x = minute;
            if(index == 3){
                EditText editText = (EditText)findViewById(R.id.stime);
                editText.setText(hour_x +":"+minute_x, TextView.BufferType.EDITABLE);
            }else if(index == 4){
                EditText editText = (EditText)findViewById(R.id.etime);
                editText.setText(hour_x +":"+minute_x, TextView.BufferType.EDITABLE);
            }
        }
    };
}
