package bar.final2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ASUS on 6/6/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RestaurantInfoHolder> {
    public static class RestaurantInfoHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView restuarantName;
        TextView restaurantDetails;
        ImageView restaurantPhoto;
        RestaurantInfoHolder(View itemView){
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.c);
            restuarantName = (TextView) itemView.findViewById(R.id.object_name);
            restaurantDetails = (TextView) itemView.findViewById(R.id.object_details);
        }
    }
    ArrayList<RestaurantInfo> restaurantInfos;
    RVAdapter(ArrayList<RestaurantInfo> restaurantInfos){
        this.restaurantInfos = restaurantInfos;
    }

    @Override
    public RestaurantInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main, parent, false);
        RestaurantInfoHolder rih = new RestaurantInfoHolder(v);
        return rih;
    }

    @Override
    public void onBindViewHolder(RestaurantInfoHolder holder, int position) {
        if(holder != null) {
            System.out.println("position" + position+ restaurantInfos.get(position).restaurantName + "1");
            if(holder.restuarantName == null){
                System.out.println("problem");
            }
           // holder.restuarantName.setText(restaurantInfos.get(position).restaurantName);
           // holder.restaurantDetails.setText(restaurantInfos.get(position).restaurantDetails);
            //holder.restaurantPhoto.setImageResource(restaurantInfos.get(position).imageId);
        }else{
            System.out.println("maybe");
        }
    }

    @Override
    public int getItemCount(){return restaurantInfos.size();}

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
