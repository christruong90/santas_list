package ca.bcit.ass2.truong_chen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertRow extends AppCompatActivity {
    private EditText insertFirstName;
    private EditText insertLastName;
    private EditText insertBirthDate;
    private EditText insertStreet;
    private EditText insertCity;
    private EditText insertProvince;
    private EditText insertPostalCode;
    private EditText insertCountry;
    private EditText insertLatitude;
    private EditText insertLongitude;
    private EditText insertIsNaughty;
    private EditText insertDateCreated;
    private RadioGroup radioGroup;
    private RadioButton radioBtn;

    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_row);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        radioGroup = findViewById(R.id.naughty_group);
        insertFirstName = findViewById(R.id.editText_firstName);
        insertLastName = findViewById(R.id.editText_lastName);
        insertBirthDate = findViewById(R.id.editText_birthDate);
        insertStreet = findViewById(R.id.editText_street);
        insertCity = findViewById(R.id.editText_city);
        insertProvince = findViewById(R.id.editText_province);
        insertPostalCode = findViewById(R.id.editText_postalCode);
        insertCountry = findViewById(R.id.editText_country);
        insertLatitude = findViewById(R.id.editText_latitude);
        insertLongitude = findViewById(R.id.editText_longitude);
//        insertIsNaughty = findViewById(R.id.editText_isNaughty);
//        insertDateCreated = findViewById(R.id.editText_dateCreated);




        Button submitInput = findViewById(R.id.button_InsertInput);

        submitInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(insertFirstName.getText().toString().trim().equals("") || insertLastName.getText().toString().trim().equals("") ||
                        radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(InsertRow.this, "You must enter all fields", Toast.LENGTH_SHORT).show();
                }
//                if ((!insertIsNaughty.getText().toString().toLowerCase().trim().equals("y")) && (!insertIsNaughty.getText().toString().toLowerCase().trim().equals("n")) ) {
//                    Toast.makeText(InsertRow.this," \"Is Naughty\" field must be either \"y\" or \"n\" ", Toast.LENGTH_SHORT).show();
//                }

                else {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("insertFirstName", insertFirstName.getText().toString().trim());
                    returnIntent.putExtra("insertLastName", insertLastName.getText().toString().trim());
                    returnIntent.putExtra("insertBirthDate", insertBirthDate.getText().toString().trim());
                    returnIntent.putExtra("insertStreet", insertStreet.getText().toString().trim());
                    returnIntent.putExtra("insertCity", insertCity.getText().toString().trim());
                    returnIntent.putExtra("insertProvince", insertProvince.getText().toString().trim());
                    returnIntent.putExtra("insertPostalCode", insertPostalCode.getText().toString().trim());
                    returnIntent.putExtra("insertCountry", insertCountry.getText().toString().trim());

                    if (isNumeric(insertLatitude.getText().toString())) {
                        returnIntent.putExtra("insertLatitude", Double.parseDouble(insertLatitude.getText().toString().trim()));
                    } else {
                        returnIntent.putExtra("insertLatitude", insertLatitude.getText().toString().trim());
                    }

                    if (isNumeric(insertLongitude.getText().toString().trim())) {
                        returnIntent.putExtra("insertLongitude", Double.parseDouble(insertLongitude.getText().toString().trim()));
                    } else {
                        returnIntent.putExtra("insertLongitude",insertLongitude.getText().toString().trim());
                    }

                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioBtn = (RadioButton) findViewById(radioId);



                    String radioText = radioBtn.getText().toString();

                    if (radioText == null) {
                    }
                    if (radioText.equals("Yes")) {
                        returnIntent.putExtra("insertIsNaughty", 1);
                    } else if (radioText.equals("No")) {
                        returnIntent.putExtra("insertIsNaughty", 0);
                    }




//                    if(naughtyYes.isChecked()){
//                        naughtyNo.setChecked(false);
//                        returnIntent.putExtra("insertIsNaughty", 1);
//                    } if (naughtyNo.isChecked()) {
//                        naughtyYes.setChecked(false);
//                        returnIntent.putExtra("insertIsNaughty", 0);
//                    }
//                    if (insertIsNaughty.getText().toString().toLowerCase().trim().equals("y")) {
//                        returnIntent.putExtra("insertIsNaughty", 1);
//                    } else if  (insertIsNaughty.getText().toString().toLowerCase().trim().equals("n")) {
//                        returnIntent.putExtra("insertIsNaughty", 0);
//                    } else {
//                        Toast.makeText(InsertRow.this,"ERRORS ", Toast.LENGTH_SHORT).show();
//                    }


//                    returnIntent.putExtra("insertDateCreated", insertDateCreated.getText().toString());

                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            }
        });

    }


    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
