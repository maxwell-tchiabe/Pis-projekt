package PisProjekt;


import processing.sound.*;
import processing.core.PApplet;
import processing.core.PFont;
import java.util.ArrayList;
import java.util.List;

public class ZombieShooter extends PApplet {



	public static void main(String[] args) {
		PApplet.runSketch(new String[] {""}, new ZombieShooter());
		}

//+++++++++++++++++++++++++++++++++++++++++++Variable Declaration+++++++++++++++++++++++++++++++++++
    private 	SoundFile file;
	private SoundFile hit;


	private int place_y = 450;
	private  int a =5;
	private  int b=150;
	private  int posX =180;
	private  int posY=110;


	//private boolean ball= true;
	private float ball_y= place_y;
	private float point_x=25;
	private float point_y=25;
	private float time_x = 280;
	private float time_y = 25;
	private float timeEnd_x = 5;
	private float timeEnd_y = 110;
	private PFont zigBlack;


	private float time= 50;
	private  int anzahl;
	private int zombie_1;
	private  int zombie_2;


	private boolean moveUp= false; // ball starts
	
	

	private List<Zombie_1> Zombie_1Array;
	private Spieler spieler;
	private List<Zombie_2> Zombie_2Array;
	private Background background;


	// instance von GameEngine
	Interface gameEngine = new GameEngine();

	public void settings() {
		size(500,500);
	}

	public void setup() {

//+++++++++++++++++++++++++++++++++++++++++++++++++++ Initialisation von Variablen +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		file = new SoundFile(this, "smb_world_clear.wav");
		hit = new SoundFile(this, "explosion.mp3");
		file.loop();
		size(500, 500);

		zigBlack = createFont("Ziggurat-Black", 25);
		textFont(zigBlack);
		fill(0);


		noCursor();
		imageMode(CENTER);


		background = new Background(loadImage("backgrund2.png"));
		spieler = new Spieler(loadImage("R2D2-icon (1).png"));


			Zombie_2Array = new ArrayList<>(List.of(new Zombie_2(loadImage("Grim-Reaper-icon.png"),this),new Zombie_2(loadImage("Grim-Reaper-icon.png"),this)));

			Zombie_1Array = new ArrayList<>(List.of(new Zombie_1(loadImage("Diablo-icon.png"),this),new Zombie_1(loadImage("Diablo-icon.png"),this)));


			zombie_1=0;
			zombie_2=0;


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	}


