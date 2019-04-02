
public class Fantome extends Entite {
	public Fantome(Grille grille) {
		super(grille);
		this.cheminImage = "resources/images/ghostsprite.png";
	}
	public String toString() {
		return "Fantôme";
	}
}
