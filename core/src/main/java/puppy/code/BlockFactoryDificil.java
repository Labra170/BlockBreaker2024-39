package puppy.code;

public class BlockFactoryDificil implements BlockFactory{
	@Override
	public BlockHP crearBlockHP(int x, int y, int width, int height, int nivel)
	{
		return new BlockHP(x, y, width, height, nivel);
	}
	public BlockDefault crearBlockDefault(int x, int y, int width, int height, int nivel)
	{
		return new BlockDefault(x, y, width, height, nivel);
	}
	public BlockDoublePoints crearBlockDoublePoints(int x, int y, int width, int height, int nivel)
	{
		return new BlockDoublePoints(x, y, width, height, nivel);
	}
}
