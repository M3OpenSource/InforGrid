// E:\LifeCycle\<host>\grid\<grid>\runtimes\1.11.27\resources\servlet-api-2.5.jar
// javac -cp servlet-api-2.5.jar WEB-INF\classes\net\company\your\HelloWorldServlet.java

package net.company.your;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorldServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("HelloWorld servlet! The time is " + new Date());
		out.println("</body>");
		out.println("</html>");
	}
}