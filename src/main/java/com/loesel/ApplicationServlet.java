/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loesel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author stewi
 */
@WebServlet(name = "ApplicationServlet", urlPatterns = {"/applications"})
public class ApplicationServlet extends HttpServlet {
    private volatile int APPLICATION_ID_SEQUENCE = 1;
    private HashMap<Integer,Application> applications; 
    
    private void createApplication(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("application");
        request.setAttribute("applicationSent", false);
        
        Application application = new Application();
        boolean error = false;
        String firstName = request.getParameter("firstName");
        if (firstName == null || firstName.equals("")) {
            error = true;
            application.setFirstNameError("First name required");
        } else {
            application.setFirstName(firstName);
        }
        String lastName = request.getParameter("lastName");
        if (lastName == null || lastName.equals("")) {
            error = true;
            application.setLastNameError("Last name required");
        } else {
            application.setLastName(lastName);
        }
        String email = request.getParameter("email");
        if (email == null || email.equals("")) {
            error = true;
            application.setLastNameError("Email required");
        } else {
            application.setEmail(email);
        }
        String phone = request.getParameter("phone");
        if (phone == null || phone.equals("")) {
            error = true;
            application.setPhoneError("Phone number required");
        } else {
            application.setPhone(phone);
        }
        String salary = request.getParameter("salary");
        if (salary == null || salary.equals("")) {
            error = true;
            application.setSalaryError("Desired Salary required");
        } else {
            application.setDesiredSalary(Double.parseDouble(salary));
        }
        String startDate = request.getParameter("startDate");
        if (startDate == null || startDate.equals("")) {
            error = true;
            application.setStartDateError("Start date required");
        } else {
            application.setEarliestStartDate(LocalDate.parse(startDate));
        }
        
        Part resume = request.getPart("resume");
        if (resume != null && resume.getSize() > 0) {
            Attachment attachment = processAttachment(resume);
        } else {
            error = true;
            application.setResumeError("Resume is required");
            
        }
        String jobid = request.getParameter("jobId");
        String jobTitle = request.getParameter("jobTitle");
        if (!error) {
            application.setJobid(Integer.parseInt(jobid));
            application.setJobTitle(jobTitle);
            application.setDateTimeSubmitted(Instant.now());
            application.setActive(true);
            int id;
            synchronized (this){
                id = this.APPLICATION_ID_SEQUENCE++;
                application.setId(id);
                applications.put(id, application);
            }
            request.setAttribute("applicationSent", true);
            request.getRequestDispatcher("/jobs?id=" + jobid).forward(request,response); 
        } else{
            session.setAttribute("applications", application);
            response.sendRedirect("jobs?id=" + jobid);
        }
    
    
    }
    
    private Attachment processAttachment(Part resume) throws IOException {
        Attachment attachment = new Attachment();
        try (InputStream inputStream = resume.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            attachment.setName(resume.getSubmittedFileName());
            attachment.setContents(outputStream.toByteArray());
        }
        return attachment;
    }



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
    if (request.getParameter("logout") != null) {
        session.invalidate();
        response.sendRedirect("login");
        return;
    } else if (session.getAttribute("username") != null) {
        response.sendRedirect("applications");
        return;
    }
    request.setAttribute("loginFailed", false);
    request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
        
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
        createApplication(request, response);
    }


}
