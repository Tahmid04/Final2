package bar.final2;


public class OrderInfo {
    String FoodName;
    String Restaurant;
    String Price;
    int Quantity;

    public OrderInfo(String F,String R,String P){
        FoodName=F;
        Restaurant=R;
        Price=P;
        Quantity=1;
    }

    public boolean equals(OrderInfo another) {
        return (this.FoodName.equals(another.FoodName) &&
                this.Restaurant.equals(another.Restaurant) &&
                this.Price.equals(another.Price) &&
                this.Quantity==another.Quantity);
    }

    public int hashCode(){
        int hash = FoodName.hashCode()*11+Restaurant.hashCode()*11*11+Price.hashCode()*11*11*11;
        return hash;
    }
}
