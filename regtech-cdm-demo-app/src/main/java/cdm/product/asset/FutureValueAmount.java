package cdm.product.asset;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder;
import cdm.product.asset.FutureValueAmount;
import cdm.product.asset.FutureValueAmount.FutureValueAmountBuilder;
import cdm.product.asset.FutureValueAmount.FutureValueAmountBuilderImpl;
import cdm.product.asset.FutureValueAmount.FutureValueAmountImpl;
import cdm.product.asset.meta.FutureValueAmountMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class defining a currency and a future value date.
 * @version ${project.version}
 */
@RosettaDataType(value="FutureValueAmount", builder=FutureValueAmount.FutureValueAmountBuilderImpl.class, version="${project.version}")
public interface FutureValueAmount extends RosettaModelObject {

	FutureValueAmountMeta metaData = new FutureValueAmountMeta();

	/*********************** Getter Methods  ***********************/
	ReferenceWithMetaNonNegativeQuantitySchedule getQuantity();
	/**
	 * The currency in which the an amount is denominated. The list of valid currencies is not presently positioned as an enumeration as part of the CDM because that scope is limited to the values specified by ISDA and FpML. As a result, implementers have to make reference to the relevant standard, such as the ISO 4217 standard for currency codes.
	 */
	FieldWithMetaString getCurrency();
	/**
	 * The number of days from the adjusted calculation period start date to the adjusted value date, calculated in accordance with the applicable day count fraction.
	 */
	Integer getCalculationPeriodNumberOfDays();
	/**
	 * Adjusted value date of the future value amount.
	 */
	Date getValueDate();

	/*********************** Build Methods  ***********************/
	FutureValueAmount build();
	
	FutureValueAmount.FutureValueAmountBuilder toBuilder();
	
