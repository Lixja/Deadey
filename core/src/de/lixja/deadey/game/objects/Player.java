/*
 * Copyright (C) 2016 Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.lixja.deadey.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import de.lixja.deadey.game.handler.CollisionHandler;
import de.lixja.deadey.game.utils.AssetLoader;
import de.lixja.deadey.game.utils.GameUpdater;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Player extends GameObject {

    private Vector2 speed;
    private boolean moving = false;
    private boolean left = false;
    private boolean fire = false;
    private boolean fly = false;
    private boolean fall = false;
    private boolean portal = false;
    private boolean canMoveNorth = true;
    private boolean canMoveEast = true;
    private boolean canMoveSouth = true;
    private boolean canMoveWest = true;

    private float time;
    private float portalreloader;
    private float shotloader;
    private float flypower = 1f;

    private int points;
    private GameUpdater gu;
    public final static String OBJECTID = "player";

    public Player(float x, float y, int width, int height, GameUpdater gu) {
        super(x, y, width, height, OBJECTID);
        speed = new Vector2(250, 180);
        this.gu = gu;
        points = 0;
    }

    public void update(float delta) {
        moving = false;
        fire = false;
        fly = false;
        width = AssetLoader.player_stands.getRegionWidth();

        if (portalreloader >= 5) {
            portal = true;
            portalreloader = 0;
        }

        //Moves right;
        if (Gdx.input.isKeyPressed(Keys.D) && canMoveEast) {
            movePlayer(speed.x * delta, 0);
            moving = true;
            left = false;
            width = AssetLoader.player_left.getRegionWidth();

            // Moves Left;
        } else {
            if (Gdx.input.isKeyPressed(Keys.A) && canMoveWest) {
                movePlayer(-(speed.x * delta), 0);
                moving = true;
                left = true;
                width = AssetLoader.player_left.getRegionWidth();
                //Shoots
            } else {
                if (Gdx.input.isKeyPressed(Keys.SPACE)) {
                    fire = true;
                    shotloader += delta;
                    if (shotloader >= AssetLoader.player_fire_left.getAnimationDuration() - 0.15f) {
                        shotloader = 0;
                        gu.createShot(left);
                    }
                } else {
                    shotloader = 0;
                }
            }
        }
        //Flys

        if (Gdx.input.isKeyPressed(Keys.W) && flypower > 0) {
            if (canMoveNorth) {
                movePlayer(0, -(speed.y * delta));
            }
            fly = true;
            flypower -= delta;
        } else {
            if (canMoveSouth) {
                movePlayer(0, speed.y * delta);
            }
        }
        //Portal
        if (Gdx.input.isKeyPressed(Keys.Q) && portal) {
            float teleX = position.x - Float.parseFloat("" + Math.random() * (300 - width));
            float teleY = Float.parseFloat("" + Math.random() * 100 + 50) - position.y;
            movePlayer(teleX, teleY);
            portal = false;
        }
        canMoveEast = true;
        canMoveSouth = true;
        canMoveNorth = true;
        canMoveWest = true;
        portalreloader += delta;
        time += delta;
    }

    @Override
    public void collisionWith(GameObject object) {
        if (object.getId().equals(Coin.OBJECTID)) {
            points += 10;
        }
    }

    @Override
    public void collisionWithFrom(GameObject object, String direction) {
        if (object.getId().equals(Block.OBJECTID)) {
        if (direction.equals(CollisionHandler.EAST)) {
            canMoveWest = false;
            movePlayer((position.x - object.getPosition().x + object.getWidth() + 1), 0);
        } else {
            if (direction.equals(CollisionHandler.SOUTH)) {
                canMoveSouth = false;
                movePlayer(0, object.getPosition().y - height - 1 - position.y);
                reloadFlyPower();
            } else {
                if (direction.equals(CollisionHandler.WEST)) {
                    canMoveEast = false;
                    movePlayer((position.x - object.getPosition().x - width - 1), 0);
                } else {
                    if (direction.equals(CollisionHandler.NORTH)) {
                        canMoveNorth = false;
                        movePlayer(0, (object.getPosition().y + object.getHeight() + 1 - position.y));
                    }
                }
            }
            }
        } else
            if (object.getId().equals(Block.WIN_BLOCK)) {
                gu.won();
        }
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isFire() {
        return fire;
    }

    public boolean isFly() {
        return fly;
    }

    public float getTime() {
        return time;
    }

    private void movePlayer(float x, float y) {
        position.x += x;
        position.y += y;
        gu.getCamera().moveCamera(x, 0);
    }

    private void reloadFlyPower() {
        flypower = 1f;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

}
