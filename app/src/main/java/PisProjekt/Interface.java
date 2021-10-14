package PisProjekt;
import java.util.List;



public interface Interface {


    public int getMin();
    public String setName(String value);
    public Integer setSumme(Integer value);
    public Integer getSumme();
    public boolean setBadPlayer(boolean value);
    public boolean getBadPlayer();
    public boolean setResetBall(boolean value);
    public boolean getResetBall();
    public boolean getStart();
    public boolean setStart();
    public boolean setGameOver(boolean value);
    public boolean getGameOver();
    public int  setAnzahl(int value);
    public int  getAnzahl();


    public boolean isKollision(float zombieX, float zombieY,float ballX,float ballY);
    boolean isGameOver(float value);
    public String    toString( String message);
    public int count_zombie(Object value, int size);
    public  String  nameOfSpieler(List<String> name);
    public  int summeOfEnemy(List<Integer> array);
    public boolean isBadPlayer(List<Integer> array ,int value);
    public boolean resetBall(float ballY, int value);

}
