package cdm.product.common.schedule;

import cdm.observable.asset.Money;
import cdm.product.asset.FloatingRateDefinition;
import cdm.product.common.schedule.CalculationPeriod;
import cdm.product.common.schedule.CalculationPeriod.CalculationPeriodBuilder;
import cdm.product.common.schedule.CalculationPeriod.CalculationPeriodBuilderImpl;
import cdm.product.common.schedule.CalculationPeriod.CalculationPeriodImpl;
import cdm.product.common.schedule.CalculationPeriodBase;
import cdm.product.common.schedule.CalculationPeriodBase.CalculationPeriodBaseBuilder;
import cdm.product.common.schedule.CalculationPeriodBase.CalculationPeriodBaseBuilderImpl;
import cdm.product.common.schedule.CalculationPeriodBase.CalculationPeriodBaseImpl;
import cdm.product.common.schedule.FxLinkedNotionalAmount;
import cdm.product.common.schedule.meta.CalculationPeriodMeta;
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
import com.rosetta.model.metafields.MetaFields;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A data defining:  the parameters used in the calculation of a fixed or floating rate calculation period amount. This data forms:  part of cashflows representation of a swap stream.
 * @version ${project.version}
 */
@RosettaDataType(value="CalculationPeriod", builder=CalculationPeriod.CalculationPeriodBuilderImpl.class, version="${project.version}")
public interface CalculationPeriod extends CalculationPeriodBase {

	CalculationPeriodMeta metaData = new CalculationPeriodMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The calculation start date, unadjusted.
	 */
	Date getUnadjustedStartDate();
	/**
	 * The calculation end date, unadjusted.
	 */
	Date getUnadjustedEndDate();
	/**
	 * The number of days from the adjusted effective / start date to the adjusted termination / end date calculated in accordance with the applicable day count fraction.
	 */
	Integer getCalculationPeriodNumberOfDays();
	/**
	 * The amount that a cashflow will accrue interest on.
	 */
	BigDecimal getNotionalAmount();
	/**
	 * The amount that a cashflow will accrue interest on. This is the calculated amount of the FX linked - i.e. the other currency notional amount multiplied by the appropriate FX spot rate.
	 */
	FxLinkedNotionalAmount getFxLinkedNotionalAmount();
	/**
	 * The floating rate reset information for the calculation period.
	 */
	FloatingRateDefinition getFloatingRateDefinition();
	/**
	 * The calculation period fixed rate. A per annum rate, expressed as a decimal. A fixed rate of 5% would be represented as 0.05.
	 */
	BigDecimal getFixedRate();
	/**
	 * The year fraction value of the calculation period, result of applying the ISDA rules for day count fraction defined in the ISDA Annex.
	 */
	BigDecimal getDayCountYearFraction();
	/**
	 * The amount representing the forecast of the accrued value of the calculation period. An intermediate value used to generate the forecastPaymentAmount in the PaymentCalculationPeriod.
	 */
	Money getForecastAmount();
	/**
	 * A value representing the forecast rate used to calculate the forecast future value of the accrual period. This is a calculated rate determined based on averaging the rates in the rateObservation elements, and incorporates all of the rate treatment and averaging rules. A value of 1% should be represented as 0.01.
	 */
	BigDecimal getForecastRate();

	/*********************** Build Methods  ***********************/
	CalculationPeriod build();
	
	CalculationPeriod.CalculationPeriodBuilder toBuilder();
	
