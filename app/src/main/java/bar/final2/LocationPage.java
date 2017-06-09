package bar.final2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LocationPage extends AppCompatActivity{
    public static String City;
    public static String Location;
    public static ArrayList<RestaurantInfo> RestaurantBase=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Location");//hard-code

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //Database Retrieve Code Begin
        RestaurantBase.clear();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("RestaurantInfo");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long orderInfo= dataSnapshot.getChildrenCount();
                Iterable<DataSnapshot>Row=dataSnapshot.getChildren();
                for(DataSnapshot ds:Row){
                    Iterable<DataSnapshot>Attributes=ds.getChildren();
                    String Details="";
                    String RestaurantName="";
                    String Price="";
                    int COUNT=0;

                    for(DataSnapshot attr: Attributes){
                        COUNT++;
                        if(COUNT==1) Details=attr.getValue(String.class);
                        else if(COUNT==2) RestaurantName=attr.getValue(String.class);
                    }
                    RestaurantBase.add(new RestaurantInfo(RestaurantName,Details,0));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        //Database Retrieve Code End

        RadioButton Option1=(RadioButton) findViewById(R.id.Dhaka);
        Option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView now= (TextView) findViewById(R.id.Location);
                now.setVisibility(View.VISIBLE);

                City="Dhaka";
                RadioButton rb1=(RadioButton) findViewById(R.id.Option1);
                rb1.setText("Banani");
                RadioButton rb2=(RadioButton) findViewById(R.id.Option2);
                rb2.setText("ShahBagh");
                RadioButton rb3=(RadioButton) findViewById(R.id.Option3);
                rb3.setText("Uttara");

                rb1.setVisibility(View.VISIBLE);
                rb2.setVisibility(View.VISIBLE);
                rb3.setVisibility(View.VISIBLE);
            }
        });

        RadioButton Option2=(RadioButton) findViewById(R.id.Chittagong);
        Option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView now= (TextView) findViewById(R.id.Location);
                now.setVisibility(View.VISIBLE);

                City="Chittagong";
                RadioButton rb1=(RadioButton) findViewById(R.id.Option1);
                rb1.setText("Kotwali");
                RadioButton rb2=(RadioButton) findViewById(R.id.Option2);
                rb2.setText("ChawkBajar");
                RadioButton rb3=(RadioButton) findViewById(R.id.Option3);
                rb3.setText("GEC");

                rb1.setVisibility(View.VISIBLE);
                rb2.setVisibility(View.VISIBLE);
                rb3.setVisibility(View.VISIBLE);
            }
        });

        RadioButton Option3=(RadioButton) findViewById(R.id.Khulna);
        Option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView now= (TextView) findViewById(R.id.Location);
                now.setVisibility(View.VISIBLE);

                City="Khulna";
                RadioButton rb1=(RadioButton) findViewById(R.id.Option1);
                rb1.setText("Khalishpur");
                RadioButton rb2=(RadioButton) findViewById(R.id.Option2);
                rb2.setText("Khan Jahan Ali Road");
                RadioButton rb3=(RadioButton) findViewById(R.id.Option3);
                rb3.setText("KDA Avenue");

                rb1.setVisibility(View.VISIBLE);
                rb2.setVisibility(View.VISIBLE);
                rb3.setVisibility(View.VISIBLE);
            }
        });


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button now=(Button) findViewById(R.id.ConfirmLocation);
                now.setVisibility(View.VISIBLE);
                Location=((RadioButton)view).getText().toString();
            }
        };

        RadioButton rb1=(RadioButton) findViewById(R.id.Option1);
        rb1.setOnClickListener(listener);
        RadioButton rb2=(RadioButton) findViewById(R.id.Option2);
        rb2.setOnClickListener(listener);
        RadioButton rb3=(RadioButton) findViewById(R.id.Option3);
        rb3.setOnClickListener(listener);

        Button now=(Button) findViewById(R.id.ConfirmLocation);
        now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
