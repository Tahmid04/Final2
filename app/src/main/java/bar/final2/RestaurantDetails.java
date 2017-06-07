package bar.final2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RestaurantDetails extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        System.out.println("Created");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(MainActivity.RestaurantName);//hard-code

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        try {
            InputStream inputStream = getResources().openRawResource(
                    getResources().getIdentifier("khabar",
                            "raw", getPackageName()));
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString1 = "";
                String receiveString2 = "";
                String receiveString3 = "";

                while ((receiveString1 = bufferedReader.readLine()) != null ) {
                    receiveString2=bufferedReader.readLine();
                    receiveString3=bufferedReader.readLine();
                    if(receiveString1.compareTo(MainActivity.RestaurantName)!=0) continue;

                    LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    View view = layoutInflater.inflate(R.layout.card_view1, null);
                    TextView textView = (TextView) view.findViewById(R.id.object_name);
                    textView.setText(receiveString2);
                    TextView textView1 = (TextView) view.findViewById(R.id.object_details);
                    textView1.setText(receiveString3);
                    ImageView imageView = (ImageView) view.findViewById(R.id.object_photo);
                    imageView.setImageResource(R.drawable.ic_menu_share);
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

                            String ResName=MainActivity.RestaurantName;
                            String FoodName=now1.getText().toString();
                            String Price=now2.getText().toString();
                            MainActivity.myOrders.add(new OrderInfo(ResName,FoodName,Price));
                        }
                    });

                    LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.content_main);
                    relativeLayout.addView(view);



                    System.out.println(receiveString2);
                }
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Handle the camera action

            Intent intent = new Intent(RestaurantDetails.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_orders) {
            for(OrderInfo now: MainActivity.myOrders){
                System.out.println(now.Restaurant+" "+now.FoodName+" "+now.Price);
            }
        }  else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
