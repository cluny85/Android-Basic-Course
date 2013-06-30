package es.mesa.SQLite;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Usuarios implements BaseColumns{

	//Clase interna para declarar las constantes de columna
	
	//Nombres de columnas
	public static final String _ID = "_id";
	public static final String COL_NOMBRE = "nombre";
	public static final String COL_TELEFONO = "telefono";
	public static final String COL_EMAIL = "email";
	private static final String uri = "content://es.mesa/usuarios";
	public static final Uri CONTENT_URI = Uri.parse(uri);	

}
