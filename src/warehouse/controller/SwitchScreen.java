package warehouse.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import warehouse.bean.Category;
import warehouse.view.HomePage;
import warehouse.view.WarehouseJPanel;

public class SwitchScreen {
    private JPanel main;
    private String kindSelected = "";
    private List<Category> listItem = null;

    public SwitchScreen(JPanel jbnMain) {
        this.main = jbnMain;
    }
    
    public void setView(JPanel jpnItem, JLabel jlbItem) {
       kindSelected = "TrangChu";
       jpnItem.setBackground(new Color(96, 100, 191));
       jlbItem.setBackground(new Color(96, 100, 191));
       main.removeAll();
       main.setLayout(new BorderLayout());
       main.add(new HomePage());
       main.validate();
       main.repaint();
    }
    
    public void setEvent(List<Category> listItem) {
        this.listItem = listItem;
        for (Category item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }
    
    class LabelEvent implements MouseListener {

    private JPanel node;
    private String kind;

    private JPanel jpnItem;
    private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
           this.kind = kind;
           this.jpnItem = jpnItem;
           this.jlbItem = jlbItem;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "homepage":
                    node = new HomePage();
                    break;
                case "warehouse":
                    node = new WarehouseJPanel();
                    break;
                default:
                    break;
           }
           main.removeAll();
           main.setLayout(new BorderLayout());
           main.add(node);
           main.validate();
           main.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
           kindSelected = kind;
           jpnItem.setBackground(new Color(96, 100, 191));
           jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
          jpnItem.setBackground(new Color(96, 100, 191));
          jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(76, 175, 80));
                jlbItem.setBackground(new Color(76, 175, 80));
            }
        }
    }
    
        private void setChangeBackground(String kind){
            for(Category item : listItem){
                if(item.getKind().equalsIgnoreCase(kind)){
                    item.getJpn().setBackground(new Color(96, 100, 191));
                    item.getJlb().setBackground(new Color(96, 100, 191));
                } else {
                    item.getJpn().setBackground(new Color(76, 175, 80));
                    item.getJlb().setBackground(new Color(76, 175, 80));
                }
            }
        }
    }
