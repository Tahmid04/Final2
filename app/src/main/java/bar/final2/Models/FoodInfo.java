package bar.final2.Models;

/**
 * Created by mushfiq on 6/9/17.
 */

public class FoodInfo {
    public String FoodName;
    public String Restaurant;
    public String Price;

    public FoodInfo(String F,String R,String P){
        FoodName=F;
        Restaurant=R;
        Price=P;
    }

    public boolean equals(FoodInfo another) {
        return (this.FoodName.equals(another.FoodName) &&
                this.Restaurant.equals(another.Restaurant) &&
                this.Price.equals(another.Price));
    }

    public int hashCode(){
        int hash = FoodName.hashCode()*11^Restaurant.hashCode()*11*11^Price.hashCode()*11*11*11;
        return hash;
    }

    public String toString(){
        return FoodName+" "+Restaurant+" "+Price;
    }
}
