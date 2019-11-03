package frontend;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import backend.classes.Bestellung;
import backend.classes.Buchung;
import backend.classes.Nutzer;
import backend.connections.Requests;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShoppingCartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShoppingCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShoppingCartFragment extends Fragment implements ReservierungAdapter.OnDeleteListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private OnLoadCartListener onLoadCartListener;
    private ReservierungAdapter.OnDeleteListener onDeleteListener = this;

    private Nutzer nutzer;
    private ArrayList<Buchung>reservierungen;
    private ArrayList<Bestellung>bestellungen;
    private ArrayList<Buchung> buchungen;

    private RecyclerView reservierugsList;
    private ReservierungAdapter resAdapter;

    private RecyclerView bestellungsList;
    private BestellungAdapter besAdapter;

    private Button buyallBtn;
    private TextView cartTV,purchasesTV;

    public ShoppingCartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShoppingCartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShoppingCartFragment newInstance(String param1, String param2) {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
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
        onLoadCartListener = (OnLoadCartListener) getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        //Lade den ausgewählten Nutzer
        nutzer = onLoadCartListener.onLoadGetNutzer();
        reservierungen = new ArrayList<>();
        bestellungen = new ArrayList<>();
        buchungen = new ArrayList<>();
        Requests request = new Requests();
        if (nutzer.getNutzerID()!=0){
            reservierungen = request.getReservierungen(String.valueOf(nutzer.getNutzerID()));
            bestellungen = request.getBestellungen(String.valueOf(nutzer.getNutzerID()));
            buchungen = getBestellungsBuchungen();
        }//then

        cartTV = view.findViewById(R.id.header_cart);
        purchasesTV = view.findViewById(R.id.header_pruchases);

        if(reservierungen.size()==0 && buchungen.size()==0){
            cartTV.setVisibility(View.GONE);
            purchasesTV.setVisibility(View.GONE);
        }//then
        if(reservierungen.size()==0)cartTV.setVisibility(View.GONE);
        else cartTV.setVisibility(View.VISIBLE);
        if(buchungen.size()==0)purchasesTV.setVisibility(View.GONE);
        else purchasesTV.setVisibility(View.VISIBLE);

        reservierugsList = view.findViewById(R.id.cart_item_list);
        reservierugsList.setHasFixedSize(true);

        bestellungsList = view.findViewById(R.id.purchases_item_list);
        bestellungsList.setHasFixedSize(true);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        reservierugsList.setLayoutManager(layoutManager);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        bestellungsList.setLayoutManager(layoutManager1);





        resAdapter = new ReservierungAdapter(nutzer,reservierungen,getActivity(),onDeleteListener);
        besAdapter = new BestellungAdapter(nutzer,buchungen,getActivity());

        reservierugsList.setAdapter(resAdapter);
        reservierugsList.getAdapter().notifyDataSetChanged();

        bestellungsList.setAdapter(besAdapter);
        bestellungsList.getAdapter().notifyDataSetChanged();

        //initialize button
        buyallBtn = view.findViewById(R.id.buy_all_btn);
        if (reservierungen.size()>0){
            buyallBtn.setVisibility(View.VISIBLE);
        }//then
        buyallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reservierungen.size()>0){
                    Requests r = new Requests();
                    boolean erfolg = r.buchen(String.valueOf(nutzer.getNutzerID()));
                    if (erfolg){
                        for (int i = reservierungen.size()-1;i>=0;i--){
                            buchungen.add(reservierungen.get(i));
                            reservierungen.remove(i);
                        }//for
                        reservierugsList.getAdapter().notifyDataSetChanged();
                        bestellungsList.getAdapter().notifyDataSetChanged();
                        buyallBtn.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT).show();
                        cartTV.setVisibility(View.GONE);
                        purchasesTV.setVisibility(View.VISIBLE);
                    }//then
                    else Toast.makeText(getContext(), "Error!", Toast.LENGTH_SHORT).show();
                }//then
                else{
                    Toast.makeText(getContext(), "Your shopping cart is empty...", Toast.LENGTH_SHORT).show();
                }//else
            }//onClick
        });

        // Inflate the layout for this fragment
        return view;
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
    public void onDelete(Buchung buchung) {
        Requests r = new Requests();
        boolean erfolg = r.stonieren(buchung.getBuchungID(),String.valueOf(nutzer.getNutzerID()));
        if (erfolg){
            reservierungen.remove(buchung);
            if (reservierungen.size()==0){
                buyallBtn.setVisibility(View.GONE);
                cartTV.setVisibility(View.GONE);
            }//then
            reservierugsList.getAdapter().notifyDataSetChanged();
            Toast.makeText(getContext(), "Deleted successfully.", Toast.LENGTH_SHORT).show();
        }//then
        else {
            Toast.makeText(getContext(), "Deletion failed.", Toast.LENGTH_SHORT).show();
        }//else
    }//onDelete

    public ArrayList<Buchung> getBestellungsBuchungen (){
        ArrayList<Buchung> buchungen = new ArrayList<>();
        if (bestellungen!=null){
            for (Bestellung b : bestellungen){
                for (Buchung buchung : b.getBuchungen()){
                    buchungen.add(buchung);
                }//for
            }//for
        }//then
        return buchungen;
    }//getBestellungsBuchungen

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

    public interface OnLoadCartListener {
        Nutzer onLoadGetNutzer();
    }//interface
}
