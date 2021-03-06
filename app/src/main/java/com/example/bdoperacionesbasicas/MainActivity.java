package com.example.bdoperacionesbasicas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnInserta, btnBorra, btnModifica, btnMuestra;
    private Intent i;
    private Helper bd;
    private ListView listUsuarios;
    private final int COD_LLAMADA = 1;
    private boolean mostrar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = new Helper(this, "bdusuarios", null, 1);

        btnInserta = findViewById(R.id.btnInserta);
        btnBorra = findViewById(R.id.btnBorra);
        btnModifica = findViewById(R.id.btnModifica);
        btnMuestra = findViewById(R.id.btnMuestra);
        listUsuarios = findViewById(R.id.listUsuarios);


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
                startActivityForResult(i, COD_LLAMADA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == COD_LLAMADA && resultCode == RESULT_OK){
            String[] usuarios = data.getExtras().getStringArray("usuarios");
            if (usuarios.length != 0) {
                AdaptadorPersonalizado adaptador = new AdaptadorPersonalizado(
                        MainActivity.this,
                        usuarios
                );
                listUsuarios.setAdapter(adaptador);
            } else {
                Toast.makeText(this,
                        "No hay usuarios registrados",
                        Toast.LENGTH_SHORT).show();
            }
            mostrar = true;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!mostrar) {
            listUsuarios.setAdapter(null);
        }
        mostrar = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bd.close();
    }
}