	static FutureValueAmount.FutureValueAmountBuilder builder() {
		return new FutureValueAmount.FutureValueAmountBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FutureValueAmount> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FutureValueAmount> getType() {
		return FutureValueAmount.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("quantity"), processor, ReferenceWithMetaNonNegativeQuantitySchedule.class, getQuantity());
		processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.class, getCurrency());
		processor.processBasic(path.newSubPath("calculationPeriodNumberOfDays"), Integer.class, getCalculationPeriodNumberOfDays(), this);
		processor.processBasic(path.newSubPath("valueDate"), Date.class, getValueDate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface FutureValueAmountBuilder extends FutureValueAmount, RosettaModelObjectBuilder {
		ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getOrCreateQuantity();
		ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getQuantity();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateCurrency();
		FieldWithMetaString.FieldWithMetaStringBuilder getCurrency();
		FutureValueAmount.FutureValueAmountBuilder setQuantity(ReferenceWithMetaNonNegativeQuantitySchedule quantity0);
		FutureValueAmount.FutureValueAmountBuilder setQuantityValue(NonNegativeQuantitySchedule quantity1);
		FutureValueAmount.FutureValueAmountBuilder setCurrency(FieldWithMetaString currency0);
		FutureValueAmount.FutureValueAmountBuilder setCurrencyValue(String currency1);
		FutureValueAmount.FutureValueAmountBuilder setCalculationPeriodNumberOfDays(Integer calculationPeriodNumberOfDays);
		FutureValueAmount.FutureValueAmountBuilder setValueDate(Date valueDate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("quantity"), processor, ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder.class, getQuantity());
			processRosetta(path.newSubPath("currency"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getCurrency());
			processor.processBasic(path.newSubPath("calculationPeriodNumberOfDays"), Integer.class, getCalculationPeriodNumberOfDays(), this);
			processor.processBasic(path.newSubPath("valueDate"), Date.class, getValueDate(), this);
		}
		

		FutureValueAmount.FutureValueAmountBuilder prune();
	}

	/*********************** Immutable Implementation of FutureValueAmount  ***********************/
	class FutureValueAmountImpl implements FutureValueAmount {
		private final ReferenceWithMetaNonNegativeQuantitySchedule quantity;
		private final FieldWithMetaString currency;
		private final Integer calculationPeriodNumberOfDays;
		private final Date valueDate;
		
		protected FutureValueAmountImpl(FutureValueAmount.FutureValueAmountBuilder builder) {
			this.quantity = ofNullable(builder.getQuantity()).map(f->f.build()).orElse(null);
			this.currency = ofNullable(builder.getCurrency()).map(f->f.build()).orElse(null);
			this.calculationPeriodNumberOfDays = builder.getCalculationPeriodNumberOfDays();
			this.valueDate = builder.getValueDate();
		}
		
		@Override
		@RosettaAttribute("quantity")
		public ReferenceWithMetaNonNegativeQuantitySchedule getQuantity() {
			return quantity;
		}
		
		@Override
		@RosettaAttribute("currency")
		public FieldWithMetaString getCurrency() {
			return currency;
		}
		
		@Override
		@RosettaAttribute("calculationPeriodNumberOfDays")
		public Integer getCalculationPeriodNumberOfDays() {
			return calculationPeriodNumberOfDays;
		}
		
		@Override
		@RosettaAttribute("valueDate")
		public Date getValueDate() {
			return valueDate;
		}
		
		@Override
		public FutureValueAmount build() {
			return this;
		}
		
		@Override
		public FutureValueAmount.FutureValueAmountBuilder toBuilder() {
			FutureValueAmount.FutureValueAmountBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FutureValueAmount.FutureValueAmountBuilder builder) {
			ofNullable(getQuantity()).ifPresent(builder::setQuantity);
			ofNullable(getCurrency()).ifPresent(builder::setCurrency);
			ofNullable(getCalculationPeriodNumberOfDays()).ifPresent(builder::setCalculationPeriodNumberOfDays);
			ofNullable(getValueDate()).ifPresent(builder::setValueDate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FutureValueAmount _that = getType().cast(o);
		
			if (!Objects.equals(quantity, _that.getQuantity())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			if (!Objects.equals(calculationPeriodNumberOfDays, _that.getCalculationPeriodNumberOfDays())) return false;
			if (!Objects.equals(valueDate, _that.getValueDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodNumberOfDays != null ? calculationPeriodNumberOfDays.hashCode() : 0);
			_result = 31 * _result + (valueDate != null ? valueDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FutureValueAmount {" +
				"quantity=" + this.quantity + ", " +
				"currency=" + this.currency + ", " +
				"calculationPeriodNumberOfDays=" + this.calculationPeriodNumberOfDays + ", " +
				"valueDate=" + this.valueDate +
			'}';
		}
	}

	/*********************** Builder Implementation of FutureValueAmount  ***********************/
	class FutureValueAmountBuilderImpl implements FutureValueAmount.FutureValueAmountBuilder {
	
		protected ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder quantity;
		protected FieldWithMetaString.FieldWithMetaStringBuilder currency;
		protected Integer calculationPeriodNumberOfDays;
		protected Date valueDate;
	
		public FutureValueAmountBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("quantity")
		public ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getQuantity() {
			return quantity;
		}
		
		@Override
		public ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getOrCreateQuantity() {
			ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder result;
			if (quantity!=null) {
				result = quantity;
			}
			else {
				result = quantity = ReferenceWithMetaNonNegativeQuantitySchedule.builder();
			}
			
			return result;
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
		@RosettaAttribute("calculationPeriodNumberOfDays")
		public Integer getCalculationPeriodNumberOfDays() {
			return calculationPeriodNumberOfDays;
		}
		
		@Override
		@RosettaAttribute("valueDate")
		public Date getValueDate() {
			return valueDate;
		}
		
	
		@Override
		@RosettaAttribute("quantity")
		public FutureValueAmount.FutureValueAmountBuilder setQuantity(ReferenceWithMetaNonNegativeQuantitySchedule quantity) {
			this.quantity = quantity==null?null:quantity.toBuilder();
			return this;
		}
		@Override
		public FutureValueAmount.FutureValueAmountBuilder setQuantityValue(NonNegativeQuantitySchedule quantity) {
			this.getOrCreateQuantity().setValue(quantity);
			return this;
		}
		@Override
		@RosettaAttribute("currency")
		public FutureValueAmount.FutureValueAmountBuilder setCurrency(FieldWithMetaString currency) {
			this.currency = currency==null?null:currency.toBuilder();
			return this;
		}
		@Override
		public FutureValueAmount.FutureValueAmountBuilder setCurrencyValue(String currency) {
			this.getOrCreateCurrency().setValue(currency);
			return this;
		}
		@Override
		@RosettaAttribute("calculationPeriodNumberOfDays")
		public FutureValueAmount.FutureValueAmountBuilder setCalculationPeriodNumberOfDays(Integer calculationPeriodNumberOfDays) {
			this.calculationPeriodNumberOfDays = calculationPeriodNumberOfDays==null?null:calculationPeriodNumberOfDays;
			return this;
		}
		@Override
		@RosettaAttribute("valueDate")
		public FutureValueAmount.FutureValueAmountBuilder setValueDate(Date valueDate) {
			this.valueDate = valueDate==null?null:valueDate;
			return this;
		}
		
		@Override
		public FutureValueAmount build() {
			return new FutureValueAmount.FutureValueAmountImpl(this);
		}
		
		@Override
		public FutureValueAmount.FutureValueAmountBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FutureValueAmount.FutureValueAmountBuilder prune() {
			if (quantity!=null && !quantity.prune().hasData()) quantity = null;
			if (currency!=null && !currency.prune().hasData()) currency = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getQuantity()!=null && getQuantity().hasData()) return true;
			if (getCurrency()!=null) return true;
			if (getCalculationPeriodNumberOfDays()!=null) return true;
			if (getValueDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FutureValueAmount.FutureValueAmountBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FutureValueAmount.FutureValueAmountBuilder o = (FutureValueAmount.FutureValueAmountBuilder) other;
			
			merger.mergeRosetta(getQuantity(), o.getQuantity(), this::setQuantity);
			merger.mergeRosetta(getCurrency(), o.getCurrency(), this::setCurrency);
			
			merger.mergeBasic(getCalculationPeriodNumberOfDays(), o.getCalculationPeriodNumberOfDays(), this::setCalculationPeriodNumberOfDays);
			merger.mergeBasic(getValueDate(), o.getValueDate(), this::setValueDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FutureValueAmount _that = getType().cast(o);
		
			if (!Objects.equals(quantity, _that.getQuantity())) return false;
			if (!Objects.equals(currency, _that.getCurrency())) return false;
			if (!Objects.equals(calculationPeriodNumberOfDays, _that.getCalculationPeriodNumberOfDays())) return false;
			if (!Objects.equals(valueDate, _that.getValueDate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			_result = 31 * _result + (currency != null ? currency.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodNumberOfDays != null ? calculationPeriodNumberOfDays.hashCode() : 0);
			_result = 31 * _result + (valueDate != null ? valueDate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FutureValueAmountBuilder {" +
				"quantity=" + this.quantity + ", " +
				"currency=" + this.currency + ", " +
				"calculationPeriodNumberOfDays=" + this.calculationPeriodNumberOfDays + ", " +
				"valueDate=" + this.valueDate +
			'}';
		}
	}
}
