package bdtheque.beniapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class editLivreActivity extends Activity {
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.edit_livre);

        // récupération des éléments front
	    TextView textResult = (TextView) findViewById(R.id.textResult);
        Button btReturn = (Button) findViewById(R.id.btReturn);
	    
        //Listener sur le Bouton 'Retour'
	    btReturn.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		Intent intentSearch = new Intent(editLivreActivity.this,BdthequeActivity.class);
        		startActivity(intentSearch);
        	}
        });
	    
   
	    // récupération des données de l'intent recu
	    Intent thisIntent = getIntent();
	    String strTitre = thisIntent.getExtras().getString("intentTitre");
	    String strSerie = thisIntent.getExtras().getString("intentSerie");
	    String strNumero = thisIntent.getExtras().getString("intentNumero");
	    String strISBN = thisIntent.getExtras().getString("intentISBN");


        //Création d'une instance de ma classe LivresBDD et ouverture de la bdd
        LivresDBAdaptateur livreDB = new LivresDBAdaptateur(this.getApplicationContext());
        livreDB.open();

        
        // ajout du livre
        if(!strTitre.equals("")){
        	Livre livre = new Livre(strISBN, strTitre, strNumero, strSerie);
        	long Id = livreDB.insertLivre(livre);
        	if(Id > 1){
            	textResult.setText("La bd a été ajouté (id "+ Id+")");
        	}else{
            	textResult.setText("Erreur lors de l'ajout");
        	}
        }else{
        	textResult.setText("Le titre est obligatoire");
        }
        
        
        livreDB.close();
       
        
		
    }
}
