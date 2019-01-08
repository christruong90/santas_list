package ca.bcit.ass2.truong_chen;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class DisplayList extends AppCompatActivity {
    myListDbHelper myDb;

    ImageButton btnInsert;
    ImageButton btnEdit;
    ImageButton btnDel;
    ImageButton btnSearch;


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

        btnInsert = (ImageButton) findViewById(R.id.button_landInsert);
        btnEdit = (ImageButton) findViewById(R.id.button_landEdit);
        btnDel = (ImageButton) findViewById(R.id.button_landDelete);
        btnSearch = (ImageButton) findViewById(R.id.button_landSearch);

//        insertData();
//        deleteData();

    }

    public void insertData() {
        btnInsert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(DisplayList.this, InsertRow.class);
                        startActivityForResult(i, 2);
                    }
                }
        );
    }

    public void deleteData() {
        btnDel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDelDialog();
                    }
                }
        );
    }

    public void openDelDialog() {
        ChildDialog deleteWindow = new ChildDialog();
        deleteWindow.show(getSupportFragmentManager(), "Delete Dialog");
    }

    public void applyTexts(String id) {
        Integer deletedRows = myDb.deleteEntry(id);

        if(deletedRows > 0) {
            Toast.makeText(DisplayList.this, "Child entry has been deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(DisplayList.this, "Data could not be deleted", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    String returnId = data.getStringExtra("id");
                    String returnLastName = data.getStringExtra("lastName");
                    String returnFirstName = data.getStringExtra("firstName");
                    String returnBirthDate = data.getStringExtra("birthDate");
                    String returnStreet = data.getStringExtra("street");
                    String returnCity = data.getStringExtra("city");
                    String returnProvince = data.getStringExtra("province");
                    String returnPostalCode = data.getStringExtra("postalCode");
                    String returnCountry = data.getStringExtra("country");
                    double returnLatitude = data.getDoubleExtra("latitude", 0);
                    double returnLongitude = data.getDoubleExtra("longitude", 0);
                    int returnIsNaughty = data.getIntExtra("isNaughty", 0);
                    String returnDateCreated = data.getStringExtra("dateCreated");

                    boolean isUpdated = myDb.editData(returnFirstName, returnId, returnLastName, returnBirthDate, returnStreet, returnCity,
                            returnProvince, returnPostalCode, returnCountry, returnLatitude, returnLongitude,returnIsNaughty);

                    if (isUpdated == true) {
                        Toast.makeText(DisplayList.this, "Entry has been Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DisplayList.this, "Data was not Updated", Toast.LENGTH_SHORT).show();
                    }
                } if(resultCode == RESULT_CANCELED) {
                Toast.makeText(DisplayList.this, "Edit Canceled", Toast.LENGTH_SHORT).show();
            }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    String firstNameInput = data.getStringExtra("insertFirstName");
                    String lastNameInput = data.getStringExtra("insertLastName");
                    String birthDateInput = data.getStringExtra("insertBirthDate");
                    String streetInput = data.getStringExtra("insertStreet");
                    String cityInput = data.getStringExtra("insertCity");
                    String provinceInput = data.getStringExtra("insertProvince");
                    String postalCodeInput = data.getStringExtra("insertPostalCode");
                    String countryInput = data.getStringExtra("insertCountry");
                    Double latitudeInput = data.getDoubleExtra("insertLatitude",0);
                    Double longitudeInput = data.getDoubleExtra("insertLongitude", 0);
                    int isNaughtyInput = data.getIntExtra("insertIsNaughty", 0);
//                    String dateCreatedInput = data.getStringExtra("insertDateCreated");


                    boolean isInserted = myDb.insertData(firstNameInput,
                            lastNameInput,
                            birthDateInput,
                            streetInput,
                            cityInput,
                            provinceInput,
                            postalCodeInput,
                            countryInput,
                            latitudeInput,
                            longitudeInput,
                            isNaughtyInput
//                            dateCreatedInput
                    );
                    if(isInserted == true) {
                        Toast.makeText(DisplayList.this, "Data has been inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DisplayList.this, "Data was not inserted", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }


}
