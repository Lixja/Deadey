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
package de.lixja.deadey.game.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import de.lixja.deadey.game.utils.GameUpdater;

/**
 *
 * @author Dimitrios Diamantidis &lt;Dimitri.dia@ledimi.com&gt;
 */
public class GameObject {

    protected Vector2 position;
    protected Vector2 startPosition;
    protected Vector2 speed;
    protected Body body;

    protected int width;
    protected int height;
    protected String id;

    protected World world;
    protected GameUpdater gu;

    protected boolean toDelete;
    protected Fixture fixture;


    public GameObject(float x, float y, int width, int height, String id, BodyType type, GameUpdater gu) {
        position = new Vector2(x, y);
        startPosition = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.id = id;
        this.world = gu.getWorld();
        this.gu = gu;
        toDelete = false;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set((x + width / 2) / GameUpdater.PPM, (y + height / 2) / GameUpdater.PPM);
        body = world.createBody(bodyDef);
        body.setUserData(id);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((width / 2) / GameUpdater.PPM, (height / 2) / GameUpdater.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0f;
        fixtureDef.friction = 1f;
        fixture = body.createFixture(fixtureDef);
        gu.addGameObject(this);
    }

    public GameObject(float x, float y, int width, int height, float speedX, float speedY, String id, BodyType type, GameUpdater gu) {
        this(x, y, width, height, id, type, gu);
        this.speed = new Vector2(speedX, speedY);
    }

    public void updateBody() {
        position.x = (body.getPosition().x * GameUpdater.PPM) - width / 2;
        position.y = (body.getPosition().y * GameUpdater.PPM) - height / 2;
    }

    public void move(boolean xLeft, boolean xRight, boolean yUp, boolean yDown, float delta) {
        if (xLeft) {
            body.applyForceToCenter(-speed.x * delta * GameUpdater.PPM, 0, true);
        }
        if (xRight) {
            body.applyForceToCenter(speed.x * delta * GameUpdater.PPM, 0, true);
        }
        if (yUp) {
            body.applyForceToCenter(0, -speed.y * delta * GameUpdater.PPM, true);
        }
        if (yDown) {

        }
    }

    public void moveTo(float x, float y) {
        position.x = x;
        position.y = y;
        setBodyPosition(x, y);
    }

    public void fightAgainstGravity() {
        body.applyForceToCenter(0, -9.8f, true);
    }

    protected void markForDelete() {
        toDelete = true;
    }

    public boolean isToDelete() {
        return toDelete;
    }

    public void collisionWith(GameObject object) {

    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        body = body;
    }

    protected void setBodyPosition(float x, float y) {
        body.getPosition().x = (x + width / 2) / GameUpdater.PPM;
        body.getPosition().y = (y + height / 2) / GameUpdater.PPM;
    }


    public Vector2 getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getId() {
        return id;
    }




}
