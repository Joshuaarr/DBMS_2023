package project.servlet;

import project.dao.JobLevelDao;
import project.model.JobLevel;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/manageJobLevel")
public class JobLevelServlet extends HttpServlet {

    private JobLevelDao jobLevelDao;

    @Override
    public void init() {
        jobLevelDao = JobLevelDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageJobLevel.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int minExp = Integer.parseInt(request.getParameter("minExp"));
        int maxExp = Integer.parseInt(request.getParameter("maxExp"));
        boolean isMaxLevel = Boolean.parseBoolean(request.getParameter("isMaxLevel"));

        try {
            JobLevel newJobLevel = new JobLevel(minExp, maxExp, isMaxLevel);
            jobLevelDao.create(newJobLevel);
            request.setAttribute("message", "JobLevel created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error creating JobLevel: " + e.getMessage());
        }
        doGet(request, response);
    }
}
