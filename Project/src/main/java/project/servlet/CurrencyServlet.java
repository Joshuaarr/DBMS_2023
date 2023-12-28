package project.servlet;

import project.dao.CurrencyDao;
import project.model.Currency;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/manageCurrency")
public class CurrencyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currencyName = request.getParameter("currencyName");
        int totalCapAmount = Integer.parseInt(request.getParameter("totalCapAmount"));
        int weeklyCapAmount = Integer.parseInt(request.getParameter("weeklyCapAmount"));
        boolean isWeeklyCap = Boolean.parseBoolean(request.getParameter("isWeeklyCap"));
        boolean isDiscontinued = Boolean.parseBoolean(request.getParameter("isDiscontinued"));

        try {
            Currency newCurrency = new Currency(currencyName, totalCapAmount, weeklyCapAmount, isWeeklyCap, isDiscontinued);
            CurrencyDao.getInstance().create(newCurrency);
            request.setAttribute("action", "create");
            request.setAttribute("currency", newCurrency);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageCurrency.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when creating currency", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currencyName = request.getParameter("currencyName");

        try {
            Currency currency = CurrencyDao.getInstance().getCurrencyByCurrencyName(currencyName);
            request.setAttribute("action", "get");
            request.setAttribute("currency", currency);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageCurrency.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when fetching currency", e);
        }
    }
}
