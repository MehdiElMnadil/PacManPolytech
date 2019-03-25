import java.util.Random;

public abstract class Entite implements Runnable {
	private int x;
	private int y;
	private Grille grille;

	public Entite(Grille grille) {
		x = 0;
		y = 0;
		this.grille = grille;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	

}
