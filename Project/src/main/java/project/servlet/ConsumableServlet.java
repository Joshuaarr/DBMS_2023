package project.servlet;

import project.dao.ConsumableDao;
import project.model.Consumable;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/manageConsumable")
public class ConsumableServlet extends HttpServlet {

    private ConsumableDao consumableDao;

    @Override
    public void init() {
        consumableDao = ConsumableDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    createConsumable(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "create"; // 默认显示创建 Consumable 表单
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "getById":
                getConsumableById(request, response);
                break;
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("createConsumable.jsp");
        dispatcher.forward(request, response);
    }

    private void createConsumable(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemName = request.getParameter("itemName");
        int maxStackSize = Integer.parseInt(request.getParameter("maxStackSize"));
        double vendorPrice = Double.parseDouble(request.getParameter("vendorPrice"));
        int itemLevel = Integer.parseInt(request.getParameter("itemLevel"));
        String description = request.getParameter("description");

        try {
            Consumable consumable = new Consumable(itemName, maxStackSize, vendorPrice, itemLevel, description);
            consumableDao.create(consumable);
            request.setAttribute("message", "Consumable created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error creating Consumable: " + e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("createConsumable.jsp");
        dispatcher.forward(request, response);
    }

    private void getConsumableById(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemCategoryID = Integer.parseInt(request.getParameter("itemCategoryId"));

        try {
            Consumable consumable = consumableDao.getConsumableByItemCategoryID(itemCategoryID);
            if (consumable != null) {
                request.setAttribute("consumable", consumable);
            } else {
                request.setAttribute("error", "Consumable not found with ItemCategoryID: " + itemCategoryID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error getting Consumable by ItemCategoryID: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("manageConsumable.jsp");
        dispatcher.forward(request, response);
    }
}
