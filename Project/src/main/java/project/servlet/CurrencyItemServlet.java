package project.servlet;

import project.dao.CurrencyItemDao;
import project.model.CurrencyItem;
import project.model.Character;
import project.model.Currency;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/manageCurrencyItem")
public class CurrencyItemServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int characterID = Integer.parseInt(request.getParameter("characterID"));
		String currencyName = request.getParameter("currencyName");
		int totalAmount = Integer.parseInt(request.getParameter("totalAmount"));
		int weeklyAmount = Integer.parseInt(request.getParameter("weeklyAmount"));

		try {
			Character character = new Character(weeklyAmount, null, currencyName, currencyName);
			Currency currency = new Currency(currencyName, weeklyAmount, weeklyAmount, false, false);
			CurrencyItem currencyItem = new CurrencyItem(character, currency, totalAmount, weeklyAmount);

			CurrencyItemDao.getInstance().create(currencyItem);
			request.setAttribute("action", "create");
			request.setAttribute("currencyItem", currencyItem);
			RequestDispatcher dispatcher = request.getRequestDispatcher("manageCurrencyItem.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			throw new ServletException("SQL error when creating currency item", e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int characterID = (request.getParameter("characterID") == null ||  "".equals(request.getParameter("characterID"))) ? 1 : Integer.parseInt(request.getParameter("characterID"));
    	String currencyName = request.getParameter("currencyName") == null ? "" : request.getParameter("currencyName");
    	
//        int characterID = Integer.parseInt(request.getParameter("characterID"));
//        String currencyName = request.getParameter("currencyName");

        try {
            CurrencyItem currencyItem = CurrencyItemDao.getInstance().getCurrencyItemByCharacterIDAndCurrencyName(characterID, currencyName);
            request.setAttribute("action", "get");
            request.setAttribute("currencyItem", currencyItem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageCurrencyItem.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when fetching currency item", e);
        }
    }
}
