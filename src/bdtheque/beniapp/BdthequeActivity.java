package bdtheque.beniapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BdthequeActivity extends Activity {
	    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // récupération des éléments front
        final EditText textSearchTitre = (EditText) findViewById(R.id.textSearchTitre);
        final EditText textSearchSerie = (EditText) findViewById(R.id.textSearchSerie);
        final Button btSearch = (Button) findViewById(R.id.btSearch);
        
        //Listener sur le Bouton 'Rechercher'
        btSearch.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		
        		// récupération des données
        		final String strSearchTitre = textSearchTitre.getText().toString();
                final String strSearchSerie = textSearchSerie.getText().toString();
                
                // transmission des données
        		Intent intent = new Intent(BdthequeActivity.this,listLivresActivity.class);
        		intent.putExtra("intentTitre", strSearchTitre);
        		intent.putExtra("intentSerie", strSearchSerie);
        		startActivity(intent);
        	}
        });
        
    }
   
}