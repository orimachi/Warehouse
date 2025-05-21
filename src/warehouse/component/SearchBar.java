package warehouse.component;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchBar extends JPanel {
    private final JTextField txtSearch;
    private ActionListener searchListener;

    public SearchBar() {
        setLayout(new BorderLayout());
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder());

        txtSearch = new JTextField();
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON,
                new FlatSVGIcon("warehouse/icon/search.svg", 16, 16));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:1;"
                + "borderColor:#E0E0E0;"
                + "focusBorderColor:#3B82F6;"
                + "background:#FFFFFF;"
                + "focusBackground:#F8FAFC;"
                + "margin:8,20,8,20;"
                + "caretColor:#3B82F6;"
                + "selectionColor:#BFDBFE;"
                + "font:14 'Inter';"
                + "innerPadding:5,14,5,14;");
        txtSearch.putClientProperty(FlatClientProperties.COMPONENT_FOCUS_OWNER, ""
                + "background:#F8FAFC;");

        txtSearch.addActionListener(e -> onSearch());

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
