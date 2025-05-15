package de.ait.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoRestController {

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Person> getUser(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("jack",10));
        personList.add(new Person("lena",11));

        return personList;
    }

}
