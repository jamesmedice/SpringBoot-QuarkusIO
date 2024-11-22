package com.medici.component;

import com.medici.model.ErrorResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        ErrorResponse error = new ErrorResponse(exception.getMessage(), HttpResponseStatus.INTERNAL_SERVER_ERROR.toString());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
}