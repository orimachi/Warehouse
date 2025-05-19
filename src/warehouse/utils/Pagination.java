package warehouse.utils;

import java.util.List;

public class Pagination {
    public static String getTotalPage(List<?> list, int pageSize) {
        if (list == null || list.isEmpty() || pageSize <= 0) return "0";
        int totalPages = (int) Math.ceil((double) list.size() / pageSize);
        return String.valueOf(totalPages);
    }
    
     public static String getCurrentPage(int requestedPage, List<?> list, int pageSize) {
        int totalPages = Integer.parseInt(getTotalPage(list, pageSize));
        int currentPage = Math.max(1, Math.min(requestedPage, totalPages));
        return String.valueOf(currentPage);
    }
    
    public static <T> List<T> getPage(List<T> data, int page, int pageSize) {
        if (page < 1 || pageSize < 1) return List.of();

        int totalItems = data.size();
        int fromIndex = (page - 1) * pageSize;
        if (fromIndex >= totalItems) return List.of(); 

        int toIndex = Math.min(fromIndex + pageSize, totalItems);
        return data.subList(fromIndex, toIndex);
    }
}
