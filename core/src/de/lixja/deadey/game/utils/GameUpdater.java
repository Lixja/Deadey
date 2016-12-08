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
    private LinkedList<Enemy> enemys;
    private LinkedList<Shot> shots;
    private CollisionHandler chandler;

    private float time;

    public GameUpdater(Deadey game) {
        this.game = game;
        this.GameWidth = game.getGameWidth();
        this.GameHeight = game.getGameHeight();
        player = new Player(50, 100, 17, 29, this);
        enemys = new LinkedList<Enemy>();
        for (int i = 0; i < 5; i++) {
            Enemy e = new Enemy((float) (Math.random() * 300) + 300, 100, 17, 29, this);
            enemys.add(e);
        }
        shots = new LinkedList<Shot>();
        chandler = new CollisionHandler();

    }

    public void update_g(float delta) {
        player.update(delta);
        for (Enemy e : enemys) {
            e.update(delta);
            if (chandler.update(player, e)) {
                game.setScreen(new GameOverScreen(game));
            } else
                for (int i1 = 0; i1 < shots.size(); i1++) {
                    if (chandler.update(e, shots.get(i1)) && shots.get(i1).isAvailable()) {
                        e.die();
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

    public LinkedList<Enemy> getEnemy() {
        return enemys;
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
