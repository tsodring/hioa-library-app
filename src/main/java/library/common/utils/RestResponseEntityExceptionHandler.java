package library.common.utils;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

/*
  This is an implementation of a global exception handler extending the ResponseEntityExceptionHandler
  in order to gain control status codes and response bodies that will be sent to the client. This allows
  us to give more useful information back to the client

  Taken from baeldung, need to check reference
 */


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//    private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    public RestResponseEntityExceptionHandler() {
        super();
    }

    // 4XX range of status codes
    // 400 Bad Requests

    // If there is a problem with
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers,
                                                                  final HttpStatus status, final WebRequest request) {
        return handleExceptionInternal(ex, message(status, ex), headers, HttpStatus.BAD_REQUEST, request);
    }

    // If there is a problem with incoming arguments i.e null value where value is required
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers,
                                                                  final HttpStatus status, final WebRequest request) {
        return handleExceptionInternal(ex, message(status, ex), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class/*, TransactionSystemException.class */})
    public ResponseEntity<Object> handleBadRequest(final DataIntegrityViolationException ex, final WebRequest request) {

        return handleExceptionInternal(ex, message(HttpStatus.BAD_REQUEST, ex), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    // 500
    @ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        logger.error("500 Status Code", ex);
        final String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    // 404
    @ExceptionHandler({ EmptyResultDataAccessException.class, EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFound(final RuntimeException ex, final WebRequest request) {
        logger.error("404 Status Code", ex);
        final String bodyOfResponse = "Entity not found!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private ApiError message(final HttpStatus httpStatus, final Exception ex) {
        logger.error("REST Exception occurred " + ex.getMessage());
        final String message = ex.getMessage() == null ? ex.getClass().getSimpleName() : ex.getMessage();
        final String devMessage = ex.toString();
        final String devStackTrace = ex.toString();
        return new ApiError(httpStatus.value(), message, devMessage, devStackTrace);
    }
}
