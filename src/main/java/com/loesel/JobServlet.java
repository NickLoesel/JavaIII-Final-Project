/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loesel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

@WebServlet(name = "JobServlet", urlPatterns = {"/jobs"})
public class JobServlet extends HttpServlet {

    private static final String FILE_PATH = "WEB-INF/assets/";
    private static final String FILE_NAME = "jobs.tsv";
    private static SortedSet<Job> jobs;
    private int id = 0;
    private Job singleJob = new Job();

    private void readFromFile(HttpServletRequest request, HttpServletResponse response)
            throws ParserConfigurationException, MalformedURLException, IOException, SAXException {
        if (jobs == null) {
            try ( Scanner in = new Scanner(new File(getServletContext().getRealPath(FILE_PATH + FILE_NAME)))) {
                jobs = new TreeSet<>();
                int lineCount = 0;
                String line = in.nextLine();
                String[] fields;
                int id;
                boolean active;
                LocalDate dateCreated;
                String title;
                String city;
                String state;
                boolean fullTime;
                String department;
                String experience;
                String wageCategory;
                double salary;
                String jobDescription;
                while (in.hasNextLine()) {
                    lineCount++;
                    line = in.nextLine();
                    fields = line.split("\t");
                    id = Integer.parseInt(fields[0]);
                    active = Boolean.parseBoolean(fields[1]);
                    dateCreated = LocalDate.parse(fields[2]);
                    title = fields[3];
                    city = fields[4];
                    state = fields[5];
                    fullTime = Boolean.parseBoolean(fields[6]);
                    department = fields[7];
                    experience = fields[8];
                    wageCategory = fields[9];
                    salary = Double.parseDouble(fields[10]);
                    jobDescription = fields[11];
                    if (active == false) {
                        lineCount++;
                    } else {
                        jobs.add(new Job(id, active, dateCreated, title,
                                city, state, fullTime, department, experience,
                                wageCategory, salary, jobDescription));
                    }

                }
            } catch (FileNotFoundException fnfe) {
                response.setContentType("text/html;charset=UTF-8");
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Data Error</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Error Reading Data.</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
                return;
            }
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            readFromFile(request, response);
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            return;
        }
        String Stringid = request.getParameter("id");
        if (Stringid == null) {
            displayJobList(request, response);
        } else {
            try {
                id = Integer.parseInt(Stringid);
            } catch (NumberFormatException e) {
                request.getRequestDispatcher("/WEB-INF/jsp/view/jobList.jsp");
            }
            displayJob(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void displayJobList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int jobsPerPage = 4;
        int begin = 0;
        int end = 0;
        int maxPages = jobs.size() / jobsPerPage;
        if (jobs.size() % jobsPerPage != 0) {
            maxPages++;
        }
        String PageStr = request.getParameter("page");
        if (PageStr != null && !PageStr.equals("")) {
            try {
                page = Integer.parseInt(PageStr);
                if (page < 1) {
                    page = 1;
                } else if (page > maxPages) {
                    page = maxPages;
                }
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        begin = (page - 1) * jobsPerPage;
        end = begin + jobsPerPage - 1;
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);
        request.setAttribute("maxPages", maxPages);
        request.setAttribute("currentPage", page);

        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("/WEB-INF/jsp/view/jobList.jsp").forward(request, response);
    }

    private void displayJob(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getJob(id);
        request.setAttribute("jobs", singleJob);
        request.getRequestDispatcher("/WEB-INF/jsp/view/job.jsp").forward(request, response);
    }

    private Job getJob(int id) {
        for (Job job : jobs)
            if(job.getId() == id){
                singleJob = job;
            }
        return singleJob;
    }
}
