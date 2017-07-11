package bar.final2.Models;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class OrderInfo {
    public String FoodName;
    public String Restaurant;
    public String Review;
    public String Date;

    public OrderInfo(String F,String R,String P){
        FoodName=F;
        Restaurant=R;
        Review=P;

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        Date=df.format(c.getTime());
    }

    public boolean equals(OrderInfo another) {
        return (this.FoodName.equals(another.FoodName) &&
                this.Restaurant.equals(another.Restaurant) &&
                this.Review.equals(another.Review) &&
                this.Date.equals(another.Date)
        );
    }


    public int hashCode(){
        int hash = FoodName.hashCode()*11^Restaurant.hashCode()*11*11^Review.hashCode()*11*11*11^Date.hashCode()*17*19;
        return hash;
    }

    public String toString(){
        return FoodName+" "+Restaurant+" "+Review+" "+Date;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("FoodName", FoodName);
        map.put("Restaurant", Restaurant);
        map.put("Review", Review);
        map.put("Date", Date);
        return map;
    }
}
