package project.servlet;

import project.dao.AccountDao;
import project.model.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/manageAccount")
public class AccountServlet extends HttpServlet {

	private AccountDao accountDao;

	@Override
	public void init() {
		accountDao = AccountDao.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("search".equals(action)) {
			searchAccount(request, response);
		} else if ("detail".equals(action)) {
			detailAccount(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("manageAccount.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	protected void detailAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		try {
			Account account = accountDao.getAccountByAccountID(accountID);
			if (account != null) {
				request.setAttribute("account", account);
			} else {
				request.setAttribute("message", "Account not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error retrieving Account: " + e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("manageCharacterDetail.jsp");
		dispatcher.forward(request, response);
	}

	protected void searchAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		try {
			Account account = accountDao.getAccountByAccountID(accountID);
			if (account != null) {
				request.setAttribute("account", account);
			} else {
				request.setAttribute("message", "Account not found.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error retrieving Account: " + e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("manageAccount.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));

		try {
			Account newAccount = new Account(name, email, isActive);
			accountDao.create(newAccount);
			request.setAttribute("message", "Account created successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "Error creating Account: " + e.getMessage());
		}
		doGet(request, response);
	}
}
