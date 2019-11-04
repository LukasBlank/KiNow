package frontend;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import backend.classes.Buchung;
import backend.classes.Nutzer;
import backend.classes.Sitz;
import backend.connections.Requests;

public class PaymentActivity extends AppCompatActivity {

    LinearLayout paypalLinLay;
    LinearLayout mastercardLinLay;

    Boolean paypal_bool = false;
    Boolean mastercard_bool = false;
    Boolean a = false;
    Boolean b = false;

    // PayPal
    EditText pEmailInput;
    String paypalEmail;
    EditText pPwdInput;
    String paypalPwd;

    // MasterCard
    EditText cardHolder;
    String mastercardHolder;
    EditText cardNumber;
    String mastercardNumber;
    EditText expirationDate;
    String mastercardExpiration;
    EditText securityCode;
    String mastercardSecurityCode;
    Button pay_btn;

    Nutzer nutzer;
    ArrayList<Buchung> reservierungen;
    ArrayList<Sitz> sitze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);

        nutzer = (Nutzer) getIntent().getSerializableExtra("nutzer");
        if (nutzer.getNutzerID()!=0)reservierungen = (ArrayList<Buchung>) getIntent().getSerializableExtra("reservierungen");
        else reservierungen = new ArrayList<>();
        if (nutzer.getNutzerID()==0)sitze = (ArrayList<Sitz>) getIntent().getSerializableExtra("sitze");
        else sitze = new ArrayList<>();

        pay_btn = findViewById(R.id.pay_now_btn);

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if (nutzer.getNutzerID()!=0)pay();
                 else payGuest();
            }
        });

        RadioGroup rg = findViewById(R.id.radio_group);
        final RadioButton rb_paypal = findViewById(R.id.rb_paypal);
        final RadioButton rb_mastercard = findViewById(R.id.rb_mastercard);

        paypalLinLay = findViewById(R.id.paypal_linLay);
        mastercardLinLay = findViewById(R.id.mastercard_linLay);

        //finde Textfelder etc
        pEmailInput = findViewById(R.id.paypal_email_input);
        pPwdInput = findViewById(R.id.paypal_pwd_input);
        cardHolder = findViewById(R.id.mastercard_cardholder_input);
        cardNumber = findViewById(R.id.mastercard_cardnumber_input);
        securityCode = findViewById(R.id.mastercard_securitycode_input);
        expirationDate = findViewById(R.id.mastercard_expire_input);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_paypal){
                    rb_paypal.setTextColor(Color.BLACK);
                    rb_mastercard.setTextColor(Color.GRAY);
                    paypalLinLay.setVisibility(View.VISIBLE);
                    mastercardLinLay.setVisibility(View.GONE);
                    pay_btn.setVisibility(View.VISIBLE);
                    paypal_bool = true;
                    if(nutzer.getNutzerID()!=0)pEmailInput.setText(nutzer.getEmail());
                }//then
                if (checkedId == R.id.rb_mastercard){
                    rb_mastercard.setTextColor(Color.BLACK);
                    rb_paypal.setTextColor(Color.GRAY);
                    mastercardLinLay.setVisibility(View.VISIBLE);
                    paypalLinLay.setVisibility(View.GONE);
                    pay_btn.setVisibility(View.VISIBLE);
                    mastercard_bool = true;
                }
            }
        });

    }

    public void pay() {

        // PayPal
       if(paypal_bool){
           paypalEmail = pEmailInput.getText().toString();
           paypalPwd = pPwdInput.getText().toString();
           if(paypalEmail.matches("") || paypalEmail.contains(" ")){
               pEmailInput.setHint("E-Mail *");
               pEmailInput.setHintTextColor(getResources().getColor(R.color.red));
               a = true;
           }
           if(paypalPwd.matches("") || paypalPwd.contains(" ")){
               pPwdInput.setHint("Password *");
               pPwdInput.setHintTextColor(getResources().getColor(R.color.red));
               a = true;
           }
           if(!a){
               Requests r = new Requests();
               boolean erfolg = r.buchen(String.valueOf(nutzer.getNutzerID()),"Paypal");
               if (erfolg){
                   Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
                   setResult(1);
                   finish();
               }
               else {
                   Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
                   setResult(0);
                   finish();
               }

           }
       }

       // MasterCard
        if(mastercard_bool){
            mastercardHolder = cardHolder.getText().toString();
            mastercardNumber = cardNumber.getText().toString();
            mastercardSecurityCode = securityCode.getText().toString();
            mastercardExpiration = expirationDate.getText().toString();
            if(mastercardHolder.matches("") || mastercardHolder.contains(" ")){
                cardHolder.setHint("Card Holder`s Name *");
                cardHolder.setHintTextColor(getResources().getColor(R.color.red));
                b = true;
            }
            if(mastercardNumber.matches("") || mastercardNumber.contains(" ")){
                cardNumber.setHint("Card Number *");
                cardNumber.setHintTextColor(getResources().getColor(R.color.red));
                b = true;
            }
            if(mastercardSecurityCode.matches("") || mastercardSecurityCode.contains(" ")){
                securityCode.setHint("Security Code *");
                securityCode.setHintTextColor(getResources().getColor(R.color.red));
                b = true;
            }
            if(mastercardExpiration.matches("") || mastercardExpiration.contains(" ")){
                expirationDate.setHint("Expiration Date *");
                expirationDate.setHintTextColor(getResources().getColor(R.color.red));
                b = true;
            }
            if(!b){
                Requests r = new Requests();
                boolean erfolg = r.buchen(String.valueOf(nutzer.getNutzerID()),"Paypal");
                if (erfolg){
                    Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
                    setResult(1);
                    finish();
                }
                else {
                    setResult(0);
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    private void payGuest(){
        if(paypal_bool) {
            paypalEmail = pEmailInput.getText().toString();
            paypalPwd = pPwdInput.getText().toString();
            if (paypalEmail.matches("") || paypalEmail.contains(" ")) {
                pEmailInput.setHint("E-Mail *");
                pEmailInput.setHintTextColor(getResources().getColor(R.color.red));
                a = true;
            }
            if (paypalPwd.matches("") || paypalPwd.contains(" ")) {
                pPwdInput.setHint("Password *");
                pPwdInput.setHintTextColor(getResources().getColor(R.color.red));
                a = true;
            }
            if (paypalEmail.indexOf('@')==-1) Toast.makeText(this, "Email incorrect.", Toast.LENGTH_SHORT).show();
            else {
                if (!a) {
                    Requests r = new Requests();
                    boolean erfolg = r.gastBuchen(sitze);
                    if (erfolg) {
                        Toast.makeText(this, "Tickets were send to + " + pEmailInput + ".", Toast.LENGTH_SHORT).show();
                        setResult(1);
                        finish();
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
                        setResult(0);
                        finish();
                    }

                }//then
            }//else

        }//then

        if(mastercard_bool){
            mastercardHolder = cardHolder.getText().toString();
            mastercardNumber = cardNumber.getText().toString();
            mastercardSecurityCode = securityCode.getText().toString();
            mastercardExpiration = expirationDate.getText().toString();
            if(mastercardHolder.matches("") || mastercardHolder.contains(" ")){
                cardHolder.setHint("Card Holder`s Name *");
                cardHolder.setHintTextColor(getResources().getColor(R.color.red));
                b = true;
            }
            if(mastercardNumber.matches("") || mastercardNumber.contains(" ")){
                cardNumber.setHint("Card Number *");
                cardNumber.setHintTextColor(getResources().getColor(R.color.red));
                b = true;
            }
            if(mastercardSecurityCode.matches("") || mastercardSecurityCode.contains(" ")){
                securityCode.setHint("Security Code *");
                securityCode.setHintTextColor(getResources().getColor(R.color.red));
                b = true;
            }
            if(mastercardExpiration.matches("") || mastercardExpiration.contains(" ")){
                expirationDate.setHint("Expiration Date *");
                expirationDate.setHintTextColor(getResources().getColor(R.color.red));
                b = true;
            }
            if(!b){
                Requests r = new Requests();
                boolean erfolg = r.gastBuchen(sitze);
                if (erfolg){
                    Toast.makeText(this, "\"Tickets were send to + \" + pEmailInput + \".\"", Toast.LENGTH_SHORT).show();
                    setResult(1);
                    finish();
                }
                else {
                    setResult(0);
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }//mastercard

    }//payGuest


}
