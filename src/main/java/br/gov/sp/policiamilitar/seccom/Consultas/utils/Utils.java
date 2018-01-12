package br.gov.sp.policiamilitar.seccom.Consultas.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Utils {

    public static byte[] redimensionaImg(byte[] image, int new_w, int new_h) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(image);
            BufferedImage imagem = ImageIO.read(bis);
            BufferedImage new_img = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, new_w, new_h, null);
            g.dispose();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( new_img, "png", baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            return imageInByte;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
