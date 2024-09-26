package tech.renovus.solarec.business.impl.report.html.basic.config;

import java.awt.Color;
import java.text.DecimalFormat;

import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;

public class ChartOptions {

	//--- Private properties --------------------
	private String title				= null;
	private String categoryAxisLabel	= null;
	private String valueAxisLabel		= null;
	private double minY					= -1;
	private double maxY					= -1;
	private double minX					= -1;
	private double maxX					= -1;
	private boolean showLegend			= true;
	private int plotSplitFactor			= 0;
	private Color forceColor			= null;
	private StandardCategoryItemLabelGenerator labelGenerator;
	private DecimalFormat numberFormat	= null;
	
	//--- Builder methods -----------------------
	public ChartOptions withTitle(String title)							{ this.setTitle(title); return this; }
	public ChartOptions withCategoryAxisLabel(String categoryAxisLabel)	{ this.setCategoryAxisLabel(categoryAxisLabel); return this; }
	public ChartOptions withValueAxisLabel(String valueAxisLabel)		{ this.setValueAxisLabel(valueAxisLabel); return this; }
	public ChartOptions withMinY(double minY)							{ this.setMinY(minY); return this; }
	public ChartOptions withMaxY(double maxY)							{ this.setMaxY(maxY); return this; }
	public ChartOptions withMinX(double minX)							{ this.setMinX(minX); return this; }
	public ChartOptions withMaxX(double maxX)							{ this.setMaxX(maxX); return this; }
	public ChartOptions withShowLegend(boolean showLegend)				{ this.setShowLegend(showLegend); return this; }
	public ChartOptions withForceColor(Color forceColor)				{ this.setForceColor(forceColor); return this; }
	public ChartOptions withPlotSplitFactor(int plotSplitFactor)		{ this.setPlotSplitFactor(plotSplitFactor); return this; }
	public ChartOptions withLabelGenerator(StandardCategoryItemLabelGenerator labelGenerator) { this.setLabelGenerator(labelGenerator); return this; }
	public ChartOptions withNumberFormat(DecimalFormat numberFormat)	{ this.setNumberFormat(numberFormat); return this; }
	
	//--- Public methods ------------------------
	public boolean mustSetYMaxMin() {
		if (this.maxY <= this.minY) {
			this.maxY = this.minY + 1;
		}
		return this.minY != -1 && this.maxY != -1;
	}

	public boolean mustSetXMaxMin() {
		if (this.maxX <= this.minX) {
			this.maxX = this.minX + 1;
		}
		return this.minX != -1 && this.maxX != -1;
	}
	
	public boolean hasPlotSpliteFactor() { return this.plotSplitFactor > 1; }
	
	//--- Getters and Setters -------------------
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategoryAxisLabel() {
		return categoryAxisLabel;
	}
	public void setCategoryAxisLabel(String categoryAxisLabel) {
		this.categoryAxisLabel = categoryAxisLabel;
	}
	public String getValueAxisLabel() {
		return valueAxisLabel;
	}
	public void setValueAxisLabel(String valueAxisLabel) {
		this.valueAxisLabel = valueAxisLabel;
	}
	public double getMinY() {
		return minY;
	}
	public void setMinY(double minY) {
		this.minY = minY;
	}
	public double getMaxY() {
		return maxY;
	}
	public void setMaxY(double maxX) {
		this.maxY = maxX;
	}
	public boolean isShowLegend() {
		return showLegend;
	}
	public void setShowLegend(boolean showLegend) {
		this.showLegend = showLegend;
	}
	public int getPlotSplitFactor() {
		return plotSplitFactor;
	}
	public void setPlotSplitFactor(int plotSplitFactor) {
		this.plotSplitFactor = plotSplitFactor;
	}
	public Color getForceColor() {
		return forceColor;
	}
	public void setForceColor(Color forceColor) {
		this.forceColor = forceColor;
	}
	public double getMinX() {
		return minX;
	}
	public void setMinX(double minX) {
		this.minX = minX;
	}
	public double getMaxX() {
		return maxX;
	}
	public void setMaxX(double maxX) {
		this.maxX = maxX;
	}
	public StandardCategoryItemLabelGenerator getLabelGenerator() {
		return labelGenerator;
	}
	public void setLabelGenerator(StandardCategoryItemLabelGenerator labelGenerator) {
		this.labelGenerator = labelGenerator;
	}
	public DecimalFormat getNumberFormat() {
		return numberFormat;
	}
	public void setNumberFormat(DecimalFormat numberFormat) {
		this.numberFormat = numberFormat;
	}
}
