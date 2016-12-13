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
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class Map {

    private LinkedList<LinkedList<Block>> map;
    private Vector2 playerStart;
    private LinkedList<Vector2> enemyAntiPlayerStart;
    private LinkedList<Vector2> enemyDragonStart;
    private LinkedList<Vector2> coins;

    public Map(String mapfile) {
        enemyAntiPlayerStart = new LinkedList<Vector2>();
        enemyDragonStart = new LinkedList<Vector2>();
        coins = new LinkedList<Vector2>();
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
                switch (Integer.parseInt(mapDetails[i][i2])) {
                    case 55:
                        coins.add(new Vector2(i2 * 10, i * 10));
                        break;
                    case 333:
                        playerStart = new Vector2(i2 * 10, i * 10);
                        break;
                    case 666:
                        enemyDragonStart.add(new Vector2(i2 * 10, i * 10));
                        break;
                    case 999:
                        enemyAntiPlayerStart.add(new Vector2(i2 * 10, i * 10));
                        break;
                    default:
                        map.get(i).add(new Block(i2 * 10, i * 10, 10, 10, Integer.parseInt(mapDetails[i][i2])
                        ));
                        break;
                    }
                }
            }
        }
    }

    public LinkedList<LinkedList<Block>> getMap() {
        return map;
    }

    public Vector2 getPlayerStart() {
        return playerStart;
    }

    public LinkedList<Vector2> getCoins() {
        return coins;
    }


    public LinkedList<Vector2> getEnemyAntiPlayersStart() {
        return enemyAntiPlayerStart;
    }

    public LinkedList<Vector2> getEnemyDragonStart() {
        return enemyDragonStart;
    }



}
