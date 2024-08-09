package web;

import model.User;
import repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private UserRepository userRepository;

    public void init(){
        userRepository=new UserRepository();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("inside post");
        doGet(request,response);
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)  {
        String action=request.getServletPath();
        try {
            switch (action) {
                case "/new":
                	
                    showNewForm(request, response);
                    break;
                case "/insert":

                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                	
                    listuser(request, response);
                    break;

            }
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }


    }

    private void listuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<User> listUser = userRepository.getUser();
    	System.out.println("ListUser details: "+listUser);
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		 dispatcher.forward(request, response); 
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        int pincode=Integer.parseInt(request.getParameter("pincode"));
        User user=new User(id,name,address,pincode);
        userRepository.updateUser(user);
        System.out.println("User update detials: " +user);
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        User user=userRepository.selectUserById(id);
        RequestDispatcher dispatcher=request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user",user);
        dispatcher.forward(request,response);

    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        userRepository.deleteUser(id);
        System.out.println("User Deleted successfully of Id"+ id);
        response.sendRedirect("list");
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name=request.getParameter("name");
        String address=request.getParameter("address");
        int pincode= Integer.parseInt(request.getParameter("pincode"));
        User newUser=new User(name,address,pincode);
        System.out.println("Inserted USer"+newUser);
        userRepository.saveUser(newUser);
        response.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request,response);
    }
}
