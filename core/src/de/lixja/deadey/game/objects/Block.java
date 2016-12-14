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
public class Block extends GameObject {

    public final static String OBJECTID = "block";
    public final static String WIN_BLOCK = "win_block";
    public final static String DESTROYABLE_BLOCK = "destroy_block";
    private Map map;

    public Block(float x, float y, int width, int height, int id, Map map) {
        super(x, y, width, height, OBJECTID);
        switch (id) {
            case 1:
                this.id = OBJECTID;
                break;
            case 2:
                this.id = DESTROYABLE_BLOCK;
                break;
            case 369:
                this.id = WIN_BLOCK;
                break;

        }
        this.map = map;
    }

    public void collides(GameObject object, int x, int y) {
        if (id == DESTROYABLE_BLOCK) {
            if (object.getId().equals(Shot.OBJECTID)) {
                map.removeFromMap(x, y);
            }
        }
    }

}
