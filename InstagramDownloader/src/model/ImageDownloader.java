package model;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ImageDownloader {
	public ImageDownloader(){}
	

	public static boolean downloadImage(String url, String fileName) {
		if(!url.startsWith("https://www.instagram.com/")) {
			System.out.println("Invalid website \nPlease use a link from https://www.instagram.com");
			return false;
		}
		try {
			Document doc = Jsoup.connect(url).get();
			String src = getSrc(doc);
			String fileType = "";
			if(src.contains(".jpg")) {
				fileType = ".jpg";
			} else if(src.contains(".mp4")) {
				fileType = ".mp4";
			}
			URL source = new URL(src);
			InputStream in = source.openStream();
			String user = System.getProperty("user.name");
			Files.copy(in, Paths.get("C:/Users/" + user + "/Pictures/Saved Pictures/" + fileName + fileType));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
}
