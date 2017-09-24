package com.example.user.socialevent;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import socialevent.model.DatabaseHelper;
import socialevent.model.eventListModel;

import socialevent.model.EventListsAdapter;

public class EventLists extends AppCompatActivity {
    ListView LV;
    EventListsAdapter adapter;
    public EventLists lists = null;
    public static ArrayList<eventListModel> listArr = new ArrayList<eventListModel>();
    public static int pos = 0;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_lists);
        db = new DatabaseHelper(this);
        lists = this;
        setListData();
        Resources res = getResources();
        LV = (ListView)findViewById(R.id.listView);
        adapter = new EventListsAdapter(lists,listArr,res);
        LV.setAdapter(adapter);
    }

    private void setListData() {
        Cursor res = db.getEvent();
        listArr.clear();
        res.moveToFirst();
        while(res.moveToNext()) {
                final eventListModel model = new eventListModel();
                model.setID(res.getString(0));
                model.setEventName(res.getString(1));
                model.setStartDate(res.getString(2));
                model.setEndDate(res.getString(3));
                model.setStartTime(res.getString(4));
                model.setEndTime(res.getString(5));
                model.setVenue(res.getString(6));
                model.setLatitude(res.getDouble(7));
                model.setLongtitude(res.getDouble(8));
                model.setNotes(res.getString(9));
                model.setContactList(res.getString(10));
                listArr.add(model);
        }res.close();
}
    public void onItemClick(int position) {
        pos = position;
        eventListModel tempValues = (eventListModel)listArr.get(position);
        Toast.makeText(lists,tempValues.getEventName(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EventLists.this,EditingEvent.class);
        startActivity(intent);
    }

    public void onItemLongClick(int position){
        final int dPosition = position;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(EventLists.this);
        alertDialog.setTitle("Action");
        alertDialog.setMessage("Select the function you going to do?");
        alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eventListModel tempValues = (eventListModel)listArr.get(dPosition);
                int check = db.deleteEvent(tempValues.getID());
                listArr.remove(dPosition);
                adapter.notifyDataSetChanged();
                if(check>0){
                Toast.makeText(EventLists.this,tempValues.getEventName()+" Deleted", Toast.LENGTH_SHORT).show();
                finish();}else{Toast.makeText(EventLists.this,"Deletion failed",Toast.LENGTH_SHORT).show();}
            }
        });
        alertDialog.setNeutralButton("GoMap",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(EventLists.this, MapsAPIActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = alertDialog.create();
        alert.show();
    }
}
