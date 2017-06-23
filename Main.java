import java.sql.*;
import java.util.*;
public class Main{
	
	final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final static String DB_url = "jdbc:mysql://localhost/employees";
	final static String user = "root";
	final static	 String pass = "usopen321";
	
	public static void main(String [] args){
		
		Connection conn = null;
		Statement stmt = null;
		
		
		getTable();
		
		/*
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection(DB_url, user, pass);
			
			stmt = conn.createStatement();
			String queiry = "Select * from employees";
			
			getTable();
			
			/*
			ResultSet r = stmt.executeQuery(a(2));
			ResultSetMetaData rm = r.getMetaData();
			int columnsNumber = rm.getColumnCount();
			   while (r.next()) {
			       for (int i = 1; i <= columnsNumber; i++) {
			           if (i > 1) System.out.print(",  ");
			           String columnValue = r.getString(i);
			           System.out.print(columnValue + " " + rm.getColumnName(i));
			       }
				   //System.out.println("Department with fewest employees is " + r.getObject(1) + " with a total of " + r.getObject(2) + " employees");
				   System.out.println( "The " + r.getObject(1) + " department with an average female salary of $" + r.getObject(2));
			       //System.out.println("");
			   }
			   */
		/*	
			
		}
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		finally{
			
			try{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				
			}
			try{
				if(conn!= null){
					conn.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			*/
			
		}
	
		
		
		
		
		
	
	
	public static String a(int num){
		
		
		switch(num){
		case 1:return"select dept_name as Department_with_fewest_Employees, count(*) as Total from employees e, current_dept_emp c, departments d where e.emp_no = c.emp_no and c.dept_no = d.dept_no group by dept_name order by count(*) asc limit 1";
		//stmt = conn.
		/*
		ResultSet r = stmt.executeQuery(a(2));
		ResultSetMetaData rm = r.getMetaData();
		int columnsNumber = rm.getColumnCount();
		   while (r.next()) {
		       for (int i = 1; i <= columnsNumber; i++) {
		           if (i > 1) System.out.print(",  ");
		           String columnValue = r.getString(i);
		           System.out.print(columnValue + " " + rm.getColumnName(i));
		       }
			   //System.out.println("Department with fewest employees is " + r.getObject(1) + " with a total of " + r.getObject(2) + " employees");
			   System.out.println( "The " + r.getObject(1) + " department with an average female salary of $" + r.getObject(2));
		       //System.out.println("");
		*/
		   
		   
				
				
				
				
				
				
				
				
				
				
				
				
				
		
		case 2: return "select deptName, fAvgSal from (select deptName, cast(avg(tt.femaleAvgSal) as decimal(19,2))  as fAvgSal from(select t.id as id, t.avgSal as FemaleAvgSal, dd.dept_name as deptName from (select any_value(e.emp_no) as id , avg(s.salary) as avgSal from employees e natural join salaries s where e.gender = 'F' and e.emp_no = s.emp_no group by emp_no) as t, dept_emp d, departments dd where t.id = d.emp_no and d.dept_no = dd.dept_no) as tt, departments where tt.deptName = dept_name group by dept_name) as female natural join (select deptName, cast(avg(tt.maleAvgSal) as decimal(19,2))  as MAvgSal from(select t.id as id, t.avgSal as MaleAvgSal, dd.dept_name as deptName from (select any_value(e.emp_no) as id , avg(s.salary) as avgSal from employees e natural join salaries s where e.gender = 'M' and e.emp_no = s.emp_no group by emp_no) as t, dept_emp d, departments dd where t.id = d.emp_no and d.dept_no = dd.dept_no) as tt, departments where tt.deptName = dept_name group by dept_name) as male where fAvgSal > MAvgSal";

				
		case 3: return "select any_value(emp_no) as EmployeeId ,any_value(first_name) as FirstName, any_value(last_name) as LastName, any_value(dept_name) as DeptName, any_value(durationInDays) as Duration_in_Days from (select dm.emp_no, first_name, last_name, datediff((case when dm.to_date = '9999-01-01' then curdate() else dm.to_date end) , dm.from_date) as durationInDays, d.dept_name from employees e, current_dept_emp c, departments d, dept_manager dm where e.emp_no = c.emp_no and c.dept_no = d.dept_no and c.emp_no = dm.emp_no)as tt order by durationInDays desc limit 1";
		
		case 4:	return  "select dpt.dept_name, cast(avg(any_value(tt.emp_total)) as decimal(19,2)) as Avg, count(tt.emp_id) as BornIn from  (select any_value(d.emp_no) as emp_id, any_value(d.dept_no) as dept_number, avg(s.salary) as emp_total from employees e, dept_emp d, salaries s where (e.birth_date > '1949-12-31' and e.birth_date < '1960-01-01') and e.emp_no = d.emp_no and d.emp_no = s.emp_no group by d.emp_no) as tt, departments dpt where tt.dept_number = dpt.dept_no group by dept_name";
		case 5: return "select dpt.dept_name, cast(avg(any_value(tt.emp_total)) as decimal(19,2)) as Avg, count(tt.emp_id) as BornIn from  (select any_value(d.emp_no) as emp_id, any_value(d.dept_no) as dept_number, avg(s.salary) as emp_total from employees e, dept_emp d, salaries s where (e.birth_date > '1959-12-31' and e.birth_date < '1970-01-01') and e.emp_no = d.emp_no and d.emp_no = s.emp_no group by d.emp_no) as tt, departments dpt where tt.dept_number = dpt.dept_no group by dept_name";
		case 6: return "select dpt.dept_name, cast(avg(any_value(tt.emp_total)) as decimal(19,2)) as Avg, count(tt.emp_id) as BornIn from  (select any_value(d.emp_no) as emp_id, any_value(d.dept_no) as dept_number, avg(s.salary) as emp_total from employees e, dept_emp d, salaries s where (e.birth_date > '1969-12-31' and e.birth_date < '1980-01-01') and e.emp_no = d.emp_no and d.emp_no = s.emp_no group by d.emp_no) as tt, departments dpt where tt.dept_number = dpt.dept_no group by dept_name";
		
		case 7: return "select dpt.dept_name, cast(avg(any_value(tt.emp_total)) as decimal(19,2)) as Avg, count(tt.emp_id) as BornIn from  (select any_value(d.emp_no) as emp_id, any_value(d.dept_no) as dept_number, avg(s.salary) as emp_total from employees e, dept_emp d, salaries s where (e.birth_date > '1979-12-31' and e.birth_date < '1990-01-01') and e.emp_no = d.emp_no and d.emp_no = s.emp_no group by d.emp_no) as tt, departments dpt where tt.dept_number = dpt.dept_no group by dept_name";
		
		case 8: return "select dpt.dept_name, cast(avg(any_value(tt.emp_total)) as decimal(19,2)) as Avg, count(tt.emp_id) as BornIn from  (select any_value(d.emp_no) as emp_id, any_value(d.dept_no) as dept_number, avg(s.salary) as emp_total from employees e, dept_emp d, salaries s where (e.birth_date > '1989-12-31' and e.birth_date < '2000-01-01') and e.emp_no = d.emp_no and d.emp_no = s.emp_no group by d.emp_no) as tt, departments dpt where tt.dept_number = dpt.dept_no group by dept_name";
		
		case 9: return "select dpt.dept_name, cast(avg(any_value(tt.emp_total)) as decimal(19,2)) as Avg, count(tt.emp_id) as BornIn from  (select any_value(d.emp_no) as emp_id, any_value(d.dept_no) as dept_number, avg(s.salary) as emp_total from employees e, dept_emp d, salaries s where (e.birth_date > '1959-12-31' and e.birth_date < '1970-01-01') and e.emp_no = d.emp_no and d.emp_no = s.emp_no group by d.emp_no) as tt, departments dpt where tt.dept_number = dpt.dept_no group by dept_name";

	
		
		case 10: return "select e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, d.dept_name, s.salary from employees e, departments d, dept_manager dm, salaries s where e.gender = 'F' and e.birth_date < '1990-1-1' and e.emp_no = dm.emp_no and dm.dept_no = d.dept_no and dm.emp_no = s.emp_no and s.salary > 80000 and s.to_date = '9999-01-01'  limit 100";
		}
		return null;
		
		
	}
	
	
	public static List <String> getTable(){
		
		
		List <String> list = new ArrayList <String> ();
		Connection conn = null;
		Statement stmt = null;
		String table = null;
		
		try{
			
			
			
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_url, user, pass);
			
			stmt = conn.createStatement();
			String queiry = "Select * from employees";
			
			
			
			//Get Department with lowest number of employees
			ResultSet r = stmt.executeQuery(a(1));
			ResultSetMetaData rm = r.getMetaData();
			int columnsNumber = rm.getColumnCount();
			while(r.next()){
				
				table = "Department  " + r.getObject(1) + " has " + r.getObject(2) + " Employees";
				System.out.println(table);
				
				
			}
			
			list.add(table);
			
			table = "";
			
			
			r = stmt.executeQuery(a(2));
			rm = r.getMetaData();
			columnsNumber = rm.getColumnCount();
			   while (r.next()) {
			       
				 
				  table = table.concat("The " + r.getObject(1) + " department with an average female salary of $" + r.getObject(2) +"\n");
				   
			       
				   	System.out.println("lol");
			
			
			
			
			
			
			
			   }
			   System.out.println(table);
			   
			  list.add(table);
			  
			  table = "";
			  
			  r = stmt.executeQuery(a(3));
			  rm = r.getMetaData();
			  columnsNumber = rm.getColumnCount();
			  
			  while(r.next()){
				  
				  table = table.concat("Manager with the longest duration is " + r.getObject(2) + " " + r.getObject(3) + " in the department of "
						  + r.getObject(4) + " with the duration of " + r.getObject(5)) + " days";
				  
			  }
			  System.out.println(table);
			  list.add(table);
			  
			  table = "Employees born in the 50's\n";
			  table = table.concat("Department          " + "Total Employees   " + "Avg Salary\n");
			  r = stmt.executeQuery(a(4));
			  rm = r.getMetaData();
			  columnsNumber = rm.getColumnCount();
			  if(r.next()){
				  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) + "\n" );
				  while(r.next()){
				  
				  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) + "\n" );
				  
				  
			 
				  }
			  }
			  else{
				  table = "No Employees born in the 50s exsists";
			  }
			  System.out.println(table);
			  list.add(table);
			  
			  
			  table = "Employees born in the 60s's\n";
			  table = table.concat("Department          " + "Total Employees   " + "Avg Salary\n");
			  r = stmt.executeQuery(a(5));
			  rm = r.getMetaData();
			  columnsNumber = rm.getColumnCount();
			  if(r.next()){
				  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) + "\n" );
				  while(r.next()){
				  
				  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) + "\n" );
				  
				  
			 
				  }
			  }
			  else{
				  table = "No Employees born in the 60s exsists";
			  }
			  list.add(table);
			  
			 System.out.println(table);
			  
			  
			  table = "Employees born in the 70's\n";
			  table = table.concat("Department          " + "Total Employees   " + "Avg Salary\n");
			  r = stmt.executeQuery(a(6));
			  rm = r.getMetaData();
			  columnsNumber = rm.getColumnCount();
			  if(r.next()){
				  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) + "\n" );
				  while(r.next()){
				  
				  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) + "\n" );
				  
				  
			 
				  }
			  }
			  else{
				  table = "No Employees born in the 70s exsists";
			  }
			  System.out.println(table);
			  
			  list.add(table);
			  
			  table = "Employees born in the 80s's\n";
			  table = table.concat("Department          " + "Total Employees   " + "Avg Salary\n");
			  r = stmt.executeQuery(a(7));
			  rm = r.getMetaData();
			  columnsNumber = rm.getColumnCount();
			  if(r.next()){
				  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) + "\n" );
				  while(r.next()){
				  
				  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) + "\n" );
				  
				  
			 
				  }
			  }
			  else{
				  table = "No Employees born in the 80s exsists";
			  }
			  System.out.println(table);
			  list.add(table);
			  
			  table = "Employees born in the 90's\n";
			  table = table.concat("Department          " + "Total Employees   " + "Avg Salary\n");
			  r = stmt.executeQuery(a(8));
			  rm = r.getMetaData();
			  columnsNumber = rm.getColumnCount();
			  if(r.next()){
				  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) + "\n" );
				  while(r.next()){
				  
				  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) + "\n" );
				  
				  
			 
				  }
			  }
			  else{
				  table = "No Employees born in the 90s exsists";
			  }
			  System.out.println(table);
			  list.add(table);
		}
		
		
		
		
		
		
		
	
		catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }
		finally{
			
			try{
				if(stmt != null)
					stmt.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				
			}
			try{
				if(conn!= null){
					conn.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			
		}
		return null;
		
		
	}
	
	
	

	
}