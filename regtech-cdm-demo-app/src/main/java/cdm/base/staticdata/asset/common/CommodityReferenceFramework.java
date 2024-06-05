package cdm.base.staticdata.asset.common;

import cdm.base.math.CapacityUnitEnum;
import cdm.base.math.WeatherUnitEnum;
import cdm.base.staticdata.asset.common.CommodityReferenceFramework;
import cdm.base.staticdata.asset.common.CommodityReferenceFramework.CommodityReferenceFrameworkBuilder;
import cdm.base.staticdata.asset.common.CommodityReferenceFramework.CommodityReferenceFrameworkBuilderImpl;
import cdm.base.staticdata.asset.common.CommodityReferenceFramework.CommodityReferenceFrameworkImpl;
import cdm.base.staticdata.asset.common.meta.CommodityReferenceFrameworkMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specifies the type of commodity.
 * @version ${project.version}
 */
@RosettaDataType(value="CommodityReferenceFramework", builder=CommodityReferenceFramework.CommodityReferenceFrameworkBuilderImpl.class, version="${project.version}")
public interface CommodityReferenceFramework extends RosettaModelObject {

	CommodityReferenceFrameworkMeta metaData = new CommodityReferenceFrameworkMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Identifies the commodity more specifically. Where possible, this should follow the naming convention used in the 2005 ISDA Commodity Definitions SubAnnex A, including the subCommodity and additional qualifiers, but should be limited to 256 characters or less.
	 */
	String getCommodityName();
	/**
	 * Provides an enumerated value for a capacity unit, generally used in the context of defining quantities for commodities.
	 */
	CapacityUnitEnum getCapacityUnit();
	/**
	 * Provides an enumerated values for a weather unit, generally used in the context of defining quantities for commodities.
	 */
	WeatherUnitEnum getWeatherUnit();
	/**
	 * Defines the currency in which the commodity is priced.
	 */
	FieldWithMetaString getCurrency();

	/*********************** Build Methods  ***********************/
	CommodityReferenceFramework build();
	
	CommodityReferenceFramework.CommodityReferenceFrameworkBuilder toBuilder();
	
