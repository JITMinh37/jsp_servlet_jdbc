package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {
	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	public Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=Newservlet_jsp;user=sa;password=123;encrypt=false;";
//			Class.forName(resourceBundle.getString("drivename"));
//			String connectionUrl = resourceBundle.getString("url");
			return DriverManager.getConnection(connectionUrl);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public <T> ArrayList<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		ArrayList<T> list = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				statement = con.prepareStatement(sql);
				setStatement(statement, parameters);
				rs = statement.executeQuery();
				while (rs.next()) {
					list.add(rowMapper.mapRow(rs));
				}
				return list;
			} catch (SQLException e) {
				return null;
			} finally {
				try {
					if (con != null) {
						con.close();
					}
					if (statement != null) {
						statement.close();
					}
					if (rs != null) {
						rs.close();
					}
				} catch (SQLException e) {
					return null;
				}
			}
		}
		return null;
	}

	private void setStatement(PreparedStatement statement, Object... parameters) {
		for (int i = 0; i < parameters.length; i++) {
			Object parameter = parameters[i];
			int index = i + 1;
			try {
				if (parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				}
				else if(parameter instanceof String) {
					statement.setString(index, (String) parameter);
				}
				else if(parameter instanceof Integer) {
					statement.setInt(index, (Integer) parameter);
				}
				else if(parameter instanceof Date) {
					statement.setDate(index, (Date) parameter);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			statement = con.prepareStatement(sql);
			setStatement(statement, parameters);
			statement.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		Long id = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			statement = con.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
			setStatement(statement, parameters);
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getLong(1);
			}
			con.commit();
			return id;
		} catch (SQLException e) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return null;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
	@Override
	public int count(String sql, Object... parameters) {
		Connection con = getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		if (con != null) {
			try {
				int count = 0;
				statement = con.prepareStatement(sql);
				setStatement(statement, parameters);
				rs = statement.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}
				return count;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null) {
						con.close();
					}
					if (statement != null) {
						statement.close();
					}
					if (rs != null) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
}
