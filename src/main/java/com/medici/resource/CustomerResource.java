package com.medici.resource;

import com.medici.model.Customer;
import com.medici.model.PageResponse;
import com.medici.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/customers")
public class CustomerResource {

    @Inject
    CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers(@QueryParam("page") @DefaultValue("0") int page,
                                    @QueryParam("size") @DefaultValue("10") int size,
                                    @QueryParam("sortField") @DefaultValue("customerId") String sortField,
                                    @QueryParam("sortOrder") @DefaultValue("asc") String sortOrder) {
        PageResponse payload = customerService.getAllCustomers(page, size, sortField, sortOrder);
        return Response.ok(payload).build();
    }

    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("customerId") Integer customerId) {
        Customer payload = customerService.getCustomerById(customerId);
        return Response.ok(payload).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void createCustomer(Customer customer) {
        customerService.saveCustomer(customer);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@Valid Customer customer) {
        Customer payload = customerService.updateCustomer(customer);
        return Response.ok(payload).build();
    }

    @DELETE
    @Path("/{customerId}")
    public void deleteCustomerById(@PathParam("customerId") Integer customerId) {
        customerService.deleteCustomerById(customerId);
    }
}
