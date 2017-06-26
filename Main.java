import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.*;


public class Main{
	
	final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final static String DB_url = "jdbc:mysql://localhost/employees";
	final static String user = "root";
	final static	 String pass = "usopen321";
	
	public static void main(String [] args){
		
		Connection conn = null;
		Statement stmt = null;
		
		List <String> db_return = getTable();
		
		
		
		List<JTextArea> all = new ArrayList <JTextArea>();
		
		
		for(int i = 0; i < db_return.size(); i++){
			JTextArea j = new JTextArea(db_return.get(i));
			//j.setBackground(New Color("Black")));
			
			j.setEditable(false);
			
			
			
			all.add(j);
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				JFrame frame = new JFrame("Employee DataBase");
				frame.setSize(1000, 1000);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				Container container = new Container();
				
				
				
				
				//JLabel l1 = new JLabel("LOL");
				
				
				//frame.getContentPane().add(button1);
				
				//frame.setLayout(new FlowLayout(frame, FlowLayout.CENTER));
				
				frame.setLayout(new FlowLayout());
				
				
				JButton button1 = new JButton("List department(s) with minimum number of employees");
		        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		     
		        frame.getContentPane().add(button1);
		        //frame.getContentPane().add(all.get(0), FlowLayout.CENTER);
		        
		        
		        
		        
		       
		        
		        JButton button2 = new JButton("List department(s) with maximum ratio of average female salaries to average men salaries ");
		        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		        container.add(button2);
		        frame.getContentPane().add(button2);
		        
		        
		        
		        JButton button3 = new JButton("List manager(s) who holds office for the longest duration");
		        button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		        container.add(button3);
		        frame.getContentPane().add(button3);
		        
		        
		        JButton button4 = new JButton("For each department, list number of employees born in each decade and their average salaries");
		        button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		        container.add(button4);
		        frame.getContentPane().add(button4);
		        
		        JButton button5 = new JButton("List employees, who are female, born before Jan 1, 1990, makes more than 80K annually and hold a manager position");
		        button5.setAlignmentX(Component.CENTER_ALIGNMENT);
		        container.add(button5);
		        frame.getContentPane().add(button5);
		        
		        frame.getContentPane().add(all.get(0));
		        frame.getContentPane().add(all.get(1));
		        frame.getContentPane().add(all.get(2));
		        frame.getContentPane().add(all.get(3));
		        frame.getContentPane().add(all.get(4));
		        frame.getContentPane().add(all.get(5));
		        frame.getContentPane().add(all.get(6));
		        frame.getContentPane().add(all.get(7));
		        frame.getContentPane().add(all.get(8));
		       // frame.getContentPane().add(all.get(9));
		        
		        all.get(0).setVisible(true);
		        
		        all.get(0).setVisible(false);
		        
		       
		        
		        button1.addActionListener(new ActionListener(){

					

					@Override
					public void actionPerformed(ActionEvent e) {
						int num = 0;
						
						System.out.println("LOL");
						
						for(int i = 0; i < all.size(); i++){
							
							if(i != 0){
								all.get(i).setVisible(false);
								System.out.println(i);
								
							}
						}
						
						all.get(num).setVisible(true);
						
					}
					
					
					
				});
		        
 button2.addActionListener(new ActionListener(){

					

					@Override
					public void actionPerformed(ActionEvent e) {
						int num = 1;
						
						
						
						for(int i = 0; i < all.size(); i++){
							
						
								all.get(i).setVisible(false);
								System.out.println(i);
								
							
						}
						
						all.get(1).setVisible(true);
						
					}
					
					
					
				});
 
 
 button3.addActionListener(new ActionListener(){

		

		@Override
		public void actionPerformed(ActionEvent e) {
			int num = 1;
			
			
			
			for(int i = 0; i < all.size(); i++){
				
			
					all.get(i).setVisible(false);
					System.out.println(i);
					
				
			}
			
			all.get(2).setVisible(true);
			
			
		}
		
		
		
	});
 
 button4.addActionListener(new ActionListener(){

		

		@Override
		public void actionPerformed(ActionEvent e) {
			int num = 1;
			
			
			
			for(int i = 0; i < all.size(); i++){
				
			
					all.get(i).setVisible(false);
					System.out.println(i);
					
				
			}
			
			all.get(3).setVisible(true);
			all.get(4).setVisible(true);
			all.get(5).setVisible(true);
			all.get(6).setVisible(true);
			all.get(7).setVisible(true);
			
			
		}
		
		
		
	});
 
 button5.addActionListener(new ActionListener(){

		

		@Override
		public void actionPerformed(ActionEvent e) {
			int num = 1;
			
			
			
			for(int i = 0; i < all.size(); i++){
				
			
					all.get(i).setVisible(false);
					System.out.println(i);
					
				
			}
			
			all.get(8).setVisible(true);
			
			
			
		}
		
		
		
	});
		        
		        
				
		        
				//frame.pack();
		        //frame.pack();
				//frame.setSize(1000, 1000);
				
			}
			
			
		});
		
		
		
			
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
		   
		   
				
				
				
				
				
				
				
				
				
				
				
				
				
		
		case 2: return "select deptName, cast(fAvgSal/MAvgSal as decimal(19,4)) as ratio from  (select deptName, cast(avg(tt.femaleAvgSal) as decimal(19,2))  as fAvgSal from(select t.id as id, t.avgSal as FemaleAvgSal, dd.dept_name as deptName from (select any_value(e.emp_no) as id , avg(s.salary) as avgSal from employees e   natural join salaries s where e.gender = 'F' and e.emp_no = s.emp_no group by emp_no) as t, dept_emp d, departments dd where t.id = d.emp_no and d.dept_no = dd.dept_no) as tt, departments where tt.deptName = dept_name group by dept_name) as female natural join (select deptName, cast(avg(tt.maleAvgSal) as decimal(19,2))  as MAvgSal from(select t.id as id, t.avgSal as MaleAvgSal, dd.dept_name as deptName from (select any_value(e.emp_no) as id , avg(s.salary) as avgSal from employees e natural join salaries s where e.gender = 'M' and e.emp_no = s.emp_no group by emp_no) as t, dept_emp d, departments dd where t.id = d.emp_no and d.dept_no = dd.dept_no) as tt, departments where tt.deptName = dept_name group by dept_name) as male order by (fAvgSal/MAvgSal) desc limit 1";

				
		case 3: return "select any_value(emp_no) as EmployeeId ,any_value(first_name) as FirstName, any_value(last_name) as LastName, any_value(dept_name) as DeptName, any_value(durationInDays) as Duration_in_Days from (select dm.emp_no, first_name, last_name, datediff((case when dm.to_date = '9999-01-01' then curdate() else dm.to_date end) , dm.from_date) as durationInDays, d.dept_name from employees e, current_dept_emp c, departments d, dept_manager dm where e.emp_no = c.emp_no and c.dept_no = d.dept_no and c.emp_no = dm.emp_no)as tt order by durationInDays desc limit 1";
		
		case 4:	return  "select dpt.dept_name, cast(avg(any_value(tt.emp_total)) as decimal(19,2)) as Avg, count(tt.emp_id) as BornIn from  (select any_value(d.emp_no) as emp_id, any_value(d.dept_no) as dept_number, avg(s.salary) as emp_total from employees e, dept_emp d, salaries s where (e.birth_date > '1949-12-31' and e.birth_date < '1960-01-01') and e.emp_no = d.emp_no and d.emp_no = s.emp_no group by d.emp_no) as tt, departments dpt where tt.dept_number = dpt.dept_no group by dept_name";
		case 5: return "select dpt.dept_name, cast(avg(any_value(tt.emp_total)) as decimal(19,2)) as Avg, count(tt.emp_id) as BornIn from  (select any_value(d.emp_no) as emp_id, any_value(d.dept_no) as dept_number, avg(s.salary) as emp_total from employees e, dept_emp d, salaries s where (e.birth_date > '1959-12-31' and e.birth_date < '1970-01-01') and e.emp_no = d.emp_no and d.emp_no = s.emp_no group by d.emp_no) as tt, departments dpt where tt.dept_number = dpt.dept_no group by dept_name";
		case 6: return "select dpt.dept_name, cast(avg(any_value(tt.emp_total)) as decimal(19,2)) as Avg, count(tt.emp_id) as BornIn from  (select any_value(d.emp_no) as emp_id, any_value(d.dept_no) as dept_number, avg(s.salary) as emp_total from employees e, dept_emp d, salaries s where (e.birth_date > '1969-12-31' and e.birth_date < '1980-01-01') and e.emp_no = d.emp_no and d.emp_no = s.emp_no group by d.emp_no) as tt, departments dpt where tt.dept_number = dpt.dept_no group by dept_name";
		
		case 7: return "select dpt.dept_name, cast(avg(any_value(tt.emp_total)) as decimal(19,2)) as Avg, count(tt.emp_id) as BornIn from  (select any_value(d.emp_no) as emp_id, any_value(d.dept_no) as dept_number, avg(s.salary) as emp_total from employees e, dept_emp d, salaries s where (e.birth_date > '1979-12-31' and e.birth_date < '1990-01-01') and e.emp_no = d.emp_no and d.emp_no = s.emp_no group by d.emp_no) as tt, departments dpt where tt.dept_number = dpt.dept_no group by dept_name";
		
		case 8: return "select dpt.dept_name, cast(avg(any_value(tt.emp_total)) as decimal(19,2)) as Avg, count(tt.emp_id) as BornIn from  (select any_value(d.emp_no) as emp_id, any_value(d.dept_no) as dept_number, avg(s.salary) as emp_total from employees e, dept_emp d, salaries s where (e.birth_date > '1989-12-31' and e.birth_date < '2000-01-01') and e.emp_no = d.emp_no and d.emp_no = s.emp_no group by d.emp_no) as tt, departments dpt where tt.dept_number = dpt.dept_no group by dept_name";
		
		case 9: return "select e.emp_no, e.first_name, e.last_name, e.birth_date, e.gender, d.dept_name, s.salary from employees e, departments d, dept_manager dm, salaries s where e.gender = 'F' and e.birth_date < '1990-1-1' and e.emp_no = dm.emp_no and dm.dept_no = d.dept_no and dm.emp_no = s.emp_no and s.salary > 80000 and s.to_date = '9999-01-01'  limit 100";
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
			  
			  
			  
			 
			  
			  table = "";
			  table = table.concat("emp_no  " + "first_name   " + "last_name   " + "birth_date     " + "gender  " + "dept_name    " + "salary\n");
			  r = stmt.executeQuery(a(9));
			  rm = r.getMetaData();
			  columnsNumber = rm.getColumnCount();
			  if(r.next()){
				  System.out.println("WJATTT");
				  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) +  "    "  + r.getObject(4) +    
						  "    " + r.getObject(5)  + "  " + r.getObject(6) + "    $" + r.getObject(7) + "\n" );
				  while(r.next()){
				  
					  table = table.concat(r.getObject(1) +"   " + r.getObject(2) + "   " +  r.getObject(3) +  "    "  + r.getObject(4) +    
							  "    " + r.getObject(5)  + "  " + r.getObject(6) + "    $" + r.getObject(7) + "\n" );
				  System.out.println("lololol");
			 
				  }
			  }
			  
			 list.add(table);
			  
			  System.out.println(table);
			  
			  System.out.println("A TEST TO SEE ALL STRINGS IN THE LIST\n\n");
			  
			  for(int i = 0; i < list.size(); i++){
				  
				  System.out.println(list.get(i) + "\n\n");
			  }
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
		
		return list;
		
		
	}
	
	
	

	
}