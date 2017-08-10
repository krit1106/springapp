package java.spring.dao;
import java.io.IOException;
import java.spring.model.Contact;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class HomeController {
	 @Autowired
	    private ContactDAO contactDAO;
	 
	    // handler methods go here...
	 
	 //handler method for listing all the contacts (it will serve as the home page)
	 @RequestMapping(value="/")
	 public ModelAndView listContact(ModelAndView model) throws IOException{
	     List<Contact> listContact = contactDAO.list();
	     model.addObject("listContact", listContact);
	     model.setViewName("home");
	  
	     return model;
	 }
	 
	 //handler method for displaying new contact form
	 @RequestMapping(value = "/newContact", method = RequestMethod.GET)
	 public ModelAndView newContact(ModelAndView model) {
	     Contact newContact = new Contact();
	     model.addObject("contact", newContact);
	     model.setViewName("ContactForm");
	     return model;
	 }
	 
	 //handler method for inserting/updating the contact form
	 @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	 public ModelAndView saveContact(@ModelAttribute Contact contact) {
	     contactDAO.save(contact);
	     return new ModelAndView("redirect:/");
	 }
	 
	 //handler method for deleting a contact
	 @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	 public ModelAndView deleteContact(HttpServletRequest request) {
	     int contactId = Integer.parseInt(request.getParameter("id"));
	     contactDAO.delete(contactId);
	     return new ModelAndView("redirect:/");
	 }
	 
	 //handler method for retrieving details of a particular contact for editing
	 @RequestMapping(value = "/editContact", method = RequestMethod.GET)
	 public ModelAndView editContact(HttpServletRequest request) {
	     int contactId = Integer.parseInt(request.getParameter("id"));
	     Contact contact = contactDAO.get(contactId);
	     ModelAndView model = new ModelAndView("ContactForm");
	     model.addObject("contact", contact);
	  
	     return model;
	 }
	 
	}
	
