package dao.base;

import contexts.helpFinanceContext;
import entities.base.EntityBase;
import interfaces.dao.base.IBaseDAO;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
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

        PreparedStatement statement = null;
        var generatedScript = "";
        var fieldsValues = new ArrayList<Object>();

        try {
            var classType = entity.getClass();

            var entityFields = classType.getDeclaredFields();
            var entityBaseFields = EntityBase.class.getDeclaredFields();

            var fields = new ArrayList<Field>();

            fields.addAll(Arrays.stream(entityFields).toList());
            fields.addAll(Arrays.stream(entityBaseFields).toList());

            var methods = Arrays.stream(classType.getMethods()).filter(x -> x.getName().toLowerCase().startsWith("get")).toList();

            var fieldsNames = new StringBuilder();
            var fieldValuesFormatted = new StringBuilder();

            fields.remove(fields.size() - 1);

            var count = 1;

            for (var field : fields) {
                var separator = (count == fields.size() ? "" : ", ");

                fieldsNames.append(field.getName() + separator);
                fieldValuesFormatted.append(":param" + count + separator);

                var getMethodFind = methods.stream().filter(z -> z.getName().toLowerCase().equals("get" + field.getName().toLowerCase())).findFirst();
                var getMethod = getMethodFind.orElse(null);

                if (getMethod != null) {
                    try {
                        var value = getMethod.invoke(entity);

                        if (field.getType().getTypeName().equals(UUID.class.getTypeName()))
                        {
                            value = ((UUID)value).toString().replace("-", "").toUpperCase();
                        }

                        if (field.getType().getSuperclass() != null && field.getType().getSuperclass().getTypeName().equals(Enum.class.getTypeName()))
                        {
                            value = value.toString();
                        }

                        fieldsValues.add(value);
                    } catch (Exception e) {
                        fieldsValues.add(null);
                        e.printStackTrace();
                    }
                }

                count++;
            }

            var insertStatement = "INSERT INTO " + _tableName + " (" + fieldsNames + ")" + " VALUES (" + fieldValuesFormatted + ")";

            statement = connection.prepareStatement(insertStatement);

            int position = 1;

            for (var fieldValue : fieldsValues) {
                try {
                    statement.setObject(position, fieldValue);

                    position++;
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            };

            generatedScript = insertStatement;

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(generatedScript);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(TEntity entity) {
        PreparedStatement statement = null;

        try {
            var classType = entity.getClass();

            var fields = classType.getDeclaredFields();
            var methods = Arrays.stream(classType.getMethods()).filter(x -> x.getName().toLowerCase().startsWith("get")).toList();

            var setScript = new StringBuilder();
            var fieldsValues = new ArrayList<Object>();

            var count = 1;

            setScript.append(" SET ");

            for (var field : fields) {
                setScript.append(field.getName() + "? ");

                var getMethod = methods.stream().filter(z -> z.getName().toLowerCase().contains(field.getName().toLowerCase())).findFirst().get();

                if (getMethod != null) {
                    try {
                        fieldsValues.add(getMethod.invoke(entity));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                count++;
            }

            var updateStatement = "UPDATE " + classType.getSimpleName() + setScript + " WHERE id = " + entity.getId();

            statement = connection.prepareStatement(updateStatement);

            int position = 1;

            for (var fieldValue : fieldsValues) {
                statement.setObject(position, fieldValue);

                position++;
            }
            ;

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(TEntity entity) {
        PreparedStatement statement = null;

        try {
            var classType = entity.getClass();

            var deleteStatement = "DELETE FROM " + classType.getSimpleName() + " WHERE id = " + entity.getId();

            statement = connection.prepareStatement(deleteStatement);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(UUID id) {
        PreparedStatement statement = null;

        try {
            var deleteStatement = "DELETE FROM " + _entityType.getSimpleName() + " WHERE id = " + id;

            statement = connection.prepareStatement(deleteStatement);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public TEntity get(UUID id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM " + _tableName + " WHERE id = '" + id.toString().replace("-", "").toUpperCase() + "'");
            resultSet = statement.executeQuery();

            if (!resultSet.next())
                return null;

            var constructor = Arrays.stream(_entityType.getDeclaredConstructors()).filter(x -> x.getParameterCount() == 0).findFirst().get();
            constructor.setAccessible(true);
            var entity = (TEntity) constructor.newInstance();

            var fields = new java.util.ArrayList<>(Arrays.stream(_entityType.getDeclaredFields()).toList());
            fields.addAll(Arrays.stream(_entityType.getSuperclass().getDeclaredFields()).toList());
            var methods = _entityType.getMethods();

            for (var field : fields) {
                try {
                    var setMethod = Arrays.stream(methods).filter(x -> x.getName().toLowerCase().contains("set" + field.getName().toLowerCase())).findFirst().get();

                    if (setMethod != null && setMethod.getParameterCount() > 0) {
                        var value = resultSet.getObject(field.getName());

                        if (field.getType().getTypeName().equals(UUID.class.getTypeName()))
                        {
                            var uuidFormated = value.toString().substring(0, 8) + "-" + value.toString().substring(8, 12) + "-" + value.toString().substring(12, 16) + "-" + value.toString().substring(16, 20) + "-" + value.toString().substring(20, value.toString().length() - 1).trim();
                            value = UUID.fromString(uuidFormated);
                        }

                        setMethod.invoke(entity, value);
                    }
                } catch (Exception e) {
                    continue;
                }
            }

            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<TEntity> getAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM " + _tableName);
            resultSet = statement.executeQuery();

            var entities = new ArrayList<TEntity>();

            while (resultSet.next()) {
                var constructor = Arrays.stream(_entityType.getDeclaredConstructors()).filter(x -> x.getParameterCount() == 0).findFirst().get();
                constructor.setAccessible(true);
                var newEntity = (TEntity) constructor.newInstance();

                var fields = new java.util.ArrayList<>(Arrays.stream(_entityType.getDeclaredFields()).toList());
                fields.addAll(Arrays.stream(_entityType.getSuperclass().getDeclaredFields()).toList());
                var methods = _entityType.getMethods();

                for (var field : fields) {
                    try {
                        var setMethod = Arrays.stream(methods).filter(x -> x.getName().toLowerCase().equals("set" + field.getName().toLowerCase())).findFirst().orElse(null);

                        if (setMethod != null && setMethod.getParameterCount() > 0) {
                            var value = resultSet.getObject(field.getName());

                            if (field.getType().getTypeName().equals(UUID.class.getTypeName()))
                            {
                                var uuidFormated = value.toString().substring(0, 8) + "-" + value.toString().substring(8, 12) + "-" + value.toString().substring(12, 16) + "-" + value.toString().substring(16, 20) + "-" + value.toString().substring(20, value.toString().length() - 1).trim();
                                value = UUID.fromString(uuidFormated);
                            }

                            setMethod.invoke(newEntity, value);
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }

                entities.add(newEntity);
            }

            return entities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                statement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
