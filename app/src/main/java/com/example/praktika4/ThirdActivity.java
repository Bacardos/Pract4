package com.example.praktika4;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText StreetA,HouseA,FlatA;
    EditText StreetB,HouseB,FlatB;
    Button ok;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Log.d(TAG, "Загрзка меню");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trird_activity);

        init();

        MyButton button = new MyButton(this,R.id.Ok);


    }


    private void init(){
        StreetA = findViewById(R.id.streetA);
        HouseA = findViewById(R.id.houseA);
        FlatA = findViewById(R.id.flatA);

        StreetB = findViewById(R.id.streetB);
        HouseB = findViewById(R.id.houseB);
        FlatB = findViewById(R.id.flatB);

        ok = findViewById(R.id.Ok);
    }


    private class MyButton implements View.OnClickListener{
        Button button;

        MyButton(Activity activity, int id){
            button = findViewById(id);
            button.setOnClickListener(this);
        }

        private Boolean ChechInput(){

            if(StreetA.length() != 0 && HouseA.length() != 0 && StreetB.length() != 0 && HouseB.length() != 0){
                return true; // строка заполнина
            }

            return false; // строка не заполнина
        }

        @Override
        public void onClick(View view) {
            if(ChechInput()){
                Intent i = new Intent();
                i.putExtra("StreetA",StreetA.getText().toString());
                i.putExtra("HouseA", HouseA.getText().toString());
                i.putExtra("FlatA", FlatA.getText().toString());

                i.putExtra("StreetB",StreetB.getText().toString());
                i.putExtra("HouseB", HouseB.getText().toString());
                i.putExtra("FlatB", FlatB.getText().toString());

                i.putExtra("key", "true");

                setResult(0,i);

                finish();
                overridePendingTransition(R.anim.right_in,R.anim.alpha);


            }
            else {
                Toast.makeText(getApplication(),"Вы ввели не все данные", Toast.LENGTH_LONG).show();
            }
        }
    }

}
