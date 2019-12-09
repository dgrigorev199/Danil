package com.example.danyaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {
    public TextView title2;
    public ImageView img2;
    public TextView obiem;
    public TextView fio;
    public TextView compania;
    public TextView telephone;
    public TextView mail;
    public Button zayavka;
    public boolean nazatie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Оформление заказа");
        title2 = findViewById(R.id.title2);
        img2 = findViewById(R.id.imageView2);
        title2.setText(getIntent().getStringExtra("title"));
        img2.setImageResource(getIntent().getIntExtra("img",0));
        obiem = findViewById(R.id.obiem);
        fio = findViewById(R.id.fio);
        compania = findViewById(R.id.compania);
        telephone = findViewById(R.id.telefon);
        mail = findViewById(R.id.mail);
        zayavka = findViewById(R.id.zayavka);
        nazatie = true;
        zayavka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nazatie) {
                    Server server = new Server();
                    server.execute(fio.getText().toString(), obiem.getText().toString(), compania.getText().toString(),
                            telephone.getText().toString(), mail.getText().toString(), title2.getText().toString());
                    nazatie = false;
                }
            }
        });
        if(!title2.getText().toString().equals("Пшеница") && !title2.getText().toString().equals("Ячмень") &&
        !title2.getText().toString().equals("Кукуруза") && !title2.getText().toString().equals("Горох")
        && !title2.getText().toString().equals("Подсолнечник")) {
            obiem.setText("1");
            obiem.setVisibility(View.GONE);
        }
    }
    class Server extends AsyncTask<String, Void, String> {
        HttpURLConnection conn;
        Integer res;
        @Override
        protected String doInBackground(String... strings) {
            String server_name = "https://kfxtruzhenik.000webhostapp.com";
            String ansver = "";
            String post_url = server_name+"/zakaz.php?fio="+strings[0]+"&obiem="+strings[1]+"&compania="+strings[2]+"&telephone="+strings[3]+"&mail="+strings[4]+"&cultura="+strings[5];
            URL url = null;
            try {
                url = new URL(post_url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection conn;
            Integer res;
            try {
                //Получаем ответ от сервера
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(10000); // ждем 10сек
                conn.setRequestMethod("GET");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0");
                conn.connect();
                res = conn.getResponseCode();
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
                ansver = reader.readLine();
                Log.d("chat1", "+ ChatActivity - ответ сервера (200 - все ОК): " + res.toString());
                conn.disconnect();
            } catch (IOException e) {
                ansver = null;
                e.printStackTrace();
            }

            return ansver;
        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if(result != null){
                Toast.makeText(getApplicationContext(),"Успешно!",Toast.LENGTH_LONG).show();
                finish();
                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("data.txt",MODE_APPEND)));
                    String text = "ФИО: "+fio.getText().toString()+"##Объём: "+obiem.getText().toString()+" тонн##Компания: "+compania.getText().toString()+"##Телефон: "+
                            telephone.getText().toString()+"##Мейл: "+mail.getText().toString()+"!!!"+title2.getText().toString()+"\n";
                    if(obiem.getVisibility()!=View.VISIBLE)
                        text = "ФИО: "+fio.getText().toString()+"##Компания: "+compania.getText().toString()+"##Телефон: "+
                                telephone.getText().toString()+"##Мейл: "+mail.getText().toString()+"!!!"+title2.getText().toString()+"\n";
                    writer.write(text);
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(getApplicationContext(),"Произошла ошибка!",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
