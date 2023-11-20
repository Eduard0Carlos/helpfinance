package com.helpfinance.infrastructure.repositories;

import com.helpfinance.infrastructure.interfaces.IInvestmentRepository;
import com.helpfinance.infrastructure.repositories.base.EntityRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.helpfinance.domain.entities.Investment;

@Repository
public class InvestmentRepository extends EntityRepository<Investment> implements IInvestmentRepository {

    public InvestmentRepository() {
        super("Investments");
    }

    @Override
    public List<Investment> getAll(UUID userId) {
        if (connection == null) {
            notificationService.addError("A conexão com o banco de dados não foi estabelecida!");
            return null;
        }

        var statement = (PreparedStatement) null;
        var resultSet = (ResultSet) null;

        try {
            var sqlGenerated = "SELECT * FROM " + tableName + " WHERE userId = " + uuidToString(userId);
                  
            statement = connection.prepareStatement(sqlGenerated);
            resultSet = statement.executeQuery();

            var entityList = new ArrayList<Investment>();

            while (resultSet.next()) {
                entityList.add(getEntityFromResult(resultSet));
            }

            return entityList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Investment>();
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