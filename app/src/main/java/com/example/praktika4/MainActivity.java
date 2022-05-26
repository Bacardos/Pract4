package com.example.praktika4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AuthenticationRequiredException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.praktika4.DATA.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity  {

    EditText phone;
    EditText Name;
    EditText Surname;
    Button regist, reg;
    TextView tv;

    String ph,N,Sur;

    SharedPreferences sPref;

    LinearLayout root;
    //BD
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


        loadText();

        MyButton button = new MyButton(this,R.id.regist_button);






    }





    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStart() {
        super.onStart();
        memory();
        LOG_IN();

    }

    private void LOG_IN(){
        if(ChechInput()){
            if(ph.equals(phone.getText().toString()) && N.equals(Name.getText().toString()) && Sur.equals(Surname.getText().toString())){
                regist.setText("LOG IN");
            }
            else {
                regist.setText("REGISTATION");
            }

        }

    }

    private void memory(){
        ph = phone.getText().toString();
        N = Name.getText().toString();
        Sur = Surname.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    private void init(){
        phone = findViewById(R.id.phone);
        Name = findViewById(R.id.name);
        Surname = findViewById(R.id.surname);
        regist = findViewById(R.id.regist_button);

        root = findViewById(R.id.root);
    }

    private Boolean ChechInput(){



        if(phone.length() != 0 && Name.length() != 0 && Surname.length() != 0 ){
            return true; // строка заполнина
        }

        return false; // строка не заполнина
    }



    private class MyButton implements View.OnClickListener{
        Button button;

        MyButton(Activity activity, int id){
            button = findViewById(id);
            button.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            if(ChechInput()){

                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                i.putExtra("Name",Name.getText().toString());
                i.putExtra("Surname", Surname.getText().toString());
                i.putExtra("phone", phone.getText().toString());

                i.putExtra("key",true);

                startActivity(i);
                overridePendingTransition(R.anim.right_in,R.anim.alpha);

            }
            else {
                Toast.makeText(getApplication(),"Вы ввели не все данные", Toast.LENGTH_LONG).show();
            }
        }
    }

    //SharedPreferences

    private void saveText(){
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();

        editor.putString("phone",phone.getText().toString());
        editor.putString("Name",Name.getText().toString());
        editor.putString("Surname",Surname.getText().toString());
        editor.commit();
        Toast.makeText(getApplication(),"Text saved", Toast.LENGTH_LONG);


    }

    private void loadText(){

        sPref = getPreferences(MODE_PRIVATE);

        phone.setText(sPref.getString("phone",""));
        Name.setText(sPref.getString("Name",""));
        Surname.setText(sPref.getString("Surname",""));

        Toast.makeText(getApplication(),"Text loaded", Toast.LENGTH_LONG);

    }


    class DownloadImage extends AsyncTask<String,Void, Bitmap>{

        ImageView bmImage;

        DownloadImage(ImageView bmImage){
            this.bmImage = bmImage;
        }



        @Override
        protected Bitmap doInBackground(String... strings) {

            String urldisplay = strings[0];
            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            }
            catch (Exception e){
                Log.e("Error", e.getMessage()); e.printStackTrace();
                e.printStackTrace();
            }

            return mIcon;

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Toast.makeText(getApplication(), "!!", Toast.LENGTH_LONG).show();
            bmImage.setImageBitmap(bitmap);
        }
    }

}