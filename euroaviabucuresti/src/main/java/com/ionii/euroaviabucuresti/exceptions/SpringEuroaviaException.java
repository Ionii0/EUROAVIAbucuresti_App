package com.ionii.euroaviabucuresti.exceptions;



public class SpringEuroaviaException extends  Throwable{
    public SpringEuroaviaException(String exMessage, Exception e){
        super(exMessage,e);

    }
    public SpringEuroaviaException(String exMessage){
        super(exMessage);
    }
}
