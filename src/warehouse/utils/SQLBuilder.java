package warehouse.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class SQLBuilder {
    
    public static String buildSQLInsert(String table, String... columns) {
        String colNames = String.join(", ", columns);
        String values= String.join(", ", Collections.nCopies(columns.length, "?"));
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
    
    public static String buildSQLSelect(String table, String idColumn) {
        return String.format("SELECT * FROM %s WHERE %s=?", table, idColumn);
    }
}
