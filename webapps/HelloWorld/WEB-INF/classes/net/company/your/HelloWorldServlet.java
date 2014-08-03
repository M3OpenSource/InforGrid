// E:\LifeCycle\<host>\grid\<grid>\runtimes\1.11.27\resources\servlet-api-2.5.jar
// javac -cp servlet-api-2.5.jar;. net\company\your\HelloWorldServlet.java

package net.company.your;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import net.company.your.library2.*;

public class HelloWorldServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println(HelloWorldLibrary2.getMessage());
		out.println("</body>");
		out.println("</html>");
	}
}