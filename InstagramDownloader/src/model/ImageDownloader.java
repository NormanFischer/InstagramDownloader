package model;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ImageDownloader {
	
	private boolean isDownloaded;
	
	public ImageDownloader(){
		isDownloaded = false;
	}


	public void downloadImage (String url, String fileName) throws IOException, IllegalArgumentException {
		Document doc;
		String src;
		String fileType = "";
		URL source;
		if(!url.startsWith("https://www.instagram.com/")) {
			System.out.println("Please use a link from https://www.instagram.com");
			return;
		}
		try {
			doc = Jsoup.connect(url).get();
			src = getSrc(doc);
			source = new URL(src);
			InputStream in = source.openStream();
			String user = System.getProperty("user.name");
			if(src.contains(".jpg")) {
				fileType = ".jpg";
			} else if(src.contains(".mp4")) {
				fileType = ".mp4";
			} else {
				System.out.println("Error: Invalid file type");
				return;
			}
			Files.copy(in, Paths.get("C:/Users/" + user + "/Pictures/Saved Pictures/" + fileName + fileType));
		} catch(IllegalArgumentException e) {
			System.out.println("Error: Not a valid URL");
			return;
		}
		isDownloaded = true;
	}

private static String getSrc(Document doc) {
	String sourceURL;
	try {
		sourceURL = doc.select("meta[property=og:video:secure_url]").first().attr("content");
	} catch (Exception e) {
		sourceURL = doc.select("meta[property=og:image]").first().attr("content");
	}
	return sourceURL;
}

public boolean getIsDownloaded() {
	return isDownloaded;
}
}
