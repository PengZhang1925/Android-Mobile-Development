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
import android.widget.Space;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import socialevent.model.DatabaseHelper;
import socialevent.model.eventListModel;

import static java.lang.Integer.parseInt;

public class EditingEvent extends AppCompatActivity {
    private static EditText a;
    private static EditText b1;
    private static EditText c;
    private static EditText d;
    private static EditText e;
    private static EditText f;
    private static EditText g;
    private static EditText h;
    private static EditText i;
    private static EditText j;
    private static Button back;
    Button invite;
    Button update;
    private static Button button_start;
    private static Button time_start;
    private static Button button_end;
    private static Button time_end;
    int year_x,month_x,day_x;
    int hour_x,minute_x;
    static final int DILOG_ID = 0;
    static final int DILOG_ID1 = 1;
    int index;
    final int CONTACT_PICK_REQUEST = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_event);
        final java.util.Calendar cal = java.util.Calendar.getInstance();
        year_x = cal.get(java.util.Calendar.YEAR);
        month_x = cal.get(java.util.Calendar.MONTH);
        day_x = cal.get(java.util.Calendar.DAY_OF_MONTH);
        final eventListModel tempValues = (eventListModel)EventLists.listArr.get(EventLists.pos);
        final DatabaseHelper db = new DatabaseHelper(this);
        showDateOnButtonClick();
//        Bundle b=this.getIntent().getExtras();
//        String[] array=b.getStringArray("name");
//        String s = array[0];
//        String[] words = s.split("\\s+");
//        for (int i = 0; i < words.length; i++) {
//            // You may want to check for a non-word character before blindly
//            // performing a replacement
//            // It may also be necessary to adjust the character class
//            words[i] = words[i].replaceAll("[\n]", "");
//        }
        a = (EditText)findViewById(R.id.editName);
        b1 = (EditText)findViewById(R.id.editSD);
        c = (EditText)findViewById(R.id.editST);
        d = (EditText)findViewById(R.id.editED);
        e = (EditText)findViewById(R.id.editET);
        f = (EditText)findViewById(R.id.editVenue);
        g = (EditText)findViewById(R.id.editLatitude);
        h = (EditText)findViewById(R.id.editLongtitude);
        i = (EditText)findViewById(R.id.editNotes);
        j = (EditText) findViewById(R.id.attendees);
        back = (Button)findViewById(R.id.cancel);
        update = (Button)findViewById(R.id.update);
        invite = (Button)findViewById(R.id.invite);

        a.setText(tempValues.getEventName(), TextView.BufferType.EDITABLE);
        b1.setText(tempValues.getStartDate(), TextView.BufferType.EDITABLE);
        c.setText(tempValues.getStartTime(), TextView.BufferType.EDITABLE);
        d.setText(tempValues.getEndDate(), TextView.BufferType.EDITABLE);
        e.setText(tempValues.getEndTime(), TextView.BufferType.EDITABLE);
        f.setText(tempValues.getVenue(), TextView.BufferType.EDITABLE);
        g.setText(tempValues.getLatitude().toString(), TextView.BufferType.EDITABLE);
        h.setText(tempValues.getLongtitude().toString(), TextView.BufferType.EDITABLE);
        i.setText(tempValues.getNotes(), TextView.BufferType.EDITABLE);
        j.setText(tempValues.getContactList(), TextView.BufferType.SPANNABLE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditingEvent.this, EventLists.class);
                startActivity(intent);
                finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean update = db.updateEvent(tempValues.getID(),a.getText().toString(),b1.getText().toString(),d.getText().toString(),c.getText().toString(),e.getText().toString(),f.getText().toString(),Double.parseDouble(g.getText().toString()),Double.parseDouble(h.getText().toString()),i.getText().toString(),j.getText().toString());
                if(update == true){
                    Toast.makeText(EditingEvent.this,"Event Updated.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditingEvent.this,EventLists.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(EditingEvent.this,"Event update failed.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentContactPick = new Intent(EditingEvent.this, ContactsPickerActivity.class);
                EditingEvent.this.startActivityForResult(intentContactPick, CONTACT_PICK_REQUEST);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CONTACT_PICK_REQUEST && resultCode == RESULT_OK) {

            ArrayList<Contact> selectedContacts = data.getParcelableArrayListExtra("SelectedContacts");

            String display = "";
            for (int i = 0; i < selectedContacts.size(); i++) {

                display += (i + 1) + ". " + selectedContacts.get(i).toString() + "\n";

            }
            j.setText(display);

        }

    }

    public void showDateOnButtonClick(){

        button_start = (Button)findViewById(R.id.editSDButton);
        button_end = (Button)findViewById(R.id.editEDButton);
        time_start = (Button)findViewById(R.id.editSTButton);
        time_end = (Button)findViewById(R.id.editETButton);

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
            return new TimePickerDialog(EditingEvent.this, kTimePickerListener,hour_x,minute_x,false);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickListener = new DatePickerDialog.OnDateSetListener(){
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            if(index == 1){
                EditText editText = (EditText)findViewById(R.id.editSD);
                editText.setText(year_x+"/"+month_x+"/"+day_x, TextView.BufferType.EDITABLE);
            }else if(index == 2){
                EditText editText = (EditText)findViewById(R.id.editED);
                editText.setText(year_x+"/"+month_x+"/"+day_x, TextView.BufferType.EDITABLE);
            }
        }
    };

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener = new TimePickerDialog.OnTimeSetListener(){
        public void onTimeSet(TimePicker view, int hourOfDay, int minute){
            hour_x = hourOfDay;
            minute_x = minute;
            if(index == 3){
                EditText editText = (EditText)findViewById(R.id.editST);
                editText.setText(hour_x +":"+minute_x, TextView.BufferType.EDITABLE);
            }else if(index == 4){
                EditText editText = (EditText)findViewById(R.id.editET);
                editText.setText(hour_x +":"+minute_x, TextView.BufferType.EDITABLE);
            }
        }
    };
}
