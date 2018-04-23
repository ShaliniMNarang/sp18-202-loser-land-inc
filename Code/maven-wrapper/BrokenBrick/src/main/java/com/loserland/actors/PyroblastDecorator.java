package com.loserland.actors;

import com.loserland.utils.GifImage;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.List;

public class PyroblastDecorator implements IBall, IBallDecorator{
    private BasicBall basicBall;
    private GreenfootImage explodeImage;

    public PyroblastDecorator() {
        explodeImage = new GifImage("explosion.gif").getImages().get(0);
    }

    @Override
    public void action() {
        if(basicBall.gifImage != null)
            basicBall.setImage(basicBall.gifImage.getCurrentImage());
        if (!basicBall.onPaddle) {
            moveBall();
            checkBallMiss();
            makeSmoke();
        }
    }

    @Override
    public void moveBall() {
        basicBall.moveBall();
    }

    @Override
    public void checkBallMiss() {
        basicBall.checkBallMiss();
    }

    @Override
    public void makeSmoke() {
        basicBall.smokeTimingCount++;
        if(basicBall.getWorld() != null) {
            if (basicBall.smokeTimingCount % basicBall.smokeFrequency == 0){
                Smoke smoke = new Smoke();
                smoke.setImage("pyroblast_smoke.png");
                basicBall.getWorld().addObject(smoke, basicBall.getX(), basicBall.getY());
            }
        }
    }

    @Override
    public void brickCollision(Brick brick) {
        int h = explodeImage.getHeight();
        int w = explodeImage.getWidth();
        int radius = h > w ? h : w;
        List<Brick> brickList = basicBall.getObjectsInRange(radius, Brick.class);

        for(Brick b:brickList) {
            b.killEffect();
        }

        if(brickList.size() > 0) {
            Explosion explosion = new Explosion();
            basicBall.getWorld().addObject(explosion, basicBall.getX(), basicBall.getY());
            basicBall.setDecorator(basicBall);
        }
    }

    @Override
    public void wallCollision() {
        basicBall.wallCollision();
    }

    @Override
    public void bounce(Actor actor) {
        basicBall.bounce(actor);
    }

    @Override
    public PowerSquareFactory.PowerType getCurrentPower() {
        return PowerSquareFactory.PowerType.PYROBLAST_BALL;
    }

    @Override
    public void assemble(IBall basicBall) {
        this.basicBall = (BasicBall)basicBall;
        this.basicBall.setDecorator(this);
        this.basicBall.setImage("pyroblast.png");
    }

    @Override
    public Actor getBall() {
        return basicBall;
    }
}