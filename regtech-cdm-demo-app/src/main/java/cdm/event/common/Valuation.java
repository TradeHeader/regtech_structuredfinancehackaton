package cdm.event.common;

import cdm.event.common.PriceTimingEnum;
import cdm.event.common.Valuation;
import cdm.event.common.Valuation.ValuationBuilder;
import cdm.event.common.Valuation.ValuationBuilderImpl;
import cdm.event.common.Valuation.ValuationImpl;
import cdm.event.common.ValuationSourceEnum;
import cdm.event.common.ValuationTypeEnum;
import cdm.event.common.meta.ValuationMeta;
import cdm.observable.asset.Money;
import cdm.observable.asset.Price;
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
import java.time.ZonedDateTime;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Defines the value of an investment, asset, or security
 * @version ${project.version}
 */
@RosettaDataType(value="Valuation", builder=Valuation.ValuationBuilderImpl.class, version="${project.version}")
public interface Valuation extends RosettaModelObject {

	ValuationMeta metaData = new ValuationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Current value of the outstanding contract
	 */
	Money getAmount();
	/**
	 * Date and time of the last valuation marked to market, provided by the central counterparty (CCP) or calculated using the current or last available market price of the inputs.
	 */
	ZonedDateTime getTimestamp();
	/**
	 * Method used for the valuation of the transaction by the valuation party.
	 */
	ValuationTypeEnum getMethod();
	/**
	 * Source of the valuation of the transaction by the valuation party.
	 */
	ValuationSourceEnum getSource();
	/**
	 * The ratio of the change in the price of a derivative transaction to the change in the price of the underlying. This field is applicable only to options and swaptions.
	 */
	BigDecimal getDelta();
	/**
	 * Denotes when the valuation was sourced during a business day.
	 */
	PriceTimingEnum getValuationTiming();
	/**
	 * Denotes the price used to compute the valuation.
	 */
	Price getPriceComponent();

	/*********************** Build Methods  ***********************/
	Valuation build();
	
	Valuation.ValuationBuilder toBuilder();
	
	static Valuation.ValuationBuilder builder() {
		return new Valuation.ValuationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends Valuation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends Valuation> getType() {
		return Valuation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("amount"), processor, Money.class, getAmount());
		processor.processBasic(path.newSubPath("timestamp"), ZonedDateTime.class, getTimestamp(), this);
		processor.processBasic(path.newSubPath("method"), ValuationTypeEnum.class, getMethod(), this);
		processor.processBasic(path.newSubPath("source"), ValuationSourceEnum.class, getSource(), this);
		processor.processBasic(path.newSubPath("delta"), BigDecimal.class, getDelta(), this);
		processor.processBasic(path.newSubPath("valuationTiming"), PriceTimingEnum.class, getValuationTiming(), this);
		processRosetta(path.newSubPath("priceComponent"), processor, Price.class, getPriceComponent());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ValuationBuilder extends Valuation, RosettaModelObjectBuilder {
		Money.MoneyBuilder getOrCreateAmount();
		Money.MoneyBuilder getAmount();
		Price.PriceBuilder getOrCreatePriceComponent();
		Price.PriceBuilder getPriceComponent();
		Valuation.ValuationBuilder setAmount(Money amount);
		Valuation.ValuationBuilder setTimestamp(ZonedDateTime timestamp);
		Valuation.ValuationBuilder setMethod(ValuationTypeEnum method);
		Valuation.ValuationBuilder setSource(ValuationSourceEnum source);
		Valuation.ValuationBuilder setDelta(BigDecimal delta);
		Valuation.ValuationBuilder setValuationTiming(PriceTimingEnum valuationTiming);
		Valuation.ValuationBuilder setPriceComponent(Price priceComponent);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("amount"), processor, Money.MoneyBuilder.class, getAmount());
			processor.processBasic(path.newSubPath("timestamp"), ZonedDateTime.class, getTimestamp(), this);
			processor.processBasic(path.newSubPath("method"), ValuationTypeEnum.class, getMethod(), this);
			processor.processBasic(path.newSubPath("source"), ValuationSourceEnum.class, getSource(), this);
			processor.processBasic(path.newSubPath("delta"), BigDecimal.class, getDelta(), this);
			processor.processBasic(path.newSubPath("valuationTiming"), PriceTimingEnum.class, getValuationTiming(), this);
			processRosetta(path.newSubPath("priceComponent"), processor, Price.PriceBuilder.class, getPriceComponent());
		}
		

