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
    public final int SIZE_X = 10;
    public final int SIZE_Y = 10;
	
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
        
        Image casenoire = new Image(new FileInputStream("resources/images/casenoire.gif"));
        
        Image casepleine = new Image(new FileInputStream("resources/images/casepleine.gif"));
        
        Image fantomeImage = new Image(new FileInputStream("resources/images/ghostsprite.png"));
        
        ImageView[][] tab = new ImageView[SIZE_X][SIZE_Y]; // tableau permettant de récupérer les cases graphiques lors du rafraichissement

        for (int i = 0; i < SIZE_X; i++) { // initialisation de la grille (sans image)
            for (int j = 0; j < SIZE_Y; j++) {
                ImageView img = new ImageView();
                
                tab[i][j] = img;
                
                gPane.add(img, i, j);
            }
            
        }
        
//        this.grille.addObserver(new Observer() {
//            
//            @Override
//            public void update(Observable o, Object arg) {
//            	//affichage.setText("Err");
//            	for (Map.Entry me : grille.getCoordonnees().entrySet()) {
//            		;
//						tab[((Coordonnees) me.getValue()).getX()][((Coordonnees) me.getValue()).getY()].setImage(fantomeImage);;
//                    System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
//            	}
//           }
//        });

        for (int i = 0; i < Grille.HEIGHT_TAB_SIZE; i++) {
        	for (int j = 0; j < Grille.WIDTH_TAB_SIZE; j++) {
        		if (!this.grille.getTab()[i][j]) {
        			tab[i][j].setImage(casepleine);
        		}
        		
        		
        		
        		/*ImageView caseactuelle;
        		if (this.grille.getTab()[i][j]) {
        			caseactuelle = new ImageView(casenoire);
        		}
        		else {
        			caseactuelle = new ImageView(casepleine);
        		}*/
                //gPane.add(caseactuelle, i, j);
                
        	}
            //System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
        	 
        }
        
        Observer o =  new Observer() { // l'observer observe l'obervable (update est exécuté dès notifyObservers() est appelé côté modèle )
            @Override
            public void update(Observable o, Object arg) {
            	
                for (int i = 0; i < SIZE_X; i++) { // rafraichissement graphique
                    for (int j = 0; j < SIZE_Y; j++) {
                        
                        if (!grille.getTab()[i][j]) { // spm est à la position i, j => le dessiner
                            tab[i][j].setImage(casenoire);
                            
                        }
                    }
                }
                
                for (Map.Entry me : grille.getCoordonnees().entrySet()) {
                	System.out.println(fantomeImage);
					
					tab[((Coordonnees) me.getValue()).getX()][((Coordonnees) me.getValue()).getY()].setImage(fantomeImage);
                    
					System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
            	}
            }
        };
        
        grille.addObserver(o);
        
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
