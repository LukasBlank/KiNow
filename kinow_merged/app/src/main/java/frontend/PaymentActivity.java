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

public class PaymentActivity extends AppCompatActivity {

    LinearLayout paypalLinLay = findViewById(R.id.paypal_linLay);
    LinearLayout mastercardLinLay = findViewById(R.id.mastercard_linLay);

    Boolean paypal_bool = false;
    Boolean mastercard_bool = false;
    Boolean a = false;
    Boolean b = false;

    // PayPal
    EditText pEmailInput = findViewById(R.id.paypal_email_input);
    String paypalEmail;
    EditText pPwdInput = findViewById(R.id.paypal_pwd_input);
    String paypalPwd;

    // MasterCard
    EditText cardHolder = findViewById(R.id.mastercard_cardholder_input);
    String mastercardHolder;
    EditText cardNumber = findViewById(R.id.mastercard_cardnumber_input);
    String mastercardNumber;
    EditText expirationDate = findViewById(R.id.mastercard_expire_input);
    String mastercardExpiration;
    EditText securityCode = findViewById(R.id.mastercard_securitycode_input);
    String mastercardSecurityCode;

    Button pay_btn = findViewById(R.id.pay_now_btn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pay();
            }
        });

        RadioGroup rg = findViewById(R.id.radio_group);
        final RadioButton rb_paypal = findViewById(R.id.rb_paypal);
        final RadioButton rb_mastercard = findViewById(R.id.rb_mastercard);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.rb_paypal){
                    rb_paypal.setTextColor(Color.BLACK);
                    rb_mastercard.setTextColor(Color.GRAY);
                    paypalLinLay.setVisibility(View.VISIBLE);
                    pay_btn.setVisibility(View.VISIBLE);
                    paypal_bool = true;
                }
                if (checkedId == R.id.rb_mastercard){
                    rb_mastercard.setTextColor(Color.BLACK);
                    rb_paypal.setTextColor(Color.GRAY);
                    mastercardLinLay.setVisibility(View.VISIBLE);
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
               Toast.makeText(this, "Payment completed.", Toast.LENGTH_SHORT).show();
               finish();
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
                Toast.makeText(this, "Payment completed.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

}
