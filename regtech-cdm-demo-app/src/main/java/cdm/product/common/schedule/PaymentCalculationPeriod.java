package cdm.product.common.schedule;

import cdm.observable.asset.Money;
import cdm.product.common.schedule.CalculationPeriod;
import cdm.product.common.schedule.PaymentCalculationPeriod;
import cdm.product.common.schedule.PaymentCalculationPeriod.PaymentCalculationPeriodBuilder;
import cdm.product.common.schedule.PaymentCalculationPeriod.PaymentCalculationPeriodBuilderImpl;
import cdm.product.common.schedule.PaymentCalculationPeriod.PaymentCalculationPeriodImpl;
import cdm.product.common.schedule.meta.PaymentCalculationPeriodMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
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
import com.rosetta.util.ListEquals;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A data defining:  the adjusted payment date and associated calculation period parameters required to calculate the actual or projected payment amount. This data forms:  part of the cashflow representation of a swap stream.
 * @version ${project.version}
 */
@RosettaDataType(value="PaymentCalculationPeriod", builder=PaymentCalculationPeriod.PaymentCalculationPeriodBuilderImpl.class, version="${project.version}")
public interface PaymentCalculationPeriod extends RosettaModelObject, GlobalKey {

	PaymentCalculationPeriodMeta metaData = new PaymentCalculationPeriodMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The unadjusted payment date.
	 */
	Date getUnadjustedPaymentDate();
	/**
	 * The adjusted payment date. This date should already be adjusted for any applicable business day convention. This component is not intended for use in trade confirmation but may be specified to allow the fee structure to also serve as a cashflow type component.
	 */
	Date getAdjustedPaymentDate();
	/**
	 * The parameters used in the calculation of a fixed or floating rate calculation period amount. A list of calculation period elements may be ordered in the document by ascending start date. An FpML document which contains an unordered list of calculation periods is still regarded as a conformant document.
	 */
	List<? extends CalculationPeriod> getCalculationPeriod();
	/**
	 * A known fixed payment amount.
	 */
	Money getFixedPaymentAmount();
	/**
	 * A decimal value representing the discount factor used to calculate the present value of cash flow.
	 */
	BigDecimal getDiscountFactor();
	/**
	 * A monetary amount representing the forecast of the future value of the payment.
	 */
	Money getForecastPaymentAmount();
	/**
	 * A monetary amount representing the present value of the forecast payment.
	 */
	Money getPresentValueAmount();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	PaymentCalculationPeriod build();
	
	PaymentCalculationPeriod.PaymentCalculationPeriodBuilder toBuilder();
	
