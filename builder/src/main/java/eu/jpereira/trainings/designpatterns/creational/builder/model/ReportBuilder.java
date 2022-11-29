package eu.jpereira.trainings.designpatterns.creational.builder.model;

abstract public class ReportBuilder implements ReportBody {
    protected Report report;
    protected SaleEntry saleEntry;
    //public abstract void addInfo();
    public ReportBuilder (SaleEntry saleEntry) { this.saleEntry = saleEntry; }
    public Report getReport() { return report; }
    public void createNewReport() { report = new Report(); }
}
