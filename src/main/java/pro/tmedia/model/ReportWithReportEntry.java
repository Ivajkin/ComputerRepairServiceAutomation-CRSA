package pro.tmedia.model;

/**
 * Created with IntelliJ IDEA.
 * User: Q
 * Date: 24.02.14
 * Time: 21:31
 * To change this template use File | Settings | File Templates.
 */
public class ReportWithReportEntry {
    //@Valid
    public Report report;
    //@Valid
    public ReportEntry reportEntry;

    public void setReport(Report report) {
        this.report = report;
    }
    public Report getReport() {
        return report;
    }

    public void setReportEntry(ReportEntry reportEntry) {
        this.reportEntry = reportEntry;
    }
    public ReportEntry getReportEntry() {
        return reportEntry;
    }
}
