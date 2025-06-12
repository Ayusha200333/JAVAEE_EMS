//package org.example;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.Part;
//import org.apache.commons.dbcp2.BasicDataSource;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.*;
//
//import static java.lang.System.out;
//
//@WebServlet("/api/v1/employee")
//@MultipartConfig
//public class EmployeeServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//        ObjectMapper mapper = new ObjectMapper();
//        PrintWriter writer = response.getWriter();
//
//        String ename = requset.getParameter("ename");
//        String eaddress = requset.getParameter("eaddress");
//        String eemail = requset.getParameter("eemail");
//
//        Part filePart = requset.getPart("eimage");
//        String originalFilename = filePart.getSubmittedFileName();
//        String fileName = UUID.randomUUID() + "_" + originalFilename;
//
//        String uploadPath = "C:\\Users\\skc\\Documents\\AAD\\JAVAEE\\Work\\My_Practice_Project\\EMS-FN\\assets";
//        java.io.File uploadDir = new java.io.File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
//        String fileAbsolutePath = uploadPath + java.io.File.separator + fileName;
//        filePart.write(fileAbsolutePath);
//
//        ServletContext sc = requset.getServletContext();
//        BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");
//
//        try(Connection connection = ds.getConnection()){
//            PreparedStatement pstm = connection.prepareStatement(
//                    "INSERT INTO employee (eid,ename,eaddress,eemail,eimage)" + "VALUES (?,?,?,?,?)"
//            );
//
//            String eid = UUID.randomUUID().toString();
//
//            pstm.setString(1, eid);
//            pstm.setString(2, ename);
//            pstm.setString(3, eaddress);
//            pstm.setString(4, eemail);
//            pstm.setString(5, fileName);
//
//            int executed = pstm.executeUpdate();
//
//            if (executed > 0) {
//                response.setStatus(HttpServletResponse.SC_OK);
//                mapper.writeValue(out, Map.of(
//                        "code","200",
//                        "status","success",
//                        "message","Employee added successfully"
//                ));
//            }else {
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                mapper.writeValue(out, Map.of(
//                        "code", "400",
//                        "status","error",
//                        "message","Employee not added"
//                ));
//            }
//
//        }catch (SQLException e){
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            mapper.writeValue(out, Map.of(
//                    "code","500",
//                    "status","error",
//                    "message","Internal Server Error"
//            ));
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        resp.setContentType("application/json");
//
//        ServletContext sc = req.getServletContext();
//        BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");
//
//        try(Connection connection = ds.getConnection()){
//            PreparedStatement pstm = connection.prepareStatement(
//                   "SELECT eid,ename,eaddress,eemail,eimage FROM employee"
//            );
//            ResultSet rs = pstm.executeQuery();
//
//            List<Map<String,String>> employees = new ArrayList<>();
//
//            while (rs.next()) {
//                Map<String,String> emp = new HashMap<>();
//                emp.put("eid",rs.getString("eid"));
//                emp.put("ename",rs.getString("ename"));
//                emp.put("eaddress",rs.getString("eaddress"));
//                emp.put("eemail",rs.getString("eemail"));
//                emp.put("eimage",rs.getString("eimage"));
//                employees.add(emp);
//            }
//
//            PrintWriter out = resp.getWriter();
//            resp.setStatus(HttpServletResponse.SC_OK);
//            mapper.writeValue(out, Map.of(
//                    "code", "200",
//                    "status","success",
//                    "data",employees
//            ));
//
//        }catch (Exception e){
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            mapper.writeValue(resp.getWriter(), Map.of(
//                    "code","500",
//                    "status","error",
//                    "message","Internal Server Error"
//            ));
//        }
//    }
//}



//
// correct code
//
//package org.example;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.MultipartConfig;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.Part;
//import org.apache.commons.dbcp2.BasicDataSource;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.*;
//import java.util.*;
//
//@WebServlet("/api/v1/employee")
//@MultipartConfig
//public class EmployeeServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//        ObjectMapper mapper = new ObjectMapper();
//        PrintWriter writer = response.getWriter();
//
//        String ename = request.getParameter("ename");
//        String eaddress = request.getParameter("eaddress");
//        String eemail = request.getParameter("eemail");
//
//        Part filePart = request.getPart("eimage");
//        String originalFilename = filePart.getSubmittedFileName();
//        String fileName = UUID.randomUUID() + "_" + originalFilename;
//
//        // Save to the "assets" folder inside the deployed web directory
//        String uploadPath = getServletContext().getRealPath("/") + "assets";
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
//        String fileAbsolutePath = uploadPath + File.separator + fileName;
//        filePart.write(fileAbsolutePath);
//
//        ServletContext sc = request.getServletContext();
//        BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");
//
//        try (Connection connection = ds.getConnection()) {
//            PreparedStatement pstm = connection.prepareStatement(
//                    "INSERT INTO employee (eid, ename, eaddress, eemail, eimage) VALUES (?, ?, ?, ?, ?)"
//            );
//
//            String eid = UUID.randomUUID().toString();
//
//            pstm.setString(1, eid);
//            pstm.setString(2, ename);
//            pstm.setString(3, eaddress);
//            pstm.setString(4, eemail);
//            pstm.setString(5, fileName);
//
//            int executed = pstm.executeUpdate();
//
//            if (executed > 0) {
//                response.setStatus(HttpServletResponse.SC_OK);
//                mapper.writeValue(writer, Map.of(
//                        "code", "200",
//                        "status", "success",
//                        "message", "Employee added successfully"
//                ));
//            } else {
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                mapper.writeValue(writer, Map.of(
//                        "code", "400",
//                        "status", "error",
//                        "message", "Employee not added"
//                ));
//            }
//
//        } catch (SQLException e) {
//            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            mapper.writeValue(writer, Map.of(
//                    "code", "500",
//                    "status", "error",
//                    "message", "Internal Server Error"
//            ));
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        resp.setContentType("application/json");
//
//        ServletContext sc = req.getServletContext();
//        BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");
//
//        try (Connection connection = ds.getConnection()) {
//            PreparedStatement pstm = connection.prepareStatement(
//                    "SELECT eid, ename, eaddress, eemail, eimage FROM employee"
//            );
//            ResultSet rs = pstm.executeQuery();
//
//            List<Map<String, String>> employees = new ArrayList<>();
//
//            while (rs.next()) {
//                Map<String, String> emp = new HashMap<>();
//                emp.put("eid", rs.getString("eid"));
//                emp.put("ename", rs.getString("ename"));
//                emp.put("eaddress", rs.getString("eaddress"));
//                emp.put("eemail", rs.getString("eemail"));
//                emp.put("eimage", rs.getString("eimage"));
//                employees.add(emp);
//            }
//
//            PrintWriter out = resp.getWriter();
//            resp.setStatus(HttpServletResponse.SC_OK);
//            mapper.writeValue(out, Map.of(
//                    "code", "200",
//                    "status", "success",
//                    "data", employees
//            ));
//
//        } catch (Exception e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            mapper.writeValue(resp.getWriter(), Map.of(
//                    "code", "500",
//                    "status", "error",
//                    "message", "Internal Server Error"
//            ));
//        }
//    }
//}
//
//




package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.*;
import java.sql.*;
import java.util.*;

@WebServlet("/api/v1/employee")
@MultipartConfig
public class EmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = response.getWriter();

        String ename = request.getParameter("ename");
        String eaddress = request.getParameter("eaddress");
        String eemail = request.getParameter("eemail");

        Part filePart = request.getPart("eimage");
        String originalFilename = filePart.getSubmittedFileName();
        String fileName = UUID.randomUUID() + "_" + originalFilename;

        String uploadPath = getServletContext().getRealPath("/") + "assets";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String fileAbsolutePath = uploadPath + File.separator + fileName;
        filePart.write(fileAbsolutePath);

        ServletContext sc = request.getServletContext();
        BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");

        try (Connection connection = ds.getConnection()) {
            PreparedStatement pstm = connection.prepareStatement(
                    "INSERT INTO employee (eid, ename, eaddress, eemail, eimage) VALUES (?, ?, ?, ?, ?)"
            );

            String eid = UUID.randomUUID().toString();

            pstm.setString(1, eid);
            pstm.setString(2, ename);
            pstm.setString(3, eaddress);
            pstm.setString(4, eemail);
            pstm.setString(5, fileName);

            int executed = pstm.executeUpdate();

            if (executed > 0) {
                response.setStatus(HttpServletResponse.SC_OK);
                mapper.writeValue(writer, Map.of(
                        "code", "200",
                        "status", "success",
                        "message", "Employee added successfully"
                ));
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                mapper.writeValue(writer, Map.of(
                        "code", "400",
                        "status", "error",
                        "message", "Employee not added"
                ));
            }

        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(writer, Map.of(
                    "code", "500",
                    "status", "error",
                    "message", "Internal Server Error"
            ));
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");

        ServletContext sc = req.getServletContext();
        BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");

        try (Connection connection = ds.getConnection()) {
            PreparedStatement pstm = connection.prepareStatement(
                    "SELECT eid, ename, eaddress, eemail, eimage FROM employee"
            );
            ResultSet rs = pstm.executeQuery();

            List<Map<String, String>> employees = new ArrayList<>();

            while (rs.next()) {
                Map<String, String> emp = new HashMap<>();
                emp.put("eid", rs.getString("eid"));
                emp.put("ename", rs.getString("ename"));
                emp.put("eaddress", rs.getString("eaddress"));
                emp.put("eemail", rs.getString("eemail"));
                emp.put("eimage", rs.getString("eimage"));
                employees.add(emp);
            }

            PrintWriter out = resp.getWriter();
            resp.setStatus(HttpServletResponse.SC_OK);
            mapper.writeValue(out, Map.of(
                    "code", "200",
                    "status", "success",
                    "data", employees
            ));

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(resp.getWriter(), Map.of(
                    "code", "500",
                    "status", "error",
                    "message", "Internal Server Error"
            ));
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();

        try {
            String eid = req.getParameter("eid");
            String ename = req.getParameter("ename");
            String eaddress = req.getParameter("eaddress");
            String eemail = req.getParameter("eemail");

            ServletContext sc = req.getServletContext();
            BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");

            String fileName = null;

            Part imagePart = null;
            try {
                imagePart = req.getPart("eimage");
            } catch (IllegalStateException | ServletException e) {
                // no file part sent; ignore
            }

            if (imagePart != null && imagePart.getSize() > 0) {
                String originalFileName = imagePart.getSubmittedFileName();
                fileName = UUID.randomUUID() + "_" + originalFileName;

                String uploadPath = getServletContext().getRealPath("/") + "assets";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                imagePart.write(uploadPath + java.io.File.separator + fileName);
            }

            try (Connection connection = ds.getConnection()) {
                String sql = "UPDATE employee SET ename=?, eaddress=?, eemail=?" +
                        (fileName != null ? ", eimage=?" : "") +
                        " WHERE eid=?";

                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setString(1, ename);
                pstm.setString(2, eaddress);
                pstm.setString(3, eemail);
                int index = 4;
                if (fileName != null) {
                    pstm.setString(index++, fileName);
                }
                pstm.setString(index, eid);

                int updated = pstm.executeUpdate();

                if (updated > 0) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    mapper.writeValue(out, Map.of(
                            "code", "200",
                            "status", "success",
                            "message", "Employee updated successfully"
                    ));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    mapper.writeValue(out, Map.of(
                            "code", "404",
                            "status", "error",
                            "message", "Employee not found"
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(out, Map.of(
                    "code", "500",
                    "status", "error",
                    "message", "Internal server error"
            ));
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();

        try {
            String eid = req.getParameter("eid");

            if (eid == null || eid.trim().isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                mapper.writeValue(out, Map.of(
                        "code", "400",
                        "status", "error",
                        "message", "Missing or invalid employee ID"
                ));
                return;
            }

            ServletContext sc = req.getServletContext();
            BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");

            try (Connection connection = ds.getConnection()) {
                PreparedStatement pstm = connection.prepareStatement(
                        "DELETE FROM employee WHERE eid = ?"
                );
                pstm.setString(1, eid);

                int deleted = pstm.executeUpdate();

                if (deleted > 0) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    mapper.writeValue(out, Map.of(
                            "code", "200",
                            "status", "success",
                            "message", "Employee deleted successfully"
                    ));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    mapper.writeValue(out, Map.of(
                            "code", "404",
                            "status", "error",
                            "message", "Employee not found"
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            mapper.writeValue(out, Map.of(
                    "code", "500",
                    "status", "error",
                    "message", "Internal server error"
            ));
}
}
}
