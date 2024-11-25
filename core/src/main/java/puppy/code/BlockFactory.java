package puppy.code;

public interface BlockFactory {
	BlockAbstract crearBlockHP(int x, int y, int width, int height, int nivel);
	BlockAbstract crearBlockDefault(int x, int y, int width, int height, int nivel);
	BlockAbstract crearBlockDoublePoints(int x, int y, int width, int height, int nivel);
}
