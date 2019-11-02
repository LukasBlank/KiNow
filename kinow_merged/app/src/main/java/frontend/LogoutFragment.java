package frontend;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import backend.classes.Buchung;
import backend.connections.Requests;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogoutFragment extends Fragment implements AlertDialog.OnAlertButtonListener,View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button setNewPwd_btn;
    Button save_btn;
    Button logout_btn;
    LinearLayout newPwdField;
    EditText setNewPwd;
    EditText confirmSetNewPwd;
    boolean visible = true;
    boolean emptyPassword = false;

    private String setNewPwdText;
    private String confirmSetNewPwdText;

    private OnFragmentInteractionListener mListener;
    private OnLogoutListener onLogoutListener;

    public LogoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogoutFragment newInstance(String param1, String param2) {
        LogoutFragment fragment = new LogoutFragment();
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
        View view = inflater.inflate(R.layout.fragment_logout, container, false);
        setNewPwd_btn = view.findViewById(R.id.set_new_pwd_btn);
        setNewPwd_btn.setOnClickListener(this);
        save_btn = view.findViewById(R.id.save_btn);
        save_btn.setOnClickListener(this);
        logout_btn = view.findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(this);
        newPwdField = view.findViewById(R.id.new_pwd_field);

        setNewPwd = view.findViewById(R.id.set_new_pwd_input);
        confirmSetNewPwd = view.findViewById(R.id.confirm_set_new_pwd_input);

        onLogoutListener = (OnLogoutListener) getContext();

        return view;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.set_new_pwd_btn:
                if(visible){
                    newPwdField.setVisibility(View.VISIBLE);
                    visible = false;
                } else if(!visible) {
                    newPwdField.setVisibility(View.GONE);
                    visible = true;
                }
                break;

            case R.id.save_btn:

                // Get text of EditTextFields
                setNewPwdText = setNewPwd.getText().toString();
                confirmSetNewPwdText = confirmSetNewPwd.getText().toString();

                // Compare if both new pwd EditTextFields match
                // If they match, save new password
                emptyPassword = false;
                if(setNewPwdText.matches("") || setNewPwdText.contains(" ")){
                    setNewPwd.setHint("New Password *");
                    setNewPwd.setHintTextColor(getResources().getColor(R.color.red));
                    emptyPassword = true;
                }
                if(confirmSetNewPwdText.matches("") || confirmSetNewPwdText.contains(" ")){
                    confirmSetNewPwd.setHint("Confirm Password *");
                    confirmSetNewPwd.setHintTextColor(getResources().getColor(R.color.red));
                    emptyPassword = true;
                }
                if(!emptyPassword) {
                    newPwdField.setVisibility(View.GONE);
                    visible = true;
                    Toast.makeText(getContext(), "Saved new password.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.logout_btn:
                // Log user out
                ArrayList<Buchung> reservierungen = onLogoutListener.onLogoutGetRes();
                if (reservierungen!= null && reservierungen.size()>0){
                    AlertDialog alert = new AlertDialog(getActivity());
                    alert.show();
                }//then
                else {
                    onLogoutListener.onLogout();
                    Toast.makeText(getContext(), "You logged out.", Toast.LENGTH_SHORT).show();
                }//else

                break;
        }//switch

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

    @Override
    public void onYes() {
        Requests requests = new Requests();
        //stonieren
    }//onYes


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

    public interface OnLogoutListener {
        ArrayList<Buchung> onLogoutGetRes();
        void onLogout();
    }//interface
}
