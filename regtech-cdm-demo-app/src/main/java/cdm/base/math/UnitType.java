package cdm.base.math;

import cdm.base.math.CapacityUnitEnum;
import cdm.base.math.FinancialUnitEnum;
import cdm.base.math.UnitType;
import cdm.base.math.UnitType.UnitTypeBuilder;
import cdm.base.math.UnitType.UnitTypeBuilderImpl;
import cdm.base.math.UnitType.UnitTypeImpl;
import cdm.base.math.WeatherUnitEnum;
import cdm.base.math.meta.UnitTypeMeta;
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
 * Defines the unit to be used for price, quantity, or other purposes
 * @version ${project.version}
 */
@RosettaDataType(value="UnitType", builder=UnitType.UnitTypeBuilderImpl.class, version="${project.version}")
public interface UnitType extends RosettaModelObject {

	UnitTypeMeta metaData = new UnitTypeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Provides an enumerated value for a capacity unit, generally used in the context of defining quantities for commodities.
	 */
	CapacityUnitEnum getCapacityUnit();
	/**
	 * Provides an enumerated values for a weather unit, generally used in the context of defining quantities for commodities.
	 */
	WeatherUnitEnum getWeatherUnit();
	/**
	 * Provides an enumerated value for financial units, generally used in the context of defining quantities for securities.
	 */
	FinancialUnitEnum getFinancialUnit();
	/**
	 * Defines the currency to be used as a unit for a price, quantity, or other purpose.
	 */
	FieldWithMetaString getCurrency();

	/*********************** Build Methods  ***********************/
	UnitType build();
	
	UnitType.UnitTypeBuilder toBuilder();
	
