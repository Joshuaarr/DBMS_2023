package project.servlet;

import project.dao.EquipmentDao;
import project.model.Armor;
import project.model.Equipment;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/manageEquipment")
public class EquipmentServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    int itemCategoryID = Integer.parseInt(request.getParameter("itemCategoryID"));
	    String itemName = request.getParameter("itemName");
	    int maxStackSize = Integer.parseInt(request.getParameter("maxStackSize"));
	    double vendorPrice = Double.parseDouble(request.getParameter("vendorPrice"));
	    int itemLevel = Integer.parseInt(request.getParameter("itemLevel"));
	    int requiredLevel = Integer.parseInt(request.getParameter("requiredLevel"));

	    try {
	        Armor armor = new Armor(itemCategoryID, itemName, maxStackSize, vendorPrice, itemLevel, requiredLevel);
	        EquipmentDao.getInstance().create(armor);
	        request.setAttribute("equipment", armor);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("manageEquipment.jsp");
	        dispatcher.forward(request, response);
	    } catch (SQLException e) {
	        throw new ServletException("SQL error when creating equipment", e);
	    }
	}


}
