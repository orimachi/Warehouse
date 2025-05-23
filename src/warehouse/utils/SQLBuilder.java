package warehouse.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import warehouse.bean.EPositions;

public class SQLBuilder {

    public static String buildSQLInsert(String table, String... columns) {
        String colNames = String.join(", ", columns);
        String values = String.join(", ", Collections.nCopies(columns.length, "?"));
        return String.format("INSERT INTO %s (%s) VALUES (%s)", table, colNames, values);
    }

    public static String buildSQLUpdate(String table, String idColumn, String... columns) {
        String setClause = Arrays.stream(columns)
                .map(col -> col + "=?")
                .collect(Collectors.joining(", "));
        return String.format("UPDATE %s SET %s WHERE %s=?", table, setClause, idColumn);
    }

    public static String buildSQLDelete(String table, String idColumn) {
        return String.format("DELETE FROM %s WHERE %s=?", table, idColumn);
    }

    public static String buildSQLSelectALL(String table) {
        return String.format("SELECT * FROM %s", table);
    }

    public static String buildSQLSelect(String table, String idColumn) {
        return String.format("SELECT * FROM %s WHERE %s=?", table, idColumn);
    }

    public static String getUUIDByName(String table, String idColumn) {
      return String.format("SELECT Id FROM %s WHERE %s=?", table, idColumn);
    }
    
    public static String buildSQLSelectLike(String table, String idColumn, String keyword, EPositions positions) {
        String likePattern;
        switch (positions) {
            case EPositions.START:
                likePattern = keyword + "%";
                break;
            case EPositions.END:
                likePattern = "%" + keyword;
                break;
            default:
                likePattern = "%" + keyword + "%";
                break;
        }
        return String.format("SELECT * FROM %s WHERE %s LIKE ?", table, idColumn, likePattern);
    }

    public static String buildSQLSelectsLikeAnd(String table, List<String> columns, List<String> operators, List<String> keywords) {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + table + " WHERE ");
        for (int i = 0; i < columns.size(); i++) {
            if (isNumeric(keywords.get(i))) {
                sql.append(String.format("%s %s %s", columns.get(i), operators.get(i), keywords.get(i)));
            }
            sql.append(String.format("%s %s '%s'", columns.get(i), operators.get(i), keywords.get(i)));
            if (i < columns.size() - 1) {
                sql.append(" AND ");
            }
        }
        return sql.toString();
    }

    private static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static String getNameByUUID(String table, String idColumn) {
      return String.format("SELECT Name FROM %s WHERE %s=?", table, idColumn);
    }
    
    public static String buildSQLSelectJoinLike(String table, String joinClause, String likeColumn) {
        return String.format(
            "SELECT * FROM %s %s WHERE %s LIKE ?",
            table,
            joinClause != null ? joinClause : "",
            likeColumn
        );
    }

}
