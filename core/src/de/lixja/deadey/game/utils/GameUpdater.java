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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import de.lixja.deadey.Deadey;
import de.lixja.deadey.game.handler.CollisionHandler;
import de.lixja.deadey.game.objects.Enemy;
import de.lixja.deadey.game.objects.Player;
import de.lixja.deadey.game.objects.Shot;
import de.lixja.deadey.game.screens.GameOverScreen;
import java.util.LinkedList;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GameUpdater {

    private int GameWidth, GameHeight;
    private Deadey game;

    private Player player;
    private Enemy enemy[];
    private LinkedList<Shot> shots;
    private CollisionHandler chandler;

    private float time;

    public GameUpdater(Deadey game) {
        this.game = game;
        this.GameWidth = game.getGameWidth();
        this.GameHeight = game.getGameHeight();
        player = new Player(50, 100, 17, 29, this);
        enemy = new Enemy[3];
        enemy[0] = new Enemy(300, 100, 17, 29, this);
        enemy[1] = new Enemy(330, 100, 17, 29, this);
        enemy[2] = new Enemy(360, 100, 17, 29, this);
        shots = new LinkedList<Shot>();
        chandler = new CollisionHandler();

    }

    public void update_g(float delta) {
        player.update(delta);
        for (int i = 0; i < enemy.length; i++) {
            enemy[i].update(delta);
            if (chandler.update(player, enemy[i])) {
                game.setScreen(new GameOverScreen(game));
            } else
                for (int i1 = 0; i1 < shots.size(); i1++) {
                    if (chandler.update(enemy[i], shots.get(i1)) && shots.get(i1).isAvailable()) {
                        enemy[i].die();
                        shots.remove(i1);
                    }
                }
        }
        for (int i1 = 0; i1 < shots.size(); i1++) {
            if (shots.get(i1).isAvailable()) {
                shots.get(i1).update(delta);
            }
        }
        time += delta;

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy[] getEnemy() {
        return enemy;
    }

    public LinkedList<Shot> getShots() {
        return shots;
    }

    public float getTime() {
        return time;
    }

    public void createShot(boolean left) {
        Shot shot = new Shot(1, 1, 10, 10);
        shot.setPosition(player.getPosition().x + (player.getWidth() / 2), player.getPosition().y + (player.getHeight() / 2));
        shot.setToLeft(left);
        shot.setAvailable(true);
        shots.add(shot);
    }



}
