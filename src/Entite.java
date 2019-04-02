import java.util.Random;

public abstract class Entite{
	protected Grille grille;

	protected String cheminImage;
	
	public Entite(Grille grille) {

		this.grille = grille;
	}

	public String getCheminImage() {
		return cheminImage;
	}
	


}
