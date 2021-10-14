package PisProjekt;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Zombie_2  {
	//+++++++++++++++++++++++++++++++++++++Variable Declaration++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private float positionX= 500;
	private float positionY= -2000;
	private float move = 4;
	
	private float x;
	private float y;
	private PImage bild;
	private PApplet g;

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	// Constructor
	Zombie_2(PImage h,PApplet p){
		bild = h;
	putZombie_2(p);
		
      }
	public void putZombie_2(PApplet p) {
		x= p.random(positionX);
		y= p.random(positionY);

	}

	
	public void moving(PApplet p) {
		y=y+move;
		if(y>positionX) {
			putZombie_2(p);
		}
	}
	public float gibx() {
		return x;
	}
	public float giby() {
		return y;
	}

	public void drawing(PGraphics g) {
		g.image(bild, x,y);
		
	}

	public void update(PApplet p) {
		drawing(p.g);
		moving(p);
		
	}
	
}
