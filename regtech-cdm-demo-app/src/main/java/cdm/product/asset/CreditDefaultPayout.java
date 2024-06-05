package cdm.product.asset;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.observable.asset.TransactedPrice;
import cdm.product.asset.CreditDefaultPayout;
import cdm.product.asset.CreditDefaultPayout.CreditDefaultPayoutBuilder;
import cdm.product.asset.CreditDefaultPayout.CreditDefaultPayoutBuilderImpl;
import cdm.product.asset.CreditDefaultPayout.CreditDefaultPayoutImpl;
import cdm.product.asset.GeneralTerms;
import cdm.product.asset.ProtectionTerms;
import cdm.product.asset.meta.CreditDefaultPayoutMeta;
import cdm.product.common.settlement.PayoutBase;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilder;
import cdm.product.common.settlement.PayoutBase.PayoutBaseBuilderImpl;
import cdm.product.common.settlement.PayoutBase.PayoutBaseImpl;
import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.ResolvablePriceQuantity;
import cdm.product.common.settlement.SettlementTerms;
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
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 *  The credit default payout specification provides the details necessary for determining when a credit payout will be triggered as well as the parameters for calculating the payout and the settlement terms. The associated globalKey denotes the ability to associate a hash value to the CreditDefaultPayout instantiations for the purpose of model cross-referencing, in support of functionality such as the event effect and the lineage.
 * @version ${project.version}
 */
@RosettaDataType(value="CreditDefaultPayout", builder=CreditDefaultPayout.CreditDefaultPayoutBuilderImpl.class, version="${project.version}")
public interface CreditDefaultPayout extends PayoutBase, GlobalKey {

	CreditDefaultPayoutMeta metaData = new CreditDefaultPayoutMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The specification of the non-monetary terms for the Credit Derivative Transaction, including the buyer and seller and selected items from the ISDA 2014 Credit Definition article II, such as the reference obligation and related terms.
	 */
	GeneralTerms getGeneralTerms();
	/**
	 * Specifies the terms for calculating a payout to protect the buyer of the swap in the case of a qualified credit event. These terms include the applicable credit events, the reference obligation, and in the case of a CDS on mortgage-backed securities, the floatingAmountEvents.
	 */
	List<? extends ProtectionTerms> getProtectionTerms();
	/**
	 * The qualification of the price at which the contract has been transacted, in terms of market fixed rate, initial points, market price and/or quotation style. In FpML, those attributes are positioned as part of the fee leg.
	 */
	TransactedPrice getTransactedPrice();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	CreditDefaultPayout build();
	
	CreditDefaultPayout.CreditDefaultPayoutBuilder toBuilder();
	
