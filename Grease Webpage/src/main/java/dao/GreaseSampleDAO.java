package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.GreaseSample;


//This DAO class provides CRUD database operations for the table grease samples 
//in the database.
public class GreaseSampleDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/greasesamples?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "MRGCorp410";
	
	private static final String INSERT_SAMPLES_SQL = "INSERT INTO sample" + "  "
			+ "(barcodeId, mass, PPM, color, date, time) VALUES " + 
			" (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_SAMPLE_BY_ID = "select sampleId, "
			+ "barcodeId, mass, PPM, color, date, time from "
			+ "sample where sampleId =?";
	private static final String SELECT_ALL_SAMPLES = "select * from sample";
	private static final String DELETE_SAMPLES_SQL = "delete from sample where"
			+ " sampleId = ?;";
	private static final String UPDATE_SAMPLES_SQL = "update sample set "
			+ "barcodeId = ?, mass = ?, PPM = ?, color = ?, date = ?, time = ? "
			+ "where sampleId = ?;";
	private static final String CLEAR_TABLE = "truncate sample;";

	public GreaseSampleDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertSample(GreaseSample sample) throws SQLException {
		System.out.println(INSERT_SAMPLES_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SAMPLES_SQL)) {
			preparedStatement.setInt(1, sample.getBarcodeId());
			preparedStatement.setDouble(2, sample.getMass());
			preparedStatement.setInt(3, sample.getPPM());
			preparedStatement.setString(4, sample.getColor());
			preparedStatement.setString(5, sample.getDate());
			preparedStatement.setString(6, sample.getTime());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public GreaseSample selectSample(int sampleId) {
		GreaseSample sample = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SAMPLE_BY_ID);) {
			preparedStatement.setInt(1, sampleId);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int barcodeId = rs.getInt("barcodeId");
				double mass = rs.getDouble("mass");
				int PPM = rs.getInt("PPM");
				String color = rs.getString("color");
				String date = rs.getString("date");
				String time = rs.getString("time");
				
				sample = new GreaseSample(sampleId, barcodeId, mass, PPM, color, date, time);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return sample;
	}

	public List<GreaseSample> selectAllSamples() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<GreaseSample> samples = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SAMPLES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int sampleId = rs.getInt("sampleId");
				int barcodeId = rs.getInt("barcodeId");
				double mass = rs.getDouble("mass");
				int PPM = rs.getInt("PPM");
				String color = rs.getString("color");
				String date = rs.getString("date");
				String time = rs.getString("time");
				samples.add(new GreaseSample(sampleId, barcodeId, mass, PPM, color, date, time));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return samples;
	}

	public boolean deleteSample(int sampleId) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SAMPLES_SQL);) {
			statement.setInt(1, sampleId);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateSample(GreaseSample sample) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SAMPLES_SQL);) {
			statement.setInt(1, sample.getBarcodeId());
			statement.setDouble(2, sample.getMass());
			statement.setInt(3, sample.getPPM());
			statement.setString(4, sample.getColor());
			statement.setString(5, sample.getDate());
			statement.setString(6, sample.getTime());
			statement.setInt(7, sample.getSampleId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	public boolean clearTable() throws SQLException {
		boolean tableTruncated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CLEAR_TABLE);) {
			tableTruncated = statement.executeUpdate() > 0;
		}
		return tableTruncated;
	}

}
