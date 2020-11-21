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
		try {
			Document doc = Jsoup.connect(url).get();
			String imgSrc = doc.select("meta[property=og:image]").first().attr("content");
			String user = System.getProperty("user.name");
			URL source = new URL(imgSrc);
			InputStream in = source.openStream();
			Files.copy(in, Paths.get("C:/Users/" + user + "/Pictures/Saved Pictures/" + fileName + ".jpg"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
