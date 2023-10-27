package factories;

import dao.base.baseEntityDAO;

import java.util.Arrays;

public class daoFactory {
    public static <TDAO extends baseEntityDAO> TDAO tryGet(Class<TDAO> type) {
        try {
            var daoClassConstructor = type.getConstructor();

            return (TDAO) daoClassConstructor.newInstance();
        }
        catch (Exception ex) {
            return null;
        }
    }
}
