import java.util.logging.Level;
import java.util.logging.Logger;

public class Fantome extends Entite implements Runnable{
	public Fantome(Grille grille) {
		super(grille);
		this.cheminImage = "resources/images/ghostsprite.png";
	}
	public String toString() {
		return "Fantôme";
	}
	
	@Override
	public void run() {
		while (true) {
			boolean ok = false;
			while(!ok) {
				Direction d = Direction.getRandomDirection();
				ok = this.grille.seDeplacer(d, this);
			}
			
			try {
                Thread.sleep(300); // pause
            } catch (InterruptedException ex) {
                Logger.getLogger(Fantome.class.getName()).log(Level.SEVERE, null, ex);
            }
		}

	}
}
