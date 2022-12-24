package data;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Texture extends ImageIcon{
    private String filePath;

    public Texture(String filePath) {
        this.filePath = filePath;
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException | NullPointerException e) {
            System.out.println("File " + filePath + " doesn't exist");
        }
        setImage(image);
    }

    public String getTexturePath()
    {
        return filePath;
    }

}
