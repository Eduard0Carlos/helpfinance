package com.helpfinance.core.utils;

import com.helpfinance.domain.entities.base.EntityBase;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class classUtils {
    public static <T> ArrayList<Method> getGetMethods(Class<T> type) {
        var methods = getAllEntityMethods(type);

        return new ArrayList<>(methods.stream().filter(z -> z.getName().toLowerCase().contains("get")).toList());
    }

    public static <T> ArrayList<Method> getAllEntityMethods(Class<T> type) {
        var methods = new ArrayList<Method>();

        methods.addAll(Arrays.stream(type.getDeclaredMethods()).toList());
        methods.addAll(Arrays.stream(EntityBase.class.getDeclaredMethods()).toList());

        return methods;
    }

    public static <T> ArrayList<Method> getSetMethods(Class<T> type) {
        var methods = getAllEntityMethods(type);

        return new ArrayList<>(methods.stream().filter(z -> z.getName().toLowerCase().contains("set")).toList());
    }

    public static Method getSetMethodForField(ArrayList<Method> methods, Field field) {
        return methods.stream().filter(x -> x.getName().toLowerCase().equals("set" + field.getName().toLowerCase())).findFirst().orElse(null);
    }

    public static <T> Method getSetMethodForField(Class<T> type, Field field) {
        var methods = getAllEntityMethods(type);

        return methods.stream().filter(x -> x.getName().toLowerCase().equals("set" + field.getName().toLowerCase())).findFirst().orElse(null);    }

    public static Method getGetMethodForField(ArrayList<Method> methods, Field field) {
        return methods.stream().filter(x -> x.getName().toLowerCase().equals("get" + field.getName().toLowerCase())).findFirst().orElse(null);
    }

    public static <T> Method getGetMethodForField(Class<T> type, Field field) {
        var methods = getAllEntityMethods(type);

        return methods.stream().filter(x -> x.getName().toLowerCase().equals("get" + field.getName().toLowerCase())).findFirst().orElse(null);
    }

    public static <T extends EntityBase> ArrayList<Field> getEntityFields(Class<T> type) {
        var fields = new ArrayList<Field>();

        fields.addAll(Arrays.stream(type.getDeclaredFields()).toList());
        fields.addAll(Arrays.stream(EntityBase.class.getDeclaredFields()).toList());

        return new ArrayList<>(fields.stream().toList());
    }

    public static <T> T tryGetInstanceOf(Class<T> type) {
        try {
            var constructor = Arrays.stream(type.getDeclaredConstructors()).filter(x -> x.getParameterCount() == 0).findFirst().get();
            constructor.setAccessible(true);

            return (T) constructor.newInstance();
        }
        catch (Exception e) {
            return null;
        }

    }
}