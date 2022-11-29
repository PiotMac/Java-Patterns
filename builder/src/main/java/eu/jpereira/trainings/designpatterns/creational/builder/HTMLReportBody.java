package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.Iterator;

public class HTMLReportBody extends ReportBuilder implements ReportBody {

	private StringBuffer delegate = new StringBuffer();

	public HTMLReportBody(SaleEntry saleEntry) {
		super(saleEntry);
	}

	public String getAsString() {
		return this.delegate.toString();
	}

	public void addContent(Object content) {
		this.delegate.append(content);
	}

	public void addInfo() {
		addContent("<span class=\"customerName\">");
		addContent(super.saleEntry.getCustomer().getName());
		addContent("</span><span class=\"customerPhone\">");
		addContent(super.saleEntry.getCustomer().getPhone());
		addContent("</span>");

		addContent("<items>");

		Iterator<SoldItem> it = super.saleEntry.getSoldItems().iterator();
		while ( it.hasNext() ) {
			SoldItem soldEntry= it.next();
			addContent("<item><name>");
			addContent(soldEntry.getName());
			addContent("</name><quantity>");
			addContent(soldEntry.getQuantity());
			addContent("</quantity><price>");
			addContent(soldEntry.getUnitPrice());
			addContent("</price></item>");
		}
		addContent("</items>");
		String info = getAsString();
		report.setInfo(info);
	}
}
