package GUI;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

/**
 * @web http://jc-mouse.blogspot.com/
 *
 * @author Mouse
 */
public class ImagenFondo implements Border {

    BufferedImage fondo;

    public ImagenFondo(String ruta) {
        try {

            Properties propiedad = new Properties();
            propiedad.load(new FileInputStream(VeterinariaSJ.propiedades));
            if (propiedad.getProperty("fondo").equalsIgnoreCase(("null").trim())) {
                URL url = new URL(getClass().getResource(ruta).toString());
                fondo = ImageIO.read(url);
            } else {
                try {
                    fondo = ImageIO.read(new File(propiedad.getProperty("fondo")));

                } catch (Exception e) {
                   propiedad.setProperty("fondo", "null");
                   propiedad.store(new FileOutputStream(VeterinariaSJ.propiedades), null);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ImagenFondo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // se sobreescriben metodos propios de Border
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        //se dibuja la imagen de fondo en el centro del contenedor
        //cada que se redimensione el contenedor, la imagen automaticamente se posiciona en el centro   
        try {
            g.drawImage(fondo, (x + (width - fondo.getWidth()) / 2), (y + (height - fondo.getHeight()) / 2), null);

        } catch (NullPointerException e) {
            try {
                URL url = new URL(getClass().getResource("/Imagenes/fondo1.jpg").toString());
                fondo = ImageIO.read(url);
                g.drawImage(fondo, (x + (width - fondo.getWidth()) / 2), (y + (height - fondo.getHeight()) / 2), null);

            } catch (IOException ex) {
                Logger.getLogger(ImagenFondo.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}