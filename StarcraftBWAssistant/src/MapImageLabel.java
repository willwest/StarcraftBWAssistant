
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Dustin Dannenhauer
 * @email dtd212@lehigh.edu
 */
public class MapImageLabel extends JLabel {

    private BufferedImage mapImage;

    public MapImageLabel(String filename) {
        loadImage(filename);
    }

    public void loadImage(String filename) {
        try {
            //mapImage = ImageIO.read(new File(filename).getAbsoluteFile());
            this.setIcon(new ImageIcon(new File(filename).getAbsoluteFile().toString()));
            System.out.println("Load the map image just fine");
            System.out.println("Absolute path of fn is "+new File(filename).getAbsoluteFile());
        } catch (Exception ex) {
            Logger.getLogger(MapImageLabel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        this.setIcon(new ImageIcon(mapImage));
        super.paintComponent(grphcs);
    }
}
