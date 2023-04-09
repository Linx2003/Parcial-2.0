package com.example.agenda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.agenda.db.DbHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Insertar_Db {

    private Context context;
    private DbHelper dbHelper;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        dbHelper = new DbHelper(context);
        dbHelper.clearTable(); // vacía la tabla antes de cada prueba
    }

    @Test
    public void testInsertarProducto() {
        boolean result = dbHelper.insertProducto("admin", "producto1", 5);
        assertTrue(result);
    }

    @After
    public void tearDown() {
        dbHelper.clearTable();
    }

    /*@Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.agenda", appContext.getPackageName());
    }*/
}