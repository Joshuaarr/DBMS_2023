package project.servlet;

import project.dao.JobItemsDao;
import project.dao.CharacterDao;
import project.dao.JobDao;
import project.dao.JobLevelDao;
import project.model.JobItems;
import project.model.Character;
import project.model.Job;
import project.model.JobLevel;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/manageJobItems")
public class JobItemsServlet extends HttpServlet {

    private JobItemsDao jobItemsDao;
    private CharacterDao characterDao;
    private JobDao jobDao;
    private JobLevelDao jobLevelDao;

    @Override
    public void init() {
        jobItemsDao = JobItemsDao.getInstance();
        characterDao = CharacterDao.getInstance();
        jobDao = JobDao.getInstance();
        jobLevelDao = JobLevelDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve characters and jobs from the database
            List<Character> characters = characterDao.getAllCharacters();
            List<Job> jobs = jobDao.getAllJobs();

            // Set attributes for the JSP page
            request.setAttribute("characters", characters);
            request.setAttribute("jobs", jobs);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving data: " + e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageJobItems.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int characterID = Integer.parseInt(request.getParameter("characterID"));
        int jobID = Integer.parseInt(request.getParameter("jobID"));
        int exp = Integer.parseInt(request.getParameter("exp"));
        int levelID = Integer.parseInt(request.getParameter("levelID"));
        boolean isCurrentJob = Boolean.parseBoolean(request.getParameter("isCurrentJob"));

        try {
            Character character = characterDao.getCharacterByCharacterID(characterID);
            Job job = jobDao.getJobByJobID(jobID);
            JobLevel jobLevel = jobLevelDao.getJobLevelByLevelID(levelID);

            JobItems newJobItems = new JobItems(character, job, exp, jobLevel, isCurrentJob);
            jobItemsDao.create(newJobItems);
            request.setAttribute("message", "JobItems record created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error creating JobItems record: " + e.getMessage());
        }
        doGet(request, response);
    }
}
