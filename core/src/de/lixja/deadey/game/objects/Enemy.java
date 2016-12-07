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

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import de.lixja.deadey.game.utils.GameUpdater;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Enemy extends GameObject {

    private Vector2 speed;
    private boolean left;

    private Rectangle rect;
    private float time;

    private GameUpdater gu;

    public Enemy(float x, float y, int width, int height, GameUpdater gu) {
        super(x, y, width, height);
        speed = new Vector2(25, 0);
        rect = new Rectangle(x, y, width, height);
        this.gu = gu;
    }

    public void update(float delta) {
        if (gu.getPlayer().getPosition().x < position.x) {
            position.x -= speed.x * delta;
            left = true;
        } else {
            position.x += speed.x * delta;
            left = false;
        }
        if (time > 10) {
            position.x = Float.parseFloat("" + (Math.random() * 300) + 300);
            time = 0;
        }

        time += delta;
    }

    public float getTime() {
        return time;
    }

    public boolean isLeft() {
        return left;
    }

}
