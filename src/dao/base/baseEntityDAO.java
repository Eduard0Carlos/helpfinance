package dao.base;

import contexts.helpFinanceContext;
import entities.base.EntityBase;
import interfaces.dao.base.IBaseDAO;
import utils.classUtils;
import utils.fieldUtils;
import utils.statementUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class baseEntityDAO<TEntity extends EntityBase> implements IBaseDAO<TEntity> {

    private final String _tableName;
    private final Class<TEntity> _entityType;
    protected Connection connection = helpFinanceContext.getConnection();

    public baseEntityDAO(Class<TEntity> type, String tableName) {
        _tableName = tableName;
        _entityType = type;
    }

    public void insert(TEntity entity) {
        if (connection == null)
            return;

        var statement = (PreparedStatement) null;
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

                fieldsNames.append(field.getName() + separator);
                parameters.append(":param" + count + separator);

                var value = fieldUtils.valueOf(entity, field);

                fieldsValues.add(value);

                count++;
            }

            generatedScript = "INSERT INTO " + _tableName + " (" + fieldsNames + ")" + " VALUES (" + parameters + ")";

            statement = connection.prepareStatement(generatedScript);

            statementUtils.trySetParams(statement, fieldsValues);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(generatedScript);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(TEntity entity) {
        var statement = (PreparedStatement) null;
        var generatedScript = "";

        try {
            var classType = entity.getClass();
            var fields = classUtils.getEntityFields(classType);

            var setScript = new StringBuilder();
            var fieldsValues = new ArrayList<Object>();

            setScript.append(" SET ");

            var count = 1;

            for (var field : fields) {
                setScript.append(field.getName() + " = :param" + count);
                fieldsValues.add(fieldUtils.valueOf(entity, field));

                count++;
            }

            generatedScript = "UPDATE " + _tableName + setScript + " WHERE id = " + entity.getId();

            statement = connection.prepareStatement(generatedScript);

            statementUtils.trySetParams(statement, fieldsValues);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(generatedScript);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(TEntity entity) {
        var statement = (PreparedStatement) null;

        try {
            statement = connection.prepareStatement("DELETE FROM " + _tableName + " WHERE id = " + fieldUtils.formatUuidToString(entity.getId()));

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(UUID id) {
        var statement = (PreparedStatement) null;

        try {
            statement = connection.prepareStatement("DELETE FROM " + _tableName + " WHERE id = " + fieldUtils.formatUuidToString(id));

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public TEntity get(UUID id) {
        if (connection == null)
            return null;

        var statement = (PreparedStatement) null;
        var resultSet = (ResultSet) null;

        try {
            statement = connection.prepareStatement("SELECT * FROM " + _tableName + " WHERE id = '" + fieldUtils.formatUuidToString(id) + "'");
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
            statement = connection.prepareStatement("SELECT * FROM " + _tableName);
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
}
