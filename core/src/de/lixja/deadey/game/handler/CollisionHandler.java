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
package de.lixja.deadey.game.handler;

import de.lixja.deadey.game.objects.Block;
import de.lixja.deadey.game.objects.GameObject;
import de.lixja.deadey.game.objects.Map;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class CollisionHandler {

    public final static String EAST = "east";
    public final static String WEST = "west";
    public final static String NORTH = "north";
    public final static String SOUTH = "south";

    public boolean update(GameObject go1, GameObject go2) {
        if (go1.getPosition().x <= go2.getPosition().x + go2.getWidth()) {
            if (go1.getPosition().x + go1.getWidth() >= go2.getPosition().x) {
                if (go1.getPosition().y + go1.getHeight() >= go2.getPosition().y) {
                    if (go1.getPosition().y <= go2.getPosition().y + go2.getHeight()) {
                        go1.collisionWidth(go2);
                        go2.collisionWidth(go1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean colidesWidthBlock(Map map, GameObject go) {
        for (LinkedList<Block> rows : map.getMap()) {
            for (Block block : rows) {
                if (go.getPosition().y + go.getHeight() >= block.getPosition().y) {
                    if (go.getPosition().y < block.getPosition().y) {
                        if (go.getPosition().x <= block.getPosition().x + block.getWidth()) {
                            if (go.getPosition().x + go.getWidth() >= block.getPosition().x) {
                                go.collisionWidthFrom(block, SOUTH);
                            }

                        }
                    }
                }

                if (go.getPosition().x <= block.getPosition().x + block.getWidth()) {
                    if (go.getPosition().x > block.getPosition().x) {
                        if (go.getPosition().y + go.getHeight() >= block.getPosition().y) {
                            if (go.getPosition().y <= block.getPosition().y + block.getHeight()) {
                                go.collisionWidthFrom(block, EAST);
                            }
                        }
                    }
                }
                if (go.getPosition().x + go.getWidth() >= block.getPosition().x) {
                    if (go.getPosition().x < block.getPosition().x) {
                        if (go.getPosition().y + go.getHeight() >= block.getPosition().y) {
                            if (go.getPosition().y <= block.getPosition().y + block.getHeight()) {
                                go.collisionWidthFrom(block, WEST);
                            }
                        }
                    }
                }
                if (go.getPosition().y <= block.getPosition().y + block.getHeight()) {
                    if (go.getPosition().y > block.getPosition().y) {
                        if (go.getPosition().x <= block.getPosition().x + block.getWidth()) {
                            if (go.getPosition().x + go.getWidth() >= block.getPosition().x) {
                                go.collisionWidthFrom(block, NORTH);
                            }

                        }
                    }
                }

            }
        }
        return false;
    }

}