	static UnitType.UnitTypeBuilder builder() {
		return new UnitType.UnitTypeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends UnitType> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends UnitType> getType() {
		return UnitType.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("capacityUnit"), CapacityUnitEnum.class, getCapacityUnit(), this);
		processor.processBasic(path.newSubPath("weatherUnit"), WeatherUnitEnum.class, getWeatherUnit(), this);
		processor.processBasic(path.newSubPath("financialUnit"), FinancialUnitEnum.class, getFinancialUnit(), this);
		processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.class, getCurrency());
	}
	

	/*********************** Builder Interface  ***********************/
	interface UnitTypeBuilder extends UnitType, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCurrency();
		FieldWithMetaString.FieldWithMetaStringBuilder getCurrency();
		UnitType.UnitTypeBuilder setCapacityUnit(CapacityUnitEnum capacityUnit);
		UnitType.UnitTypeBuilder setWeatherUnit(WeatherUnitEnum weatherUnit);
		UnitType.UnitTypeBuilder setFinancialUnit(FinancialUnitEnum financialUnit);
		UnitType.UnitTypeBuilder setCurrency(FieldWithMetaString currency0);
		UnitType.UnitTypeBuilder setCurrencyValue(String currency1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("capacityUnit"), CapacityUnitEnum.class, getCapacityUnit(), this);
			processor.processBasic(path.newSubPath("weatherUnit"), WeatherUnitEnum.class, getWeatherUnit(), this);
			processor.processBasic(path.newSubPath("financialUnit"), FinancialUnitEnum.class, getFinancialUnit(), this);
			processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCurrency());
		}
		

		UnitType.UnitTypeBuilder prune();
	}

	/*********************** Immutable Implementation of UnitType  ***********************/
	class UnitTypeImpl implements UnitType {
		private final CapacityUnitEnum capacityUnit;
		private final WeatherUnitEnum weatherUnit;
		private final FinancialUnitEnum financialUnit;
		private final FieldWithMetaString currency;
		
		protected UnitTypeImpl(UnitType.UnitTypeBuilder builder) {
			this.capacityUnit = builder.getCapacityUnit();
			this.weatherUnit = builder.getWeatherUnit();
			this.financialUnit = builder.getFinancialUnit();
			this.currency = ofNullable(builder.getCurrency()).map(f->f.build()).orElse(null);
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
		@RosettaAttribute("financialUnit")
		public FinancialUnitEnum getFinancialUnit() {
			return financialUnit;
		}
		
		@Override
		@RosettaAttribute("currency")
		public FieldWithMetaString getCurrency() {
			return currency;
		}
		
		@Override
		public UnitType build() {
			return this;
		}
		
		@Override
		public UnitType.UnitTypeBuilder toBuilder() {
			UnitType.UnitTypeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(UnitType.UnitTypeBuilder builder) {
			ofNullable(getCapacityUnit()).ifPresent(builder::setCapacityUnit);
			ofNullable(getWeatherUnit()).ifPresent(builder::setWeatherUnit);
			ofNullable(getFinancialUnit()).ifPresent(builder::setFinancialUnit);
			ofNullable(getCurrency()).ifPresent(builder::setCurrency);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			UnitType _that = getType().cast(o);
		
			if (!Objects.equals(capacityUnit, _that.getCapacityUnit())) return false;
			if (!Objects.equals(weatherUnit, _that.getWeatherUnit())) return false;
			if (!Objects.equals(financialUnit, _that.getFinancialUnit())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (capacityUnit != null ? capacityUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (weatherUnit != null ? weatherUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (financialUnit != null ? financialUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "UnitType {" +
				"capacityUnit=" + this.capacityUnit + ", " +
				"weatherUnit=" + this.weatherUnit + ", " +
				"financialUnit=" + this.financialUnit + ", " +
				"currency=" + this.currency +
			'}';
		}
	}

	/*********************** Builder Implementation of UnitType  ***********************/
	class UnitTypeBuilderImpl implements UnitType.UnitTypeBuilder {
	
		protected CapacityUnitEnum capacityUnit;
		protected WeatherUnitEnum weatherUnit;
		protected FinancialUnitEnum financialUnit;
		protected FieldWithMetaString.FieldWithMetaStringBuilder currency;
	
		public UnitTypeBuilderImpl() {
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
		@RosettaAttribute("financialUnit")
		public FinancialUnitEnum getFinancialUnit() {
			return financialUnit;
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
		@RosettaAttribute("capacityUnit")
		public UnitType.UnitTypeBuilder setCapacityUnit(CapacityUnitEnum capacityUnit) {
			this.capacityUnit = capacityUnit==null?null:capacityUnit;
			return this;
		}
		@Override
		@RosettaAttribute("weatherUnit")
		public UnitType.UnitTypeBuilder setWeatherUnit(WeatherUnitEnum weatherUnit) {
			this.weatherUnit = weatherUnit==null?null:weatherUnit;
			return this;
		}
		@Override
		@RosettaAttribute("financialUnit")
		public UnitType.UnitTypeBuilder setFinancialUnit(FinancialUnitEnum financialUnit) {
			this.financialUnit = financialUnit==null?null:financialUnit;
			return this;
		}
		@Override
		@RosettaAttribute("currency")
		public UnitType.UnitTypeBuilder setCurrency(FieldWithMetaString currency) {
			this.currency = currency==null?null:currency.toBuilder();
			return this;
		}
		@Override
		public UnitType.UnitTypeBuilder setCurrencyValue(String currency) {
			this.getOrCreateCurrency().setValue(currency);
			return this;
		}
		
		@Override
		public UnitType build() {
			return new UnitType.UnitTypeImpl(this);
		}
		
		@Override
		public UnitType.UnitTypeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public UnitType.UnitTypeBuilder prune() {
			if (currency!=null && !currency.prune().hasData()) currency = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCapacityUnit()!=null) return true;
			if (getWeatherUnit()!=null) return true;
			if (getFinancialUnit()!=null) return true;
			if (getCurrency()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public UnitType.UnitTypeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			UnitType.UnitTypeBuilder o = (UnitType.UnitTypeBuilder) other;
			
			merger.mergeRosetta(getCurrency(), o.getCurrency(), this::setCurrency);
			
			merger.mergeBasic(getCapacityUnit(), o.getCapacityUnit(), this::setCapacityUnit);
			merger.mergeBasic(getWeatherUnit(), o.getWeatherUnit(), this::setWeatherUnit);
			merger.mergeBasic(getFinancialUnit(), o.getFinancialUnit(), this::setFinancialUnit);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			UnitType _that = getType().cast(o);
		
			if (!Objects.equals(capacityUnit, _that.getCapacityUnit())) return false;
			if (!Objects.equals(weatherUnit, _that.getWeatherUnit())) return false;
			if (!Objects.equals(financialUnit, _that.getFinancialUnit())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (capacityUnit != null ? capacityUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (weatherUnit != null ? weatherUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (financialUnit != null ? financialUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "UnitTypeBuilder {" +
				"capacityUnit=" + this.capacityUnit + ", " +
				"weatherUnit=" + this.weatherUnit + ", " +
				"financialUnit=" + this.financialUnit + ", " +
				"currency=" + this.currency +
			'}';
		}
	}
}
