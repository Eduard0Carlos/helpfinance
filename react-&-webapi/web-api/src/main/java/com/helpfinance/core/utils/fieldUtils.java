package com.helpfinance.core.utils;

import com.helpfinance.domain.entities.base.EntityBase;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.UUID;

public class fieldUtils {
    public static <T extends EntityBase> Object valueOf(T entity, Field field) {
        try {
            var getMethod = classUtils.getGetMethodForField(entity.getClass(), field);
            var value = getMethod.invoke(entity);

            if (isUUID(field))
                return formatUuidToString((UUID) value);

            if (isEnum(field))
                return value.toString();

            if (isBigDecimal(field))
                return Integer.parseInt(((BigDecimal)value).toString());

            return getMethod.invoke(entity);
        } catch (Exception ex) {
            return null;
        }
    }

    public static <T> void setValueTo(T object, Field field, Object value) {
        try {
            var setMethod = classUtils.getSetMethodForField(object.getClass(), field);
            setMethod.trySetAccessible();

            if (isUUID(field))
                value = fieldUtils.formatStringToUUID(value);

            if (isEnum(field))
                value = fieldUtils.formatStringToEnum(object, field, value);

            if (setMethod != null && setMethod.canAccess(object))
                setMethod.invoke(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isEnum(Field field) {
        return field.getType().getSuperclass() != null
                && field.getType().getSuperclass().getTypeName().equals(Enum.class.getTypeName());
    }

    public static UUID formatStringToUUID(Object value) {
        var valueString = value.toString().trim();

        return UUID.fromString(
                valueString.substring(0, 8) + "-" + valueString.substring(8, 12) + "-" + valueString.substring(12, 16)
                        + "-" + valueString.substring(16, 20) + "-" + valueString.substring(20, valueString.length()));
    }

    public static <T> Object formatStringToEnum(T instance, Field field, Object value) {
        var enumMethods = classUtils.getAllEntityMethods(field.getType());

        var valueOfMethod = enumMethods.stream()
                .filter(x -> x.getName().toLowerCase().contains("valueof") && x.getParameterCount() == 1).findFirst()
                .orElse(null);

        if (valueOfMethod == null)
            return null;

        try {
            return valueOfMethod.invoke(instance, value);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatBooleanToString(Boolean bool) {
        return bool ? "1" : "0";
    }

    public static String formatUuidToString(UUID value) {
        return value.toString().replace("-", "").toUpperCase();
    }

    public static boolean isUUID(Field field) {
        return field.getType().getTypeName().equals(UUID.class.getTypeName());
    }

    public static boolean isBigDecimal(Field field) {
        return field.getType().getTypeName().equals(BigDecimal.class.getTypeName());
    }

    public static boolean isBoolean(Field field) {
        return field.getType().getTypeName().equals(Boolean.TYPE.getName());
    }
}
