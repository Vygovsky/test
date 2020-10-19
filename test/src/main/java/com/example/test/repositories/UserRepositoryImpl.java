package com.example.test.repositories;

import com.example.test.jpaConfig.JpaConfig;
import com.example.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private DataSource dataSource;

    private final static String BD_FIND_ALL_USERS = "SELECT*FROM USER";
    private final static String BD_FIND_BY_ID_USER = "SELECT * FROM USER WHERE ID=?";
    private final static String BD_INSERT_USER = "INSERT INTO USER (NICKNAME, EMAIL, BIRTHDAY) VALUES (?,?,?)";
    private final static String BD_DELETE_USER = "DELETE FROM USER WHERE ID=?";
    private final static String BD_UPDATE_USER = "UPDATE USER SET NICKNAME = ?, EMAIL = ?, BIRTHDAY = ? WHERE ID = ?";

    /*  @Override
      public Collection<User> findAll() throws SQLException {
          Connection connection = dataSource.getConnection();
          List<User> users = new ArrayList<>();
          try {
              connection.setAutoCommit(false);
              Statement statement = connection.createStatement();
              ResultSet resultSet = statement.executeQuery(BD_FIND_ALL_USERS);
              while (resultSet.next()) {
                  User user = new User();
                  user.setId(resultSet.getLong("id"));
                  user.setNickname(resultSet.getString("nickName"));
                  user.setEmail(resultSet.getString("email"));
                  user.setBirthday(resultSet.getDate("birthday"));
                  users.add(user);
                  connection.commit();
              }
          } catch (SQLException ex) {
              System.out.println("SQLException. Executing rollback to savepoint..." + ex);
              connection.rollback();
          }
          return users;
      }
  */

    @Override
    public Collection<User> findAll() throws SQLException{
        Connection connection = dataSource.getConnection();
        List<User> users = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(BD_FIND_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setNickname(resultSet.getString("nickName"));
                user.setEmail(resultSet.getString("email"));
                user.setBirthday(resultSet.getDate("birthday"));
                users.add(user);
                connection.commit();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException. Executing rollback to savepoint..." + ex);
            connection.rollback();
        }
        return users;
    }

    @Override
    public User findById(Long uuid) {
        Connection connection=dataSource.getConnection();
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BD_FIND_BY_ID_USER);
            preparedStatement.setLong(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setNickname(resultSet.getString("nickName"));
                user.setEmail(resultSet.getString("email"));
                user.setBirthday(resultSet.getDate("birthday"));
            }
        } catch (SQLException ex) {
            System.out.println("SQLException. Executing rollback not user by uuid ..." + ex);
            connection.rollback();
        }
        return user;
    }

    @Override
    public void save(User user) {
        Connection connection = dataSource.getConnection();
        Date birthday = user.getBirthday();
        java.sql.Date sqlDate = new java.sql.Date(birthday.getTime());
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(BD_INSERT_USER);
            preparedStatement.setString(1, user.getNickname());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            System.out.println("SQLException. Executing rollback to savepoint..." + ex);
            connection.rollback();
        }
    }


    @Override
    public void update(User user) {
        Connection connection = dataSource.getConnection();
        Date birthday = user.getBirthday();
        java.sql.Date sqlDate = new java.sql.Date(birthday.getTime());
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(BD_UPDATE_USER);
            preparedStatement.setString(1, user.getNickname());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setLong(4, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            System.out.println("SQLException. Executing rollback to update..." + ex);
            connection.rollback();
        }
    }

    @Override
    public void delete(Long uuid) {
        Connection connection = dataSource.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(BD_DELETE_USER);
            preparedStatement.setLong(1, uuid);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            System.out.println("SQLException. Executing rollback to delete..." + ex);
            connection.rollback();
        }
    }
}
