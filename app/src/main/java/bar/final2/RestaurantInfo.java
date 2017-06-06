package bar.final2;

import java.util.ArrayList;

/**
 * Created by ASUS on 6/6/2017.
 */

public class RestaurantInfo {
    String restaurantName;
    String restaurantDetails;
    int imageId;

    public RestaurantInfo(String restaurantName, String restaurantDetails, int imageId){
        this.restaurantName = restaurantName;
        this.restaurantDetails = restaurantDetails;
        this.imageId = imageId;
    }

    public static ArrayList<RestaurantInfo> initialize(){
        ArrayList<RestaurantInfo> restaurantInfos = new ArrayList<>();
        restaurantInfos.add(new RestaurantInfo("Pizza Hut", "60 minutes", R.drawable.ic_menu_gallery));
        restaurantInfos.add(new RestaurantInfo("Pizza Guy", "120 minutes", R.drawable.ic_menu_send));
        return restaurantInfos;
    }
}
