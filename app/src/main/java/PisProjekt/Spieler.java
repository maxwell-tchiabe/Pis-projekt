package PisProjekt;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Spieler   {
	
//+++++++++++++++++++++++++++++++++++++Variable Declaration++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private int place_y = 480;
	private int place_x = 300;
	private float x;
	private float y;
	private PImage image;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	Spieler(PImage h){
		image = h;
	} // Constructor
	

	public void drawing(PGraphics g) {
		g.image(image,x,y);
		
	}

	public void update(PApplet p) {
		y= place_y; 
		x= p.mouseX ;
		drawing(p.g);

	}
	
}