	static PaymentCalculationPeriod.PaymentCalculationPeriodBuilder builder() {
		return new PaymentCalculationPeriod.PaymentCalculationPeriodBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PaymentCalculationPeriod> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PaymentCalculationPeriod> getType() {
		return PaymentCalculationPeriod.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("unadjustedPaymentDate"), Date.class, getUnadjustedPaymentDate(), this);
		processor.processBasic(path.newSubPath("adjustedPaymentDate"), Date.class, getAdjustedPaymentDate(), this);
		processRosetta(path.newSubPath("calculationPeriod"), processor, CalculationPeriod.class, getCalculationPeriod());
		processRosetta(path.newSubPath("fixedPaymentAmount"), processor, Money.class, getFixedPaymentAmount());
		processor.processBasic(path.newSubPath("discountFactor"), BigDecimal.class, getDiscountFactor(), this);
		processRosetta(path.newSubPath("forecastPaymentAmount"), processor, Money.class, getForecastPaymentAmount());
		processRosetta(path.newSubPath("presentValueAmount"), processor, Money.class, getPresentValueAmount());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PaymentCalculationPeriodBuilder extends PaymentCalculationPeriod, RosettaModelObjectBuilder {
		CalculationPeriod.CalculationPeriodBuilder getOrCreateCalculationPeriod(int _index);
		List<? extends CalculationPeriod.CalculationPeriodBuilder> getCalculationPeriod();
		Money.MoneyBuilder getOrCreateFixedPaymentAmount();
		Money.MoneyBuilder getFixedPaymentAmount();
		Money.MoneyBuilder getOrCreateForecastPaymentAmount();
		Money.MoneyBuilder getForecastPaymentAmount();
		Money.MoneyBuilder getOrCreatePresentValueAmount();
		Money.MoneyBuilder getPresentValueAmount();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setUnadjustedPaymentDate(Date unadjustedPaymentDate);
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setAdjustedPaymentDate(Date adjustedPaymentDate);
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder addCalculationPeriod(CalculationPeriod calculationPeriod0);
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder addCalculationPeriod(CalculationPeriod calculationPeriod1, int _idx);
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder addCalculationPeriod(List<? extends CalculationPeriod> calculationPeriod2);
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setCalculationPeriod(List<? extends CalculationPeriod> calculationPeriod3);
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setFixedPaymentAmount(Money fixedPaymentAmount);
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setDiscountFactor(BigDecimal discountFactor);
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setForecastPaymentAmount(Money forecastPaymentAmount);
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setPresentValueAmount(Money presentValueAmount);
		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("unadjustedPaymentDate"), Date.class, getUnadjustedPaymentDate(), this);
			processor.processBasic(path.newSubPath("adjustedPaymentDate"), Date.class, getAdjustedPaymentDate(), this);
			processRosetta(path.newSubPath("calculationPeriod"), processor, CalculationPeriod.CalculationPeriodBuilder.class, getCalculationPeriod());
			processRosetta(path.newSubPath("fixedPaymentAmount"), processor, Money.MoneyBuilder.class, getFixedPaymentAmount());
			processor.processBasic(path.newSubPath("discountFactor"), BigDecimal.class, getDiscountFactor(), this);
			processRosetta(path.newSubPath("forecastPaymentAmount"), processor, Money.MoneyBuilder.class, getForecastPaymentAmount());
			processRosetta(path.newSubPath("presentValueAmount"), processor, Money.MoneyBuilder.class, getPresentValueAmount());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		PaymentCalculationPeriod.PaymentCalculationPeriodBuilder prune();
	}

	/*********************** Immutable Implementation of PaymentCalculationPeriod  ***********************/
	class PaymentCalculationPeriodImpl implements PaymentCalculationPeriod {
		private final Date unadjustedPaymentDate;
		private final Date adjustedPaymentDate;
		private final List<? extends CalculationPeriod> calculationPeriod;
		private final Money fixedPaymentAmount;
		private final BigDecimal discountFactor;
		private final Money forecastPaymentAmount;
		private final Money presentValueAmount;
		private final MetaFields meta;
		
