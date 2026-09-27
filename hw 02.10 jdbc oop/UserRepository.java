package oop;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends CrudRequstery<User>{

    List<User> findAll() throws SQLException;

    void saveAll(List users) throws SQLException;

    List<User> findAllByAgeGreaterThen(int age) throws SQLException;


}
