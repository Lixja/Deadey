package de.lixja.deadey.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.lixja.deadey.Deadey;

public class DesktopLauncher {
	public static void main (String[] arg) {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
            config.title = "Deadey";
            config.width = 640;
            config.height = 360;
            config.addIcon("Icon.png", Files.FileType.Internal);
            new LwjglApplication(new Deadey(), config);
	}
}
