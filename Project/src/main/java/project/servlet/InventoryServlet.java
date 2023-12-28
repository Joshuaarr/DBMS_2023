package project.servlet;

import project.dao.InventoryDao;
import project.dao.CharacterDao;
import project.dao.ItemCategoryDao;
import project.dao.GearDao;
import project.dao.WeaponDao;
import project.model.*;
import project.model.Character;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/manageInventory")
public class InventoryServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Parse request parameters
            int characterID = Integer.parseInt(request.getParameter("characterID"));
            int slotID = Integer.parseInt(request.getParameter("slotID"));
            String itemCategoryType = request.getParameter("itemCategoryType"); // "Gear" or "Weapon"
            // Other parameters...

            CharacterDao characterDao = CharacterDao.getInstance();
            Character character = characterDao.getCharacterByCharacterID(characterID);

            ItemCategory itemCategory;
            ItemCategoryDao itemCategoryDao = ItemCategoryDao.getInstance();
            if ("Gear".equals(itemCategoryType)) {
                Gear gear = new Gear(slotID, itemCategoryType, slotID, null, slotID, slotID, slotID, slotID);
                itemCategory = itemCategoryDao.create(gear);
            } else if ("Weapon".equals(itemCategoryType)) {
                Weapon weapon = new Weapon(slotID, itemCategoryType, slotID, null, slotID, slotID, slotID, null, null, null);
                itemCategory = itemCategoryDao.create(weapon);
            } else {
                throw new IllegalArgumentException("Invalid item category type");
            }

            Inventory inventory = new Inventory(character, slotID, itemCategory, slotID);
            InventoryDao.getInstance().create(inventory);
            request.setAttribute("inventory", inventory);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageInventory.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException | IllegalArgumentException e) {
            throw new ServletException("Error processing request", e);
        }
    }

    // doGet method implementation for retrieving an Inventory
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int characterID = Integer.parseInt(request.getParameter("characterID"));
        int slotID = Integer.parseInt(request.getParameter("slotID"));

        try {
            Inventory inventory = InventoryDao.getInstance().getInventoryByCharacterIDAndSlotID(characterID, slotID);
            request.setAttribute("inventory", inventory);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageInventory.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when retrieving inventory", e);
        }
    }
}
