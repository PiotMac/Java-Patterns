/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.creational.builder.xml;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.Iterator;

/**
 * This class is for demonstration purpose only!!
 * 
 * @author jpereira
 * 
 */
public class XMLReportBody extends ReportBuilder {

	private StringBuilder stringBuilder = new StringBuilder();

	public XMLReportBody(SaleEntry saleEntry) {
		super(saleEntry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.jpereira.trainings.designpatterns.creational.builder.ReportBody#
	 * getAsString()
	 */
	public String getAsString() { return stringBuilder.toString(); }

	public void addContent(Object content) {
		this.stringBuilder.append(content);
	}

	@Override
	public void addInfo() {
		addContent("<sale><customer><name>");
		addContent(this.saleEntry.getCustomer().getName());
		addContent("</name><phone>");
		addContent(this.saleEntry.getCustomer().getPhone());
		addContent("</phone></customer>");

		addContent("<items>");

		Iterator<SoldItem> it = saleEntry.getSoldItems().iterator();
		while ( it.hasNext() ) {
			SoldItem soldEntry = it.next();
			addContent("<item><name>");
			addContent(soldEntry.getName());
			addContent("</name><quantity>");
			addContent(soldEntry.getQuantity());
			addContent("</quantity><price>");
			addContent(soldEntry.getUnitPrice());
			addContent("</price></item>");
		}
		addContent("</items></sale>");
		String info = getAsString();
		report.setInfo(info);
	}
}
