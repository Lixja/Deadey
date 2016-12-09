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

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class DCamera {

    float toMoveX, toMoveY;
    float angle;

    public DCamera() {
    }

    public void moveCamera(float speedX, float speedY) {
        toMoveX += speedX;
        toMoveY += speedY;
    }

    public void rotateCamera(float angle) {
        this.angle += angle;
    }

    public void setRotationTo(float angle) {
        this.angle = angle;
    }

    public float getToMoveX() {
        float tmp = toMoveX;
        toMoveX = 0;
        return tmp;
    }

    public float getToMoveY() {
        float tmp = toMoveY;
        toMoveY = 0;
        return tmp;
    }


}
