package com.UserService.Exception;

public class ResourseNotFoundException extends RuntimeException{

    public ResourseNotFoundException(){
        super();
    }

    public ResourseNotFoundException(String msg){
        super(msg);
    }

}
