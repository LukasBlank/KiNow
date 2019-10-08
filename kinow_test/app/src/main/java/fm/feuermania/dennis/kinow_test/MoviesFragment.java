package fm.feuermania.dennis.kinow_test;

import android.content.Context;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


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
    String movies[]={"TestMovieOne","TestMovieTwo","Drei", "Vier", "Fuenf", "Sechs", "Sieben"};
    String desc [] = {"Description 1", "Description 2", "Description 3", "Description 4", "Description 5", "Description 6", "Description 7"};

    private OnFragmentInteractionListener mListener;

    public MoviesFragment() {
        // Required empty public constructor
    }

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
        movieView = inflater.inflate(R.layout.fragment_movies, container, false);

        movieList = movieView.findViewById(R.id.movieList);
        movieList.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        movieList.setLayoutManager(layoutManager);

        ArrayList<String> list_of_movies = new ArrayList<>();
        list_of_movies.add(movies[0]);
        list_of_movies.add(movies[1]);
        list_of_movies.add(movies[2]);
        list_of_movies.add(movies[3]);
        list_of_movies.add(movies[4]);
        list_of_movies.add(movies[5]);
        list_of_movies.add(movies[6]);

        ArrayList<String> list_of_descriptions = new ArrayList<>();
        list_of_descriptions.add(desc[0]);
        list_of_descriptions.add(desc[1]);
        list_of_descriptions.add(desc[2]);
        list_of_descriptions.add(desc[3]);
        list_of_descriptions.add(desc[4]);
        list_of_descriptions.add(desc[5]);
        list_of_descriptions.add(desc[6]);

        mAdapter = new MovieAdapter(list_of_movies,list_of_descriptions, getActivity());
        movieList.setAdapter(mAdapter);

        movieList.getAdapter().notifyDataSetChanged();


        return movieView;
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
