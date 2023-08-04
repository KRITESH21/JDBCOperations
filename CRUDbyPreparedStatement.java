import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import iNeuron.JavaUtil;

public class CRUDbyPreparedStatement {

	public static void main(String[] args) throws SQLException {
		
		
		Connection con=null;
		PreparedStatement prstmt=null;
		ResultSet rset=null;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter:\n1. For Reading\n2. For Inserting\n3. For Updating\n4. For deleting");
		Integer number=sc.nextInt();
		
		
		try {
			con=JavaUtil.getJdbcConnection();
			if(con!=null) {
			switch(number) {
			case 1:
				
				String query="select * from dept";
				prstmt=con.prepareStatement(query);
				//prstmt.setInt(1, 10);
				
				if(prstmt!=null) {
					rset=prstmt.executeQuery();
					System.out.println("deptno\tdname\t        loc");
					System.out.println("===========================");
					while(rset.next()) {
						Integer i1=rset.getInt(1);
						String s1=rset.getString(2);
						String s2=rset.getString(3);
						System.out.println(i1+"\t"+s1+"\t"+s2);
					}
				}
				break;
				
			case 2:
				
				System.out.println("Enter dept number ");
				Integer deptno=sc.nextInt();
				System.out.println("Enter dept name ");
				String dname=sc.next();
				System.out.println("Enter dept location ");
				String loc=sc.next();
				String insertQuery="insert into dept(`deptno`,`dname`,`loc`)values(?,?,?)";
				prstmt=con.prepareStatement(insertQuery);
				if(prstmt!=null) {
					prstmt.setInt(1, deptno);
					prstmt.setString(2, dname);
					prstmt.setString(3, loc);
					
				}
				Integer noOfrows=prstmt.executeUpdate();
				System.out.println("rows inserted is "+noOfrows);
				break;
				
			case 3:
				

				System.out.println("Enter dept number ");
				deptno=sc.nextInt();
				System.out.println("Enter dept name ");
			    dname=sc.next();
				System.out.println("Enter dept location ");
				loc=sc.next();
				String updateQuery="update dept set dname=?, loc =? where deptno =?";
				prstmt=con.prepareStatement(updateQuery);
				if(prstmt!=null) {
					prstmt.setString(1, dname);
					prstmt.setString(2, loc);
					prstmt.setInt(3, deptno);
					Integer noOfrow=prstmt.executeUpdate();
					System.out.println("rows updated is "+noOfrow);
				}
				break;
				
			case 4:
				
				System.out.println("Enter dept number ");
				deptno=sc.nextInt();
				String deleteQuery="delete from dept where deptno=?";
				prstmt=con.prepareStatement(deleteQuery);
				if(prstmt!=null) {
					prstmt.setInt(1, deptno);
				}
				Integer deletedrows=prstmt.executeUpdate();
				System.out.println("no of rows deleted "+deletedrows);
				break;
			}
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JavaUtil.closeConnection(rset, prstmt, con);
			if(sc!=null)
				sc.close();
			
		}
	}

	}


