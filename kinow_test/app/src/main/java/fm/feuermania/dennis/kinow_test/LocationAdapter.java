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

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    ArrayList<String> location_list, description_list;
    Context context;

    public LocationAdapter(ArrayList<String> list, ArrayList<String> desc, Context context) {
        this.location_list = list;
        this.description_list = desc;
        this.context = context;
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.location_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder viewHolder, int i) {
        viewHolder.locTitle.setText(location_list.get(i).toString());
        viewHolder.locDesc.setText(description_list.get(i).toString());
    }

    @Override
    public int getItemCount() {
        return location_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView locImage;
        public TextView locTitle, locDesc;
        public RelativeLayout relLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            locImage  = itemView.findViewById(R.id.locImage);
            locTitle     = itemView.findViewById(R.id.locTitle);
            locDesc      = itemView.findViewById(R.id.locDesc);
            relLayout = itemView.findViewById(R.id.relLayout);
        }
    }

}
