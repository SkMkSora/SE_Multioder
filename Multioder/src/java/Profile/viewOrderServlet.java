/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Profile;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import model.Menu;
import model.Order;
import model.OrderItem;

/**
 *
 * @author Chronical
 */
@WebServlet(name = "viewOrderServlet", urlPatterns = {"/viewOrderServlet"})
public class viewOrderServlet extends HttpServlet {

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
        Connection conn = null;
        try {
            conn = seproject.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger("connection-error").log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String order = request.getParameter("ord_id");
            int order_id = Integer.parseInt(order);
            ArrayList<OrderItem> ordlist = new ArrayList<OrderItem>();
            String sql = "select * from order_item where order_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, order_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                OrderItem ord = new OrderItem(getServletContext());
                ord.setQuentity(rs.getInt("amount"));
                int amount = rs.getInt("amount");
                double price = rs.getDouble("price") * amount;
                ord.setPrice(price);
                
                ord.setMenu_id(rs.getInt("menu_id"));
                ord.setItem_num(rs.getInt("itemnumber"));
                ord.setOrder_id(rs.getInt("order_id"));
                int shop_id = rs.getInt("shop_id");
                String shop = "select * from shop where shopid = ?";
                PreparedStatement s = conn.prepareStatement(shop);
                s.setInt(1, shop_id);
                ResultSet rs_s = s.executeQuery();
                rs_s.next();
                ord.setShopname(rs_s.getString("shopname"));
                ordlist.add(ord);
            }
            String s_order = "select * from `order` where order_id = ?";
            PreparedStatement s_ord = conn.prepareStatement(s_order);
            s_ord.setInt(1, order_id);
            ResultSet rs_s = s_ord.executeQuery();
            rs_s.next();
            Order ord_s = new Order();
            ord_s.setOrder_id(rs_s.getInt("order_id"));
            ord_s.setBuy_date(rs_s.getDate("buy_date"));
            ord_s.setStatus(rs_s.getString("status"));
            ord_s.setTotal(rs_s.getDouble("total"));
            request.setAttribute("ordlist", ordlist);
            request.setAttribute("ord_s", ord_s);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/orderdetail.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(viewOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger("connection-close").log(Level.SEVERE, null, ex);
            }
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

}