	public void draw() {
		background.drawing(this.g);

		movingBall(); //benutzung der tastatur für die bewegung der ball

		spieler.update(this);

		startGame();
		fill(245,245,245);
		text(gameEngine.toString(" WELCOME !!! "), posX, posY);
		fill(499, 654, 76);
		text(gameEngine.toString(" PRESS 'DOWN' KEY TO START .......... "), a, b);


		if (gameEngine.getStart()) {

			if (time > 0) {
				a=-1000;
				b=-1000;
				posX=-1000;
				posY=-1000;
// ++++++++++++++++++++++++++++ Bewegung und Eingenschaften von Zombie_2 und mit Hilfe von GameEngine +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				for (int i = 0; i < Zombie_2Array.size(); i++) {
					Zombie_2Array.get(i).update(this);
					//zähle ich hier die Anzahl von Zombie;
					System.out.println("[Anzahl von Zombie_2 im Array]=>"+ gameEngine.setAnzahl(gameEngine.count_zombie(Zombie_2Array, Zombie_2Array.size())));


					if (gameEngine.setGameOver(gameEngine.isGameOver(Zombie_2Array.get(i).giby()))) {
						time = 0;
					}
					// überprüfe ich hier ob es eine kollision gibt.
					if (gameEngine.isKollision(Zombie_2Array.get(i).gibx(), Zombie_2Array.get(i).giby(), mouseX, ball_y)) {
						Zombie_2Array.get(i).putZombie_2(this);
						hit.play();
						zombie_2++;
					}
				}
//++++++++++++++++++++++++++ Bewegung und Eingenschaften von Zombie_1 und  mit Hilfe von GameEngine +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				for (int i = 0; i < Zombie_1Array.size(); i++) {
					Zombie_1Array.get(i).update(this);
					//zähle ich hier die Anzahl von Zombie;
					System.out.println("[Anzahl von Zombie_1 im Array]=>"+ gameEngine.setAnzahl(gameEngine.count_zombie(Zombie_1Array, Zombie_1Array.size())));

					//
					if (gameEngine.setGameOver(gameEngine.isGameOver(Zombie_1Array.get(i).giby()))) {
						time = 0;

					}

					// überprüfe ich hier ob es eine kollision gibt
					if (gameEngine.isKollision(Zombie_1Array.get(i).gibx(), Zombie_1Array.get(i).giby(), mouseX, ball_y)) {
						Zombie_1Array.get(i).putZombie_1(this);
						hit.play();
						zombie_1 += 1;
					}
				}

//++++++++++++++++ Darstellung von texte und mit Hilfe von GameEngine +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				fill(255, 0, 0);
				text(gameEngine.toString(" Zombie2= ") + zombie_1, point_x, 50);
				fill(193, 205, 205);
				text(gameEngine.toString(" Zombie1= ") + zombie_2, point_x, point_y);

				fill(499, 654, 76);
				putTime();
				text(gameEngine.toString(" Zeit: ") + time, time_x, time_y);
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

			} else {
//+++++++++++++++++++++++++++++++++++++++Darstellung von text am Ende des spiels und  mit Hilfe von GameEngine++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

				fill(245,245,245);
				text(gameEngine.toString(" ++++++++++GAMEOVER+++++++++++ "), timeEnd_x, timeEnd_y);

				// hier prüfe ich ob die summe alle getroffene Zombie <= das min ist
				if (gameEngine.setBadPlayer(gameEngine.isBadPlayer(new ArrayList<>(List.of(zombie_1, zombie_2, gameEngine.setSumme(gameEngine.summeOfEnemy(new ArrayList<>(List.of(zombie_1, zombie_2)))))), gameEngine.getMin()))) {
					text("  $ BAD PLAYER  ", 150, 140);

					fill(499, 654, 76);
					text(gameEngine.toString(" +++++++++++++++++++++++++++++ "), 30, 190);
					text(" PRESS 'DOWN' KEY TO CONTINUE... ", 30, 210);
					text(gameEngine.toString(" +++++++++++++++++++++++++++++ "), 30, 230);
					startGame();
				} else {
					fill(245,245,245);
					text(gameEngine.toString("$Congratulations!!!!!  "),150 , 140);
					text(gameEngine.setName(gameEngine.nameOfSpieler(new ArrayList<>(List.of(" $ Overview ", " of ", " Players:  ")))), 30, 210);
					text(gameEngine.toString("$   Zombie1: ") + zombie_1 + gameEngine.toString(" hit   "), 30, 240);
					text(gameEngine.toString("$  Zombie2: ") + zombie_2 + gameEngine.toString(" hit    "), 30, 290);
					text(gameEngine.toString("$    total :") + gameEngine.setSumme(gameEngine.summeOfEnemy(new ArrayList<>(List.of(zombie_1, zombie_2)))) + gameEngine.toString(" hit    "), 30, 330);

					fill(499, 654, 76);
					text(gameEngine.toString(" +++++++++++++++++++++++++++++ "), 30, 360);
					text(" PRESS 'DOWN' KEY TO CONTINUE... ", 30, 390);
					text(gameEngine.toString(" +++++++++++++++++++++++++++++ "), 30, 420);
					startGame();

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				}
			}

		}
	}


		public void putTime() {
		time = time - (1/ frameRate);
	}

	    public  void  startGame(){
		 if (keyPressed && keyCode == DOWN) {

			 gameEngine.setStart();
			time=50;
		 }
	 }



	public  void  movingBall(){

		ellipse(mouseX, ball_y, 30, 30); // ball initialisation

		if (keyPressed && key == ' ') {
			moveUp = true;

		}


		if (moveUp == true) {
			ball_y = ball_y - 10;
		}


		if (gameEngine.setResetBall(gameEngine.resetBall(ball_y, 10))) {
			ball_y = place_y;
			moveUp = false;
		}
	}

}

	


