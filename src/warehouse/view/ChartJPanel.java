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
import warehouse.dao.ProductDAO;
import warehouse.dao.StockDAO;
import warehouse.dao.StockInDAO;
import warehouse.dao.StockOutDAO;
import warehouse.entity.Stock;
import warehouse.entity.StockIn;
import warehouse.entity.StockOut;

public class ChartJPanel extends javax.swing.JPanel {

    public ChartJPanel() {
        initComponents();
        mostImportedProduct();
        mostSellProduct();
        statictisProduct();
        chart.start();
    }

    StockInDAO stockInDAO = new StockInDAO();
    ProductDAO productDAO = new ProductDAO();
    StockOutDAO stockOutDAO = new StockOutDAO();

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

        chart.clear();
        chart.addLegend("Income", new Color(245, 189, 135));
        chart.addLegend("Expense", new Color(135, 189, 245));
        chart.addLegend("Profit", new Color(189, 135, 245));
        chart.addLegend("Cost", new Color(139, 229, 222));

        Set<UUID> allProductIds = new HashSet<>();
        allProductIds.addAll(mapIn.keySet());
        allProductIds.addAll(mapOut.keySet());
        allProductIds.addAll(mapStock.keySet());

        for (UUID productId : allProductIds) {
            int inQty = mapIn.getOrDefault(productId, 0);
            int outQty = mapOut.getOrDefault(productId, 0);
            int stockQty = mapStock.getOrDefault(productId, 0);

            int totalHandled = inQty;
            double percentSold = (totalHandled == 0) ? 0 : (outQty * 100.0 / totalHandled);

            String name = productDAO.getNameByUUID(productId);

            chart.addData(new ModelChart(
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

    private void mostImportedProduct() {
        List<StockIn> list = stockInDAO.selectAll();
        pieChart1.clearData();
        pieChart1.setChartType(PieChart.PeiChartType.DONUT_CHART);

        Map<UUID, Integer> mapQuantity = new HashMap<>();
        for (StockIn s : list) {
            if (s.getStatus() == EStatus.ACCEPT) {
                mapQuantity.put(
                        s.getIdProduct(),
                        mapQuantity.getOrDefault(s.getIdProduct(), 0) + s.getQuantity()
                );
            }
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
            String name = productDAO.getNameByUUID(idProduct);
            Color color = colors[colorIndex % colors.length];
            pieChart1.addData(new ModelPieChart(name, quantity, color));
            colorIndex++;
        }
    }

    private void mostSellProduct() {
        List<StockOut> list = stockOutDAO.selectAll();

        pieChart2.clearData();
        pieChart2.setChartType(PieChart.PeiChartType.DONUT_CHART);

        Map<UUID, Integer> mapQuantity = new HashMap<>();
        for (StockOut s : list) {
            if (s.getStatus() == EStatus.ACCEPT) {
                mapQuantity.put(
                        s.getIdProduct(),
                        mapQuantity.getOrDefault(s.getIdProduct(), 0) + s.getQuantity()
                );
            }
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
            String name = productDAO.getNameByUUID(idProduct);
            Color color = colors[colorIndex % colors.length];
            pieChart2.addData(new ModelPieChart(name, quantity, color));
            colorIndex++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chart = new warehouse.component.chart.barchart.Chart();
        pieChart1 = new warehouse.component.chart.piechart.PieChart();
        pieChart2 = new warehouse.component.chart.piechart.PieChart();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Most Stock Out Product");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Most Import Product");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(211, 211, 211))
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pieChart2, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pieChart2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private warehouse.component.chart.barchart.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private warehouse.component.chart.piechart.PieChart pieChart1;
    private warehouse.component.chart.piechart.PieChart pieChart2;
    // End of variables declaration//GEN-END:variables
}
