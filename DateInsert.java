import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import iNeuron.JavaUtil;

public class DateInsert {

	public static void main(String[] args)throws ParseException {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement ps=null;
		Scanner sc=new Scanner(System.in);
		try {
			
			con=JavaUtil.getJdbcConnection();
			if(con!=null) {
				System.out.println("Enter name"); 
				String name=sc.next();
				System.out.println("Enter dob(dd-MM-yyyy)");
				String dob=sc.next();
				System.out.println("Enter address ");
				String address=sc.next();
				System.out.println("Enter gender ");
				String gender=sc.next();
				System.out.println("enter date of joining ");
				String DOJ=sc.next();
				System.out.println("enter date of marriage");
				String DOM=sc.next();
				
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				Date uDate=sdf.parse(dob);
				Date uDOJ=sdf.parse(DOJ);
				Date uDOM=sdf.parse(DOM);
				
				long l=uDate.getTime();
				java.sql.Date sDate=new java.sql.Date(l);
				
				long l1=uDOJ.getTime();
				java.sql.Date sDOJ=new java.sql.Date(l1);
				
				long l2=uDOM.getTime();
				java.sql.Date sDOM=new java.sql.Date(l2);
				
				String insertQuery="insert into information(`name`,`dob`,`address`,`gender`,`DOJ`,`DOM`) values(?,?,?,?,?,?)";
				ps=con.prepareStatement(insertQuery);
				if(ps!=null) {
					ps.setString(1, name);
					ps.setDate(2, sDate);
					ps.setString(3, address);
					ps.setString(4, gender);
					ps.setDate(5, sDOJ);
					ps.setDate(6, sDOM);
					
					Integer noOfrows=ps.executeUpdate();
					System.out.println("no of rows inserted "+noOfrows);
				}
			}
		}catch(ParseException pe) {
			pe.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JavaUtil.closeConnection(null, ps, con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(sc!=null) {
					sc.close();
				}
			}
			
		}
		

	}

}
