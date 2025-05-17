package warehouse.dao;

import java.util.List;
import java.util.UUID;
import warehouse.entity.Warehouse;

public class WarehouseDAO extends BaseDAO<Warehouse, UUID>{

    @Override
    public void insert(Warehouse entity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Warehouse entity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Warehouse selectById(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Warehouse selectByName(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Warehouse> selectAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected List<Warehouse> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
