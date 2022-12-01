package eu.jpereira.trainings.designpatterns.creational.builder.json;

import eu.jpereira.trainings.designpatterns.creational.builder.ReportBodyBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;

public class JSONBodyReportBuilder extends ReportBodyBuilder {
    //private ReportBodyBuilder jsonReportBodyBuilder = new JSONBodyReportBuilder();
    private JSONReportBody jsonReportBody= new JSONReportBody();

    @Override
    public ReportBodyBuilder setCustomerName(String customerName) {
        jsonReportBody.addContent("sale:{customer:");
        jsonReportBody.addContent("{name:\"");
        jsonReportBody.addContent(customerName);
        return reportBodyBuilder;
    }

    @Override
    public ReportBodyBuilder setCustomerPhone(String phoneNumber) {
        jsonReportBody.addContent("\",phone:\"");
        jsonReportBody.addContent(phoneNumber);
        jsonReportBody.addContent("\"}");
        return reportBodyBuilder;
    }

    @Override
    public ReportBodyBuilder withItems() {
        jsonReportBody.addContent(",items:[");
        return reportBodyBuilder;
    }

    @Override
    public ReportBodyBuilder newItem(String name, int quantity, double price) {
        jsonReportBody.addContent("{name:\"");
        jsonReportBody.addContent(name);
        jsonReportBody.addContent("\",quantity:");
        jsonReportBody.addContent(String.valueOf(quantity));
        jsonReportBody.addContent(",price:");
        jsonReportBody.addContent(String.valueOf(price));
        jsonReportBody.addContent("}");
        jsonReportBody.addContent(",");
        return reportBodyBuilder;
    }

    @Override
    public ReportBodyBuilder lastItem(String name, int quantity, double unitPrice) {
        jsonReportBody.addContent("{name:\"");
        jsonReportBody.addContent(name);
        jsonReportBody.addContent("\",quantity:");
        jsonReportBody.addContent(String.valueOf(quantity));
        jsonReportBody.addContent(",price:");
        jsonReportBody.addContent(String.valueOf(unitPrice));
        jsonReportBody.addContent("}");
        jsonReportBody.addContent("]}");
        return reportBodyBuilder;
    }
    @Override
    public ReportBody getReportBody() {
        return jsonReportBody;
    }
}
