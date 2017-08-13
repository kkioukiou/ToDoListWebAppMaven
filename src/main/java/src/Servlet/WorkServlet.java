package src.Servlet;

import src.WorkPackage.WorkClass;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/WorkServlet")
public class WorkServlet extends HttpServlet {

    private WorkClass workClass;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workClass = new WorkClass();
        workClass.inputSimpleItem(req);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        workClass = new WorkClass();
        resp.getWriter().write(workClass.getAllItems());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workClass = new WorkClass();
        workClass.deleteSimpleItem(req);
    }


}

