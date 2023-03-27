package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.db.DbHelper;

public class MainActivity extends AppCompatActivity {

    EditText user, password;
    Button acceder;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.txt_usuario);
        password = findViewById(R.id.txt_contraseña);
        acceder = findViewById(R.id.btn_acceder);
    }

    public void inicioSesion(View v){
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String users = user.getText().toString();
        String contrasena = password.getText().toString();
        usuario = new Usuario(users);

        Cursor fila=db.rawQuery("select id,contrasena from T_usuario where id='"+
                users+"' and contrasena='"+contrasena+"'",null);

        try{
            if(fila.moveToFirst()){
                String usua =fila.getString(0);
                String pass =fila.getString(1);
                if (users.equals(usua)&&contrasena.equals(pass)){
                    Intent ven=new Intent(this, Agendar.class);
                    ven.putExtra("usuario",usuario);
                    startActivity(ven);
                    user.setText("");
                    password.setText("");
                }
            }
            else {
                Toast.makeText(this, "Datos erroneos", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}