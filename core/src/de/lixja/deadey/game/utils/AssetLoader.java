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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class AssetLoader {

    private static Texture texture;
    static TextureRegion player_stands, player_left, player_right;
    static TextureRegion player_runs_left_regions[], player_runs_right_regions[];
    static Animation player_runs_left, player_runs_right;

    public static void load() {
        texture = new Texture("Texture.png");
        player_stands = new TextureRegion(texture, 0, 0, 17, 29);
        player_stands.flip(false, true);
        player_left = new TextureRegion(texture, 17, 0, 13, 29);
        player_left.flip(true, true);
        player_right = new TextureRegion(texture, 17, 0, 13, 29);
        player_right.flip(false, true);
        player_runs_left_regions = new TextureRegion[2];
        player_runs_left_regions[0] = new TextureRegion(texture, 30, 0, 13, 29);
        player_runs_left_regions[0].flip(true, true);
        player_runs_left_regions[1] = new TextureRegion(texture, 43, 0, 13, 29);
        player_runs_left_regions[1].flip(true, true);
        player_runs_left = new Animation(0.05f, player_runs_left_regions);
        player_runs_right_regions = new TextureRegion[2];
        player_runs_right_regions[0] = new TextureRegion(texture, 30, 0, 13, 29);
        player_runs_right_regions[0].flip(false, true);
        player_runs_right_regions[1] = new TextureRegion(texture, 43, 0, 13, 29);
        player_runs_right_regions[1].flip(false, true);
        player_runs_right = new Animation(0.05f, player_runs_right_regions);
    }

    public static void dispose() {
        texture.dispose();
    }
}
