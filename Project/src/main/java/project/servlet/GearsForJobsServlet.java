package project.servlet;

import project.dao.GearsForJobsDao;
import project.dao.GearDao;
import project.dao.JobDao;
import project.model.GearsForJobs;
import project.model.Gear;
import project.model.Job;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/manageGearsForJobs")
public class GearsForJobsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemCategoryID = Integer.parseInt(request.getParameter("itemCategoryID"));
        int jobID = Integer.parseInt(request.getParameter("jobID"));

        try {
            GearDao gearDao = GearDao.getInstance();
            JobDao jobDao = JobDao.getInstance();
            Gear gear = gearDao.getGearByItemCategoryID(itemCategoryID);
            Job job = jobDao.getJobByJobID(jobID);

            GearsForJobs gearsForJobs = new GearsForJobs(gear, job);
            GearsForJobsDao.getInstance().create(gearsForJobs);
            request.setAttribute("gearsForJobs", gearsForJobs);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageGearsForJobs.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when creating gears for jobs", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int itemCategoryID = Integer.parseInt(request.getParameter("itemCategoryID"));

        try {
            GearsForJobs gearsForJobs = GearsForJobsDao.getInstance().getGearsForJobsByItemCategoryID(itemCategoryID);
            request.setAttribute("gearsForJobs", gearsForJobs);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageGearsForJobs.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("SQL error when retrieving gears for jobs", e);
        }
    }
}
