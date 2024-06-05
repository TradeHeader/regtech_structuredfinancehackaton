package cdm.product.asset;

import cdm.base.staticdata.party.AncillaryRoleEnum;
import cdm.product.asset.DividendAmountTypeEnum;
import cdm.product.asset.DividendCompositionEnum;
import cdm.product.asset.DividendCurrency;
import cdm.product.asset.DividendEntitlementEnum;
import cdm.product.asset.DividendPayoutRatio;
import cdm.product.asset.DividendPeriod;
import cdm.product.asset.DividendPeriodEnum;
import cdm.product.asset.DividendReturnTerms;
import cdm.product.asset.DividendReturnTerms.DividendReturnTermsBuilder;
import cdm.product.asset.DividendReturnTerms.DividendReturnTermsBuilderImpl;
import cdm.product.asset.DividendReturnTerms.DividendReturnTermsImpl;
import cdm.product.asset.NonCashDividendTreatmentEnum;
import cdm.product.asset.meta.DividendReturnTermsMeta;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class describing the conditions governing the payment of dividends to the receiver of the equity return, with the exception of the dividend payout ratio, which is defined for each of the underlying components.
 * @version ${project.version}
 */
@RosettaDataType(value="DividendReturnTerms", builder=DividendReturnTerms.DividendReturnTermsBuilderImpl.class, version="${project.version}")
public interface DividendReturnTerms extends RosettaModelObject {

	DividendReturnTermsMeta metaData = new DividendReturnTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the dividend payout ratio associated with each underlier. In FpML 5.10 the payout is positioned at the underlier level, although there is an intent to reconsider this approach and position it at the leg level. This is approach adopted by the CDM.
	 */
	List<? extends DividendPayoutRatio> getDividendPayoutRatio();
	/**
	 * Boolean element that defines whether the dividend will be reinvested or not.
	 */
	Boolean getDividendReinvestment();
	/**
	 * Defines the date on which the receiver of the equity return is entitled to the dividend.
	 */
	DividendEntitlementEnum getDividendEntitlement();
	/**
	 * Specifies whether the dividend is paid with respect to the Dividend Period.
	 */
	DividendAmountTypeEnum getDividendAmountType();
	/**
	 * Performance calculation, in accordance with Part 1 Section 12 of the 2018 ISDA CDM Equity Confirmation for Security Equity Swap, Para 75. &#39;Equity Performance&#39;. Cumulative performance is used as a notional multiplier factor on both legs of an Equity Swap.
	 */
	String getPerformance();
	/**
	 * 2002 ISDA Equity Derivatives Definitions: Dividend Period as either the First Period or the Second Period. | 
	 */
	DividendPeriodEnum getFirstOrSecondPeriod();
	/**
	 * Specifies the party which determines if dividends are extraordinary in relation to normal levels.
	 */
	AncillaryRoleEnum getExtraordinaryDividendsParty();
	/**
	 * Determination of Gross Cash Dividend per Share.
	 */
	DividendAmountTypeEnum getExcessDividendAmount();
	/**
	 * Specifies the currency in which the dividend will be denominated, e.g. the dividend currency, or a specified currency. This class is not specified as such in FpML, which makes use of the CurrencyAndDeterminationMethod.model to specify such terms.
	 */
	DividendCurrency getDividendCurrency();
	/**
	 * Specifies the treatment of Non-Cash Dividends.
	 */
	NonCashDividendTreatmentEnum getNonCashDividendTreatment();
	/**
	 * Specifies how the composition of Dividends is to be determined.
	 */
	DividendCompositionEnum getDividendComposition();
	/**
	 * Specifies the method according to which special dividends are determined.
	 */
	Boolean getSpecialDividends();
	/**
	 * If present and true, then material non cash dividends are applicable.
	 */
	Boolean getMaterialDividend();
	/**
	 * One to many time bounded dividend payment periods, each with a dividend payment date per period.
	 */
	List<? extends DividendPeriod> getDividendPeriod();

	/*********************** Build Methods  ***********************/
	DividendReturnTerms build();
	
	DividendReturnTerms.DividendReturnTermsBuilder toBuilder();
	
