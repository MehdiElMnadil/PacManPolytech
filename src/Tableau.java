import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Tableau extends Application implements Observer  {
	
	private Grille grille;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.grille = new Grille();
		Fantome lefantome = new Fantome(grille);
		grille.ajouterEntiteAGrille(lefantome, 3, 3);
		//Console c = new Console(g);
		// gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();
        
        FileInputStream input = new FileInputStream("resources/images/casenoire.gif");
        Image casenoire = new Image(input);
        
        input = new FileInputStream("resources/images/casepleine.gif");
        Image casepleine = new Image(input);
        
        this.grille.addObserver(new Observer() {
            
            @Override
            public void update(Observable o, Object arg) {
            	//affichage.setText("Err");
            	for (Map.Entry me : grille.getCoordonnees().entrySet()) {
            		;
            		try {
            			FileInputStream fantomeImage = new FileInputStream(((Entite) me.getKey()).getCheminImage());
						gPane.add(new ImageView(new Image(fantomeImage)), ((Coordonnees) me.getValue()).getX(), ((Coordonnees) me.getValue()).getY());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
                  }
            }
        });

        for (int i = 0; i < Grille.HEIGHT_TAB_SIZE; i++) {
        	for (int j = 0; j < Grille.WIDTH_TAB_SIZE; j++) {
        		ImageView caseactuelle;
        		if (this.grille.getTab()[i][j]) {
        			caseactuelle = new ImageView(casenoire);
        		}
        		else {
        			caseactuelle = new ImageView(casepleine);
        		}
                gPane.add(caseactuelle, i, j);
                
        	}
            //System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
        	 
        }
        
        
        gPane.setGridLinesVisible(true);
        
        border.setCenter(gPane);
        
        Scene scene = new Scene(border, Color.WHITE);
        
        (new Thread(grille)).start();
        (new Thread(lefantome)).start();
        
        primaryStage.setTitle("Pacman");
        primaryStage.setScene(scene);
        primaryStage.show();
	}

}
