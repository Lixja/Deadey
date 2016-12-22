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
public class EnemyBird extends GameObject {

    private boolean left;
    private boolean canMoveWest = true;
    private float time;

    public final static String OBJECTID = "enemy_bird";

    public EnemyBird(float x, float y, int width, int height, GameUpdater gu) {
        super(x, y, width, height, OBJECTID, BodyDef.BodyType.DynamicBody, gu);
        speed = new Vector2(350, 250);
    }

    public void update(float delta) {
        if (canMoveWest) {
            left = true;
            this.move(true, false, false, false, delta);
            position.x -= speed.x * delta;
        } else {
            left = false;
            this.move(false, true, false, false, delta);
        }
        fightAgainstGravity();
        updateBody();
        time += delta;
    }

    public void collisionWith(GameObject object) {
        if (object.getId().equals(Shot.OBJECTID)) {
            die();
        } else if (object.getId().equals(Block.OBJECTID) || (object.getId().equals(Block.DESTROYABLE_BLOCK))) {
            if (canMoveWest) {
                canMoveWest = false;
            } else {
                canMoveWest = true;
            }
        }
    }

    public void die() {
        position.x = startPosition.x;
        position.y = startPosition.y;
        markForDelete();
        gu.removeEnemyBird(this);
    }

    public float getTime() {
        return time;
    }

    public boolean isLeft() {
        return left;
    }

}
