import javafx.application.Application;

public class Main {

	public static void main(String[] args) {
		Grille g = new Grille();
		Console c = new Console(g);
		g.addObserver(c);
		//(new Thread(g)).start();
		Application.launch(args);
		
	}

}
