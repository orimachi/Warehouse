package warehouse.dao;

import java.util.UUID;


public interface CustomDAO {
    String getNameByUUID(UUID id);
    UUID getUUIDByName(String name);
}
