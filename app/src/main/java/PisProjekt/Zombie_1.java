package PisProjekt;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class Zombie_1 {


	//+++++++++++++++++++++++++++++++++++++Variable Declaration++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private float window_large= 500;
	private float start_y= -1000;
	private float move= 3;
	private float x;
	private float y;
	private PImage image;
	private PApplet g;
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	// Constructor
	Zombie_1(PImage h,PApplet p){
		image = h;
		putZombie_1(p);
	
      }
	public void putZombie_1(PApplet g) {
		x= g.random(window_large);
		y= g.random(start_y);
	}
	
	
	public void moving(PApplet p) {
		y=y+move;
		if(y>500) {
			putZombie_1(p);
		}
	}
	public float gibx() {
		return x;
	}
	public float giby() {
		return y;
	}
	
		

	public void drawing(PGraphics g) {
		g.image(image, x,y);
		
	}

	public void update(PApplet p) {
		drawing(p.g);
		moving(p);
		
	}

}
