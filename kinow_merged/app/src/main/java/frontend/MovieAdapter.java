package frontend;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.Serializable;
import java.util.ArrayList;

import backend.classes.Film;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements Serializable {

    ArrayList<Film> filme;
    Context context;

    public MovieAdapter(ArrayList<Film> filme, Context context) {
        this.filme = filme;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_view, viewGroup, false);

        // OnItemClick -> Open MovieDetailScreen Activity
        final ViewHolder vHolder = new ViewHolder(v);
        final Intent intent = new Intent(context, MovieDetailScreen.class);
        vHolder.rLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Film film = new Film();
                for (Film fi : filme){
                    if (fi.getTitel().equals(vHolder.Title.getText().toString()))film = fi;
                }//for

                Toast.makeText(context, "Film " + film.getTitel() + " wurde aufgerufen.", Toast.LENGTH_SHORT).show();
                intent.putExtra("filmSelect", (Serializable) film);
                context.startActivity(intent);
            }
        });

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.Title.setText(filme.get(i).getTitel());
        viewHolder.Desc.setText(filme.get(i).getBeschreibung());
    }

    @Override
    public int getItemCount() {
         if (filme!= null) return filme.size();
         else return -1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView movieImage;
        public TextView Title, Desc;
        public RelativeLayout rLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.movieImage);
            Title = itemView.findViewById(R.id.title);
            Desc = itemView.findViewById(R.id.desc);
            rLayout = itemView.findViewById(R.id.rLayout);
        }
    }

}//class
