package com.example.agenda;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isNotEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

import android.app.Instrumentation;
import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.agenda.db.DbHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Prueba_espresso {

    private DbHelper dbHelper;

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule = new ActivityScenarioRule(MainActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @Test
    public void testLogin() throws InterruptedException {
        // Ingrese un correo electrónico y una contraseña válidos en los campos de entrada de texto
        onView(withId(R.id.txt_usuario)).perform(typeText("admin"));
        onView(withId(R.id.txt_contraseña)).perform(typeText("admin"));

        // Haga clic en el botón de inicio de sesión
        onView(withId(R.id.btn_acceder)).perform(click());
        Thread.sleep(2000);

        // Verifique que la actividad de inicio de sesión se haya cerrado y que se haya iniciado la actividad de bienvenida o la actividad principal de la aplicación.
        intended(hasComponent(Agendar.class.getName())); // Suponiendo que hay una actividad de bienvenida

        onView(withId(R.id.Txt_nombre)).perform(typeText("Producto 1"));
        onView(withId(R.id.Txt_cantidad)).perform(typeText("5"));
        onView(withId(R.id.Btn_insertar)).perform(click());

        DbHelper dbHelper = new DbHelper(ApplicationProvider.getApplicationContext());
        assertTrue(dbHelper.checkIfProductExists("Producto 1", Integer.parseInt("5")));
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    /*@Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.agenda", appContext.getPackageName());
    }*/
}