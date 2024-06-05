package cdm.product.asset;

import cdm.observable.asset.Money;
import cdm.product.asset.FixedAmountCalculationDetails;
import cdm.product.asset.FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder;
import cdm.product.asset.FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilderImpl;
import cdm.product.asset.FixedAmountCalculationDetails.FixedAmountCalculationDetailsImpl;
import cdm.product.asset.meta.FixedAmountCalculationDetailsMeta;
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
@RosettaDataType(value="FixedAmountCalculationDetails", builder=FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilderImpl.class, version="${project.version}")
public interface FixedAmountCalculationDetails extends RosettaModelObject {

	FixedAmountCalculationDetailsMeta metaData = new FixedAmountCalculationDetailsMeta();

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
	 * The value of the fixed rate that was used.
	 */
	BigDecimal getFixedRate();
	/**
	 * The fraction of a year that this calculation represents, according to the day count fraction method.
	 */
	BigDecimal getYearFraction();
	/**
	 * The amount of the cash flow that was computed, including any spreads and other processing.
	 */
	BigDecimal getCalculatedAmount();

	/*********************** Build Methods  ***********************/
	FixedAmountCalculationDetails build();
	
	FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder toBuilder();
	
	static FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder builder() {
		return new FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FixedAmountCalculationDetails> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FixedAmountCalculationDetails> getType() {
		return FixedAmountCalculationDetails.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("calculationPeriod"), processor, CalculationPeriodBase.class, getCalculationPeriod());
		processRosetta(path.newSubPath("calculationPeriodNotionalAmount"), processor, Money.class, getCalculationPeriodNotionalAmount());
		processor.processBasic(path.newSubPath("fixedRate"), BigDecimal.class, getFixedRate(), this);
		processor.processBasic(path.newSubPath("yearFraction"), BigDecimal.class, getYearFraction(), this);
		processor.processBasic(path.newSubPath("calculatedAmount"), BigDecimal.class, getCalculatedAmount(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface FixedAmountCalculationDetailsBuilder extends FixedAmountCalculationDetails, RosettaModelObjectBuilder {
		CalculationPeriodBase.CalculationPeriodBaseBuilder getOrCreateCalculationPeriod();
		CalculationPeriodBase.CalculationPeriodBaseBuilder getCalculationPeriod();
		Money.MoneyBuilder getOrCreateCalculationPeriodNotionalAmount();
		Money.MoneyBuilder getCalculationPeriodNotionalAmount();
		FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder setCalculationPeriod(CalculationPeriodBase calculationPeriod);
		FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder setCalculationPeriodNotionalAmount(Money calculationPeriodNotionalAmount);
		FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder setFixedRate(BigDecimal fixedRate);
		FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder setYearFraction(BigDecimal yearFraction);
		FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder setCalculatedAmount(BigDecimal calculatedAmount);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("calculationPeriod"), processor, CalculationPeriodBase.CalculationPeriodBaseBuilder.class, getCalculationPeriod());
			processRosetta(path.newSubPath("calculationPeriodNotionalAmount"), processor, Money.MoneyBuilder.class, getCalculationPeriodNotionalAmount());
			processor.processBasic(path.newSubPath("fixedRate"), BigDecimal.class, getFixedRate(), this);
			processor.processBasic(path.newSubPath("yearFraction"), BigDecimal.class, getYearFraction(), this);
			processor.processBasic(path.newSubPath("calculatedAmount"), BigDecimal.class, getCalculatedAmount(), this);
		}
		

		FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder prune();
	}

	/*********************** Immutable Implementation of FixedAmountCalculationDetails  ***********************/
	class FixedAmountCalculationDetailsImpl implements FixedAmountCalculationDetails {
		private final CalculationPeriodBase calculationPeriod;
		private final Money calculationPeriodNotionalAmount;
		private final BigDecimal fixedRate;
		private final BigDecimal yearFraction;
		private final BigDecimal calculatedAmount;
		
		protected FixedAmountCalculationDetailsImpl(FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder builder) {
			this.calculationPeriod = ofNullable(builder.getCalculationPeriod()).map(f->f.build()).orElse(null);
			this.calculationPeriodNotionalAmount = ofNullable(builder.getCalculationPeriodNotionalAmount()).map(f->f.build()).orElse(null);
			this.fixedRate = builder.getFixedRate();
			this.yearFraction = builder.getYearFraction();
			this.calculatedAmount = builder.getCalculatedAmount();
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
		@RosettaAttribute("fixedRate")
		public BigDecimal getFixedRate() {
			return fixedRate;
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
		public FixedAmountCalculationDetails build() {
			return this;
		}
		
		@Override
		public FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder toBuilder() {
			FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder builder) {
			ofNullable(getCalculationPeriod()).ifPresent(builder::setCalculationPeriod);
			ofNullable(getCalculationPeriodNotionalAmount()).ifPresent(builder::setCalculationPeriodNotionalAmount);
			ofNullable(getFixedRate()).ifPresent(builder::setFixedRate);
			ofNullable(getYearFraction()).ifPresent(builder::setYearFraction);
			ofNullable(getCalculatedAmount()).ifPresent(builder::setCalculatedAmount);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FixedAmountCalculationDetails _that = getType().cast(o);
		
			if (!Objects.equals(calculationPeriod, _that.getCalculationPeriod())) return false;
			if (!Objects.equals(calculationPeriodNotionalAmount, _that.getCalculationPeriodNotionalAmount())) return false;
			if (!Objects.equals(fixedRate, _that.getFixedRate())) return false;
			if (!Objects.equals(yearFraction, _that.getYearFraction())) return false;
			if (!Objects.equals(calculatedAmount, _that.getCalculatedAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationPeriod != null ? calculationPeriod.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodNotionalAmount != null ? calculationPeriodNotionalAmount.hashCode() : 0);
			_result = 31 * _result + (fixedRate != null ? fixedRate.hashCode() : 0);
			_result = 31 * _result + (yearFraction != null ? yearFraction.hashCode() : 0);
			_result = 31 * _result + (calculatedAmount != null ? calculatedAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FixedAmountCalculationDetails {" +
				"calculationPeriod=" + this.calculationPeriod + ", " +
				"calculationPeriodNotionalAmount=" + this.calculationPeriodNotionalAmount + ", " +
				"fixedRate=" + this.fixedRate + ", " +
				"yearFraction=" + this.yearFraction + ", " +
				"calculatedAmount=" + this.calculatedAmount +
			'}';
		}
	}

	/*********************** Builder Implementation of FixedAmountCalculationDetails  ***********************/
	class FixedAmountCalculationDetailsBuilderImpl implements FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder {
	
		protected CalculationPeriodBase.CalculationPeriodBaseBuilder calculationPeriod;
		protected Money.MoneyBuilder calculationPeriodNotionalAmount;
		protected BigDecimal fixedRate;
		protected BigDecimal yearFraction;
		protected BigDecimal calculatedAmount;
	
		public FixedAmountCalculationDetailsBuilderImpl() {
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
		@RosettaAttribute("fixedRate")
		public BigDecimal getFixedRate() {
			return fixedRate;
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
		@RosettaAttribute("calculationPeriod")
		public FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder setCalculationPeriod(CalculationPeriodBase calculationPeriod) {
			this.calculationPeriod = calculationPeriod==null?null:calculationPeriod.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("calculationPeriodNotionalAmount")
		public FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder setCalculationPeriodNotionalAmount(Money calculationPeriodNotionalAmount) {
			this.calculationPeriodNotionalAmount = calculationPeriodNotionalAmount==null?null:calculationPeriodNotionalAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fixedRate")
		public FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder setFixedRate(BigDecimal fixedRate) {
			this.fixedRate = fixedRate==null?null:fixedRate;
			return this;
		}
		@Override
		@RosettaAttribute("yearFraction")
		public FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder setYearFraction(BigDecimal yearFraction) {
			this.yearFraction = yearFraction==null?null:yearFraction;
			return this;
		}
		@Override
		@RosettaAttribute("calculatedAmount")
		public FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder setCalculatedAmount(BigDecimal calculatedAmount) {
			this.calculatedAmount = calculatedAmount==null?null:calculatedAmount;
			return this;
		}
		
		@Override
		public FixedAmountCalculationDetails build() {
			return new FixedAmountCalculationDetails.FixedAmountCalculationDetailsImpl(this);
		}
		
		@Override
		public FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder prune() {
			if (calculationPeriod!=null && !calculationPeriod.prune().hasData()) calculationPeriod = null;
			if (calculationPeriodNotionalAmount!=null && !calculationPeriodNotionalAmount.prune().hasData()) calculationPeriodNotionalAmount = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCalculationPeriod()!=null && getCalculationPeriod().hasData()) return true;
			if (getCalculationPeriodNotionalAmount()!=null && getCalculationPeriodNotionalAmount().hasData()) return true;
			if (getFixedRate()!=null) return true;
			if (getYearFraction()!=null) return true;
			if (getCalculatedAmount()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder o = (FixedAmountCalculationDetails.FixedAmountCalculationDetailsBuilder) other;
			
			merger.mergeRosetta(getCalculationPeriod(), o.getCalculationPeriod(), this::setCalculationPeriod);
			merger.mergeRosetta(getCalculationPeriodNotionalAmount(), o.getCalculationPeriodNotionalAmount(), this::setCalculationPeriodNotionalAmount);
			
			merger.mergeBasic(getFixedRate(), o.getFixedRate(), this::setFixedRate);
			merger.mergeBasic(getYearFraction(), o.getYearFraction(), this::setYearFraction);
			merger.mergeBasic(getCalculatedAmount(), o.getCalculatedAmount(), this::setCalculatedAmount);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FixedAmountCalculationDetails _that = getType().cast(o);
		
			if (!Objects.equals(calculationPeriod, _that.getCalculationPeriod())) return false;
			if (!Objects.equals(calculationPeriodNotionalAmount, _that.getCalculationPeriodNotionalAmount())) return false;
			if (!Objects.equals(fixedRate, _that.getFixedRate())) return false;
			if (!Objects.equals(yearFraction, _that.getYearFraction())) return false;
			if (!Objects.equals(calculatedAmount, _that.getCalculatedAmount())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationPeriod != null ? calculationPeriod.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodNotionalAmount != null ? calculationPeriodNotionalAmount.hashCode() : 0);
			_result = 31 * _result + (fixedRate != null ? fixedRate.hashCode() : 0);
			_result = 31 * _result + (yearFraction != null ? yearFraction.hashCode() : 0);
			_result = 31 * _result + (calculatedAmount != null ? calculatedAmount.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FixedAmountCalculationDetailsBuilder {" +
				"calculationPeriod=" + this.calculationPeriod + ", " +
				"calculationPeriodNotionalAmount=" + this.calculationPeriodNotionalAmount + ", " +
				"fixedRate=" + this.fixedRate + ", " +
				"yearFraction=" + this.yearFraction + ", " +
				"calculatedAmount=" + this.calculatedAmount +
			'}';
		}
	}
}
