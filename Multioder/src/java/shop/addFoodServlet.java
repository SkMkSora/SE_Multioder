/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author Chronical
 */
@WebServlet(name = "addFoodServlet", urlPatterns = {"/addFoodServlet"})
public class addFoodServlet extends HttpServlet {

    @Resource(name = "seproject")
    private DataSource seproject;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            Connection conn = null;
            double price = 0;
            try {
                conn = seproject.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger("connection-error").log(Level.SEVERE, null, ex);
            }
            String foodname = request.getParameter("foodname");
            String description = request.getParameter("description");
            String price_str = request.getParameter("price");
            try {
                  price = Double.parseDouble(price_str);
            } catch (NumberFormatException e) {
                
            }

            String image = request.getParameter("image");
            if (foodname.isEmpty() || description.isEmpty() || price_str.isEmpty() || image.isEmpty()) {
                int fail = 1;
                request.setAttribute("add_menu", fail);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/add_menu.jsp");
                rd.forward(request, response);
                return;
            } else {

                HttpSession session = request.getSession();

                model.Shop shopid = (model.Shop)session.getAttribute("shop");
                int shopid_num = shopid.getShopid();
                String insert_menu = "INSERT INTO seproject.menu"
                        + "(name, shop_id, description, price, image) VALUES"
                        + "(?,?,?,?,?)";
                PreparedStatement m = conn.prepareStatement(insert_menu);
                m.setString(1, foodname);
                m.setInt(2, shopid_num);
                m.setString(3, description);
                m.setDouble(4, price);
                m.setString(5, image);
                m.executeUpdate();
                response.sendRedirect("manageMenuServlet?shopid=" + shopid_num);
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger("connection-close").log(Level.SEVERE, null, ex);
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(addFoodServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private int parseInt(int shopid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
