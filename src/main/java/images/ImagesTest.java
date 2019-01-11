package images;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.images.Eyes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Paths;

public class ImagesTest {
    public static void main(String[] args) throws IOException {

        Eyes eyes = new Eyes();

        // Initialize the eyes SDK and set your private API key.
        String applitoolsApiKey = System.getenv("APPLITOOLS_API_KEY");
        eyes.setApiKey(applitoolsApiKey);

        // Define the OS and hosting application to identify the baseline.
        eyes.setHostOS(System.getProperty("os.name"));
        eyes.setHostApp(InetAddress.getLocalHost().getHostName());

        BufferedImage img;

        try {

            // Start the test with a viewport size of 800x600.
            eyes.open("Applitools site", "Java Screenshot test!", new RectangleSize(800, 610));

            // Load page image and validate.
            img = ImageIO.read(Paths.get("resources/sampleImages/Slide1.png").toUri().toURL());

            // Visual validation.
            eyes.checkImage(img, "Contact-us page");

            // End visual UI testing.
            eyes.close();

        } finally {

            // If the test was aborted before eyes.close was called, ends the test as aborted.
            eyes.abortIfNotClosed();

        }
    }
}