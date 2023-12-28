package project.servlet;

import project.dao.GearDao;
import project.model.Gear;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/manageGear")
public class GearServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemCategoryID = Integer.parseInt(request.getParameter("itemCategoryID"));
        String itemName = request.getParameter("itemName");
        int maxStackSize = Integer.parseInt(request.getParameter("maxStackSize"));
        double vendorPrice = Double.parseDouble(request.getParameter("vendorPrice"));
        int itemLevel = Integer.parseInt(request.getParameter("itemLevel"));
        int requiredLevel = Integer.parseInt(request.getParameter("requiredLevel"));
        int defenceRating = Integer.parseInt(request.getParameter("defenceRating"));
        int magicDefenceRating = Integer.parseInt(request.getParameter("magicDefenceRating"));

        try {
            Gear gear = new Gear(itemCategoryID, itemName, maxStackSize, vendorPrice, itemLevel, requiredLevel, defenceRating, magicDefenceRating);
            GearDao.getInstance().create(gear);
            request.setAttribute("gear", gear);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageGear.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when creating gear", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	int itemCategoryID = (request.getParameter("itemCategoryID") == null ||  "".equals(request.getParameter("itemCategoryID"))) ? 1 : Integer.parseInt(request.getParameter("itemCategoryID"));
    	
    	
//        int itemCategoryID = Integer.parseInt(request.getParameter("itemCategoryID"));

        try {
            Gear gear = GearDao.getInstance().getGearByItemCategoryID(itemCategoryID);
            request.setAttribute("gear", gear);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageGear.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when retrieving gear", e);
        }
    }
}