	static CommodityReferenceFramework.CommodityReferenceFrameworkBuilder builder() {
		return new CommodityReferenceFramework.CommodityReferenceFrameworkBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CommodityReferenceFramework> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CommodityReferenceFramework> getType() {
		return CommodityReferenceFramework.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("commodityName"), String.class, getCommodityName(), this);
		processor.processBasic(path.newSubPath("capacityUnit"), CapacityUnitEnum.class, getCapacityUnit(), this);
		processor.processBasic(path.newSubPath("weatherUnit"), WeatherUnitEnum.class, getWeatherUnit(), this);
		processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.class, getCurrency());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CommodityReferenceFrameworkBuilder extends CommodityReferenceFramework, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCurrency();
		FieldWithMetaString.FieldWithMetaStringBuilder getCurrency();
		CommodityReferenceFramework.CommodityReferenceFrameworkBuilder setCommodityName(String commodityName);
		CommodityReferenceFramework.CommodityReferenceFrameworkBuilder setCapacityUnit(CapacityUnitEnum capacityUnit);
		CommodityReferenceFramework.CommodityReferenceFrameworkBuilder setWeatherUnit(WeatherUnitEnum weatherUnit);
		CommodityReferenceFramework.CommodityReferenceFrameworkBuilder setCurrency(FieldWithMetaString currency0);
		CommodityReferenceFramework.CommodityReferenceFrameworkBuilder setCurrencyValue(String currency1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("commodityName"), String.class, getCommodityName(), this);
			processor.processBasic(path.newSubPath("capacityUnit"), CapacityUnitEnum.class, getCapacityUnit(), this);
			processor.processBasic(path.newSubPath("weatherUnit"), WeatherUnitEnum.class, getWeatherUnit(), this);
			processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCurrency());
		}
		

		CommodityReferenceFramework.CommodityReferenceFrameworkBuilder prune();
	}

	/*********************** Immutable Implementation of CommodityReferenceFramework  ***********************/
	class CommodityReferenceFrameworkImpl implements CommodityReferenceFramework {
		private final String commodityName;
		private final CapacityUnitEnum capacityUnit;
		private final WeatherUnitEnum weatherUnit;
		private final FieldWithMetaString currency;
		
		protected CommodityReferenceFrameworkImpl(CommodityReferenceFramework.CommodityReferenceFrameworkBuilder builder) {
			this.commodityName = builder.getCommodityName();
			this.capacityUnit = builder.getCapacityUnit();
			this.weatherUnit = builder.getWeatherUnit();
			this.currency = ofNullable(builder.getCurrency()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("commodityName")
		public String getCommodityName() {
			return commodityName;
		}
		
		@Override
		@RosettaAttribute("capacityUnit")
		public CapacityUnitEnum getCapacityUnit() {
			return capacityUnit;
		}
		
		@Override
		@RosettaAttribute("weatherUnit")
		public WeatherUnitEnum getWeatherUnit() {
			return weatherUnit;
		}
		
		@Override
		@RosettaAttribute("currency")
		public FieldWithMetaString getCurrency() {
			return currency;
		}
		
		@Override
		public CommodityReferenceFramework build() {
			return this;
		}
		
		@Override
		public CommodityReferenceFramework.CommodityReferenceFrameworkBuilder toBuilder() {
			CommodityReferenceFramework.CommodityReferenceFrameworkBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CommodityReferenceFramework.CommodityReferenceFrameworkBuilder builder) {
			ofNullable(getCommodityName()).ifPresent(builder::setCommodityName);
			ofNullable(getCapacityUnit()).ifPresent(builder::setCapacityUnit);
			ofNullable(getWeatherUnit()).ifPresent(builder::setWeatherUnit);
			ofNullable(getCurrency()).ifPresent(builder::setCurrency);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CommodityReferenceFramework _that = getType().cast(o);
		
			if (!Objects.equals(commodityName, _that.getCommodityName())) return false;
			if (!Objects.equals(capacityUnit, _that.getCapacityUnit())) return false;
			if (!Objects.equals(weatherUnit, _that.getWeatherUnit())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (commodityName != null ? commodityName.hashCode() : 0);
			_result = 31 * _result + (capacityUnit != null ? capacityUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (weatherUnit != null ? weatherUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CommodityReferenceFramework {" +
				"commodityName=" + this.commodityName + ", " +
				"capacityUnit=" + this.capacityUnit + ", " +
				"weatherUnit=" + this.weatherUnit + ", " +
				"currency=" + this.currency +
			'}';
		}
	}

	/*********************** Builder Implementation of CommodityReferenceFramework  ***********************/
	class CommodityReferenceFrameworkBuilderImpl implements CommodityReferenceFramework.CommodityReferenceFrameworkBuilder {
	
		protected String commodityName;
		protected CapacityUnitEnum capacityUnit;
		protected WeatherUnitEnum weatherUnit;
		protected FieldWithMetaString.FieldWithMetaStringBuilder currency;
	
		public CommodityReferenceFrameworkBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("commodityName")
		public String getCommodityName() {
			return commodityName;
		}
		
		@Override
		@RosettaAttribute("capacityUnit")
		public CapacityUnitEnum getCapacityUnit() {
			return capacityUnit;
		}
		
		@Override
		@RosettaAttribute("weatherUnit")
		public WeatherUnitEnum getWeatherUnit() {
			return weatherUnit;
		}
		
		@Override
		@RosettaAttribute("currency")
		public FieldWithMetaString.FieldWithMetaStringBuilder getCurrency() {
			return currency;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCurrency() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (currency!=null) {
				result = currency;
			}
			else {
				result = currency = FieldWithMetaString.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("commodityName")
		public CommodityReferenceFramework.CommodityReferenceFrameworkBuilder setCommodityName(String commodityName) {
			this.commodityName = commodityName==null?null:commodityName;
			return this;
		}
		@Override
		@RosettaAttribute("capacityUnit")
		public CommodityReferenceFramework.CommodityReferenceFrameworkBuilder setCapacityUnit(CapacityUnitEnum capacityUnit) {
			this.capacityUnit = capacityUnit==null?null:capacityUnit;
			return this;
		}
		@Override
		@RosettaAttribute("weatherUnit")
		public CommodityReferenceFramework.CommodityReferenceFrameworkBuilder setWeatherUnit(WeatherUnitEnum weatherUnit) {
			this.weatherUnit = weatherUnit==null?null:weatherUnit;
			return this;
		}
		@Override
		@RosettaAttribute("currency")
		public CommodityReferenceFramework.CommodityReferenceFrameworkBuilder setCurrency(FieldWithMetaString currency) {
			this.currency = currency==null?null:currency.toBuilder();
			return this;
		}
		@Override
		public CommodityReferenceFramework.CommodityReferenceFrameworkBuilder setCurrencyValue(String currency) {
			this.getOrCreateCurrency().setValue(currency);
			return this;
		}
		
		@Override
		public CommodityReferenceFramework build() {
			return new CommodityReferenceFramework.CommodityReferenceFrameworkImpl(this);
		}
		
		@Override
		public CommodityReferenceFramework.CommodityReferenceFrameworkBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CommodityReferenceFramework.CommodityReferenceFrameworkBuilder prune() {
			if (currency!=null && !currency.prune().hasData()) currency = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCommodityName()!=null) return true;
			if (getCapacityUnit()!=null) return true;
			if (getWeatherUnit()!=null) return true;
			if (getCurrency()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CommodityReferenceFramework.CommodityReferenceFrameworkBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CommodityReferenceFramework.CommodityReferenceFrameworkBuilder o = (CommodityReferenceFramework.CommodityReferenceFrameworkBuilder) other;
			
			merger.mergeRosetta(getCurrency(), o.getCurrency(), this::setCurrency);
			
			merger.mergeBasic(getCommodityName(), o.getCommodityName(), this::setCommodityName);
			merger.mergeBasic(getCapacityUnit(), o.getCapacityUnit(), this::setCapacityUnit);
			merger.mergeBasic(getWeatherUnit(), o.getWeatherUnit(), this::setWeatherUnit);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CommodityReferenceFramework _that = getType().cast(o);
		
			if (!Objects.equals(commodityName, _that.getCommodityName())) return false;
			if (!Objects.equals(capacityUnit, _that.getCapacityUnit())) return false;
			if (!Objects.equals(weatherUnit, _that.getWeatherUnit())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (commodityName != null ? commodityName.hashCode() : 0);
			_result = 31 * _result + (capacityUnit != null ? capacityUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (weatherUnit != null ? weatherUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CommodityReferenceFrameworkBuilder {" +
				"commodityName=" + this.commodityName + ", " +
				"capacityUnit=" + this.capacityUnit + ", " +
				"weatherUnit=" + this.weatherUnit + ", " +
				"currency=" + this.currency +
			'}';
		}
	}
}
