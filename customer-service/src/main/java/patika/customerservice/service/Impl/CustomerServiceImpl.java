package patika.customerservice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patika.customerservice.entity.Customer;
import patika.customerservice.entity.dto.request.CustomerChangeEmailAndAddressesRequest;
import patika.customerservice.entity.dto.request.CustomerSaveRequest;
import patika.customerservice.entity.dto.response.AddressResponse;
import patika.customerservice.entity.dto.response.CustomerAllResponse;
import patika.customerservice.entity.dto.response.CustomerResponse;
import patika.customerservice.entity.dto.response.OrderResponse;
import patika.customerservice.entity.enums.AccountType;
import patika.customerservice.repository.CustomerRepository;
import patika.customerservice.service.CustomerService;
import patika.customerservice.utils.client.service.AddressService;
import patika.customerservice.utils.client.service.OrderService;
import patika.customerservice.utils.converter.CustomerConverter;
import patika.customerservice.utils.exception.ExceptionMessages;
import patika.customerservice.utils.exception.KitapYurdumException;
import patika.customerservice.utils.producer.NotificationProducer;
import patika.customerservice.utils.producer.dto.CustomerDto;
import patika.customerservice.utils.producer.dto.NotificationDto;
import patika.customerservice.utils.producer.dto.enums.NotificationStatus;
import patika.customerservice.utils.producer.dto.enums.NotificationType;

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
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressService addressService;
    private final OrderService orderService;
    private final NotificationProducer notificationProducer;

    /**
     * Constructs a new instance of {@code CustomerServiceImpl} with the given services and repository.
     * <p>
     * This constructor is responsible for initializing the service with dependencies required for its operation,
     * including a {@link CustomerRepository} for accessing customer data, an {@link AddressService} for managing addresses,
     * an {@link OrderService} for handling orders, and a {@link NotificationProducer} for sending notifications.
     *
     * @param customerRepository The {@link CustomerRepository} to use for accessing customer data.
     * @param addressService The {@link AddressService} to use for managing addresses.
     * @param orderService The {@link OrderService} to use for handling orders.
     * @param notificationProducer The {@link NotificationProducer} to use for sending notifications.
     */
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AddressService addressService, OrderService orderService, NotificationProducer notificationProducer) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
        this.orderService = orderService;
        this.notificationProducer = notificationProducer;
    }

    /**
     * Saves a customer record, associates it with addresses, sets default properties, and saves the customer. Returns the saved customer along with a success message.
     *
     * @param request A {@link CustomerSaveRequest} object containing the customer's details
     * @return A {@link CustomerResponse} object representing the saved customer
     */
    @Override
    public CustomerResponse save(CustomerSaveRequest request) {
        userExistsByEmail(request.getEmail());

        Customer customer = CustomerConverter.toCustomer(request);
        customerRepository.save(customer);

        CustomerDto customerDto = CustomerConverter.toCustomerDto(customer);
        notificationProducer.sendNotification(prepareNotificationDto(NotificationType.MAIL, NotificationStatus.SUCCESS, customerDto));

        return CustomerConverter.toCustomerResponse(customer);
    }

    /**
     * Retrieves all customers and converts them to a list of {@link CustomerAllResponse} objects.
     *
     * @return A list of {@link CustomerAllResponse} objects representing all customers
     */
    @Override
    public List<CustomerAllResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(this::prepareCustomerAllResponse)
                .toList();
    }

    /**
     * Finds a customer by their ID and returns them as a {@link CustomerAllResponse} object.
     *
     * @param customerId The ID of the customer to find
     * @return The found customer as a {@link CustomerAllResponse} object, or throws an exception if not found
     */
    @Override
    public CustomerAllResponse findById(Integer customerId) {
        Optional<Customer> foundCustomer = customerRepository.findById(customerId);

        if (foundCustomer.isEmpty()) {
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_FOUND);
        }

        Customer customer = foundCustomer.get();

        return prepareCustomerAllResponse(customer);
    }

    /**
     * Finds a customer by their email and returns them as a {@link CustomerAllResponse} object.
     *
     * @param email The email of the customer to find
     * @return The found customer as a {@link CustomerAllResponse} object, or throws an exception if not found or inactive
     */
    @Override
    public CustomerAllResponse findByEmail(String email) {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);

        if (foundCustomer.isEmpty()) {
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_FOUND);
        }

        Customer customer = foundCustomer.get();

        if (!customer.getIsActive()) {
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_ACTIVE);
        }

        return prepareCustomerAllResponse(customer);
    }

    /**
     * Changes the account type of customer based on their email.
     *
     * @param email       The email of the customer whose account type needs to be changed
     * @param accountType The new account type for the customer
     * @return The updated {@link CustomerAllResponse} object
     */
    @Override
    public CustomerAllResponse changeAccountType(String email, AccountType accountType) {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);

        if (foundCustomer.isEmpty()) {
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_FOUND);
        }

        Customer customer = foundCustomer.get();
        customer.setAccountType(accountType);
        customerRepository.save(customer);
        
        return prepareCustomerAllResponse(customer);
    }

    /**
     * Changes the account type of customer based on their credit points.
     *
     * @param email The email of the customer whose account type needs to be changed
     * @return The updated {@link CustomerAllResponse} object
     */
    @Override
    public CustomerAllResponse changeAccountTypeByCredit(String email) {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);

        if (foundCustomer.isEmpty()) {
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_FOUND);
        }

        Customer customer = foundCustomer.get();
        int pointsCategory = customer.getCredit();

        if (pointsCategory >= 0 && pointsCategory < 1000) {
            customer.setAccountType(AccountType.STANDARD);
        } else if (pointsCategory >= 1000 && pointsCategory < 2000) {
            customer.setAccountType(AccountType.SILVER);
        } else if (pointsCategory >= 2000 && pointsCategory < 4000) {
            customer.setAccountType(AccountType.GOLD);
        } else if (pointsCategory >= 4000) {
            customer.setAccountType(AccountType.PLATINUM);
        }

        customerRepository.save(customer);

        return prepareCustomerAllResponse(customer);
    }

    /**
     * Changes the email and associated addresses of a customer.
     *
     * @param request A {@link CustomerChangeEmailAndAddressesRequest} object containing the new email and address IDs
     * @return The updated {@link CustomerAllResponse} object
     */
    @Override
    public CustomerAllResponse changeEmailAndAddresses(CustomerChangeEmailAndAddressesRequest request) {
        userExistsByEmail(request.getEmail());

        Optional<Customer> foundCustomer = customerRepository.findById(request.getId());

        if (foundCustomer.isEmpty()) {
            throw new KitapYurdumException(ExceptionMessages.CUSTOMER_NOT_FOUND);
        }

        Customer customer = foundCustomer.get();
        customer.setAddressIds(request.getAddressIds());
        customer.setEmail(request.getEmail());
        customerRepository.save(customer);
        
        return prepareCustomerAllResponse(customer);
    }

    /**
     * Checks if a user exists by their email.
     *
     * @param email The email to check for existing users
     */
    private void userExistsByEmail(String email) {
        Optional<Customer> foundCustomer = customerRepository.findByEmail(email);

        if (foundCustomer.isPresent()) {
            throw new KitapYurdumException(ExceptionMessages.EMAIL_ALREADY_EXIST);
        }
    }

    /**
     * Prepares a {@link NotificationDto} object based on the given {@link NotificationType} and {@link CustomerDto}.
     *
     * @param notificationType The type of notification to be prepared.
     * @param customerDto The customer-related data to be included in the notification.
     * @return A newly constructed {@link NotificationDto} object with the specified type and customer data.
     */
    private NotificationDto prepareNotificationDto(NotificationType notificationType, NotificationStatus notificationStatus, CustomerDto customerDto) {
        return NotificationDto.builder()
                .notificationType(notificationType)
                .notificationStatus(notificationStatus)
                .customerDto(customerDto)
                .productDto(null)
                .invoiceDto(null)
                .build();
    }

    /**
     * Prepares a comprehensive response for a customer, including their addresses and orders.
     * <p>
     * This method fetches the customer's addresses and orders, converts them into the respective response objects,
     * and then combines them into a single {@link CustomerAllResponse} object using a converter utility.
     *
     * @param customer The customer entity whose details are to be prepared.
     * @return A {@link CustomerAllResponse} object containing the customer's details, addresses, and orders.
     */
    private CustomerAllResponse prepareCustomerAllResponse(Customer customer) {
        List<AddressResponse> addressResponses = addressService.filterByIdList(customer.getAddressIds());
        List<OrderResponse> orderResponses = orderService.filterByCustomerId(customer.getId());

        return CustomerConverter.toCustomerAllResponse(customer, addressResponses, orderResponses);
    }
}
