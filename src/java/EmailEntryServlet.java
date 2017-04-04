/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import entity.EmailEntry;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.EmailEntryFacade;

@WebServlet(name = "EmailEntryServlet", urlPatterns = {"/EmailEntryServlet"})
public class EmailEntryServlet extends HttpServlet {

    @EJB
    private EmailEntryFacade EmailEntrySessionObj;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {
            case "Search": {
                String lastname = request.getParameter("lastName");

                List<EmailEntry> guestList = EmailEntrySessionObj.findByLastName(lastname);
                request.setAttribute("guestList", guestList);
                break;

            }

            case "Show All": {
                List<EmailEntry> guestList = EmailEntrySessionObj.showAllRecords();
                request.setAttribute("guestList", guestList);
                break;
            }
            case "Edit": {
                int id = Integer.parseInt(request.getParameter("guestID"));
                List<EmailEntry> guestList = EmailEntrySessionObj.findById(id);
                String firstname = request.getParameter("firstName");
                String lastname = request.getParameter("lastName");
                String emailaddress = request.getParameter("emailAddress");

                guestList.get(0).setId(id);
                guestList.get(0).setFirstName(firstname);
                guestList.get(0).setLastName(lastname);
                guestList.get(0).setEmailAddress(emailaddress);

                try {
                    EmailEntrySessionObj.edit(guestList.get(0));
                    String message = "Edit successful";
                    request.setAttribute("message1", message);
                } catch (Exception e) {
                    String message = "Edit failure";
                    request.setAttribute("message2", message);
                }
                break;
            }
            case "Delete": {
                int id = Integer.parseInt(request.getParameter("guestID"));
                List<EmailEntry> guestList = EmailEntrySessionObj.findById(id);
                try {
                    EmailEntrySessionObj.deleteById(guestList.get(0));
                    String message = "Delete successful";
                    request.setAttribute("message2", message);
                } catch (Exception e) {
                    String message = "Delete failure";
                    request.setAttribute("message2", message);
                }
                break;
            }
            case "Total Records": {
                int count = EmailEntrySessionObj.getNumberOfRecords();
                String message = Integer.toString(count);
                request.setAttribute("message3", message);
                break;
            }
            default: {
                int id = Integer.parseInt(request.getParameter("guestID"));
                String firstname = request.getParameter("firstName");
                String lastname = request.getParameter("lastName");
                String emailaddress = request.getParameter("emailAddress");
                EmailEntrySessionObj.persistEmailEntryData(id, firstname, lastname,emailaddress);
                List<EmailEntry> guestList = EmailEntrySessionObj.findAll();
                request.setAttribute("guestList", guestList);
            }

        }
        getServletContext()
                .getRequestDispatcher("/editor.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}