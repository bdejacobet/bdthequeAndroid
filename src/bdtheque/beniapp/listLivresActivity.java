package bdtheque.beniapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class listLivresActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.list_livres);

        // r�cup�ration des �l�ments front
        ListView lvLivres = (ListView) findViewById(R.id.lvListLivres);
        Button btReturn = (Button) findViewById(R.id.btReturn);
	    Button btAdd = (Button) findViewById(R.id.btAdd);
	    
        //Listener sur le Bouton 'Retour'
	    btReturn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent intentReturn = new Intent(listLivresActivity.this,BdthequeActivity.class);
        		startActivity(intentReturn);
        	}
        });
	    
        //Listener sur le Bouton 'Ajout'
	    btAdd.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent intentAdd = new Intent(listLivresActivity.this,formLivreActivity.class);
        		startActivity(intentAdd);
        	}
        });
	    
	    // r�cup�ration des donn�es de l'intent recu
	    Intent thisIntent = getIntent();
	    String strSearchTitre = thisIntent.getExtras().getString("intentTitre");
	    String strSearchSerie = thisIntent.getExtras().getString("intentSerie");


        //Cr�ation d'une instance de ma classe LivresBDD et ouverture de la bdd
        LivresDBAdaptateur livreDB = new LivresDBAdaptateur(this.getApplicationContext());
        livreDB.open();

        
        // r�cup�ration des livres
        ArrayList<Livre> listlivres = new ArrayList();
        String strResult = "";
        if(!strSearchTitre.equals("")){
        	listlivres = livreDB.getLivresWithTitre(strSearchTitre);
        	strResult = "Recherche du titre '"+strSearchTitre + "'";
        }else if(!strSearchSerie.equals("")){
        	listlivres = livreDB.getLivresWithSerie(strSearchSerie);
        	strResult = "Recherche de la serie '"+strSearchSerie + "'";
        }else{
        	listlivres = livreDB.getLivresAll();
        	strResult = "Recup�ration de toutes les bds";
        }
        
        // affichage r�sultats
        if(listlivres.size() != 0){
        	LivreAdapter adapter = new LivreAdapter(this, listlivres);
        	lvLivres.setAdapter(adapter);
         	Toast.makeText(listLivresActivity.this, strResult + " : " + listlivres.size()+ " r�sultats", Toast.LENGTH_LONG).show();
        }else{
        	Toast.makeText(listLivresActivity.this, strResult + " : pas de r�sultats", Toast.LENGTH_LONG).show();
        }
        
        livreDB.close();
       
        
		
    }
}
