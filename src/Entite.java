import java.util.Random;

public abstract class Entite implements Runnable {
	private Grille grille;

	protected String cheminImage;
	
	public Entite(Grille grille) {

		this.grille = grille;
	}

	public String getCheminImage() {
		return cheminImage;
	}
	
	@Override
	public void run() {
		while (true) {
			boolean ok = false;
			while(!ok) {
				Direction d = Direction.getRandomDirection();
				ok = grille.seDeplacer(d, this);
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
