package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;

public class HTMLBodyReportBuilder extends ReportBodyBuilder {
    HTMLReportBody htmlReportBody = new HTMLReportBody();
    @Override
    public ReportBodyBuilder setCustomerName(String customerName) {
        htmlReportBody.putContent("<span class=\"customerName\">");
        htmlReportBody.putContent(customerName);
        return reportBodyBuilder;
    }

    @Override
    public ReportBodyBuilder setCustomerPhone(String phoneNumber) {
        htmlReportBody.putContent("</span><span class=\"customerPhone\">");
        htmlReportBody.putContent(phoneNumber);
        htmlReportBody.putContent("</span>");
        return reportBodyBuilder;
    }

    @Override
    public ReportBodyBuilder withItems() {
        htmlReportBody.putContent("<items>");
        return reportBodyBuilder;
    }

    @Override
    public ReportBodyBuilder newItem(String name, int quantity, double price) {
        htmlReportBody.putContent("<item><name>");
        htmlReportBody.putContent(name);
        htmlReportBody.putContent("</name><quantity>");
        htmlReportBody.putContent(quantity);
        htmlReportBody.putContent("</quantity><price>");
        htmlReportBody.putContent(price);
        htmlReportBody.putContent("</price></item>");
        return reportBodyBuilder;
    }

    @Override
    public ReportBody getReportBody() {

        return htmlReportBody;
    }

    @Override
    public ReportBodyBuilder lastItem(String name, int quantity, double unitPrice) {
        htmlReportBody.putContent("<item><name>");
        htmlReportBody.putContent(name);
        htmlReportBody.putContent("</name><quantity>");
        htmlReportBody.putContent(quantity);
        htmlReportBody.putContent("</quantity><price>");
        htmlReportBody.putContent(unitPrice);
        htmlReportBody.putContent("</price></item>");
        htmlReportBody.putContent("</items>");
        return reportBodyBuilder;
    }
}
