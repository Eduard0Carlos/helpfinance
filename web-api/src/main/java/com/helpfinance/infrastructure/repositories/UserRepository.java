package com.helpfinance.infrastructure.repositories;

import com.helpfinance.infrastructure.interfaces.IUserRepository;
import com.helpfinance.infrastructure.repositories.base.EntityRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.User;

@Repository
public class UserRepository extends EntityRepository<User> implements IUserRepository {

    public UserRepository() { super("Users"); }

    public User get(String email, String password) {
         if (connection == null) {
            notificationService.addError("A conexão com o banco de dados não foi estabelecida!");
            return null;
        }

        var statement = (PreparedStatement) null;
        var resultSet = (ResultSet) null;

        try {
            statement = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE email = '" + email + "' AND password = '" + password + "'");
            resultSet = statement.executeQuery();

            if (!resultSet.next())
                return null;

            return getEntityFromResult(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (statement != null)
                    statement.close();

                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
