package fm.feuermania.dennis.kinow_test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import lukas.classes.Kino;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    ArrayList<Kino> kinos;
    Context context;
    LocationAdapter.ViewHolder vh;
    onLocationClickedListener kListener;


    public LocationAdapter(ArrayList<Kino> kinos, Context context, onLocationClickedListener listener) {
        this.kinos = kinos;
        this.context = context;
        kListener = listener;
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.location_view, viewGroup, false);
        return new ViewHolder(v, kListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder viewHolder, int i) {
        vh = viewHolder;
        viewHolder.locTitle.setText(kinos.get(i).getName());
        viewHolder.locDesc.setText(kinos.get(i).getOrt());
    }

    @Override
    public int getItemCount() {
        if (kinos!=null)return kinos.size();
        else return -1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView locImage;
        public TextView locTitle, locDesc;
        public RelativeLayout relLayout;
        public CardView cv;
        onLocationClickedListener oL;

        public ViewHolder(@NonNull View itemView, onLocationClickedListener oL) {
            super(itemView);

            locImage = itemView.findViewById(R.id.locImage);
            locTitle = itemView.findViewById(R.id.locTitle);
            locDesc = itemView.findViewById(R.id.locDesc);
            relLayout = itemView.findViewById(R.id.relLayout);
            cv = itemView.findViewById(R.id.cv);
            this.oL = oL;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                if (getAdapterPosition()!=RecyclerView.NO_POSITION){
                    oL.onKinoSelection(getAdapterPosition());
                }//then

            }//then
        }
    }


    public interface onLocationClickedListener {
        void onKinoSelection (long id);
    }//interface

}
