package bdtheque.beniapp;

import java.io.InputStream;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;


public class MaBaseSQLite extends SQLiteOpenHelper {
 
	// proprietes de la table
	private static final String TABLE_LIVRES = "table_livres";
	private static final String COL_ID = "_id";
	private static final String COL_ISBN = "ISBN";
	private static final String COL_TITRE = "Titre";
	private static final String COL_SERIE = "Serie";
	private static final String COL_NUMERO = "Numero";
 
	// sql creation bdd
	private static final String CREATE_BDD = "CREATE TABLE " + TABLE_LIVRES + " ("
			+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COL_ISBN + " TEXT, "
			+ COL_TITRE + " TEXT, "
			+ COL_SERIE + " TEXT, "
			+ COL_NUMERO + " INTEGER );";
	
	//sql ajout livres
	private static final String SQL_AJOUT_LIVRE = "insert_livres.sql";
	
    private final Context myContext;
 
	public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
        myContext = context;
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		//creation de la table à partir de la requête écrite dans la variable CREATE_BDD
		db.execSQL(CREATE_BDD);
				
		//insertion des livres via le script insert_livres.sql
		try{
			InputStream is = myContext.getResources().getAssets().open(SQL_AJOUT_LIVRE);
			String[] statements = FileHelper.parseSqlFile(is);
			for (String statement : statements) {
				db.execSQL(statement);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE " + TABLE_LIVRES + ";");
		onCreate(db);
	}
 
}