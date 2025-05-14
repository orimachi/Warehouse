package warehouse.controller;

import warehouse.bean.DanhMuc;
import warehouse.view.DonViQuanLyJPanel;
import com.QLK.view.QuanLyHangHoaJPanel;
import com.QLK.view.ThongTinKhachHangJPanel;
import com.QLK.view.ThongTinNhaCungCapJPanel;
import com.QLK.view.TraCuuHangHoaJPanel;
import com.QLK.view.TrangChuJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Switchscreen {
    private JPanel main;
    private String kindSelected = "";
    private List<DanhMuc> listItem = null;

    public Switchscreen(JPanel jbnMain) {
        this.main = jbnMain;
    }
    
    public void setView(JPanel jpnItem, JLabel jlbItem) {
       kindSelected = "TrangChu";
       jpnItem.setBackground(new Color(96, 100, 191));
       jlbItem.setBackground(new Color(96, 100, 191));
       main.removeAll();
       main.setLayout(new BorderLayout());
       main.add(new TrangChuJPanel());
       main.validate();
       main.repaint();
    }
    
    public void setEvent(List<DanhMuc> listItem) {
        this.listItem = listItem;
        for (DanhMuc item : listItem) {
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
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "ThongTinNhaCungCap":
                    node = new ThongTinNhaCungCapJPanel();
                    break;
                case "DonViQuanLy":
                    node = new DonViQuanLyJPanel();
                    break;
                case "ThongTinKhachHang":
                    node = new ThongTinKhachHangJPanel();
                    break;
                case "QuanLyHangHoa":
                    node = new QuanLyHangHoaJPanel();
                    break;
                case "TraCuuHangTonKho":
                    node = new TraCuuHangHoaJPanel();
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
            for(DanhMuc item : listItem){
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
