package cdm.product.asset.floatingrate;

import cdm.observable.asset.Money;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilderImpl;
import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsImpl;
import cdm.product.asset.floatingrate.FloatingRateProcessingDetails;
import cdm.product.asset.floatingrate.FloatingRateSettingDetails;
import cdm.product.asset.floatingrate.meta.FloatingAmountCalculationDetailsMeta;
import cdm.product.common.schedule.CalculationPeriodBase;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Type for reporting the detailed results of calculating a cash flow for a calculation period.  This is enhanced relative to the FpML-based cashflows structure to allow more information to be returned about daily compounded rates.
 * @version ${project.version}
 */
@RosettaDataType(value="FloatingAmountCalculationDetails", builder=FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilderImpl.class, version="${project.version}")
public interface FloatingAmountCalculationDetails extends RosettaModelObject {

	FloatingAmountCalculationDetailsMeta metaData = new FloatingAmountCalculationDetailsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The calculation period for which the floating calculation was performed.
	 */
	CalculationPeriodBase getCalculationPeriod();
	/**
	 * The notional in effect during the calculation period.
	 */
	Money getCalculationPeriodNotionalAmount();
	/**
	 * The details of the floating rate setting.  (If it is a calculated rate, details of that calculation will be inside that.
	 */
	FloatingRateSettingDetails getFloatingRate();
	/**
	 * Details fo the floating rate treatment after the rate is observed or calculated.  This will include details of things like multipliers, spreads, caps and floors, and the raw and treated rates.
	 */
	FloatingRateProcessingDetails getProcessingDetails();
	/**
	 * The rate that was actually applied, after all calculations and treatments.
	 */
	BigDecimal getAppliedRate();
	/**
	 * The fraction of a year that this calculation represents, according to the day count fraction method.
	 */
	BigDecimal getYearFraction();
	/**
	 * The amount of the cash flow that was computed, including any spreads and other processing.
	 */
	BigDecimal getCalculatedAmount();
	/**
	 * The amount of the cash flow excluding any spread, for subsequent processing.
	 */
	BigDecimal getSpreadExclusiveCalculatedAMount();

	/*********************** Build Methods  ***********************/
	FloatingAmountCalculationDetails build();
	
	FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder toBuilder();
	
