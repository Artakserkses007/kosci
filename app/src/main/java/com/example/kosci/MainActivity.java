package com.example.kosci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText Liczba;
    private Button Losuj, Rosnaco, Malejaco;
    private GridLayout grid;
    private TextView Wyniki;

    private List<Integer> kostki = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Liczba = findViewById(R.id.editTextLiczbaKostek);
        Losuj = findViewById(R.id.buttonLosuj);
        grid = findViewById(R.id.gridKostki);
        Wyniki = findViewById(R.id.textViewWyniki);
        Rosnaco = findViewById(R.id.buttonRosnaco);
        Malejaco = findViewById(R.id.buttonMalejaco);

        grid.setColumnCount(5);

        Losuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tekst = Liczba.getText().toString();
                if (tekst.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Podaj liczbę kostek", Toast.LENGTH_SHORT).show();
                    return;
                }

                int ile = Integer.parseInt(tekst);
                if (ile < 1 || ile > 100) {
                    Toast.makeText(MainActivity.this, "Podaj liczbę od 1 do 100", Toast.LENGTH_SHORT).show();
                    return;
                }

                kostki.clear();
                Random random = new Random();
                for (int i = 0; i < ile; i++) {
                    kostki.add(random.nextInt(6) + 1);
                }
                odswiezEkran();
            }
        });
        Rosnaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kostki.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Najpierw wylosuj", Toast.LENGTH_SHORT).show();
                    return;
                }
                int r = kostki.size();
                for (int i = 0; i < r - 1; i++) {
                    for (int j = 0; j < r - i - 1; j++) {
                        if (kostki.get(j) > kostki.get(j + 1)) {
                            int temp = kostki.get(j);
                            kostki.set(j, kostki.get(j + 1));
                            kostki.set(j + 1, temp);
                        }
                    }
                }
                odswiezEkran();
            }
        });
        Malejaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kostki.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Najpierw wylosuj", Toast.LENGTH_SHORT).show();
                    return;
                }
                int m = kostki.size();
                for (int i = 0; i < m - 1; i++) {
                    for (int j = 0; j < m - i - 1; j++) {
                        if (kostki.get(j) < kostki.get(j + 1)) {
                            int temp = kostki.get(j);
                            kostki.set(j, kostki.get(j + 1));
                            kostki.set(j + 1, temp);
                        }
                    }
                }
                odswiezEkran();
            }
        });
    }
    private void odswiezEkran() {
        grid.removeAllViews();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < kostki.size(); i++) {
            int k = kostki.get(i);
            TextView tv = new TextView(this);
            tv.setText(String.valueOf(k));
            tv.setTextSize(20);
            tv.setPadding(15, 15, 15, 15);
            grid.addView(tv);
            sb.append(k);
            if (i < kostki.size() - 1) {
                sb.append(", ");
            }
        }

        Wyniki.setText(sb.toString());
    }
}