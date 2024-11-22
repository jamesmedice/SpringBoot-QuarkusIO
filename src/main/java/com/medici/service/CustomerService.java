package com.medici.service;

import com.medici.constants.EntityConstants;
import com.medici.entity.CustomerEntity;
import com.medici.mapper.CustomerMapper;
import com.medici.model.Customer;
import com.medici.model.PageResponse;
import com.medici.repository.CustomerRepository;
import com.medici.utils.FunctionalInterfaceOperation;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@Slf4j
@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    CustomerMapper customerMapper;

    public Customer getCustomerById(Integer customerId) {
        CustomerEntity entity = customerRepository.findById(customerId);

        if (Objects.isNull(entity))
            throw new EntityNotFoundException();

        return customerMapper.toDomain(entity);
    }

    public PageResponse<?> getAllCustomers(int page, int size, String sortField, String sortOrder) {
        PanacheQuery<CustomerEntity> allCustomers = customerRepository.findAllCustomers(page, size, sortField, parseSortDirection(sortOrder));
        long total = customerRepository.count();
        List<Customer> customers = customerMapper.toDomainList(allCustomers.list());
        PageResponse<Customer> pageResponse = new PageResponse<>();
        pageResponse.setPage(page);
        pageResponse.setRows(size);
        pageResponse.setPayload(customers);
        pageResponse.setTotal(total);
        return pageResponse;
    }

    private Sort.Direction parseSortDirection(String sortOrder) {
        return EntityConstants.DESC.equalsIgnoreCase(sortOrder) ? Sort.Direction.Descending : Sort.Direction.Ascending;
    }

    public List<CustomerEntity> findByFirstName(String name) {
        return customerRepository.findByFirstName(name);
    }

    @Transactional
    public void saveCustomer(Customer customer) {
        FunctionalInterfaceOperation.minusHours(customer);
        CustomerEntity entity = customerMapper.toEntity(customer);
        customerRepository.saveCustomer(entity);
    }

    @Transactional
    public Customer updateCustomer(@Valid Customer customer) {
        log.debug(customer.toString());

        if (Objects.isNull(customer.getCustomerId())) {
            throw new BadRequestException();
        }

        FunctionalInterfaceOperation.plusHours(customer);

        CustomerEntity entity = customerRepository.findByIdOptional(customer.getCustomerId()).orElseThrow(() -> new EntityNotFoundException());

        customerMapper.updateEntityFromDomain(customer, entity);
        customerRepository.persist(entity);
        customerMapper.updateDomainFromEntity(entity, customer);
        return customer;
    }

    public boolean deleteCustomerById(Integer id) {
        return customerRepository.deleteCustomer(id);
    }

}
