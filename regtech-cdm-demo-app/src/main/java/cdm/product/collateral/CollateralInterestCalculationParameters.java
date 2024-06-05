package cdm.product.collateral;

import cdm.base.datetime.BusinessCenterEnum;
import cdm.base.datetime.CompoundingTypeEnum;
import cdm.base.datetime.RoundingFrequencyEnum;
import cdm.base.datetime.daycount.DayCountFractionEnum;
import cdm.base.math.Rounding;
import cdm.product.collateral.CollateralAgreementFloatingRate;
import cdm.product.collateral.CollateralInterestCalculationParameters;
import cdm.product.collateral.CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder;
import cdm.product.collateral.CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilderImpl;
import cdm.product.collateral.CollateralInterestCalculationParameters.CollateralInterestCalculationParametersImpl;
import cdm.product.collateral.meta.CollateralInterestCalculationParametersMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Represents parameters for calculating the amount the floating interest calculation, e.g.  for a single currency or defaults for all currencies.
 * @version ${project.version}
 */
@RosettaDataType(value="CollateralInterestCalculationParameters", builder=CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilderImpl.class, version="${project.version}")
public interface CollateralInterestCalculationParameters extends RosettaModelObject {

	CollateralInterestCalculationParametersMeta metaData = new CollateralInterestCalculationParametersMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the applicable fixed rate  if used.
	 */
	BigDecimal getFixedRate();
	/**
	 * Specifies the floating interest rate to be used.
	 */
	CollateralAgreementFloatingRate getFloatingRate();
	/**
	 * If True, specifies that the interest transfers should be converted to base currency equivalent, or if False specifies that the transfer should be in the currency of the collateral.
	 */
	Boolean getInBaseCurrency();
	/**
	 * Specifies the type of compounding to be applied (None, Business, Calendar).
	 */
	CompoundingTypeEnum getCompoundingType();
	/**
	 * Specifies the applicable business centers for compounding.
	 */
	List<BusinessCenterEnum> getCompoundingBusinessCenter();
	/**
	 * Specifies the day count fraction to use for that currency.
	 */
	DayCountFractionEnum getDayCountFraction();
	/**
	 * Specifies the rounding rules for settling in that currency.
	 */
	Rounding getRounding();
	/**
	 * Specifies when/how often is rounding applied?
	 */
	RoundingFrequencyEnum getRoundingFrequency();
	/**
	 * Specifies the withholding tax rate if a withholding tax is applicable.
	 */
	BigDecimal getWithholdingTaxRate();

	/*********************** Build Methods  ***********************/
	CollateralInterestCalculationParameters build();
	
	CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder toBuilder();
	
