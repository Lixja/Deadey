package de.lixja.deadey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.Box2D;
import de.lixja.deadey.game.screens.MenuScreen;
import de.lixja.deadey.game.utils.AssetLoader;

public class Deadey extends Game {

    private int GameWidth = 320;
    private int GameHeight = 180;

    @Override
    public void create() {
        Box2D.init();
        AssetLoader.load();
        setScreen(new MenuScreen(this));
    }

    public int getGameWidth() {
        return GameWidth;
    }

    public int getGameHeight() {
        return GameHeight;
    }

}
