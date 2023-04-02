package com.example.agenda.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String Database_Table = "T_usuario";
    public static final String Database_Table1 = "T_producto";
    private static final int Database_Version = 4;
    private static final String DATABASE_NAME = "agenda_user.db";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + Database_Table + "(" +
                "id Text Primary Key," +
                "contrasena Text)");

        sqLiteDatabase.execSQL("CREATE TABLE " + Database_Table1 + "(" +
                "id Text Primary Key," +
                "Nombre Text," +
                "Cantidad Integer)");

        sqLiteDatabase.execSQL("insert into " + Database_Table + "(id, contrasena) values('admin','admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " +
            Database_Table);
        sqLiteDatabase.execSQL("DROP TABLE " +
                Database_Table1);
        onCreate(sqLiteDatabase);
    }

    public boolean checkUser(String id, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"id"};
        String selection = "id=? and contrasena=?";
        String[] selectionArgs = {id, password};
        Cursor cursor = db.query(Database_Table, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void clearTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + Database_Table);
        db.execSQL("INSERT INTO " + Database_Table + " (id, contrasena) VALUES ('admin', 'admin')");
        db.close();
    }

    public boolean insertProducto(String id, String nombre, int cantidad) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("nombre", nombre);
        values.put("cantidad", cantidad);
        db.insert(Database_Table1, null, values);
        db.close();
        return true;
    }
}
