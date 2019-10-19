package fm.feuermania.dennis.kinow_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import lukas.classes.Film;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    ArrayList<Film> filme;
    Context context;

    public MovieAdapter(ArrayList<lukas.classes.Film> filme, Context context) {
        this.context = context;
        this.filme = filme;
    }//K

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_view, viewGroup, false);
        return new ViewHolder(v);
    }//onCreate

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.Title.setText(filme.get(i).getTitel());
        viewHolder.Desc.setText(filme.get(i).getBeschreibung());
    }//OnBindViewHolder

    @Override
    public int getItemCount() {
        if (filme!=null) return filme.size();
        else return -1;
    }//getItemCount

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView movieImage;
        public TextView Title, Desc;
        public RelativeLayout rLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImage  = itemView.findViewById(R.id.movieImage);
            Title     = itemView.findViewById(R.id.title);
            Desc      = itemView.findViewById(R.id.desc);
            rLayout         = itemView.findViewById(R.id.rLayout);
        }//ViewHolder
    }//class

}//class