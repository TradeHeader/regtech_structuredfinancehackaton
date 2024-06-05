package cdm.product.common.settlement;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.common.settlement.PayoutBase;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilder;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilderImpl;
import cdm.product.common.settlement.PayoutBase.PayoutBaseImpl;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
import cdm.product.common.settlement.meta.PayoutBaseMeta;
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
 *  Base class that all payout types should extend. Use case is that some validation rules may need to apply across all payout types, for which the data rule can be written at the base class level
 * @version ${project.version}
 */
@RosettaDataType(value="PayoutBase", builder=PayoutBase.PayoutBaseBuilderImpl.class, version="${project.version}")
public interface PayoutBase extends RosettaModelObject {

	PayoutBaseMeta metaData = new PayoutBaseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Canonical representation of the payer and receiver parties applicable to each payout leg.
	 */
	PayerReceiver getPayerReceiver();
	/**
	 * Each payout leg must implement the quantity concept as a &#39;resolvable&#39; type, which allows for different payout legs to be linked to each other (e.g. in the case of cross-curreny products).
	 */
	ResolvablePriceQuantity getPriceQuantity();
	/**
	 * The specification of the principal exchange. Optional as only applicable in the case of cross-currency or zero-coupon swaps with a final payment.
	 */
	PrincipalPayments getPrincipalPayment();
	/**
	 * Each payout leg must specifies its settlement terms, including the delivery type (i.e. cash vs physical, and their respective terms), the transfer type (DvP etc.) and settlement date, if any.
	 */
	SettlementTerms getSettlementTerms();

	/*********************** Build Methods  ***********************/
	PayoutBase build();
	
	PayoutBase.PayoutBaseBuilder toBuilder();
	
