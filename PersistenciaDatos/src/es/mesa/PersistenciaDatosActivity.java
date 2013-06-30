package es.mesa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import es.mesa.RAW.ManejoRaw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PersistenciaDatosActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.readwrite);
		
		Button button = (Button) findViewById(R.id.buttonEscribe);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	escribe();
              }
            });
        Button button1 = (Button) findViewById(R.id.buttonLee);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	leeFich();
              }
            });
        Button button2 = (Button) findViewById(R.id.buttonToRaw);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent in = new Intent(getBaseContext(), ManejoRaw.class);
            	startActivity(in);
              }
            });
	}

	public void escribe() {
		try {
			OutputStreamWriter fout = new OutputStreamWriter(openFileOutput(
					"prueba_int.txt", Context.MODE_PRIVATE));
			fout.write("Texto de prueba.");
			fout.close();
		} catch (Exception ex) {
			Log.e("Ficheros", "Error al escribir fichero a memoria interna");
		}
	}

	public void leeFich() {
		try {
			BufferedReader fin = new BufferedReader(new InputStreamReader(
					openFileInput("prueba_int.txt")));
			String texto = fin.readLine();
			fin.close();
			Log.d("leeFich", texto);
		} catch (Exception ex) {
			Log.e("Ficheros", "Error al leer fichero desde memoria interna");
		}
	}
}