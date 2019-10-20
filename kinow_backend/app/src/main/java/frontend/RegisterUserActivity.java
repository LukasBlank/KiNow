package frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import backend.classes.Nutzer;
import backend.connections.Requests;

public class RegisterUserActivity extends AppCompatActivity {

    EditText reg_firstName_field;
    EditText reg_lastName_field;
    EditText reg_eMail_field;
    EditText reg_pwd_field;
    EditText reg_confirmPwd_field;

    String firstname_input;
    String lastname_input;
    String email_input;
    String pwd_input;
    String confirm_pwd_input;

    CheckBox accept_terms;
    CheckBox minimum_age;

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

    }

    public void registerUser(View view) {

        // Put in some code for saving user data to the DB
        // Log User in

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
                Toast.makeText(getBaseContext(), "Passwort inkorrekt.", Toast.LENGTH_SHORT).show();
            else {
                Requests request = new Requests();
                Nutzer neu = new Nutzer();
                neu.setVorname(firstname_input);neu.setNachname(lastname_input);neu.setEmail(email_input);
                neu.setPasswort(pwd_input);
                /**
                Intent intent = new Intent(RegisterUserActivity.this, MainActivity.class);
                startActivity(intent);
                 **/
            }//else
        }//then
    }//meth

    public void openSignInPage(View view) {
        finish();
    }

}
