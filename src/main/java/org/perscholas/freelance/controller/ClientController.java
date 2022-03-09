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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public ModelAndView createClient(@RequestParam(required = false) Integer id) {
        ModelAndView response = new ModelAndView();
        response.setViewName("client/createClient");

        if ( id != null ) {
            // Update
            Client client = clientDao.findById(id);
            ClientFormBean form = new ClientFormBean();

            form.setEmail(client.getEmail());
            form.setFirstName(client.getFirstName());
            form.setLastName(client.getLastName());
            form.setPhone(client.getPhone());
            form.setAddress1(client.getAddress1());
            form.setAddress2(client.getAddress2());
            form.setNotes(client.getNotes());
            form.setId(client.getId());

            response.addObject("form", form);
        } else {
            // Create
            ClientFormBean form = new ClientFormBean();
            response.addObject("form", form);
        }

        return response;
    }

    @RequestMapping(value = "/createClientSubmit", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView createClientSubmit(ClientFormBean form) throws Exception {
        ModelAndView response = new ModelAndView();
        Client client = new Client();
        User user = getLoggedInUser();

        client.setFirstName(form.getFirstName());
        client.setLastName(form.getLastName());
        client.setEmail(form.getEmail());
        client.setAddress1(form.getAddress1());
        client.setAddress2(form.getAddress2());
        client.setPhone(form.getPhone());
        client.setPhone(form.getPhone());
        client.setNotes(form.getNotes());
        client.setUser(user);
        client = clientDao.save(client);

        response.setViewName("redirect:/client/createClient");
        return response;
    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userDao.findByUsername(currentPrincipalName);
    }

}
