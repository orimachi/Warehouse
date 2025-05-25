package warehouse.bean;

public enum EPageSize {
    SMALL(5),
    MEDIUM(10),
    LARGE(20);
    
    private final int size;

    EPageSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
