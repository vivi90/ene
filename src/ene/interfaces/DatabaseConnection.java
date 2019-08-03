package ene.interfaces;

import java.util.List;
import java.util.Map;

/**
 * Database connection interface.
 * @since 0.9.0
 */
public interface DatabaseConnection {
    /**
     * Executes statement.
     * @param statement Statement.
     * @return Number of affected rows.
     */
    abstract public int execute(String statement) throws Exception;

    /**
     * Query statement.
     * @param statement Statement.
     * @return Returns a list of maps of rows.
     */
    abstract public List<Map<String,String>> query(String statement) throws Exception;
}
