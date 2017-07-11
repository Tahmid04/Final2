package bar.final2.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import bar.final2.Models.OrderInfo;
import bar.final2.R;

public class ReviewPageActivity  extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Orders");//hard-code

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


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("OrderInfo");

        //System.out.println("show me size"+PrevOrders.size());
        int cnt=0;
        for(final OrderInfo now: RecommendationPageActivity.PrevOrders){
            cnt++;
            final int INDX=cnt;

            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.card_view4, null);

            TextView textView1 = (TextView) view.findViewById(R.id.FoodName);
            textView1.setText(now.FoodName);
            textView1.setTextColor(Color.BLACK);

            TextView textView2 = (TextView) view.findViewById(R.id.RestaurantName);
            textView2.setText(now.Restaurant);
            textView2.setTextColor(Color.BLACK);

            TextView textView3 = (TextView) view.findViewById(R.id.Date);
            textView3.setText(now.Date);
            textView3.setTextColor(Color.BLACK);

            LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.content_main);
            relativeLayout.addView(view);

            Button btn=(Button) view.findViewById(R.id.AddReview);
            final Context context=this;

            btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);

                    alert.setTitle("Review");
                    //alert.setMessage("Message");

                    final EditText input = new EditText(getApplicationContext());
                    alert.setView(input);

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String reviewText= input.getText().toString();

                            System.out.println(reviewText);
                            String DBkey="Item"+String.format("%03d",INDX);

                            String key=ref.child(DBkey).getKey();
                            OrderInfo ord=new OrderInfo(now.FoodName,now.Restaurant,reviewText);
                            Map<String, Object> Values = ord.toMap();
                            Map<String, Object> childUpdates = new HashMap<>();
                            childUpdates.put(key,Values);

                            ref.updateChildren(childUpdates);
                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Canceled.
                        }
                    });
                    System.out.println("done");

                    AlertDialog ALERT = alert.create();
                    ALERT.show();
                }
            });

        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(ReviewPageActivity.this, RecommendationPageActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_orders) {

        }  else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
