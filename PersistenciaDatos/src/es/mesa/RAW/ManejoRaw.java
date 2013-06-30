package es.mesa.RAW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import es.mesa.R;
import es.mesa.SQLite.PruebasSQLiteActivity;

public class ManejoRaw extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainraw);
		
		Button button = (Button) findViewById(R.id.buttonLeeRaw);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {            	
            	leerRecurso();
              }
            });
        Button button1 = (Button) findViewById(R.id.buttonEscribeSD);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {            	
            	chequeoYescritura();
              }
            });
        Button button2 = (Button) findViewById(R.id.buttonLeeSD);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	leerTarjetaSD();
              }
            });
        Button button3 = (Button) findViewById(R.id.buttonToSQLite);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent in = new Intent(getBaseContext(), PruebasSQLiteActivity.class);
            	startActivity(in);
              }
            });
	}
	
	public void leerRecurso() {
		try {
			InputStream fraw = getResources().openRawResource(R.raw.prueba_raw);
			BufferedReader brin = new BufferedReader(
					new InputStreamReader(fraw));
			String linea = brin.readLine();
			fraw.close();
			Log.d("leerRecurso", linea);
		} catch (Exception ex) {
			Log.e("Ficheros", "Error al leer fichero desde recurso raw");
		}

	}

	/*
	 * Ficheros en tarjeta SD
	 */
	public void chequeoYescritura() {
		boolean sdDisponible = false;
		boolean sdAccesoEscritura = false;
		// Comprobamos el estado de la memoria externa (tarjeta SD)
		String estado = Environment.getExternalStorageState();
		if (estado.equals(Environment.MEDIA_MOUNTED)) {
			sdDisponible = true;
			sdAccesoEscritura = true;

			// Escribimos en el fichero de prueba
			try {
				File ruta_sd = Environment.getExternalStorageDirectory();
				File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
				OutputStreamWriter fout = new OutputStreamWriter(
						new FileOutputStream(f));
				fout.write("Texto de prueba.");
				fout.close();
			} catch (Exception ex) {
				Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
			}

		} else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			sdDisponible = true;
			sdAccesoEscritura = false;
		} else {
			sdDisponible = false;
			sdAccesoEscritura = false;
		}
	}

	/*
	 *  Leer de la tarjeta SD 
	 */
	public void leerTarjetaSD() {

		try {
			File ruta_sd = Environment.getExternalStorageDirectory();
			File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
			BufferedReader fin = new BufferedReader(new InputStreamReader(
					new FileInputStream(f)));
			String texto = fin.readLine();
			fin.close();
			Log.d("leerTarjetaSD", texto);
		} catch (Exception ex) {
			Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
		}

	}

}
