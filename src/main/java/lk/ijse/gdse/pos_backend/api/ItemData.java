package lk.ijse.gdse.pos_backend.api;

/*This Application Is Copyright Protected
    Author : Dilusha Ekanayaka
    Email  : dilushaeka99@gmail.com
    Date   : 7/28/2023
    Time   : 9:05 PM 
*/


import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse.pos_backend.dto.CustomerDTO;
import lk.ijse.gdse.pos_backend.dto.ItemDTO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

public class ItemData extends HttpServlet {


    Connection connection;
    private static final String SaveCustomer ="INSERT INTO products(itemId,description,unitPrice,qty)VALUES (?,?,?,?)";

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/products");
            this.connection=pool.getConnection();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            rsp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }

        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemObj = jsonb.fromJson(req.getReader(), ItemDTO.class);

        if (itemObj.getItemId() == null){

        } else if (itemObj.getDescription()==null) {

        }else if (itemObj.getUnitPrice() == Double.parseDouble(null)) {

        } else if (itemObj.getQty() == Double.parseDouble(null) ) {

        }

        try {
            PreparedStatement ps = connection.prepareStatement(SaveCustomer, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, itemObj.getItemId());
            ps.setString(2, itemObj.getDescription());
            ps.setString(3, String.valueOf(itemObj.getQty()));
            ps.setString(4, String.valueOf(itemObj.getUnitPrice()));

            if (ps.executeUpdate() !=1){
                throw new RuntimeException("save Failed");
            }
            ResultSet rst = ps.getGeneratedKeys();
            rst.next();
            rsp.setStatus(HttpServletResponse.SC_CREATED);
            //the created json is sent to frontend
            rsp.setContentType("application/json");
            jsonb.toJson(itemObj,rsp.getWriter());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
