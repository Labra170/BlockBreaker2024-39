package puppy.code;

public class BlockFactoryMedio implements BlockFactory{
	@Override
	public BlockDefault crearBlockDefault(int x, int y, int width, int height, int nivel)
	{
		return new BlockDefault(x, y, width, height, 2);
	}
	public BlockHP crearBlockHP(int x, int y, int width, int height, int nivel)
	{
		return new BlockHP(x, y, width, height, 3);
	}
	public BlockDoublePoints crearBlockDoublePoints(int x, int y, int width, int height, int nivel)
	{
		return new BlockDoublePoints(x, y, width, height, 4);
	}
}
