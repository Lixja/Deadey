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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.lixja.deadey.game.objects.Map;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class MapRenderer {

    ShapeRenderer shapeRenderer;

    public MapRenderer(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
    }

    public void render(Map map) {
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.BLACK);
        for (int i = 0; i < map.getMap().size(); i++) {
            for (int i2 = 0; i2 < map.getMap().get(i).size(); i2++) {
                if (map.getMap().get(i).get(i2).getId() == 0) {
                    shapeRenderer.end();
                    shapeRenderer.begin();
                    shapeRenderer.setColor(Color.BLACK);
                    shapeRenderer.rect(map.getMap().get(i).get(i2).getPosition().x, map.getMap().get(i).get(i2).getPosition().y, map.getMap().get(i).get(i2).getHeight(), map.getMap().get(i).get(i2).getHeight());
                } else {
                    shapeRenderer.end();
                    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                    shapeRenderer.setColor(Color.BLACK);
                    shapeRenderer.rect(map.getMap().get(i).get(i2).getPosition().x, map.getMap().get(i).get(i2).getPosition().y, map.getMap().get(i).get(i2).getHeight(), map.getMap().get(i).get(i2).getHeight());
                }
            }
        }
        shapeRenderer.end();
    }

}
