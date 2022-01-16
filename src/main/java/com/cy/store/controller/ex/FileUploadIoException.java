package com.cy.store.controller.ex;

/**
 * 上传文件时读写异常
 *
 * @author xiaoke
 */
public class FileUploadIoException extends FileUploadException {
    public FileUploadIoException() {
        super();
    }

    public FileUploadIoException(String message) {
        super(message);
    }

    public FileUploadIoException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadIoException(Throwable cause) {
        super(cause);
    }

    protected FileUploadIoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
