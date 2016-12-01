package de.lixja.deadey.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.lixja.deadey.Deadey;

public class DesktopLauncher {
	public static void main (String[] arg) {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "Deadey";
            config.width = 800;
            config.height = 800;
            new LwjglApplication(new Deadey(), config);
	}
}
