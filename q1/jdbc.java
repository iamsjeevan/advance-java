package jdbc;
import java.sql.*;

public class jdbc {
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306";
		String user="root";
		String password="";
		Connection conn=null;
		Statement stmt=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url,user,password);
			stmt=conn.createStatement();
			String CreateDb="CREATE DATABASE IF NOT EXISTS employee";
			stmt.executeUpdate(CreateDb);
			stmt.executeUpdate("USE employee");
			String createTable = """
					CREATE TABLE IF NOT EXISTS employee(
					Id INT,
					Fname VARCHAR(50),
					Lname VARCHAR(50),
					Project VARCHAR(50),
					Salary INT);
					""";
			stmt.executeUpdate(createTable);
			String insertValues="""
					INSERT INTO employee(Id,Fname,Lname,Project,Salary) VALUES
					(1,'john','doe','Web Development',12000),
					(2, 'Jane', 'Smith', 'Data Analysis', 60000);
					""";
			stmt.executeUpdate(insertValues);
			String Queryall="SELECT * FROM employee ";
			ResultSet rsAll=stmt.executeQuery(Queryall);
			while(rsAll.next()) {
				System.out.println("ID: "+rsAll.getInt("ID")+
						", Fname: "+rsAll.getString("Fname") +
						", Lname: "+rsAll.getString("Lname")+
						", Project: "+rsAll.getString("Project")+
						", Salary: "+rsAll.getInt("Salary"));
			}
			String QueryWebAll = "SELECT * FROM employee WHERE Project = 'Web Development'";
            ResultSet rsWebAll = stmt.executeQuery(QueryWebAll); // Execute the query

            // Display details of employees working on "Web Development"
            System.out.println("\nEmployees in Web Development:");
            while (rsWebAll.next()) {
                System.out.println("ID: " + rsWebAll.getInt("Id") + 
                        ", Fname: " + rsWebAll.getString("Fname") + 
                        ", Lname: " + rsWebAll.getString("Lname") + 
                        ", Project: " + rsWebAll.getString("Project") + 
                        ", Salary: " + rsWebAll.getInt("Salary"));
            }

			
	              
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
