/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javaproject;

import backend.Database;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author samuel
 */
@WebServlet(name = "NewPost", urlPatterns = {"/NewPost"})
public class NewPost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Insert a new post!
        HttpSession session = request.getSession();
        
        // Check against the database!
        String openshift = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        Boolean isOpenShift = false;
        
        if (openshift != null) {
            isOpenShift = true;
        }
        Database db = Database.newInstance(isOpenShift);
        
        // Grab the data
        String id = (String) session.getAttribute("user_id");
        String text = request.getParameter("post");
        
        // Grab the date
        Date date = new Date();
        DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        String grabDate = formatDate.format(date);
        
        // Now insert it!
        System.out.println("id = " + id);
        System.out.println("db = " + db);
        db.insertPost(id, text, grabDate);
        
        // Now go back to the Posts servlet
        request.getRequestDispatcher("Posts").forward(request, response);
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
