package com.bang.authservice.mapper;

import com.bang.authservice.dto.request.AddressRequest;
import com.bang.authservice.dto.response.AddressResponse;
import com.bang.authservice.entity.Address;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(AddressRequest request);
    AddressResponse toAddressResponse(Address address);
}
