package bar.final2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MyOrderPage extends AppCompatActivity
        implements OnNavigationItemSelectedListener {
    static int UnitValue=1;
    static int TotalSum=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_page);

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

        TotalSum=0;
        for(OrderInfo now: MainActivity.myOrders){
            System.out.println(now.Restaurant+" "+now.FoodName+" "+now.Price);
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.card_view2, null);
            TextView textView1 = (TextView) view.findViewById(R.id.FoodName);
            textView1.setText(now.FoodName);
            textView1.setTextColor(Color.BLACK);

            TextView textView2 = (TextView) view.findViewById(R.id.RestaurantName);
            textView2.setText(now.Restaurant);
            textView2.setTextColor(Color.BLACK);


            TextView textView3 = (TextView) view.findViewById(R.id.PriceBar);
            textView3.setText(now.Price);
            textView3.setTextColor(Color.rgb(255,69,0));

            TotalSum+=Integer.parseInt(now.Price.substring(4));

            SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar);
            seekBar.setProgress(0);
            seekBar.setMax(9);


            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    View view=(ViewGroup) seekBar.getParent();
                    TextView seekBarValue=(TextView) view.findViewById(R.id.seekBarValue);
                    seekBarValue.setText(String.valueOf(progress+1)+"/10");

                    TextView PriceBar=(TextView) view.findViewById(R.id.PriceBar);
                    PriceBar.setText("BDT "+UnitValue*(progress+1));


                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    View view = (ViewGroup) seekBar.getParent();
                    TextView PriceBar = (TextView) view.findViewById(R.id.PriceBar);
                    UnitValue=Integer.parseInt(PriceBar.getText().toString().substring(4))/(seekBar.getProgress()+1);
                    TotalSum=TotalSum-Integer.parseInt(PriceBar.getText().toString().substring(4));
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    View view = (ViewGroup) seekBar.getParent();
                    TextView PriceBar = (TextView) view.findViewById(R.id.PriceBar);
                    TotalSum+=Integer.parseInt(PriceBar.getText().toString().substring(4));
                    TextView totalCost=(TextView) findViewById(R.id.totalCost);
                    totalCost.setText("Total Bill : BDT "+Integer.toString(TotalSum));
                }

            });

            Button crossButton=(Button) view.findViewById(R.id.crossButton);

            crossButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    View PARENT=(ViewGroup) v.getParent();

                    String F=((TextView) PARENT.findViewById(R.id.FoodName)).getText().toString();
                    String Res=((TextView) PARENT.findViewById(R.id.RestaurantName)).getText().toString();
                    String Price=((TextView) PARENT.findViewById(R.id.PriceBar)).getText().toString();
                    SeekBar seekBar=(SeekBar) PARENT.findViewById(R.id.seekBar);


                    Price="BDT "+(Integer.parseInt(Price.substring(4))/(seekBar.getProgress()+1));

                    ((ViewManager)PARENT.getParent()).removeView(PARENT);

                    OrderInfo now=new OrderInfo(F,Res,Price);
                    OrderInfo toDelete = null;
                    for(OrderInfo k: MainActivity.myOrders){
                        if(k.equals(now)) {
                            toDelete = k;
                            break;
                        }
                    }
                    if(toDelete != null){
                        MainActivity.myOrders.remove(toDelete);
                    }

                    TextView totalCost=(TextView) findViewById(R.id.totalCost);
                    TotalSum=TotalSum-(Integer.parseInt(Price.substring(4)));
                    totalCost.setText("Total Bill : BDT "+Integer.toString(TotalSum));
                }
            });

            LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.content_main);
            relativeLayout.addView(view);


            Button ConfirmButton=(Button) findViewById(R.id.confirmButton);
            ConfirmButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(MyOrderPage.this, CheckOutPage.class);
                    startActivity(intent);
                }
            });
        }
        TextView totalCost=(TextView) findViewById(R.id.totalCost);
        totalCost.setText("Total Bill : BDT "+Integer.toString(TotalSum));
        totalCost.setTextColor(Color.rgb(255,69,0));
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(MyOrderPage.this, MainActivity.class);
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
