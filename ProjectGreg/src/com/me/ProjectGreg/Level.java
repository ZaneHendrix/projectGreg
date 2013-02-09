package com.me.ProjectGreg;

public class Level 
{
	Platform[] tiles;
	
	public Level(String s) 
	{
			
			
			//String str = Gdx.files.internal( "data/levels.dat" ).readString();
			////System.out.println("winner");
			String[] scX = "1 1 1 1 1 1 1 1 1 1 1 2 2 3 3 2 4 3 1 1 1 1 1 1 1 1 1 1 1 1 1 2 3 4 3 1 2 1 1 1 1 1 1 1 1 1 1 1 1 1 2 3 4 3 1 2 1 1 1 1 1 1 1 1 1 1 1 1 1 1 2 3 4 3 1 2 1 2 4".split(" ");
			String[] scY = "1 1 1 1 1 1 1 1 1 1 1 -2 -4 -6 -4 -5 -4 -2 1 1 1 1 1 1 1 1 1 1 1 1 1 2 4 2 1 -3 -4 1 1 1 1 1 1 1 1 1 1 1 1 1 2 4 2 1 -3 -4 -5 1 1 1 1 1 1 1 1 1 1 1 1 1 2 4 3 1 -3 -4 -5 -7 0".split(" ");
			tiles = new Platform [scX.length];
			int xSum = 0;
			for(int i = 1; i < scX.length-1; i++)
			{
				xSum += Integer.parseInt(scX[i]);
				if(((Integer.parseInt(scX[i])>1) && (Integer.parseInt(scX[i+1])>1))||(Integer.parseInt(scY[i])!=Integer.parseInt(scY[i-1])&&Integer.parseInt(scY[i])!=Integer.parseInt(scY[i]+1)))
				{
					tiles[i] = new Platform(-6*64+64*xSum,-32*Integer.parseInt(scY[i]),true);
				}
				else
				{
					tiles[i] = new Platform(-6*64+64*xSum,-16*Integer.parseInt(scY[i]),false); 
				}
				
			}
			tiles[0]=new Platform(-6*64+64*1,-16*Integer.parseInt(scY[0]),false);
			tiles[tiles.length-1]= new Platform(xSum*64+64,-16*Integer.parseInt(scY[tiles.length-1]),true);
	}
	
	public Platform[] getTiles()
	{
		return tiles;
	}
		
			
}

