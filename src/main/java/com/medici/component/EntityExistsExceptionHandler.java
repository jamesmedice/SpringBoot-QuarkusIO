package com.medici.component;

import com.medici.model.ErrorResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.persistence.EntityExistsException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityExistsExceptionHandler implements ExceptionMapper<EntityExistsException> {

    @Override
    public Response toResponse(EntityExistsException exception) {
        ErrorResponse error = new ErrorResponse(exception.getMessage(), HttpResponseStatus.BAD_REQUEST.toString());
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