	static DividendReturnTerms.DividendReturnTermsBuilder builder() {
		return new DividendReturnTerms.DividendReturnTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends DividendReturnTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends DividendReturnTerms> getType() {
		return DividendReturnTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("dividendPayoutRatio"), processor, DividendPayoutRatio.class, getDividendPayoutRatio());
		processor.processBasic(path.newSubPath("dividendReinvestment"), Boolean.class, getDividendReinvestment(), this);
		processor.processBasic(path.newSubPath("dividendEntitlement"), DividendEntitlementEnum.class, getDividendEntitlement(), this);
		processor.processBasic(path.newSubPath("dividendAmountType"), DividendAmountTypeEnum.class, getDividendAmountType(), this);
		processor.processBasic(path.newSubPath("performance"), String.class, getPerformance(), this);
		processor.processBasic(path.newSubPath("firstOrSecondPeriod"), DividendPeriodEnum.class, getFirstOrSecondPeriod(), this);
		processor.processBasic(path.newSubPath("extraordinaryDividendsParty"), AncillaryRoleEnum.class, getExtraordinaryDividendsParty(), this);
		processor.processBasic(path.newSubPath("excessDividendAmount"), DividendAmountTypeEnum.class, getExcessDividendAmount(), this);
		processRosetta(path.newSubPath("dividendCurrency"), processor, DividendCurrency.class, getDividendCurrency());
		processor.processBasic(path.newSubPath("nonCashDividendTreatment"), NonCashDividendTreatmentEnum.class, getNonCashDividendTreatment(), this);
		processor.processBasic(path.newSubPath("dividendComposition"), DividendCompositionEnum.class, getDividendComposition(), this);
		processor.processBasic(path.newSubPath("specialDividends"), Boolean.class, getSpecialDividends(), this);
		processor.processBasic(path.newSubPath("materialDividend"), Boolean.class, getMaterialDividend(), this);
		processRosetta(path.newSubPath("dividendPeriod"), processor, DividendPeriod.class, getDividendPeriod());
	}
	

