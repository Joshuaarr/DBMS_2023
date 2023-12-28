package project.servlet;

import project.dao.EquipmentBonusDao;
import project.dao.GearDao;
import project.dao.WeaponDao;
import project.model.EquipmentBonus;
import project.model.EquipmentBonus.AttributeName;
import project.model.Equipment;
import project.model.Gear;
import project.model.Weapon;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/manageEquipmentBonus")
public class EquipmentBonusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemCategoryID = Integer.parseInt(request.getParameter("itemCategoryID"));
        AttributeName attributeName = AttributeName.valueOf(request.getParameter("attributeName"));
        int attributeBonus = Integer.parseInt(request.getParameter("attributeBonus"));

        try {
            Equipment equipment = getEquipmentInstance(itemCategoryID);
            EquipmentBonus equipmentBonus = new EquipmentBonus(equipment, attributeName, attributeBonus);
            EquipmentBonusDao.getInstance().create(equipmentBonus);
            request.setAttribute("equipmentBonus", equipmentBonus);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageEquipmentBonus.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when creating equipment bonus", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemCategoryID = Integer.parseInt(request.getParameter("itemCategoryID"));
        String attributeNameStr = request.getParameter("attributeName");

        try {
            AttributeName attributeName = AttributeName.valueOf(attributeNameStr);
            EquipmentBonus equipmentBonus = EquipmentBonusDao.getInstance().getEquipmentBonusByItemCategoryIDAndAttributeName(itemCategoryID, attributeNameStr);
            request.setAttribute("equipmentBonus", equipmentBonus);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageEquipmentBonus.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "SQL error: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageEquipmentBonus.jsp");
            dispatcher.forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid attribute name: " + attributeNameStr);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageEquipmentBonus.jsp");
            dispatcher.forward(request, response);
        }
    }

    private Equipment getEquipmentInstance(int itemCategoryID) throws SQLException {
        GearDao gearDao = GearDao.getInstance();
        WeaponDao weaponDao = WeaponDao.getInstance();
        Gear gear = gearDao.getGearByItemCategoryID(itemCategoryID);
        if (gear != null) {
            return gear;
        } else {
            Weapon weapon = weaponDao.getWeaponByItemCategoryID(itemCategoryID);
            return weapon;
        }
    }
}