	static PayoutBase.PayoutBaseBuilder builder() {
		return new PayoutBase.PayoutBaseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PayoutBase> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PayoutBase> getType() {
		return PayoutBase.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.class, getPrincipalPayment());
		processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.class, getSettlementTerms());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PayoutBaseBuilder extends PayoutBase, RosettaModelObjectBuilder {
		PayerReceiver.PayerReceiverBuilder getOrCreatePayerReceiver();
		PayerReceiver.PayerReceiverBuilder getPayerReceiver();
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder getOrCreatePriceQuantity();
		ResolvablePriceQuantity.ResolvablePriceQuantityBuilder getPriceQuantity();
		PrincipalPayments.PrincipalPaymentsBuilder getOrCreatePrincipalPayment();
		PrincipalPayments.PrincipalPaymentsBuilder getPrincipalPayment();
		SettlementTerms.SettlementTermsBuilder getOrCreateSettlementTerms();
		SettlementTerms.SettlementTermsBuilder getSettlementTerms();
		PayoutBase.PayoutBaseBuilder setPayerReceiver(PayerReceiver payerReceiver);
		PayoutBase.PayoutBaseBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity);
		PayoutBase.PayoutBaseBuilder setPrincipalPayment(PrincipalPayments principalPayment);
		PayoutBase.PayoutBaseBuilder setSettlementTerms(SettlementTerms settlementTerms);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.ResolvablePriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.PrincipalPaymentsBuilder.class, getPrincipalPayment());
			processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.SettlementTermsBuilder.class, getSettlementTerms());
		}
		

		PayoutBase.PayoutBaseBuilder prune();
	}

	/*********************** Immutable Implementation of PayoutBase  ***********************/
	class PayoutBaseImpl implements PayoutBase {
		private final PayerReceiver payerReceiver;
		private final ResolvablePriceQuantity priceQuantity;
		private final PrincipalPayments principalPayment;
		private final SettlementTerms settlementTerms;
		
		protected PayoutBaseImpl(PayoutBase.PayoutBaseBuilder builder) {
			this.payerReceiver = ofNullable(builder.getPayerReceiver()).map(f->f.build()).orElse(null);
			this.priceQuantity = ofNullable(builder.getPriceQuantity()).map(f->f.build()).orElse(null);
			this.principalPayment = ofNullable(builder.getPrincipalPayment()).map(f->f.build()).orElse(null);
			this.settlementTerms = ofNullable(builder.getSettlementTerms()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("payerReceiver")
		public PayerReceiver getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		@RosettaAttribute("priceQuantity")
		public ResolvablePriceQuantity getPriceQuantity() {
			return priceQuantity;
		}
		
		@Override
		@RosettaAttribute("principalPayment")
		public PrincipalPayments getPrincipalPayment() {
			return principalPayment;
		}
		
		@Override
		@RosettaAttribute("settlementTerms")
		public SettlementTerms getSettlementTerms() {
			return settlementTerms;
		}
		
		@Override
		public PayoutBase build() {
			return this;
		}
		
		@Override
		public PayoutBase.PayoutBaseBuilder toBuilder() {
			PayoutBase.PayoutBaseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PayoutBase.PayoutBaseBuilder builder) {
			ofNullable(getPayerReceiver()).ifPresent(builder::setPayerReceiver);
			ofNullable(getPriceQuantity()).ifPresent(builder::setPriceQuantity);
			ofNullable(getPrincipalPayment()).ifPresent(builder::setPrincipalPayment);
			ofNullable(getSettlementTerms()).ifPresent(builder::setSettlementTerms);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PayoutBase _that = getType().cast(o);
		
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(priceQuantity, _that.getPriceQuantity())) return false;
			if (!Objects.equals(principalPayment, _that.getPrincipalPayment())) return false;
			if (!Objects.equals(settlementTerms, _that.getSettlementTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (priceQuantity != null ? priceQuantity.hashCode() : 0);
			_result = 31 * _result + (principalPayment != null ? principalPayment.hashCode() : 0);
			_result = 31 * _result + (settlementTerms != null ? settlementTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PayoutBase {" +
				"payerReceiver=" + this.payerReceiver + ", " +
				"priceQuantity=" + this.priceQuantity + ", " +
				"principalPayment=" + this.principalPayment + ", " +
				"settlementTerms=" + this.settlementTerms +
			'}';
		}
	}

	/*********************** Builder Implementation of PayoutBase  ***********************/
	class PayoutBaseBuilderImpl implements PayoutBase.PayoutBaseBuilder {
	
		protected PayerReceiver.PayerReceiverBuilder payerReceiver;
		protected ResolvablePriceQuantity.ResolvablePriceQuantityBuilder priceQuantity;
		protected PrincipalPayments.PrincipalPaymentsBuilder principalPayment;
		protected SettlementTerms.SettlementTermsBuilder settlementTerms;
	
		public PayoutBaseBuilderImpl() {
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
		@RosettaAttribute("priceQuantity")
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder getPriceQuantity() {
			return priceQuantity;
		}
		
		@Override
		public ResolvablePriceQuantity.ResolvablePriceQuantityBuilder getOrCreatePriceQuantity() {
			ResolvablePriceQuantity.ResolvablePriceQuantityBuilder result;
			if (priceQuantity!=null) {
				result = priceQuantity;
			}
			else {
				result = priceQuantity = ResolvablePriceQuantity.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("principalPayment")
		public PrincipalPayments.PrincipalPaymentsBuilder getPrincipalPayment() {
			return principalPayment;
		}
		
		@Override
		public PrincipalPayments.PrincipalPaymentsBuilder getOrCreatePrincipalPayment() {
			PrincipalPayments.PrincipalPaymentsBuilder result;
			if (principalPayment!=null) {
				result = principalPayment;
			}
			else {
				result = principalPayment = PrincipalPayments.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public SettlementTerms.SettlementTermsBuilder getSettlementTerms() {
			return settlementTerms;
		}
		
		@Override
		public SettlementTerms.SettlementTermsBuilder getOrCreateSettlementTerms() {
			SettlementTerms.SettlementTermsBuilder result;
			if (settlementTerms!=null) {
				result = settlementTerms;
			}
			else {
				result = settlementTerms = SettlementTerms.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("payerReceiver")
		public PayoutBase.PayoutBaseBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceQuantity")
		public PayoutBase.PayoutBaseBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity) {
			this.priceQuantity = priceQuantity==null?null:priceQuantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("principalPayment")
		public PayoutBase.PayoutBaseBuilder setPrincipalPayment(PrincipalPayments principalPayment) {
			this.principalPayment = principalPayment==null?null:principalPayment.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public PayoutBase.PayoutBaseBuilder setSettlementTerms(SettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		
		@Override
		public PayoutBase build() {
			return new PayoutBase.PayoutBaseImpl(this);
		}
		
		@Override
		public PayoutBase.PayoutBaseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PayoutBase.PayoutBaseBuilder prune() {
			if (payerReceiver!=null && !payerReceiver.prune().hasData()) payerReceiver = null;
			if (priceQuantity!=null && !priceQuantity.prune().hasData()) priceQuantity = null;
			if (principalPayment!=null && !principalPayment.prune().hasData()) principalPayment = null;
			if (settlementTerms!=null && !settlementTerms.prune().hasData()) settlementTerms = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPayerReceiver()!=null && getPayerReceiver().hasData()) return true;
			if (getPriceQuantity()!=null && getPriceQuantity().hasData()) return true;
			if (getPrincipalPayment()!=null && getPrincipalPayment().hasData()) return true;
			if (getSettlementTerms()!=null && getSettlementTerms().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PayoutBase.PayoutBaseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PayoutBase.PayoutBaseBuilder o = (PayoutBase.PayoutBaseBuilder) other;
			
			merger.mergeRosetta(getPayerReceiver(), o.getPayerReceiver(), this::setPayerReceiver);
			merger.mergeRosetta(getPriceQuantity(), o.getPriceQuantity(), this::setPriceQuantity);
			merger.mergeRosetta(getPrincipalPayment(), o.getPrincipalPayment(), this::setPrincipalPayment);
			merger.mergeRosetta(getSettlementTerms(), o.getSettlementTerms(), this::setSettlementTerms);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PayoutBase _that = getType().cast(o);
		
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(priceQuantity, _that.getPriceQuantity())) return false;
			if (!Objects.equals(principalPayment, _that.getPrincipalPayment())) return false;
			if (!Objects.equals(settlementTerms, _that.getSettlementTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (priceQuantity != null ? priceQuantity.hashCode() : 0);
			_result = 31 * _result + (principalPayment != null ? principalPayment.hashCode() : 0);
			_result = 31 * _result + (settlementTerms != null ? settlementTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PayoutBaseBuilder {" +
				"payerReceiver=" + this.payerReceiver + ", " +
				"priceQuantity=" + this.priceQuantity + ", " +
				"principalPayment=" + this.principalPayment + ", " +
				"settlementTerms=" + this.settlementTerms +
			'}';
		}
	}
}
