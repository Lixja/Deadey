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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Player {

    private Vector2 position;
    private Vector2 speed;
    private boolean moving = false;
    private boolean left = false;
    private boolean fire = false;

    private int width;
    private int height;

    private Rectangle rect;
    private float time;

    public Player(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
        speed = new Vector2(50, 0);
        rect = new Rectangle(x, y, width, height);
    }

    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Keys.D)) {
            position.x += speed.x * delta;
            moving = true;
            left = false;
        } else if (Gdx.input.isKeyPressed(Keys.A)) {
            position.x -= speed.x * delta;
            moving = true;
            left = true;
        } else if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            moving = false;
            fire = true;
        } else {
            moving = false;
            fire = false;
        }
        time += delta;

    }

    public Vector2 getPosition() {
        return position;
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

    public float getTime() {
        return time;
    }



}