	static CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder builder() {
		return new CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CollateralInterestCalculationParameters> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CollateralInterestCalculationParameters> getType() {
		return CollateralInterestCalculationParameters.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("fixedRate"), BigDecimal.class, getFixedRate(), this);
		processRosetta(path.newSubPath("floatingRate"), processor, CollateralAgreementFloatingRate.class, getFloatingRate());
		processor.processBasic(path.newSubPath("inBaseCurrency"), Boolean.class, getInBaseCurrency(), this);
		processor.processBasic(path.newSubPath("compoundingType"), CompoundingTypeEnum.class, getCompoundingType(), this);
		processor.processBasic(path.newSubPath("compoundingBusinessCenter"), BusinessCenterEnum.class, getCompoundingBusinessCenter(), this);
		processor.processBasic(path.newSubPath("dayCountFraction"), DayCountFractionEnum.class, getDayCountFraction(), this);
		processRosetta(path.newSubPath("rounding"), processor, Rounding.class, getRounding());
		processor.processBasic(path.newSubPath("roundingFrequency"), RoundingFrequencyEnum.class, getRoundingFrequency(), this);
		processor.processBasic(path.newSubPath("withholdingTaxRate"), BigDecimal.class, getWithholdingTaxRate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CollateralInterestCalculationParametersBuilder extends CollateralInterestCalculationParameters, RosettaModelObjectBuilder {
		CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder getOrCreateFloatingRate();
		CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder getFloatingRate();
		Rounding.RoundingBuilder getOrCreateRounding();
		Rounding.RoundingBuilder getRounding();
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setFixedRate(BigDecimal fixedRate);
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setFloatingRate(CollateralAgreementFloatingRate floatingRate);
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setInBaseCurrency(Boolean inBaseCurrency);
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setCompoundingType(CompoundingTypeEnum compoundingType);
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder addCompoundingBusinessCenter(BusinessCenterEnum compoundingBusinessCenter0);
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder addCompoundingBusinessCenter(BusinessCenterEnum compoundingBusinessCenter1, int _idx);
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder addCompoundingBusinessCenter(List<? extends BusinessCenterEnum> compoundingBusinessCenter2);
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setCompoundingBusinessCenter(List<? extends BusinessCenterEnum> compoundingBusinessCenter3);
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setDayCountFraction(DayCountFractionEnum dayCountFraction);
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setRounding(Rounding rounding);
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setRoundingFrequency(RoundingFrequencyEnum roundingFrequency);
		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setWithholdingTaxRate(BigDecimal withholdingTaxRate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("fixedRate"), BigDecimal.class, getFixedRate(), this);
			processRosetta(path.newSubPath("floatingRate"), processor, CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder.class, getFloatingRate());
			processor.processBasic(path.newSubPath("inBaseCurrency"), Boolean.class, getInBaseCurrency(), this);
			processor.processBasic(path.newSubPath("compoundingType"), CompoundingTypeEnum.class, getCompoundingType(), this);
			processor.processBasic(path.newSubPath("compoundingBusinessCenter"), BusinessCenterEnum.class, getCompoundingBusinessCenter(), this);
			processor.processBasic(path.newSubPath("dayCountFraction"), DayCountFractionEnum.class, getDayCountFraction(), this);
			processRosetta(path.newSubPath("rounding"), processor, Rounding.RoundingBuilder.class, getRounding());
			processor.processBasic(path.newSubPath("roundingFrequency"), RoundingFrequencyEnum.class, getRoundingFrequency(), this);
			processor.processBasic(path.newSubPath("withholdingTaxRate"), BigDecimal.class, getWithholdingTaxRate(), this);
		}
		

		CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder prune();
	}

	/*********************** Immutable Implementation of CollateralInterestCalculationParameters  ***********************/
	class CollateralInterestCalculationParametersImpl implements CollateralInterestCalculationParameters {
		private final BigDecimal fixedRate;
		private final CollateralAgreementFloatingRate floatingRate;
		private final Boolean inBaseCurrency;
		private final CompoundingTypeEnum compoundingType;
		private final List<BusinessCenterEnum> compoundingBusinessCenter;
		private final DayCountFractionEnum dayCountFraction;
		private final Rounding rounding;
		private final RoundingFrequencyEnum roundingFrequency;
		private final BigDecimal withholdingTaxRate;
		
		protected CollateralInterestCalculationParametersImpl(CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder builder) {
			this.fixedRate = builder.getFixedRate();
			this.floatingRate = ofNullable(builder.getFloatingRate()).map(f->f.build()).orElse(null);
			this.inBaseCurrency = builder.getInBaseCurrency();
			this.compoundingType = builder.getCompoundingType();
			this.compoundingBusinessCenter = ofNullable(builder.getCompoundingBusinessCenter()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.dayCountFraction = builder.getDayCountFraction();
			this.rounding = ofNullable(builder.getRounding()).map(f->f.build()).orElse(null);
			this.roundingFrequency = builder.getRoundingFrequency();
			this.withholdingTaxRate = builder.getWithholdingTaxRate();
		}
		
		@Override
		@RosettaAttribute("fixedRate")
		public BigDecimal getFixedRate() {
			return fixedRate;
		}
		
		@Override
		@RosettaAttribute("floatingRate")
		public CollateralAgreementFloatingRate getFloatingRate() {
			return floatingRate;
		}
		
		@Override
		@RosettaAttribute("inBaseCurrency")
		public Boolean getInBaseCurrency() {
			return inBaseCurrency;
		}
		
		@Override
		@RosettaAttribute("compoundingType")
		public CompoundingTypeEnum getCompoundingType() {
			return compoundingType;
		}
		
		@Override
		@RosettaAttribute("compoundingBusinessCenter")
		public List<BusinessCenterEnum> getCompoundingBusinessCenter() {
			return compoundingBusinessCenter;
		}
		
		@Override
		@RosettaAttribute("dayCountFraction")
		public DayCountFractionEnum getDayCountFraction() {
			return dayCountFraction;
		}
		
		@Override
		@RosettaAttribute("rounding")
		public Rounding getRounding() {
			return rounding;
		}
		
		@Override
		@RosettaAttribute("roundingFrequency")
		public RoundingFrequencyEnum getRoundingFrequency() {
			return roundingFrequency;
		}
		
		@Override
		@RosettaAttribute("withholdingTaxRate")
		public BigDecimal getWithholdingTaxRate() {
			return withholdingTaxRate;
		}
		
		@Override
		public CollateralInterestCalculationParameters build() {
			return this;
		}
		
		@Override
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder toBuilder() {
			CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder builder) {
			ofNullable(getFixedRate()).ifPresent(builder::setFixedRate);
			ofNullable(getFloatingRate()).ifPresent(builder::setFloatingRate);
			ofNullable(getInBaseCurrency()).ifPresent(builder::setInBaseCurrency);
			ofNullable(getCompoundingType()).ifPresent(builder::setCompoundingType);
			ofNullable(getCompoundingBusinessCenter()).ifPresent(builder::setCompoundingBusinessCenter);
			ofNullable(getDayCountFraction()).ifPresent(builder::setDayCountFraction);
			ofNullable(getRounding()).ifPresent(builder::setRounding);
			ofNullable(getRoundingFrequency()).ifPresent(builder::setRoundingFrequency);
			ofNullable(getWithholdingTaxRate()).ifPresent(builder::setWithholdingTaxRate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralInterestCalculationParameters _that = getType().cast(o);
		
			if (!Objects.equals(fixedRate, _that.getFixedRate())) return false;
			if (!Objects.equals(floatingRate, _that.getFloatingRate())) return false;
			if (!Objects.equals(inBaseCurrency, _that.getInBaseCurrency())) return false;
			if (!Objects.equals(compoundingType, _that.getCompoundingType())) return false;
			if (!ListEquals.listEquals(compoundingBusinessCenter, _that.getCompoundingBusinessCenter())) return false;
			if (!Objects.equals(dayCountFraction, _that.getDayCountFraction())) return false;
			if (!Objects.equals(rounding, _that.getRounding())) return false;
			if (!Objects.equals(roundingFrequency, _that.getRoundingFrequency())) return false;
			if (!Objects.equals(withholdingTaxRate, _that.getWithholdingTaxRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fixedRate != null ? fixedRate.hashCode() : 0);
			_result = 31 * _result + (floatingRate != null ? floatingRate.hashCode() : 0);
			_result = 31 * _result + (inBaseCurrency != null ? inBaseCurrency.hashCode() : 0);
			_result = 31 * _result + (compoundingType != null ? compoundingType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (compoundingBusinessCenter != null ? compoundingBusinessCenter.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (dayCountFraction != null ? dayCountFraction.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (rounding != null ? rounding.hashCode() : 0);
			_result = 31 * _result + (roundingFrequency != null ? roundingFrequency.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (withholdingTaxRate != null ? withholdingTaxRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralInterestCalculationParameters {" +
				"fixedRate=" + this.fixedRate + ", " +
				"floatingRate=" + this.floatingRate + ", " +
				"inBaseCurrency=" + this.inBaseCurrency + ", " +
				"compoundingType=" + this.compoundingType + ", " +
				"compoundingBusinessCenter=" + this.compoundingBusinessCenter + ", " +
				"dayCountFraction=" + this.dayCountFraction + ", " +
				"rounding=" + this.rounding + ", " +
				"roundingFrequency=" + this.roundingFrequency + ", " +
				"withholdingTaxRate=" + this.withholdingTaxRate +
			'}';
		}
	}

	/*********************** Builder Implementation of CollateralInterestCalculationParameters  ***********************/
	class CollateralInterestCalculationParametersBuilderImpl implements CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder {
	
		protected BigDecimal fixedRate;
		protected CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder floatingRate;
		protected Boolean inBaseCurrency;
		protected CompoundingTypeEnum compoundingType;
		protected List<BusinessCenterEnum> compoundingBusinessCenter = new ArrayList<>();
		protected DayCountFractionEnum dayCountFraction;
		protected Rounding.RoundingBuilder rounding;
		protected RoundingFrequencyEnum roundingFrequency;
		protected BigDecimal withholdingTaxRate;
	
		public CollateralInterestCalculationParametersBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("fixedRate")
		public BigDecimal getFixedRate() {
			return fixedRate;
		}
		
		@Override
		@RosettaAttribute("floatingRate")
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder getFloatingRate() {
			return floatingRate;
		}
		
		@Override
		public CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder getOrCreateFloatingRate() {
			CollateralAgreementFloatingRate.CollateralAgreementFloatingRateBuilder result;
			if (floatingRate!=null) {
				result = floatingRate;
			}
			else {
				result = floatingRate = CollateralAgreementFloatingRate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("inBaseCurrency")
		public Boolean getInBaseCurrency() {
			return inBaseCurrency;
		}
		
		@Override
		@RosettaAttribute("compoundingType")
		public CompoundingTypeEnum getCompoundingType() {
			return compoundingType;
		}
		
		@Override
		@RosettaAttribute("compoundingBusinessCenter")
		public List<BusinessCenterEnum> getCompoundingBusinessCenter() {
			return compoundingBusinessCenter;
		}
		
		@Override
		@RosettaAttribute("dayCountFraction")
		public DayCountFractionEnum getDayCountFraction() {
			return dayCountFraction;
		}
		
		@Override
		@RosettaAttribute("rounding")
		public Rounding.RoundingBuilder getRounding() {
			return rounding;
		}
		
		@Override
		public Rounding.RoundingBuilder getOrCreateRounding() {
			Rounding.RoundingBuilder result;
			if (rounding!=null) {
				result = rounding;
			}
			else {
				result = rounding = Rounding.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("roundingFrequency")
		public RoundingFrequencyEnum getRoundingFrequency() {
			return roundingFrequency;
		}
		
		@Override
		@RosettaAttribute("withholdingTaxRate")
		public BigDecimal getWithholdingTaxRate() {
			return withholdingTaxRate;
		}
		
	
		@Override
		@RosettaAttribute("fixedRate")
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setFixedRate(BigDecimal fixedRate) {
			this.fixedRate = fixedRate==null?null:fixedRate;
			return this;
		}
		@Override
		@RosettaAttribute("floatingRate")
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setFloatingRate(CollateralAgreementFloatingRate floatingRate) {
			this.floatingRate = floatingRate==null?null:floatingRate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("inBaseCurrency")
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setInBaseCurrency(Boolean inBaseCurrency) {
			this.inBaseCurrency = inBaseCurrency==null?null:inBaseCurrency;
			return this;
		}
		@Override
		@RosettaAttribute("compoundingType")
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setCompoundingType(CompoundingTypeEnum compoundingType) {
			this.compoundingType = compoundingType==null?null:compoundingType;
			return this;
		}
		@Override
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder addCompoundingBusinessCenter(BusinessCenterEnum compoundingBusinessCenter) {
			if (compoundingBusinessCenter!=null) this.compoundingBusinessCenter.add(compoundingBusinessCenter);
			return this;
		}
		
		@Override
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder addCompoundingBusinessCenter(BusinessCenterEnum compoundingBusinessCenter, int _idx) {
			getIndex(this.compoundingBusinessCenter, _idx, () -> compoundingBusinessCenter);
			return this;
		}
		@Override 
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder addCompoundingBusinessCenter(List<? extends BusinessCenterEnum> compoundingBusinessCenters) {
			if (compoundingBusinessCenters != null) {
				for (BusinessCenterEnum toAdd : compoundingBusinessCenters) {
					this.compoundingBusinessCenter.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("compoundingBusinessCenter")
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setCompoundingBusinessCenter(List<? extends BusinessCenterEnum> compoundingBusinessCenters) {
			if (compoundingBusinessCenters == null)  {
				this.compoundingBusinessCenter = new ArrayList<>();
			}
			else {
				this.compoundingBusinessCenter = compoundingBusinessCenters.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("dayCountFraction")
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setDayCountFraction(DayCountFractionEnum dayCountFraction) {
			this.dayCountFraction = dayCountFraction==null?null:dayCountFraction;
			return this;
		}
		@Override
		@RosettaAttribute("rounding")
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setRounding(Rounding rounding) {
			this.rounding = rounding==null?null:rounding.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("roundingFrequency")
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setRoundingFrequency(RoundingFrequencyEnum roundingFrequency) {
			this.roundingFrequency = roundingFrequency==null?null:roundingFrequency;
			return this;
		}
		@Override
		@RosettaAttribute("withholdingTaxRate")
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder setWithholdingTaxRate(BigDecimal withholdingTaxRate) {
			this.withholdingTaxRate = withholdingTaxRate==null?null:withholdingTaxRate;
			return this;
		}
		
		@Override
		public CollateralInterestCalculationParameters build() {
			return new CollateralInterestCalculationParameters.CollateralInterestCalculationParametersImpl(this);
		}
		
		@Override
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder prune() {
			if (floatingRate!=null && !floatingRate.prune().hasData()) floatingRate = null;
			if (rounding!=null && !rounding.prune().hasData()) rounding = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFixedRate()!=null) return true;
			if (getFloatingRate()!=null && getFloatingRate().hasData()) return true;
			if (getInBaseCurrency()!=null) return true;
			if (getCompoundingType()!=null) return true;
			if (getCompoundingBusinessCenter()!=null && !getCompoundingBusinessCenter().isEmpty()) return true;
			if (getDayCountFraction()!=null) return true;
			if (getRounding()!=null && getRounding().hasData()) return true;
			if (getRoundingFrequency()!=null) return true;
			if (getWithholdingTaxRate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder o = (CollateralInterestCalculationParameters.CollateralInterestCalculationParametersBuilder) other;
			
			merger.mergeRosetta(getFloatingRate(), o.getFloatingRate(), this::setFloatingRate);
			merger.mergeRosetta(getRounding(), o.getRounding(), this::setRounding);
			
			merger.mergeBasic(getFixedRate(), o.getFixedRate(), this::setFixedRate);
			merger.mergeBasic(getInBaseCurrency(), o.getInBaseCurrency(), this::setInBaseCurrency);
			merger.mergeBasic(getCompoundingType(), o.getCompoundingType(), this::setCompoundingType);
			merger.mergeBasic(getCompoundingBusinessCenter(), o.getCompoundingBusinessCenter(), (Consumer<BusinessCenterEnum>) this::addCompoundingBusinessCenter);
			merger.mergeBasic(getDayCountFraction(), o.getDayCountFraction(), this::setDayCountFraction);
			merger.mergeBasic(getRoundingFrequency(), o.getRoundingFrequency(), this::setRoundingFrequency);
			merger.mergeBasic(getWithholdingTaxRate(), o.getWithholdingTaxRate(), this::setWithholdingTaxRate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CollateralInterestCalculationParameters _that = getType().cast(o);
		
			if (!Objects.equals(fixedRate, _that.getFixedRate())) return false;
			if (!Objects.equals(floatingRate, _that.getFloatingRate())) return false;
			if (!Objects.equals(inBaseCurrency, _that.getInBaseCurrency())) return false;
			if (!Objects.equals(compoundingType, _that.getCompoundingType())) return false;
			if (!ListEquals.listEquals(compoundingBusinessCenter, _that.getCompoundingBusinessCenter())) return false;
			if (!Objects.equals(dayCountFraction, _that.getDayCountFraction())) return false;
			if (!Objects.equals(rounding, _that.getRounding())) return false;
			if (!Objects.equals(roundingFrequency, _that.getRoundingFrequency())) return false;
			if (!Objects.equals(withholdingTaxRate, _that.getWithholdingTaxRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (fixedRate != null ? fixedRate.hashCode() : 0);
			_result = 31 * _result + (floatingRate != null ? floatingRate.hashCode() : 0);
			_result = 31 * _result + (inBaseCurrency != null ? inBaseCurrency.hashCode() : 0);
			_result = 31 * _result + (compoundingType != null ? compoundingType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (compoundingBusinessCenter != null ? compoundingBusinessCenter.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (dayCountFraction != null ? dayCountFraction.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (rounding != null ? rounding.hashCode() : 0);
			_result = 31 * _result + (roundingFrequency != null ? roundingFrequency.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (withholdingTaxRate != null ? withholdingTaxRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CollateralInterestCalculationParametersBuilder {" +
				"fixedRate=" + this.fixedRate + ", " +
				"floatingRate=" + this.floatingRate + ", " +
				"inBaseCurrency=" + this.inBaseCurrency + ", " +
				"compoundingType=" + this.compoundingType + ", " +
				"compoundingBusinessCenter=" + this.compoundingBusinessCenter + ", " +
				"dayCountFraction=" + this.dayCountFraction + ", " +
				"rounding=" + this.rounding + ", " +
				"roundingFrequency=" + this.roundingFrequency + ", " +
				"withholdingTaxRate=" + this.withholdingTaxRate +
			'}';
		}
	}
}
