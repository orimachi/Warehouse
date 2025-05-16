package warehouse.dao;

import java.util.List;
import warehouse.entity.Customer;

public class CustomerDAO extends BaseDAO<Customer, String>{

    @Override
    public void insert(Customer entity) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Customer entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public Customer selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Customer> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    protected List<Customer> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
