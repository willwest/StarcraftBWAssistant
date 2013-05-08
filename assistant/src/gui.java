/**
 * Author: Kostas Hatalis
 * Filename: Unit.java
 * Class: CSE428 - Semantic Web
 * Assignment: Final Project
 * Description:	Class representation of the GUI - also contains the 
 * functionality to redraw the map image and update what units the enemy can
 * build/train.
*/

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.apache.jena.atlas.lib.Pair;
import org.jfree.chart.ChartPanel;

public class gui extends javax.swing.JFrame {

    public static boolean hasStarted = false;
    private String mapImageFilename = "heartbreakridge.jpg";
    private JLabel mapImageLabel;
    ChartPanel cp;
    
    public gui() {// Creates new form GUI
        initComponents();
    } 

    // this updates the image of the map
    public void update() {
        GUIInterface gui = BWAPIParser.initiateParser();
        updateEnemyAbilities(gui);
        updateRegionStates(gui); 
        updateProgressBar(gui);
    }
    
    // WARNING: Do NOT modify this code. 
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jDialog1 = new javax.swing.JDialog();
        jFrame1 = new javax.swing.JFrame();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        marine = new javax.swing.JLabel();
        firebat = new javax.swing.JLabel();
        medic = new javax.swing.JLabel();
        ghost = new javax.swing.JLabel();
        tank = new javax.swing.JLabel();
        vulture = new javax.swing.JLabel();
        goliath = new javax.swing.JLabel();
        wraith = new javax.swing.JLabel();
        dropship = new javax.swing.JLabel();
        valkyrie = new javax.swing.JLabel();
        scienceVessel = new javax.swing.JLabel();
        battleCruiser = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        supplyDepot = new javax.swing.JLabel();
        refinery = new javax.swing.JLabel();
        commandCenter = new javax.swing.JLabel();
        barracks = new javax.swing.JLabel();
        factor = new javax.swing.JLabel();
        starport = new javax.swing.JLabel();
        engbay = new javax.swing.JLabel();
        academy = new javax.swing.JLabel();
        armory = new javax.swing.JLabel();
        machineshop = new javax.swing.JLabel();
        controltower = new javax.swing.JLabel();
        comsat = new javax.swing.JLabel();
        turret = new javax.swing.JLabel();
        bunker = new javax.swing.JLabel();
        science = new javax.swing.JLabel();
        physicslab = new javax.swing.JLabel();
        covertops = new javax.swing.JLabel();
        nuclearsilo = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        unitsHealthProgressBar = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        mapImageLabel = new JLabel(new ImageIcon(new File(mapImageFilename).getAbsolutePath()));
        mapImagePanel = new javax.swing.JPanel();
        mapImageLabelPlz = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setResizable(false);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 0));
        jButton2.setText("UPDATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Map:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Opponent Can Train");

        marine.setForeground(new java.awt.Color(204, 204, 204));
        marine.setText("Marine");
        marine.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        marine.setFocusable(false);

        firebat.setForeground(new java.awt.Color(204, 204, 204));
        firebat.setText("Firebat");
        firebat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        firebat.setFocusable(false);

        medic.setForeground(new java.awt.Color(204, 204, 204));
        medic.setText("Medic");
        medic.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        medic.setFocusable(false);

        ghost.setForeground(new java.awt.Color(204, 204, 204));
        ghost.setText("Ghost");
        ghost.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ghost.setFocusable(false);

        tank.setForeground(new java.awt.Color(204, 204, 204));
        tank.setText("Tank");
        tank.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tank.setFocusable(false);

        vulture.setForeground(new java.awt.Color(204, 204, 204));
        vulture.setText("Vultures");
        vulture.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        vulture.setFocusable(false);

        goliath.setForeground(new java.awt.Color(204, 204, 204));
        goliath.setText("Goliath");
        goliath.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        goliath.setFocusable(false);

        wraith.setForeground(new java.awt.Color(204, 204, 204));
        wraith.setText("Wraith");
        wraith.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        wraith.setFocusable(false);

        dropship.setForeground(new java.awt.Color(204, 204, 204));
        dropship.setText("Dropship");
        dropship.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dropship.setFocusable(false);

        valkyrie.setForeground(new java.awt.Color(204, 204, 204));
        valkyrie.setText("Valkyrie");
        valkyrie.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        valkyrie.setFocusable(false);

        scienceVessel.setForeground(new java.awt.Color(204, 204, 204));
        scienceVessel.setText("Science Vessel");
        scienceVessel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        scienceVessel.setFocusable(false);

        battleCruiser.setForeground(new java.awt.Color(204, 204, 204));
        battleCruiser.setText("BattleCruiser");
        battleCruiser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        battleCruiser.setFocusable(false);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Ground");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Air");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Opponent Can Build");

        supplyDepot.setForeground(new java.awt.Color(204, 204, 204));
        supplyDepot.setText("Supply Depot");
        supplyDepot.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        supplyDepot.setFocusable(false);

        refinery.setForeground(new java.awt.Color(204, 204, 204));
        refinery.setText("Refinery");
        refinery.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        refinery.setFocusable(false);

        commandCenter.setForeground(new java.awt.Color(204, 204, 204));
        commandCenter.setText("Command Center");
        commandCenter.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        commandCenter.setFocusable(false);

        barracks.setForeground(new java.awt.Color(204, 204, 204));
        barracks.setText("Barracks");
        barracks.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        barracks.setFocusable(false);

        factor.setForeground(new java.awt.Color(204, 204, 204));
        factor.setText("Factory");
        factor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        factor.setFocusable(false);

        starport.setForeground(new java.awt.Color(204, 204, 204));
        starport.setText("Starport");
        starport.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        starport.setFocusable(false);

        engbay.setForeground(new java.awt.Color(204, 204, 204));
        engbay.setText("Eng Bay");
        engbay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        engbay.setFocusable(false);

        academy.setForeground(new java.awt.Color(204, 204, 204));
        academy.setText("Academy");
        academy.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        academy.setFocusable(false);

        armory.setForeground(new java.awt.Color(204, 204, 204));
        armory.setText("Armory");
        armory.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        armory.setFocusable(false);

        machineshop.setForeground(new java.awt.Color(204, 204, 204));
        machineshop.setText("Machine Shop");
        machineshop.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        machineshop.setFocusable(false);

        controltower.setForeground(new java.awt.Color(204, 204, 204));
        controltower.setText("Control Tower");
        controltower.setFocusable(false);

        comsat.setForeground(new java.awt.Color(204, 204, 204));
        comsat.setText("Comsat Station");
        comsat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        comsat.setFocusable(false);

        turret.setForeground(new java.awt.Color(204, 204, 204));
        turret.setText("Missile Turret");
        turret.setFocusable(false);

        bunker.setForeground(new java.awt.Color(204, 204, 204));
        bunker.setText("Bunker");
        bunker.setFocusable(false);

        science.setForeground(new java.awt.Color(204, 204, 204));
        science.setText("Science Facility");
        science.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        science.setFocusable(false);

        physicslab.setForeground(new java.awt.Color(204, 204, 204));
        physicslab.setText("Physics Lab");
        physicslab.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        physicslab.setFocusable(false);

        covertops.setForeground(new java.awt.Color(204, 204, 204));
        covertops.setText("Covert Ops");
        covertops.setFocusable(false);

        nuclearsilo.setForeground(new java.awt.Color(204, 204, 204));
        nuclearsilo.setText("Nuclear Silo");
        nuclearsilo.setFocusable(false);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("Addons");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("Infastructure");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("Research");

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Defensive");

        jLabel2.setText("Army Overall Health:");

        mapImagePanel.add(mapImageLabel);
        mapImagePanel.setVisible(true);

        mapImageLabelPlz.setIcon(new javax.swing.ImageIcon("heartbreakridge.jpg"));

        javax.swing.GroupLayout mapImagePanelLayout = new javax.swing.GroupLayout(mapImagePanel);
        mapImagePanel.setLayout(mapImagePanelLayout);
        mapImagePanelLayout.setHorizontalGroup(
            mapImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapImageLabelPlz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        mapImagePanelLayout.setVerticalGroup(
            mapImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapImageLabelPlz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(unitsHealthProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))))
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mapImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(firebat)
                                        .addComponent(medic))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(vulture)
                                        .addComponent(tank)
                                        .addComponent(goliath))
                                    .addGap(16, 16, 16)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(valkyrie)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(marine)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scienceVessel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(wraith, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(dropship, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(ghost)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(battleCruiser))
                                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bunker)
                                        .addComponent(jLabel42)
                                        .addComponent(turret)
                                        .addComponent(commandCenter)
                                        .addComponent(supplyDepot)
                                        .addComponent(refinery))
                                    .addGap(8, 8, 8)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(barracks)
                                                .addComponent(factor))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(academy, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(engbay, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(starport)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(armory))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(controltower)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(nuclearsilo))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(machineshop)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(covertops))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(comsat)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(physicslab)))
                                            .addGap(6, 6, 6))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(jLabel38))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(science)
                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(wraith)
                            .addComponent(marine)
                            .addComponent(vulture)))
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unitsHealthProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dropship)
                            .addComponent(firebat)
                            .addComponent(tank))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(scienceVessel)
                            .addComponent(medic)
                            .addComponent(goliath))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(battleCruiser)
                            .addComponent(ghost))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valkyrie)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(commandCenter)
                            .addComponent(barracks)
                            .addComponent(academy))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(factor)
                            .addComponent(engbay)
                            .addComponent(supplyDepot))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(starport)
                            .addComponent(armory)
                            .addComponent(refinery))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(science)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel42))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(bunker)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(turret))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comsat)
                                    .addComponent(physicslab))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(machineshop)
                                    .addComponent(covertops))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(controltower)
                                    .addComponent(nuclearsilo)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(mapImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Action button, updates map info, enemy info, and player statistics
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        update(); // updates map and players movements and enemy stats
    }//GEN-LAST:event_jButton2ActionPerformed

    // This method calls to the ontology to infer he possibile units an enemy can build based on seen units
    public void updateEnemyAbilities(GUIInterface gui) {
        String[] words = {"missleturret", "engineeringbay", "supplydepot", "factory",
            "barrcks", "bunker", "commandCenter", "armory", "starport", "refinery", "sciencefaciliy", "academy"};
        ArrayList<String> stringList = new ArrayList<String>(words.length);
        for (String s : words) {
            stringList.add(s);
        }
        
        ResultSet results = gui.queryEnemyAbilities();
        
        while (results.hasNext()) {
            QuerySolution soln = results.nextSolution();
            
            String matchTrain = "canTrain";
            String matchBuild = "canBuild";
            if (soln.getResource("p").toString().contains(matchTrain.subSequence(0, matchTrain.length()))) {
                
                Color c = new Color(0,0,0);
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("marine")) {
                    marine.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("firebat")) {
                    firebat.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("medic")) {
                    medic.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("ghost")) {
                    ghost.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("vulture")) {
                    vulture.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("tank")) {
                    tank.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("goliath")) {
                    goliath.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("wraith")) {
                    wraith.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("dropship")) {
                    dropship.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("scienceVessel")) {
                    scienceVessel.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("battleCruiser")) {
                    battleCruiser.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("valkyrie")) {
                    valkyrie.setForeground(c);
                }
            }
            if (soln.getResource("p").toString().contains(matchBuild.subSequence(0, matchBuild.length()))) {
                Color c = new Color(0, 0, 0);
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("factory")) {
                    factor.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("engineeringBay")) {
                    engbay.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("supplyDepot")) {
                    supplyDepot.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("commandCenter")) {
                    commandCenter.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("bunker")) {
                    bunker.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("armory")) {
                    armory.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("academy")) {
                    academy.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("refinery")) {
                    refinery.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("scienceFacility")) {
                    science.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("starport")) {
                    starport.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("missleTurret")) {
                    turret.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("comsatStation")) {
                    comsat.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("machineShop")) {
                    machineshop.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("controlTower")) {
                    controltower.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("physicsLab")) {
                    physicslab.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("nuclearSilo")) {
                    nuclearsilo.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("covertOps")) {
                    covertops.setForeground(c);
                }
                if (soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Available", "").equals("barracks")) {
                    barracks.setForeground(c);
                }                
            }
        }
    }

    // Keeps track of player and enemy positions on map and overlays them too to the GUI
    public void updateRegionStates(GUIInterface gui) {
        ResultSet resultsC = gui.queryContestedRegions();
        // For each region that is controlled by a player
        // find that regions centerX and centerY values, scale them,
        // and create a new image that writes the players name over that region.
        // Keep a running data structure that maps region x,y pairs to players 
        // that control them.
        // The pair of ints represents the x and y of the region and the
        // value int represents the player id, -1 signifies no player (unknown)

        //HashMap<String,int> regionsOwnedByPlayers = new HashMap<String, int>();
        ArrayList<Pair<Integer, Integer>> contestedCoords = new ArrayList<Pair<Integer, Integer>>();
        while (resultsC.hasNext()) {
            QuerySolution soln = resultsC.nextSolution();
            // add the coord of the result to the list of contested regions coords
            contestedCoords.add(new Pair(soln.getLiteral("centerX").getInt(),
                    soln.getLiteral("centerY").getInt()));
        }
        
        // now do same thing but for battle regions
        ResultSet resultsB = gui.queryBattleRegions();
        ArrayList<Pair<Integer, Integer>> battleCoords = new ArrayList<Pair<Integer, Integer>>();
        while (resultsB.hasNext()) {
            QuerySolution soln = resultsB.nextSolution();
            // add the coord of the result to the list of contested regions coords
            battleCoords.add(new Pair(soln.getLiteral("centerX").getInt(),
                    soln.getLiteral("centerY").getInt()));
        }
        // draw all battle coords
        BufferedImage mapImageOriginal = null;
        try {
            // load in the original map
            mapImageOriginal = ImageIO.read(new File("heartbreakridge.jpg"));
        } catch (IOException ex) {
            System.out.append("Could not read original map image from file");
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        }

        // then draw all contested coords not in battle coords
        // heartbreakridge
        int mapHeight = 96 * 32;
        int mapWidth = 128 * 32;
        ResultSet myUnitsResults = gui.queryMyUnits();
        ArrayList<Pair<Integer, Integer>> myUnits = new ArrayList<Pair<Integer, Integer>>();    
        
        while (myUnitsResults.hasNext()) {
            QuerySolution soln = myUnitsResults.nextSolution();
            // add the coord of the result to the list of contested regions coords
            myUnits.add(new Pair(soln.getLiteral("x").getInt(),
                    soln.getLiteral("y").getInt()));
        }      
        ResultSet enemyUnitsResults = gui.queryEnemyUnits();
        ArrayList<Pair<Integer, Integer>> enemyUnits = new ArrayList<Pair<Integer, Integer>>();      
        
        while (enemyUnitsResults.hasNext()) {
            QuerySolution soln = enemyUnitsResults.nextSolution();
            // add the coord of the result to the list of contested regions coords
            enemyUnits.add(new Pair(soln.getLiteral("x").getInt(),
                    soln.getLiteral("y").getInt()));
        }
       
        // TODO - add enemy units
        BufferedImage newMapImage = redrawMapImage(mapImageOriginal,
                contestedCoords, battleCoords, mapHeight, mapWidth, myUnits, enemyUnits);

        // now update the label
        mapImageLabelPlz.removeAll();
        mapImageLabelPlz.setIcon(new ImageIcon(newMapImage));
        mapImageLabelPlz.revalidate();
    }

    /**
     * Given all the data needed to draw the units and region data onto the map,
     * this method draws it and returns a new buffered image
     *
     * @param origImage
     * @param contestedCoords
     * @param battleCoords
     * @param mapHeight
     * @param mapWidth
     * @param myUnits
     * @param enemyUnits
     */
    private BufferedImage redrawMapImage(BufferedImage origImage, ArrayList<Pair<Integer, Integer>> contestedCoords,
            ArrayList<Pair<Integer, Integer>> battleCoords, int mapHeight, int mapWidth, ArrayList<Pair<Integer, Integer>> myUnits, ArrayList<Pair<Integer, Integer>> enemyUnits) {
        
        int w = origImage.getWidth();
        int h = origImage.getHeight();
        
        double heightScale = mapHeight / h;
        double widthScale = mapWidth / w;
        
        BufferedImage img = origImage;
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(origImage, 0, 0, null);
        g2d.setPaint(Color.green);
        
        // draw green rectangles where our units are
        for (Pair<Integer, Integer> u : myUnits) {
            int adjustedX = (int) ((double) u.car() / heightScale);
            int adjustedY = (int) ((double) u.cdr() / widthScale);
            g2d.drawRect(adjustedX, adjustedY, 2, 2);
        }
        
        // draw red rectangles where enemy units are
        g2d.setPaint(Color.red);

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
        
        // now draw yellow question marks for contested (but not battle) regions
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
        
        g2d.dispose();
        return img;
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gui().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel academy;
    private javax.swing.JLabel armory;
    private javax.swing.JLabel barracks;
    private javax.swing.JLabel battleCruiser;
    private javax.swing.JLabel bunker;
    private javax.swing.JLabel commandCenter;
    private javax.swing.JLabel comsat;
    private javax.swing.JLabel controltower;
    private javax.swing.JLabel covertops;
    private javax.swing.JLabel dropship;
    private javax.swing.JLabel engbay;
    private javax.swing.JLabel factor;
    private javax.swing.JLabel firebat;
    private javax.swing.JLabel ghost;
    private javax.swing.JLabel goliath;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel machineshop;
    private javax.swing.JLabel mapImageLabelPlz;
    private javax.swing.JPanel mapImagePanel;
    private javax.swing.JLabel marine;
    private javax.swing.JLabel medic;
    private javax.swing.JLabel nuclearsilo;
    private javax.swing.JLabel physicslab;
    private javax.swing.JLabel refinery;
    private javax.swing.JLabel science;
    private javax.swing.JLabel scienceVessel;
    private javax.swing.JLabel starport;
    private javax.swing.JLabel supplyDepot;
    private javax.swing.JLabel tank;
    private javax.swing.JLabel turret;
    private javax.swing.JProgressBar unitsHealthProgressBar;
    private javax.swing.JLabel valkyrie;
    private javax.swing.JLabel vulture;
    private javax.swing.JLabel wraith;
    // End of variables declaration//GEN-END:variables

    // Keep track of players total army health
    private void updateProgressBar(GUIInterface gui) {
        ResultSet results = gui.queryArmyHealth();
        int runningCurrHP = 0;
        int runningMaxHP = 0;
        while (results.hasNext()) {
            QuerySolution soln = results.nextSolution();
            runningCurrHP += soln.getLiteral("curr").getInt();
            runningMaxHP += soln.getLiteral("max").getInt();
        }
        unitsHealthProgressBar.setMaximum(runningMaxHP);
        unitsHealthProgressBar.setValue(runningCurrHP);
    }
}
