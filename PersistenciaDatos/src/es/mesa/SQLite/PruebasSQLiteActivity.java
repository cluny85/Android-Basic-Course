package es.mesa.SQLite;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import es.mesa.R;
import es.mesa.ListAdapter.Lista;

public class PruebasSQLiteActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlmain);
		
		/*
		 *  Botones para la práctica del content provider
		 */
		Button button1 = (Button) findViewById(R.id.buttonConsulTodos);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {          	
            	usarContentProvider();
              }
            });
        Button button2 = (Button) findViewById(R.id.buttonInsertarReg);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {          	
            	insertarEnContentProvider();
              }
            });
        Button button3 = (Button) findViewById(R.id.buttonEliminarReg);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {          	
            	deleteEnContentProvider();
              }
            });
        Button button4 = (Button) findViewById(R.id.toListView);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {          	
            	Intent in = new Intent(getBaseContext(),Lista.class);
            	startActivity(in);
              }
            });
		
		/*
		// Abrimos la base de datos 'DBUsuarios' en modo escritura
		UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this,
				"DBUsuarios", null, 1);
		SQLiteDatabase db = usdbh.getWritableDatabase();
		// Si hemos abierto correctamente la base de datos
		if (db != null) {
			// Insertamos 5 usuarios de ejemplo
			for (int i = 1; i <= 5; i++) {
				// Generamos los datos
				int codigo = i;
				String nombre = "Usuario" + i;
				// Insertamos los datos en la tabla Usuarios
				db.execSQL("INSERT INTO Usuarios (codigo, nombre) "
						+ "VALUES (" + codigo + ", '" + nombre + "')");
			}
			// Cerramos la base de datos
			db.close();
		}
		*/
	}

	public void usarContentProvider() {
		// Columnas de la tabla a recuperar
		String[] projection = new String[] { Usuarios._ID, Usuarios.COL_NOMBRE,
				Usuarios.COL_TELEFONO, Usuarios.COL_EMAIL };
		Uri clientesUri = UsuariosContentProvider.CONTENT_URI;
		ContentResolver cr = getContentResolver();
		// Hacemos la consulta
		Cursor cur = cr.query(clientesUri, projection, // Columnas a devolver
				null, // Condición de la query
				null, // Argumentos variables de la query
				null); // Orden de los resultados
		
		EditText txtResultados = (EditText) findViewById(R.id.txtResultados);		
		if (cur.moveToFirst()) {
			String nombre;
			String telefono;
			String email;
			int colNombre = cur.getColumnIndex(Usuarios.COL_NOMBRE);
			int colTelefono = cur.getColumnIndex(Usuarios.COL_TELEFONO);
			int colEmail = cur.getColumnIndex(Usuarios.COL_EMAIL);
			txtResultados.setText("");
			do {
				nombre = cur.getString(colNombre);
				telefono = cur.getString(colTelefono);
				email = cur.getString(colEmail);
				txtResultados.append(nombre + " - " + telefono + " - " + email + "\n");
			} while (cur.moveToNext());
		}
		cur.close();
	}
	
	public void insertarEnContentProvider(){
		ContentValues values = new ContentValues();
		values.put(Usuarios.COL_NOMBRE, "ClienteN");
		values.put(Usuarios.COL_TELEFONO, "999111222");
		values.put(Usuarios.COL_EMAIL, "nuevo@email.com");
		ContentResolver cr = getContentResolver();
		cr.insert(UsuariosContentProvider.CONTENT_URI, values);		
	}
		
	public void deleteEnContentProvider(){
		ContentResolver cr = getContentResolver();
		cr.delete(UsuariosContentProvider.CONTENT_URI, Usuarios.COL_NOMBRE + " = 'ClienteN'", null);
	}

}
