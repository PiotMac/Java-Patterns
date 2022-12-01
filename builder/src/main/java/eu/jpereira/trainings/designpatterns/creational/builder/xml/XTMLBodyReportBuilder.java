package eu.jpereira.trainings.designpatterns.creational.builder.xml;

import eu.jpereira.trainings.designpatterns.creational.builder.ReportBodyBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;

public class XTMLBodyReportBuilder extends ReportBodyBuilder {
    private XMLReportBody xmlReportBody = new XMLReportBody();
    @Override
    public ReportBodyBuilder setCustomerName(String customerName) {
        xmlReportBody.putContent("<sale><customer><name>");
        xmlReportBody.putContent(customerName);
        return reportBodyBuilder;
    }

    @Override
    public ReportBodyBuilder setCustomerPhone(String phoneNumber) {
        xmlReportBody.putContent("</name><phone>");
        xmlReportBody.putContent(phoneNumber);
        xmlReportBody.putContent("</phone></customer>");
        return reportBodyBuilder;
    }

    @Override
    public ReportBodyBuilder withItems() {
        xmlReportBody.putContent("<items>");
        return reportBodyBuilder;
    }

    @Override
    public ReportBodyBuilder newItem(String name, int quantity, double price) {
        xmlReportBody.putContent("<item><name>");
        xmlReportBody.putContent(name);
        xmlReportBody.putContent("</name><quantity>");
        xmlReportBody.putContent(quantity);
        xmlReportBody.putContent("</quantity><price>");
        xmlReportBody.putContent(price);
        xmlReportBody.putContent("</price></item>");
        return reportBodyBuilder;
    }

    @Override
    public ReportBody getReportBody() {

        return xmlReportBody;
    }

    @Override
    public ReportBodyBuilder lastItem(String name, int quantity, double unitPrice) {
        xmlReportBody.putContent("<item><name>");
        xmlReportBody.putContent(name);
        xmlReportBody.putContent("</name><quantity>");
        xmlReportBody.putContent(quantity);
        xmlReportBody.putContent("</quantity><price>");
        xmlReportBody.putContent(unitPrice);
        xmlReportBody.putContent("</price></item>");
        xmlReportBody.putContent("</items></sale>");
        return reportBodyBuilder;
    }
}
