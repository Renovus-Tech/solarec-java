package tech.renovus.solarec.business.impl.report.html.basic;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.springframework.beans.factory.annotation.Autowired;

import tech.renovus.solarec.UserData;
import tech.renovus.solarec.business.EmailService;
import tech.renovus.solarec.business.SolarService;
import tech.renovus.solarec.business.TranslationService;
import tech.renovus.solarec.business.impl.chart.base.Chart;
import tech.renovus.solarec.business.impl.report.html.basic.config.ChartOptions;
import tech.renovus.solarec.configuration.RenovusSolarecConfiguration;
import tech.renovus.solarec.db.data.dao.interfaces.LocationDao;
import tech.renovus.solarec.util.ClassUtil;
import tech.renovus.solarec.util.DateUtil;
import tech.renovus.solarec.util.NumberUtil;
import tech.renovus.solarec.util.StringUtil;
import tech.renovus.solarec.vo.custom.chart.Error;
import tech.renovus.solarec.vo.rest.chart.ChartFilter;
import tech.renovus.solarec.vo.rest.chart.IFilter;

public abstract class BasicHtmlFactory <T extends IFilter> implements IReportHtml<T> {

	//--- Private properties -------------------
	public static final Color COLOR_1 = new Color(0, 63, 92); //#003f5c
	public static final Color COLOR_2 = new Color(122, 81, 149); //#7a5195
	public static final Color COLOR_3 = new Color(188, 80, 144); //#bc5090
	public static final Color COLOR_4 = new Color(239, 86, 117); //#ef5675
	public static final Color COLOR_5 = new Color(255, 118, 74); //#ff764a
	public static final Color COLOR_6 = new Color(255, 166, 0); //#ffa600
	public static final Color COLOR_7 = new Color(156, 235, 1); //#9ceb01
	public static final Color COLOR_8 = new Color(23, 62, 89); //173e59

	public static final Color COLOR_AVERAGE		= new Color(01, 00, 23); //#010023
	public static final Color COLOR_OTHER		= new Color(124, 124, 124); //#7c7c7c
	
	public static final Color COLOR_TOTAL		= new Color(22, 22, 22); //#222222
	public static final Color COLOR_MAST		= new Color(243, 46, 46); //#f32e2e
	
	private static final Color[] COLORS	= new Color[] {COLOR_3, COLOR_2, COLOR_1, COLOR_4, COLOR_5, COLOR_6, COLOR_7};
	
	private static final java.awt.Font FONT_TITLE		= new java.awt.Font( "Century Gothic", java.awt.Font.BOLD, 26 );
	private static final java.awt.Font FONT_LEGEND		= new java.awt.Font( "Century Gothic", java.awt.Font.PLAIN, 18 );
	private static final java.awt.Font FONT_AXIS_LABEL	= new java.awt.Font( "Century Gothic", java.awt.Font.BOLD, 18 );
	private static final java.awt.Font FONT_AXIS		= new java.awt.Font( "Century Gothic", java.awt.Font.PLAIN, 18 );
	
	private static final DecimalFormat NUMBER_FORMAT 	= new DecimalFormat("#,##0.00");
	
	//--- Resources ----------------------------
	@Autowired protected RenovusSolarecConfiguration config;
	@Autowired protected EmailService emailService;
	@Autowired protected TranslationService translationService;

	@Resource protected SolarService chartService;
	@Resource protected LocationDao locDao;
	
	//--- Abstract methods ----------------------
	@Override public abstract String getTitle();
	@Override public abstract String createStyle();
	@Override public abstract String createHtml(UserData userData, T filter);
	
	//--- Private methods -----------------------
	private String generatePNG(JFreeChart chart) throws IOException {
		File imageFile = File.createTempFile(UUID.randomUUID().toString(), ".png");
	    ChartUtils.saveChartAsPNG(imageFile, chart, 1200, 600);
	    
	    return imageFile.getName();
	}
	
	private void setTexts(JFreeChart chart, LegendTitle legend, CategoryItemRenderer render, ValueAxis valueRange) {
		if (chart != null) {
			chart.getTitle().setFont(FONT_TITLE);
		}
		
		if (legend != null) {
			legend.setPosition(RectangleEdge.TOP);
			legend.setItemFont(FONT_LEGEND);
		}
		
		if (render != null) {
			ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_LEFT);
			render.setDefaultItemLabelsVisible(false);
			render.setDefaultPositiveItemLabelPosition(position);
			render.setDefaultItemLabelFont(FONT_AXIS);
		}
		
