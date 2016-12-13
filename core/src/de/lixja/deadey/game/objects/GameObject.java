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
public class GameObject {

    protected Vector2 position;
    protected Vector2 startPosition;
    protected int width;
    protected int height;
    protected String id;


    public GameObject(float x, float y, int width, int height, String id) {
        position = new Vector2(x, y);
        startPosition = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public void collisionWith(GameObject object) {

    }

    public void collisionWithFrom(GameObject object, String direction) {

    }

    public Vector2 getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
