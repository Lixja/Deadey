package de.lixja.deadey;

import com.badlogic.gdx.Game;
import de.lixja.deadey.game.screens.GameScreen;
import de.lixja.deadey.game.utils.AssetLoader;

public class Deadey extends Game {

    private int GameWidth = 240;
    private int GameHeight = 135;

    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new GameScreen(this));
    }

    public int getGameWidth() {
        return GameWidth;
    }

    public int getGameHeight() {
        return GameHeight;
    }

}
