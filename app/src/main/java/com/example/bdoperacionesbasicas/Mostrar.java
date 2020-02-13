package com.example.bdoperacionesbasicas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Mostrar extends AppCompatActivity {

    private EditText edtCodigo;
    private Button btnMostrar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        edtCodigo = findViewById(R.id.edtCodigo);
        btnMostrar = findViewById(R.id.btnMostrar);
        btnSalir = findViewById(R.id.btnSalir);

        Helper bd = new Helper(this, "bdusuarios", null, 1);
        final SQLiteDatabase escribirBD = bd.getWritableDatabase();

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c;
                String[] fields = {"codigo", "nombre"};
                if (edtCodigo.getText().toString().isEmpty()) {
                    c = escribirBD.query("usuarios",
                            fields, null, null, null,
                            null, null);
                } else {
                    c = escribirBD.query("usuarios",
                            fields,
                            "codigo = " + Integer.parseInt(edtCodigo.getText().toString()),
                            null, null,
                            null, null);
                }
                if (c.moveToFirst()) {
                    do {
                        int codigo = c.getInt(0);
                        String nombre = c.getString(1);
                        Toast.makeText(Mostrar.this,
                                "Codigo: " + codigo + "\n" +
                                        "Nombre: " + nombre,
                                Toast.LENGTH_SHORT).show();
                    } while (c.moveToNext());
                } else {
                    Toast.makeText(Mostrar.this,
                            "No hay usuarios registrados",
                            Toast.LENGTH_SHORT).show();
                }
                c.close();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edtCodigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean encontrado = false;
                if (s.length() != 0) {
                    btnMostrar.setText("MOSTRAR");
                    int codigo = Integer.parseInt(edtCodigo.getText().toString());
                    String[] fields = {"codigo"};
                    Cursor c;
                    c = escribirBD.query("usuarios",
                            fields, null, null, null,
                            null, null);
                    if (c.moveToFirst()) {
                        do {
                            if (codigo == c.getInt(0)) {
                                encontrado = true;
                            }
                        } while (c.moveToNext());
                    }
                    if (encontrado) {
                        btnMostrar.setEnabled(true);
                    } else {
                        btnMostrar.setEnabled(false);
                    }
                } else {
                    btnMostrar.setEnabled(true);
                    btnMostrar.setText("MOSTRAR TODOS");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
