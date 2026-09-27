package oop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJDBCImpl implements UserRepository {

    private Connection connection;

    private static final String SQL_SELECT_FROM_DRIVERS = "select id, first_name, last_name, age from drivers";

    public UserRepositoryJDBCImpl(Connection connection){
        this.connection = connection;
    }


    @Override
    public List<User> findAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_DRIVERS);

        List<User> result = new ArrayList<>();

        while (resultSet.next()){
            User user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age")

            );
            result.add(user);
        }
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public void saveAll(List users) throws SQLException {

        if (users.isEmpty()){
            return;
        }

        Statement statement = connection.createStatement();

        StringBuilder query = new StringBuilder("INSERT INTO drivers (first_name, last_name, age) VALUES ");

        for (int i = 0; i< users.size(); i++){
            User user = (User) users.get(i);
            query.append("('")
                    .append(user.getName())
                    .append("', '")
                    .append(user.getSurname())
                    .append("'," )
                    .append(user.getAge())
                    .append(")");

            if (i != (users.size()-1)){
                query.append(", ");
            }else{
                query.append(";");
            }
        }

        statement.executeUpdate(query.toString());
        statement.close();

    }

    public List<User> findAllByAgeGreaterThen(int age) throws SQLException{
        Statement statement = connection.createStatement();
        String query = ("SELECT id, first_name, last_name, age FROM drivers WHERE age > " + age);
        ResultSet resultSet = statement.executeQuery(query);

        List result = new ArrayList<>();
        while (resultSet.next()){
            User user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString("last_name"),
                    resultSet.getInt("age")

            );
            result.add(user);
        }
        return result;


    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public void removeById(Long id) {

    }
}
