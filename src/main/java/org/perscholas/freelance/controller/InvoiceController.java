package org.perscholas.freelance.controller;

import org.perscholas.freelance.database.dao.ClientDAO;
import org.perscholas.freelance.database.dao.InvoiceDAO;
import org.perscholas.freelance.database.dao.InvoiceLineDAO;
import org.perscholas.freelance.database.dao.UserDAO;
import org.perscholas.freelance.database.entity.Client;
import org.perscholas.freelance.database.entity.Invoice;
import org.perscholas.freelance.database.entity.InvoiceLine;
import org.perscholas.freelance.database.entity.User;
import org.perscholas.freelance.form.ClientFormBean;
import org.perscholas.freelance.form.InvoiceFormBean;
import org.perscholas.freelance.form.InvoiceLineFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/invoice")
@PreAuthorize("hasAuthority('USER')")
public class InvoiceController {

    public static final Logger LOG = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceDAO invoiceDao;

    @Autowired
    private InvoiceLineDAO invoiceLineDao;

    @Autowired
    private ClientDAO clientDao;

    @Autowired
    private UserDAO userDao;

    @RequestMapping(value = "/createInvoice", method = RequestMethod.GET)
    public ModelAndView createInvoice(@RequestParam(required = false) Integer id) {
        ModelAndView response = new ModelAndView();
        response.setViewName("invoice/createInvoice");
        User user = getLoggedInUser();
        LOG.debug("Logged in user is " + user.getUsername());

        if ( id != null ) {
            LOG.debug("ID is not null, ID passed in is " + id);
            Invoice invoice = invoiceDao.findById(id);
            LOG.debug("Invoice passed in has ID of " + invoice.getId() + " and client is " + invoice.getClient());
            InvoiceFormBean form = new InvoiceFormBean();
            Client client = invoice.getClient();

            form.setDate(invoice.getDate());
            form.setTitle(invoice.getTitle());
            form.setNotes(invoice.getNotes());
            form.setId(invoice.getId());
            form.setClient(client);
            form.setClientName(client.getFirstName() + " " + client.getLastName());
            LOG.debug("invoice.getClient() returns " + invoice.getClient());
            LOG.debug("form.getClient() returns " + form.getClient());

            response.addObject("form", form);

            List<InvoiceLine> invoiceLines = invoiceLineDao.getInvoiceLines(id);

            response.addObject("invoiceLines", invoiceLines);

        } else {
            // an id has not been passed so it is a create
            // there is no data from the database so give an empty form bean
            LOG.debug("ID was not passed in");
            InvoiceFormBean form = new InvoiceFormBean();

            List<Client> clients = clientDao.getClients(user.getId());
            response.addObject("clients", clients);
            response.addObject("form", form);
        }
        LOG.debug("createInvoice is about to return response");
        return response;
    }

    @RequestMapping(value = "/createInvoiceSubmit", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView createInvoiceSubmit(InvoiceFormBean form) throws Exception {
        LOG.debug("CreateInvoiceSubmit method begins");
        LOG.debug("InvoiceFormBean has passed an ID of " + form.getId() + " and a client of " + form.getClient());
        ModelAndView response = new ModelAndView();
        Invoice invoice = new Invoice();
        User user = getLoggedInUser();
        Client client = clientDao.findById(form.getClientId());
        LOG.debug("Found client " + client + " under clientId of " + form.getClientId());

        invoice.setUser(user);
        invoice.setClient(client);
        LOG.debug("User has just set the client");
        invoice.setDate(form.getDate());
        invoice.setTitle(form.getTitle());
        invoice.setNotes(form.getNotes());

        invoice = invoiceDao.save(invoice);
        LOG.debug("Invoice was just saved");

        response.setViewName("redirect:/invoice/createInvoice?id="+invoice.getId());
        return response;
    }

    @RequestMapping(value = "/createInvoiceLineSubmit", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView createInvoiceLineSubmit(InvoiceLineFormBean form) throws Exception {
        ModelAndView response = new ModelAndView();
        InvoiceLine line = new InvoiceLine();
        Integer invoiceId = form.getInvoiceId();
        Invoice invoice = invoiceDao.findById(invoiceId);

        line.setInvoice(invoice);
        line.setItem(form.getItem());
        line.setPrice(form.getPrice());
        line.setQuantity(form.getQuantity());
        line.setNotes(form.getNotes());


        line = invoiceLineDao.save(line);

        response.setViewName("redirect:/invoice/createInvoice?id="+invoiceId);
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
