package bdtheque.beniapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
 
public class LivresDBAdaptateur {
 
	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "BDtheque.db";
 
	private static final String TABLE_LIVRES = "table_livres";
	private static final String COL_ID = "_id";
	private static final int NUM_COL_ID = 0;
	private static final String COL_ISBN = "ISBN";
	private static final int NUM_COL_ISBN = 1;
	private static final String COL_TITRE = "Titre";
	private static final int NUM_COL_TITRE = 2;
	private static final String COL_SERIE = "Serie";
	private static final int NUM_COL_SERIE = 3;
	private static final String COL_NUMERO = "Numero";
	private static final int NUM_COL_NUMERO = 4;
 
	private SQLiteDatabase bdd;
 
	private MaBaseSQLite maBaseSQLite;
	
	/*
	 * Creation de la BDD et sa table
	 */
	public LivresDBAdaptateur(Context context){
		maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
	}
	 
	/*
	 * Ouverture de la BDD en écriture
	 */
	public void open(){
		bdd = maBaseSQLite.getWritableDatabase();
	}
	
	/*
	 *  Fermeture de l'accès à la BDD
	 */
	public void close(){
		bdd.close();
	}
 
	/*
	 *  Récupération de la BDD
	 */
	public SQLiteDatabase getBDD(){
		return bdd;
	}	 
	
	/*
	 * Ajout d'un livre dans la BDD
	*/
	public long insertLivre(Livre livre){
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_ISBN, livre.getIsbn());
		values.put(COL_TITRE, livre.getTitre());
		values.put(COL_SERIE, livre.getSerie());
		values.put(COL_NUMERO, livre.getNumero());
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.insert(TABLE_LIVRES, null, values);
	}
	 
	/*
	 * Mise à jour d'un livre dans la BDD grâce à l'ID
	*/
	public int updateLivre(int id, Livre livre){
		ContentValues values = new ContentValues();
		values.put(COL_ISBN, livre.getIsbn());
		values.put(COL_TITRE, livre.getTitre());
		values.put(COL_SERIE, livre.getSerie());
		values.put(COL_NUMERO, livre.getNumero());
		return bdd.update(TABLE_LIVRES, values, COL_ID + " = " +id, null);
	}
 
	/*
	 * Suppression d'un livre de la BDD grâce à l'ID
	 */
	public int removeLivreWithID(int id){
		return bdd.delete(TABLE_LIVRES, COL_ID + " = " +id, null);
	}
	 
	/*
	 * Suppression de tous les livres de la BDD
	 */
	public int removeLivreALL(){
		return bdd.delete(TABLE_LIVRES, null, null);
	}

	
	/*
	 * Récupèration dans un Cursor de tous les livres
	 */
	public ArrayList<Livre> getLivresAll(){
		Cursor c = bdd.query(TABLE_LIVRES, new String[] {COL_ID, COL_ISBN, COL_TITRE, COL_SERIE, COL_NUMERO}, null, null, null, null, COL_SERIE+", "+COL_NUMERO+", "+COL_TITRE);
		return cursorToListLivres(c);
	}

	
	/*
	 * Récupèration dans un Cursor des livres grâce à son titre
	 */
	public ArrayList<Livre> getLivresWithTitre(String titre){
		Cursor c = bdd.query(TABLE_LIVRES, new String[] {COL_ID, COL_ISBN, COL_TITRE, COL_SERIE, COL_NUMERO}, COL_TITRE + " LIKE \"%" + titre +"%\"", null, null, null, COL_SERIE+", "+COL_NUMERO+", "+COL_TITRE);
		return cursorToListLivres(c);
	}
	
	/*
	 * Récupèration dans un Cursor des livres grâce à sa serie
	 */
	public ArrayList<Livre> getLivresWithSerie(String serie){
		Cursor c = bdd.query(TABLE_LIVRES, new String[] {COL_ID, COL_ISBN, COL_TITRE, COL_SERIE, COL_NUMERO}, COL_SERIE + " LIKE \"%" + serie +"%\"", null, null, null, COL_SERIE+", "+COL_NUMERO+", "+COL_TITRE);
		return cursorToListLivres(c);
	}

	/*
	 * Convertion un cursor en ArrayList de livres
	 */
	private ArrayList<Livre> cursorToListLivres(Cursor c){

        ArrayList<Livre> listLivres = new ArrayList<Livre>();

		if (c.getCount() != 0){	        
			c.moveToFirst();
	        //pour chaque livre retourné
			 do{
	    		//on créé un livre
	    		Livre livre = new Livre();
	    		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
	    		livre.setId(c.getInt(NUM_COL_ID));
	    		livre.setIsbn(c.getString(NUM_COL_ISBN));
	    		livre.setTitre(c.getString(NUM_COL_TITRE));
	    		livre.setSerie(c.getString(NUM_COL_SERIE));
	    		livre.setNumero(c.getString(NUM_COL_NUMERO));
	    		// on ajoute le livre à la liste
	    		listLivres.add(livre);
	    		//positionnement sur le suivant
	    		c.moveToNext();
			 }while(!c.isAfterLast());
			c.close();
		}
		 
		return listLivres;
	}

}
