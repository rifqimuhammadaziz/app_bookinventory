package rifqimuhammadaziz.bookinventory.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface DaoService<T> {

    List<T> findAll() throws SQLException, ClassNotFoundException, IOException;

    T findById(Integer id) throws SQLException, ClassNotFoundException;

    List<T> findByName(String name) throws SQLException, ClassNotFoundException;

    List<T> searchByName(String name) throws SQLException, ClassNotFoundException;

    int addData(T t) throws SQLException, ClassNotFoundException, IOException;

    int updateData(T t) throws SQLException, ClassNotFoundException, IOException;

    int deleteData(T t) throws SQLException, ClassNotFoundException;

}
