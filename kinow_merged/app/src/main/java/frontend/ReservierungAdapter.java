package frontend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import backend.classes.Buchung;
import backend.classes.Nutzer;
import backend.classes.Sitz;
import backend.connections.Requests;

public class ReservierungAdapter extends RecyclerView.Adapter<ReservierungAdapter.ViewHolder> {

    private Nutzer nutzer;
    private ArrayList<Buchung> reservierungen;
    private Context context;

    public ReservierungAdapter (Nutzer nutzer, ArrayList<Buchung> reservierungen, Context context){
        this.nutzer = nutzer;
        this.reservierungen = reservierungen;
        this.context = context;
    }//ReservierungsAdapter

    public ReservierungAdapter(View v) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.cart_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }//onCreateViewHolder

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Requests r = new Requests();
        Buchung b = reservierungen.get(i);
        String zeit = r.getVorZeit(b.getVorführungsID());
        String filmID = b.getVorführungsID().substring(0,b.getVorführungsID().lastIndexOf('_'));
        filmID = filmID.substring(filmID.lastIndexOf('_')+1);
        String bild = r.getFilmBild(filmID);
        Picasso.get().load(bild).resize(700,935).centerCrop().into(viewHolder.movieImage);
        String datum = zeit.substring(0,zeit.indexOf('/'));
        zeit = zeit.substring(zeit.indexOf('/')+1);
        viewHolder.date.setText(datum);
        viewHolder.time.setText(zeit);
        viewHolder.movieTitel.setText(b.getFilmtitel());
        viewHolder.price.setText(String.valueOf(b.getBuchungspreis()));
        String sitze = "";
        for (Sitz s : b.getSitze()){
            String sitzID = s.getSitzID();
            sitze += sitzID.substring(sitzID.lastIndexOf('_')+1) + ", ";
        }//for
        if (sitze.length()>1){
            sitze = sitze.substring(0,sitze.lastIndexOf(','));
        }//then
        viewHolder.seats.setText(sitze);
    }//onBindViewHolder

    @Override
    public int getItemCount() {
        if (reservierungen!=null)return reservierungen.size();
        else return -1;
    }//getItemCount

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView movieImage;
        public TextView movieTitel,date,time,seats,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.cart_movieImage);
            movieTitel = itemView.findViewById(R.id.cart_item_title);
            date = itemView.findViewById(R.id.cart_item_date);
            time = itemView.findViewById(R.id.cart_item_time);
            seats = itemView.findViewById(R.id.cart_item_seats);
            price = itemView.findViewById(R.id.cart_item_price);

        }//K
    }//classViewHolder
}//class
