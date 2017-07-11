package bar.final2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String RestaurantName;
    static ArrayList<FoodInfo>myOrders=new ArrayList<>();
    static ArrayList<FoodInfo>FoodBase=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final EditText editText = (EditText) findViewById(R.id.myEditText);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(LocationPage.City+" , "+LocationPage.Location);
        editText.setText(LocationPage.City+" , "+LocationPage.Location);
        //hard-code

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




        //Database Retrieve Code Begin
        FoodBase.clear();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("FoodInfo");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long orderInfo= dataSnapshot.getChildrenCount();
                Iterable<DataSnapshot>Row=dataSnapshot.getChildren();
                for(DataSnapshot ds:Row){
                    Iterable<DataSnapshot>Attributes=ds.getChildren();
                    String FoodName="";
                    String Restaurant="";
                    String Price="";
                    int COUNT=0;

                    for(DataSnapshot attr: Attributes){
                        COUNT++;
                        if(COUNT==1) FoodName=attr.getValue(String.class);
                        else if(COUNT==2) Price=attr.getValue(String.class);
                        else Restaurant=attr.getValue(String.class);
                    }
                    FoodBase.add(new FoodInfo(FoodName,Restaurant,Price));
                    System.out.println("from DB: "+FoodName+" "+Restaurant+" "+Price);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        //Database Retrieve Code End




        for(RestaurantInfo RI: LocationPage.RestaurantBase){
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.card_view, null);
            TextView textView = (TextView) view.findViewById(R.id.object_name);
            textView.setText(RI.restaurantName);
            TextView textView1 = (TextView) view.findViewById(R.id.object_details);
            textView1.setText(RI.restaurantDetails);
            ImageView imageView = (ImageView) view.findViewById(R.id.object_photo);
            imageView.setImageResource(R.drawable.pfk);
            LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.content_main);
            relativeLayout.addView(view);

            view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    TextView now=(TextView) v.findViewById(R.id.object_name);
                    System.out.println(now.getText());
                    RestaurantName=now.getText().toString();
                    Intent intent = new Intent(MainActivity.this, RestaurantDetails.class);
                    startActivity(intent);
                }
            });
        }

        toolbar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction()!=KeyEvent.ACTION_DOWN) return false;

                TextView now=(TextView) findViewById(R.id.myEditText);
                System.out.println(now.getText());
                RestaurantName=now.getText().toString();
                Intent intent = new Intent(MainActivity.this, RestaurantDetails.class);
                startActivity(intent);
                return true;
            }
        });

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            System.out.println("Hello");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_orders) {
            Intent intent = new Intent(MainActivity.this, MyOrderPage.class);
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
