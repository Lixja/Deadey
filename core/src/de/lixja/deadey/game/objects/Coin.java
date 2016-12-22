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

import com.badlogic.gdx.physics.box2d.BodyDef;
import de.lixja.deadey.game.utils.GameUpdater;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Coin extends GameObject {

    private float time;
    public final static String OBJECTID = "coin";

    public Coin(float x, float y, int width, int height, GameUpdater gu) {
        super(x, y, width, height, OBJECTID, BodyDef.BodyType.StaticBody, gu);
    }

    @Override
    public void collisionWith(GameObject object) {
        markForDelete();
        gu.removeCoin(this);
    }


    public void update(float delta) {
        time += delta;
    }

    public float getTime() {
        return time;
    }

}
