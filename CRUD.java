import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CRUD {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection=null;
		Statement statement=null;
		ResultSet resultset=null;
		

		String url="jdbc:mysql://localhost:3306/nathan_sir";
		String username="root";
		String password="Kritesh21@";
		
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter:\n1. For Read\n2. For Inserting\n3. For deleting\n4. For updating");
		Integer number=sc.nextInt();

	
		

		
		try {
			connection=DriverManager.getConnection(url, username, password);
			
			if(connection!=null) {
				statement=connection.createStatement();
			}
			if(statement!=null) {
			switch(number) {
			case 1:
				
					String sqlSelectQuery="select * from dept";
					resultset=statement.executeQuery(sqlSelectQuery);
					if(resultset!=null) {
						while(resultset.next()) {
							Integer i=resultset.getInt(1);
							String s=resultset.getString(2);
							String s1=resultset.getString(3);
							System.out.println(i+ " "+s+" "+s1);
						}
						break;
			}
				
			
			case 2:
			

				
				if(statement!=null) {
				System.out.println("enter dept number ");
				Integer deptno=sc.nextInt();
				System.out.println("enter dept name ");
				String dname=sc.next();
				System.out.println("enter location ");
				String loc=sc.next();
				String s9=String.format("insert into dept(`deptno`,`dname`,`loc`) values (%d,'%s','%s')",deptno,dname,loc);
				System.out.println(s9);
				Integer noOfrows=statement.executeUpdate(s9);
				System.out.println("number of rows updated "+noOfrows);
				break;
				}	
			case 3:
			
				
					statement=connection.createStatement();
				
				
				System.out.println("enter the dept number to delete the record");
				Integer deptno=sc.nextInt();
				String s2=String.format("delete from dept where deptno=%d", deptno);
				Integer noOfrow=statement.executeUpdate(s2);
				System.out.println("no of rows deleted "+noOfrow);
				break;
				
				
			case 4:
				
				
					statement=connection.createStatement();
				
				
				System.out.println("enter the dept number to update details");
				 deptno=sc.nextInt();
				 System.out.println("enter new dept name ");
				 String dname=sc.next();
				 System.out.println("enter new location ");
				 String loc=sc.next();
				String s3=String.format("update dept set dname ='%s',loc='%s' where deptno=%d",dname,loc,deptno);
				Integer i=statement.executeUpdate(s3);
				System.out.println("number of rows updated "+i);
				break;
			}
			
			
			}	
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		if(resultset!=null)
			resultset.close();
		if(statement!=null)
			statement.close();
		if(connection!=null)
			connection.close();
		if(sc!=null)
			sc.close();
		}
	



	}
}
