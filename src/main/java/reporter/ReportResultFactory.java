package reporter;

public class ReportResultFactory {
    public IReportResult createReporter(String reportType) {
        switch (reportType) {
            case "html":
                return new ReportResultHTML();
            case "md":
                return new ReportResultMarkdown();
            case "text":
                return new ReportResultTXT();
        }
        return null;
    }
}
