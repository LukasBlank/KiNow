package frontend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import backend.classes.Buchung;
import backend.classes.Nutzer;
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
        viewHolder.movieTitel.setText(b.getFilmtitel());
        viewHolder.price.setText(String.valueOf(b.getBuchungspreis()));
        //Picasso.get().load(filme.get(i).getBildLink()).into(viewHolder.movieImage);
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
