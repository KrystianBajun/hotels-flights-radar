package com.flightradar.flightradar.controller;

import com.flightradar.flightradar.model.email.Contact;
import com.flightradar.flightradar.model.email.Email;
import com.flightradar.flightradar.security.AllowedForAdmin;
import com.flightradar.flightradar.security.AllowedForUsers;
import com.flightradar.flightradar.service.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static com.flightradar.flightradar.service.Static.CurrentUser;

@Controller
public class EmailController {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    @AllowedForUsers
    public ModelAndView showContactForm() {
        return new ModelAndView("email/contact-form", "Contact", new Contact());
    }

    @Autowired
    public EmailController(EmailSender emailSender,
                           TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @AllowedForUsers
    @RequestMapping(value = "/send/contact-message", method = RequestMethod.POST)
    public String sendContactMessage(@ModelAttribute("Contact") Contact contact) {

        Context context = new Context();
        context.setVariable("title", contact.getTitle());
        context.setVariable("phone", contact.getPhone());
        context.setVariable("message", contact.getMessage());

        if (CurrentUser().getUser().getId() < 0) {
            context.setVariable("header", contact.getSender());
        } else {
            context.setVariable("userId", CurrentUser().getUser().getId());
            context.setVariable("userName", CurrentUser().getUser().getUsername());
        }

        String body = templateEngine.process("email/email-template", context);
        emailSender.sendEmail("tripsearchapp@outlook.com", "CodeCouple Newsletter", body);

        return "redirect:/";
    }

    @RequestMapping(value = "/newsletter", method = RequestMethod.GET)
    @AllowedForAdmin
    public ModelAndView showNewsletterForm() {
        return new ModelAndView("email/newsletter-form", "Email", new Email());
    }

    @RequestMapping(value = "/newsletterSend", method = RequestMethod.POST)
    @AllowedForAdmin
    public String send(@ModelAttribute("Email") Email Email) {

        Context context = new Context();
        context.setVariable("header", Email.getHeader());
        context.setVariable("title", Email.getTitle());
        context.setVariable("message", Email.getMessage());

        String body = templateEngine.process("email/newsletter-template", context);
        emailSender.sendNewsletter("Best Trip Searcher Newsletter", body);

        return "redirect:/";
    }

}