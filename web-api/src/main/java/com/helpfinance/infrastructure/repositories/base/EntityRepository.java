package com.helpfinance.infrastructure.repositories.base;

import com.helpfinance.infrastructure.interfaces.base.IRepository;
import com.helpfinance.domain.entities.base.EntityBase;
import com.helpfinance.core.services.NotificationService;
import com.helpfinance.core.utils.classUtils;
import com.helpfinance.core.utils.fieldUtils;
import com.helpfinance.core.utils.statementUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class EntityRepository<TEntity extends EntityBase> implements IRepository<TEntity> {

    @Autowired
    protected NotificationService notificationService;

    private Class<TEntity> _entityType;
    protected final String tableName;

    @Autowired(required = false)
    protected Connection connection;

    public EntityRepository(String tableName) {
        this.tableName = tableName;

        try {
            var fullTypeName = this.getClass().getGenericSuperclass().getTypeName();
            var entityTypeName = fullTypeName.substring(fullTypeName.indexOf("<"), fullTypeName.length() - 1)
                    .replace(">", "").replace("<", "");
            _entityType = (Class<TEntity>) Class.forName(entityTypeName);
        } catch (Exception e) {
            _entityType = (Class<TEntity>) this.getClass().getGenericSuperclass().getClass();
        }

        try {
            connection.setAutoCommit(false);
        } catch (Exception e) {
        }
    }

    public Boolean insert(TEntity entity) {
        if (connection == null) {
            notificationService.addError("A conexão com o banco de dados não foi estabelecida!");
            return false;
        }

        var fieldsValues = new ArrayList<Object>();
        var generatedScript = "";

        try {
            var classType = entity.getClass();
            var fields = classUtils.getEntityFields(classType);

            var fieldsNames = new StringBuilder();
            var parameters = new StringBuilder();

            fields.remove(fields.size() - 1);

            var count = 1;

            for (var field : fields) {
                var separator = (count == fields.size() ? "" : ", ");

                fieldsNames.append("\"" + field.getName().toUpperCase() + "\"" + separator);
                parameters.append(":param" + count + separator);

                var value = fieldUtils.valueOf(entity, field);

                fieldsValues.add(value);

                count++;
            }

            generatedScript = "INSERT INTO " + tableName + " (" + fieldsNames + ")" + " VALUES (" + parameters + ")";

            var statement = connection.prepareStatement(generatedScript);

            statementUtils.trySetParams(statement, fieldsValues);

            statement.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(generatedScript);

            notificationService.addError("Houve um problema inesperado. Tente novamente mais tarde.");

            return false;
        }
    }

    public Boolean update(TEntity entity) {
        if (connection == null) {
            notificationService.addError("A conexão com o banco de dados não foi estabelecida!");
            return false;
        }

        var generatedScript = "";

        try {
            var classType = entity.getClass();
            var fields = classUtils.getEntityFields(classType);

            var setScript = new StringBuilder();

            var fieldsValues = new ArrayList<Object>();

            setScript.append(" SET ");

            var count = 1;

            for (var field : fields) {
                if (field.getName().equalsIgnoreCase("id") || (fieldUtils.valueOf(entity, field) == null))
                    continue;

                setScript.append(field.getName().toUpperCase() + " = :param" + count + " , ");

                fieldsValues.add(fieldUtils.valueOf(entity, field));

                count++;
            }

            var setScripts = setScript.toString().substring(0, setScript.length() - 2);

            generatedScript = "UPDATE " + tableName + setScripts + " WHERE \"ID\" = '"
                    + fieldUtils.formatUuidToString(entity.getId()) + "'";

            var statement = connection.prepareStatement(generatedScript);

            statementUtils.trySetParams(statement, fieldsValues);

            statement.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(generatedScript);

            notificationService.addError("Houve um problema inesperado. Tente novamente mais tarde.");

            return false;
        }
    }

    public Boolean delete(TEntity entity) {
        if (connection == null) {
            notificationService.addError("A conexão com o banco de dados não foi estabelecida!");
            return false;
        }

        try {
            var statement = connection.prepareStatement(
                    "DELETE FROM " + tableName + " WHERE id = '" + fieldUtils.formatUuidToString(entity.getId()) + "'");

            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            notificationService.addError("Houve um problema ao tentar salvar as alterações!");

            return false;
        }
    }

    public Boolean delete(UUID id) {
        if (connection == null) {
            notificationService.addError("A conexão com o banco de dados não foi estabelecida!");
            return false;
        }

        try {
            var statement = connection
                    .prepareStatement(
                            "DELETE FROM " + tableName + " WHERE id = '" + fieldUtils.formatUuidToString(id) + "'");

            statement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            notificationService.addError("Houve um problema inesperado. Tente novamente mais tarde.");

            return false;
        }
    }

    public TEntity get(UUID id) {
        if (connection == null) {
            notificationService.addError("A conexão com o banco de dados não foi estabelecida!");
            return null;
        }

        var statement = (PreparedStatement) null;
        var resultSet = (ResultSet) null;

        try {
            statement = connection.prepareStatement(
                    "SELECT * FROM " + tableName + " WHERE id = '" + fieldUtils.formatUuidToString(id) + "'");
            resultSet = statement.executeQuery();

            if (!resultSet.next())
                return null;

            var entity = classUtils.tryGetInstanceOf(_entityType);
            var fields = classUtils.getEntityFields(_entityType);

            for (var field : fields)
                fieldUtils.setValueTo(entity, field, resultSet.getObject(field.getName()));

            return entity;
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

    public List<TEntity> getAll() {
        if (connection == null)
            return null;

        var statement = (PreparedStatement) null;
        var resultSet = (ResultSet) null;

        try {
            statement = connection.prepareStatement("SELECT * FROM " + tableName);
            resultSet = statement.executeQuery();

            var entities = new ArrayList<TEntity>();

            while (resultSet.next()) {
                var newEntity = classUtils.tryGetInstanceOf(_entityType);
                var fields = classUtils.getEntityFields(_entityType);

                for (var field : fields)
                    fieldUtils.setValueTo(newEntity, field, resultSet.getObject(field.getName()));

                entities.add(newEntity);
            }

            return entities;
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

    protected TEntity getEntityFromResult(ResultSet resultSet) {
        try {
            var entity = classUtils.tryGetInstanceOf(_entityType);
            var fields = classUtils.getEntityFields(_entityType);

            for (var field : fields)
                fieldUtils.setValueTo(entity, field, resultSet.getObject(field.getName()));

            return entity;
        } catch (Exception e) {
            return null;
        }

    }

    protected String uuidToString(UUID id) {
        return "'" + id.toString().toUpperCase().replace("-", "") + "'";
    }

    protected String formatDate(Timestamp date) {
        var localDate = date.toLocalDateTime();

        return DateTimeFormatter.ISO_DATE.format(localDate);
    }
}
