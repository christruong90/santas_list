package ca.bcit.ass2.truong_chen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditChild extends AppCompatActivity {
    private EditText resultFirstName;
    private EditText resultId;
    private EditText resultLastName;
    private EditText resultBirthDate;
    private EditText resultStreet;
    private EditText resultCity;
    private EditText resultProvince;
    private EditText resultPostalCode;
    private EditText resultCountry;
    private EditText resultLatitude;
    private EditText resultLongitude;
    private EditText resultIsNaughty;
    private EditText resultDateCreated;
    private RadioGroup radioGroup;
    private RadioButton radioBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_child);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        radioGroup = findViewById(R.id.naughty_group);

        resultFirstName = findViewById(R.id.editText_firstName);
        resultId = findViewById(R.id.editText_id);
        resultLastName = findViewById(R.id.editText_lastName);
        resultBirthDate = findViewById(R.id.editText_birthDate);
        resultStreet = findViewById(R.id.editText_street);
        resultCity = findViewById(R.id.editText_city);
        resultProvince = findViewById(R.id.editText_province);
        resultPostalCode = findViewById(R.id.editText_postalCode);
        resultCountry = findViewById(R.id.editText_country);
        resultLatitude = findViewById(R.id.editText_latitude);
        resultLongitude = findViewById(R.id.editText_longitude);
//        resultIsNaughty = findViewById(R.id.editText_isNaughty);
//        resultDateCreated = findViewById(R.id.editText_dateCreated);


        Button submitInput = findViewById(R.id.button_EditInput);

        submitInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultFirstName.getText().toString().trim().equals("") || resultId.getText().toString().trim().equals("") || radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(EditChild.this, "You must enter all fields", Toast.LENGTH_SHORT).show();
                }
//                if((!resultIsNaughty.getText().toString().trim().toLowerCase().equals("y")) && (!resultIsNaughty.getText().toString().trim().toLowerCase().equals("n")) ) {
//                    Toast.makeText(EditChild.this," \"Is Naughty\" field must be either \"y\" or \"n\" ", Toast.LENGTH_SHORT).show();
//                }
                else {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("id", resultId.getText().toString().trim());
                    returnIntent.putExtra("firstName", resultFirstName.getText().toString().trim());
                    returnIntent.putExtra("lastName", resultLastName.getText().toString().trim());
                    returnIntent.putExtra("birthDate", resultBirthDate.getText().toString().trim());
                    returnIntent.putExtra("street", resultStreet.getText().toString().trim());
                    returnIntent.putExtra("city", resultCity.getText().toString().trim());
                    returnIntent.putExtra("province", resultProvince.getText().toString().trim());
                    returnIntent.putExtra("postalCode", resultPostalCode.getText().toString().trim());
                    returnIntent.putExtra("country", resultCountry.getText().toString().trim());


                    if (isNumeric(resultLatitude.getText().toString().trim())) {
                        returnIntent.putExtra("latitude", Double.parseDouble(resultLatitude.getText().toString()));
                    } else {
                        returnIntent.putExtra("latitude", resultLatitude.getText().toString());
                    }

                    if (isNumeric(resultLongitude.getText().toString().trim())) {
                        returnIntent.putExtra("longitude", Double.parseDouble(resultLongitude.getText().toString()));
                    } else {
                        returnIntent.putExtra("longitude", resultLongitude.getText().toString());
                    }

                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioBtn = (RadioButton) findViewById(radioId);



                    String radioText = radioBtn.getText().toString();

                    if (radioText == null) {
                    }
                    if (radioText.equals("Yes")) {
                        returnIntent.putExtra("isNaughty", 1);
                    } else if (radioText.equals("No")) {
                        returnIntent.putExtra("isNaughty", 0);
                    }

//                    if (resultIsNaughty.getText().toString().toLowerCase().equals("y")) {
//                        returnIntent.putExtra("isNaughty", 1);
//                    } else if  (resultIsNaughty.getText().toString().toLowerCase().equals("n")) {
//                        returnIntent.putExtra("isNaughty", 0);
//                    } else {
//                        Toast.makeText(EditChild.this,"ERRORS ", Toast.LENGTH_SHORT).show();
//                    }
//                    returnIntent.putExtra("dateCreated", resultDateCreated.getText().toString());


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
