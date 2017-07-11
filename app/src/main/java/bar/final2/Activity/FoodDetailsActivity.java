package bar.final2.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import bar.final2.Models.FoodInfo;
import bar.final2.R;

public class FoodDetailsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        System.out.println("Created");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(RecommendationPageActivity.RestaurantName);//hard-code

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        String receiveString1;
        String receiveString2;
        String receiveString3;
        for(FoodInfo f: RecommendationPageActivity.FoodBase){
            receiveString1=f.FoodName;
            receiveString2=f.Restaurant;
            receiveString3=f.Price;


            if(!receiveString2.contains(RecommendationPageActivity.RestaurantName)
                    && !receiveString1.contains(RecommendationPageActivity.RestaurantName)) continue;

            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.card_view1, null);
            TextView textView = (TextView) view.findViewById(R.id.object_name);
            textView.setText(receiveString1);
            TextView textView1 = (TextView) view.findViewById(R.id.object_details);
            textView1.setText(receiveString3);
            ImageView imageView = (ImageView) view.findViewById(R.id.object_photo);
            imageView.setImageResource(R.drawable.pizza);
            Button button = (Button) view.findViewById(R.id.add_to_cart);
            button.setText("Add To Cart");
            button.setTextColor(Color.WHITE);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    View PARENT=(ViewGroup) v.getParent();
                    TextView now1=(TextView) PARENT.findViewById(R.id.object_name);
                    TextView now2=(TextView) PARENT.findViewById(R.id.object_details);

                    System.out.println(now1.getText());
                    System.out.println(now2.getText());

                    String ResName= RecommendationPageActivity.RestaurantName;
                    String FoodName=now1.getText().toString();
                    String Price=now2.getText().toString();

                    FoodInfo curr=new FoodInfo(FoodName,ResName,Price);
                    boolean flag=true;
                    for(FoodInfo k: RecommendationPageActivity.myOrders){
                        if(k.equals(curr)) flag=false;
                    }

                    if(flag){
                        RecommendationPageActivity.myOrders.add(curr);
                        TextView messageBox=(TextView) findViewById(R.id.AddedToCart);
                        messageBox.setTextColor(Color.rgb(0,120,0));
                        messageBox.setText("Successfully added to cart");
                    }
                    else{
                        TextView messageBox=(TextView) findViewById(R.id.AddedToCart);
                        messageBox.setTextColor(Color.rgb(255,0,0));
                        messageBox.setText("Item already exists in cart");
                    }
                }
            });

            LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.content_main);
            relativeLayout.addView(view);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Handle the camera action

            Intent intent = new Intent(FoodDetailsActivity.this, RecommendationPageActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_orders) {
            Intent intent = new Intent(FoodDetailsActivity.this, MyOrderPageActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
