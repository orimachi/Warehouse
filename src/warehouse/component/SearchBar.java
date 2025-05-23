package warehouse.component;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchBar extends JPanel {
    private final JTextField txtSearch;
    private final JLabel lblIcon;
    private ActionListener searchListener;

    public SearchBar() {
        setLayout(new BorderLayout(5, 0));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        lblIcon = new JLabel();
        try {
            FlatSVGIcon searchIcon = new FlatSVGIcon("warehouse/icon/search.svg", 16, 16);
            lblIcon.setIcon(searchIcon);
        } catch (Exception e) {
            System.err.println("Error loading SVG icon: " + e.getMessage());
            try {
                lblIcon.setIcon(new ImageIcon(getClass().getResource("/warehouse/icon/search.png")));
            } catch (Exception ex) {
                System.err.println("Error loading fallback PNG icon: " + ex.getMessage());
            }
        }

        txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "borderWidth:1;"
                + "borderColor:#E0E0E0;"
                + "focusBorderColor:#3B82F6;"
                + "background:#FFFFFF;"
                + "focusBackground:#F8FAFC;"
                + "margin:8,20,8,20;"
                + "caretColor:#3B82F6;"
                + "selectionColor:#BFDBFE;"
                + "font:14px 'Inter';"
                + "innerPadding:5,14,5,14;");
        txtSearch.putClientProperty(FlatClientProperties.COMPONENT_FOCUS_OWNER, ""
                + "background:#F8FAFC;");

        txtSearch.addActionListener(e -> onSearch());

        add(lblIcon, BorderLayout.WEST);
        add(txtSearch, BorderLayout.CENTER);
    }

    private void onSearch() {
        if (searchListener != null) {
            searchListener.actionPerformed(null);
        }
    }

    public void setSearchListener(ActionListener listener) {
        this.searchListener = listener;
    }

    public String getSearchText() {
        return txtSearch.getText().trim();
    }

    public void setSearchText(String text) {
        txtSearch.setText(text);
    }

    public void requestFocusOnSearch() {
        txtSearch.requestFocus();
    }
}