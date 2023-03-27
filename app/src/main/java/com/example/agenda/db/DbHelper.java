package com.example.agenda.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String Database_Table = "T_usuario";
    public static final String Database_Table1 = "T_producto";
    private static final int Database_Version = 3;
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
                "id Text," +
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
}
