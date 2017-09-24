package com.example.user.socialevent;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import socialevent.model.DatabaseHelper;

import static java.lang.Double.parseDouble;

public class contacts extends AppCompatActivity {
    TextView contactsDisplay;
    Button pickContacts;
    final int CONTACT_PICK_REQUEST = 1000;
    private static Button save;
    DatabaseHelper db;
    String contacts;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        db = new DatabaseHelper(this);
        final String  ID = Editing.ID;
        final String eventName = Editing.plaintext.getText().toString();
        final String sd = Editing.sd.getText().toString();
        final String ed = Editing.ed.getText().toString();
        final String ts = Editing.ts.getText().toString();
        final String te = Editing.te.getText().toString();
        final String venue = Editinglocation.venue.getText().toString();
        final Double latitude = parseDouble(Editinglocation.latitude.getText().toString());
        final Double longtitude = parseDouble(Editinglocation.longtitude.getText().toString());
        final String notes = Editinglocation.notes.getText().toString();
        contactsDisplay = (TextView) findViewById(R.id.txt_selected_contacts);
        pickContacts = (Button) findViewById(R.id.btn_pick_contacts);
        save = (Button) findViewById(R.id.saveEvents);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(contacts.this, MainActivity.class);
                boolean check = db.insertData(ID,eventName,sd,ed,ts,te,venue,latitude,longtitude,notes,contacts);
                if(check == true){
                    Toast.makeText(contacts.this, "Event inserted", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();}else{
                    Toast.makeText(contacts.this, "Event insert failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        pickContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentContactPick = new Intent(contacts.this, ContactsPickerActivity.class);
                contacts.this.startActivityForResult(intentContactPick, CONTACT_PICK_REQUEST);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CONTACT_PICK_REQUEST && resultCode == RESULT_OK) {

            ArrayList<Contact> selectedContacts = data.getParcelableArrayListExtra("SelectedContacts");

            String display = "";
            for (int i = 0; i < selectedContacts.size(); i++) {

                display += (i + 1) + ". " + selectedContacts.get(i).toString() + "\n";

            }
            contacts = display;
            contactsDisplay.setText("Selected Contacts : \n\n" + display);

        }

    }
}
