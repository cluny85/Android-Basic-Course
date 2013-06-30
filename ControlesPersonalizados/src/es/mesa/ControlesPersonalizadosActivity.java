package es.mesa;

import android.app.Activity;
import android.os.Bundle;

public class ControlesPersonalizadosActivity extends Activity {
	private ControlLogin ctlLogin;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ctlLogin = (ControlLogin) findViewById(R.id.CtlLogin);
		ctlLogin.setOnLoginListener(new OnLoginListener() {
			public void onLogin(String usuario, String password) {
				// Validamos el usuario y la contraseña
				if (usuario.equals("demo") && password.equals("demo"))
					ctlLogin.setMensaje("Login correcto!");
				else
					ctlLogin.setMensaje("Vuelva a intentarlo.");
			}
		});
	}
}