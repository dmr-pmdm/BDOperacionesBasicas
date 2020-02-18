package com.example.bdoperacionesbasicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insertar extends AppCompatActivity {

    private EditText edtCodigo, edtNombre;
    private Button btnInsertar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        edtCodigo = findViewById(R.id.edtCodigo);
        edtNombre = findViewById(R.id.edtNombre);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnSalir = findViewById(R.id.btnSalir);

        Helper bd = new Helper(this, "bdusuarios", null, 1);
        final SQLiteDatabase escribirBD = bd.getWritableDatabase();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigo = Integer.parseInt(edtCodigo.getText().toString());
                String nombre = edtNombre.getText().toString();

                if (!nombre.isEmpty()) {
                    ContentValues nuevoRegistro = new ContentValues();
                    nuevoRegistro.put("codigo", codigo);
                    nuevoRegistro.put("nombre", nombre);

                    long logInsert = escribirBD.insert("usuarios",
                            null,
                            nuevoRegistro);
                    if (logInsert != -1) {
                        Toast.makeText(Insertar.this,
                                "Usuario insertado correctamente",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Insertar.this,
                                "Ya existe un usuario con ese código en la bd",
                                Toast.LENGTH_SHORT).show();
                    }

                    edtCodigo.setText("");
                    edtNombre.setText("");
                } else {
                    Toast.makeText(Insertar.this,
                            "El campo nombre no puede estar vacio",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
