package app;

import java.util.Scanner;

import model.ImageDownloader;
import view.View;

public class Controller {
	private boolean isOpen;

	public Controller() {
		isOpen = true;
	}

	public void init() {
		while(isOpen) {
			Scanner scan = new Scanner(System.in);
			View.promptURL();
			String urlInput = scan.nextLine();
			View.promptFileName();
			String miscInput = scan.nextLine();
			View.downloadVisual(urlInput);
			if(!ImageDownloader.downloadImage(urlInput, miscInput)) {
				System.out.println("Error downloading image");
				continue;
			}
			View.success(urlInput, miscInput);
			View.promptDownloadMore();
			miscInput = scan.nextLine();
			if(!miscInput.equals("Y")) {
				isOpen = false;
				scan.close();
			}
		}
	}
}