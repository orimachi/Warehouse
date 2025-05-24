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

        chart.clear();
        chart.addLegend("Income", new Color(245, 189, 135));
        chart.addLegend("Expense", new Color(135, 189, 245));
        chart.addLegend("Profit", new Color(189, 135, 245));
        chart.addLegend("Cost", new Color(139, 229, 222));

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
        StockInDAO stockInDAO = new StockInDAO();
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
            String name = String.valueOf(idProduct);  // Hàm lấy tên sản phẩm
            Color color = colors[colorIndex % colors.length];
            pieChart1.addData(new ModelPieChart(name, quantity, color));
            colorIndex++;
        }
    }

    private void mostSellProduct() {
        StockOutDAO stockOutDAO = new StockOutDAO();
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
            String name = idProduct.toString();
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
        pieChart3 = new warehouse.component.chart.piechart.PieChart();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(pieChart3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(pieChart2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(pieChart2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(pieChart3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private warehouse.component.chart.barchart.Chart chart;
    private warehouse.component.chart.piechart.PieChart pieChart1;
    private warehouse.component.chart.piechart.PieChart pieChart2;
    private warehouse.component.chart.piechart.PieChart pieChart3;
    // End of variables declaration//GEN-END:variables
}
