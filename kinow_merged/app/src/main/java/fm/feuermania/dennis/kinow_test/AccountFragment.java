package fm.feuermania.dennis.kinow_test;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AccountFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button registerButton;
    Button signInButton;
    Button forgotPwdButton;

    EditText email_field;
    EditText pwd_field;

    CheckBox rememberMe;

    private String email_field_input;
    private String pwd_field_input;

    private OnFragmentInteractionListener mListener;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        registerButton = view.findViewById(R.id.register_btn);
        registerButton.setOnClickListener(this);
        signInButton = view.findViewById(R.id.sign_in_btn);
        signInButton.setOnClickListener(this);
        rememberMe = view.findViewById(R.id.checkbox_remember_me);
        rememberMe.setOnCheckedChangeListener(this);
        forgotPwdButton = view.findViewById(R.id.forgot_pwd_btn);
        forgotPwdButton.setOnClickListener(this);

        email_field = view.findViewById(R.id.email_input);
        pwd_field = view.findViewById(R.id.pwd_input);

        //return inflater.inflate(R.layout.fragment_account, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            // Check in DB if User exists and if he/she used the correct e-mail and password
            case R.id.sign_in_btn:
                // Do some DB activity here

                // Get text of EditTextFields
                email_field_input = email_field.getText().toString();
                pwd_field_input = pwd_field.getText().toString();

                // Check if E-Mail or Password field is empty
                boolean check_fields = true;
                if(email_field_input.matches("") || email_field_input.contains(" ")){
                    email_field.setHint("E-Mail *");
                    email_field.setHintTextColor(getResources().getColor(R.color.red));
                    check_fields = false;
                }
                if(pwd_field_input.matches("") || pwd_field_input.contains(" ")){
                    pwd_field.setHint("Password *");
                    pwd_field.setHintTextColor(getResources().getColor(R.color.red));
                    check_fields = false;
                }

                // If data is correct, User will be signed-in
                if(check_fields) {

                    Toast.makeText(getActivity(), "SignIn - Bitte lösche diesen Toast am Ende, danke!", Toast.LENGTH_SHORT).show();
                }
                break;

            // Open RegisterUserActivity when register_btn is clicked
            case R.id.register_btn:
                Intent intent = new Intent(getActivity(), RegisterUserActivity.class);
                startActivity(intent);
                break;

            // User can choose a new password
            case R.id.forgot_pwd_btn:
                // Do some DB activity here
                // Send E-Mail to User, with Link to SetNewPassword Screen
                // DB: Replace old pwd with new chosen pwd
                Toast.makeText(getActivity(), "Forgot Pwd - Bitte lösche diesen Toast am Ende, danke!", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    // Remember User if CheckBox is checked
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(isChecked) {
            // Remember User and login
            Toast.makeText(getActivity(), "Checked - Lösch diesen Toast bitte am Ende!", Toast.LENGTH_SHORT).show();
        }
        else {
            // Do not remember User and login, if app gets closed User should be logged out automatically
            Toast.makeText(getActivity(), "Un-Checked - Lösch diesen Toast bitte am Ende!", Toast.LENGTH_SHORT).show();
        }

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
