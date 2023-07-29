package lk.ijse.gdse.pos_backend.filters;

/*This Application Is Copyright Protected
    Author : Dilusha Ekanayaka
    Email  : dilushaeka99@gmail.com
    Date   : 7/27/2023
    Time   : 9:06 PM 
*/


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class CROSFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String origin = req.getHeader("Origin");

        if (origin.contains("http://localhost")){
            System.out.println("cors done");
            res.setHeader("Access-Control-Allow-Origin",origin);
            res.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,HEADER");
            res.setHeader("Access-Control-Allow-Headers","Content-Type");
            res.setHeader("Access-Control-Expose-Headers","Content-Type");
        }
        chain.doFilter(req,res);
    }
}
