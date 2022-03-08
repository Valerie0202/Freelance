package org.perscholas.freelance.controller;

import org.perscholas.freelance.database.dao.ClientDAO;
import org.perscholas.freelance.database.dao.InvoiceDAO;
import org.perscholas.freelance.database.dao.InvoiceLineDAO;
import org.perscholas.freelance.database.dao.UserDAO;
import org.perscholas.freelance.database.entity.Client;
import org.perscholas.freelance.database.entity.Invoice;
import org.perscholas.freelance.database.entity.InvoiceLine;
import org.perscholas.freelance.database.entity.User;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    @RequestMapping(value = "/viewInvoices", method = RequestMethod.GET)
    public ModelAndView viewInvoices() {
        ModelAndView response = new ModelAndView();
        response.setViewName("invoice/viewInvoices");
        User user = getLoggedInUser();
        LOG.debug("Logged in user ID is " + user.getId());

        List<Invoice> invoices = invoiceDao.getInvoices(user.getId());

        response.addObject("invoices", invoices);
        return response;
    }

    @RequestMapping(value = "/createInvoice", method = RequestMethod.GET)
    public ModelAndView createInvoice(@RequestParam(required = false) Integer id) {
        ModelAndView response = new ModelAndView();
        response.setViewName("invoice/createInvoice");
        User user = getLoggedInUser();

        if ( id != null ) {
            // Update
            Invoice invoice = invoiceDao.findById(id);
            User invoiceUser = invoice.getUser();
            if(invoiceUser.equals(user)) {
                InvoiceFormBean form = new InvoiceFormBean();
                Client client = invoice.getClient();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                form.setDate(dateFormat.format(invoice.getDate()));
                form.setTitle(invoice.getTitle());
                form.setNotes(invoice.getNotes());
                form.setId(invoice.getId());
                form.setClient(client);
                form.setClientName(client.getFirstName() + " " + client.getLastName());

                response.addObject("form", form);

                List<InvoiceLine> invoiceLines = invoiceLineDao.getInvoiceLines(id);

                response.addObject("invoiceLines", invoiceLines);
            } else {
                response.setViewName("error/404");
            }

        } else {
            // Create
            InvoiceFormBean form = new InvoiceFormBean();

            List<Client> clients = clientDao.getClients(user.getId());
            response.addObject("clients", clients);
            response.addObject("form", form);
        }

        return response;
    }

    // Submitting a new invoice will map here
    @RequestMapping(value = "/createInvoiceSubmit", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView createInvoiceSubmit(InvoiceFormBean form) throws Exception {
        ModelAndView response = new ModelAndView();
        Invoice invoice = new Invoice();
        User user = getLoggedInUser();
        Client client = clientDao.findById(form.getClientId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        invoice.setUser(user);
        invoice.setClient(client);
        invoice.setDate(dateFormat.parse(form.getDate()));
        invoice.setTitle(form.getTitle());
        invoice.setNotes(form.getNotes());
        invoice.setTax(form.getTax());

        invoice = invoiceDao.save(invoice);


        response.setViewName("redirect:/invoice/createInvoice?id="+invoice.getId());
        return response;
    }

    // Submitting a new invoice line will map here
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

    @RequestMapping(value = {"/printInvoice"}, method = RequestMethod.GET)
    public ModelAndView printInvoice(@RequestParam(required = false) Integer id) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("invoice/printInvoice");
        if( id != null) {
            User user = getLoggedInUser();
            Invoice invoice = invoiceDao.findById(id);
            User invoiceUser = invoice.getUser();
            if(invoiceUser.equals(user)) {
                Client client = invoice.getClient();
                List<InvoiceLine> invoiceLines = invoiceLineDao.getInvoiceLines(id);
                LOG.debug(String.valueOf(invoiceLines));

                response.addObject("user", user);
                response.addObject("client", client);
                response.addObject("invoice", invoice);
                response.addObject("invoiceLines", invoiceLines);
            } else {
                response.setViewName("error/404");
            }

        } else {
            response.setViewName("error/404");
        }

        return response;
    }

    public User getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userDao.findByUsername(currentPrincipalName);
    }

}
