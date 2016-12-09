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

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Shot extends GameObject {

    private Vector2 speed;
    private boolean available;
    private boolean toLeft;
    private float time;

    public Shot(float x, float y, int width, int height) {
        super(x, y, width, height, "shot");
        speed = new Vector2(100, 0);
    }

    public void update(float delta) {
        if (toLeft) {
            position.x -= speed.x * delta;
        } else {
            position.x += speed.x * delta;
        }
        time += delta;
    }

    @Override
    public void collisionWidth(GameObject object) {

    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isToLeft() {
        return toLeft;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setToLeft(boolean toLeft) {
        this.toLeft = toLeft;
    }

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public float getTime() {
        return time;
    }


}
