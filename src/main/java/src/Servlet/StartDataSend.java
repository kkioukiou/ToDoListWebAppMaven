package src.Servlet;

import src.WorkPackage.WorkClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/startDataSend")
public class StartDataSend extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req);
        resp.setContentType("application/json");
        WorkClass workClass = new WorkClass();
        resp.getWriter().write(workClass.startMethod());
    }
}
