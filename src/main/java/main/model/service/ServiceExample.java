package main.model.service;

import org.springframework.stereotype.Service;

/**
 * Created by Admin on 7/24/2015.
 */
@Service
public class ServiceExample {

    public int add(int a, int b) {
        return a+b;
    }
    public int minus(int a, int b){
        return a-b;
    }



}
