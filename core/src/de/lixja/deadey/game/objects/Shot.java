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
import com.badlogic.gdx.physics.box2d.BodyDef;
import de.lixja.deadey.game.utils.GameUpdater;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Shot extends GameObject {

    private boolean toLeft;
    private float time;
    public final static String OBJECTID = "shot";

    public Shot(float x, float y, int width, int height, GameUpdater gu) {
        super(x, y, width, height, OBJECTID, BodyDef.BodyType.DynamicBody, gu);
        speed = new Vector2(100, 0);
        fixture.setSensor(true);
    }

    public void update(float delta) {
        if (toLeft) {
            this.move(true, false, false, false, delta);
        } else {
            this.move(false, true, false, false, delta);
        }
        fightAgainstGravity();
        updateBody();
        time += delta;
    }

    @Override
    public void collisionWith(GameObject object) {
        if (object.getId().equals(EnemyBird.OBJECTID)) {
            gu.getPlayer().addPoints(100);
        } else if (object.getId().equals(EnemyAntiPlayer.OBJECTID)) {
                gu.getPlayer().addPoints(50);
        }
        if (!(object.getId().equals(Player.OBJECTID)) && !(object.getId().equals(Shot.OBJECTID))) {
            this.markForDelete();
            gu.removeShot(this);
        }
    }
    public boolean isToLeft() {
        return toLeft;
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