		Valuation.ValuationBuilder prune();
	}

	/*********************** Immutable Implementation of Valuation  ***********************/
	class ValuationImpl implements Valuation {
		private final Money amount;
		private final ZonedDateTime timestamp;
		private final ValuationTypeEnum method;
		private final ValuationSourceEnum source;
		private final BigDecimal delta;
		private final PriceTimingEnum valuationTiming;
		private final Price priceComponent;
		
		protected ValuationImpl(Valuation.ValuationBuilder builder) {
			this.amount = ofNullable(builder.getAmount()).map(f->f.build()).orElse(null);
			this.timestamp = builder.getTimestamp();
			this.method = builder.getMethod();
			this.source = builder.getSource();
			this.delta = builder.getDelta();
			this.valuationTiming = builder.getValuationTiming();
			this.priceComponent = ofNullable(builder.getPriceComponent()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("amount")
		public Money getAmount() {
			return amount;
		}
		
		@Override
		@RosettaAttribute("timestamp")
		public ZonedDateTime getTimestamp() {
			return timestamp;
		}
		
		@Override
		@RosettaAttribute("method")
		public ValuationTypeEnum getMethod() {
			return method;
		}
		
		@Override
		@RosettaAttribute("source")
		public ValuationSourceEnum getSource() {
			return source;
		}
		
		@Override
		@RosettaAttribute("delta")
		public BigDecimal getDelta() {
			return delta;
		}
		
		@Override
		@RosettaAttribute("valuationTiming")
		public PriceTimingEnum getValuationTiming() {
			return valuationTiming;
		}
		
		@Override
		@RosettaAttribute("priceComponent")
		public Price getPriceComponent() {
			return priceComponent;
		}
		
		@Override
		public Valuation build() {
			return this;
		}
		
		@Override
		public Valuation.ValuationBuilder toBuilder() {
			Valuation.ValuationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(Valuation.ValuationBuilder builder) {
			ofNullable(getAmount()).ifPresent(builder::setAmount);
			ofNullable(getTimestamp()).ifPresent(builder::setTimestamp);
			ofNullable(getMethod()).ifPresent(builder::setMethod);
			ofNullable(getSource()).ifPresent(builder::setSource);
			ofNullable(getDelta()).ifPresent(builder::setDelta);
			ofNullable(getValuationTiming()).ifPresent(builder::setValuationTiming);
			ofNullable(getPriceComponent()).ifPresent(builder::setPriceComponent);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Valuation _that = getType().cast(o);
		
			if (!Objects.equals(amount, _that.getAmount())) return false;
			if (!Objects.equals(timestamp, _that.getTimestamp())) return false;
			if (!Objects.equals(method, _that.getMethod())) return false;
			if (!Objects.equals(source, _that.getSource())) return false;
			if (!Objects.equals(delta, _that.getDelta())) return false;
			if (!Objects.equals(valuationTiming, _that.getValuationTiming())) return false;
			if (!Objects.equals(priceComponent, _that.getPriceComponent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (amount != null ? amount.hashCode() : 0);
			_result = 31 * _result + (timestamp != null ? timestamp.hashCode() : 0);
			_result = 31 * _result + (method != null ? method.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (source != null ? source.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (delta != null ? delta.hashCode() : 0);
			_result = 31 * _result + (valuationTiming != null ? valuationTiming.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (priceComponent != null ? priceComponent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "Valuation {" +
				"amount=" + this.amount + ", " +
				"timestamp=" + this.timestamp + ", " +
				"method=" + this.method + ", " +
				"source=" + this.source + ", " +
				"delta=" + this.delta + ", " +
				"valuationTiming=" + this.valuationTiming + ", " +
				"priceComponent=" + this.priceComponent +
			'}';
		}
	}

	/*********************** Builder Implementation of Valuation  ***********************/
	class ValuationBuilderImpl implements Valuation.ValuationBuilder {
	
		protected Money.MoneyBuilder amount;
		protected ZonedDateTime timestamp;
		protected ValuationTypeEnum method;
		protected ValuationSourceEnum source;
		protected BigDecimal delta;
		protected PriceTimingEnum valuationTiming;
		protected Price.PriceBuilder priceComponent;
	
		public ValuationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("amount")
		public Money.MoneyBuilder getAmount() {
			return amount;
		}
		
		@Override
		public Money.MoneyBuilder getOrCreateAmount() {
			Money.MoneyBuilder result;
			if (amount!=null) {
				result = amount;
			}
			else {
				result = amount = Money.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("timestamp")
		public ZonedDateTime getTimestamp() {
			return timestamp;
		}
		
		@Override
		@RosettaAttribute("method")
		public ValuationTypeEnum getMethod() {
			return method;
		}
		
		@Override
		@RosettaAttribute("source")
		public ValuationSourceEnum getSource() {
			return source;
		}
		
		@Override
		@RosettaAttribute("delta")
		public BigDecimal getDelta() {
			return delta;
		}
		
		@Override
		@RosettaAttribute("valuationTiming")
		public PriceTimingEnum getValuationTiming() {
			return valuationTiming;
		}
		
		@Override
		@RosettaAttribute("priceComponent")
		public Price.PriceBuilder getPriceComponent() {
			return priceComponent;
		}
		
		@Override
		public Price.PriceBuilder getOrCreatePriceComponent() {
			Price.PriceBuilder result;
			if (priceComponent!=null) {
				result = priceComponent;
			}
			else {
				result = priceComponent = Price.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("amount")
		public Valuation.ValuationBuilder setAmount(Money amount) {
			this.amount = amount==null?null:amount.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("timestamp")
		public Valuation.ValuationBuilder setTimestamp(ZonedDateTime timestamp) {
			this.timestamp = timestamp==null?null:timestamp;
			return this;
		}
		@Override
		@RosettaAttribute("method")
		public Valuation.ValuationBuilder setMethod(ValuationTypeEnum method) {
			this.method = method==null?null:method;
			return this;
		}
		@Override
		@RosettaAttribute("source")
		public Valuation.ValuationBuilder setSource(ValuationSourceEnum source) {
			this.source = source==null?null:source;
			return this;
		}
		@Override
		@RosettaAttribute("delta")
		public Valuation.ValuationBuilder setDelta(BigDecimal delta) {
			this.delta = delta==null?null:delta;
			return this;
		}
		@Override
		@RosettaAttribute("valuationTiming")
		public Valuation.ValuationBuilder setValuationTiming(PriceTimingEnum valuationTiming) {
			this.valuationTiming = valuationTiming==null?null:valuationTiming;
			return this;
		}
		@Override
		@RosettaAttribute("priceComponent")
		public Valuation.ValuationBuilder setPriceComponent(Price priceComponent) {
			this.priceComponent = priceComponent==null?null:priceComponent.toBuilder();
			return this;
		}
		
		@Override
		public Valuation build() {
			return new Valuation.ValuationImpl(this);
		}
		
		@Override
		public Valuation.ValuationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Valuation.ValuationBuilder prune() {
			if (amount!=null && !amount.prune().hasData()) amount = null;
			if (priceComponent!=null && !priceComponent.prune().hasData()) priceComponent = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAmount()!=null && getAmount().hasData()) return true;
			if (getTimestamp()!=null) return true;
			if (getMethod()!=null) return true;
			if (getSource()!=null) return true;
			if (getDelta()!=null) return true;
			if (getValuationTiming()!=null) return true;
			if (getPriceComponent()!=null && getPriceComponent().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public Valuation.ValuationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			Valuation.ValuationBuilder o = (Valuation.ValuationBuilder) other;
			
			merger.mergeRosetta(getAmount(), o.getAmount(), this::setAmount);
			merger.mergeRosetta(getPriceComponent(), o.getPriceComponent(), this::setPriceComponent);
			
			merger.mergeBasic(getTimestamp(), o.getTimestamp(), this::setTimestamp);
			merger.mergeBasic(getMethod(), o.getMethod(), this::setMethod);
			merger.mergeBasic(getSource(), o.getSource(), this::setSource);
			merger.mergeBasic(getDelta(), o.getDelta(), this::setDelta);
			merger.mergeBasic(getValuationTiming(), o.getValuationTiming(), this::setValuationTiming);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			Valuation _that = getType().cast(o);
		
			if (!Objects.equals(amount, _that.getAmount())) return false;
			if (!Objects.equals(timestamp, _that.getTimestamp())) return false;
			if (!Objects.equals(method, _that.getMethod())) return false;
			if (!Objects.equals(source, _that.getSource())) return false;
			if (!Objects.equals(delta, _that.getDelta())) return false;
			if (!Objects.equals(valuationTiming, _that.getValuationTiming())) return false;
			if (!Objects.equals(priceComponent, _that.getPriceComponent())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (amount != null ? amount.hashCode() : 0);
			_result = 31 * _result + (timestamp != null ? timestamp.hashCode() : 0);
			_result = 31 * _result + (method != null ? method.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (source != null ? source.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (delta != null ? delta.hashCode() : 0);
			_result = 31 * _result + (valuationTiming != null ? valuationTiming.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (priceComponent != null ? priceComponent.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ValuationBuilder {" +
				"amount=" + this.amount + ", " +
				"timestamp=" + this.timestamp + ", " +
				"method=" + this.method + ", " +
				"source=" + this.source + ", " +
				"delta=" + this.delta + ", " +
				"valuationTiming=" + this.valuationTiming + ", " +
				"priceComponent=" + this.priceComponent +
			'}';
		}
	}
}
