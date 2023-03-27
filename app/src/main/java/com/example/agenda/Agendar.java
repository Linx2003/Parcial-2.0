package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.db.DbAgenda;

public class Agendar extends AppCompatActivity {

    private Usuario usuario;

    EditText nombre, cantidad;
    Button insertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        nombre = findViewById(R.id.Txt_nombre);
        cantidad = findViewById(R.id.Txt_cantidad);
        insertar = findViewById(R.id.Btn_insertar);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
    }

    public void Insertardatos(View v){
        DbAgenda dbAgenda = new DbAgenda(Agendar.this);
        long id = dbAgenda.insertarAgenda(usuario.getUsuario().toString(), nombre.getText().toString(), Integer.valueOf(cantidad.getText().toString()));

        if (id > 0){
            Toast.makeText(this, "registro guardado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiar(){
        nombre.setText("");
        cantidad.setText("");
    }
}