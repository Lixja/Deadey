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
import de.lixja.deadey.game.objects.DCamera;
import de.lixja.deadey.game.objects.EnemyAntiPlayer;
import de.lixja.deadey.game.objects.EnemyBird;
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
    private LinkedList<EnemyAntiPlayer> enemyAntiPlayers;
    private LinkedList<EnemyBird> enemyBird;
    private LinkedList<Shot> shots;
    private LinkedList<Coin> coins;
    private CollisionHandler chandler;
    private Map stage1;
    private DCamera cam;

    private float time;

    public GameUpdater(Deadey game) {
        this.game = game;
        this.GameWidth = game.getGameWidth();
        this.GameHeight = game.getGameHeight();
        FileHandle file = Gdx.files.internal("Stage1.dat");
        String map = file.readString();
        stage1 = new Map(map);
        player = new Player(stage1.getPlayerStart().x, stage1.getPlayerStart().y, 17, 29, this);
        enemyAntiPlayers = new LinkedList<EnemyAntiPlayer>();
        for (int i = 0; i < stage1.getEnemyAntiPlayersStart().size(); i++)        {
            EnemyAntiPlayer e = new EnemyAntiPlayer(stage1.getEnemyAntiPlayersStart().get(i).x, stage1.getEnemyAntiPlayersStart().get(i).y, 17, 29, this);
            enemyAntiPlayers.add(e);
        }
        enemyBird = new LinkedList<EnemyBird>();
        for (int i = 0; i < stage1.getEnemyBirdStart().size(); i++) {
            EnemyBird e = new EnemyBird(stage1.getEnemyBirdStart().get(i).x, stage1.getEnemyBirdStart().get(i).y, 32, 19, this);
            enemyBird.add(e);
        }
        coins = new LinkedList<Coin>();
        for (int i = 0; i < stage1.getCoins().size(); i++) {
            Coin c = new Coin(stage1.getCoins().get(i).x, stage1.getCoins().get(i).y, 9, 9);
            coins.add(c);
        }
        shots = new LinkedList<Shot>();
        chandler = new CollisionHandler();
        cam = new DCamera();
    }

    public void update_g(float delta) {
        player.update(delta);
        chandler.colidesWidthBlockAt(stage1, player);
        for (EnemyAntiPlayer e : enemyAntiPlayers) {
            e.update(delta);
            chandler.colidesWidthBlockAt(stage1, e);
            if (chandler.colides(player, e)) {
                lose();
            } else
                for (int i1 = 0; i1 < shots.size(); i1++) {
                    if (shots.get(i1).isAvailable()) {
                        if (chandler.colides(e, shots.get(i1))) {
                            shots.remove(i1);

                        }
                    }
                }
        }
        for (EnemyBird e : enemyBird) {
            e.update(delta);
            chandler.colidesWidthBlockAt(stage1, e);
            if (chandler.colides(player, e)) {
                lose();
            } else {
                for (int i1 = 0; i1 < shots.size(); i1++) {
                    if (shots.get(i1).isAvailable()) {
                        if (chandler.colides(e, shots.get(i1))) {
                            shots.remove(i1);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < shots.size(); i++) {
            if (shots.get(i).isAvailable()) {
                shots.get(i).update(delta);
                if (chandler.colidesWidthBlock(stage1, shots.get(i))) {
                    shots.remove(i);
                }

            }
        }
        for (int i = 0; i < coins.size(); i++) {
            coins.get(i).update(delta);
            if (chandler.colides(player, coins.get(i))) {
                coins.remove(i);
            }
        }
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        time += delta;

    }

    public Player getPlayer() {
        return player;
    }

    public LinkedList<EnemyAntiPlayer> getEnemyAntiPlayers() {
        return enemyAntiPlayers;
    }

    public LinkedList<EnemyBird> getEnemyBird() {
        return enemyBird;
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
        Shot shot = new Shot(1, 1, 10, 10, this);
        shot.setPosition(player.getPosition().x + (player.getWidth() / 2), player.getPosition().y + (player.getHeight() / 2));
        shot.setToLeft(left);
        shot.setAvailable(true);
        shots.add(shot);
    }

    public DCamera getCamera() {
        return cam;
    }

    public void won() {
        game.setScreen(new GameOverScreen(true, game));
    }

    public void lose() {
        game.setScreen(new GameOverScreen(false, game));
    }



}
