package com.helpfinance.infrastructure.repositories;

import com.helpfinance.infrastructure.interfaces.IMovimentationRepository;
import com.helpfinance.infrastructure.repositories.base.EntityRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Movimentation;

@Repository
public class MovimentationRepository extends EntityRepository<Movimentation> implements IMovimentationRepository {
  
    public MovimentationRepository() { super("Movimentations"); }

    public List<Movimentation> getAll(UUID userId, Timestamp from, Timestamp to) {
          if (connection == null) {
            notificationService.addError("A conexão com o banco de dados não foi estabelecida!");
            return null;
        }

        var statement = (PreparedStatement) null;
        var resultSet = (ResultSet) null;

        try {
            var sqlGenerated = "SELECT * FROM " + tableName + " WHERE userId = " + uuidToString(userId) + " AND \"DATE\" BETWEEN TO_DATE('" + formatDate(from) + "','YYYY-MM-DD') AND TO_DATE('" + formatDate(to) + "','YYYY-MM-DD')";
            statement = connection.prepareStatement(sqlGenerated);
            resultSet = statement.executeQuery();

            var entityList = new ArrayList<Movimentation>();

            while (resultSet.next()) {
                entityList.add(getEntityFromResult(resultSet));
            }

            return entityList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Movimentation>();
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