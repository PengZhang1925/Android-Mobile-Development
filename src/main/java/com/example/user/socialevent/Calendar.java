package com.example.user.socialevent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {
    private PreCalendar preCalendar;
    private Button btnNext;
    private Button btnPre;
    private TextView tvDate;
    final int EVENT_PICK_REQUEST = 1;
    AdapterView<?> adapter = null;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        preCalendar = (PreCalendar)findViewById(R.id.gvCalendar);
        preCalendar.initCalendar();
        btnPre = (Button)findViewById(R.id.btn_pre);
        btnNext = (Button)findViewById(R.id.btn_next);
        tvDate = (TextView)findViewById(R.id.tv_date);
        tvDate.setText(preCalendar.getTitle());

        //listener of buttons
        btnPre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                preCalendar.PreMonth();
                tvDate.setText(preCalendar.getTitle());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                preCalendar.NextMonth();
                tvDate.setText(preCalendar.getTitle());
            }
        });

        //on item click, just a test
        preCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentContactPick = new Intent(Calendar.this, CalendarEvent.class);
                Calendar.this.startActivityForResult(intentContactPick, EVENT_PICK_REQUEST);
                adapter = parent;
                pos = position;
            }

        });
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == EVENT_PICK_REQUEST) {
            if(resultCode == RESULT_OK){
                CalendarItem item = (CalendarItem) adapter.getItemAtPosition(pos);
                item.setHasPlan(!item.isHasPlan());
                preCalendar.refreshCalendar();
            }
        }

    }
}
