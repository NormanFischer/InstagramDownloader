package app;

import java.io.IOException;
import java.util.Scanner;

import model.ImageDownloader;
import view.View;

public class Controller {
	private boolean isOpen;

	public Controller() {
		isOpen = true;
	}

	public void init() throws IOException {
		ImageDownloader id = new ImageDownloader();
		while(isOpen) {
			Scanner scan = new Scanner(System.in);
			View.promptURL();
			String urlInput = scan.nextLine();
			View.promptFileName();
			String miscInput = scan.nextLine();
			View.downloadVisual(urlInput);
			id.downloadImage(urlInput, miscInput);
			if(id.getIsDownloaded()) {
				View.success(urlInput, miscInput);
			}
			View.promptDownloadMore();
			miscInput = scan.nextLine();
			if(!miscInput.equals("Y")) {
				isOpen = false;
				scan.close();
			}
		}
	}
}