package com.example.danyaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ListAdapter listAdapter;
    public ListAdapter listAdapter2;
    public GridView gridView;
    public ArrayList<Slot1> arrayList1;
    public ArrayList<Slot2> arrayList2;
    public ListView listView;
    public LinearLayout mainpage;
    public LinearLayout info;
    public void tech(){
        listView.setVisibility(View.VISIBLE);
        mainpage.setVisibility(View.INVISIBLE);
        info.setVisibility(View.INVISIBLE);
        arrayList1.clear();
        Slot1 slot1 = new Slot1("Тракторы",R.drawable.tract);
        arrayList1.add(slot1);
        Slot1 slot2 = new Slot1("Комбайны",R.drawable.comb);
        arrayList1.add(slot2);
        Slot1 slot3 = new Slot1("Плуги",R.drawable.plug);
        arrayList1.add(slot3);
        Slot1 slot4 = new Slot1("Сеялки",R.drawable.sey);
        arrayList1.add(slot4);
        Slot1 slot8 = new Slot1("Сеялки точного высева",R.drawable.stp);
        arrayList1.add(slot8);
        Slot1 slot5 = new Slot1("Культиваторы",R.drawable.cult);
        arrayList1.add(slot5);
        Slot1 slot6 = new Slot1("Бороны",R.drawable.boron);
        arrayList1.add(slot6);
        Slot1 slot7 = new Slot1("Дискаторы",R.drawable.diskators);
        arrayList1.add(slot7);
        listView.setAdapter(listAdapter);
    }
    public void zerno(){
        listView.setVisibility(View.VISIBLE);
        info.setVisibility(View.INVISIBLE);
        mainpage.setVisibility(View.INVISIBLE);
        arrayList1.clear();
        Slot1 slot1 = new Slot1("Пшеница",R.drawable.psheno);
        arrayList1.add(slot1);
        Slot1 slot2 = new Slot1("Ячмень",R.drawable.yachmen);
        arrayList1.add(slot2);
        Slot1 slot3 = new Slot1("Кукуруза",R.drawable.cucuruza);
        arrayList1.add(slot3);
        Slot1 slot4 = new Slot1("Горох",R.drawable.goroh);
        arrayList1.add(slot4);
        Slot1 slot8 = new Slot1("Подсолнечник",R.drawable.semki);
        arrayList1.add(slot8);
        listView.setAdapter(listAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        gridView = findViewById(R.id.techica);
        gridView = findViewById(R.id.techica);
        arrayList1 = new ArrayList<Slot1>();
        arrayList2 = new ArrayList<Slot2>();
        mainpage = findViewById(R.id.mainpage);
        mainpage.setVisibility(View.VISIBLE);
        info = findViewById(R.id.informatia);
        listAdapter = new mainAdapter(getApplicationContext(),R.layout.main_layout,arrayList1);
        listAdapter2 = new slot2Adapter(getApplicationContext(),R.layout.slot2_layout,arrayList2);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_main:
                        getSupportActionBar().setTitle("Главная");
                        mainpage.setVisibility(View.VISIBLE);
                        info.setVisibility(View.INVISIBLE);
                        listView.setVisibility(View.INVISIBLE);
                        gridView.setVisibility(View.GONE);
                        break;
                    case R.id.action_recents:
                        getSupportActionBar().setTitle("Техника");
                        gridView.setVisibility(View.GONE);
                        tech();
                        break;
                    case R.id.action_favorites:
                        getSupportActionBar().setTitle("Продукция");
                        gridView.setVisibility(View.GONE);
                        zerno();
                        break;
                    case R.id.action_nearby:
                        getSupportActionBar().setTitle("О компании");
                        gridView.setVisibility(View.GONE);
                        mainpage.setVisibility(View.INVISIBLE);
                        info.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.INVISIBLE);
                        break;
                }
                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = getSupportActionBar().getTitle().toString();
                if(title.equals("Продукция")){
                    Slot1 slot1 = (Slot1)listView.getItemAtPosition(position);
                    Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                    intent.putExtra("title",slot1.title);
                    intent.putExtra("img",slot1.img);
                    startActivity(intent);
                }
                if(title.equals("Техника")){
                    Slot1 slot1 = (Slot1)listView.getItemAtPosition(position);
                    Slot2 slot21,slot22,slot23;
                    switch (slot1.title){
                        case "Тракторы":
                           slot21 = new Slot2("МТЗ-1221","Цена: 1200000 руб",R.drawable.mtz1221);
                            slot22 = new Slot2("K-424","Цена: 3700000 руб",R.drawable.k424);
                            slot23 = new Slot2("K-701","Цена: 450000 руб",R.drawable.k701);
                            arrayList2.clear();
                           arrayList2.add(slot21);
                           arrayList2.add(slot22);
                           arrayList2.add(slot23);
                           gridView.setAdapter(listAdapter2);
                           gridView.setVisibility(View.VISIBLE);
                           listView.setVisibility(View.INVISIBLE);
                            break;
                        case "Комбайны":
                            slot21 = new Slot2("Полесье GS-12","Цена: 5600000 руб",R.drawable.gs12);
                            slot22 = new Slot2("Acros 580","Цена: 3000000 руб",R.drawable.acros580);
                            slot23 = new Slot2("Vector 410","Цена: 2400000 руб",R.drawable.vector);
                            arrayList2.clear();
                            arrayList2.add(slot21);
                            arrayList2.add(slot22);
                            arrayList2.add(slot23);
                            gridView.setAdapter(listAdapter2);
                            gridView.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.INVISIBLE);
                            break;
                        case "Плуги":
                            slot21 = new Slot2("ПСКУ-4","Цена: 450000 руб",R.drawable.psku4);
                            slot22 = new Slot2("ПСКУ-5","Цена: 500000 руб",R.drawable.psku5);
                            arrayList2.clear();
                            arrayList2.add(slot21);
                            arrayList2.add(slot22);
                            gridView.setAdapter(listAdapter2);
                            gridView.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.INVISIBLE);
                            break;
                        case "Сеялки":
                            slot21 = new Slot2("СЗ 5,4 А","Цена: 670000 руб",R.drawable.sz54);
                            slot22 = new Slot2("СЗП 3,6 А","Цена: 450000 руб",R.drawable.szp36);
                            arrayList2.clear();
                            arrayList2.add(slot21);
                            arrayList2.add(slot22);
                            gridView.setAdapter(listAdapter2);
                            gridView.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.INVISIBLE);
                            break;
                        case "Сеялки точного высева":
                            slot21 = new Slot2("СПП-6","Цена: 760000 руб",R.drawable.spp6);
                            arrayList2.clear();
                            arrayList2.add(slot21);
                            gridView.setAdapter(listAdapter2);
                            gridView.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.INVISIBLE);
                            break;
                        case "Культиваторы":
                            slot21 = new Slot2("АК-8.5","Цена: 1700000 руб",R.drawable.ak85);
                            arrayList2.clear();
                            arrayList2.add(slot21);
                            gridView.setAdapter(listAdapter2);
                            gridView.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.INVISIBLE);
                            break;
                        case "Бороны":
                            slot21 = new Slot2("АГС-16-1з","Цена: 500000 руб",R.drawable.ags);
                            arrayList2.clear();
                            arrayList2.add(slot21);
                            gridView.setAdapter(listAdapter2);
                            gridView.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.INVISIBLE);
                            break;
                        case "Дискаторы":
                            slot21 = new Slot2("ANTARES 3Х4","Цена: 2200000 руб",R.drawable.antares);
                            slot22 = new Slot2("АГЛ","Цена: 2170000 руб",R.drawable.rubin);
                            arrayList2.clear();
                            arrayList2.add(slot21);
                            arrayList2.add(slot22);
                            gridView.setAdapter(listAdapter2);
                            gridView.setVisibility(View.VISIBLE);
                            listView.setVisibility(View.INVISIBLE);
                            break;
                    }
                }
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Slot2 slot = (Slot2)gridView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("title",slot.korztitle);
                intent.putExtra("img",slot.korzimageView);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(gridView.getVisibility() == View.VISIBLE){gridView.setVisibility(View.GONE);listView.setVisibility(View.VISIBLE);
        }else
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.korz:
                Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(intent);
                return true;
        }
        return true;
    }


}
