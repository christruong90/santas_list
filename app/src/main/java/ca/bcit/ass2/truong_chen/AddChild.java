package ca.bcit.ass2.truong_chen;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddChild extends AppCompatActivity implements ChildDialog.ChildDialogLisenter, SearchDialog.SearchDialogListener {
    myListDbHelper myDb;
    EditText editfirstName, editLastName, editBirthDate, editStreet, editCity, editProvince, editPostalCode;
    EditText editCountry, editLatitude, editLongitude, editIsNaughty, editDateCreated;
    Button btnAddData;
    ImageButton btnViewData;
    ImageButton btnEdit;
    ImageButton btnDel;
    ImageButton btnSearch;

//    String returnId;
//    String returnFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myDb = new myListDbHelper(this);







//        editfirstName = (EditText) findViewById(R.id.editText_firstName);
//        editLastName = (EditText) findViewById(R.id.editText_lastName);
//        editBirthDate = (EditText) findViewById(R.id.editText_birthDate);
//        editStreet = (EditText) findViewById(R.id.editText_street);
//        editCity = (EditText) findViewById(R.id.editText_city);
//        editProvince = (EditText) findViewById(R.id.editText_province);
//        editPostalCode = (EditText) findViewById(R.id.editText_postalCode);
//        editCountry =  (EditText) findViewById(R.id.editText_country);
//        editLatitude = (EditText) findViewById(R.id.editText_latitude);
//        editLongitude = (EditText) findViewById(R.id.editText_longitude);
//        editIsNaughty = (EditText) findViewById(R.id.editText_isNaughty);
//        editDateCreated = (EditText) findViewById(R.id.editText_dateCreated);

//        btnAddData = (Button)findViewById(R.id.button_addRow);
        btnViewData = (ImageButton) findViewById(R.id.button_showData);
        btnEdit = (ImageButton) findViewById(R.id.button_edit);
        btnDel = (ImageButton) findViewById(R.id.button_Delete);
        btnSearch = (ImageButton)findViewById(R.id.button_Search);
        viewData();
        searchData();
        editData();
        deleteData();
//        deleteEntry();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu. This adds items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_child:
                Intent i = new Intent(this, InsertRow.class);
                startActivityForResult(i, 2);
                return true;
            case R.id.action_edit_child:
                Intent k = new Intent(AddChild.this,EditChild.class );
                startActivityForResult(k, 1);
                return true;
            case R.id.action_search_child:
                openSearchDialog();
                return true;
            case R.id.action_delete_table:
                deleteList();
                return true;
            case R.id.action_delete_child:
                openDelDialog();
                return true;
            case R.id.action_display_table:
                Intent j = new Intent(AddChild.this, DisplayList.class);
                startActivity(j);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openSearchDialog() {
        SearchDialog searchWindow = new SearchDialog();
        searchWindow.show(getSupportFragmentManager(), "Search Dialog");
    }

    public void deleteList() {
        myDb.deleteAllData();
    }

    public void searchData() {
        btnSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openSearchDialog();
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

    public void viewData() {
        btnViewData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(AddChild.this, DisplayList.class);
                        startActivity(i);
                    }
                }
        );
    }


    public void editData() {
        btnEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(AddChild.this, EditChild.class);
                        startActivityForResult(i, 1);
                    }
                }
        );
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
                        Toast.makeText(AddChild.this, "Entry has been Updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddChild.this, "Data was not Updated", Toast.LENGTH_SHORT).show();
                    }
                } if(resultCode == RESULT_CANCELED) {
                Toast.makeText(AddChild.this, "Edit Canceled", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(AddChild.this, "Data has been inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddChild.this, "Data was not inserted", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }

    public void showData(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);

        builder.show();
    }

    @Override
    public void applyTexts(String id) {
        Integer deletedRows = myDb.deleteEntry(id);

        if(deletedRows > 0) {
            Toast.makeText(AddChild.this, "Child entry has been deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(AddChild.this, "Data could not be deleted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void applyTexts(String firstName, String lastName) {
        Cursor resultData = myDb.findEntry(firstName,lastName);
        if(resultData.getCount() == 0) {
            showData("error", "No data found");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (resultData.moveToNext()) {
            String resultNaughty = "";
            if (resultData.getString(11).equals("0")) {
                resultNaughty = "Not Naughty!";
            } else if (resultData.getString(11).equals("1")) {
                resultNaughty = "Naughty!";
            }
            buffer.append("Id: " + resultData.getString(0) + "\n");
            buffer.append("Name: " + resultData.getString(1) + "\n");
            buffer.append("Last Name: " + resultData.getString(2) + "\n");
            buffer.append("Birth Date: " + resultData.getString(3) + "\n");
            buffer.append("Street: " + resultData.getString(4) + "\n");
            buffer.append("City: " + resultData.getString(5) + "\n");
            buffer.append("Province: " + resultData.getString(6) + "\n");
            buffer.append("Postal Code: " + resultData.getString(7) + "\n");
            buffer.append("Country: " + resultData.getString(8) + "\n");
            buffer.append("Latitude: " + resultData.getString(9) + "\n");
            buffer.append("Longitude: " + resultData.getString(10) + "\n");
            buffer.append("Is Naughty?: " + resultNaughty + "\n");
            buffer.append("Date Created: " + resultData.getString(12) + "\n\n");
        }
        onPause();
        showData("Search Results", buffer.toString());

    }
}
