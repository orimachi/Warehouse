/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package warehouse.view;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import warehouse.bean.EStatus;
import warehouse.component.chart.barchart.ModelChart;
import warehouse.component.chart.piechart.ModelPieChart;
import warehouse.component.chart.piechart.PieChart;
import warehouse.component.notification.MessageDialog;
import warehouse.component.pagination.PaginationItemRenderStyle1;
import warehouse.dao.ProductDAO;
import warehouse.dao.StockDAO;
import warehouse.dao.StockInDAO;
import warehouse.dao.StockOutDAO;
import warehouse.entity.Product;
import warehouse.entity.Stock;
import warehouse.entity.StockIn;
import warehouse.entity.StockOut;

/**
 *
 * @author ADMIN
 */
public class TEST extends javax.swing.JFrame {

    /**
     * Creates new form TEST
     */
    public TEST() {
        initComponents();
        statictisProduct();
        getContentPane().setBackground(new Color(250, 250, 250));
        chart2.addLegend("Income", new Color(245, 189, 135));
        chart2.addLegend("Expense", new Color(135, 189, 245));
        chart2.addLegend("Profit", new Color(189, 135, 245));
        chart2.addLegend("Cost", new Color(139, 229, 222));
        chart2.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
        chart2.addData(new ModelChart("February", new double[]{600, 750, 90, 150}));
        chart2.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
        chart2.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
        chart2.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
        chart2.addData(new ModelChart("June", new double[]{190, 280, 81, 200}));

        StockDAO stockDAO = new StockDAO();
        List<Stock> list = stockDAO.selectAll();

        pieChart.clearData();
        pieChart.setChartType(PieChart.PeiChartType.DONUT_CHART);

        Map<UUID, Integer> mapQuantity = new HashMap<>();
        for (Stock s : list) {
            mapQuantity.put(s.getIdProduct(), mapQuantity.getOrDefault(s.getIdProduct(), 0) + s.getQuantity());
        }

        Color[] colors = {
            new Color(23, 126, 238),
            new Color(221, 65, 65),
            new Color(47, 157, 64),
            new Color(196, 151, 58),
            new Color(102, 45, 145),
            new Color(255, 128, 0)
        };

        int colorIndex = 0;

        for (Map.Entry<UUID, Integer> entry : mapQuantity.entrySet()) {
            UUID idProduct = entry.getKey();
            int quantity = entry.getValue();
            String name = String.valueOf(idProduct);  
            Color color = colors[colorIndex % colors.length];  
            pieChart.addData(new ModelPieChart(name, quantity, color));
            colorIndex++;
        }
    }

     private void statictisProduct() {
        List<StockIn> stockInList = new StockInDAO().selectAll();
        List<StockOut> stockOutList = new StockOutDAO().selectAll();
        List<Stock> stockList = new StockDAO().selectAll();

        Map<UUID, Integer> mapIn = new HashMap<>();
        Map<UUID, Integer> mapOut = new HashMap<>();
        Map<UUID, Integer> mapStock = new HashMap<>();

        for (StockIn in : stockInList) {
            if (in.getStatus() == EStatus.ACCEPT) {
                mapIn.put(in.getIdProduct(), mapIn.getOrDefault(in.getIdProduct(), 0) + in.getQuantity());
            }
        }

        for (StockOut out : stockOutList) {
            if (out.getStatus() == EStatus.ACCEPT) {
                mapOut.put(out.getIdProduct(), mapOut.getOrDefault(out.getIdProduct(), 0) + out.getQuantity());
            }
        }

        for (Stock s : stockList) {
            mapStock.put(s.getIdProduct(), mapStock.getOrDefault(s.getIdProduct(), 0) + s.getQuantity());
        }

        chart1.clear();
        chart1.addLegend("Income", new Color(245, 189, 135));
        chart1.addLegend("Expense", new Color(135, 189, 245));
        chart1.addLegend("Profit", new Color(189, 135, 245));
        chart1.addLegend("Cost", new Color(139, 229, 222));

        Set<UUID> allProductIds = new HashSet<>();
        allProductIds.addAll(mapIn.keySet());
        allProductIds.addAll(mapOut.keySet());
        allProductIds.addAll(mapStock.keySet());

        ProductDAO productDAO = new ProductDAO();

        for (UUID productId : allProductIds) {
            int inQty = mapIn.getOrDefault(productId, 0);
            int outQty = mapOut.getOrDefault(productId, 0);
            int stockQty = mapStock.getOrDefault(productId, 0);

            int totalHandled = inQty;
            double percentSold = (totalHandled == 0) ? 0 : (outQty * 100.0 / totalHandled);

//    String name = productDAO.findById(productId).getName();
            String name = productId.toString();

            chart1.addData(new ModelChart(
                    name,
                    new double[]{
                        inQty, 
                        outQty, 
                        stockQty, 
                        percentSold 
                    }
            ));
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paginationItemRenderStyle11 = new warehouse.component.pagination.PaginationItemRenderStyle1();
        page1 = new warehouse.component.pagination.Page();
        page2 = new warehouse.component.pagination.Page();
        defaultPaginationItemRender1 = new warehouse.component.pagination.DefaultPaginationItemRender();
        paginationItemRenderStyle12 = new warehouse.component.pagination.PaginationItemRenderStyle1();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pieChart = new warehouse.component.chart.piechart.PieChart();
        chart1 = new warehouse.component.chart.barchart.Chart();
        chart2 = new warehouse.component.chart.barchart.Chart();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(18, 163, 24));
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chart1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(chart2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(16, 16, 16))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136)
                .addComponent(jButton2)
                .addGap(38, 38, 38)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(chart1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Product p = new Product();
        System.out.println(p);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MessageDialog obj = new MessageDialog(this);
        obj.showMessage("Delete your account ?", "All data will lose if you press ok button\nYou can restore any time within 30 days start from now.");
        if (obj.getMessageType() == MessageDialog.MessageType.OK) {
            System.out.println("User click ok");
        } else {
            System.out.println("User click cancel");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        chart2.start();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TEST().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private warehouse.component.chart.barchart.Chart chart1;
    private warehouse.component.chart.barchart.Chart chart2;
    private warehouse.component.pagination.DefaultPaginationItemRender defaultPaginationItemRender1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private warehouse.component.pagination.Page page1;
    private warehouse.component.pagination.Page page2;
    private warehouse.component.pagination.PaginationItemRenderStyle1 paginationItemRenderStyle11;
    private warehouse.component.pagination.PaginationItemRenderStyle1 paginationItemRenderStyle12;
    private warehouse.component.chart.piechart.PieChart pieChart;
    // End of variables declaration//GEN-END:variables
}
