package com.example.danyaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {
    public ListView listView;
    public ListAdapter listAdapter;
    public ArrayList<Slot2> arrayList1;
    public String history;
    public void load_history(){
        try {
            history = "";
            arrayList1.clear();
            listView.setAdapter(listAdapter);
            BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("data.txt")));
            String rd = "";
            int i = 0;
            while ((rd = reader.readLine())!=null){
                history +=rd+"\n";
                String[] array = rd.split("!!!");
                int image = 0;
                if(array[1].equals("Ячмень")){
                    image = R.drawable.yachmen;
                }
                if(array[1].equals("Пшеница")){
                    image = R.drawable.psheno;
                }
                if(array[1].equals("Кукуруза")){
                    image = R.drawable.cucuruza;
                }
                if(array[1].equals("Горох")){
                    image = R.drawable.goroh;
                }
                if(array[1].equals("Подсолнечник")){
                    image = R.drawable.semki;
                }
                if(array[1].equals("МТЗ-1221")){
                    image = R.drawable.mtz1221;
                }
                if(array[1].equals("K-424")){
                    image = R.drawable.k424;
                }
                if(array[1].equals("K-701")){
                    image = R.drawable.k701;
                }
                if(array[1].equals("Полесье GS-12")){
                    image = R.drawable.gs12;
                }
                if(array[1].equals("Acros 580")){
                    image = R.drawable.acros580;
                }
                if(array[1].equals("Vector 410")){
                    image = R.drawable.vector;
                }
                if(array[1].equals("ПСКУ-4")){
                    image = R.drawable.psku4;
                }
                if(array[1].equals("ПСКУ-5")){
                    image = R.drawable.psku5;
                }
                if(array[1].equals("СЗ 5,4 А")){
                    image = R.drawable.sz54;
                }
                if(array[1].equals("СЗП 3,6 А")){
                    image = R.drawable.szp36;
                }
                if(array[1].equals("СПП-6")){
                    image = R.drawable.spp6;
                }
                if(array[1].equals("АК-8.5")){
                    image = R.drawable.ak85;
                }
                if(array[1].equals("АГС-16-1з")){
                    image = R.drawable.ags;
                }
                if(array[1].equals("ANTARES 3X4")){
                    image = R.drawable.antares;
                }
                if(array[1].equals("АГЛ")){
                    image = R.drawable.rubin;
                }
                Slot2 slot2 = new Slot2(array[1], array[0].replace("##","\n"), image);
                arrayList1.add(slot2);
                listView.setAdapter(listAdapter);
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(),"Корзина пуста!",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().setTitle("Корзина");
        listView = findViewById(R.id.listView2);
        arrayList1 = new ArrayList<Slot2>();
        history = "";
        listAdapter = new slot2Adapter(getApplicationContext(),R.layout.slot2_layout,arrayList1);
        load_history();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final String[] catNamesArray = {"Отменить заявку"};

                AlertDialog.Builder builder = new AlertDialog.Builder(Main3Activity.this);
                builder
                        .setItems(catNamesArray, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("delete","del");
                            int i = 0;
                            String new_history = "";
                            String[] array = history.split("\n");
                            for(String str:array){
                                if(position != i){
                                    new_history +=str+"\n";
                                }
                                i++;
                            }
                            history = new_history;
                                try {
                                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("data.txt",MODE_PRIVATE)));
                                    writer.write(history);
                                    writer.close();
                                    load_history();
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
                builder.create();
                builder.show();
                return false;

            }
        });
    }
}
