package project.servlet;

import project.dao.CustomizedEquipmentDao;
import project.model.CustomizedEquipment;
import project.model.Character;
import project.model.CustomizedEquipment.Quality;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/manageCustomizedEquipment")
public class CustomizedEquipmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int ownerID = Integer.parseInt(request.getParameter("ownerID"));
        int slotID = Integer.parseInt(request.getParameter("slotID"));
        String dyeColor = request.getParameter("dyeColor");
        Quality quality = Quality.valueOf(request.getParameter("quality"));
        String condition = request.getParameter("condition");
        int makerID = Integer.parseInt(request.getParameter("makerID"));

        try {
            Character owner = new Character(ownerID, null, condition, condition); 
            Character maker = new Character(makerID, null, condition, condition); 
            CustomizedEquipment equipment = new CustomizedEquipment(owner, slotID, dyeColor, quality, condition, maker);

            CustomizedEquipmentDao.getInstance().create(equipment);
            request.setAttribute("equipment", equipment);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageCustomizedEquipment.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when creating customized equipment", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	int ownerID = (request.getParameter("ownerID") == null || "".equals(request.getParameter("ownerID"))) ? 1 : Integer.parseInt(request.getParameter("ownerID"));
    	
    	int slotID = (request.getParameter("slotID") == null || "".equals(request.getParameter("slotID"))) ? 1 : Integer.parseInt(request.getParameter("slotID"));
    	
//        int ownerID = Integer.parseInt(request.getParameter("ownerID"));
//        int slotID = Integer.parseInt(request.getParameter("slotID"));

        try {
            CustomizedEquipment equipment = CustomizedEquipmentDao.getInstance().getCustomizedEquipmentByOwnerIDAndSlotID(ownerID, slotID);
            request.setAttribute("equipment", equipment);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageCustomizedEquipment.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when fetching customized equipment", e);
        }
    }
}
