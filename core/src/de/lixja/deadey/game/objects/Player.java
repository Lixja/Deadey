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
import com.badlogic.gdx.physics.box2d.BodyDef;
import de.lixja.deadey.game.utils.AssetLoader;
import de.lixja.deadey.game.utils.GameUpdater;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Player extends GameObject {

    private boolean moving = false;
    private boolean left = false;
    private boolean fire = false;
    private boolean fly = false;
    private boolean portal = true;

    private float time;
    private float portalreloader;
    private float shotloader;
    private float shotreloader = 0f;
    private float shotcounter = 5;
    private float flypower = 0.3f;
    private float maxFlyPower = 0.3f;

    private int points;
    public final static String OBJECTID = "player";

    public Player(float x, float y, int width, int height, GameUpdater gu) {
        super(x, y, width, height, 8, 15, OBJECTID, BodyDef.BodyType.DynamicBody, gu);
        points = 0;
    }

    public void update(float delta) {
        moving = false;
        fire = false;
        fly = false;
        width = AssetLoader.player_stands.getRegionWidth();

        if (!body.isAwake()) {
            reloadFlyPower();
        }

        if (portalreloader >= 5) {
            portal = true;
            portalreloader = 0;
        }
        if (shotreloader >= 5) {
            shotcounter = 5;
            shotreloader = 0;
        }

        //Moves right;
        if (Gdx.input.isKeyPressed(Keys.D)) {
            move(false, true, false, false, delta);
            moving = true;
            left = false;
            width = AssetLoader.player_left.getRegionWidth();
            // Moves Left;
        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            move(true, false, false, false, delta);
            moving = true;
            left = true;
            width = AssetLoader.player_left.getRegionWidth();

        }
        //Shoots
        if (Gdx.input.isKeyPressed(Keys.SPACE) && shotcounter > 0) {
            fire = true;
            shotloader += delta;
            if (shotloader >= AssetLoader.player_fire_left.getAnimationDuration() - 0.15f) {
                shotloader = 0;
                shotcounter -= 1;
                gu.createShot(left);
            }
        } else {
            shotloader = 0;
        }
        //Flys

        if (Gdx.input.isKeyPressed(Keys.W) && flypower > 0) {
            move(false, false, true, false, delta);
            fly = true;
            flypower -= delta;
        }
        //Portal
        if (Gdx.input.isKeyPressed(Keys.Q) && portal) {
            float teleX = Float.parseFloat("" + Math.random() * (300));
            float teleY = Float.parseFloat("" + Math.random() * 150);
            setBodyPosition(teleX, teleY);
            portal = false;
        }
        if (Gdx.input.isKeyPressed(Keys.L)) {
            gu.lose();
        }
        updateBody();
        gu.getCamera().setX(position.x);
        gu.getCamera().setY(position.y);

        portalreloader += delta;
        shotreloader += delta;
        time += delta;
    }

    @Override
    public void collisionWith(GameObject object) {
        if (object.getId().equals(Coin.OBJECTID)) {
            points += 10;
        } else if (object.getId().equals(Block.WIN_BLOCK)) {
            gu.won();
        } else if (object.getId().equals(Block.END_BLOCK)) {
            gu.lose();
        } else if (object.getId().equals(Block.OBJECTID) || object.getId().equals(Block.DESTROYABLE_BLOCK)) {
            reloadFlyPower();
        } else if (object.getId().equals(EnemyBird.OBJECTID) || object.getId().equals(EnemyAntiPlayer.OBJECTID)) {
            gu.lose();
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

    private void reloadFlyPower() {
        if (maxFlyPower > flypower) {
            flypower += 0.3f;
        }
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

}
