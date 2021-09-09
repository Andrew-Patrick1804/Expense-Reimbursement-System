package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.ERSReimbursement;
import models.Response;
import services.ERSReimbursementServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ERSReimbursementController {

    //singleton pattern
    private static ERSReimbursementController ersReimbursementController;

    private ERSReimbursementController(){

    }

    public static ERSReimbursementController getInstance(){
        if(ersReimbursementController == null){
            ersReimbursementController = new ERSReimbursementController();
        }
        return ersReimbursementController;
    }

    public void getUsersReimbursementRequests(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("employee reimbursement requests controller hit");

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        Integer userId = Integer.parseInt(req.getParameter("userId"));

        out.println(new ObjectMapper().writeValueAsString(
                new Response("employee reimbursement requests retrieved", true,
                        ERSReimbursementServiceImpl.getInstance().getAllReimbursementRequestsForUser(userId))));
    }

    public void getAllReimbursementRequests(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        System.out.println("getting every reimbursement request controller hit");

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        out.println(new ObjectMapper().writeValueAsString(
                new Response("every reimbursement request retrieved", true,
                        ERSReimbursementServiceImpl.getInstance().getAllReimbursementRequests())));
    }

    public void addReimbursementRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        System.out.println("add reimbursement controller hit");

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ERSReimbursement ersReimbursement = new ObjectMapper().readValue(requestBody, ERSReimbursement.class);

        ERSReimbursementServiceImpl.getInstance().addReimbursementRequest(ersReimbursement);

        out.println(new ObjectMapper().writeValueAsString(
                new Response("reimbursement request created", true, null)));
    }

    public void resolveReimbursementRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        System.out.println("resolve reimbursement controller hit");

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        Integer resolverId = Integer.parseInt(req.getParameter("userId"));
        Integer reimbursementId = Integer.parseInt(req.getParameter("reimbursementId"));

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ERSReimbursement ersReimbursement = new ObjectMapper().readValue(requestBody, ERSReimbursement.class);

        ERSReimbursementServiceImpl.getInstance().resolveAndChangeRequestStatus(ersReimbursement);

        out.println(new ObjectMapper().writeValueAsString(
                new Response("reimbursement request resolved", true, null)));

    }

    public void getSingleReimbursementRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("get single reimbursement request controller hit");

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        Integer reimbId = Integer.parseInt(req.getParameter("reimbursementId"));

        ERSReimbursement ersReimbursement = ERSReimbursementServiceImpl.getInstance().getOneReimbursementRequest(reimbId);

        if(ersReimbursement == null){
            out.println(new ObjectMapper().writeValueAsString(
                    new Response("reimbursement requst not found", false, null)));
        }
        else{
            out.println(new ObjectMapper().writeValueAsString(
                    new Response("retrieved reimbursement requst", true, ersReimbursement)
            ));
        }
    }
}
