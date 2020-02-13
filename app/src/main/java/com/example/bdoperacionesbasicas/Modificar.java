package com.example.bdoperacionesbasicas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar extends AppCompatActivity {

    private EditText edtCodigo, edtNombre;
    private Button btnModificar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        edtCodigo = findViewById(R.id.edtCodigo);
        edtNombre = findViewById(R.id.edtNombre);
        btnModificar = findViewById(R.id.btnModificar);
        btnSalir = findViewById(R.id.btnSalir);

        Helper bd = new Helper(this, "bdusuarios", null, 1);
        final SQLiteDatabase escribirBD = bd.getWritableDatabase();

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigo = Integer.parseInt(edtCodigo.getText().toString());
                String nombre = edtNombre.getText().toString();
                ContentValues otroRegistro = new ContentValues();
                otroRegistro.put("nombre", nombre);
                int logMod = escribirBD.update("usuarios",
                        otroRegistro,
                        "codigo = "+codigo,
                        null);
                if (logMod != 0) {
                    Toast.makeText(Modificar.this,
                            "Usuario modificado correctamente",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Modificar.this,
                            "No existe ningún usuario con ese código en la bd",
                            Toast.LENGTH_SHORT).show();
                }
                edtCodigo.setText("");
                edtNombre.setText("");
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean encontrado = false;
                if (s.length() != 0) {
                    int codigo = Integer.parseInt(edtCodigo.getText().toString());
                    String[] fields = {"codigo"};
                    Cursor c;
                    c = escribirBD.query("usuarios",
                            fields, null, null, null,
                            null, null);
                    if (c.moveToFirst()) {
                        do {
                            if (codigo == c.getInt(0)){
                                encontrado = true;
                            }
                        }while (c.moveToNext());
                    }
                }
                if (encontrado) {
                    edtNombre.setVisibility(View.VISIBLE);
                } else {
                    edtNombre.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }
}
