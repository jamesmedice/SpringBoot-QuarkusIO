package com.medici.component;

import com.medici.model.ErrorResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EntityNotFoundExceptionHandler implements ExceptionMapper<EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(exception.getMessage(), HttpResponseStatus.NOT_FOUND.toString());
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
