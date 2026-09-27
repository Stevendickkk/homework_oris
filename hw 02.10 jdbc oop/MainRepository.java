package oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainRepository {

    private static final String DB_USERNAME = "postgres";

    private static final String DB_PASSWORD = "52";

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/testdb";


    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

        UserRepository userRepository = new UserRepositoryJDBCImpl(connection);

        List<User> users = userRepository.findAll();

//        users.forEach(user -> System.out.println(user.getName()));

//        List<User> users6 = List.of(
//                new User(null, "Cristina", "Prasad",20),
//                new User(null, "Moussa", "Ren",20),
//                new User(null, "Kevin", "Hossen",20),
//                new User(null, "Judith","Yang",20),
//                new User(null, "Elisabeth" ,"Sahu",20),
//                new User(null, "Mariya" ,"Abubakar",20)
//                );
//
//        userRepository.saveAll(users6);

        List<User> usersGreaterThen23 = userRepository.findAllByAgeGreaterThen(23);

        usersGreaterThen23.forEach(user -> System.out.println(user.getName()));




    }
}