	static CreditDefaultPayout.CreditDefaultPayoutBuilder builder() {
		return new CreditDefaultPayout.CreditDefaultPayoutBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CreditDefaultPayout> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CreditDefaultPayout> getType() {
		return CreditDefaultPayout.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.class, getPriceQuantity());
		processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.class, getPrincipalPayment());
		processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.class, getSettlementTerms());
		processRosetta(path.newSubPath("generalTerms"), processor, GeneralTerms.class, getGeneralTerms());
		processRosetta(path.newSubPath("protectionTerms"), processor, ProtectionTerms.class, getProtectionTerms());
		processRosetta(path.newSubPath("transactedPrice"), processor, TransactedPrice.class, getTransactedPrice());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface CreditDefaultPayoutBuilder extends CreditDefaultPayout, PayoutBase.PayoutBaseBuilder, RosettaModelObjectBuilder {
		GeneralTerms.GeneralTermsBuilder getOrCreateGeneralTerms();
		GeneralTerms.GeneralTermsBuilder getGeneralTerms();
		ProtectionTerms.ProtectionTermsBuilder getOrCreateProtectionTerms(int _index);
		List<? extends ProtectionTerms.ProtectionTermsBuilder> getProtectionTerms();
		TransactedPrice.TransactedPriceBuilder getOrCreateTransactedPrice();
		TransactedPrice.TransactedPriceBuilder getTransactedPrice();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		CreditDefaultPayout.CreditDefaultPayoutBuilder setPayerReceiver(PayerReceiver payerReceiver);
		CreditDefaultPayout.CreditDefaultPayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity);
		CreditDefaultPayout.CreditDefaultPayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment);
		CreditDefaultPayout.CreditDefaultPayoutBuilder setSettlementTerms(SettlementTerms settlementTerms);
		CreditDefaultPayout.CreditDefaultPayoutBuilder setGeneralTerms(GeneralTerms generalTerms);
		CreditDefaultPayout.CreditDefaultPayoutBuilder addProtectionTerms(ProtectionTerms protectionTerms0);
		CreditDefaultPayout.CreditDefaultPayoutBuilder addProtectionTerms(ProtectionTerms protectionTerms1, int _idx);
		CreditDefaultPayout.CreditDefaultPayoutBuilder addProtectionTerms(List<? extends ProtectionTerms> protectionTerms2);
		CreditDefaultPayout.CreditDefaultPayoutBuilder setProtectionTerms(List<? extends ProtectionTerms> protectionTerms3);
		CreditDefaultPayout.CreditDefaultPayoutBuilder setTransactedPrice(TransactedPrice transactedPrice);
		CreditDefaultPayout.CreditDefaultPayoutBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processRosetta(path.newSubPath("priceQuantity"), processor, ResolvablePriceQuantity.ResolvablePriceQuantityBuilder.class, getPriceQuantity());
			processRosetta(path.newSubPath("principalPayment"), processor, PrincipalPayments.PrincipalPaymentsBuilder.class, getPrincipalPayment());
			processRosetta(path.newSubPath("settlementTerms"), processor, SettlementTerms.SettlementTermsBuilder.class, getSettlementTerms());
			processRosetta(path.newSubPath("generalTerms"), processor, GeneralTerms.GeneralTermsBuilder.class, getGeneralTerms());
			processRosetta(path.newSubPath("protectionTerms"), processor, ProtectionTerms.ProtectionTermsBuilder.class, getProtectionTerms());
			processRosetta(path.newSubPath("transactedPrice"), processor, TransactedPrice.TransactedPriceBuilder.class, getTransactedPrice());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		CreditDefaultPayout.CreditDefaultPayoutBuilder prune();
	}

	/*********************** Immutable Implementation of CreditDefaultPayout  ***********************/
	class CreditDefaultPayoutImpl extends PayoutBase.PayoutBaseImpl implements CreditDefaultPayout {
		private final GeneralTerms generalTerms;
		private final List<? extends ProtectionTerms> protectionTerms;
		private final TransactedPrice transactedPrice;
		private final MetaFields meta;
		
		protected CreditDefaultPayoutImpl(CreditDefaultPayout.CreditDefaultPayoutBuilder builder) {
			super(builder);
			this.generalTerms = ofNullable(builder.getGeneralTerms()).map(f->f.build()).orElse(null);
			this.protectionTerms = ofNullable(builder.getProtectionTerms()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.transactedPrice = ofNullable(builder.getTransactedPrice()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("generalTerms")
		public GeneralTerms getGeneralTerms() {
			return generalTerms;
		}
		
		@Override
		@RosettaAttribute("protectionTerms")
		public List<? extends ProtectionTerms> getProtectionTerms() {
			return protectionTerms;
		}
		
		@Override
		@RosettaAttribute("transactedPrice")
		public TransactedPrice getTransactedPrice() {
			return transactedPrice;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public CreditDefaultPayout build() {
			return this;
		}
		
		@Override
		public CreditDefaultPayout.CreditDefaultPayoutBuilder toBuilder() {
			CreditDefaultPayout.CreditDefaultPayoutBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CreditDefaultPayout.CreditDefaultPayoutBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getGeneralTerms()).ifPresent(builder::setGeneralTerms);
			ofNullable(getProtectionTerms()).ifPresent(builder::setProtectionTerms);
			ofNullable(getTransactedPrice()).ifPresent(builder::setTransactedPrice);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CreditDefaultPayout _that = getType().cast(o);
		
			if (!Objects.equals(generalTerms, _that.getGeneralTerms())) return false;
			if (!ListEquals.listEquals(protectionTerms, _that.getProtectionTerms())) return false;
			if (!Objects.equals(transactedPrice, _that.getTransactedPrice())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (generalTerms != null ? generalTerms.hashCode() : 0);
			_result = 31 * _result + (protectionTerms != null ? protectionTerms.hashCode() : 0);
			_result = 31 * _result + (transactedPrice != null ? transactedPrice.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditDefaultPayout {" +
				"generalTerms=" + this.generalTerms + ", " +
				"protectionTerms=" + this.protectionTerms + ", " +
				"transactedPrice=" + this.transactedPrice + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of CreditDefaultPayout  ***********************/
	class CreditDefaultPayoutBuilderImpl extends PayoutBase.PayoutBaseBuilderImpl  implements CreditDefaultPayout.CreditDefaultPayoutBuilder, GlobalKeyBuilder {
	
		protected GeneralTerms.GeneralTermsBuilder generalTerms;
		protected List<ProtectionTerms.ProtectionTermsBuilder> protectionTerms = new ArrayList<>();
		protected TransactedPrice.TransactedPriceBuilder transactedPrice;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public CreditDefaultPayoutBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("generalTerms")
		public GeneralTerms.GeneralTermsBuilder getGeneralTerms() {
			return generalTerms;
		}
		
		@Override
		public GeneralTerms.GeneralTermsBuilder getOrCreateGeneralTerms() {
			GeneralTerms.GeneralTermsBuilder result;
			if (generalTerms!=null) {
				result = generalTerms;
			}
			else {
				result = generalTerms = GeneralTerms.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("protectionTerms")
		public List<? extends ProtectionTerms.ProtectionTermsBuilder> getProtectionTerms() {
			return protectionTerms;
		}
		
		public ProtectionTerms.ProtectionTermsBuilder getOrCreateProtectionTerms(int _index) {
		
			if (protectionTerms==null) {
				this.protectionTerms = new ArrayList<>();
			}
			ProtectionTerms.ProtectionTermsBuilder result;
			return getIndex(protectionTerms, _index, () -> {
						ProtectionTerms.ProtectionTermsBuilder newProtectionTerms = ProtectionTerms.builder();
						return newProtectionTerms;
					});
		}
		
		@Override
		@RosettaAttribute("transactedPrice")
		public TransactedPrice.TransactedPriceBuilder getTransactedPrice() {
			return transactedPrice;
		}
		
		@Override
		public TransactedPrice.TransactedPriceBuilder getOrCreateTransactedPrice() {
			TransactedPrice.TransactedPriceBuilder result;
			if (transactedPrice!=null) {
				result = transactedPrice;
			}
			else {
				result = transactedPrice = TransactedPrice.builder();
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
		@RosettaAttribute("payerReceiver")
		public CreditDefaultPayout.CreditDefaultPayoutBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceQuantity")
		public CreditDefaultPayout.CreditDefaultPayoutBuilder setPriceQuantity(ResolvablePriceQuantity priceQuantity) {
			this.priceQuantity = priceQuantity==null?null:priceQuantity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("principalPayment")
		public CreditDefaultPayout.CreditDefaultPayoutBuilder setPrincipalPayment(PrincipalPayments principalPayment) {
			this.principalPayment = principalPayment==null?null:principalPayment.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("settlementTerms")
		public CreditDefaultPayout.CreditDefaultPayoutBuilder setSettlementTerms(SettlementTerms settlementTerms) {
			this.settlementTerms = settlementTerms==null?null:settlementTerms.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("generalTerms")
		public CreditDefaultPayout.CreditDefaultPayoutBuilder setGeneralTerms(GeneralTerms generalTerms) {
			this.generalTerms = generalTerms==null?null:generalTerms.toBuilder();
			return this;
		}
		@Override
		public CreditDefaultPayout.CreditDefaultPayoutBuilder addProtectionTerms(ProtectionTerms protectionTerms) {
			if (protectionTerms!=null) this.protectionTerms.add(protectionTerms.toBuilder());
			return this;
		}
		
		@Override
		public CreditDefaultPayout.CreditDefaultPayoutBuilder addProtectionTerms(ProtectionTerms protectionTerms, int _idx) {
			getIndex(this.protectionTerms, _idx, () -> protectionTerms.toBuilder());
			return this;
		}
		@Override 
		public CreditDefaultPayout.CreditDefaultPayoutBuilder addProtectionTerms(List<? extends ProtectionTerms> protectionTermss) {
			if (protectionTermss != null) {
				for (ProtectionTerms toAdd : protectionTermss) {
					this.protectionTerms.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("protectionTerms")
		public CreditDefaultPayout.CreditDefaultPayoutBuilder setProtectionTerms(List<? extends ProtectionTerms> protectionTermss) {
			if (protectionTermss == null)  {
				this.protectionTerms = new ArrayList<>();
			}
			else {
				this.protectionTerms = protectionTermss.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("transactedPrice")
		public CreditDefaultPayout.CreditDefaultPayoutBuilder setTransactedPrice(TransactedPrice transactedPrice) {
			this.transactedPrice = transactedPrice==null?null:transactedPrice.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public CreditDefaultPayout.CreditDefaultPayoutBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public CreditDefaultPayout build() {
			return new CreditDefaultPayout.CreditDefaultPayoutImpl(this);
		}
		
		@Override
		public CreditDefaultPayout.CreditDefaultPayoutBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditDefaultPayout.CreditDefaultPayoutBuilder prune() {
			super.prune();
			if (generalTerms!=null && !generalTerms.prune().hasData()) generalTerms = null;
			protectionTerms = protectionTerms.stream().filter(b->b!=null).<ProtectionTerms.ProtectionTermsBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (transactedPrice!=null && !transactedPrice.prune().hasData()) transactedPrice = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getGeneralTerms()!=null && getGeneralTerms().hasData()) return true;
			if (getProtectionTerms()!=null && getProtectionTerms().stream().filter(Objects::nonNull).anyMatch(a->a.hasData())) return true;
			if (getTransactedPrice()!=null && getTransactedPrice().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CreditDefaultPayout.CreditDefaultPayoutBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			CreditDefaultPayout.CreditDefaultPayoutBuilder o = (CreditDefaultPayout.CreditDefaultPayoutBuilder) other;
			
			merger.mergeRosetta(getGeneralTerms(), o.getGeneralTerms(), this::setGeneralTerms);
			merger.mergeRosetta(getProtectionTerms(), o.getProtectionTerms(), this::getOrCreateProtectionTerms);
			merger.mergeRosetta(getTransactedPrice(), o.getTransactedPrice(), this::setTransactedPrice);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			CreditDefaultPayout _that = getType().cast(o);
		
			if (!Objects.equals(generalTerms, _that.getGeneralTerms())) return false;
			if (!ListEquals.listEquals(protectionTerms, _that.getProtectionTerms())) return false;
			if (!Objects.equals(transactedPrice, _that.getTransactedPrice())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (generalTerms != null ? generalTerms.hashCode() : 0);
			_result = 31 * _result + (protectionTerms != null ? protectionTerms.hashCode() : 0);
			_result = 31 * _result + (transactedPrice != null ? transactedPrice.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CreditDefaultPayoutBuilder {" +
				"generalTerms=" + this.generalTerms + ", " +
				"protectionTerms=" + this.protectionTerms + ", " +
				"transactedPrice=" + this.transactedPrice + ", " +
				"meta=" + this.meta +
			'}' + " " + super.toString();
		}
	}
}
