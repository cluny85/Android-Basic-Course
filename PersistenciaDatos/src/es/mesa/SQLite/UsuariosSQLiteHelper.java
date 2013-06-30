package es.mesa.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 3;

	// Sentencia SQL para crear la tabla de Usuarios
	String sqlCreate = "CREATE TABLE Usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT, email TEXT)";
	String sqlDelete = "DROP TABLE Usuarios";

	public UsuariosSQLiteHelper(Context contexto, String nombre,
			CursorFactory factory, int version) {
		super(contexto, nombre, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Se ejecuta la sentencia SQL de creación de la tabla
		db.execSQL(sqlCreate);

		// Apartado de Content Provider
		// ****************************
		// Insertamos 15 clientes de ejemplo
		for (int i = 1; i <= 15; i++) {
			// Generamos los datos de muestra
			String nombre = "Cliente" + i;
			String telefono = "900-123-00" + i;
			String email = "email" + i + "@mail.com";
			// Insertamos los datos en la tabla Clientes
			db.execSQL("INSERT INTO Usuarios (nombre, telefono, email) "
					+ "VALUES ('" + nombre + "', '" + telefono + "', '" + email
					+ "')");
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versionAnterior,
			int versionNueva) {
		// NOTA: Por simplicidad del ejemplo aquí utilizamos directamente
		// la opción de eliminar la tabla anterior y crearla de nuevo
		// vacía con el nuevo formato.
		// Sin embargo lo normal será que haya que migrar datos de la
		// tabla antigua a la nueva, por lo que este método debería
		// ser más elaborado.
		// Se elimina la versión anterior de la tabla
		db.execSQL("DROP TABLE IF EXISTS Usuarios");
		// Se crea la nueva versión de la tabla
		db.execSQL(sqlCreate);
	}

	public void newRegistro(SQLiteDatabase db) {
		// Creamos el registro a insertar como objeto ContentValues
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put("usuario", "usu10");
		nuevoRegistro.put("email", "usu10@email.com");
		// Insertamos el registro en la base de datos
		db.insert("Usuarios", null, nuevoRegistro);
	}

	public void updateUsuario(SQLiteDatabase db) {
		// Establecemos los campos-valores a actualizar
		ContentValues valores = new ContentValues();
		valores.put("email", "usu1_nuevo@email.com");
		// Actualizamos el registro en la base de datos
		db.update("Usuarios", valores, "usuario='usu1'", null);
	}

	public void deleteUsuario(SQLiteDatabase db) {
		// Eliminamos el registro del usuario 'usu2'
		db.delete("Usuarios", "usuario='usu2'", null);
	}

	// Usando el método execSQL
	// ************************
	public void eliminarUsuario(SQLiteDatabase db) {
		// Eliminar un registro con execSQL(), utilizando argumentos
		String[] args = new String[] { "usu1" };
		db.execSQL("DELETE FROM Usuarios WHERE usuario=?", args);
	}

	public void actualizarRegistros(SQLiteDatabase db) {
		// Actualizar dos registros con update(), utilizando argumentos
		ContentValues valores = new ContentValues();
		valores.put("email", "usu1_nuevo@email.com");
		String[] args = new String[] { "usu1", "usu2" };
		db.update("Usuarios", valores, "usuario=? OR usuario=?", args);
	}

	public Cursor getUsuarios1(SQLiteDatabase db) {
		String query = " SELECT usuario,email FROM Usuarios WHERE usuario='usu1' ";
		Cursor c = db.rawQuery(query, null);
		return c;
	}

	public Cursor getUsuarios2(SQLiteDatabase db) {
		String query = " SELECT usuario,email FROM Usuarios WHERE usuario=? ";
		String[] args = new String[] { "usu1" };
		Cursor c = db.rawQuery(query, args);
		return c;
	}

	public Cursor getUsuarios3(SQLiteDatabase db) {
		String[] campos = new String[] { "usuario", "email" };
		String[] args = new String[] { "usu1" };
		Cursor c = db.query("Usuarios", campos, "usuario=?", args, null, null,
				null);
		return c;
	}

	public void getUsuarios4(SQLiteDatabase db) {
		String[] campos = new String[] { "usuario", "email" };
		String[] args = new String[] { "usu1" };
		Cursor c = db.query("Usuarios", campos, "usuario=?", args, null, null,
				null);
		// Nos aseguramos de que existe al menos un registro
		if (c.moveToFirst()) {
			// Recorremos el cursor hasta que no haya más registros
			do {
				String usuario = c.getString(0);
				String email = c.getString(1);
				Log.d("getUsuarios4", "Usuario= " + usuario + ", Email= "
						+ email);
			} while (c.moveToNext());
		}

	}

}
