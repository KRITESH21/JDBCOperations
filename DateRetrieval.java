import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import iNeuron.JavaUtil;

public class DateRetrieval {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		Connection con= null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Scanner sc=new Scanner(System.in);
		System.out.println("enter name ");
		String name=sc.next();
		
		try {
			con=JavaUtil.getJdbcConnection();
			if(con!=null) {
				String selectQuery="select * from information where name=? ";
				ps=con.prepareStatement(selectQuery);
				if(ps!=null) {
					ps.setString(1, name);
					rs=ps.executeQuery();
					if(rs.next()) {
						String s1=rs.getString(1);
						java.sql.Date s2=rs.getDate(2);
						String s3=rs.getString(3);
						String s4=rs.getString(4);
						java.sql.Date s5=rs.getDate(5);
						java.sql.Date s6=rs.getDate(6);
						
						SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
						String s7=sdf.format(s2);
						String s8=sdf.format(s5);
						String s9=sdf.format(s6);
						System.out.println(s1+" "+s3+"  "+s4+"  "+s7+"  "+s8+"  "+s9);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JavaUtil.closeConnection(rs, ps, con);
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
