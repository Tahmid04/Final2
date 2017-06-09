package bar.final2.Models;

import java.util.ArrayList;

/**
 * Created by ASUS on 6/6/2017.
 */

public class RestaurantInfo {
    public String restaurantName;
    public String restaurantDetails;
    int imageId;

    public RestaurantInfo(String restaurantName, String restaurantDetails, int imageId){
        this.restaurantName = restaurantName;
        this.restaurantDetails = restaurantDetails;
        this.imageId = imageId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantDetails() {
        return restaurantDetails;
    }

    public void setRestaurantDetails(String restaurantDetails) {
        this.restaurantDetails = restaurantDetails;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