	static CalculationPeriod.CalculationPeriodBuilder builder() {
		return new CalculationPeriod.CalculationPeriodBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CalculationPeriod> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CalculationPeriod> getType() {
		return CalculationPeriod.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("adjustedStartDate"), Date.class, getAdjustedStartDate(), this);
		processor.processBasic(path.newSubPath("adjustedEndDate"), Date.class, getAdjustedEndDate(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
		processor.processBasic(path.newSubPath("unadjustedStartDate"), Date.class, getUnadjustedStartDate(), this);
		processor.processBasic(path.newSubPath("unadjustedEndDate"), Date.class, getUnadjustedEndDate(), this);
		processor.processBasic(path.newSubPath("calculationPeriodNumberOfDays"), Integer.class, getCalculationPeriodNumberOfDays(), this);
		processor.processBasic(path.newSubPath("notionalAmount"), BigDecimal.class, getNotionalAmount(), this);
		processRosetta(path.newSubPath("fxLinkedNotionalAmount"), processor, FxLinkedNotionalAmount.class, getFxLinkedNotionalAmount());
		processRosetta(path.newSubPath("floatingRateDefinition"), processor, FloatingRateDefinition.class, getFloatingRateDefinition());
		processor.processBasic(path.newSubPath("fixedRate"), BigDecimal.class, getFixedRate(), this);
		processor.processBasic(path.newSubPath("dayCountYearFraction"), BigDecimal.class, getDayCountYearFraction(), this);
		processRosetta(path.newSubPath("forecastAmount"), processor, Money.class, getForecastAmount());
		processor.processBasic(path.newSubPath("forecastRate"), BigDecimal.class, getForecastRate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CalculationPeriodBuilder extends CalculationPeriod, CalculationPeriodBase.CalculationPeriodBaseBuilder, RosettaModelObjectBuilder {
		FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder getOrCreateFxLinkedNotionalAmount();
		FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder getFxLinkedNotionalAmount();
		FloatingRateDefinition.FloatingRateDefinitionBuilder getOrCreateFloatingRateDefinition();
		FloatingRateDefinition.FloatingRateDefinitionBuilder getFloatingRateDefinition();
		Money.MoneyBuilder getOrCreateForecastAmount();
		Money.MoneyBuilder getForecastAmount();
		CalculationPeriod.CalculationPeriodBuilder setAdjustedStartDate(Date adjustedStartDate);
		CalculationPeriod.CalculationPeriodBuilder setAdjustedEndDate(Date adjustedEndDate);
		CalculationPeriod.CalculationPeriodBuilder setMeta(MetaFields meta);
		CalculationPeriod.CalculationPeriodBuilder setUnadjustedStartDate(Date unadjustedStartDate);
		CalculationPeriod.CalculationPeriodBuilder setUnadjustedEndDate(Date unadjustedEndDate);
		CalculationPeriod.CalculationPeriodBuilder setCalculationPeriodNumberOfDays(Integer calculationPeriodNumberOfDays);
		CalculationPeriod.CalculationPeriodBuilder setNotionalAmount(BigDecimal notionalAmount);
		CalculationPeriod.CalculationPeriodBuilder setFxLinkedNotionalAmount(FxLinkedNotionalAmount fxLinkedNotionalAmount);
		CalculationPeriod.CalculationPeriodBuilder setFloatingRateDefinition(FloatingRateDefinition floatingRateDefinition);
		CalculationPeriod.CalculationPeriodBuilder setFixedRate(BigDecimal fixedRate);
		CalculationPeriod.CalculationPeriodBuilder setDayCountYearFraction(BigDecimal dayCountYearFraction);
		CalculationPeriod.CalculationPeriodBuilder setForecastAmount(Money forecastAmount);
		CalculationPeriod.CalculationPeriodBuilder setForecastRate(BigDecimal forecastRate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("adjustedStartDate"), Date.class, getAdjustedStartDate(), this);
			processor.processBasic(path.newSubPath("adjustedEndDate"), Date.class, getAdjustedEndDate(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
			processor.processBasic(path.newSubPath("unadjustedStartDate"), Date.class, getUnadjustedStartDate(), this);
			processor.processBasic(path.newSubPath("unadjustedEndDate"), Date.class, getUnadjustedEndDate(), this);
			processor.processBasic(path.newSubPath("calculationPeriodNumberOfDays"), Integer.class, getCalculationPeriodNumberOfDays(), this);
			processor.processBasic(path.newSubPath("notionalAmount"), BigDecimal.class, getNotionalAmount(), this);
			processRosetta(path.newSubPath("fxLinkedNotionalAmount"), processor, FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder.class, getFxLinkedNotionalAmount());
			processRosetta(path.newSubPath("floatingRateDefinition"), processor, FloatingRateDefinition.FloatingRateDefinitionBuilder.class, getFloatingRateDefinition());
			processor.processBasic(path.newSubPath("fixedRate"), BigDecimal.class, getFixedRate(), this);
			processor.processBasic(path.newSubPath("dayCountYearFraction"), BigDecimal.class, getDayCountYearFraction(), this);
			processRosetta(path.newSubPath("forecastAmount"), processor, Money.MoneyBuilder.class, getForecastAmount());
			processor.processBasic(path.newSubPath("forecastRate"), BigDecimal.class, getForecastRate(), this);
		}
		

		CalculationPeriod.CalculationPeriodBuilder prune();
	}

	/*********************** Immutable Implementation of CalculationPeriod  ***********************/
	class CalculationPeriodImpl extends CalculationPeriodBase.CalculationPeriodBaseImpl implements CalculationPeriod {
		private final Date unadjustedStartDate;
		private final Date unadjustedEndDate;
		private final Integer calculationPeriodNumberOfDays;
		private final BigDecimal notionalAmount;
		private final FxLinkedNotionalAmount fxLinkedNotionalAmount;
		private final FloatingRateDefinition floatingRateDefinition;
		private final BigDecimal fixedRate;
		private final BigDecimal dayCountYearFraction;
		private final Money forecastAmount;
		private final BigDecimal forecastRate;
		
		protected CalculationPeriodImpl(CalculationPeriod.CalculationPeriodBuilder builder) {
			super(builder);
			this.unadjustedStartDate = builder.getUnadjustedStartDate();
			this.unadjustedEndDate = builder.getUnadjustedEndDate();
			this.calculationPeriodNumberOfDays = builder.getCalculationPeriodNumberOfDays();
			this.notionalAmount = builder.getNotionalAmount();
			this.fxLinkedNotionalAmount = ofNullable(builder.getFxLinkedNotionalAmount()).map(f->f.build()).orElse(null);
			this.floatingRateDefinition = ofNullable(builder.getFloatingRateDefinition()).map(f->f.build()).orElse(null);
			this.fixedRate = builder.getFixedRate();
			this.dayCountYearFraction = builder.getDayCountYearFraction();
			this.forecastAmount = ofNullable(builder.getForecastAmount()).map(f->f.build()).orElse(null);
			this.forecastRate = builder.getForecastRate();
		}
		
		@Override
		@RosettaAttribute("unadjustedStartDate")
		public Date getUnadjustedStartDate() {
			return unadjustedStartDate;
		}
		
		@Override
		@RosettaAttribute("unadjustedEndDate")
		public Date getUnadjustedEndDate() {
			return unadjustedEndDate;
		}
		
		@Override
		@RosettaAttribute("calculationPeriodNumberOfDays")
		public Integer getCalculationPeriodNumberOfDays() {
			return calculationPeriodNumberOfDays;
		}
		
		@Override
		@RosettaAttribute("notionalAmount")
		public BigDecimal getNotionalAmount() {
			return notionalAmount;
		}
		
		@Override
		@RosettaAttribute("fxLinkedNotionalAmount")
		public FxLinkedNotionalAmount getFxLinkedNotionalAmount() {
			return fxLinkedNotionalAmount;
		}
		
		@Override
		@RosettaAttribute("floatingRateDefinition")
		public FloatingRateDefinition getFloatingRateDefinition() {
			return floatingRateDefinition;
		}
		
		@Override
		@RosettaAttribute("fixedRate")
		public BigDecimal getFixedRate() {
			return fixedRate;
		}
		
		@Override
		@RosettaAttribute("dayCountYearFraction")
		public BigDecimal getDayCountYearFraction() {
			return dayCountYearFraction;
		}
		
		@Override
		@RosettaAttribute("forecastAmount")
		public Money getForecastAmount() {
			return forecastAmount;
		}
		
		@Override
		@RosettaAttribute("forecastRate")
		public BigDecimal getForecastRate() {
			return forecastRate;
		}
		
		@Override
		public CalculationPeriod build() {
			return this;
		}
		
		@Override
		public CalculationPeriod.CalculationPeriodBuilder toBuilder() {
			CalculationPeriod.CalculationPeriodBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CalculationPeriod.CalculationPeriodBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getUnadjustedStartDate()).ifPresent(builder::setUnadjustedStartDate);
			ofNullable(getUnadjustedEndDate()).ifPresent(builder::setUnadjustedEndDate);
			ofNullable(getCalculationPeriodNumberOfDays()).ifPresent(builder::setCalculationPeriodNumberOfDays);
			ofNullable(getNotionalAmount()).ifPresent(builder::setNotionalAmount);
			ofNullable(getFxLinkedNotionalAmount()).ifPresent(builder::setFxLinkedNotionalAmount);
			ofNullable(getFloatingRateDefinition()).ifPresent(builder::setFloatingRateDefinition);
			ofNullable(getFixedRate()).ifPresent(builder::setFixedRate);
			ofNullable(getDayCountYearFraction()).ifPresent(builder::setDayCountYearFraction);
			ofNullable(getForecastAmount()).ifPresent(builder::setForecastAmount);
			ofNullable(getForecastRate()).ifPresent(builder::setForecastRate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CalculationPeriod _that = getType().cast(o);
		
			if (!Objects.equals(unadjustedStartDate, _that.getUnadjustedStartDate())) return false;
			if (!Objects.equals(unadjustedEndDate, _that.getUnadjustedEndDate())) return false;
			if (!Objects.equals(calculationPeriodNumberOfDays, _that.getCalculationPeriodNumberOfDays())) return false;
			if (!Objects.equals(notionalAmount, _that.getNotionalAmount())) return false;
			if (!Objects.equals(fxLinkedNotionalAmount, _that.getFxLinkedNotionalAmount())) return false;
			if (!Objects.equals(floatingRateDefinition, _that.getFloatingRateDefinition())) return false;
			if (!Objects.equals(fixedRate, _that.getFixedRate())) return false;
			if (!Objects.equals(dayCountYearFraction, _that.getDayCountYearFraction())) return false;
			if (!Objects.equals(forecastAmount, _that.getForecastAmount())) return false;
			if (!Objects.equals(forecastRate, _that.getForecastRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (unadjustedStartDate != null ? unadjustedStartDate.hashCode() : 0);
			_result = 31 * _result + (unadjustedEndDate != null ? unadjustedEndDate.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodNumberOfDays != null ? calculationPeriodNumberOfDays.hashCode() : 0);
			_result = 31 * _result + (notionalAmount != null ? notionalAmount.hashCode() : 0);
			_result = 31 * _result + (fxLinkedNotionalAmount != null ? fxLinkedNotionalAmount.hashCode() : 0);
			_result = 31 * _result + (floatingRateDefinition != null ? floatingRateDefinition.hashCode() : 0);
			_result = 31 * _result + (fixedRate != null ? fixedRate.hashCode() : 0);
			_result = 31 * _result + (dayCountYearFraction != null ? dayCountYearFraction.hashCode() : 0);
			_result = 31 * _result + (forecastAmount != null ? forecastAmount.hashCode() : 0);
			_result = 31 * _result + (forecastRate != null ? forecastRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationPeriod {" +
				"unadjustedStartDate=" + this.unadjustedStartDate + ", " +
				"unadjustedEndDate=" + this.unadjustedEndDate + ", " +
				"calculationPeriodNumberOfDays=" + this.calculationPeriodNumberOfDays + ", " +
				"notionalAmount=" + this.notionalAmount + ", " +
				"fxLinkedNotionalAmount=" + this.fxLinkedNotionalAmount + ", " +
				"floatingRateDefinition=" + this.floatingRateDefinition + ", " +
				"fixedRate=" + this.fixedRate + ", " +
				"dayCountYearFraction=" + this.dayCountYearFraction + ", " +
				"forecastAmount=" + this.forecastAmount + ", " +
				"forecastRate=" + this.forecastRate +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of CalculationPeriod  ***********************/
	class CalculationPeriodBuilderImpl extends CalculationPeriodBase.CalculationPeriodBaseBuilderImpl  implements CalculationPeriod.CalculationPeriodBuilder {
	
		protected Date unadjustedStartDate;
		protected Date unadjustedEndDate;
		protected Integer calculationPeriodNumberOfDays;
		protected BigDecimal notionalAmount;
		protected FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder fxLinkedNotionalAmount;
		protected FloatingRateDefinition.FloatingRateDefinitionBuilder floatingRateDefinition;
		protected BigDecimal fixedRate;
		protected BigDecimal dayCountYearFraction;
		protected Money.MoneyBuilder forecastAmount;
		protected BigDecimal forecastRate;
	
		public CalculationPeriodBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("unadjustedStartDate")
		public Date getUnadjustedStartDate() {
			return unadjustedStartDate;
		}
		
		@Override
		@RosettaAttribute("unadjustedEndDate")
		public Date getUnadjustedEndDate() {
			return unadjustedEndDate;
		}
		
		@Override
		@RosettaAttribute("calculationPeriodNumberOfDays")
		public Integer getCalculationPeriodNumberOfDays() {
			return calculationPeriodNumberOfDays;
		}
		
		@Override
		@RosettaAttribute("notionalAmount")
		public BigDecimal getNotionalAmount() {
			return notionalAmount;
		}
		
		@Override
		@RosettaAttribute("fxLinkedNotionalAmount")
		public FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder getFxLinkedNotionalAmount() {
			return fxLinkedNotionalAmount;
		}
		
		@Override
		public FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder getOrCreateFxLinkedNotionalAmount() {
			FxLinkedNotionalAmount.FxLinkedNotionalAmountBuilder result;
			if (fxLinkedNotionalAmount!=null) {
				result = fxLinkedNotionalAmount;
			}
			else {
				result = fxLinkedNotionalAmount = FxLinkedNotionalAmount.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("floatingRateDefinition")
		public FloatingRateDefinition.FloatingRateDefinitionBuilder getFloatingRateDefinition() {
			return floatingRateDefinition;
		}
		
		@Override
		public FloatingRateDefinition.FloatingRateDefinitionBuilder getOrCreateFloatingRateDefinition() {
			FloatingRateDefinition.FloatingRateDefinitionBuilder result;
			if (floatingRateDefinition!=null) {
				result = floatingRateDefinition;
			}
			else {
				result = floatingRateDefinition = FloatingRateDefinition.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fixedRate")
		public BigDecimal getFixedRate() {
			return fixedRate;
		}
		
		@Override
		@RosettaAttribute("dayCountYearFraction")
		public BigDecimal getDayCountYearFraction() {
			return dayCountYearFraction;
		}
		
		@Override
		@RosettaAttribute("forecastAmount")
		public Money.MoneyBuilder getForecastAmount() {
			return forecastAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateForecastAmount() {
			Money.MoneyBuilder result;
			if (forecastAmount!=null) {
				result = forecastAmount;
			}
			else {
				result = forecastAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("forecastRate")
		public BigDecimal getForecastRate() {
			return forecastRate;
		}
		
	
		@Override
		@RosettaAttribute("adjustedStartDate")
		public CalculationPeriod.CalculationPeriodBuilder setAdjustedStartDate(Date adjustedStartDate) {
			this.adjustedStartDate = adjustedStartDate==null?null:adjustedStartDate;
			return this;
		}
		@Override
		@RosettaAttribute("adjustedEndDate")
		public CalculationPeriod.CalculationPeriodBuilder setAdjustedEndDate(Date adjustedEndDate) {
			this.adjustedEndDate = adjustedEndDate==null?null:adjustedEndDate;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public CalculationPeriod.CalculationPeriodBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("unadjustedStartDate")
		public CalculationPeriod.CalculationPeriodBuilder setUnadjustedStartDate(Date unadjustedStartDate) {
			this.unadjustedStartDate = unadjustedStartDate==null?null:unadjustedStartDate;
			return this;
		}
		@Override
		@RosettaAttribute("unadjustedEndDate")
		public CalculationPeriod.CalculationPeriodBuilder setUnadjustedEndDate(Date unadjustedEndDate) {
			this.unadjustedEndDate = unadjustedEndDate==null?null:unadjustedEndDate;
			return this;
		}
		@Override
		@RosettaAttribute("calculationPeriodNumberOfDays")
		public CalculationPeriod.CalculationPeriodBuilder setCalculationPeriodNumberOfDays(Integer calculationPeriodNumberOfDays) {
			this.calculationPeriodNumberOfDays = calculationPeriodNumberOfDays==null?null:calculationPeriodNumberOfDays;
			return this;
		}
		@Override
		@RosettaAttribute("notionalAmount")
		public CalculationPeriod.CalculationPeriodBuilder setNotionalAmount(BigDecimal notionalAmount) {
			this.notionalAmount = notionalAmount==null?null:notionalAmount;
			return this;
		}
		@Override
		@RosettaAttribute("fxLinkedNotionalAmount")
		public CalculationPeriod.CalculationPeriodBuilder setFxLinkedNotionalAmount(FxLinkedNotionalAmount fxLinkedNotionalAmount) {
			this.fxLinkedNotionalAmount = fxLinkedNotionalAmount==null?null:fxLinkedNotionalAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("floatingRateDefinition")
		public CalculationPeriod.CalculationPeriodBuilder setFloatingRateDefinition(FloatingRateDefinition floatingRateDefinition) {
			this.floatingRateDefinition = floatingRateDefinition==null?null:floatingRateDefinition.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fixedRate")
		public CalculationPeriod.CalculationPeriodBuilder setFixedRate(BigDecimal fixedRate) {
			this.fixedRate = fixedRate==null?null:fixedRate;
			return this;
		}
		@Override
		@RosettaAttribute("dayCountYearFraction")
		public CalculationPeriod.CalculationPeriodBuilder setDayCountYearFraction(BigDecimal dayCountYearFraction) {
			this.dayCountYearFraction = dayCountYearFraction==null?null:dayCountYearFraction;
			return this;
		}
		@Override
		@RosettaAttribute("forecastAmount")
		public CalculationPeriod.CalculationPeriodBuilder setForecastAmount(Money forecastAmount) {
			this.forecastAmount = forecastAmount==null?null:forecastAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("forecastRate")
		public CalculationPeriod.CalculationPeriodBuilder setForecastRate(BigDecimal forecastRate) {
			this.forecastRate = forecastRate==null?null:forecastRate;
			return this;
		}
		
		@Override
		public CalculationPeriod build() {
			return new CalculationPeriod.CalculationPeriodImpl(this);
		}
		
		@Override
		public CalculationPeriod.CalculationPeriodBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationPeriod.CalculationPeriodBuilder prune() {
			super.prune();
			if (fxLinkedNotionalAmount!=null && !fxLinkedNotionalAmount.prune().hasData()) fxLinkedNotionalAmount = null;
			if (floatingRateDefinition!=null && !floatingRateDefinition.prune().hasData()) floatingRateDefinition = null;
			if (forecastAmount!=null && !forecastAmount.prune().hasData()) forecastAmount = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getUnadjustedStartDate()!=null) return true;
			if (getUnadjustedEndDate()!=null) return true;
			if (getCalculationPeriodNumberOfDays()!=null) return true;
			if (getNotionalAmount()!=null) return true;
			if (getFxLinkedNotionalAmount()!=null && getFxLinkedNotionalAmount().hasData()) return true;
			if (getFloatingRateDefinition()!=null && getFloatingRateDefinition().hasData()) return true;
			if (getFixedRate()!=null) return true;
			if (getDayCountYearFraction()!=null) return true;
			if (getForecastAmount()!=null && getForecastAmount().hasData()) return true;
			if (getForecastRate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CalculationPeriod.CalculationPeriodBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			CalculationPeriod.CalculationPeriodBuilder o = (CalculationPeriod.CalculationPeriodBuilder) other;
			
			merger.mergeRosetta(getFxLinkedNotionalAmount(), o.getFxLinkedNotionalAmount(), this::setFxLinkedNotionalAmount);
			merger.mergeRosetta(getFloatingRateDefinition(), o.getFloatingRateDefinition(), this::setFloatingRateDefinition);
			merger.mergeRosetta(getForecastAmount(), o.getForecastAmount(), this::setForecastAmount);
			
			merger.mergeBasic(getUnadjustedStartDate(), o.getUnadjustedStartDate(), this::setUnadjustedStartDate);
			merger.mergeBasic(getUnadjustedEndDate(), o.getUnadjustedEndDate(), this::setUnadjustedEndDate);
			merger.mergeBasic(getCalculationPeriodNumberOfDays(), o.getCalculationPeriodNumberOfDays(), this::setCalculationPeriodNumberOfDays);
			merger.mergeBasic(getNotionalAmount(), o.getNotionalAmount(), this::setNotionalAmount);
			merger.mergeBasic(getFixedRate(), o.getFixedRate(), this::setFixedRate);
			merger.mergeBasic(getDayCountYearFraction(), o.getDayCountYearFraction(), this::setDayCountYearFraction);
			merger.mergeBasic(getForecastRate(), o.getForecastRate(), this::setForecastRate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CalculationPeriod _that = getType().cast(o);
		
			if (!Objects.equals(unadjustedStartDate, _that.getUnadjustedStartDate())) return false;
			if (!Objects.equals(unadjustedEndDate, _that.getUnadjustedEndDate())) return false;
			if (!Objects.equals(calculationPeriodNumberOfDays, _that.getCalculationPeriodNumberOfDays())) return false;
			if (!Objects.equals(notionalAmount, _that.getNotionalAmount())) return false;
			if (!Objects.equals(fxLinkedNotionalAmount, _that.getFxLinkedNotionalAmount())) return false;
			if (!Objects.equals(floatingRateDefinition, _that.getFloatingRateDefinition())) return false;
			if (!Objects.equals(fixedRate, _that.getFixedRate())) return false;
			if (!Objects.equals(dayCountYearFraction, _that.getDayCountYearFraction())) return false;
			if (!Objects.equals(forecastAmount, _that.getForecastAmount())) return false;
			if (!Objects.equals(forecastRate, _that.getForecastRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (unadjustedStartDate != null ? unadjustedStartDate.hashCode() : 0);
			_result = 31 * _result + (unadjustedEndDate != null ? unadjustedEndDate.hashCode() : 0);
			_result = 31 * _result + (calculationPeriodNumberOfDays != null ? calculationPeriodNumberOfDays.hashCode() : 0);
			_result = 31 * _result + (notionalAmount != null ? notionalAmount.hashCode() : 0);
			_result = 31 * _result + (fxLinkedNotionalAmount != null ? fxLinkedNotionalAmount.hashCode() : 0);
			_result = 31 * _result + (floatingRateDefinition != null ? floatingRateDefinition.hashCode() : 0);
			_result = 31 * _result + (fixedRate != null ? fixedRate.hashCode() : 0);
			_result = 31 * _result + (dayCountYearFraction != null ? dayCountYearFraction.hashCode() : 0);
			_result = 31 * _result + (forecastAmount != null ? forecastAmount.hashCode() : 0);
			_result = 31 * _result + (forecastRate != null ? forecastRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CalculationPeriodBuilder {" +
				"unadjustedStartDate=" + this.unadjustedStartDate + ", " +
				"unadjustedEndDate=" + this.unadjustedEndDate + ", " +
				"calculationPeriodNumberOfDays=" + this.calculationPeriodNumberOfDays + ", " +
				"notionalAmount=" + this.notionalAmount + ", " +
				"fxLinkedNotionalAmount=" + this.fxLinkedNotionalAmount + ", " +
				"floatingRateDefinition=" + this.floatingRateDefinition + ", " +
				"fixedRate=" + this.fixedRate + ", " +
				"dayCountYearFraction=" + this.dayCountYearFraction + ", " +
				"forecastAmount=" + this.forecastAmount + ", " +
				"forecastRate=" + this.forecastRate +
			'}' + " " + super.toString();
		}
	}
}
