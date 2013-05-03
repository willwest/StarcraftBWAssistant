/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.apache.jena.atlas.lib.Pair;

/**
 *
 * @author dtdannen
 */
public class TextOverlay extends JLabel {

    private BufferedImage image;
    //public int newImageCount = 0;
    
    public TextOverlay(ArrayList<Pair<Integer, Integer>> contestedCoords,
            ArrayList<Pair<Integer, Integer>> battleCoords, int mapHeight, int mapWidth, ArrayList<Pair<Integer, Integer>> myUnits, ArrayList<Pair<Integer, Integer>> enemyUnits) {

        BufferedImage imageRGB = null;

        try {

            //image = new BufferedImage(540, 405,
            //BufferedImage.TYPE_INT_RGB);
            image = ImageIO.read(new File(
                    "heartbreakridge.jpg"));


            imageRGB = new BufferedImage(image.getWidth(),
                    image.getHeight(), BufferedImage.TYPE_INT_RGB);

            imageRGB.setData(image.getData());




            //
            //image = ImageIO.read(new File(
            //        "heartbreakridge.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setPreferredSize(new Dimension(
                imageRGB.getWidth(), imageRGB.getHeight()));
        process(imageRGB, contestedCoords, battleCoords, mapHeight, mapWidth, myUnits, enemyUnits);
    }

    private void process(BufferedImage old, ArrayList<Pair<Integer, Integer>> contestedCoords,
            ArrayList<Pair<Integer, Integer>> battleCoords, int mapHeight, int mapWidth, ArrayList<Pair<Integer, Integer>> myUnits, ArrayList<Pair<Integer, Integer>> enemyUnits) {
        int w = old.getWidth();
        int h = old.getHeight();

        double heightScale = mapHeight / h;
        double widthScale = mapWidth / w;

        //BufferedImage img = new BufferedImage(
        //         w, h, BufferedImage.TYPE_INT_ARGB);
        BufferedImage img = old;
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(old, 0, 0, null);
        g2d.setPaint(Color.green);
        // draw green dots where our units are
        for (Pair<Integer, Integer> u : myUnits) {
            int adjustedX = (int) ((double) u.car() / heightScale);
            int adjustedY = (int) ((double) u.cdr() / widthScale);
            g2d.drawRect(adjustedX, adjustedY, 2, 2);
        }

        // draw red dots where enemy units are
        g2d.setPaint(Color.red);
        // draw green dots where our units are
        if (enemyUnits != null) {
            for (Pair<Integer, Integer> u : enemyUnits) {
                int adjustedX = (int) ((double) u.car() / heightScale);
                int adjustedY = (int) ((double) u.cdr() / widthScale);
                g2d.drawRect(adjustedX, adjustedY, 2, 2);
            }
        }
        // draw red exclamantion marks for each battle region
        g2d.setPaint(Color.red);
        g2d.setFont(new Font("Serif", Font.BOLD, 24));
        String battleStr = "!!!";
        FontMetrics fm = g2d.getFontMetrics();
        for (Pair<Integer, Integer> b : battleCoords) {
            int adjustedX = (int) ((double) b.car() / heightScale);
            int adjustedY = (int) ((double) b.cdr() / widthScale);
            g2d.drawString(battleStr, adjustedX, adjustedY);
        }

        // now draw yellow question marks for contesed (but not battle) regions
        g2d.setPaint(Color.yellow);
        g2d.setFont(new Font("Serif", Font.BOLD, 24));
        battleStr = "???";
        fm = g2d.getFontMetrics();
        for (Pair<Integer, Integer> c : contestedCoords) {
            if (!battleCoords.contains(c)) {
                int adjustedX = (int) ((double) c.car() / heightScale);
                int adjustedY = (int) ((double) c.cdr() / widthScale);
                g2d.drawString(battleStr, adjustedX, adjustedY);
            }
        }

        //int x = img.getWidth() - fm.stringWidth(s) - 5;
        //int y = fm.getHeight();
        //g2d.drawString(s, x, y);
        g2d.dispose();

        File outputfile = new File("heartbreakRidgeNew.jpg");
        try {
            ImageIO.write(img, "jpg", outputfile);
            //return img;
        } catch (IOException ex) {
            Logger.getLogger(TextOverlay.class.getName()).log(Level.SEVERE, null, ex);
        }

//        final BufferedImage finalImg = img;
//
//        JFrame f = new JFrame();
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        class meh extends JPanel {
//
//            public meh() {
//                this.setPreferredSize(new Dimension(
//                        image.getWidth(), image.getHeight()));
//                image = finalImg;
//            }
//        }
//        f.add(new meh());
//        f.pack();
//        f.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            image = ImageIO.read(new File(
                        "C:\\Users\\dustin\\Dropbox\\StarcraftBWAssistantParserBranch\\StarcraftBWAssistant\\heartbreakRidgeNew.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(TextOverlay.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawImage(image, 0, 0, null);
        
    }

    private static void create()
    {
        
        System.out.println("calling create()");
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.add(new TextOverlay());
        f.pack();
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                create();
            }
        });
    }
}
