package jmaster.controller;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
    private static final Logger logger = Logger.getLogger(ExceptionController.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public String pageNotFoundHandler(Exception exception) {
//        logger.error(exception);
        return "404";
    }

    @ExceptionHandler(value = {JDBCConnectionException.class, CannotCreateTransactionException.class})
    public String internalServerErrorHandler(Exception exception) {
//        logger.error(exception);
        return "500";
    }
}
