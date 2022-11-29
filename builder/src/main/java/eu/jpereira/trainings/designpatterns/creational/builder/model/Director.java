package eu.jpereira.trainings.designpatterns.creational.builder.model;

public class Director {
    private ReportBuilder reportBuilder;
    public void setReportBuilder(ReportBuilder rb) { reportBuilder = rb; }
    public Report getReport() { return reportBuilder.getReport(); }
    public void constructReport() {
        reportBuilder.createNewReport();
        reportBuilder.addInfo();
    }
}
