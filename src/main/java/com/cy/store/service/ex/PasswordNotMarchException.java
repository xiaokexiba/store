package com.cy.store.service.ex;

/**
 * 密码不匹配的异常
 *
 * @author xiaoke
 */
public class PasswordNotMarchException extends ServiceException{
    public PasswordNotMarchException() {
        super();
    }

    public PasswordNotMarchException(String message) {
        super(message);
    }

    public PasswordNotMarchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMarchException(Throwable cause) {
        super(cause);
    }

    protected PasswordNotMarchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
