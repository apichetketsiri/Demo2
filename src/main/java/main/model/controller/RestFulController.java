package main.model.controller;

import main.model.model.Person;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Admin on 7/27/2015.
 */
@RestController
@RequestMapping(value="/api")
public class RestFulController {

    @RequestMapping(value="/person/{name}/{age}", method= RequestMethod.GET)
    public @ResponseBody
    Person getPerson(@PathVariable String name,@PathVariable int age){
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        return person;
    }
}
