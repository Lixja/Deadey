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
import de.lixja.deadey.game.handler.CollisionHandler;
import de.lixja.deadey.game.objects.Enemy;
import de.lixja.deadey.game.objects.Player;
import de.lixja.deadey.game.objects.Shot;
import de.lixja.deadey.game.screens.GameOverScreen;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GameUpdater {

    private int GameWidth, GameHeight;
    private Deadey game;

    private Player player;
    private Enemy enemy[];
    private Shot shot;
    private CollisionHandler chandler;

    private float time;

    public GameUpdater(Deadey game) {
        this.game = game;
        this.GameWidth = game.getGameWidth();
        this.GameHeight = game.getGameHeight();
        player = new Player(50, 100, 17, 29, this);
        enemy = new Enemy[2];
        enemy[0] = new Enemy(300, 100, 17, 29, this);
        enemy[1] = new Enemy(-100, 100, 17, 29, this);
        shot = new Shot(1000, 1000, 10, 10);
        chandler = new CollisionHandler();

    }

    public void update_g(float delta) {
        player.update(delta);
        enemy[0].update(delta);
        enemy[1].update(delta);
        for (int i = 0; i < enemy.length; i++) {
            if (chandler.update(player, enemy[i])) {
            game.setScreen(new GameOverScreen());
            } else
                if (chandler.update(enemy[i], shot)) {
                    enemy[i].die();
                }
        }
        if (shot.isAvailable()) {
            shot.update(delta);
        }
        time += delta;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy[] getEnemy() {
        return enemy;
    }

    public Shot getShot() {
        return shot;
    }

    public float getTime() {
        return time;
    }

    public void createShot() {
        shot.setPosition(player.getPosition().x + (player.getWidth() / 2), player.getPosition().y + (player.getHeight() / 2));
        shot.setAvailable(true);
    }



}
