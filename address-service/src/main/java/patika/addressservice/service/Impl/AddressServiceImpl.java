package patika.addressservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.addressservice.entity.Address;
import patika.addressservice.entity.dto.request.AddressIdsRequest;
import patika.addressservice.entity.dto.request.AddressSaveRequest;
import patika.addressservice.entity.dto.response.AddressResponse;
import patika.addressservice.repository.AddressRepository;
import patika.addressservice.service.AddressService;
import patika.addressservice.utils.converter.AddressConverter;

import java.util.List;
import java.util.Optional;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 * @since 20.06.2024
 */
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    /**
     * Constructs a new instance of {@code AddressServiceImpl} using the provided {@link AddressRepository}.
     *
     * @param addressRepository An instance of {@link AddressRepository} to interact with the database for address-related operations
     */
    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Saves an address record and returns the saved address along with a successful operation response.
     *
     * @param request A {@link AddressSaveRequest} object containing the information for the new address
     * @return A {@link AddressResponse} object representing the saved address and a successful operation response
     */
    @Override
    public AddressResponse save(AddressSaveRequest request) {
        Address address = AddressConverter.toAddress(request);
        addressRepository.save(address);

        return AddressConverter.toAddressResponse(address);
    }

    /**
     * Retrieves all addresses and returns them as a set of {@link AddressResponse} objects.
     *
     * @return A set of {@link AddressResponse} objects representing all addresses
     */
    @Override
    public List<AddressResponse> findAll() {
        List<Address> addresses = addressRepository.findAll();

        return AddressConverter.toListAddressResponse(addresses);
    }

    /**
     * Finds an address by its ID and returns it as a {@link AddressResponse} object.
     *
     * @param addressId The ID of the address to find
     * @return The found address as a {@link AddressResponse} object, or null if not found
     */
    @Override
    public AddressResponse findById(Integer addressId) {
        Optional<Address> address = addressRepository.findById(addressId);

        return address.map(AddressConverter::toAddressResponse).orElse(null);
    }

    /**
     * Filters addresses by a list of IDs and returns them as a set of {@link AddressResponse} objects.
     *
     * @param request A {@link AddressIdsRequest} A list of IDs to filter addresses by
     * @return A set of {@link AddressResponse} objects representing the filtered addresses
     */
    @Override
    public List<AddressResponse> filterByIdList(AddressIdsRequest request) {
        List<Address> addresses = addressRepository.filterByIdList(request.getAddressIds());

        return AddressConverter.toListAddressResponse(addresses);
    }
}

