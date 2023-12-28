package project.servlet;

import project.dao.JobDao;
import project.model.Job;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/manageJob")
public class JobServlet extends HttpServlet {

    private JobDao jobDao;

    @Override
    public void init() {
        jobDao = JobDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("searchByID".equals(action)) {
            searchJobByID(request, response);
        } else {
            displayAllJobs(request, response);
        }
    }

    private void searchJobByID(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int jobID = Integer.parseInt(request.getParameter("jobID"));
            Job job = jobDao.getJobByJobID(jobID);
            request.setAttribute("job", job);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error finding job: " + e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageJob.jsp");
        dispatcher.forward(request, response);
    }

    private void displayAllJobs(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Job> jobs = jobDao.getAllJobs();
            request.setAttribute("jobs", jobs);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving jobs: " + e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("Job.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String jobName = request.getParameter("jobName");
        try {
            Job newJob = new Job(jobName);
            jobDao.create(newJob);
            request.setAttribute("message", "Job created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error creating job: " + e.getMessage());
        }
        doGet(request, response); 
    }
}
