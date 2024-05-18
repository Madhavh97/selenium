import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageComparison {

    public static void main(String[] args) {
        String expectedImagePath = "/Users/madhavh/Desktop/imageselenium/screenshots/Desktop/1920x1080/Screenshot-20240508-175619.png";
        String actualImagePath = "/Users/madhavh/Desktop/imageselenium/screenshots/Desktop/1366x768/Screenshot-20240508-180446.png";

        try {
            BufferedImage expectedImage = ImageIO.read(new File(expectedImagePath));
            BufferedImage actualImage = ImageIO.read(new File(actualImagePath));

            int width1 = expectedImage.getWidth();
            int width2 = actualImage.getWidth();
            int height1 = expectedImage.getHeight();
            int height2 = actualImage.getHeight();

            if ((width1 != width2) || (height1 != height2)) {
                System.out.println("Images are of different dimensions. Validation failed.");
            } else {
                long difference = 0;
                for (int y = 0; y < height1; y++) {
                    for (int x = 0; x < width1; x++) {
                        int rgbExpected = expectedImage.getRGB(x, y);
                        int rgbActual = actualImage.getRGB(x, y);
                        int redDiff = (rgbExpected >> 16) - (rgbActual >> 16);
                        int greenDiff = ((rgbExpected >> 8) & 0xFF) - ((rgbActual >> 8) & 0xFF);
                        int blueDiff = (rgbExpected & 0xFF) - (rgbActual & 0xFF);
                        difference += (redDiff * redDiff) + (greenDiff * greenDiff) + (blueDiff * blueDiff);
                    }
                }

                double mse = difference / (double)(width1 * height1);
                double psnr = 10 * Math.log10((255 * 255) / mse);

                if (psnr > 30) {
                    System.out.println("Images are similar. Validation passed.");
                } else {
                    System.out.println("Images are not similar. Validation failed.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
