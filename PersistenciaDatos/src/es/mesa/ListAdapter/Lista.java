package es.mesa.ListAdapter;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import es.mesa.R;
import es.mesa.SQLite.Usuarios;

public class Lista extends ListActivity {
	Cursor cursor;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_example);
		
		Button bInsert = (Button) findViewById(R.id.btnInsert);
		Button bShow = (Button) findViewById(R.id.btnShow);
		
		// some code
		cursor = getContentResolver().query(Usuarios.CONTENT_URI,
				new String[] { Usuarios._ID, Usuarios.COL_NOMBRE, Usuarios.COL_TELEFONO }, null,
				null, null);
		startManagingCursor(cursor);
		// the desired columns to be bound
		String[] columns = new String[] { Usuarios._ID, Usuarios.COL_NOMBRE, Usuarios.COL_TELEFONO };
		// the XML defined views which the data will be bound to
		int[] to = new int[] {R.id.id_entry, R.id.name_entry, R.id.number_entry };
		// create the adapter using the cursor pointing to the desired data as
		// well as the layout information
		SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,
				R.layout.list_example_entry, cursor, columns, to);
		// set this adapter as your ListActivity's adapter
		this.setListAdapter(mAdapter);
		
		ListView listView = this.getListView();
		listView.setOnItemClickListener(new OnItemClickListener() { 
	         public void onItemClick(AdapterView<?> parent, View view, int position, long id) { 
	             Toast.makeText(getBaseContext(), "Pulsado. Id="+id+" position="+position, 1000).show();
	         } 
	        });
		
		bInsert.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				ContentValues values = new ContentValues();
				values.put(Usuarios.COL_NOMBRE, "Oscar");
				values.put(Usuarios.COL_TELEFONO, "910000000");
				Uri uri = getContentResolver().insert(Usuarios.CONTENT_URI, values );
				Log.i("Uri", uri.toString());
			}
		});
		bShow.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				cursor.requery();	
			}
		});
	}

}
