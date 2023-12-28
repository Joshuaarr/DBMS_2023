package project.servlet;

import project.dao.ConsumableBonusDao;
import project.model.ConsumableBonus;
import project.model.ConsumableBonus.AttributeName;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/manageConsumableBonus")
public class ConsumableBonusServlet extends HttpServlet {

    private ConsumableBonusDao consumableBonusDao;

    @Override
    public void init() {
        consumableBonusDao = ConsumableBonusDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createConsumableBonus(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "create"; // Default to showing create ConsumableBonus form
        }

        if ("create".equals(action)) {
            showCreateForm(request, response);
        } else if ("getByItemCategoryIDAndAttributeName".equals(action)) {
            getByItemCategoryIDAndAttributeName(request, response);
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageConsumableBonus.jsp");
        dispatcher.forward(request, response);
    }

    private void createConsumableBonus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemCategoryID = Integer.parseInt(request.getParameter("itemCategoryID"));
        AttributeName attributeName = AttributeName.valueOf(request.getParameter("attributeName"));
        double attributeBonusPercent = Double.parseDouble(request.getParameter("attributeBonusPercent"));
        int attributeBonusCap = Integer.parseInt(request.getParameter("attributeBonusCap"));

        ConsumableBonus consumableBonus = new ConsumableBonus(null, attributeName, attributeBonusPercent, attributeBonusCap);

        try {
            consumableBonusDao.create(consumableBonus);
            request.setAttribute("message", "ConsumableBonus created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/manageConsumableBonus?action=create");
    }

    private void getByItemCategoryIDAndAttributeName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemCategoryID = Integer.parseInt(request.getParameter("searchItemCategoryID"));
        AttributeName attributeName = AttributeName.valueOf(request.getParameter("searchAttributeName"));

        try {
            ConsumableBonus consumableBonus = consumableBonusDao.getConsumableBonusByItemCategoryIDAndAttributeName(itemCategoryID, attributeName.name());

            if (consumableBonus != null) {
                request.setAttribute("consumableBonus", consumableBonus);
            } else {
                request.setAttribute("error", "ConsumableBonus not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("manageConsumableBonus.jsp");
        dispatcher.forward(request, response);
    }
}
