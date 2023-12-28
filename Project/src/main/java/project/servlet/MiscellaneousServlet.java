package project.servlet;

import project.dao.MiscellaneousDao;
import project.model.Miscellaneous;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/manageMiscellaneous")
public class MiscellaneousServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String itemName = request.getParameter("itemName");
            int maxStackSize = Integer.parseInt(request.getParameter("maxStackSize"));
            double vendorPrice = Double.parseDouble(request.getParameter("vendorPrice"));
            String description = request.getParameter("description");

            Miscellaneous miscellaneous = new Miscellaneous(itemName, maxStackSize, vendorPrice, description);
            MiscellaneousDao miscellaneousDao = MiscellaneousDao.getInstance();
            miscellaneous = miscellaneousDao.create(miscellaneous);

            request.setAttribute("miscellaneous", miscellaneous);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageMiscellaneous.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when creating miscellaneous item", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int itemCategoryID = Integer.parseInt(request.getParameter("itemCategoryID"));

            MiscellaneousDao miscellaneousDao = MiscellaneousDao.getInstance();
            Miscellaneous miscellaneous = miscellaneousDao.getMiscellaneousByItemCategoryID(itemCategoryID);

            request.setAttribute("miscellaneous", miscellaneous);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageMiscellaneous.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when retrieving miscellaneous item", e);
        }
    }
}
