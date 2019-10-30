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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import backend.classes.Film;
import backend.classes.Kino;
import backend.classes.Nutzer;
import backend.classes.Vorführung;
import backend.connections.Requests;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MoviesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MoviesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoviesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // movieList RecyclerView
    private RecyclerView movieList;
    private View movieView;
    MovieAdapter mAdapter;
    private Kino kino;
    private long kinoAlt,nutzerAlt;
    private ArrayList<Film> filme;
    private Nutzer nutzer;

    private OnFragmentInteractionListener mListener;
    private OnSelectionListener onSelectionListener;

    public MoviesFragment() {
        // Required empty public constructor
    }//K

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoviesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoviesFragment newInstance(String param1, String param2) {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }//newInstance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }//then
        //setting params
        filme = new ArrayList<Film>();
        kino = new Kino();
        kino.setKinoID(0);
        kinoAlt = 0;
        nutzer = new Nutzer();
        nutzer.setNutzerID(0);
        nutzerAlt = 0;
    }//onCreate

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        movieView = inflater.inflate(R.layout.fragment_movies, container, false);

        movieList = movieView.findViewById(R.id.movieList);
        movieList.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        movieList.setLayoutManager(layoutManager);

        //Beim ersten Mal alle Filme holen, danach nur neu holen, wenn sich das gewählte Kino ändert
        onSelectionListener = (OnSelectionListener) getContext();
        kino  = onSelectionListener.getSelectedKino();
        nutzer = onSelectionListener.getSelectedNutzer();

        if (filme.size()==0 || kino.getKinoID()!=kinoAlt || nutzer.getNutzerID()!=nutzerAlt){
            kinoAlt = kino.getKinoID();
            nutzerAlt = nutzer.getNutzerID();
            Requests request = new Requests();
            filme = request.getFilme(kino.getKinoID());
            mAdapter = new MovieAdapter(filme, kino ,nutzer, getActivity());
        }//then

        movieList.setAdapter(mAdapter);
        movieList.getAdapter().notifyDataSetChanged();

        return movieView;
    }//onCreateView



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }//then
    }//onButtonPressed

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
           mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }//else
    }//onAttach

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }//onDetach

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

    public interface OnSelectionListener {
        Kino getSelectedKino ();
        Nutzer getSelectedNutzer();
    }//interface

}//class
