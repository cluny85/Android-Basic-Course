package es.mesa.SQLite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class UsuariosContentProvider extends ContentProvider {

	private static final String INVALID_URI_MESSAGE = "Invalid Uri: ";

	// Definición del CONTENT_URI
	// public static final String AUTHORITY_PART = "es.mesa";
	private static final String uri = "content://es.mesa/usuarios";
	public static final Uri CONTENT_URI = Uri.parse(uri);

	public static final String MIME_TYPE_ALL_ITEMS = "vnd.android.cursor.dir/vnd.es.mesa.android";
	public static final String MIME_TYPE_SINGLE_ITEM = "vnd.android.cursor.item/vnd.es.mesa.android";

	// Base de datos
	private UsuariosSQLiteHelper clidbh;
	private static final String BD_NOMBRE = "DBUsuarios";
	//private static final int BD_VERSION = 1;
	private static final String TABLA_USUARIOS = "Usuarios";

	// UriMatcher
	private static final int CLIENTES = 1;
	private static final int CLIENTES_ID = 2;
	private static final UriMatcher uriMatcher;
	// Inicializamos el UriMatcher
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		uriMatcher.addURI("es.mesa", "usuarios", CLIENTES);
		uriMatcher.addURI("es.mesa", "usuarios/#", CLIENTES_ID);
	}

	@Override
	public boolean onCreate() {
		//clidbh = new UsuariosSQLiteHelper(getContext(), BD_NOMBRE, null,BD_VERSION);
		clidbh = new UsuariosSQLiteHelper(getContext(), BD_NOMBRE, null,0);
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		// Si es una consulta a un ID concreto construimos el WHERE
		String where = selection;
		if (uriMatcher.match(uri) == CLIENTES_ID) {
			where = "_id=" + uri.getLastPathSegment();
		}
		SQLiteDatabase db = clidbh.getWritableDatabase();
		Cursor c = db.query(TABLA_USUARIOS, projection, where, selectionArgs,
				null, null, sortOrder);
		return c;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int cont;
		// Si es una consulta a un ID concreto construimos el WHERE
		String where = selection;
		if (uriMatcher.match(uri) == CLIENTES_ID) {
			where = "_id=" + uri.getLastPathSegment();
		} else {
			//throw new IllegalArgumentException(INVALID_URI_MESSAGE + uri);
		}
		SQLiteDatabase db = clidbh.getWritableDatabase();
		cont = db.delete(TABLA_USUARIOS, where, selectionArgs);
		return cont;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int cont;
		// Si es una consulta a un ID concreto construimos el WHERE
		String where = selection;
		if (uriMatcher.match(uri) == CLIENTES_ID) {
			where = "_id=" + uri.getLastPathSegment();
		}
		SQLiteDatabase db = clidbh.getWritableDatabase();
		cont = db.update(TABLA_USUARIOS, values, where, selectionArgs);
		return cont;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case CLIENTES:
			return	MIME_TYPE_ALL_ITEMS;
		case CLIENTES_ID:
			return	MIME_TYPE_SINGLE_ITEM;
		default:
			throw new IllegalArgumentException(INVALID_URI_MESSAGE + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		long regId = 1;
		SQLiteDatabase db = clidbh.getWritableDatabase();
		regId = db.insert(TABLA_USUARIOS, null, values);
		Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);
		Log.i("CD_INSERT", ""+regId);
		return newUri;
	}

}