		protected PaymentCalculationPeriodImpl(PaymentCalculationPeriod.PaymentCalculationPeriodBuilder builder) {
			this.unadjustedPaymentDate = builder.getUnadjustedPaymentDate();
			this.adjustedPaymentDate = builder.getAdjustedPaymentDate();
			this.calculationPeriod = ofNullable(builder.getCalculationPeriod()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.fixedPaymentAmount = ofNullable(builder.getFixedPaymentAmount()).map(f->f.build()).orElse(null);
			this.discountFactor = builder.getDiscountFactor();
			this.forecastPaymentAmount = ofNullable(builder.getForecastPaymentAmount()).map(f->f.build()).orElse(null);
			this.presentValueAmount = ofNullable(builder.getPresentValueAmount()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("unadjustedPaymentDate")
		public Date getUnadjustedPaymentDate() {
			return unadjustedPaymentDate;
		}
		
		@Override
		@RosettaAttribute("adjustedPaymentDate")
		public Date getAdjustedPaymentDate() {
			return adjustedPaymentDate;
		}
		
		@Override
		@RosettaAttribute("calculationPeriod")
		public List<? extends CalculationPeriod> getCalculationPeriod() {
			return calculationPeriod;
		}
		
		@Override
		@RosettaAttribute("fixedPaymentAmount")
		public Money getFixedPaymentAmount() {
			return fixedPaymentAmount;
		}
		
		@Override
		@RosettaAttribute("discountFactor")
		public BigDecimal getDiscountFactor() {
			return discountFactor;
		}
		
		@Override
		@RosettaAttribute("forecastPaymentAmount")
		public Money getForecastPaymentAmount() {
			return forecastPaymentAmount;
		}
		
		@Override
		@RosettaAttribute("presentValueAmount")
		public Money getPresentValueAmount() {
			return presentValueAmount;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public PaymentCalculationPeriod build() {
			return this;
		}
		
		@Override
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder toBuilder() {
			PaymentCalculationPeriod.PaymentCalculationPeriodBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PaymentCalculationPeriod.PaymentCalculationPeriodBuilder builder) {
			ofNullable(getUnadjustedPaymentDate()).ifPresent(builder::setUnadjustedPaymentDate);
			ofNullable(getAdjustedPaymentDate()).ifPresent(builder::setAdjustedPaymentDate);
			ofNullable(getCalculationPeriod()).ifPresent(builder::setCalculationPeriod);
			ofNullable(getFixedPaymentAmount()).ifPresent(builder::setFixedPaymentAmount);
			ofNullable(getDiscountFactor()).ifPresent(builder::setDiscountFactor);
			ofNullable(getForecastPaymentAmount()).ifPresent(builder::setForecastPaymentAmount);
			ofNullable(getPresentValueAmount()).ifPresent(builder::setPresentValueAmount);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentCalculationPeriod _that = getType().cast(o);
		
			if (!Objects.equals(unadjustedPaymentDate, _that.getUnadjustedPaymentDate())) return false;
			if (!Objects.equals(adjustedPaymentDate, _that.getAdjustedPaymentDate())) return false;
			if (!ListEquals.listEquals(calculationPeriod, _that.getCalculationPeriod())) return false;
			if (!Objects.equals(fixedPaymentAmount, _that.getFixedPaymentAmount())) return false;
			if (!Objects.equals(discountFactor, _that.getDiscountFactor())) return false;
			if (!Objects.equals(forecastPaymentAmount, _that.getForecastPaymentAmount())) return false;
			if (!Objects.equals(presentValueAmount, _that.getPresentValueAmount())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (unadjustedPaymentDate != null ? unadjustedPaymentDate.hashCode() : 0);
			_result = 31 * _result + (adjustedPaymentDate != null ? adjustedPaymentDate.hashCode() : 0);
			_result = 31 * _result + (calculationPeriod != null ? calculationPeriod.hashCode() : 0);
			_result = 31 * _result + (fixedPaymentAmount != null ? fixedPaymentAmount.hashCode() : 0);
			_result = 31 * _result + (discountFactor != null ? discountFactor.hashCode() : 0);
			_result = 31 * _result + (forecastPaymentAmount != null ? forecastPaymentAmount.hashCode() : 0);
			_result = 31 * _result + (presentValueAmount != null ? presentValueAmount.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentCalculationPeriod {" +
				"unadjustedPaymentDate=" + this.unadjustedPaymentDate + ", " +
				"adjustedPaymentDate=" + this.adjustedPaymentDate + ", " +
				"calculationPeriod=" + this.calculationPeriod + ", " +
				"fixedPaymentAmount=" + this.fixedPaymentAmount + ", " +
				"discountFactor=" + this.discountFactor + ", " +
				"forecastPaymentAmount=" + this.forecastPaymentAmount + ", " +
				"presentValueAmount=" + this.presentValueAmount + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of PaymentCalculationPeriod  ***********************/
	class PaymentCalculationPeriodBuilderImpl implements PaymentCalculationPeriod.PaymentCalculationPeriodBuilder, GlobalKeyBuilder {
	
		protected Date unadjustedPaymentDate;
		protected Date adjustedPaymentDate;
		protected List<CalculationPeriod.CalculationPeriodBuilder> calculationPeriod = new ArrayList<>();
		protected Money.MoneyBuilder fixedPaymentAmount;
		protected BigDecimal discountFactor;
		protected Money.MoneyBuilder forecastPaymentAmount;
		protected Money.MoneyBuilder presentValueAmount;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public PaymentCalculationPeriodBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("unadjustedPaymentDate")
		public Date getUnadjustedPaymentDate() {
			return unadjustedPaymentDate;
		}
		
		@Override
		@RosettaAttribute("adjustedPaymentDate")
		public Date getAdjustedPaymentDate() {
			return adjustedPaymentDate;
		}
		
		@Override
		@RosettaAttribute("calculationPeriod")
		public List<? extends CalculationPeriod.CalculationPeriodBuilder> getCalculationPeriod() {
			return calculationPeriod;
		}
		
		public CalculationPeriod.CalculationPeriodBuilder getOrCreateCalculationPeriod(int _index) {
		
			if (calculationPeriod==null) {
				this.calculationPeriod = new ArrayList<>();
			}
			CalculationPeriod.CalculationPeriodBuilder result;
			return getIndex(calculationPeriod, _index, () -> {
						CalculationPeriod.CalculationPeriodBuilder newCalculationPeriod = CalculationPeriod.builder();
						return newCalculationPeriod;
					});
		}
		
		@Override
		@RosettaAttribute("fixedPaymentAmount")
		public Money.MoneyBuilder getFixedPaymentAmount() {
			return fixedPaymentAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateFixedPaymentAmount() {
			Money.MoneyBuilder result;
			if (fixedPaymentAmount!=null) {
				result = fixedPaymentAmount;
			}
			else {
				result = fixedPaymentAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("discountFactor")
		public BigDecimal getDiscountFactor() {
			return discountFactor;
		}
		
		@Override
		@RosettaAttribute("forecastPaymentAmount")
		public Money.MoneyBuilder getForecastPaymentAmount() {
			return forecastPaymentAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateForecastPaymentAmount() {
			Money.MoneyBuilder result;
			if (forecastPaymentAmount!=null) {
				result = forecastPaymentAmount;
			}
			else {
				result = forecastPaymentAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("presentValueAmount")
		public Money.MoneyBuilder getPresentValueAmount() {
			return presentValueAmount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreatePresentValueAmount() {
			Money.MoneyBuilder result;
			if (presentValueAmount!=null) {
				result = presentValueAmount;
			}
			else {
				result = presentValueAmount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("unadjustedPaymentDate")
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setUnadjustedPaymentDate(Date unadjustedPaymentDate) {
			this.unadjustedPaymentDate = unadjustedPaymentDate==null?null:unadjustedPaymentDate;
			return this;
		}
		@Override
		@RosettaAttribute("adjustedPaymentDate")
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setAdjustedPaymentDate(Date adjustedPaymentDate) {
			this.adjustedPaymentDate = adjustedPaymentDate==null?null:adjustedPaymentDate;
			return this;
		}
		@Override
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder addCalculationPeriod(CalculationPeriod calculationPeriod) {
			if (calculationPeriod!=null) this.calculationPeriod.add(calculationPeriod.toBuilder());
			return this;
		}
		
		@Override
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder addCalculationPeriod(CalculationPeriod calculationPeriod, int _idx) {
			getIndex(this.calculationPeriod, _idx, () -> calculationPeriod.toBuilder());
			return this;
		}
		@Override 
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder addCalculationPeriod(List<? extends CalculationPeriod> calculationPeriods) {
			if (calculationPeriods != null) {
				for (CalculationPeriod toAdd : calculationPeriods) {
					this.calculationPeriod.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("calculationPeriod")
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setCalculationPeriod(List<? extends CalculationPeriod> calculationPeriods) {
			if (calculationPeriods == null)  {
				this.calculationPeriod = new ArrayList<>();
			}
			else {
				this.calculationPeriod = calculationPeriods.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("fixedPaymentAmount")
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setFixedPaymentAmount(Money fixedPaymentAmount) {
			this.fixedPaymentAmount = fixedPaymentAmount==null?null:fixedPaymentAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("discountFactor")
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setDiscountFactor(BigDecimal discountFactor) {
			this.discountFactor = discountFactor==null?null:discountFactor;
			return this;
		}
		@Override
		@RosettaAttribute("forecastPaymentAmount")
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setForecastPaymentAmount(Money forecastPaymentAmount) {
			this.forecastPaymentAmount = forecastPaymentAmount==null?null:forecastPaymentAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("presentValueAmount")
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setPresentValueAmount(Money presentValueAmount) {
			this.presentValueAmount = presentValueAmount==null?null:presentValueAmount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public PaymentCalculationPeriod build() {
			return new PaymentCalculationPeriod.PaymentCalculationPeriodImpl(this);
		}
		
		@Override
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder prune() {
			calculationPeriod = calculationPeriod.stream().filter(b->b!=null).<CalculationPeriod.CalculationPeriodBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (fixedPaymentAmount!=null && !fixedPaymentAmount.prune().hasData()) fixedPaymentAmount = null;
			if (forecastPaymentAmount!=null && !forecastPaymentAmount.prune().hasData()) forecastPaymentAmount = null;
			if (presentValueAmount!=null && !presentValueAmount.prune().hasData()) presentValueAmount = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getUnadjustedPaymentDate()!=null) return true;
			if (getAdjustedPaymentDate()!=null) return true;
			if (getCalculationPeriod()!=null && getCalculationPeriod().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getFixedPaymentAmount()!=null && getFixedPaymentAmount().hasData()) return true;
			if (getDiscountFactor()!=null) return true;
			if (getForecastPaymentAmount()!=null && getForecastPaymentAmount().hasData()) return true;
			if (getPresentValueAmount()!=null && getPresentValueAmount().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PaymentCalculationPeriod.PaymentCalculationPeriodBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PaymentCalculationPeriod.PaymentCalculationPeriodBuilder o = (PaymentCalculationPeriod.PaymentCalculationPeriodBuilder) other;
			
			merger.mergeRosetta(getCalculationPeriod(), o.getCalculationPeriod(), this::getOrCreateCalculationPeriod);
			merger.mergeRosetta(getFixedPaymentAmount(), o.getFixedPaymentAmount(), this::setFixedPaymentAmount);
			merger.mergeRosetta(getForecastPaymentAmount(), o.getForecastPaymentAmount(), this::setForecastPaymentAmount);
			merger.mergeRosetta(getPresentValueAmount(), o.getPresentValueAmount(), this::setPresentValueAmount);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getUnadjustedPaymentDate(), o.getUnadjustedPaymentDate(), this::setUnadjustedPaymentDate);
			merger.mergeBasic(getAdjustedPaymentDate(), o.getAdjustedPaymentDate(), this::setAdjustedPaymentDate);
			merger.mergeBasic(getDiscountFactor(), o.getDiscountFactor(), this::setDiscountFactor);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PaymentCalculationPeriod _that = getType().cast(o);
		
			if (!Objects.equals(unadjustedPaymentDate, _that.getUnadjustedPaymentDate())) return false;
			if (!Objects.equals(adjustedPaymentDate, _that.getAdjustedPaymentDate())) return false;
			if (!ListEquals.listEquals(calculationPeriod, _that.getCalculationPeriod())) return false;
			if (!Objects.equals(fixedPaymentAmount, _that.getFixedPaymentAmount())) return false;
			if (!Objects.equals(discountFactor, _that.getDiscountFactor())) return false;
			if (!Objects.equals(forecastPaymentAmount, _that.getForecastPaymentAmount())) return false;
			if (!Objects.equals(presentValueAmount, _that.getPresentValueAmount())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (unadjustedPaymentDate != null ? unadjustedPaymentDate.hashCode() : 0);
			_result = 31 * _result + (adjustedPaymentDate != null ? adjustedPaymentDate.hashCode() : 0);
			_result = 31 * _result + (calculationPeriod != null ? calculationPeriod.hashCode() : 0);
			_result = 31 * _result + (fixedPaymentAmount != null ? fixedPaymentAmount.hashCode() : 0);
			_result = 31 * _result + (discountFactor != null ? discountFactor.hashCode() : 0);
			_result = 31 * _result + (forecastPaymentAmount != null ? forecastPaymentAmount.hashCode() : 0);
			_result = 31 * _result + (presentValueAmount != null ? presentValueAmount.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PaymentCalculationPeriodBuilder {" +
				"unadjustedPaymentDate=" + this.unadjustedPaymentDate + ", " +
				"adjustedPaymentDate=" + this.adjustedPaymentDate + ", " +
				"calculationPeriod=" + this.calculationPeriod + ", " +
				"fixedPaymentAmount=" + this.fixedPaymentAmount + ", " +
				"discountFactor=" + this.discountFactor + ", " +
				"forecastPaymentAmount=" + this.forecastPaymentAmount + ", " +
				"presentValueAmount=" + this.presentValueAmount + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
