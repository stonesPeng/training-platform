package me.stone.training.platform.training.spring4all.retry.exception;

/**
 * @author penglei4
 * @description default description
 * @date 2021/12/2 10:21
 */
public class CustomRetryException extends Exception{
    public CustomRetryException(String error){
        super(error);
    }
}
