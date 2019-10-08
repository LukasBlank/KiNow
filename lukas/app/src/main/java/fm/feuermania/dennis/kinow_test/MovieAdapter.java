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

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

        ArrayList<String> movie_list;
        Context context;

        public MovieAdapter(ArrayList<String> list, Context context) {
            this.movie_list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(context).inflate(R.layout.movie_view, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.Title.setText(movie_list.get(i).toString());
        }

        @Override
        public int getItemCount() {
            return movie_list.size();
        }

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
            }
        }

}
