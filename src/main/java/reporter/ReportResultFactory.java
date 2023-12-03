package reporter;

public class ReportResultFactory {
	public enum ReportTypes{ HTML, Markdown, TXT }
	
	public ReportResult createMainController(ReportTypes  reportType) {
		switch(reportType) {
		case HTML:
			return new ReportResultHTML();
		case Markdown:
			return new ReportResultMarkdown();
		case TXT:
			return new ReportResultTXT();
		}
		return null;
	}
}
