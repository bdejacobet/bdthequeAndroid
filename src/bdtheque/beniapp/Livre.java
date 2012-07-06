package bdtheque.beniapp;

public class Livre {
	private int id;
	private String isbn;
	private String titre;
	private String numero;
	private String serie;
	
	public Livre(){}
 
	public Livre(String isbn, String titre, String numero, String serie){
		this.setIsbn(isbn);
		this.setTitre(titre);
		this.setNumero(numero);
		this.setSerie(serie);
	}
 
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public String getIsbn() {
		return isbn;
	}
 
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
 
	public String getTitre() {
		return titre;
	}
 
	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	 
	public String toString(){
		return "ID : "+id+"\nISBN : "+isbn+"\nSerie : "+serie+"\nNumero : "+numero+"\nTitre : "+titre;
	}
}
