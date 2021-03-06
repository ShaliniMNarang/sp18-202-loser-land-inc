package com.loserland.actors;

import com.loserland.configs.Config;
import com.loserland.configs.ConfigFactory;
import com.loserland.context.GameContext;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class com.loserland.actors.Smoke here.
 * 
 * University of Kent
 * 18km
 */
public class Smoke  extends Actor
{
    // declare instance variables
    private GreenfootImage image;   // the original image
    private int fade;               // the rate of fading

    private boolean shouldPause = false;
    private Config config = ConfigFactory.getInstance().getConfig(GameContext.GAME_DEFAULT_CONFIG_FILENAME);
    // smoke constructor
    public Smoke()
    {
        // initalize smoke image and fades it with each act
        setImage(config.get(GameContext.SMOKE_IMG));
        image = getImage();
        fade = Greenfoot.getRandomNumber(4) + 1;  // 1 to 3
        if (fade > 3) {
            fade = fade - 2;
        }        
    }

    public void act() 
    {
        if(shouldPause) return;
        // fades the smoke image with each act
        shrink();
    }    

    private void shrink()
    {
        // method to "fade" smoke object with each act
        if(getImage().getWidth() < 10 || (getWorld().getObjects(BasicBall.class).isEmpty() )) {
            // remove smoke when out of world
            getWorld().removeObject(this);
        }
        else {
            // "fades" in size with each act
            GreenfootImage img = new GreenfootImage(image);
            img.scale ( getImage().getWidth()-fade, getImage().getHeight()-fade );
            setImage (img);
        }        
    }

    public void setImage(String fileName) {
        super.setImage(fileName);
        image = getImage();
    }

    public void pause() {
        shouldPause = true;
    }

    public void resume() {
        shouldPause = false;
    }

}
