package cdm.product.template;

import cdm.base.math.NonNegativeQuantitySchedule;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule;
import cdm.base.math.metafields.ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder;
import cdm.product.asset.CorrelationReturnTerms;
import cdm.product.asset.DividendReturnTerms;
import cdm.product.asset.PriceReturnTerms;
import cdm.product.asset.VarianceReturnTerms;
import cdm.product.asset.VolatilityReturnTerms;
import cdm.product.template.PortfolioReturnTerms;
import cdm.product.template.PortfolioReturnTerms.PortfolioReturnTermsBuilder;
import cdm.product.template.PortfolioReturnTerms.PortfolioReturnTermsBuilderImpl;
import cdm.product.template.PortfolioReturnTerms.PortfolioReturnTermsImpl;
import cdm.product.template.Product;
import cdm.product.template.ReturnTerms;
import cdm.product.template.ReturnTerms.ReturnTermsBuilder;
import cdm.product.template.ReturnTerms.ReturnTermsBuilderImpl;
import cdm.product.template.ReturnTerms.ReturnTermsImpl;
import cdm.product.template.meta.PortfolioReturnTermsMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specifies an individual type of return of a Performance Payout, when such individual return is part of an aggregation of multiple similar returns, at Performance Payout level.
 * @version ${project.version}
 */
@RosettaDataType(value="PortfolioReturnTerms", builder=PortfolioReturnTerms.PortfolioReturnTermsBuilderImpl.class, version="${project.version}")
public interface PortfolioReturnTerms extends ReturnTerms {

	PortfolioReturnTermsMeta metaData = new PortfolioReturnTermsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Canonical representation of the payer and receiver parties applicable to each individual return leg.
	 */
	PayerReceiver getPayerReceiver();
	/**
	 * Defines the product that is the subject of a tradable product definition, an underlying product definition, a physical exercise, a position, or other purposes.
	 */
	Product getUnderlier();
	/**
	 * Notional quantity of the underlier that applies to each individual return leg.
	 */
	ReferenceWithMetaNonNegativeQuantitySchedule getQuantity();
	/**
	 * Specifies the initial valuation price(s) of the underlier. This price can be expressed either as an actual amount/currency, as a determination method, or by reference to another value specified in the swap document.
	 */
	ReferenceWithMetaPriceSchedule getInitialValuationPrice();
	/**
	 * Specifies the initial valuation price(s) of the underlier. This price can be expressed either as an actual amount/currency, as a determination method, or by reference to another value specified in the swap document.
	 */
	ReferenceWithMetaPriceSchedule getInterimValuationPrice();
	/**
	 * 2018 ISDA CDM Equity Confirmation for Security Equity Swap: Final Price | Specifies the final valuation price of the underlier. This price can be expressed either as an actual amount/currency, as a determination method, or by reference to another value specified in the swap document.
	 */
	ReferenceWithMetaPriceSchedule getFinalValuationPrice();

	/*********************** Build Methods  ***********************/
	PortfolioReturnTerms build();
	
	PortfolioReturnTerms.PortfolioReturnTermsBuilder toBuilder();
	