		if (valueRange != null) {
			valueRange.setLabelFont(FONT_AXIS_LABEL);
			valueRange.setTickLabelFont(FONT_AXIS);
		}
	}
	
	private void setRange(ValueAxis axis, ChartOptions options) {
		axis.setAutoRange(true);
		axis.setLabelFont(FONT_AXIS_LABEL);
		axis.setTickLabelFont(FONT_AXIS);
		if (options.mustSetYMaxMin()) {
			if (axis instanceof NumberAxis) ((NumberAxis) axis).setAutoRangeIncludesZero(false);
			axis.setAutoRange(false);
			axis.setRange(options.getMinY(), options.getMaxY());
		}
		
		if (axis instanceof NumberAxis) {
			((NumberAxis) axis).setNumberFormatOverride(options.getNumberFormat() == null ? NUMBER_FORMAT : options.getNumberFormat());
		}
	}

	private void setDomainAxis(CategoryPlot plot) {
		CategoryAxis axis	= plot.getDomainAxis();
		axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		axis.setVisible(true);
		axis.setLabelFont(FONT_AXIS_LABEL);
		axis.setTickLabelFont(FONT_AXIS);
		axis.setMaximumCategoryLabelLines(10);
	}
	
	private void setDomainAxis(XYPlot plot, ChartOptions options) {
		ValueAxis axis	= plot.getDomainAxis();
		axis.setVisible(true);
		axis.setLabelFont(FONT_AXIS_LABEL);
		axis.setTickLabelFont(FONT_AXIS);
		
		if (options.mustSetXMaxMin()) {
			axis.setRange(options.getMinX(), options.getMaxX());
		}
	}
	
	private void setColors(Plot plot, CategoryItemRenderer render, int amountSeries) { this.setColors(plot, render, amountSeries, null); }
	private void setColors(Plot plot, CategoryItemRenderer render, int amountSeries, Color forceColor) {
		if (plot != null) {
			plot.setBackgroundPaint(Color.WHITE);
		}
		
		if (render != null) {
			if (amountSeries == 1 && forceColor != null) {
				render.setSeriesPaint(0, forceColor);
			} else {
				for (int serie = 0; serie <= amountSeries && serie < COLORS.length; serie ++ ) {
					render.setSeriesPaint(serie, COLORS[serie % COLORS.length]);
				}
			}
			
			if (render instanceof LineAndShapeRenderer) {
				LineAndShapeRenderer lineRender = (LineAndShapeRenderer) render;
				lineRender.setDefaultItemLabelsVisible(false);
				lineRender.setDefaultShapesVisible(true);
			}
		}
	}
	
	private JFreeChart createLineChart(DefaultCategoryDataset dataset, ChartOptions options) {
		JFreeChart chart			= ChartFactory.createLineChart(
											options.getTitle(), 
											options.getCategoryAxisLabel(), 
											options.getValueAxisLabel(), 
											dataset, 
											PlotOrientation.VERTICAL, 
											options.isShowLegend(), 
											false, 
											false
										);
		return chart;
	}
	
	private JFreeChart createXYLineChart(XYDataset dataset, ChartOptions options) {
		JFreeChart chart			= ChartFactory.createXYLineChart(
											options.getTitle(), 
											options.getCategoryAxisLabel(), 
											options.getValueAxisLabel(), 
											dataset, 
											PlotOrientation.VERTICAL, 
											options.isShowLegend(), 
											false, 
											false
										);
		return chart;
	}
	
	private JFreeChart createBarChart(DefaultCategoryDataset dataset, ChartOptions options) {
		JFreeChart chart			= ChartFactory.createBarChart(
				options.getTitle(), 
				options.getCategoryAxisLabel(), 
				options.getValueAxisLabel(), 
				dataset, 
				PlotOrientation.VERTICAL, 
				options.isShowLegend(), 
				false, 
				false
				);
		return chart;
	}
	
	
	//--- Public methods ------------------------
	public String getFromTo(Date dateFrom, Date dateTo) {
		return this.getFromTo(DateUtil.formatDateTime(dateFrom, DateUtil.FMT_PARAMETER_DATE), DateUtil.formatDateTime(dateTo, DateUtil.FMT_PARAMETER_DATE));
	}
	
	public String getFromTo(String dateFrom, String dateTo) {
		String resultFrom = StringUtil.notEmpty(dateFrom) ? StringUtil.split(dateFrom, StringUtil.SPACE_STRING)[0] : StringUtil.EMPTY_STRING;		
		String resultTo = StringUtil.notEmpty(dateTo) ? StringUtil.split(dateTo, StringUtil.SPACE_STRING)[0] : StringUtil.EMPTY_STRING;
		
		return StringUtil.replace(ClassUtil.equals(resultFrom, resultTo) ? resultFrom : resultFrom + " - " + resultTo, "/" , "-");
	}
	
	public String generatePeriodHtml(ChartFilter filter, UserData userData) {
		return new StringBuilder()
				.append("<div><small>")
				.append(this.translationService.forLabel(userData.getLocale(), "report.result.general.previous_period"))
				.append(": <strong>")
				.append(DateUtil.formatDateTime(filter.getFrom(), DateUtil.FMT_PARAMETER_DATE))
				.append("</strong> ")
				.append(this.translationService.forLabel(userData.getLocale(), "report.result.general.previous_period_to"))
				.append("<strong> ")
				.append(DateUtil.formatDateTime(filter.getTo(), DateUtil.FMT_PARAMETER_DATE))
				.append("</strong></small></div>")
				.toString();
	}
	
	public String generatePeriodHtml(Chart chart) {
		return new StringBuilder()
				.append("<div><small>")
				.append("Previous data period: <strong>")
				.append(chart.getFrom())
				.append("</strong> to <strong>")
				.append(chart.getTo())
				.append("</strong></small></div>")
				.toString();
	}
	
	public String generateHtmlError(Error error) {
		return new StringBuilder()
				.append("<div class='error'>")
				.append("<p>Error found (")
				.append(error.getCode())
				.append("): ")
				.append(error.getMessage())
				.append("</p>")
				.append("</div>")
				.toString();
	}
	
	public String generateHtmlError(Exception e) {
		return "<div class='error'><p>Error found: " + e.getLocalizedMessage() + "</p><small>" + StringUtil.toString(e, true) + "</small></p></div>";	
	}
	
	public String generateErrorHtml(String message) {
		return "<div class='error'><p>" + message + "</p></div>";
	}
	
	public String generateNumberValue(Double value, int decimals) { return this.generateNumberValue(value == null ? 0 : value.doubleValue(), decimals); }
	public String generateNumberValue(double value, int decimals) { return this.generateNumberValue(NumberUtil.truncate(value, decimals)); }
	public String generateNumberValue(Double value) { return this.generateNumberValue(value == null ? 0 : value.doubleValue()); }
	public String generateNumberValue(double value) { return NUMBER_FORMAT.format(value); }
	
	//--- Chart methods -------------------------
	protected String generateLineBarChart(DefaultCategoryDataset lineDataSet, DefaultCategoryDataset barDataSet, ChartOptions lineOptions, ChartOptions barOptions) throws IOException {
		JFreeChart chart				= this.createLineChart(lineDataSet, lineOptions);
		LegendTitle legend				= chart.getLegend();
		CategoryPlot plot				= (CategoryPlot) chart.getPlot();
		CategoryItemRenderer lineRender = plot.getRenderer();
		ValueAxis lineValueRange		= plot.getRangeAxis();
		
		if (barOptions != null) {
			NumberAxis barValueRange = new NumberAxis(barOptions.getValueAxisLabel());
			plot.setRangeAxis(1, barValueRange);
			this.setRange(barValueRange, barOptions);
		}
		
		CategoryItemRenderer barRenderer = new BarRenderer();
		plot.setDataset(1, barDataSet);
		plot.setRenderer(1, barRenderer);
		
		this.setRange(lineValueRange, lineOptions);
		
	    this.setDomainAxis(plot);
	    this.setTexts(chart, legend, lineRender, null);
	    this.setColors(plot, lineRender, lineDataSet.getRowCount(), COLOR_6);
	    
	    this.setColors(plot, barRenderer, barDataSet.getRowCount());
	
	    return this.generatePNG(chart);
	}

	protected String generateBarLineChart(DefaultCategoryDataset toBarDataSet, DefaultCategoryDataset toLineDataSet, ChartOptions toBarOptions, ChartOptions toLineOptions) throws IOException {
		JFreeChart chart					= this.createBarChart(toBarDataSet, toBarOptions);
		LegendTitle legend					= chart.getLegend();
		CategoryPlot plot					= (CategoryPlot) chart.getPlot();
		CategoryItemRenderer toBarRender	= plot.getRenderer();
		ValueAxis barValueRange				= plot.getRangeAxis();
		
		CategoryItemRenderer toLineRender = new LineAndShapeRenderer();
		plot.setDataset(1, toLineDataSet);
		plot.setRenderer(1, toLineRender);
		
		if (toLineOptions != null) {
			NumberAxis lineValueRange = new NumberAxis(toLineOptions.getValueAxisLabel());
			plot.setRangeAxis(1, lineValueRange);
			plot.mapDatasetToRangeAxis(1, 1);
			this.setRange(lineValueRange, toLineOptions);
		}
		
		this.setRange(barValueRange, toBarOptions);
	    this.setDomainAxis(plot);
	    this.setTexts(chart, legend, toBarRender, null);
	    this.setColors(plot, toLineRender, toLineDataSet.getRowCount(), COLOR_6);
	    this.setColors(plot, toBarRender, toBarDataSet.getRowCount());
	
	    return this.generatePNG(chart);
	}

	
	protected String generateBarChart(DefaultCategoryDataset dataset, ChartOptions options) throws IOException {
		JFreeChart chart			= ChartFactory.createBarChart(
											options.getTitle(), 
											options.getCategoryAxisLabel(), 
											options.getValueAxisLabel(), 
											dataset, 
											PlotOrientation.VERTICAL, 
											options.isShowLegend(), 
											false, 
											false
										);
		LegendTitle legend			= chart.getLegend();
		CategoryPlot plot			= (CategoryPlot) chart.getPlot();
		CategoryItemRenderer render = plot.getRenderer();
		ValueAxis valueRange		= plot.getRangeAxis();
		
		this.setRange(valueRange, options);
	    this.setDomainAxis(plot);
	    this.setTexts(chart, legend, render, null);
	    this.setColors(plot, render, dataset.getRowCount());
	
	    return this.generatePNG(chart);
	}
	
	protected final String generateLineChart(DefaultCategoryDataset dataset, ChartOptions options)	throws IOException {
		JFreeChart chart			= this.createLineChart(dataset, options);
		LegendTitle legend			= chart.getLegend();
        CategoryPlot plot			= (CategoryPlot) chart.getPlot();
        ValueAxis valueRange		= plot.getRangeAxis();
        CategoryItemRenderer render	= plot.getRenderer();
        
        if (options.hasPlotSpliteFactor()) {
	        CategoryAxis domainAxis = plot.getDomainAxis();
	        int maxSize = plot.getCategories().size();
			for (int i = 0; i < maxSize; i++) {
				if (i % options.getPlotSplitFactor() != 0) {
					String catName = (String) plot.getCategories().get(i);
					domainAxis.setTickLabelPaint(catName, new java.awt.Color(255,255,255));
					domainAxis.setTickLabelFont(catName, new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 0));
				}
			}
        }
        
		this.setRange(valueRange, options);
        this.setDomainAxis(plot);
        this.setTexts(chart, legend, render, null);
		this.setColors(plot, render, dataset.getRowCount(), options.getForceColor());
        
        return this.generatePNG(chart);
	}
	
	protected String generateLineChart(DefaultCategoryDataset lineDataSet, DefaultCategoryDataset lineDataSet2, ChartOptions lineOptions, ChartOptions lineOptions2) throws IOException {
		JFreeChart chart				= this.createLineChart(lineDataSet, lineOptions);
		LegendTitle legend				= chart.getLegend();
		CategoryPlot plot				= (CategoryPlot) chart.getPlot();
		CategoryItemRenderer lineRender = plot.getRenderer();
		ValueAxis lineValueRange		= plot.getRangeAxis();
		this.setRange(lineValueRange, lineOptions);
		
		LineAndShapeRenderer lineRender2 = new LineAndShapeRenderer();
		plot.setDataset(1, lineDataSet2);
		plot.setRenderer(1, lineRender2);
		
		if (lineOptions2 != null) {
			NumberAxis valueRange2 = new NumberAxis(lineOptions2.getValueAxisLabel());
			this.setRange(valueRange2, lineOptions2);
			plot.setRangeAxis(1, valueRange2);
			plot.mapDatasetToRangeAxis(1, 1);
		}
		
		if (lineOptions.hasPlotSpliteFactor()) {
	        CategoryAxis domainAxis = plot.getDomainAxis();
	        int maxSize = plot.getCategories().size();
			for (int i = 0; i < maxSize; i++) {
				if (i % lineOptions.getPlotSplitFactor() != 0) {
					String catName = (String) plot.getCategories().get(i);
					domainAxis.setTickLabelPaint(catName, new java.awt.Color(255,255,255));
					domainAxis.setTickLabelFont(catName, new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 0));
				}
			}
        }
		
	    this.setDomainAxis(plot);
	    this.setTexts(chart, legend, lineRender, null);
	    this.setColors(plot, lineRender, lineDataSet.getRowCount(), COLOR_6);
	    this.setColors(plot, lineRender2, lineDataSet2.getRowCount(), COLOR_8);
	
	    return this.generatePNG(chart);
	}
	
	protected final String generateXYLineChart(XYDataset dataset, ChartOptions options)	throws IOException {
		JFreeChart chart			= this.createXYLineChart(dataset, options);
		LegendTitle legend			= chart.getLegend();
        XYPlot plot			= (XYPlot) chart.getPlot();
        ValueAxis valueRange		= plot.getRangeAxis();
        XYItemRenderer render	= plot.getRenderer();
        
        if (options.hasPlotSpliteFactor()) {
	        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();            
            domainAxis.setRange(options.getMinX(), options.getMaxX());
            domainAxis.setTickUnit(new NumberTickUnit(options.getPlotSplitFactor()));
            plot.mapDatasetToRangeAxis(1, 1);
        }
        
//		this.setRange(valueRange, options);
//        this.setDomainAxis(plot);
//        this.setTexts(chart, legend, render, null);
//		this.setColors(plot, render, dataset.getRowCount(), options.getForceColor());
        
        this.setRange(valueRange, options);
		this.setDomainAxis(plot, options);
        this.setTexts(chart, legend, null, null);
		this.setColors(plot, null, -1);
        
        return this.generatePNG(chart);
	}
	
	protected final String generateStackedBarChart(DefaultCategoryDataset dataset, ChartOptions options) throws IOException {
		JFreeChart chart			= ChartFactory.createStackedBarChart(
											options.getTitle(), 
											options.getCategoryAxisLabel(), 
											options.getValueAxisLabel(), 
											dataset, 
											PlotOrientation.VERTICAL, 
											options.isShowLegend(), 
											true, 
											false
										);
		LegendTitle legend			= chart.getLegend();
		CategoryPlot plot			= (CategoryPlot) chart.getPlot();
		StackedBarRenderer render	= (StackedBarRenderer) plot.getRenderer();
		ValueAxis valueRange		= plot.getRangeAxis();

		this.setRange(valueRange, options);
	    this.setDomainAxis(plot);
	    this.setTexts(chart, legend, render, valueRange);
	    this.setColors(plot, render, dataset.getRowCount());
	
	    if (options.getLabelGenerator() != null) {
		    render.setDrawBarOutline(false);
			render.setDefaultItemLabelsVisible(true);
			render.setDefaultItemLabelsVisible(true);
			render.setDefaultItemLabelGenerator(options.getLabelGenerator());
	    }
	    
	    return this.generatePNG(chart);
	}
	
	protected final String generateScatterChart(XYDataset dataset, ChartOptions options) throws IOException {
		JFreeChart chart		= ChartFactory.createScatterPlot(
										options.getTitle(), 
										options.getCategoryAxisLabel(), 
										options.getValueAxisLabel(), 
										dataset, 
										PlotOrientation.VERTICAL, 
										options.isShowLegend(), 
										false, 
										false
									);
		LegendTitle legend		= chart.getLegend();
		XYPlot plot				= (XYPlot) chart.getPlot();
		ValueAxis valueRange	= plot.getRangeAxis();
		
		this.setRange(valueRange, options);
		this.setDomainAxis(plot, options);
        this.setTexts(chart, legend, null, null);
		this.setColors(plot, null, -1);

        return this.generatePNG(chart);
	}
	
	protected final String generateSpiderWebChart(DefaultCategoryDataset dataset, ChartOptions options) throws IOException {
		
		SpiderWebPlot plot = new SpiderWebPlot(dataset);
	    plot.setStartAngle(90);
	    plot.setInteriorGap(0.15);
	    plot.setOutlinePaint(null);
	    
	    JFreeChart chart = new JFreeChart(options.getTitle(),FONT_TITLE, plot, false);
	    LegendTitle legend = new LegendTitle(plot);
	    legend.setPosition(RectangleEdge.TOP);
	    chart.addSubtitle(legend);
	    chart.setBackgroundPaint(Color.WHITE);
		
        this.setTexts(chart, legend, null, null);
		this.setColors(plot, null, -1);

        return this.generatePNG(chart);
	}
	
}
