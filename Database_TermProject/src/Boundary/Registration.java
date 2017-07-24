package Boundary;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logicLayer.Register;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Servlet implementation class SignUp
 * 
 * Servlet that handles creation of new user accounts.
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static  String         templateDir = "WEB-INF/templates";
	static  String         signUpTemplateName = "UpdateSuccess.ftl";
	static  String         failureTemplateName = "UpdateFailure.ftl";
	private Map<String, Object> root = new HashMap<String,Object>();
	   private Configuration cfg;
	   
	   
	   /*
	    *  init method used to setup different things when 
	    *  servlet first run. In this case, set up is done for
	    *  freemarker templates
	    * (non-Javadoc)
	    * @see javax.servlet.GenericServlet#init()
	    */
	    public void init() 
	    {
	    	// Prepare the FreeMarker configuration;
	        // - Load templates from the WEB-INF/templates directory of the Web app.
	        //
	        cfg = new Configuration();
	        cfg.setServletContextForTemplateLoading(
	                getServletContext(), 
	                "WEB-INF/templates"
	                );
	    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		
	
		
		Register demo = new Register();
		
		if(demo.checkUser(userName) == false){	
			if(demo.addUser(userName, password, email) == 1){
				System.out.println("success");
				root.put("reason", "Sign Up succesful");
				setUpTemplate(request,response,signUpTemplateName);
			}else{
				System.out.println("failed");
				root.put("reason", "Sign Up failed");
				setUpTemplate(request,response,failureTemplateName);
			}
		}else{
			System.out.println("failed");
			root.put("reason", "Username exists");
			setUpTemplate(request,response,failureTemplateName);
		}
		demo.disconnect();
		
		
	}
	
	public void setUpTemplate(HttpServletRequest request, HttpServletResponse response, String templateToDisplay){
		Template resultTemplate = null;
		BufferedWriter toClient = null;
		 // Load templates from the WEB-INF/templates directory of the Web app.
        //
        try {
            resultTemplate = cfg.getTemplate( templateToDisplay );
        } 
        catch (IOException e) {
            try {
				throw new ServletException( "ShowMainOfficerWindow.doGet: Can't load template in: " + templateDir + ": " + e.toString());
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }

        // Prepare the HTTP response:
        // - Use the charset of template for the output
        // - Use text/html MIME-type
        //
        try {
			toClient = new BufferedWriter( new OutputStreamWriter( response.getOutputStream(), resultTemplate.getEncoding() ) );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        response.setContentType("text/html; charset=" + resultTemplate.getEncoding());
        
        
        try {
            try {
				resultTemplate.process( root, toClient );
				toClient.flush();
				 toClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        } 
        catch (TemplateException e) {
            try {
				throw new ServletException( "Error while processing FreeMarker template", e);
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
		
	}// end of setUpTemplate function

}
