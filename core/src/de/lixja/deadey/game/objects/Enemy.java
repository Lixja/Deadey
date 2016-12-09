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
import de.lixja.deadey.game.handler.CollisionHandler;
import de.lixja.deadey.game.utils.AssetLoader;
import de.lixja.deadey.game.utils.GameUpdater;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Enemy extends GameObject {

    private Vector2 speed;
    private boolean left;
    private boolean canMoveNorth = true;
    private boolean canMoveEast = true;
    private boolean canMoveSouth = true;
    private boolean canMoveWest = true;
    private float time;

    private GameUpdater gu;

    public Enemy(float x, float y, int width, int height, GameUpdater gu) {
        super(x, y, width, height, "enemy_ground_1");
        speed = new Vector2(40, 0);
        this.gu = gu;
    }

    public void update(float delta) {
        if (gu.getPlayer().getPosition().x < position.x && canMoveEast) {
            position.x -= speed.x * delta;
            left = true;
            width = AssetLoader.enemy_left.getRegionWidth();
        } else
            if (canMoveWest) {
            position.x += speed.x * delta;
            left = false;
            width = AssetLoader.enemy_left.getRegionWidth();
        }
        if (time > 30) {
            die();
            time = 0;
        }

        canMoveEast = true;
        canMoveSouth = true;
        canMoveNorth = true;
        canMoveWest = true;
        time += delta;
    }

    public void collisionWith(GameObject object) {
        die();
    }

    @Override
    public void collisionWithFrom(GameObject object, String direction) {
        if (direction.equals(CollisionHandler.EAST)) {
            canMoveWest = false;
            position.x = object.getPosition().x + object.getWidth() + 1;
        } else {
            if (direction.equals(CollisionHandler.SOUTH)) {
                canMoveSouth = false;
                position.y = object.getPosition().y - height - 1;
            } else {
                if (direction.equals(CollisionHandler.WEST)) {
                    canMoveSouth = false;
                    position.x = object.getPosition().x - width - 1;
                } else {
                    if (direction.equals(CollisionHandler.NORTH)) {
                        canMoveSouth = false;
                        position.y = object.getPosition().y + object.getHeight() + 1;
                    }
                }
            }
        }
    }


    public void die() {
        position.x = Float.parseFloat("" + (Math.random() * 200 + 270));
    }

    public float getTime() {
        return time;
    }

    public boolean isLeft() {
        return left;
    }

}
