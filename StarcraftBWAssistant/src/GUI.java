/**
 * @author Kostas Hatalis
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
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class GUI extends javax.swing.JFrame {

    public static boolean hasStarted = false;
    private String mapImageFilename = "heartbreakridge.jpg";
    private JLabel mapImageLabel;
    ChartPanel cp;
    
    public GUI() {// Creates new form GUI
        initComponents();
        //mapImageLabel.setVisible(true);
        //mapImagePanel.setVisible(true);
        //System.out.println("added the image");
    } 

    // this updates the image of the map
    public void update() {
        GUIInterface gui = BWAPIParser.initiateParser();
        updateEnemyAbilities(gui);
        updateRegionStates(gui); 
        updateProgressBar(gui);
    }
    
    /* Creates a the pie chart dataset */
    private PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Structures: "+40+"%", 40);
        result.setValue("Ground Units: "+51+"%", 51); // Change this to be realtime!
        result.setValue("Flying Units: "+9+"%", 9);
        return result; 
    }
    
    /* Create pie a chart */
    private JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart3D(
                title,dataset,true,true,false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;  
    }
        
    /* Set the created pie chart to an internal Jframe */
    private void display(){
        PieDataset dataset = createDataset();
        JFreeChart chart   = createChart(dataset,"Player 1 Units");
        ChartPanel cp = new ChartPanel(chart);
        this.jInternalFrame1.setContentPane(cp);
        this.jInternalFrame1.pack();
        this.jInternalFrame1.setVisible(true);
        this.jInternalFrame1.setSize(100, 100);
        this.pack();
        this.setVisible(true);
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
        jInternalFrame1 = new javax.swing.JInternalFrame();
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

        marine.setText("Marine");

        firebat.setText("Firebat");

        medic.setText("Medic");

        ghost.setText("Ghost");

        tank.setText("Tank");

        vulture.setText("Vultures");

        goliath.setText("Goliath");

        wraith.setText("Wraith");

        dropship.setText("Dropship");

        valkyrie.setText("Valkyrie");

        scienceVessel.setText("Science Vessel");

        battleCruiser.setText("BattleCruiser");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Ground");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Air");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Opponent Can Build");

        supplyDepot.setText("Supply Depot");

        refinery.setText("Refinery");

        commandCenter.setText("Command Center");

        barracks.setText("Barracks");

        factor.setText("Factory");

        starport.setText("Starport");

        engbay.setText("Eng Bay");

        academy.setText("Academy");

        armory.setText("Armory");

        machineshop.setText("Machine Shop");

        controltower.setText("Control Tower");

        comsat.setText("Comsat Station");

        turret.setText("Missile Turret");

        bunker.setText("Bunker");

        science.setText("Science Facility");

        physicslab.setText("Physics Lab");

        covertops.setText("Covert Ops");

        nuclearsilo.setText("Nuclear Silo");

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

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
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
                        .addGap(0, 41, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jInternalFrame1)
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Action button, updates map info, enemy info, and player statistics
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        display(); // updates pie chart of players stats
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
        //stringList = (ArrayList<String>) Arrays.asList(new String[]{"missleturret","engineeringbay","supplydepot","factory",
        //"barrcks","bunker","commandCenter","armory","starport","refinery","sciencefaciliy","academy"});
        ResultSet results = gui.queryEnemyAbilities();
        // jTextPane1.setText("Importing Ontology...\n");
        while (results.hasNext()) {
            QuerySolution soln = results.nextSolution();
            //System.out.println(soln);
            String matchTrain = "canTrain";
            String matchBuild = "canBuild";
            if (soln.getResource("p").toString().contains(matchTrain.subSequence(0, matchTrain.length()))) {
                //jTextArea1.append(soln.getResource("o").toString().replace(GUIInterface.NS, "").replace("Avaliable", "") + "\n");
                Color c = new Color(0, 206, 209);
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
                Color c = new Color(0, 206, 209);
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

    // Keeps track o player and enemy positions on map and overlays them too to the GUI
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
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        // then draw all contested coords not in battle coords
        // EWWWW - hardcoded map height and width, only works on heartbreak ridge
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
        //BufferedImage img = new BufferedImage(
        //         w, h, BufferedImage.TYPE_INT_ARGB);
        BufferedImage img = origImage;
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(origImage, 0, 0, null);
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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
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
    private javax.swing.JInternalFrame jInternalFrame1;
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
