package bar.final2.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import bar.final2.Models.FoodInfo;
import bar.final2.Models.OrderInfo;
import bar.final2.R;

public class CheckOutPageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean OptionChosen=false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Check Out");//hard-code

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OptionChosen=true;
            }
        };

        RadioButton Option1=(RadioButton) findViewById(R.id.Option1);
        Option1.setOnClickListener(listener);
        RadioButton Option2=(RadioButton) findViewById(R.id.Option2);
        Option2.setOnClickListener(listener);
        RadioButton Option3=(RadioButton) findViewById(R.id.Option3);
        Option3.setOnClickListener(listener);

        TextView address=(TextView) findViewById(R.id.inputAddress);
        CheckBox checkBox=(CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                TextView address=(TextView) findViewById(R.id.inputAddress);
                if(isChecked) address.setText("ECE building, BUET, West Palashi, Dhaka");
                else address.setText("");
            }
        });

        Button PlaceOrder=(Button) findViewById(R.id.PlaceOrder);
        PlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView address=(TextView) findViewById(R.id.inputAddress);
                TextView AlertMessage=(TextView) findViewById(R.id.AlertMessage);
                AlertMessage.setTextColor(Color.rgb(255,0,0));
                if(!OptionChosen) AlertMessage.setText("Please choose a payment method");
                else if(address.getText().length()==0){
                    AlertMessage.setText("    Please enter delivery address");
                }
                else {
                    AlertMessage.setTextColor(Color.rgb(0,120,0));
                    AlertMessage.setText("   Your Order has been placed\n                  Thank You");


                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("OrderInfo");

                    int DataBaseSize=0;
                    Map<String, OrderInfo> myOrderMap = new HashMap<String, OrderInfo>();

                    for(FoodInfo ord: RecommendationPageActivity.myOrders){
                        myOrderMap.put("Item"+String.format("%03d",++DataBaseSize),
                                new OrderInfo(ord.FoodName,ord.Restaurant,""));
                    }
                    ref.setValue(myOrderMap);
                    RecommendationPageActivity.myOrders.clear();

                }
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(CheckOutPageActivity.this, RecommendationPageActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_orders) {
            Intent intent = new Intent(CheckOutPageActivity.this, MyOrderPageActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
