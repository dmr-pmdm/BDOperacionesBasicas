package com.example.bdoperacionesbasicas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Borrar extends AppCompatActivity {

    private EditText edtCodigo;
    private Button btnBorrar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar);

        edtCodigo = findViewById(R.id.edtCodigo);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnSalir = findViewById(R.id.btnSalir);

        Helper bd = new Helper(this, "bdusuarios", null, 1);
        final SQLiteDatabase escribirBD = bd.getWritableDatabase();

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigo = Integer.parseInt(edtCodigo.getText().toString());

                int logDelete = escribirBD.delete("usuarios",
                        "codigo = "+codigo,
                        null);
                if (logDelete != 0) {
                    Toast.makeText(Borrar.this,
                            "Usuario borrado correctamente",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Borrar.this,
                            "No existe ningún usuario con ese código en la bd",
                            Toast.LENGTH_SHORT).show();
                }

                edtCodigo.setText("");
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
