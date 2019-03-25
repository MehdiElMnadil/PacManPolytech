import java.util.Random;

public enum Direction {
	HAUT,
	BAS,
	GAUCHE,
	DROITE;

public static Direction getRandomDirection(){
	return Direction.values()[new Random().nextInt(Direction.values().length)];
}
}