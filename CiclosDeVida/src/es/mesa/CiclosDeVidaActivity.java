package es.mesa;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class CiclosDeVidaActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        showMsg("onCreate");
    }
    
    @Override
	protected void onStart(){
		super.onStart();
		showMsg("onStart");		
	}
    @Override
	protected void onResume(){
		super.onResume();
		showMsg("onResume");
	}
    @Override
	protected void onPause(){
		super.onPause();
		showMsg("onPause");
	}
    @Override
	protected void onStop(){
		super.onStop();
		showMsg("onStop");
	}
    @Override
	protected void onRestart(){
		super.onRestart();
		showMsg("onRestart");
	}
    @Override
	protected void onDestroy(){
		super.onDestroy();
		showMsg("onDestroy");
	}
    /*
     * Ciclo para guardar y cargar datos
    */
    @Override        
    protected void onSaveInstanceState(Bundle SavedInstanceState) {
       super.onSaveInstanceState(SavedInstanceState);            
       showMsg("onSaveInstanceState");
    }
    @Override    
    protected void onRestoreInstanceState(Bundle RestoreInstanceState) {
        super.onRestoreInstanceState(RestoreInstanceState);     
        showMsg("onRestoreInstanceState");   
    } 
    
    private void showMsg(String message) {
		Toast msg = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);
		msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2,
				msg.getYOffset() / 2);
		msg.show();
	}
}