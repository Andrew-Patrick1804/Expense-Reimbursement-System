package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.ERSUser;
import models.Response;
import services.ERSUserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ERSUserController {

    //singleton design pattern
    private static ERSUserController ersUserController;

    private ERSUserController(){

    }

    public static ERSUserController getInstance(){
        if (ersUserController == null){
            ersUserController = new ERSUserController();
        }
        return ersUserController;
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("login controller hit");

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        ERSUser ersUser = new ObjectMapper().readValue(requestBody, ERSUser.class);

        ERSUser tempUser = ERSUserServiceImpl.getInstance().login(ersUser);

        if (tempUser != null){
            out.println(new ObjectMapper().writeValueAsString(
                    new Response("Login Successful", true, tempUser)));
        }
        else{
            out.println(new ObjectMapper().writeValueAsString(
                    new Response("Invalid Username or Password", false, null)));
        }
    }

    public void getName(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        System.out.println("get user name controller hit");

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        Integer userId = Integer.parseInt(req.getParameter("userId"));

        out.println(new ObjectMapper().writeValueAsString(new Response("user name retrieved", true,
                ERSUserServiceImpl.getInstance().getFirstAndLastName(userId))));
    }
}
