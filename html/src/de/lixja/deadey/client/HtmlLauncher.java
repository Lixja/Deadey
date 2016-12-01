package de.lixja.deadey.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import de.lixja.deadey.Deadey;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
            return new GwtApplicationConfiguration(800, 480);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new Deadey();
        }
}