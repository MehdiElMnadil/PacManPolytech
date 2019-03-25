import java.util.Observable;
import java.util.Observer;

public class Console implements Observer {

	private Grille grille;
	
	public Console(Grille grille) {
		this.grille = grille;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		for (int i=0; i < Grille.HEIGHT_TAB_SIZE; i++) {
			for (int j = 0; j < Grille.WIDTH_TAB_SIZE; j++) {
				System.out.print(this.grille.getValue(i, j));
			}
			System.out.print("\n");
		}
	}

}
