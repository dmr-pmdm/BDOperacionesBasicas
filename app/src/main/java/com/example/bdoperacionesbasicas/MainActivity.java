package com.example.bdoperacionesbasicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnInserta, btnBorra, btnModifica, btnMuestra;
    private Intent i;
    private Helper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new Helper(this, "bdusuarios", null, 1);

        btnInserta = findViewById(R.id.btnInserta);
        btnBorra = findViewById(R.id.btnBorra);
        btnModifica = findViewById(R.id.btnModifica);
        btnMuestra = findViewById(R.id.btnMuestra);


        btnInserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this, Insertar.class);
                startActivity(i);
            }
        });

        btnBorra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this, Borrar.class);
                startActivity(i);
            }
        });

        btnModifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this, Modificar.class);
                startActivity(i);
            }
        });

        btnMuestra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this, Mostrar.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bd.close();
    }
}
