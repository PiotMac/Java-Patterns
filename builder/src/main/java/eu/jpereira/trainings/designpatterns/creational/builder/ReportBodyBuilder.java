package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;

public abstract class ReportBodyBuilder {
    protected ReportBodyBuilder reportBodyBuilder;
    abstract public ReportBodyBuilder setCustomerName(String customerName);
    abstract public ReportBodyBuilder setCustomerPhone(String phoneNumber);
    abstract public ReportBodyBuilder withItems();
    abstract public ReportBodyBuilder newItem(String name, int quantity, double price);
    abstract public ReportBody getReportBody();

    abstract public ReportBodyBuilder lastItem(String name, int quantity, double unitPrice);
}
