package com.medici.mapper;

import com.medici.entity.CustomerEntity;
import com.medici.model.Customer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface CustomerMapper {

    List<Customer> toDomainList(List<CustomerEntity> entities);

    @Mapping(target = "mobile", source = "phone")
    Customer toDomain(CustomerEntity entity);

    @InheritInverseConfiguration(name = "toDomain")
    @Mapping(target = "customerId", ignore = true)
    CustomerEntity toEntity(Customer domain);

    void updateEntityFromDomain(Customer domain, @MappingTarget CustomerEntity entity);

    void updateDomainFromEntity(CustomerEntity entity, @MappingTarget Customer domain);
}
