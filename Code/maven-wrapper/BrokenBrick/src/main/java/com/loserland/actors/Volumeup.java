package com.loserland.actors;
import com.loserland.configs.Config;
import com.loserland.configs.ConfigFactory;
import com.loserland.context.GameContext;
import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
/**
 * Write a description of class VOLUME here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Volumeup extends Actor implements ScoreObserver
{
    private Config config = ConfigFactory.getInstance().getConfig(GameContext.GAME_DEFAULT_CONFIG_FILENAME);
    private GreenfootImage button = new GreenfootImage(config.get(GameContext.VOLUME_UP_IMG));
    private int WIDTH = config.get(Integer.class, GameContext.VOLUME_SIZE);
    private int HEIGHT = config.get(Integer.class, GameContext.VOLUME_SIZE);
     private int volume = config.get(Integer.class, GameContext.VOLUME_DEFAULT);

    long lastAdded;

    public Volumeup()
    {
        button.scale(WIDTH,HEIGHT);
        // display on screen
        setImage(button);
        // Add your action code here.
    }    
    
    public void act(){   
        if (Greenfoot.mouseClicked(this) ){
            setImage(button);       
            GreenfootImage show_volume = new GreenfootImage(""+volume, 20, Color.WHITE, new Color(0, 0, 0, 0));
            lastAdded = System.currentTimeMillis();
            setImage(show_volume);
        }
        
        if (System.currentTimeMillis() >= lastAdded + 500){
                setImage(button); 
        }

    }
    
       // updates the VOLUME display
    public void update(int v)
    {      
        volume = v;
    }
}
