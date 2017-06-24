import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.layout.GridData;


public class Interface {

	protected Shell shlEmployeesDatabase;
	private List<String> info = Main.getTable();

	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Interface window = new Interface();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		 Display.getDefault().syncExec(
				   new Runnable(){
				       public void run(){
				        // your UI code  
				    		//Display display = Display.getDefault();
				    		createContents();
				    		shlEmployeesDatabase.open();
				    		shlEmployeesDatabase.layout();
				    		while (!shlEmployeesDatabase.isDisposed()) {
				    			//if (!display.readAndDispatch()) {
				    				//display.sleep();
				    			//}
				    		}
				      } 
				   }
				 );
		
	
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		
		//System.out.println(info.isEmpty());
		
		
		shlEmployeesDatabase = new Shell();
		shlEmployeesDatabase.setSize(1000, 1000);
		shlEmployeesDatabase.setText("Employees Database");
		GridLayout gl_shlEmployeesDatabase = new GridLayout(1, false);
		gl_shlEmployeesDatabase.verticalSpacing = 10;
		gl_shlEmployeesDatabase.marginRight = 5;
		gl_shlEmployeesDatabase.marginBottom = 5;
		shlEmployeesDatabase.setLayout(gl_shlEmployeesDatabase);
		
		Label lblNewLabel = new Label(shlEmployeesDatabase, SWT.NONE);
		lblNewLabel.setText("Click button below to see dept with fewest employees");
			
			Label lblNewLabel_4 = new Label(shlEmployeesDatabase, SWT.NONE);
			new Label(shlEmployeesDatabase, SWT.NONE);
			Button btnClickHere = new Button(shlEmployeesDatabase, SWT.NONE);
			btnClickHere.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					lblNewLabel_4.setSize(500,500);
					lblNewLabel_4.setText(info.get(0));
				
				}
			});
			btnClickHere.setText("Click");
			new Label(shlEmployeesDatabase, SWT.NONE);
		
			
			Label lblNewLabel_1 = new Label(shlEmployeesDatabase, SWT.NONE);
			lblNewLabel_1.setText("Click to see dept with max ratio of avg female salaries to avg men salaries");
		
		Label lblDeptWithFemaleavgsal = new Label(shlEmployeesDatabase, SWT.NONE);
		new Label(shlEmployeesDatabase, SWT.NONE);
		
		Button btnClick = new Button(shlEmployeesDatabase, SWT.NONE);
		btnClick.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblDeptWithFemaleavgsal.setSize(500,500);
				lblDeptWithFemaleavgsal.setText(info.get(1));
			}
		});
		
	
		btnClick.setText("Click");
		new Label(shlEmployeesDatabase, SWT.NONE);
		
		Label lblNewLabel_3 = new Label(shlEmployeesDatabase, SWT.NONE);
		lblNewLabel_3.setText("Manager with longest tenure");
		
		Label label = new Label(shlEmployeesDatabase, SWT.NONE);
		new Label(shlEmployeesDatabase, SWT.NONE);
		
		Button btnNewButton = new Button(shlEmployeesDatabase, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				label.setSize(1000,500);
				label.setText(info.get(2));
			}
		});
		btnNewButton.setText("Click");
		new Label(shlEmployeesDatabase, SWT.NONE);
		
		Label lblNumberOfEmployees = new Label(shlEmployeesDatabase, SWT.NONE);
		lblNumberOfEmployees.setText("Number of employees born in each decade and their average salaries");
		
		Label lblOutputHere = new Label(shlEmployeesDatabase, SWT.NONE);
		lblOutputHere.setText("Output here");
		new Label(shlEmployeesDatabase, SWT.NONE);
		
		Button btnClick_1 = new Button(shlEmployeesDatabase, SWT.NONE);
		btnClick_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblOutputHere.setSize(1000,1000);
				lblOutputHere.setText(info.get(3) + info.get(4) + info.get(5) + info.get(6) + info.get(7));

			}
		});
		btnClick_1.setText("Click");
		new Label(shlEmployeesDatabase, SWT.NONE);
		
		Label lblEmployeesWhoAre = new Label(shlEmployeesDatabase, SWT.NONE);
		lblEmployeesWhoAre.setText("Employees, who are female, born before Jan 1, 1990, makes more than 80K annually and hold a manager position");
		
		Label lblOutputHere_1 = new Label(shlEmployeesDatabase, SWT.NONE);
		lblOutputHere_1.setText("Output here");
		new Label(shlEmployeesDatabase, SWT.NONE);
		
		Button btnNewButton_1 = new Button(shlEmployeesDatabase, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lblOutputHere_1.setSize(1000,1000);
			}
		});
		btnNewButton_1.setText("Click");

	}
}
