package fm.feuermania.dennis.kinow_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lukas.java_classes.Film;


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
    ArrayList<Film> filme;
    Requests request;

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

        request = new Requests();
        //String test = request.connect();
        String testen = "{\"1\":\"{titel=ES: Kapitel 2, regie=[Andy Muschietti], genres=[Horror], filmID=1, darsteller=[James McAvoy, Jessica Chastain, Bill Skarsgård, Bill Hader, Isaiah Mustafa, Jay Ryan, James Ransone], dauer=170, beschreibung=Das Böse taucht erneut in Derry auf: Regisseur Andy Muschietti kehrt mit \\\"ES: Kapitel 2\\\" dahin zurück, wo alles begann, und bringt den Klub der Verlierer - als Kinder und Erwachsene - wieder zusammen. Drei Jahrzehnte nach den Ereignissen des ersten Films führt \\\"ES: Kapitel 2\\\" die Charaktere, deren Wege sich mittlerweile getrennt haben, als Erwachsene erneut zusammen - denn alle 27 Jahre sucht das Böse die kleine Stadt Derry in Maine heim., bewertung=10, fsk=16}\",\"2\":\"{titel=Joker, regie=[Todd Phillips], genres=[Drama, Action], filmID=2, darsteller=[Joaquin Phoenix, Robert De Niro, Zazie Beetz], dauer=122, beschreibung=Jeder kennt Joker, den erbitterten Erzfeind und schlimmsten Gegner, der Batman erbarmungslos zusetzt, aber keiner weiß, warum das geschah. Eigentlich war Arthur Fleck früher ein Junge wie viele andere, er war etwas eigenbrötlerisch und sein Lachen klang ziemlich erschreckend, doch nichts deutete darauf hin, dass er das Böse in sich trug, welches er später zu seinem Lebensinhalt machte. Doch der Spott und die Demütigungen, denen er sogar in seinen therapeutischen Sitzungen ausgesetzt war, ließen in seiner Seele eine finstere Saat reifen..., bewertung=9, fsk=16}\",\"3\":\"{titel=Downton Abbey, regie=[Michael Engler], genres=[Drama, Historienfilm, Liebesfilm], filmID=3, darsteller=[Hugh Bonneville, Jim Carter, Michelle Dockery], dauer=122, beschreibung=Aufregung auf Downton Abbey: Der König und die Königin erweisen dem Hause Grantham die Ehre. Es müssen ein royaler Lunch, eine Parade und ein Dinner organisiert werden. Aber wir befinden uns im Jahr 1927 und der Earl of Grantham (Hugh Bonneville) sinnt, ob sie nach all den Veränderungen der letzten Jahre überhaupt noch einem königlichen Besuch gewachsen sind... Als wäre diese Aufgabe nicht schon schwer genug zu bewältigen, muss sich das Personal mit dem furchteinflößenden royalen Haushalt herumärgern, und schon bald keimt eine kleine Revolte im unteren Teil des Hauses auf. Währenddessen quält Lady Mary (Michelle Dockery) die Frage, ob der Erhalt von Downton überhaupt noch zeitgemäß ist. Soll das Familienanwesen vielleicht doch einer bürgerlichen Institution weichen? Auch die Dowager Countess of Grantham (Maggie Smith) sieht dem königlichen Besuch mit gemischten Gefühlen entgegen, gehört ihre Cousine Lady Bagshaw (Imelda Staunton) doch zum royalen Gefolge, und mit dem Aufeinandertreffen reißen auch alte Wunden wieder auf., bewertung=6, fsk=0}\",\"4\":\"{titel=Gemini Man, regie=[Ang Lee], genres=[Action, Thriller, Science-Fiction], filmID=4, darsteller=[Will Smith, Mary Elizabeth Winstead, Clive Owen], dauer=119, beschreibung=Der Elite-Auftragskiller Henry Brogan (Will Smith) sieht sich plötzlich selbst im Zentrum der Verfolgung durch einen mysteriösen jungen Agenten, der scheinbar jeden einzelnen seiner Schritte vorhersehen kann. , bewertung=8, fsk=12}\",\"5\":\"{titel=Eine ganz heiße Nummer 2.0, regie=[Rainer Kaufmann], genres=[Komödie], filmID=5, darsteller=[Gisela Schneeberger, Rosalie Thomass, Bettina Mittendorfer, Franziska Schlattner, Matthias Ransberger, Tristan Seith, Felix von Manteuffel, Jorge González, Günther Maria Halmer], dauer=91, beschreibung=In Marienzell ist der Wurm drin: Die Leute ziehen weg, die Touristen kommen nicht mehr, es gibt keine Arbeit und keine Kinder. Niemand interessiert sich mehr für das Dorf, denn ohne High Speed Internet ist man heutzutage abgeschrieben, quasi ein niemand. Doch eine schnelle Leitung ist nicht in Sicht: Zu wenig Einwohner und zu hohe Kosten. Während die Männer sich erfolglos durch die Landschaft graben, um die Kabel selbst zu verlegen, haben die Freundinnen Waltraud (Gisela Schneeberger), Maria (Bettina Mittendorfer) und Lena (Rosalie Thomass) eine ganz andere Idee: Das große Preisgeld beim Tanzwettbewerb im benachbarten Josefskirchen! Eine heiße Sohle aufs Parkett legen und mit dem Gewinn die teure Internetleitung anzahlen - das wäre gleichzeitig noch die beste Werbung für Marienzell! Aber das Trio hat die Rechnung ohne Moni (Franziska Schlattner) gemacht, denn die intrigante Frau des Bürgermeisters will das Preisgeld mit ihrem \\\"hochanständigen\\\" Trachtenverein ebenfalls abräumen..., bewertung=3, fsk=12}\",\"6\":\"{titel=Everest - Ein Yeti will hoch hinaus, regie=[Jill Culton, Todd Wilderman], genres=[Animation, Abenteuer, Komödie], filmID=6, dauer=97, beschreibung=Ein kleiner Yeti ist einem Versuchslabor in Shanghai entkommen und sucht Zuflucht auf den Dächern der Millionenstadt. Dort begegnet er ausgerechnet dem neugierigen Teenager-Mädchen Yi und ihren Freunden Jin und Peng. Schnell wird klar, dass das Trio den kleinen Yeti, den sie kurzerhand auf den Namen \\\"Everest\\\" taufen, nur vor dem finsteren Laborleiter Burnish und der Zoologin Dr. Zara retten können, wenn sie ihn zurück in seine Heimat bringen. Gemeinsam begeben sich die Freunde auf eine faszinierende Reise durch sagenhafte Landschaften, wunderliche Ereignisse und magische Momente, um Everest am höchsten Punkt der Erde wieder mit seiner Familie zu vereinen., bewertung=1, fsk=0}\",\"7\":\"{titel=Ich war noch niemals in New York, regie=[Philipp Stölzl], genres=[Komödie, Musical], filmID=7, darsteller=[Heike Makatsch, Moritz Bleibtreu, Katharina Thalbach, Uwe Ochsenknecht, Michael Ostrowski, Pasquale Aleardi, Marlon Schramm, Mat Schuh], dauer=128, beschreibung=Für Lisa Wartberg (Heike Makatsch), erfolgsverwöhnte Fernsehmoderatorin und Single, steht ihre Show an erster Stelle. Doch dann verliert ihre Mutter Maria (Katharina Thalbach) nach einem Unfall ihr Gedächtnis, kommt ins Krankenhaus und kann sich nur noch an eines erinnern: Sie war noch niemals in New York! Kurzentschlossen flieht Maria und schmuggelt sich als blinder Passagier an Bord eines luxuriösen Kreuzfahrtschiffes. Gemeinsam mit ihrem Maskenbildner Fred (Michael Ostrowski) macht sich Lisa auf die Suche nach ihrer Mutter und spürt sie tatsächlich auf der \\\"MS Maximiliane\\\" auf. Doch bevor die beiden Maria wieder von Bord bringen können, legt der Ozeandampfer auch schon ab und die drei finden sich auf einer unfreiwilligen Reise über den Atlantik wieder. Lisa lernt an Bord Axel Staudach (Moritz Bleibtreu) und dessen Sohn Florian (Marlon Schramm) kennen. Axel ist so gar nicht Lisas Typ, doch durch eine Reihe unglücklicher Missgeschicke kommen sich die beiden schließlich näher... Mutter Maria trifft auf Eintänzer Otto (Uwe Ochsenknecht), der behauptet, eine gemeinsame Vergangenheit mit ihr zu haben - was Maria mangels Gedächtnis natürlich nicht überprüfen kann. Und Fred verliebt sich Hals über Kopf in den griechischen Bordzauberer Costa (Pasquale Aleardi). So verläuft die turbulente Schiffsreise - mit mehrmaligem Finden und Verlieren der Liebe und jeder Menge Überraschungen - nach New York., bewertung=5, fsk=0}\"}";
        request.convert(testen);

        mAdapter = new MovieAdapter(filme,getActivity());
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
