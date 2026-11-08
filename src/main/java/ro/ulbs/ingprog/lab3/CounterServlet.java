package ro.ulbs.ingprog.lab3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CounterServlet", value = "/CounterServlet")
public class CounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        update(request, "CounterGetName", "WarningGet");
        status(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        update(request, "CounterPostName", "WarningPost");
        status(request, response);
    }


    private int parse(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private void update(HttpServletRequest request, String counterName, String warningName) {
        HttpSession session = request.getSession();
        Integer counter = (Integer) session.getAttribute(counterName);
        if (counter == null) counter = 0;

        counter += parse(request.getParameter("inc"));
        counter -= parse(request.getParameter("dec"));
        session.setAttribute(counterName, counter);


        if (counter == 0) {
            session.setAttribute(warningName, true);
        } else {
            session.removeAttribute(warningName);
        }
    }

    private void status(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Welcome to Counter Servlet</h2>");
        out.println("<br> Counter for GET: " + session.getAttribute("CounterGetName"));
        if (session.getAttribute("WarningGet") != null) {
            out.println(" <b>WARNING GET</b>");
        }
        out.println("<br> Counter for POST: " + session.getAttribute("CounterPostName"));
        if (session.getAttribute("WarningPost") != null) {
            out.println(" <b>WARNING POST</b>");
        }
    }
}