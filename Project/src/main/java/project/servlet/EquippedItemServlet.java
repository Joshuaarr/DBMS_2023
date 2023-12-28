package project.servlet;

import project.dao.EquippedItemDao;
import project.dao.CharacterDao;
import project.model.EquippedItem;
import project.model.Character;
import project.model.EquippedItem.SlotName;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/manageEquippedItem")
public class EquippedItemServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int characterID = Integer.parseInt(request.getParameter("characterID"));
        SlotName slotName = SlotName.valueOf(request.getParameter("slotName"));
        int slotID = Integer.parseInt(request.getParameter("slotID"));

        try {
            CharacterDao characterDao = CharacterDao.getInstance();
            Character character = characterDao.getCharacterByCharacterID(characterID);
            EquippedItem equippedItem = new EquippedItem(character, slotName, slotID);
            EquippedItemDao.getInstance().create(equippedItem);
            request.setAttribute("equippedItem", equippedItem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageEquippedItem.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when creating equipped item", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String slotNameStr = request.getParameter("slotName");
        int characterID = Integer.parseInt(request.getParameter("characterID"));

        try {
            SlotName slotName = SlotName.valueOf(slotNameStr);
            EquippedItem equippedItem = EquippedItemDao.getInstance().getEquippedItemBySlotNameAndCharacterID(slotNameStr, characterID);
            request.setAttribute("equippedItem", equippedItem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageEquippedItem.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", "SQL error: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageEquippedItem.jsp");
            dispatcher.forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid slot name: " + slotNameStr);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageEquippedItem.jsp");
            dispatcher.forward(request, response);
        }
    }
}
