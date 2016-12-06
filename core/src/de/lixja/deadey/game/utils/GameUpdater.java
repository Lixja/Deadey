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
package de.lixja.deadey.game.utils;

import de.lixja.deadey.Deadey;
import de.lixja.deadey.game.objects.Enemy;
import de.lixja.deadey.game.objects.Player;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GameUpdater {

    private int GameWidth, GameHeight;
    private Deadey game;

    private Player player;
    private Enemy enemy;

    float time;

    public GameUpdater(Deadey game) {
        this.game = game;
        this.GameWidth = game.getGameWidth();
        this.GameHeight = game.getGameHeight();
        player = new Player(50, 100, 17, 29);
    }

    public void update_g(float delta) {
        player.update(delta);
        time += delta;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public float getTime() {
        return time;
    }



}
