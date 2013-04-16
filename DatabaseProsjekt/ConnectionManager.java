//lik Opprydder klassen fra læreboka
import java.sql.*;

public class ConnectionManager {
	public static void closeResSet(ResultSet res) {
		try {
			if (res != null) {
				res.close();
			}
		} catch (SQLException e) {
			printMessage(e, "closeResSet()");
		}
	}

	public static void closeStatement(Statement stm) {
		try {
			if (stm != null) {
				stm.close();
			}
		} catch (SQLException e) {
			printMessage(e, "closeStatement()");
		}
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			printMessage(e, "closeConnection()");
		}
	}

	public static void rollback(Connection connection) {
		try {
			if (connection != null && !connection.getAutoCommit()) {
				connection.rollback();
			}
		} catch (SQLException e) {
			printMessage(e, "rollback()");
		}
	}

	public static void setAutoCommit(Connection connection) {
		try {
			if (connection != null && !connection.getAutoCommit()) {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			printMessage(e, "setAutoCommit()");
		}
	}

	public static void printMessage(Exception e, String message) {
		System.err.println("*** Error occured: " + message + ". ***");
		e.printStackTrace(System.err);
	}
}