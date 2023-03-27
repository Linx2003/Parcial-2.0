package com.example.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbAgenda extends DbHelper{

    Context context;

    public DbAgenda(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarAgenda(String usuario, String nombre, Integer cantidad){
        long id = 0;
        try{
            DbHelper dbhelper = new DbHelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("id", usuario);
            values.put("nombre", nombre);
            values.put("Cantidad", cantidad);

            id = db.insert(Database_Table1, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
