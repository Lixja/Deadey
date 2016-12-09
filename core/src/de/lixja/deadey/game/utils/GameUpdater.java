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
import com.badlogic.gdx.files.FileHandle;
import de.lixja.deadey.Deadey;
import de.lixja.deadey.game.handler.CollisionHandler;
import de.lixja.deadey.game.objects.Coin;
import de.lixja.deadey.game.objects.Enemy;
import de.lixja.deadey.game.objects.Map;
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
    private LinkedList<Coin> coins;
    private CollisionHandler chandler;
    private Map stage1;

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
        coins = new LinkedList<Coin>();
        Coin c = new Coin(200, 90, 9, 9);
        coins.add(c);
        shots = new LinkedList<Shot>();
        chandler = new CollisionHandler();
        FileHandle file = Gdx.files.internal("Stage1.dat");
        String map = file.readString();
        stage1 = new Map(map);

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
                        shots.remove(i1);
                    }
                }
        }
        for (Shot shot : shots) {
            if (shot.isAvailable()) {
                shot.update(delta);
            }
        }
        for (Coin c : coins) {
            c.update(delta);
        }
        chandler.colidesWidthBlock(stage1, player);
        for (int i = 0; i < enemys.size(); i++) {
            chandler.colidesWidthBlock(stage1, enemys.get(i));
        }
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        time += delta;

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

    public LinkedList<Coin> getCoins() {
        return coins;
    }

    public Map getStage1() {
        return stage1;
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
