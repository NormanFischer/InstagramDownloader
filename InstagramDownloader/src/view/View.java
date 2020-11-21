package view;

public class View {
	public View(){}
	
	public static void promptURL() {
		System.out.print("Please enter an Instagram URL: ");
	}
	
	public static void promptDownloadMore() {
		System.out.println("Download another image? (Y/N)");
	}
	
	public static void promptFileName() {
		System.out.print("Please enter a file name: ");
	}
	
	public static void downloadVisual(String url) {
		System.out.println("Attempting to download image from: " + url);
	}
	
	public static void success(String url, String fileName) {
		System.out.println("Image file: " + fileName + " successfully downloaded from " + url);
	}
}
