package moonaFramework;

import org.lwjgl.glfw.GLFW;

public final class Moona {

	private static boolean isOn = false;
	
	public static boolean IsOn() {
		return isOn;
	}
	
	public static void Init() {
		isOn = true;
		if (!GLFW.glfwInit()) {
			throw new MoonaHandlingException("Moona could not be initialized.");
		}
	}
	
	private Moona() {
	}
}
