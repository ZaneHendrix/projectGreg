package com.me.ProjectGreg;

import java.util.*;
import java.math.*;
import java.io.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
public class Level 
{
	Platform[] tiles;
	
	public Level(String s) 
	{
			
			
			//String str = Gdx.files.internal( "data/levels.dat" ).readString();
			//System.out.println("winner");
			String[] scX = "1 1 1 2 2 2 2 1 1 1 1 2 2 1 1 1 1".split(" ");
			String[] scY = "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1".split(" ");
			tiles = new Platform [scX.length];
			int xSum = 0;
			for(int i = 0; i < scX.length; i++)
			{
				xSum += Integer.parseInt(scX[i]);
				tiles[i] = new Platform(-6*64+64*xSum,-16*Integer.parseInt(scY[i]));
			}		
	}
	
	public Platform[] getTiles()
	{
		return tiles;
	}
		
			
}

