package com.medici.repository;

import com.medici.entity.CustomerEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<CustomerEntity, Integer> {

    public PanacheQuery<CustomerEntity> findAllCustomers(int page,  int size,  String sortField,  Sort.Direction sortDirection) {
        Sort sortorder = Sort.by(sortField).direction(sortDirection);
        return findAll(sortorder).page(page, size);
    }

    public long count() {
        return findAll().count();
    }

    public List<CustomerEntity> findByFirstName(String firstName) {
        return find("firstName", firstName).list();
    }

    public List<CustomerEntity> findByLastName(String lastName) {
        return find("lastName", lastName).list();
    }

    public List<CustomerEntity> findByFullName(String firstName, String lastName) {
        return find("firstName = ?1 and lastName = ?2", firstName, lastName).list();
    }

    public void saveCustomer(CustomerEntity customer) {
        persist(customer);
    }

    public boolean deleteCustomer(Integer id) {
        return deleteById(id);
    }
}