	static PortfolioReturnTerms.PortfolioReturnTermsBuilder builder() {
		return new PortfolioReturnTerms.PortfolioReturnTermsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PortfolioReturnTerms> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PortfolioReturnTerms> getType() {
		return PortfolioReturnTerms.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("priceReturnTerms"), processor, PriceReturnTerms.class, getPriceReturnTerms());
		processRosetta(path.newSubPath("dividendReturnTerms"), processor, DividendReturnTerms.class, getDividendReturnTerms());
		processRosetta(path.newSubPath("varianceReturnTerms"), processor, VarianceReturnTerms.class, getVarianceReturnTerms());
		processRosetta(path.newSubPath("volatilityReturnTerms"), processor, VolatilityReturnTerms.class, getVolatilityReturnTerms());
		processRosetta(path.newSubPath("correlationReturnTerms"), processor, CorrelationReturnTerms.class, getCorrelationReturnTerms());
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("underlier"), processor, Product.class, getUnderlier());
		processRosetta(path.newSubPath("quantity"), processor, ReferenceWithMetaNonNegativeQuantitySchedule.class, getQuantity());
		processRosetta(path.newSubPath("initialValuationPrice"), processor, ReferenceWithMetaPriceSchedule.class, getInitialValuationPrice());
		processRosetta(path.newSubPath("interimValuationPrice"), processor, ReferenceWithMetaPriceSchedule.class, getInterimValuationPrice());
		processRosetta(path.newSubPath("finalValuationPrice"), processor, ReferenceWithMetaPriceSchedule.class, getFinalValuationPrice());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PortfolioReturnTermsBuilder extends PortfolioReturnTerms, ReturnTerms.ReturnTermsBuilder, RosettaModelObjectBuilder {
		PayerReceiver.PayerReceiverBuilder getOrCreatePayerReceiver();
		PayerReceiver.PayerReceiverBuilder getPayerReceiver();
		Product.ProductBuilder getOrCreateUnderlier();
		Product.ProductBuilder getUnderlier();
		ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getOrCreateQuantity();
		ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder getQuantity();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInitialValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getInitialValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInterimValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getInterimValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateFinalValuationPrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getFinalValuationPrice();
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setPriceReturnTerms(PriceReturnTerms priceReturnTerms);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setDividendReturnTerms(DividendReturnTerms dividendReturnTerms);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setVarianceReturnTerms(VarianceReturnTerms varianceReturnTerms);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setVolatilityReturnTerms(VolatilityReturnTerms volatilityReturnTerms);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setCorrelationReturnTerms(CorrelationReturnTerms correlationReturnTerms);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setPayerReceiver(PayerReceiver payerReceiver);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setUnderlier(Product underlier);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setQuantity(ReferenceWithMetaNonNegativeQuantitySchedule quantity0);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setQuantityValue(NonNegativeQuantitySchedule quantity1);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setInitialValuationPrice(ReferenceWithMetaPriceSchedule initialValuationPrice0);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setInitialValuationPriceValue(PriceSchedule initialValuationPrice1);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setInterimValuationPrice(ReferenceWithMetaPriceSchedule interimValuationPrice0);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setInterimValuationPriceValue(PriceSchedule interimValuationPrice1);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setFinalValuationPrice(ReferenceWithMetaPriceSchedule finalValuationPrice0);
		PortfolioReturnTerms.PortfolioReturnTermsBuilder setFinalValuationPriceValue(PriceSchedule finalValuationPrice1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("priceReturnTerms"), processor, PriceReturnTerms.PriceReturnTermsBuilder.class, getPriceReturnTerms());
			processRosetta(path.newSubPath("dividendReturnTerms"), processor, DividendReturnTerms.DividendReturnTermsBuilder.class, getDividendReturnTerms());
			processRosetta(path.newSubPath("varianceReturnTerms"), processor, VarianceReturnTerms.VarianceReturnTermsBuilder.class, getVarianceReturnTerms());
			processRosetta(path.newSubPath("volatilityReturnTerms"), processor, VolatilityReturnTerms.VolatilityReturnTermsBuilder.class, getVolatilityReturnTerms());
			processRosetta(path.newSubPath("correlationReturnTerms"), processor, CorrelationReturnTerms.CorrelationReturnTermsBuilder.class, getCorrelationReturnTerms());
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("underlier"), processor, Product.ProductBuilder.class, getUnderlier());
			processRosetta(path.newSubPath("quantity"), processor, ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder.class, getQuantity());
			processRosetta(path.newSubPath("initialValuationPrice"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getInitialValuationPrice());
			processRosetta(path.newSubPath("interimValuationPrice"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getInterimValuationPrice());
			processRosetta(path.newSubPath("finalValuationPrice"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getFinalValuationPrice());
		}
		

		PortfolioReturnTerms.PortfolioReturnTermsBuilder prune();
	}

	/*********************** Immutable Implementation of PortfolioReturnTerms  ***********************/
	class PortfolioReturnTermsImpl extends ReturnTerms.ReturnTermsImpl implements PortfolioReturnTerms {
		private final PayerReceiver payerReceiver;
		private final Product underlier;
		private final ReferenceWithMetaNonNegativeQuantitySchedule quantity;
		private final ReferenceWithMetaPriceSchedule initialValuationPrice;
		private final ReferenceWithMetaPriceSchedule interimValuationPrice;
		private final ReferenceWithMetaPriceSchedule finalValuationPrice;
		
		protected PortfolioReturnTermsImpl(PortfolioReturnTerms.PortfolioReturnTermsBuilder builder) {
			super(builder);
			this.payerReceiver = ofNullable(builder.getPayerReceiver()).map(f->f.build()).orElse(null);
			this.underlier = ofNullable(builder.getUnderlier()).map(f->f.build()).orElse(null);
			this.quantity = ofNullable(builder.getQuantity()).map(f->f.build()).orElse(null);
			this.initialValuationPrice = ofNullable(builder.getInitialValuationPrice()).map(f->f.build()).orElse(null);
			this.interimValuationPrice = ofNullable(builder.getInterimValuationPrice()).map(f->f.build()).orElse(null);
			this.finalValuationPrice = ofNullable(builder.getFinalValuationPrice()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("payerReceiver")
		public PayerReceiver getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		@RosettaAttribute("underlier")
		public Product getUnderlier() {
			return underlier;
		}
		
		@Override
		@RosettaAttribute("quantity")
		public ReferenceWithMetaNonNegativeQuantitySchedule getQuantity() {
			return quantity;
		}
		
		@Override
		@RosettaAttribute("initialValuationPrice")
		public ReferenceWithMetaPriceSchedule getInitialValuationPrice() {
			return initialValuationPrice;
		}
		
		@Override
		@RosettaAttribute("interimValuationPrice")
		public ReferenceWithMetaPriceSchedule getInterimValuationPrice() {
			return interimValuationPrice;
		}
		
		@Override
		@RosettaAttribute("finalValuationPrice")
		public ReferenceWithMetaPriceSchedule getFinalValuationPrice() {
			return finalValuationPrice;
		}
		
		@Override
		public PortfolioReturnTerms build() {
			return this;
		}
		
		@Override
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder toBuilder() {
			PortfolioReturnTerms.PortfolioReturnTermsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PortfolioReturnTerms.PortfolioReturnTermsBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getPayerReceiver()).ifPresent(builder::setPayerReceiver);
			ofNullable(getUnderlier()).ifPresent(builder::setUnderlier);
			ofNullable(getQuantity()).ifPresent(builder::setQuantity);
			ofNullable(getInitialValuationPrice()).ifPresent(builder::setInitialValuationPrice);
			ofNullable(getInterimValuationPrice()).ifPresent(builder::setInterimValuationPrice);
			ofNullable(getFinalValuationPrice()).ifPresent(builder::setFinalValuationPrice);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			PortfolioReturnTerms _that = getType().cast(o);
		
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			if (!Objects.equals(quantity, _that.getQuantity())) return false;
			if (!Objects.equals(initialValuationPrice, _that.getInitialValuationPrice())) return false;
			if (!Objects.equals(interimValuationPrice, _that.getInterimValuationPrice())) return false;
			if (!Objects.equals(finalValuationPrice, _that.getFinalValuationPrice())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			_result = 31 * _result + (initialValuationPrice != null ? initialValuationPrice.hashCode() : 0);
			_result = 31 * _result + (interimValuationPrice != null ? interimValuationPrice.hashCode() : 0);
			_result = 31 * _result + (finalValuationPrice != null ? finalValuationPrice.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PortfolioReturnTerms {" +
				"payerReceiver=" + this.payerReceiver + ", " +
				"underlier=" + this.underlier + ", " +
				"quantity=" + this.quantity + ", " +
				"initialValuationPrice=" + this.initialValuationPrice + ", " +
				"interimValuationPrice=" + this.interimValuationPrice + ", " +
				"finalValuationPrice=" + this.finalValuationPrice +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of PortfolioReturnTerms  ***********************/
	class PortfolioReturnTermsBuilderImpl extends ReturnTerms.ReturnTermsBuilderImpl  implements PortfolioReturnTerms.PortfolioReturnTermsBuilder {
	
		protected PayerReceiver.PayerReceiverBuilder payerReceiver;
		protected Product.ProductBuilder underlier;
		protected ReferenceWithMetaNonNegativeQuantitySchedule.ReferenceWithMetaNonNegativeQuantityScheduleBuilder quantity;
		protected ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder initialValuationPrice;
		protected ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder interimValuationPrice;
		protected ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder finalValuationPrice;
	
		public PortfolioReturnTermsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("payerReceiver")
		public PayerReceiver.PayerReceiverBuilder getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		public PayerReceiver.PayerReceiverBuilder getOrCreatePayerReceiver() {
			PayerReceiver.PayerReceiverBuilder result;
			if (payerReceiver!=null) {
				result = payerReceiver;
			}
			else {
				result = payerReceiver = PayerReceiver.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("underlier")
		public Product.ProductBuilder getUnderlier() {
			return underlier;
		}
		
		@Override
		public Product.ProductBuilder getOrCreateUnderlier() {
			Product.ProductBuilder result;
			if (underlier!=null) {
				result = underlier;
			}
			else {
				result = underlier = Product.builder();
			}
			
			return result;
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
		@RosettaAttribute("initialValuationPrice")
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getInitialValuationPrice() {
			return initialValuationPrice;
		}
		
		@Override
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInitialValuationPrice() {
			ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder result;
			if (initialValuationPrice!=null) {
				result = initialValuationPrice;
			}
			else {
				result = initialValuationPrice = ReferenceWithMetaPriceSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("interimValuationPrice")
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getInterimValuationPrice() {
			return interimValuationPrice;
		}
		
		@Override
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateInterimValuationPrice() {
			ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder result;
			if (interimValuationPrice!=null) {
				result = interimValuationPrice;
			}
			else {
				result = interimValuationPrice = ReferenceWithMetaPriceSchedule.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("finalValuationPrice")
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getFinalValuationPrice() {
			return finalValuationPrice;
		}
		
		@Override
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreateFinalValuationPrice() {
			ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder result;
			if (finalValuationPrice!=null) {
				result = finalValuationPrice;
			}
			else {
				result = finalValuationPrice = ReferenceWithMetaPriceSchedule.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("priceReturnTerms")
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setPriceReturnTerms(PriceReturnTerms priceReturnTerms) {
			this.priceReturnTerms = priceReturnTerms==null?null:priceReturnTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("dividendReturnTerms")
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setDividendReturnTerms(DividendReturnTerms dividendReturnTerms) {
			this.dividendReturnTerms = dividendReturnTerms==null?null:dividendReturnTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("varianceReturnTerms")
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setVarianceReturnTerms(VarianceReturnTerms varianceReturnTerms) {
			this.varianceReturnTerms = varianceReturnTerms==null?null:varianceReturnTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("volatilityReturnTerms")
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setVolatilityReturnTerms(VolatilityReturnTerms volatilityReturnTerms) {
			this.volatilityReturnTerms = volatilityReturnTerms==null?null:volatilityReturnTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("correlationReturnTerms")
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setCorrelationReturnTerms(CorrelationReturnTerms correlationReturnTerms) {
			this.correlationReturnTerms = correlationReturnTerms==null?null:correlationReturnTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("payerReceiver")
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("underlier")
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setUnderlier(Product underlier) {
			this.underlier = underlier==null?null:underlier.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("quantity")
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setQuantity(ReferenceWithMetaNonNegativeQuantitySchedule quantity) {
			this.quantity = quantity==null?null:quantity.toBuilder();
			return this;
		}
		@Override
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setQuantityValue(NonNegativeQuantitySchedule quantity) {
			this.getOrCreateQuantity().setValue(quantity);
			return this;
		}
		@Override
		@RosettaAttribute("initialValuationPrice")
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setInitialValuationPrice(ReferenceWithMetaPriceSchedule initialValuationPrice) {
			this.initialValuationPrice = initialValuationPrice==null?null:initialValuationPrice.toBuilder();
			return this;
		}
		@Override
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setInitialValuationPriceValue(PriceSchedule initialValuationPrice) {
			this.getOrCreateInitialValuationPrice().setValue(initialValuationPrice);
			return this;
		}
		@Override
		@RosettaAttribute("interimValuationPrice")
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setInterimValuationPrice(ReferenceWithMetaPriceSchedule interimValuationPrice) {
			this.interimValuationPrice = interimValuationPrice==null?null:interimValuationPrice.toBuilder();
			return this;
		}
		@Override
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setInterimValuationPriceValue(PriceSchedule interimValuationPrice) {
			this.getOrCreateInterimValuationPrice().setValue(interimValuationPrice);
			return this;
		}
		@Override
		@RosettaAttribute("finalValuationPrice")
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setFinalValuationPrice(ReferenceWithMetaPriceSchedule finalValuationPrice) {
			this.finalValuationPrice = finalValuationPrice==null?null:finalValuationPrice.toBuilder();
			return this;
		}
		@Override
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder setFinalValuationPriceValue(PriceSchedule finalValuationPrice) {
			this.getOrCreateFinalValuationPrice().setValue(finalValuationPrice);
			return this;
		}
		
		@Override
		public PortfolioReturnTerms build() {
			return new PortfolioReturnTerms.PortfolioReturnTermsImpl(this);
		}
		
		@Override
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder prune() {
			super.prune();
			if (payerReceiver!=null && !payerReceiver.prune().hasData()) payerReceiver = null;
			if (underlier!=null && !underlier.prune().hasData()) underlier = null;
			if (quantity!=null && !quantity.prune().hasData()) quantity = null;
			if (initialValuationPrice!=null && !initialValuationPrice.prune().hasData()) initialValuationPrice = null;
			if (interimValuationPrice!=null && !interimValuationPrice.prune().hasData()) interimValuationPrice = null;
			if (finalValuationPrice!=null && !finalValuationPrice.prune().hasData()) finalValuationPrice = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getPayerReceiver()!=null && getPayerReceiver().hasData()) return true;
			if (getUnderlier()!=null && getUnderlier().hasData()) return true;
			if (getQuantity()!=null && getQuantity().hasData()) return true;
			if (getInitialValuationPrice()!=null && getInitialValuationPrice().hasData()) return true;
			if (getInterimValuationPrice()!=null && getInterimValuationPrice().hasData()) return true;
			if (getFinalValuationPrice()!=null && getFinalValuationPrice().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PortfolioReturnTerms.PortfolioReturnTermsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			PortfolioReturnTerms.PortfolioReturnTermsBuilder o = (PortfolioReturnTerms.PortfolioReturnTermsBuilder) other;
			
			merger.mergeRosetta(getPayerReceiver(), o.getPayerReceiver(), this::setPayerReceiver);
			merger.mergeRosetta(getUnderlier(), o.getUnderlier(), this::setUnderlier);
			merger.mergeRosetta(getQuantity(), o.getQuantity(), this::setQuantity);
			merger.mergeRosetta(getInitialValuationPrice(), o.getInitialValuationPrice(), this::setInitialValuationPrice);
			merger.mergeRosetta(getInterimValuationPrice(), o.getInterimValuationPrice(), this::setInterimValuationPrice);
			merger.mergeRosetta(getFinalValuationPrice(), o.getFinalValuationPrice(), this::setFinalValuationPrice);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			PortfolioReturnTerms _that = getType().cast(o);
		
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(underlier, _that.getUnderlier())) return false;
			if (!Objects.equals(quantity, _that.getQuantity())) return false;
			if (!Objects.equals(initialValuationPrice, _that.getInitialValuationPrice())) return false;
			if (!Objects.equals(interimValuationPrice, _that.getInterimValuationPrice())) return false;
			if (!Objects.equals(finalValuationPrice, _that.getFinalValuationPrice())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (underlier != null ? underlier.hashCode() : 0);
			_result = 31 * _result + (quantity != null ? quantity.hashCode() : 0);
			_result = 31 * _result + (initialValuationPrice != null ? initialValuationPrice.hashCode() : 0);
			_result = 31 * _result + (interimValuationPrice != null ? interimValuationPrice.hashCode() : 0);
			_result = 31 * _result + (finalValuationPrice != null ? finalValuationPrice.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PortfolioReturnTermsBuilder {" +
				"payerReceiver=" + this.payerReceiver + ", " +
				"underlier=" + this.underlier + ", " +
				"quantity=" + this.quantity + ", " +
				"initialValuationPrice=" + this.initialValuationPrice + ", " +
				"interimValuationPrice=" + this.interimValuationPrice + ", " +
				"finalValuationPrice=" + this.finalValuationPrice +
			'}' + " " + super.toString();
		}
	}
}