	static FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder builder() {
		return new FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FloatingAmountCalculationDetails> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FloatingAmountCalculationDetails> getType() {
		return FloatingAmountCalculationDetails.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("calculationPeriod"), processor, CalculationPeriodBase.class, getCalculationPeriod());
		processRosetta(path.newSubPath("calculationPeriodNotionalAmount"), processor, Money.class, getCalculationPeriodNotionalAmount());
		processRosetta(path.newSubPath("floatingRate"), processor, FloatingRateSettingDetails.class, getFloatingRate());
		processRosetta(path.newSubPath("processingDetails"), processor, FloatingRateProcessingDetails.class, getProcessingDetails());
		processor.processBasic(path.newSubPath("appliedRate"), BigDecimal.class, getAppliedRate(), this);
		processor.processBasic(path.newSubPath("yearFraction"), BigDecimal.class, getYearFraction(), this);
		processor.processBasic(path.newSubPath("calculatedAmount"), BigDecimal.class, getCalculatedAmount(), this);
		processor.processBasic(path.newSubPath("spreadExclusiveCalculatedAMount"), BigDecimal.class, getSpreadExclusiveCalculatedAMount(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface FloatingAmountCalculationDetailsBuilder extends FloatingAmountCalculationDetails, RosettaModelObjectBuilder {
		CalculationPeriodBase.CalculationPeriodBaseBuilder getOrCreateCalculationPeriod();
		CalculationPeriodBase.CalculationPeriodBaseBuilder getCalculationPeriod();
		Money.MoneyBuilder getOrCreateCalculationPeriodNotionalAmount();
		Money.MoneyBuilder getCalculationPeriodNotionalAmount();
		FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder getOrCreateFloatingRate();
		FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder getFloatingRate();
		FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder getOrCreateProcessingDetails();
		FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder getProcessingDetails();
		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setCalculationPeriod(CalculationPeriodBase calculationPeriod);
		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setCalculationPeriodNotionalAmount(Money calculationPeriodNotionalAmount);
		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setFloatingRate(FloatingRateSettingDetails floatingRate);
		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setProcessingDetails(FloatingRateProcessingDetails processingDetails);
		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setAppliedRate(BigDecimal appliedRate);
		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setYearFraction(BigDecimal yearFraction);
		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setCalculatedAmount(BigDecimal calculatedAmount);
		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setSpreadExclusiveCalculatedAMount(BigDecimal spreadExclusiveCalculatedAMount);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("calculationPeriod"), processor, CalculationPeriodBase.CalculationPeriodBaseBuilder.class, getCalculationPeriod());
			processRosetta(path.newSubPath("calculationPeriodNotionalAmount"), processor, Money.MoneyBuilder.class, getCalculationPeriodNotionalAmount());
			processRosetta(path.newSubPath("floatingRate"), processor, FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder.class, getFloatingRate());
			processRosetta(path.newSubPath("processingDetails"), processor, FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder.class, getProcessingDetails());
			processor.processBasic(path.newSubPath("appliedRate"), BigDecimal.class, getAppliedRate(), this);
			processor.processBasic(path.newSubPath("yearFraction"), BigDecimal.class, getYearFraction(), this);
			processor.processBasic(path.newSubPath("calculatedAmount"), BigDecimal.class, getCalculatedAmount(), this);
			processor.processBasic(path.newSubPath("spreadExclusiveCalculatedAMount"), BigDecimal.class, getSpreadExclusiveCalculatedAMount(), this);
		}
		

		FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder prune();
	}

	/*********************** Immutable Implementation of FloatingAmountCalculationDetails  ***********************/
	class FloatingAmountCalculationDetailsImpl implements FloatingAmountCalculationDetails {
		private final CalculationPeriodBase calculationPeriod;
		private final Money calculationPeriodNotionalAmount;
		private final FloatingRateSettingDetails floatingRate;
		private final FloatingRateProcessingDetails processingDetails;
		private final BigDecimal appliedRate;
		private final BigDecimal yearFraction;
		private final BigDecimal calculatedAmount;
		private final BigDecimal spreadExclusiveCalculatedAMount;
		
		protected FloatingAmountCalculationDetailsImpl(FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder builder) {
			this.calculationPeriod = ofNullable(builder.getCalculationPeriod()).map(f->f.build()).orElse(null);
			this.calculationPeriodNotionalAmount = ofNullable(builder.getCalculationPeriodNotionalAmount()).map(f->f.build()).orElse(null);
			this.floatingRate = ofNullable(builder.getFloatingRate()).map(f->f.build()).orElse(null);
			this.processingDetails = ofNullable(builder.getProcessingDetails()).map(f->f.build()).orElse(null);
			this.appliedRate = builder.getAppliedRate();
			this.yearFraction = builder.getYearFraction();
			this.calculatedAmount = builder.getCalculatedAmount();
			this.spreadExclusiveCalculatedAMount = builder.getSpreadExclusiveCalculatedAMount();
		}
		
		@Override
		@RosettaAttribute("calculationPeriod")
		public CalculationPeriodBase getCalculationPeriod() {
			return calculationPeriod;
		}
		
		@Override
		@RosettaAttribute("calculationPeriodNotionalAmount")
		public Money getCalculationPeriodNotionalAmount() {
			return calculationPeriodNotionalAmount;
		}
		
		@Override
		@RosettaAttribute("floatingRate")
		public FloatingRateSettingDetails getFloatingRate() {
			return floatingRate;
		}
		
		@Override
		@RosettaAttribute("processingDetails")
		public FloatingRateProcessingDetails getProcessingDetails() {
			return processingDetails;
		}
		
		@Override
		@RosettaAttribute("appliedRate")
		public BigDecimal getAppliedRate() {
			return appliedRate;
		}
		
		@Override
		@RosettaAttribute("yearFraction")
		public BigDecimal getYearFraction() {
			return yearFraction;
		}
		
		@Override
		@RosettaAttribute("calculatedAmount")
		public BigDecimal getCalculatedAmount() {
			return calculatedAmount;
		}
		
		@Override
		@RosettaAttribute("spreadExclusiveCalculatedAMount")
		public BigDecimal getSpreadExclusiveCalculatedAMount() {
			return spreadExclusiveCalculatedAMount;
		}
		
		@Override
		public FloatingAmountCalculationDetails build() {
			return this;
		}
		
		@Override
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder toBuilder() {
			FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder builder) {
			ofNullable(getCalculationPeriod()).ifPresent(builder::setCalculationPeriod);
			ofNullable(getCalculationPeriodNotionalAmount()).ifPresent(builder::setCalculationPeriodNotionalAmount);
			ofNullable(getFloatingRate()).ifPresent(builder::setFloatingRate);
			ofNullable(getProcessingDetails()).ifPresent(builder::setProcessingDetails);
			ofNullable(getAppliedRate()).ifPresent(builder::setAppliedRate);
			ofNullable(getYearFraction()).ifPresent(builder::setYearFraction);
			ofNullable(getCalculatedAmount()).ifPresent(builder::setCalculatedAmount);
			ofNullable(getSpreadExclusiveCalculatedAMount()).ifPresent(builder::setSpreadExclusiveCalculatedAMount);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingAmountCalculationDetails _that = getType().cast(o);
		
			if (!Objects.equals(calculationPeriod, _that.getCalculationPeriod())) return false;
			if (!Objects.equals(calculationPeriodNotionalAmount, _that.getCalculationPeriodNotionalAmount())) return false;
			if (!Objects.equals(floatingRate, _that.getFloatingRate())) return false;
			if (!Objects.equals(processingDetails, _that.getProcessingDetails())) return false;
			if (!Objects.equals(appliedRate, _that.getAppliedRate())) return false;
			if (!Objects.equals(yearFraction, _that.getYearFraction())) return false;
			if (!Objects.equals(calculatedAmount, _that.getCalculatedAmount())) return false;
			if (!Objects.equals(spreadExclusiveCalculatedAMount, _that.getSpreadExclusiveCalculatedAMount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationPeriod != null ? calculationPeriod.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodNotionalAmount != null ? calculationPeriodNotionalAmount.hashCode() : 0);
			_result = 31 * _result + (floatingRate != null ? floatingRate.hashCode() : 0);
			_result = 31 * _result + (processingDetails != null ? processingDetails.hashCode() : 0);
			_result = 31 * _result + (appliedRate != null ? appliedRate.hashCode() : 0);
			_result = 31 * _result + (yearFraction != null ? yearFraction.hashCode() : 0);
			_result = 31 * _result + (calculatedAmount != null ? calculatedAmount.hashCode() : 0);
			_result = 31 * _result + (spreadExclusiveCalculatedAMount != null ? spreadExclusiveCalculatedAMount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingAmountCalculationDetails {" +
				"calculationPeriod=" + this.calculationPeriod + ", " +
				"calculationPeriodNotionalAmount=" + this.calculationPeriodNotionalAmount + ", " +
				"floatingRate=" + this.floatingRate + ", " +
				"processingDetails=" + this.processingDetails + ", " +
				"appliedRate=" + this.appliedRate + ", " +
				"yearFraction=" + this.yearFraction + ", " +
				"calculatedAmount=" + this.calculatedAmount + ", " +
				"spreadExclusiveCalculatedAMount=" + this.spreadExclusiveCalculatedAMount +
			'}';
		}
	}

	/*********************** Builder Implementation of FloatingAmountCalculationDetails  ***********************/
	class FloatingAmountCalculationDetailsBuilderImpl implements FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder {
	
		protected CalculationPeriodBase.CalculationPeriodBaseBuilder calculationPeriod;
		protected Money.MoneyBuilder calculationPeriodNotionalAmount;
		protected FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder floatingRate;
		protected FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder processingDetails;
		protected BigDecimal appliedRate;
		protected BigDecimal yearFraction;
		protected BigDecimal calculatedAmount;
		protected BigDecimal spreadExclusiveCalculatedAMount;
	
		public FloatingAmountCalculationDetailsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("calculationPeriod")
		public CalculationPeriodBase.CalculationPeriodBaseBuilder getCalculationPeriod() {
			return calculationPeriod;
		}
		
		@Override
		public CalculationPeriodBase.CalculationPeriodBaseBuilder getOrCreateCalculationPeriod() {
			CalculationPeriodBase.CalculationPeriodBaseBuilder result;
			if (calculationPeriod!=null) {
				result = calculationPeriod;
			}
			else {
				result = calculationPeriod = CalculationPeriodBase.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("calculationPeriodNotionalAmount")
		public Money.MoneyBuilder getCalculationPeriodNotionalAmount() {
			return calculationPeriodNotionalAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateCalculationPeriodNotionalAmount() {
			Money.MoneyBuilder result;
			if (calculationPeriodNotionalAmount!=null) {
				result = calculationPeriodNotionalAmount;
			}
			else {
				result = calculationPeriodNotionalAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("floatingRate")
		public FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder getFloatingRate() {
			return floatingRate;
		}
		
		@Override
		public FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder getOrCreateFloatingRate() {
			FloatingRateSettingDetails.FloatingRateSettingDetailsBuilder result;
			if (floatingRate!=null) {
				result = floatingRate;
			}
			else {
				result = floatingRate = FloatingRateSettingDetails.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("processingDetails")
		public FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder getProcessingDetails() {
			return processingDetails;
		}
		
		@Override
		public FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder getOrCreateProcessingDetails() {
			FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder result;
			if (processingDetails!=null) {
				result = processingDetails;
			}
			else {
				result = processingDetails = FloatingRateProcessingDetails.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("appliedRate")
		public BigDecimal getAppliedRate() {
			return appliedRate;
		}
		
		@Override
		@RosettaAttribute("yearFraction")
		public BigDecimal getYearFraction() {
			return yearFraction;
		}
		
		@Override
		@RosettaAttribute("calculatedAmount")
		public BigDecimal getCalculatedAmount() {
			return calculatedAmount;
		}
		
		@Override
		@RosettaAttribute("spreadExclusiveCalculatedAMount")
		public BigDecimal getSpreadExclusiveCalculatedAMount() {
			return spreadExclusiveCalculatedAMount;
		}
		
	
		@Override
		@RosettaAttribute("calculationPeriod")
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setCalculationPeriod(CalculationPeriodBase calculationPeriod) {
			this.calculationPeriod = calculationPeriod==null?null:calculationPeriod.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("calculationPeriodNotionalAmount")
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setCalculationPeriodNotionalAmount(Money calculationPeriodNotionalAmount) {
			this.calculationPeriodNotionalAmount = calculationPeriodNotionalAmount==null?null:calculationPeriodNotionalAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("floatingRate")
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setFloatingRate(FloatingRateSettingDetails floatingRate) {
			this.floatingRate = floatingRate==null?null:floatingRate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("processingDetails")
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setProcessingDetails(FloatingRateProcessingDetails processingDetails) {
			this.processingDetails = processingDetails==null?null:processingDetails.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("appliedRate")
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setAppliedRate(BigDecimal appliedRate) {
			this.appliedRate = appliedRate==null?null:appliedRate;
			return this;
		}
		@Override
		@RosettaAttribute("yearFraction")
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setYearFraction(BigDecimal yearFraction) {
			this.yearFraction = yearFraction==null?null:yearFraction;
			return this;
		}
		@Override
		@RosettaAttribute("calculatedAmount")
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setCalculatedAmount(BigDecimal calculatedAmount) {
			this.calculatedAmount = calculatedAmount==null?null:calculatedAmount;
			return this;
		}
		@Override
		@RosettaAttribute("spreadExclusiveCalculatedAMount")
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder setSpreadExclusiveCalculatedAMount(BigDecimal spreadExclusiveCalculatedAMount) {
			this.spreadExclusiveCalculatedAMount = spreadExclusiveCalculatedAMount==null?null:spreadExclusiveCalculatedAMount;
			return this;
		}
		
		@Override
		public FloatingAmountCalculationDetails build() {
			return new FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsImpl(this);
		}
		
		@Override
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder prune() {
			if (calculationPeriod!=null && !calculationPeriod.prune().hasData()) calculationPeriod = null;
			if (calculationPeriodNotionalAmount!=null && !calculationPeriodNotionalAmount.prune().hasData()) calculationPeriodNotionalAmount = null;
			if (floatingRate!=null && !floatingRate.prune().hasData()) floatingRate = null;
			if (processingDetails!=null && !processingDetails.prune().hasData()) processingDetails = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCalculationPeriod()!=null && getCalculationPeriod().hasData()) return true;
			if (getCalculationPeriodNotionalAmount()!=null && getCalculationPeriodNotionalAmount().hasData()) return true;
			if (getFloatingRate()!=null && getFloatingRate().hasData()) return true;
			if (getProcessingDetails()!=null && getProcessingDetails().hasData()) return true;
			if (getAppliedRate()!=null) return true;
			if (getYearFraction()!=null) return true;
			if (getCalculatedAmount()!=null) return true;
			if (getSpreadExclusiveCalculatedAMount()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder o = (FloatingAmountCalculationDetails.FloatingAmountCalculationDetailsBuilder) other;
			
			merger.mergeRosetta(getCalculationPeriod(), o.getCalculationPeriod(), this::setCalculationPeriod);
			merger.mergeRosetta(getCalculationPeriodNotionalAmount(), o.getCalculationPeriodNotionalAmount(), this::setCalculationPeriodNotionalAmount);
			merger.mergeRosetta(getFloatingRate(), o.getFloatingRate(), this::setFloatingRate);
			merger.mergeRosetta(getProcessingDetails(), o.getProcessingDetails(), this::setProcessingDetails);
			
			merger.mergeBasic(getAppliedRate(), o.getAppliedRate(), this::setAppliedRate);
			merger.mergeBasic(getYearFraction(), o.getYearFraction(), this::setYearFraction);
			merger.mergeBasic(getCalculatedAmount(), o.getCalculatedAmount(), this::setCalculatedAmount);
			merger.mergeBasic(getSpreadExclusiveCalculatedAMount(), o.getSpreadExclusiveCalculatedAMount(), this::setSpreadExclusiveCalculatedAMount);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingAmountCalculationDetails _that = getType().cast(o);
		
			if (!Objects.equals(calculationPeriod, _that.getCalculationPeriod())) return false;
			if (!Objects.equals(calculationPeriodNotionalAmount, _that.getCalculationPeriodNotionalAmount())) return false;
			if (!Objects.equals(floatingRate, _that.getFloatingRate())) return false;
			if (!Objects.equals(processingDetails, _that.getProcessingDetails())) return false;
			if (!Objects.equals(appliedRate, _that.getAppliedRate())) return false;
			if (!Objects.equals(yearFraction, _that.getYearFraction())) return false;
			if (!Objects.equals(calculatedAmount, _that.getCalculatedAmount())) return false;
			if (!Objects.equals(spreadExclusiveCalculatedAMount, _that.getSpreadExclusiveCalculatedAMount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationPeriod != null ? calculationPeriod.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodNotionalAmount != null ? calculationPeriodNotionalAmount.hashCode() : 0);
			_result = 31 * _result + (floatingRate != null ? floatingRate.hashCode() : 0);
			_result = 31 * _result + (processingDetails != null ? processingDetails.hashCode() : 0);
			_result = 31 * _result + (appliedRate != null ? appliedRate.hashCode() : 0);
			_result = 31 * _result + (yearFraction != null ? yearFraction.hashCode() : 0);
			_result = 31 * _result + (calculatedAmount != null ? calculatedAmount.hashCode() : 0);
			_result = 31 * _result + (spreadExclusiveCalculatedAMount != null ? spreadExclusiveCalculatedAMount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingAmountCalculationDetailsBuilder {" +
				"calculationPeriod=" + this.calculationPeriod + ", " +
				"calculationPeriodNotionalAmount=" + this.calculationPeriodNotionalAmount + ", " +
				"floatingRate=" + this.floatingRate + ", " +
				"processingDetails=" + this.processingDetails + ", " +
				"appliedRate=" + this.appliedRate + ", " +
				"yearFraction=" + this.yearFraction + ", " +
				"calculatedAmount=" + this.calculatedAmount + ", " +
				"spreadExclusiveCalculatedAMount=" + this.spreadExclusiveCalculatedAMount +
			'}';
		}
	}
}
