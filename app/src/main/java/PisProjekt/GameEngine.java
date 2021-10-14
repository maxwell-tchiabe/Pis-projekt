package PisProjekt;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.stream.*;


public  class GameEngine implements Interface{



    private int min = 10;
    private  String name;
    private  Integer summe=0;
    private boolean badPlayer=false;
    private boolean resetBall=false;
    private  boolean start=false;
    private boolean gameOver = false;
    private  int anzahl=0;


    @Override
    public int getMin()
    {
        return min;
    }

    @Override
    public String setName(String value)
    {
        return name=value;
    }

    @Override
    public Integer setSumme(Integer value)
    {
        return summe=value;
    }

    @Override
    public Integer getSumme()
    {
        return summe;
    }

    @Override
    public boolean setBadPlayer(boolean value)
    {
        return badPlayer=value;
    }

    @Override
    public boolean getBadPlayer()
    {
        return badPlayer;
    }

    @Override
    public boolean setResetBall(boolean value)
    {
        return resetBall=value;
    }

    @Override
    public boolean getResetBall()
    {
        return resetBall;
    }

    @Override
    public boolean setGameOver(boolean value)
    {
        return gameOver=value;
    }

    @Override
    public boolean getGameOver()
    {
        return gameOver;
    }

    @Override
    public boolean getStart()
    {
        return start;
    }

    @Override
    public boolean setStart()
    {
        return start=true;
    }

    @Override
    public int  setAnzahl(int value)
    {
        return anzahl=value;
    }

    @Override
    public int  getAnzahl()
    {
        return anzahl;
    }

    @Override
    public  int summeOfEnemy(List<Integer> array)
    {
        return array
                .stream()
                .reduce(0, (x,y) -> x + y);
    }

    @Override
    public  String  nameOfSpieler(List<String> array)
    {

        return  array
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining());

    }

    @Override
    public boolean isBadPlayer(List<Integer> array ,int value)
    {
        Integer max= 0;
        max = array
                .stream()
                .sorted()
                .max(Integer::compare)
                .get();
        if ( max< value)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    @Override
    public boolean isGameOver(float value)
    {
       // boolean a=false;
        if(value> 490) {

            return true;
        }
        else
            {
            return false;
        }
    }
    @Override
    public String   toString(String message)
    {

        return  message;
    }
    @Override
    public boolean isKollision(float zombieX, float zombieY,float ballX,float ballY)
    {

        if(ballY < zombieY + 50 && ballY > zombieY - 50 && ballX < zombieX + 50 && ballX> zombieX - 50) {
            return true;
        }
        else
            {
            return false;
        }
    }
    @Override
    public int count_zombie(Object value, int size)
    {
        int pos=0;
        for (int i = 0; i < size; i++) {
         pos++;

        }

        return pos;
    }



    @Override
    public boolean resetBall(float ballY, int value)
    {
        if (ballY<value)
        {
            return true;
        }
        else
            {
            return false;
        }
    }

}