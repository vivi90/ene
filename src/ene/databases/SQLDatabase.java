package ene.databases;

import ene.AbstractObject;
import ene.interfaces.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC SQL database.
 * @version 1.0.0
 */
public class SQLDatabase extends AbstractObject implements DatabaseConnection {
    /**
     * Connection instance.
     */
    protected Connection connection = null;

    /**
     * Statement instance.
     */
    protected Statement statement = null;

    /**
     * Sets the connection instance.
     * @param connection Connection instance.
     */
    protected void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Returns the connection instance.
     * @return Connection instance.
     */
    protected Connection getConnection() {
        return this.connection;
    }

    /**
     * Sets the statement instance.
     * @param statement Statement instance.
     */
    protected void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     * Returns the statement instance.
     * @return Statement instance.
     */
    protected Statement getStatement() {
        return this.statement;
    }

    /**
     * Constructor.
     * @param url A database url of the form jdbc:subprotocol:subname.
     * @param statementTimeout Statement timeout in seconds.
     */
    public SQLDatabase(String url, int statementTimeout) throws Exception {
        this.setConnection(DriverManager.getConnection(url));
        this.setStatement(this.getConnection().createStatement());
        this.getStatement().setQueryTimeout(statementTimeout);
    }

    @Override
    public int execute(String statement) throws Exception {
        return this.getStatement().executeUpdate(statement);
    }

    @Override
    public List<Map<String,String>> query(String statement) throws Exception {
        List<Map<String,String>> result = new ArrayList<>();
        ResultSet resultSet = this.getStatement().executeQuery(statement);
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            Map<String, String> row = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                row.put(metaData.getColumnName(i), resultSet.getString(i));
            }
            result.add(row);
        }
        return result;
    }
}
