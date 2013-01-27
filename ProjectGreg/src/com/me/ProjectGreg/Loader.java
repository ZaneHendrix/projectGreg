package com.me.ProjectGreg;

import java.util.*;
import java.math.*;
import java.io.*;
public class Loader 
{
	Platform[][] levels;

	public Loader() 
	{
		load();
	}

	public void load()
	{
		try
		{
			Scanner sc = new Scanner(new File("data/levels.dat"));
			int q =0;
			int f = 0;
			levels = new Platform [sc.nextInt()][];
			do
			{
				for(int i = 0;i<sc.nextInt();i++)
				{
					for(int a = 0;a<Integer.parseInt(sc.nextLine()); a++)
					{
						if(sc.next().charAt(0)=='p')
						{
							levels[q][f] = new Platform(a*64,i*64);
							f++;
						}
					}
					sc.nextLine();
					f=0;
				}q++;
			}while(q<levels.length);
		}
		catch(Exception E)
		{
		}
	}

	public Platform[] nextLevel() 
	{
		int random = (int)Math.random()*levels.length;
		return null;
	}

}
