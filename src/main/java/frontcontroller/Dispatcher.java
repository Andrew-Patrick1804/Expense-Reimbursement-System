package frontcontroller;

import controllers.ERSReimbursementController;
import controllers.ERSUserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/api/*")
public class Dispatcher extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String URI = req.getRequestURI();

        System.out.println(URI);

        switch(URI){
            case "/Project1/api/login" :
                if (req.getMethod().equals("POST")){
                    ERSUserController.getInstance().login(req, resp);
                }
                break;
            case "/Project1/api/employee" :
                if (req.getMethod().equals("GET")){
                    ERSReimbursementController.getInstance().getUsersReimbursementRequests(req, resp);
                }
                break;
            case "/Project1/api/manager" :
                if (req.getMethod().equals("GET")){
                    ERSReimbursementController.getInstance().getAllReimbursementRequests(req, resp);
                }
                break;
            case "/Project1/api/reimbursement" :
                if (req.getMethod().equals("POST")){
                    ERSReimbursementController.getInstance().addReimbursementRequest(req, resp);
                }
                if (req.getMethod().equals("PATCH")){
                    ERSReimbursementController.getInstance().resolveReimbursementRequest(req, resp);
                }
                if (req.getMethod().equals("GET")){
                    ERSReimbursementController.getInstance().getSingleReimbursementRequest(req, resp);
                }
                break;
            case "/Project1/api/username" :
                if (req.getMethod().equals("GET")){
                    ERSUserController.getInstance().getName(req, resp);
                }
        }
    }
}
