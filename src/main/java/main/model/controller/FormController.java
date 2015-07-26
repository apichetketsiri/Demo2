package main.model.controller;

import main.model.model.Person;
import main.model.service.ServiceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class FormController {
	@Autowired
	ServiceExample serviceExample;
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showForm(Person person,Model model) {
		model.addAttribute("person", new Person());
		return "Form";
	}
	@RequestMapping(value="/submit", method=RequestMethod.POST)
	public String checkPersonInfo(@Valid Person person, BindingResult bindingResult, Model model) {
		System.out.println("Controller");
		if (bindingResult.hasErrors()) {

			return "Form";
		}
		model.addAttribute("person",person);
		return "Done";
	}



	@RequestMapping(value = "/exception",method=RequestMethod.GET)
	public String getException()throws Exception{
		throw new NullPointerException();

	}





}