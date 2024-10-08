package tech.renovus.solarec.business.impl.report;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.html2pdf.HtmlConverter;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.EmailService;
import tech.renovus.solarec.business.SolarService;
import tech.renovus.solarec.business.TranslationService;
import tech.renovus.solarec.business.impl.report.html.basic.IReportHtml;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.ClientDao;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.exceptions.ReportException;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.CollectionUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.db.data.ClientVo;
import tech.renovus.solarec.vo.db.data.LocationVo;
import tech.renovus.solarec.vo.report.ReportRequest;
import tech.renovus.solarec.vo.report.ReportResponse;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;

public abstract class BaseReport {

	//--- Protected properties ------------------
	@Autowired ClientDao clientDao;
	@Autowired LocationDao locationDao;
	@Autowired RenovusSolarecConfiguration config;
	@Autowired EmailService emailService;
	@Autowired TranslationService translationService;
	
	@Resource SolarService service;

	//--- Abstract methods ----------------------
	public abstract ReportResponse generate(ReportRequest request);
	
	//--- Protected methods ---------------------
	protected UserData getUserData(Integer cliId, Integer locId) {
		ClientVo cliVo		= this.clientDao.findVo(cliId);
		LocationVo locVo	= this.locationDao.findVo(cliId, locId);
		
		UserData result		= new UserData();
		result.setClientVo(cliVo);
		result.setLocationVo(locVo);
		
		return result;
	}
	
	protected String createHtmlStart(UserData userData, ChartFilter filter, Collection<IReportHtml<ChartFilter>> factories) {
		ClientVo cliVo		= userData.getClientVo();
		LocationVo locVo	= userData.getLocationVo();
		StringBuilder html	= new StringBuilder();
		
		html
		.append("<html>")
		.append("<head>")
		.append("<title>")
		.append(this.translationService.forLabel(userData.getLocale(), "report.title.for", cliVo.getCliName(), locVo.getLocName()))
		.append("</title>")
		.append("<style>")
		.append("html, body, td, th { font: 12px 'Century Gothic', 'Trebuchet MS', Helvetica, sans-serif; }")
		.append("h1, h2, h3 { border-bottom: 1px solid black; }")
		.append("img { width: 100%; }")
		.append(".center { text-align: center; }")
		.append(".right { text-align: right; }")
		.append(".image { text-align: center; }")
		.append(".content { padding-left: 20px; }")
		.append("table.inner-col-border { border-spacing: 0; }")
		.append("table.inner-col-border th, table.inner-col-border td { padding: 4px 2px; }")
		.append("table.inner-col-border th:not(:last-child), table.inner-col-border td:not(:last-child):nth-child(2n+1)  { border-right: 0.5px solid; }")
		.append("table { width: 100%; }")
		.append("table.overview { width: 75%; }")
		.append("th { font-weight: bold; padding: 2px; }")
		.append("td { padding: 2px; vertical-align: top; }")
		.append("td.title { font-weight: bold; }")
		.append("td.value { text-align: right; white-space: nowrap; }")
		.append("td.date { text-align: center; }")
		.append("table.main tbody tr td { font-weight: bold; font-size: 2em; }")
		.append("table.main tbody tr td small { font-size: 0.5em; }")
		.append(".toc-row {padding-left: 20px }")
		.append(".toc-row a {text-decoration: none; color: black; }")
		.append(".toc-row a::after { content: leader('.') target-counter(attr(href), page, decimal); }");
		
		factories.stream().forEach(factory -> html.append(factory.createStyle()));
		
		html.append("</style>");
		html.append("</head>");
		
		html.append("<body>");
		
		return html.toString();
	}
	
	protected String createHtmlHeader(UserData userData, ChartFilter filter) {
		ClientVo cliVo		= userData.getClientVo();
		LocationVo locVo	= userData.getLocationVo();
		StringBuilder html	= new StringBuilder();
		Date dateNow		= new Date();
		
		html
		.append("<table class='main'><tbody>")
		.append("<tr><td class='center'>")
		.append(this.translationService.forLabel(userData.getLocale(), "report.title.generic"))
		.append("<br><small>")
		.append(DateUtil.formatDateTime(dateNow, DateUtil.FMT_PARAMETER_DATE_TIME))
		.append("</small>")
		.append("</td><td class='center'>")
		.append(cliVo.getCliName())
		.append("<br>")
		.append(locVo.getLocName())
		.append("</td></tr>")
		.append("</tbody></table>")
		.append("</div>");
		
		return html.toString();
	}
	
