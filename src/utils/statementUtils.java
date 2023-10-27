package utils;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class statementUtils {
    public static void trySetParams(PreparedStatement statement, ArrayList<Object> params) {
        for (int position = 1; position <= params.size(); position++) {
            trySetObject(statement, position, params.get(position - 1));
        }
    }

    public static void trySetObject(PreparedStatement statement, int position, Object object) {
        try {
            statement.setObject(position, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
