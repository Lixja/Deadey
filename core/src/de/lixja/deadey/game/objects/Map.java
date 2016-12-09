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

import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Map {

    private LinkedList<LinkedList<Block>> map;

    public Map(String mapfile) {
        setBlocks(mapfile);
    }

    public void setBlocks(String mapfile) {
        String mapRow[] = mapfile.split("\n");
        String mapDetails[][] = new String[mapRow.length][];
        for (int i = 0; i < mapRow.length; i++) {
            mapDetails[i] = mapRow[i].split(",");
        }
        map = new LinkedList<LinkedList<Block>>();
        for (int i = 0; i < mapDetails.length; i++) {
            map.add(new LinkedList<Block>());
            for (int i2 = 0; i2 < mapDetails[i].length; i2++) {
                if (Integer.parseInt(mapDetails[i][i2]) != 0) {
                    map.get(i).add(new Block(i2 * 10, i * 10, 10, 10, Integer.parseInt(mapDetails[i][i2])));
                }
            }
        }
    }

    public LinkedList<LinkedList<Block>> getMap() {
        return map;
    }

}
