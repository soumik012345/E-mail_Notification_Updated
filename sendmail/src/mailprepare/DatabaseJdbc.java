package mailprepare;
import java.io.FileInputStream;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseJdbc {

	public String Dbconnection () {
     	ResultSet rs = null;
//		ResultSet rs2 = null;
//		ResultSet rs3 = null;
//		String[] res = new String[3];
		String tablename = "";
		
		
		
	try {
		
		Properties p = new Properties();
		FileInputStream is = new FileInputStream("config2.properties");
		p.load(is); 
		String query = p.getProperty("sql");
		String forName = p.getProperty("forName");
		String dbcon = p.getProperty("dbcon");
		String user = p.getProperty("user");
		String pass = p.getProperty("pass");
		String prousr = p.getProperty("prouser");
		String procon = p.getProperty("procon");
		String propass = p.getProperty("propass");
		String prosql = p.getProperty("prosql");
		String sql2 = p.getProperty("sql2");
		String sql3 = p.getProperty("sql3");
		
		
		Class.forName(p.getProperty("forName"));
		Connection con = DriverManager.getConnection(  
			p.getProperty("procon"),p.getProperty("prouser"),p.getProperty("propass"));
	
		Statement st =con.createStatement(); 
		rs= st.executeQuery(p.getProperty("prosql"));
//		rs2= st.executeQuery(p.getProperty("sql2"));
//		rs3= st.executeQuery(p.getProperty("sql3"));
		while(rs.next()) {
			tablename = rs.getString("tableName")+ " , \r\n " + tablename;
			System.out.println(tablename);
		}
		
//		while(rs2.next()) {
//			res[1] = rs2.getString("uniqueCode");
//			System.out.println(res[1]);
//		}
//		
//		while(rs2.next()) {
//			//res[2] = rs3.getString("Utilization-Huawei")+"  "+rs3.getString("Utilization-Ericsson");
//			res[2] = rs3.getString("uniqueCode");
//		}
		
		st.close();
		con.close();
	} catch (Exception e) {
		System.out.println("Dbconnection Exception -- ");
		e.printStackTrace();
	} 
	//System.out.println(tablename);
	return tablename;



	}
	
	
	
	public String[] Dbconnectiontwo () {

     	ResultSet rs2 = null;
     	
     	String[] res = new String[3];

		//String tablename2 = "";
		
		
		
	try {
		
		Properties p = new Properties();
		FileInputStream is = new FileInputStream("config2.properties");
		p.load(is); 
		String query = p.getProperty("sql");
		String forName = p.getProperty("forName");
		String dbcon = p.getProperty("dbcon");
		String user = p.getProperty("user");
		String pass = p.getProperty("pass");
		String prousr = p.getProperty("prouser");
		String procon = p.getProperty("procon");
		String propass = p.getProperty("propass");
		String prosql = p.getProperty("prosql");
		String sql2 = p.getProperty("sql2");
		String sql3 = p.getProperty("sql3");
		
		
		Class.forName(p.getProperty("forName"));
		Connection con = DriverManager.getConnection(  
			p.getProperty("procon"),p.getProperty("prouser"),p.getProperty("propass"));
	
		Statement st =con.createStatement(); 
		rs2= st.executeQuery(p.getProperty("sql2"));
		
		while(rs2.next()) {
			// + ", \r\n "  + res[0]
			res[0] =rs2.getString("Adaptive-Modulation-Huawei");
			res[1] =rs2.getString("Adaptive-Modulation-Ericsson");
			//+ ", \r \n "+ res[1]
			System.out.println(res);
		}
		
		st.close();
		con.close();
	} catch (Exception e) {
		System.out.println("Dbconnection Exception2 -- ");
		e.printStackTrace();
	} 
	//System.out.println(tablename2);
	return res;

	}
	
	public String[] Dbconnectionthree () {
		ResultSet rs3 = null;
		String tablename3 = "";
		String[] res = new String[3];
				
	try {
		
		Properties p = new Properties();
		FileInputStream is = new FileInputStream("config2.properties");
		p.load(is); 
		String query = p.getProperty("sql");
		String forName = p.getProperty("forName");
		String dbcon = p.getProperty("dbcon");
		String user = p.getProperty("user");
		String pass = p.getProperty("pass");
		String prousr = p.getProperty("prouser");
		String procon = p.getProperty("procon");
		String propass = p.getProperty("propass");
		String prosql = p.getProperty("prosql");
		String sql2 = p.getProperty("sql2");
		String sql3 = p.getProperty("sql3");
		
		
		Class.forName(p.getProperty("forName"));
		Connection con = DriverManager.getConnection(  
			p.getProperty("procon"),p.getProperty("prouser"),p.getProperty("propass"));
	
		Statement st =con.createStatement(); 
		rs3 = st.executeQuery(p.getProperty("sql3"));
	
		while(rs3.next()) {
			// + ", \r\n "+ res[0]
			res[0] =rs3.getString("Utilization-Huawei") ;
			res[1] =rs3.getString("Utilization-Ericsson");
			//  + ", \r \n " + res[1]
		
			//tablename3 = "\t \t" + rs3.getString("Utilization-Huawei")+"\t \t \t \t \t " + rs3.getString("Utilization-Ericsson") + " , \r\n " + tablename3 ;;
			System.out.println(res);
		}
		
		st.close();
		con.close();
	} catch (Exception e) {
		System.out.println("Dbconnection Exception3 -- ");
		e.printStackTrace();
	} 
	//System.out.println(tablename3);
	return res;

	}
	
	
	
	
	
	}
	