	/*********************** Builder Interface  ***********************/
	interface DividendReturnTermsBuilder extends DividendReturnTerms, RosettaModelObjectBuilder {
		DividendPayoutRatio.DividendPayoutRatioBuilder getOrCreateDividendPayoutRatio(int _index);
		List<? extends DividendPayoutRatio.DividendPayoutRatioBuilder> getDividendPayoutRatio();
		DividendCurrency.DividendCurrencyBuilder getOrCreateDividendCurrency();
		DividendCurrency.DividendCurrencyBuilder getDividendCurrency();
		DividendPeriod.DividendPeriodBuilder getOrCreateDividendPeriod(int _index);
		List<? extends DividendPeriod.DividendPeriodBuilder> getDividendPeriod();
		DividendReturnTerms.DividendReturnTermsBuilder addDividendPayoutRatio(DividendPayoutRatio dividendPayoutRatio0);
		DividendReturnTerms.DividendReturnTermsBuilder addDividendPayoutRatio(DividendPayoutRatio dividendPayoutRatio1, int _idx);
		DividendReturnTerms.DividendReturnTermsBuilder addDividendPayoutRatio(List<? extends DividendPayoutRatio> dividendPayoutRatio2);
		DividendReturnTerms.DividendReturnTermsBuilder setDividendPayoutRatio(List<? extends DividendPayoutRatio> dividendPayoutRatio3);
		DividendReturnTerms.DividendReturnTermsBuilder setDividendReinvestment(Boolean dividendReinvestment);
		DividendReturnTerms.DividendReturnTermsBuilder setDividendEntitlement(DividendEntitlementEnum dividendEntitlement);
		DividendReturnTerms.DividendReturnTermsBuilder setDividendAmountType(DividendAmountTypeEnum dividendAmountType);
		DividendReturnTerms.DividendReturnTermsBuilder setPerformance(String performance);
		DividendReturnTerms.DividendReturnTermsBuilder setFirstOrSecondPeriod(DividendPeriodEnum firstOrSecondPeriod);
		DividendReturnTerms.DividendReturnTermsBuilder setExtraordinaryDividendsParty(AncillaryRoleEnum extraordinaryDividendsParty);
		DividendReturnTerms.DividendReturnTermsBuilder setExcessDividendAmount(DividendAmountTypeEnum excessDividendAmount);
		DividendReturnTerms.DividendReturnTermsBuilder setDividendCurrency(DividendCurrency dividendCurrency);
		DividendReturnTerms.DividendReturnTermsBuilder setNonCashDividendTreatment(NonCashDividendTreatmentEnum nonCashDividendTreatment);
		DividendReturnTerms.DividendReturnTermsBuilder setDividendComposition(DividendCompositionEnum dividendComposition);
		DividendReturnTerms.DividendReturnTermsBuilder setSpecialDividends(Boolean specialDividends);
		DividendReturnTerms.DividendReturnTermsBuilder setMaterialDividend(Boolean materialDividend);
		DividendReturnTerms.DividendReturnTermsBuilder addDividendPeriod(DividendPeriod dividendPeriod0);
		DividendReturnTerms.DividendReturnTermsBuilder addDividendPeriod(DividendPeriod dividendPeriod1, int _idx);
		DividendReturnTerms.DividendReturnTermsBuilder addDividendPeriod(List<? extends DividendPeriod> dividendPeriod2);
		DividendReturnTerms.DividendReturnTermsBuilder setDividendPeriod(List<? extends DividendPeriod> dividendPeriod3);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("dividendPayoutRatio"), processor, DividendPayoutRatio.DividendPayoutRatioBuilder.class, getDividendPayoutRatio());
			processor.processBasic(path.newSubPath("dividendReinvestment"), Boolean.class, getDividendReinvestment(), this);
			processor.processBasic(path.newSubPath("dividendEntitlement"), DividendEntitlementEnum.class, getDividendEntitlement(), this);
			processor.processBasic(path.newSubPath("dividendAmountType"), DividendAmountTypeEnum.class, getDividendAmountType(), this);
			processor.processBasic(path.newSubPath("performance"), String.class, getPerformance(), this);
			processor.processBasic(path.newSubPath("firstOrSecondPeriod"), DividendPeriodEnum.class, getFirstOrSecondPeriod(), this);
			processor.processBasic(path.newSubPath("extraordinaryDividendsParty"), AncillaryRoleEnum.class, getExtraordinaryDividendsParty(), this);
			processor.processBasic(path.newSubPath("excessDividendAmount"), DividendAmountTypeEnum.class, getExcessDividendAmount(), this);
			processRosetta(path.newSubPath("dividendCurrency"), processor, DividendCurrency.DividendCurrencyBuilder.class, getDividendCurrency());
			processor.processBasic(path.newSubPath("nonCashDividendTreatment"), NonCashDividendTreatmentEnum.class, getNonCashDividendTreatment(), this);
			processor.processBasic(path.newSubPath("dividendComposition"), DividendCompositionEnum.class, getDividendComposition(), this);
			processor.processBasic(path.newSubPath("specialDividends"), Boolean.class, getSpecialDividends(), this);
			processor.processBasic(path.newSubPath("materialDividend"), Boolean.class, getMaterialDividend(), this);
			processRosetta(path.newSubPath("dividendPeriod"), processor, DividendPeriod.DividendPeriodBuilder.class, getDividendPeriod());
		}
		

		DividendReturnTerms.DividendReturnTermsBuilder prune();
	}

	/*********************** Immutable Implementation of DividendReturnTerms  ***********************/
	class DividendReturnTermsImpl implements DividendReturnTerms {
		private final List<? extends DividendPayoutRatio> dividendPayoutRatio;
		private final Boolean dividendReinvestment;
		private final DividendEntitlementEnum dividendEntitlement;
		private final DividendAmountTypeEnum dividendAmountType;
		private final String performance;
		private final DividendPeriodEnum firstOrSecondPeriod;
		private final AncillaryRoleEnum extraordinaryDividendsParty;
		private final DividendAmountTypeEnum excessDividendAmount;
		private final DividendCurrency dividendCurrency;
		private final NonCashDividendTreatmentEnum nonCashDividendTreatment;
		private final DividendCompositionEnum dividendComposition;
		private final Boolean specialDividends;
		private final Boolean materialDividend;
		private final List<? extends DividendPeriod> dividendPeriod;
		
		protected DividendReturnTermsImpl(DividendReturnTerms.DividendReturnTermsBuilder builder) {
			this.dividendPayoutRatio = ofNullable(builder.getDividendPayoutRatio()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.dividendReinvestment = builder.getDividendReinvestment();
			this.dividendEntitlement = builder.getDividendEntitlement();
			this.dividendAmountType = builder.getDividendAmountType();
			this.performance = builder.getPerformance();
			this.firstOrSecondPeriod = builder.getFirstOrSecondPeriod();
			this.extraordinaryDividendsParty = builder.getExtraordinaryDividendsParty();
			this.excessDividendAmount = builder.getExcessDividendAmount();
			this.dividendCurrency = ofNullable(builder.getDividendCurrency()).map(f->f.build()).orElse(null);
			this.nonCashDividendTreatment = builder.getNonCashDividendTreatment();
			this.dividendComposition = builder.getDividendComposition();
			this.specialDividends = builder.getSpecialDividends();
			this.materialDividend = builder.getMaterialDividend();
			this.dividendPeriod = ofNullable(builder.getDividendPeriod()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
		}
		
		@Override
		@RosettaAttribute("dividendPayoutRatio")
		public List<? extends DividendPayoutRatio> getDividendPayoutRatio() {
			return dividendPayoutRatio;
		}
		
		@Override
		@RosettaAttribute("dividendReinvestment")
		public Boolean getDividendReinvestment() {
			return dividendReinvestment;
		}
		
		@Override
		@RosettaAttribute("dividendEntitlement")
		public DividendEntitlementEnum getDividendEntitlement() {
			return dividendEntitlement;
		}
		
		@Override
		@RosettaAttribute("dividendAmountType")
		public DividendAmountTypeEnum getDividendAmountType() {
			return dividendAmountType;
		}
		
		@Override
		@RosettaAttribute("performance")
		public String getPerformance() {
			return performance;
		}
		
		@Override
		@RosettaAttribute("firstOrSecondPeriod")
		public DividendPeriodEnum getFirstOrSecondPeriod() {
			return firstOrSecondPeriod;
		}
		
		@Override
		@RosettaAttribute("extraordinaryDividendsParty")
		public AncillaryRoleEnum getExtraordinaryDividendsParty() {
			return extraordinaryDividendsParty;
		}
		
		@Override
		@RosettaAttribute("excessDividendAmount")
		public DividendAmountTypeEnum getExcessDividendAmount() {
			return excessDividendAmount;
		}
		
		@Override
		@RosettaAttribute("dividendCurrency")
		public DividendCurrency getDividendCurrency() {
			return dividendCurrency;
		}
		
		@Override
		@RosettaAttribute("nonCashDividendTreatment")
		public NonCashDividendTreatmentEnum getNonCashDividendTreatment() {
			return nonCashDividendTreatment;
		}
		
		@Override
		@RosettaAttribute("dividendComposition")
		public DividendCompositionEnum getDividendComposition() {
			return dividendComposition;
		}
		
		@Override
		@RosettaAttribute("specialDividends")
		public Boolean getSpecialDividends() {
			return specialDividends;
		}
		
		@Override
		@RosettaAttribute("materialDividend")
		public Boolean getMaterialDividend() {
			return materialDividend;
		}
		
		@Override
		@RosettaAttribute("dividendPeriod")
		public List<? extends DividendPeriod> getDividendPeriod() {
			return dividendPeriod;
		}
		
		@Override
		public DividendReturnTerms build() {
			return this;
		}
		
		@Override
		public DividendReturnTerms.DividendReturnTermsBuilder toBuilder() {
			DividendReturnTerms.DividendReturnTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(DividendReturnTerms.DividendReturnTermsBuilder builder) {
			ofNullable(getDividendPayoutRatio()).ifPresent(builder::setDividendPayoutRatio);
			ofNullable(getDividendReinvestment()).ifPresent(builder::setDividendReinvestment);
			ofNullable(getDividendEntitlement()).ifPresent(builder::setDividendEntitlement);
			ofNullable(getDividendAmountType()).ifPresent(builder::setDividendAmountType);
			ofNullable(getPerformance()).ifPresent(builder::setPerformance);
			ofNullable(getFirstOrSecondPeriod()).ifPresent(builder::setFirstOrSecondPeriod);
			ofNullable(getExtraordinaryDividendsParty()).ifPresent(builder::setExtraordinaryDividendsParty);
			ofNullable(getExcessDividendAmount()).ifPresent(builder::setExcessDividendAmount);
			ofNullable(getDividendCurrency()).ifPresent(builder::setDividendCurrency);
			ofNullable(getNonCashDividendTreatment()).ifPresent(builder::setNonCashDividendTreatment);
			ofNullable(getDividendComposition()).ifPresent(builder::setDividendComposition);
			ofNullable(getSpecialDividends()).ifPresent(builder::setSpecialDividends);
			ofNullable(getMaterialDividend()).ifPresent(builder::setMaterialDividend);
			ofNullable(getDividendPeriod()).ifPresent(builder::setDividendPeriod);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendReturnTerms _that = getType().cast(o);
		
			if (!ListEquals.listEquals(dividendPayoutRatio, _that.getDividendPayoutRatio())) return false;
			if (!Objects.equals(dividendReinvestment, _that.getDividendReinvestment())) return false;
			if (!Objects.equals(dividendEntitlement, _that.getDividendEntitlement())) return false;
			if (!Objects.equals(dividendAmountType, _that.getDividendAmountType())) return false;
			if (!Objects.equals(performance, _that.getPerformance())) return false;
			if (!Objects.equals(firstOrSecondPeriod, _that.getFirstOrSecondPeriod())) return false;
			if (!Objects.equals(extraordinaryDividendsParty, _that.getExtraordinaryDividendsParty())) return false;
			if (!Objects.equals(excessDividendAmount, _that.getExcessDividendAmount())) return false;
			if (!Objects.equals(dividendCurrency, _that.getDividendCurrency())) return false;
			if (!Objects.equals(nonCashDividendTreatment, _that.getNonCashDividendTreatment())) return false;
			if (!Objects.equals(dividendComposition, _that.getDividendComposition())) return false;
			if (!Objects.equals(specialDividends, _that.getSpecialDividends())) return false;
			if (!Objects.equals(materialDividend, _that.getMaterialDividend())) return false;
			if (!ListEquals.listEquals(dividendPeriod, _that.getDividendPeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (dividendPayoutRatio != null ? dividendPayoutRatio.hashCode() : 0);
			_result = 31 * _result + (dividendReinvestment != null ? dividendReinvestment.hashCode() : 0);
			_result = 31 * _result + (dividendEntitlement != null ? dividendEntitlement.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (dividendAmountType != null ? dividendAmountType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (performance != null ? performance.hashCode() : 0);
			_result = 31 * _result + (firstOrSecondPeriod != null ? firstOrSecondPeriod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (extraordinaryDividendsParty != null ? extraordinaryDividendsParty.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (excessDividendAmount != null ? excessDividendAmount.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (dividendCurrency != null ? dividendCurrency.hashCode() : 0);
			_result = 31 * _result + (nonCashDividendTreatment != null ? nonCashDividendTreatment.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (dividendComposition != null ? dividendComposition.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (specialDividends != null ? specialDividends.hashCode() : 0);
			_result = 31 * _result + (materialDividend != null ? materialDividend.hashCode() : 0);
			_result = 31 * _result + (dividendPeriod != null ? dividendPeriod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendReturnTerms {" +
				"dividendPayoutRatio=" + this.dividendPayoutRatio + ", " +
				"dividendReinvestment=" + this.dividendReinvestment + ", " +
				"dividendEntitlement=" + this.dividendEntitlement + ", " +
				"dividendAmountType=" + this.dividendAmountType + ", " +
				"performance=" + this.performance + ", " +
				"firstOrSecondPeriod=" + this.firstOrSecondPeriod + ", " +
				"extraordinaryDividendsParty=" + this.extraordinaryDividendsParty + ", " +
				"excessDividendAmount=" + this.excessDividendAmount + ", " +
				"dividendCurrency=" + this.dividendCurrency + ", " +
				"nonCashDividendTreatment=" + this.nonCashDividendTreatment + ", " +
				"dividendComposition=" + this.dividendComposition + ", " +
				"specialDividends=" + this.specialDividends + ", " +
				"materialDividend=" + this.materialDividend + ", " +
				"dividendPeriod=" + this.dividendPeriod +
			'}';
		}
	}

	/*********************** Builder Implementation of DividendReturnTerms  ***********************/
	class DividendReturnTermsBuilderImpl implements DividendReturnTerms.DividendReturnTermsBuilder {
	
		protected List<DividendPayoutRatio.DividendPayoutRatioBuilder> dividendPayoutRatio = new ArrayList<>();
		protected Boolean dividendReinvestment;
		protected DividendEntitlementEnum dividendEntitlement;
		protected DividendAmountTypeEnum dividendAmountType;
		protected String performance;
		protected DividendPeriodEnum firstOrSecondPeriod;
		protected AncillaryRoleEnum extraordinaryDividendsParty;
		protected DividendAmountTypeEnum excessDividendAmount;
		protected DividendCurrency.DividendCurrencyBuilder dividendCurrency;
		protected NonCashDividendTreatmentEnum nonCashDividendTreatment;
		protected DividendCompositionEnum dividendComposition;
		protected Boolean specialDividends;
		protected Boolean materialDividend;
		protected List<DividendPeriod.DividendPeriodBuilder> dividendPeriod = new ArrayList<>();
	
		public DividendReturnTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("dividendPayoutRatio")
		public List<? extends DividendPayoutRatio.DividendPayoutRatioBuilder> getDividendPayoutRatio() {
			return dividendPayoutRatio;
		}
		
		public DividendPayoutRatio.DividendPayoutRatioBuilder getOrCreateDividendPayoutRatio(int _index) {
		
			if (dividendPayoutRatio==null) {
				this.dividendPayoutRatio = new ArrayList<>();
			}
			DividendPayoutRatio.DividendPayoutRatioBuilder result;
			return getIndex(dividendPayoutRatio, _index, () -> {
						DividendPayoutRatio.DividendPayoutRatioBuilder newDividendPayoutRatio = DividendPayoutRatio.builder();
						return newDividendPayoutRatio;
					});
		}
		
		@Override
		@RosettaAttribute("dividendReinvestment")
		public Boolean getDividendReinvestment() {
			return dividendReinvestment;
		}
		
		@Override
		@RosettaAttribute("dividendEntitlement")
		public DividendEntitlementEnum getDividendEntitlement() {
			return dividendEntitlement;
		}
		
		@Override
		@RosettaAttribute("dividendAmountType")
		public DividendAmountTypeEnum getDividendAmountType() {
			return dividendAmountType;
		}
		
		@Override
		@RosettaAttribute("performance")
		public String getPerformance() {
			return performance;
		}
		
		@Override
		@RosettaAttribute("firstOrSecondPeriod")
		public DividendPeriodEnum getFirstOrSecondPeriod() {
			return firstOrSecondPeriod;
		}
		
		@Override
		@RosettaAttribute("extraordinaryDividendsParty")
		public AncillaryRoleEnum getExtraordinaryDividendsParty() {
			return extraordinaryDividendsParty;
		}
		
		@Override
		@RosettaAttribute("excessDividendAmount")
		public DividendAmountTypeEnum getExcessDividendAmount() {
			return excessDividendAmount;
		}
		
		@Override
		@RosettaAttribute("dividendCurrency")
		public DividendCurrency.DividendCurrencyBuilder getDividendCurrency() {
			return dividendCurrency;
		}
		
		@Override
		public DividendCurrency.DividendCurrencyBuilder getOrCreateDividendCurrency() {
			DividendCurrency.DividendCurrencyBuilder result;
			if (dividendCurrency!=null) {
				result = dividendCurrency;
			}
			else {
				result = dividendCurrency = DividendCurrency.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("nonCashDividendTreatment")
		public NonCashDividendTreatmentEnum getNonCashDividendTreatment() {
			return nonCashDividendTreatment;
		}
		
		@Override
		@RosettaAttribute("dividendComposition")
		public DividendCompositionEnum getDividendComposition() {
			return dividendComposition;
		}
		
		@Override
		@RosettaAttribute("specialDividends")
		public Boolean getSpecialDividends() {
			return specialDividends;
		}
		
		@Override
		@RosettaAttribute("materialDividend")
		public Boolean getMaterialDividend() {
			return materialDividend;
		}
		
		@Override
		@RosettaAttribute("dividendPeriod")
		public List<? extends DividendPeriod.DividendPeriodBuilder> getDividendPeriod() {
			return dividendPeriod;
		}
		
		public DividendPeriod.DividendPeriodBuilder getOrCreateDividendPeriod(int _index) {
		
			if (dividendPeriod==null) {
				this.dividendPeriod = new ArrayList<>();
			}
			DividendPeriod.DividendPeriodBuilder result;
			return getIndex(dividendPeriod, _index, () -> {
						DividendPeriod.DividendPeriodBuilder newDividendPeriod = DividendPeriod.builder();
						return newDividendPeriod;
					});
		}
		
	
		@Override
		public DividendReturnTerms.DividendReturnTermsBuilder addDividendPayoutRatio(DividendPayoutRatio dividendPayoutRatio) {
			if (dividendPayoutRatio!=null) this.dividendPayoutRatio.add(dividendPayoutRatio.toBuilder());
			return this;
		}
		
		@Override
		public DividendReturnTerms.DividendReturnTermsBuilder addDividendPayoutRatio(DividendPayoutRatio dividendPayoutRatio, int _idx) {
			getIndex(this.dividendPayoutRatio, _idx, () -> dividendPayoutRatio.toBuilder());
			return this;
		}
		@Override 
		public DividendReturnTerms.DividendReturnTermsBuilder addDividendPayoutRatio(List<? extends DividendPayoutRatio> dividendPayoutRatios) {
			if (dividendPayoutRatios != null) {
				for (DividendPayoutRatio toAdd : dividendPayoutRatios) {
					this.dividendPayoutRatio.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("dividendPayoutRatio")
		public DividendReturnTerms.DividendReturnTermsBuilder setDividendPayoutRatio(List<? extends DividendPayoutRatio> dividendPayoutRatios) {
			if (dividendPayoutRatios == null)  {
				this.dividendPayoutRatio = new ArrayList<>();
			}
			else {
				this.dividendPayoutRatio = dividendPayoutRatios.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("dividendReinvestment")
		public DividendReturnTerms.DividendReturnTermsBuilder setDividendReinvestment(Boolean dividendReinvestment) {
			this.dividendReinvestment = dividendReinvestment==null?null:dividendReinvestment;
			return this;
		}
		@Override
		@RosettaAttribute("dividendEntitlement")
		public DividendReturnTerms.DividendReturnTermsBuilder setDividendEntitlement(DividendEntitlementEnum dividendEntitlement) {
			this.dividendEntitlement = dividendEntitlement==null?null:dividendEntitlement;
			return this;
		}
		@Override
		@RosettaAttribute("dividendAmountType")
		public DividendReturnTerms.DividendReturnTermsBuilder setDividendAmountType(DividendAmountTypeEnum dividendAmountType) {
			this.dividendAmountType = dividendAmountType==null?null:dividendAmountType;
			return this;
		}
		@Override
		@RosettaAttribute("performance")
		public DividendReturnTerms.DividendReturnTermsBuilder setPerformance(String performance) {
			this.performance = performance==null?null:performance;
			return this;
		}
		@Override
		@RosettaAttribute("firstOrSecondPeriod")
		public DividendReturnTerms.DividendReturnTermsBuilder setFirstOrSecondPeriod(DividendPeriodEnum firstOrSecondPeriod) {
			this.firstOrSecondPeriod = firstOrSecondPeriod==null?null:firstOrSecondPeriod;
			return this;
		}
		@Override
		@RosettaAttribute("extraordinaryDividendsParty")
		public DividendReturnTerms.DividendReturnTermsBuilder setExtraordinaryDividendsParty(AncillaryRoleEnum extraordinaryDividendsParty) {
			this.extraordinaryDividendsParty = extraordinaryDividendsParty==null?null:extraordinaryDividendsParty;
			return this;
		}
		@Override
		@RosettaAttribute("excessDividendAmount")
		public DividendReturnTerms.DividendReturnTermsBuilder setExcessDividendAmount(DividendAmountTypeEnum excessDividendAmount) {
			this.excessDividendAmount = excessDividendAmount==null?null:excessDividendAmount;
			return this;
		}
		@Override
		@RosettaAttribute("dividendCurrency")
		public DividendReturnTerms.DividendReturnTermsBuilder setDividendCurrency(DividendCurrency dividendCurrency) {
			this.dividendCurrency = dividendCurrency==null?null:dividendCurrency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("nonCashDividendTreatment")
		public DividendReturnTerms.DividendReturnTermsBuilder setNonCashDividendTreatment(NonCashDividendTreatmentEnum nonCashDividendTreatment) {
			this.nonCashDividendTreatment = nonCashDividendTreatment==null?null:nonCashDividendTreatment;
			return this;
		}
		@Override
		@RosettaAttribute("dividendComposition")
		public DividendReturnTerms.DividendReturnTermsBuilder setDividendComposition(DividendCompositionEnum dividendComposition) {
			this.dividendComposition = dividendComposition==null?null:dividendComposition;
			return this;
		}
		@Override
		@RosettaAttribute("specialDividends")
		public DividendReturnTerms.DividendReturnTermsBuilder setSpecialDividends(Boolean specialDividends) {
			this.specialDividends = specialDividends==null?null:specialDividends;
			return this;
		}
		@Override
		@RosettaAttribute("materialDividend")
		public DividendReturnTerms.DividendReturnTermsBuilder setMaterialDividend(Boolean materialDividend) {
			this.materialDividend = materialDividend==null?null:materialDividend;
			return this;
		}
		@Override
		public DividendReturnTerms.DividendReturnTermsBuilder addDividendPeriod(DividendPeriod dividendPeriod) {
			if (dividendPeriod!=null) this.dividendPeriod.add(dividendPeriod.toBuilder());
			return this;
		}
		
		@Override
		public DividendReturnTerms.DividendReturnTermsBuilder addDividendPeriod(DividendPeriod dividendPeriod, int _idx) {
			getIndex(this.dividendPeriod, _idx, () -> dividendPeriod.toBuilder());
			return this;
		}
		@Override 
		public DividendReturnTerms.DividendReturnTermsBuilder addDividendPeriod(List<? extends DividendPeriod> dividendPeriods) {
			if (dividendPeriods != null) {
				for (DividendPeriod toAdd : dividendPeriods) {
					this.dividendPeriod.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("dividendPeriod")
		public DividendReturnTerms.DividendReturnTermsBuilder setDividendPeriod(List<? extends DividendPeriod> dividendPeriods) {
			if (dividendPeriods == null)  {
				this.dividendPeriod = new ArrayList<>();
			}
			else {
				this.dividendPeriod = dividendPeriods.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		
		@Override
		public DividendReturnTerms build() {
			return new DividendReturnTerms.DividendReturnTermsImpl(this);
		}
		
		@Override
		public DividendReturnTerms.DividendReturnTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendReturnTerms.DividendReturnTermsBuilder prune() {
			dividendPayoutRatio = dividendPayoutRatio.stream().filter(b->b!=null).<DividendPayoutRatio.DividendPayoutRatioBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (dividendCurrency!=null && !dividendCurrency.prune().hasData()) dividendCurrency = null;
			dividendPeriod = dividendPeriod.stream().filter(b->b!=null).<DividendPeriod.DividendPeriodBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getDividendPayoutRatio()!=null && getDividendPayoutRatio().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getDividendReinvestment()!=null) return true;
			if (getDividendEntitlement()!=null) return true;
			if (getDividendAmountType()!=null) return true;
			if (getPerformance()!=null) return true;
			if (getFirstOrSecondPeriod()!=null) return true;
			if (getExtraordinaryDividendsParty()!=null) return true;
			if (getExcessDividendAmount()!=null) return true;
			if (getDividendCurrency()!=null && getDividendCurrency().hasData()) return true;
			if (getNonCashDividendTreatment()!=null) return true;
			if (getDividendComposition()!=null) return true;
			if (getSpecialDividends()!=null) return true;
			if (getMaterialDividend()!=null) return true;
			if (getDividendPeriod()!=null && getDividendPeriod().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public DividendReturnTerms.DividendReturnTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			DividendReturnTerms.DividendReturnTermsBuilder o = (DividendReturnTerms.DividendReturnTermsBuilder) other;
			
			merger.mergeRosetta(getDividendPayoutRatio(), o.getDividendPayoutRatio(), this::getOrCreateDividendPayoutRatio);
			merger.mergeRosetta(getDividendCurrency(), o.getDividendCurrency(), this::setDividendCurrency);
			merger.mergeRosetta(getDividendPeriod(), o.getDividendPeriod(), this::getOrCreateDividendPeriod);
			
			merger.mergeBasic(getDividendReinvestment(), o.getDividendReinvestment(), this::setDividendReinvestment);
			merger.mergeBasic(getDividendEntitlement(), o.getDividendEntitlement(), this::setDividendEntitlement);
			merger.mergeBasic(getDividendAmountType(), o.getDividendAmountType(), this::setDividendAmountType);
			merger.mergeBasic(getPerformance(), o.getPerformance(), this::setPerformance);
			merger.mergeBasic(getFirstOrSecondPeriod(), o.getFirstOrSecondPeriod(), this::setFirstOrSecondPeriod);
			merger.mergeBasic(getExtraordinaryDividendsParty(), o.getExtraordinaryDividendsParty(), this::setExtraordinaryDividendsParty);
			merger.mergeBasic(getExcessDividendAmount(), o.getExcessDividendAmount(), this::setExcessDividendAmount);
			merger.mergeBasic(getNonCashDividendTreatment(), o.getNonCashDividendTreatment(), this::setNonCashDividendTreatment);
			merger.mergeBasic(getDividendComposition(), o.getDividendComposition(), this::setDividendComposition);
			merger.mergeBasic(getSpecialDividends(), o.getSpecialDividends(), this::setSpecialDividends);
			merger.mergeBasic(getMaterialDividend(), o.getMaterialDividend(), this::setMaterialDividend);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			DividendReturnTerms _that = getType().cast(o);
		
			if (!ListEquals.listEquals(dividendPayoutRatio, _that.getDividendPayoutRatio())) return false;
			if (!Objects.equals(dividendReinvestment, _that.getDividendReinvestment())) return false;
			if (!Objects.equals(dividendEntitlement, _that.getDividendEntitlement())) return false;
			if (!Objects.equals(dividendAmountType, _that.getDividendAmountType())) return false;
			if (!Objects.equals(performance, _that.getPerformance())) return false;
			if (!Objects.equals(firstOrSecondPeriod, _that.getFirstOrSecondPeriod())) return false;
			if (!Objects.equals(extraordinaryDividendsParty, _that.getExtraordinaryDividendsParty())) return false;
			if (!Objects.equals(excessDividendAmount, _that.getExcessDividendAmount())) return false;
			if (!Objects.equals(dividendCurrency, _that.getDividendCurrency())) return false;
			if (!Objects.equals(nonCashDividendTreatment, _that.getNonCashDividendTreatment())) return false;
			if (!Objects.equals(dividendComposition, _that.getDividendComposition())) return false;
			if (!Objects.equals(specialDividends, _that.getSpecialDividends())) return false;
			if (!Objects.equals(materialDividend, _that.getMaterialDividend())) return false;
			if (!ListEquals.listEquals(dividendPeriod, _that.getDividendPeriod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (dividendPayoutRatio != null ? dividendPayoutRatio.hashCode() : 0);
			_result = 31 * _result + (dividendReinvestment != null ? dividendReinvestment.hashCode() : 0);
			_result = 31 * _result + (dividendEntitlement != null ? dividendEntitlement.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (dividendAmountType != null ? dividendAmountType.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (performance != null ? performance.hashCode() : 0);
			_result = 31 * _result + (firstOrSecondPeriod != null ? firstOrSecondPeriod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (extraordinaryDividendsParty != null ? extraordinaryDividendsParty.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (excessDividendAmount != null ? excessDividendAmount.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (dividendCurrency != null ? dividendCurrency.hashCode() : 0);
			_result = 31 * _result + (nonCashDividendTreatment != null ? nonCashDividendTreatment.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (dividendComposition != null ? dividendComposition.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (specialDividends != null ? specialDividends.hashCode() : 0);
			_result = 31 * _result + (materialDividend != null ? materialDividend.hashCode() : 0);
			_result = 31 * _result + (dividendPeriod != null ? dividendPeriod.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "DividendReturnTermsBuilder {" +
				"dividendPayoutRatio=" + this.dividendPayoutRatio + ", " +
				"dividendReinvestment=" + this.dividendReinvestment + ", " +
				"dividendEntitlement=" + this.dividendEntitlement + ", " +
				"dividendAmountType=" + this.dividendAmountType + ", " +
				"performance=" + this.performance + ", " +
				"firstOrSecondPeriod=" + this.firstOrSecondPeriod + ", " +
				"extraordinaryDividendsParty=" + this.extraordinaryDividendsParty + ", " +
				"excessDividendAmount=" + this.excessDividendAmount + ", " +
				"dividendCurrency=" + this.dividendCurrency + ", " +
				"nonCashDividendTreatment=" + this.nonCashDividendTreatment + ", " +
				"dividendComposition=" + this.dividendComposition + ", " +
				"specialDividends=" + this.specialDividends + ", " +
				"materialDividend=" + this.materialDividend + ", " +
				"dividendPeriod=" + this.dividendPeriod +
			'}';
		}
	}
}
