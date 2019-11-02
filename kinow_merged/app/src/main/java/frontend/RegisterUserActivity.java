package frontend;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import backend.classes.Nutzer;
import backend.connections.Requests;

public class RegisterUserActivity extends AppCompatActivity {

    EditText reg_firstName_field;
    EditText reg_lastName_field;
    EditText reg_eMail_field;
    EditText reg_pwd_field;
    EditText reg_confirmPwd_field;
    EditText birthday_field;
    EditText gender_field;

    String firstname_input;
    String lastname_input;
    String email_input;
    String pwd_input;
    String confirm_pwd_input;
    String dot = ".";

    CheckBox accept_terms;
    CheckBox minimum_age;

    DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        reg_firstName_field = findViewById(R.id.reg_firstname_input);
        reg_lastName_field = findViewById(R.id.reg_lastname_input);
        reg_eMail_field = findViewById(R.id.reg_email_input);
        reg_pwd_field = findViewById(R.id.reg_pwd_input);
        reg_confirmPwd_field = findViewById(R.id.reg_confirm_pwd_input);
        accept_terms = findViewById(R.id.checkbox_terms);
        minimum_age = findViewById(R.id.checkbox_sixteen);

        birthday_field = findViewById(R.id.reg_birthday_input);
        birthday_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendar();
            }
        });

        gender_field = findViewById(R.id.reg_gender_input);
        gender_field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseGender();
            }
        });

    }

    // Single Choice Gender
    public void chooseGender() {
        final String[] singleChoiceItems = getResources().getStringArray(R.array.gender);
        int itemSelected = -1;
        new AlertDialog.Builder(this)
                .setTitle("Select Your Gender:")
                .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int selectedIndex) {
                        gender_field.setText(singleChoiceItems[selectedIndex]);
                    }
                })
                .setPositiveButton("Ok", null)
                .setNegativeButton("Cancel", null)
                .show();
    }

    // Date Picker Dialog
    public void openCalendar() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        datePicker = new DatePickerDialog(RegisterUserActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                birthday_field.setText(dayOfMonth + dot + (monthOfYear + 1) + dot + year);
            }
        }, year, month, day);
        datePicker.show();
    }

    public void openTerms(View view) {
        Intent i = new Intent(RegisterUserActivity.this, TermsOfServiceActivity.class);
        startActivity(i);
    }

    public void registerUser(View view) {

        // Get text of EditTextFields
        firstname_input = reg_firstName_field.getText().toString();
        lastname_input = reg_lastName_field.getText().toString();
        email_input = reg_eMail_field.getText().toString();
        pwd_input = reg_pwd_field.getText().toString();
        confirm_pwd_input = reg_confirmPwd_field.getText().toString();

        // Check if any field is empty or if any checkbox is unchecked
        boolean check = true;
        boolean checkboxes = true;
        if(firstname_input.matches("") || firstname_input.contains(" ")){
            reg_firstName_field.setHint("First Name *");
            reg_firstName_field.setHintTextColor(getResources().getColor(R.color.red));
            check = false;
        }
        if(lastname_input.matches("") || lastname_input.contains(" ")){
            reg_lastName_field.setHint("Last Name *");
            reg_lastName_field.setHintTextColor(getResources().getColor(R.color.red));
            check = false;
        }
        if(email_input.matches("") || email_input.contains(" ")){
            reg_eMail_field.setHint("E-Mail *");
            reg_eMail_field.setHintTextColor(getResources().getColor(R.color.red));
            check = false;
        }
        if(pwd_input.matches("") || pwd_input.contains(" ")){
            reg_pwd_field.setHint("Password *");
            reg_pwd_field.setHintTextColor(getResources().getColor(R.color.red));
            check = false;
        }
        if(confirm_pwd_input.matches("") || confirm_pwd_input.contains(" ")){
            reg_confirmPwd_field.setHint("Confirm Password *");
            reg_confirmPwd_field.setHintTextColor(getResources().getColor(R.color.red));
            check = false;
        }
        if(!accept_terms.isChecked()){
            accept_terms.setTextColor(getResources().getColor(R.color.red));
            checkboxes = false;
        }
        if(!minimum_age.isChecked()){
            minimum_age.setTextColor(getResources().getColor(R.color.red));
            checkboxes = false;
        }

        // At the end of registration, open MainActivity as logged-in User
        if(check && checkboxes) {
            if (!pwd_input.equals(confirm_pwd_input))
                Toast.makeText(getBaseContext(), "Password is not correct.", Toast.LENGTH_SHORT).show();
            else {
                Requests request = new Requests();
                Nutzer neu = new Nutzer();
                neu.setVorname(firstname_input);neu.setNachname(lastname_input);neu.setEmail(email_input);
                neu.setPasswort(pwd_input);
                boolean success = request.registerUser(neu);
                if (success){
                    Toast.makeText(getBaseContext(), "Registrierung erfolgreich. Sie können sich nun anmelden."
                            , Toast.LENGTH_SHORT).show();
                    finish();
                }//then
                else Toast.makeText(getBaseContext(), "Registrierung nicht möglich", Toast.LENGTH_SHORT).show();
            }//else
        }//then
    }//meth
}//class
