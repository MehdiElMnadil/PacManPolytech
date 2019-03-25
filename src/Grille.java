import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;

public class Grille extends Observable implements Runnable {
	public final static int HEIGHT_TAB_SIZE = 10;
	public final static int WIDTH_TAB_SIZE = 10;
	private boolean[][] tab = new boolean[HEIGHT_TAB_SIZE][WIDTH_TAB_SIZE]; 
	private HashMap<Entite, Coordonnees> coordonnees = new HashMap<Entite, Coordonnees>();

	public Grille() {
		for (int i = 0; i < HEIGHT_TAB_SIZE; i++) {
			for (int j = 0; j< WIDTH_TAB_SIZE; j++) {
				this.tab[i][j]=(new Random()).nextBoolean();
			}
		}
	}

	public void run() {
		while(true) {
			setChanged();
			notifyObservers();
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean getValue(int x, int y) {
		return this.tab[x][y];
	}

	public boolean seDeplacer(Direction d, Entite entite) {
		int xresult = this.coordonnees.get(entite).getX();
		int yresult = this.coordonnees.get(entite).getY();
		switch(d) {
		case HAUT:
			yresult--;
			break;
		case BAS:
			yresult++;
			break;
		case GAUCHE:
			xresult--;
			break;
		case DROITE:
			xresult++;
			break;
		}
		if (coordonnesValides(xresult, yresult)){
			this.coordonnees.get(entite).setX(xresult);
			this.coordonnees.get(entite).setY(yresult);
			return true;
		}
		
		return false;
	}

	private boolean coordonnesValides(int x, int y) {
		if (x < 0 || y < 0) return false;
		if (x >= HEIGHT_TAB_SIZE || y >= WIDTH_TAB_SIZE) return false;
		return !getValue(x, y);
	}
	
	public void ajouterEntiteAGrille(Entite entite, int x, int y) {
		this.coordonnees.put(entite, new Coordonnees(x,y));
	}
}
