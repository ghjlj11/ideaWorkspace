import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet (name = "sd",urlPatterns = "/sd")
public class sd extends GenericServlet{

    private static final long seriaVersionUID=7126120676169060864L;
    public void service (ServletRequest req,ServletResponse resp)
            throws ServletException,IOException{
        System.out.println("primary servlet:service");
        PrintWriter w=resp.getWriter();
        w.println("<h1 align='center'>I am a primary servlet.ghj.ghj</h1>");
    }
}
