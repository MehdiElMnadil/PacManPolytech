import java.util.Observable;
import java.util.Random;

public class Grille extends Observable implements Runnable {
	public final static int HEIGHT_TAB_SIZE = 10;
	public final static int WIDTH_TAB_SIZE = 10;
	private boolean[][] tab = new boolean[HEIGHT_TAB_SIZE][WIDTH_TAB_SIZE]; 

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
}
