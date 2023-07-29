package lk.ijse.gdse.pos_backend.api;

/*This Application Is Copyright Protected
    Author : Dilusha Ekanayaka
    Email  : dilushaeka99@gmail.com
    Date   : 7/26/2023
    Time   : 1:55 PM 
*/


import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse.pos_backend.dto.CustomerDTO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

@WebServlet
public class CustomerData extends HttpServlet {


    Connection connection;
    private static final String SaveCustomer ="INSERT INTO customer(cusName,email)VALUES (?,?)";

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/student");
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
        CustomerDTO customeObj = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        if (customeObj.getCusName() == null){

        } else if (customeObj.getEmail()==null) {

        }

        try {
            PreparedStatement ps = connection.prepareStatement(SaveCustomer, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customeObj.getCusName());
            ps.setString(2,customeObj.getEmail());

            if (ps.executeUpdate() !=1){
                throw new RuntimeException("save Failed");
            }
            ResultSet rst = ps.getGeneratedKeys();
            rst.next();
            rsp.setStatus(HttpServletResponse.SC_CREATED);
            //the created json is sent to frontend
            rsp.setContentType("application/json");
            jsonb.toJson(customeObj,rsp.getWriter());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
