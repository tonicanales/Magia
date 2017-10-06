package com.example.ejb.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.ejb.bussines.Greeting;
import com.example.ejb.domain.Carta;


@WebServlet("/ServletStateLessEjb")
public class ServletStateFulEjb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String STATEFUL_ARRAY_BEAN_KEY = "array_bean";

       
    @EJB
	private Greeting greeting ;

	
    
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<ArrayList<Carta>> mazo3x7;
		String carta;

		Greeting juego = getStatefulCounterArray(request);
		juego.doCont();
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=UTF-8>");
		out.println("<title>Juego de Cartas</title>");
		out.println("<body>");
		
		
		if (juego.getCont() == 4){
			int column = Integer.parseInt(request.getParameter("numero"));
			juego.ultimacarta(column);
			carta = juego.getCarta().getPathImage();
			out.println("<h1>La carta es: <img src='/servletprojectone2/img/" + carta + "' width='70' heigth='70'>");
			out.println("<br><p><a href='../index.html'>Volver a jugar</a></p>");
			juego.resetCont();
		} else {
			int column = Integer.parseInt(request.getParameter("numero"));
			juego.jugar(column);
			mazo3x7 = juego.getMazo3x7();
		out.println("<table>");

		int j=0;
		for(int i=0; i<7;i++){
			out.println("<tr>");
			out.println ("<td WIDTH='150'><img src='/servletprojectone2/img/" + mazo3x7.get(j).get(i).getPathImage() + "' width='70' heigth='70'>" +
						"</td><td WIDTH='150'><img src='/servletprojectone2/img/" + mazo3x7.get(j+1).get(i).getPathImage() + "' width='70' heigth='70'>" + 
						"</td><td><img src='/servletprojectone2/img/" + mazo3x7.get(j+2).get(i).getPathImage() + "' width='70' heigth='70'>" + 
						"</td");
			out.println("</tr>");
			j=0;
		}
		out.println("</table>");
		out.println("<br>");
		out.println("<p>En que columna esta la carta?");
		out.println("<form action='./cartas' method='post'>");
		out.println("<select name='numero'>");
		out.println("<option value='1'>1</option>");
		out.println("<option value='2'>2</option>");
		out.println("<option value='3'>3</option>");
		out.println("</select>");
		out.println("<br>");
		out.println("<input type='submit' value='Envia'>");
		out.println("</form>");
		
		out.println("</body>");
		out.println("</head>");
		out.println("</html>");	
		}
	}




	private  Greeting getStatefulCounterArray(HttpServletRequest request)
	         throws ServletException {
		
	     HttpSession httpSession = request.getSession(true);
	     Greeting statefulTestBean = 
	             (Greeting) httpSession.getAttribute(STATEFUL_ARRAY_BEAN_KEY);
	     
	     
	     if (statefulTestBean == null) {
	         try {
	        	 
	             InitialContext ic = new InitialContext();
	             statefulTestBean =   (Greeting) ic.lookup("java:module/Greeting");
	             httpSession.setAttribute(STATEFUL_ARRAY_BEAN_KEY, statefulTestBean);	          	       
	         } catch (NamingException e) {
	             throw new ServletException(e);
	         }
	     }
	     return statefulTestBean;
	 }

}