	protected String createHtmlEnd() {
		StringBuilder html = new StringBuilder();
		
		html.append("</body>");
		html.append("</html>");
		
		return html.toString();
	}

	protected String createHtmlTOC(UserData userData, Collection<IReportHtml<ChartFilter>> factories) {
		StringBuilder html = new StringBuilder();
		
		html
			.append("<div class='toc-container'>")
			.append("<h1>")
			.append(this.translationService.forLabel(userData.getLocale(), "report.toc.title"))
			.append("</h1>");
		
		for (IReportHtml<?> factory : factories) {
			html
				.append("<div class='toc-row'><a href='#")
				.append(ClassUtil.getClassName(factory.getClass()))
				.append("'>")
				.append(this.translationService.forLabel(userData.getLocale(), factory.getTitle()))
				.append("</a></div>");
		}
		
		html.append("</div>");
		
		return html.toString();
	}
	
	protected ReportResponse generateReportResponse(String prefix, UserData userData, LocationVo locVo, File pdfFile) {
		String fileName = new StringBuilder()
				.append(DateUtil.formatDateTime(new Date(), DateUtil.FMT_PARAMETER_DATE))
				.append(StringUtil.SPACE_STRING)
				.append(this.translationService.forLabel(userData.getLocale(), "report.title.generic"))
				.append(StringUtil.SPACE_STRING)
				.append(prefix)
				.append(StringUtil.BAR_SPARATOR)
				.append(userData.getClientVo().getCliName())
				.append(StringUtil.BAR_SPARATOR)
				.append(locVo.getLocName()).toString();
		
		return new ReportResponse()
				.withCliId(locVo.getCliId())
				.withLocId(locVo.getLocId())
				.withName(fileName)
				.withGenerated(true)
				.withPath(pdfFile.getAbsolutePath());
	}
	
	protected ReportResponse generateReportResponse(String prefix, Exception e, LocationVo locVo) {
		return new ReportResponse()
				.withCliId(locVo == null ? null : locVo.getCliId())
				.withLocId(locVo == null ? null : locVo.getLocId())
				.withGenerated(false)
				.withError(StringUtil.toString(e));
	}
	
	protected void write(FileWriter writer, String text) throws IOException {
		if (StringUtil.isEmpty(text)) {
			return;
		}
		
		writer.write(text);
		writer.flush();
	}

	protected void write(FileWriter writer, UserData userData, ChartFilter filter, Collection<IReportHtml<ChartFilter>> factories) throws IOException, ReportException {
		if (CollectionUtil.isEmpty(factories)) {
			return;
		}
		
		for (IReportHtml<ChartFilter> factory : factories) {
			System.out.println("Calling report: " + factory.getClass().getName());
			this.write(writer, "<div class='page' id='" + ClassUtil.getClassName(factory.getClass()) + "'>");
			try {
				this.write(writer, factory.createHtml(userData, filter.createCopy()));
			} catch (Exception e) {
				this.write(writer, "Error found at " + factory.getClass().getCanonicalName() + ":<br>");
				this.write(writer, StringUtil.toString(e, true));
			}
			
			this.write(writer, "</div>");
		}
	}
	
	protected ReportResponse generateHtmlFor(String name, LocationVo locVo, ChartFilter filter, Collection<IReportHtml<ChartFilter>> factories, UserData userData) {
		ReportResponse response = null;
		
		try {
			File htmlFile			= File.createTempFile(UUID.randomUUID().toString(), ".html");
			File pdfFile			= File.createTempFile(htmlFile.getName(), ".pdf");
			
			try (
				FileWriter writer = new FileWriter(htmlFile, true); 
			) {
				this.write(writer, this.createHtmlStart(userData, filter.createCopy(), factories));
				this.write(writer, this.createHtmlHeader(userData, filter.createCopy()));
				this.write(writer, this.createHtmlTOC(userData, factories));
				
				this.write(writer, userData, filter, factories);
				
				this.write(writer, this.createHtmlEnd());
	
				this.convert(htmlFile, pdfFile);
				response = this.generateReportResponse(name, userData, locVo, pdfFile);
				System.out.println("Report for: " + userData.getCliName() + " - " + userData.getLocName() + " at: " + response.getPath());
			}
		} catch (ReportException | IOException e) {
			response = this.generateReportResponse(name, e, locVo);
		}
		return response;
	}
	
	private void convert(File htmlFile, File pdfFile) throws IOException {
		HtmlConverter.convertToPdf(htmlFile, pdfFile);
	}

}
