package bdtheque.beniapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class formLivreActivity extends Activity {
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.form_livre);
	
	    // récupération des éléments front
	    final EditText textTitre = (EditText) findViewById(R.id.textTitre);
	    final EditText textSerie = (EditText) findViewById(R.id.textSerie);
	    final EditText textISBN = (EditText) findViewById(R.id.textISBN);
	    final EditText textNumero = (EditText) findViewById(R.id.textNumero);
	    final Button btAdd = (Button) findViewById(R.id.btAdd);
	    final Button btCancel = (Button) findViewById(R.id.btCancel);
    
	    //Listener sur le Bouton 'Ajouter'
	    btAdd.setOnClickListener(new OnClickListener() {
	    	public void onClick(View v) {
	    		
	    		// récupération des données
	    		final String strAddTitre = textTitre.getText().toString();
	    		final String strAddSerie = textSerie.getText().toString();
	    		final String strAddISBN = textISBN.getText().toString();
	    		final String strAddNumero = textNumero.getText().toString();
	            
	            // transmission des données
	    		Intent intent = new Intent(formLivreActivity.this,editLivreActivity.class);
	    		intent.putExtra("intentTitre", strAddTitre);
	    		intent.putExtra("intentSerie", strAddSerie);
	    		intent.putExtra("intentNumero", strAddNumero);
	    		intent.putExtra("intentISBN", strAddISBN);
	    		startActivity(intent);
	    	}
	    });
	    
        //Listener sur le Bouton 'Cancel'
	    btCancel.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent intentReturn = new Intent(formLivreActivity.this,BdthequeActivity.class);
        		startActivity(intentReturn);
        	}
        });
	    
	}

}
