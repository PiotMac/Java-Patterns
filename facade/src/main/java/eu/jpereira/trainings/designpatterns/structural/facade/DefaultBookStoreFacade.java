package eu.jpereira.trainings.designpatterns.structural.facade;

import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;
import eu.jpereira.trainings.designpatterns.structural.facade.service.*;

public class DefaultBookStoreFacade implements BookstoreFacade {
    CustomerDBService customerDBService;
    WharehouseService warehouseService;
    CustomerNotificationService customerNotificationService;
    BookDBService bookDBService;
    OrderingService orderingService;
    @Override
    public void placeOrder(String customerId, String isbn) {
        Book book = bookDBService.findBookByISBN(isbn);
        Customer customer = customerDBService.findCustomerById(customerId);
        Order order = orderingService.createOrder(customer, book);
        customerNotificationService.notifyClient(order);
        DispatchReceipt dispatchReceipt = warehouseService.dispatch(order);
        customerNotificationService.notifyClient(dispatchReceipt);
    }

    @Override
    public void setCustomerService(CustomerDBService customerService) {
        this.customerDBService = customerService;
    }

    @Override
    public void setWarehouseService(WharehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @Override
    public void setNotificationService(CustomerNotificationService customerNotificationService) {
        this.customerNotificationService = customerNotificationService;
    }

    @Override
    public void setBookService(BookDBService bookService) {
        this.bookDBService = bookService;
    }

    @Override
    public void setOrderingService(OrderingService orderingService) {
        this.orderingService = orderingService;
    }
}
