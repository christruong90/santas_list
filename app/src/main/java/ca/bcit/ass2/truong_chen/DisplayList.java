package ca.bcit.ass2.truong_chen;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class DisplayList extends AppCompatActivity {
    myListDbHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list_contents);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        NumberFormat formatter = new DecimalFormat("#0.00");

        ListView listview = (ListView) findViewById(R.id.listview_listContents);
        myDb = new myListDbHelper(this);

        ArrayList<String> thisList = new ArrayList<>();
        Cursor data = myDb.getAllData();

        if (data.getCount() == 0) {
            Toast.makeText(DisplayList.this, "The Database is empty!", Toast.LENGTH_SHORT).show();
        } else {
            while(data.moveToNext()) {
                String naughtyOutput = "";
                if (data.getString(11).equals("0")) {
                    naughtyOutput = "Not Naughty!";
                } else if (data.getString(11).equals("1")) {
                    naughtyOutput = "Naughty!";
                }

                thisList.add("ID: " + data.getString(0) + "\n" +
                        "First Name: " + data.getString(1) + "\n" +
                        "Last Name: " + data.getString(2) + "\n" +
                        "Birth Date: " + data.getString(3) + "\n" +
                        "Street: " + data.getString(4) + "\n" +
                        "City: " + data.getString(5) + "\n" +
                        "Province: " + data.getString(6) + "\n" +
                        "Postal Code: " + data.getString(7) + "\n" +
                        "Country: " + data.getString(8) + "\n" +
                        "Latitude: " + data.getString(9) + "\n" +
                        "Longitude: " + data.getString(10) + "\n" +
                        "isNaughty: " + naughtyOutput + "\n" +
                        "Date Created: " + data.getString(12) + "\n");

                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thisList);
                listview.setAdapter(listAdapter);
            }
        }

    }


}
