package com.HotelService.Exception;

public class ResourseNotFoundException extends RuntimeException {
    public ResourseNotFoundException(String s) {
        super(s);
    }

    public ResourseNotFoundException(){
        super();
    }
}
