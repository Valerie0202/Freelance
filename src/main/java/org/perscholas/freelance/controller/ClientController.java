package org.perscholas.freelance.controller;

//This controller will handle everything related to the user's clients

import org.perscholas.freelance.database.dao.ClientDAO;
import org.perscholas.freelance.database.dao.UserDAO;
import org.perscholas.freelance.database.entity.Client;
import org.perscholas.freelance.database.entity.User;
import org.perscholas.freelance.form.ClientFormBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/client")
@PreAuthorize("hasAuthority('USER')")
public class ClientController {

    @Autowired
    private ClientDAO clientDao;

    @Autowired
    private UserDAO userDao;

    @RequestMapping(value = "/viewClients", method = RequestMethod.GET)
    public ModelAndView viewClients(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("client/viewClients");
        User user = getLoggedInUser();
        List<Client> clients = clientDao.getClients(user.getId());
        response.addObject("clients", clients);
        return response;
    }

    @RequestMapping(value = "/createClient", method = RequestMethod.GET)
    public ModelAndView createClient(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("client/createClient");
        return response;
    }

    @RequestMapping(value = "/createClientSubmit", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView createClientSubmit(ClientFormBean form, Principal principal) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView response = new ModelAndView();
        Client client = new Client();
        User user = getLoggedInUser();

        client.setFirstName(form.getFirstName());
        client.setLastName(form.getLastName());
        client.setEmail(form.getEmail());
        client.setAddress(form.getAddress());
        client.setCellPhone(form.getCellPhone());
        client.setHomePhone(form.getHomePhone());
        client.setNotes(form.getNotes());
        client.setUser(user);
        client = clientDao.save(client);

        response.setViewName("redirect:/client/createClient");
        return response;
    }

    public User getLoggedInUser() {
        // this is boiler plate code to get the authentication information from spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // gets the username that the user logged in with
        String currentPrincipalName = authentication.getName();
        // query the database to get the user object based on the logged in username
        // in the case that you have used the email address to get the username
        return userDao.findByUsername(currentPrincipalName);
        // or you would use this line to get the user by username if you used username to login
        // return userDao.findByUsername(currentPrincipalName);
    }

}
