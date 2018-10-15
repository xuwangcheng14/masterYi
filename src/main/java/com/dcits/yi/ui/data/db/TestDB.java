package com.dcits.yi.ui.data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.dialect.Props;


/**
 * 测试数据库类
 * @author xuwangcheng
 * @version 20181012
 *
 */
public class TestDB {
	
	private static final Log logger = LogFactory.get();
	
	private Connection conn;
	private String dbUrl;
	private String dbUser;
	private String dbType;
	private String password;
	private String driverClass;
	private String name;	
	
	private static Map<String, String> driverClasses = new HashMap<String, String>();
	
	static {
		driverClasses.put("mysql", "com.mysql.jdbc.Driver");
		driverClasses.put("oracle", "oracle.jdbc.driver.OracleDriver");
		driverClasses.put("db2", "com.ibm.db2.jdbc.app.DB2Driver");
		driverClasses.put("postgresql", "org.postgresql.Driver");
	}
			
	private TestDB(String dbUrl, String dbUser, String dbType, String password, String driverClass, String name) {
		super();
		this.dbUrl = dbUrl;
		this.dbUser = dbUser;
		this.dbType = dbType;
		this.password = password;
		this.driverClass = driverClass;
		this.name = name;
	}

	public static TestDB getInstance(Props p, String name) throws Exception {
		String type = p.getStr("db." + name + ".type").trim();
		String driverClass = p.getStr("db." + name + ".driverClass");
		if (StrUtil.isEmpty(driverClass)) driverClass = driverClasses.get(type);
		
		if (StrUtil.isEmpty(driverClass)) {
			throw new NoClassDefFoundError("不支持的数据库类型" + type);
		}
		
		TestDB db = new TestDB(p.getStr("db." + name + ".jdbcUrl").trim(), p.getStr("db." + name + ".user").trim(), type, p.getStr("db." + name + ".password").trim(), driverClass.trim(), name);
		try {
			db.connect();
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);
		}
		return db;
	}
	
	public String execSql(String sql) {
		String returnStr = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;  	
    	
    	try {
    		ps = conn.prepareStatement(sql);    		
    		rs = ps.executeQuery();
    		while (rs.next()) {
    			//只取第一条记录
    			returnStr = rs.getString(1);
    			break;
    		}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e, "数据库查询出错[{}]", this.name);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}  	
    	return returnStr;
	};
	
	public String execSql(ExecOperater oper) {
		return oper.exec(conn);
	}
	
	public void closeConnection() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName(this.driverClass);
		this.conn = DriverManager.getConnection(dbUrl, dbUser, password);
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
}
