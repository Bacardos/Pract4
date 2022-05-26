package com.example.praktika4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    TextView nameText;
    TextView phoneText;
    Button SetPath, CallTaxi;

    boolean key = false;

    TextView textInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();
        getInfoActiv();

        CallTaxi.setAlpha(0.3f);
        CallTaxi.setEnabled(false);

        TestButton(SetPath);
        TestButton(CallTaxi);


    }

    private void init(){
        nameText = findViewById(R.id.nameText);
        phoneText = findViewById(R.id.phoneText);
        SetPath = findViewById(R.id.set_path);
        CallTaxi = findViewById(R.id.call_taxi);
        textInfo = findViewById(R.id.textInfo);
    }


    private void getInfoActiv(){
        Bundle argument = getIntent().getExtras();

        String n = argument.get("Name").toString();
        String s = argument.get("Surname").toString();
        String x = n+" "+s;

        this.nameText.setText(x);
        this.phoneText.setText(argument.get("phone").toString());

    }

    private void TestButton(Button button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.set_path){
                    key = false;

                    Intent i2 = new Intent(getApplication(),ThirdActivity.class);
                    startActivityForResult(i2,0);
                    overridePendingTransition(R.anim.right_in,R.anim.alpha);

                }
                else if(view.getId() == R.id.call_taxi){
                    if(key){
                        Toast.makeText(getApplication(),"Wait for Taxi. Good Luck",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView textInfo = findViewById(R.id.textInfo);

        if (resultCode==0){


            String streeA = data.getStringExtra("StreetA");
            String houseA = data.getStringExtra("HouseA");
            String flatA = data.getStringExtra("FlatA");

            String streeB = data.getStringExtra("StreetB");
            String houseB = data.getStringExtra("HouseB");
            String flatB = data.getStringExtra("FlatB");



            String k = data.getStringExtra("key");

            int random_time = 5 + (int)(Math.random()*60);

            textInfo.setText("Taxi will arrive at " + streeA+ ", " + houseA+ ", "+ flatA+" in "+ random_time +" minutes and take you in "+streeB+", " + houseB+ ", "+ flatB+". If you are agree click Call Taxi");


            if(k.equals("true")){
               findViewById(R.id.call_taxi).setAlpha(1f);
                findViewById(R.id.call_taxi).setEnabled(true);

               key = true;
            }

        }


    }
}
