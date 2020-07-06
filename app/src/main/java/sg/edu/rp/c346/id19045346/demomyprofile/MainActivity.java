package sg.edu.rp.c346.id19045346.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnsave = findViewById(R.id.buttonSave);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!etGPA.getText().toString().isEmpty() && !etName.getText().toString().isEmpty() && rgGender.getCheckedRadioButtonId() != -1) {

                    onPause();

                }
                else {
                    Toast.makeText(MainActivity.this,"Please enter text",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();

        //Step 1a : Get the user to input from the EditText and store it in a variable
        String strname = etName.getText().toString();
        float strGPA = Float.parseFloat(etGPA.getText().toString());
        int strRadio = rgGender.getCheckedRadioButtonId();



        //Step 1b : Obtain an instance of the SharedPreference
        SharedPreferences prefname = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences prefGPA = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences prefradio = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 1c : Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefname.edit();
        SharedPreferences.Editor prefEditGPA = prefGPA.edit();
        SharedPreferences.Editor prefEditRadio = prefradio.edit();

        //Step 1d: Add the key-value pair
        prefEdit.putString("names",strname);
        prefEditGPA.putFloat("gpa",strGPA);
        prefEditRadio.putInt("gender",strRadio);

        //Step 1e: Call commit() to save the changes into SharedPreference
        prefEdit.commit();
        prefEditGPA.commit();
        prefEditRadio.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreference
        SharedPreferences prefname = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences prefGPA = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences prefRadio = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data from the SharedPreference object
        String retrievename = prefname.getString("names","");
        Float retrieveGPA = prefGPA.getFloat("gpa",0);
        int retrieveRadio = prefRadio.getInt("gender",0);

        //Step 2c: Update the UI element with the value
        etName.setText(retrievename);
        etGPA.setText(Float.toString(retrieveGPA));
        rgGender.check(retrieveRadio);

    }
}
