package cdm.base.staticdata.party;

import cdm.base.staticdata.party.CounterpartyRoleEnum;
import cdm.base.staticdata.party.PayerReceiver;
import cdm.base.staticdata.party.PayerReceiver.PayerReceiverBuilder;
import cdm.base.staticdata.party.PayerReceiver.PayerReceiverBuilderImpl;
import cdm.base.staticdata.party.PayerReceiver.PayerReceiverImpl;
import cdm.base.staticdata.party.meta.PayerReceiverMeta;
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
 * Specifies the parties responsible for making and receiving payments defined by this structure.
 * @version ${project.version}
 */
@RosettaDataType(value="PayerReceiver", builder=PayerReceiver.PayerReceiverBuilderImpl.class, version="${project.version}")
public interface PayerReceiver extends RosettaModelObject {

	PayerReceiverMeta metaData = new PayerReceiverMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the counterparty responsible for making the payments defined by this structure.  The party is one of the two principal parties to the transaction.
	 *
	 * Body ICMA
	 * Corpus MasterAgreement GMRA Global Master Repurchase Agreement GMRA 2011 "The Global Master Repurchase Agreement (GMRA) is a model legal agreement designed for parties transacting repos and is published by the International Capital Market Association (ICMA)." 
	 * namingConvention "Seller"
	 *
	 * Provision As defined in the GMRA, paragraph 1(a). The Seller transfers Securities in exchange for the Purchase Price on the Purchase Date and agrees to buy Equivalent Securities from the Buyer in exchange for the Repurchase Price on the Repurchase Date.
	 *
	 *
	 * Body ICMA
	 * Corpus Guidance ERCCBestPractice ERCC Guide to Best Practice in the European Repo Market ERCC Guide to Best Practice in the European Repo Market "The ERCC Guide to Best Practice in the European Repo Market is published by ICMAs European Repo and Collateral Council (ERCC). Its purpose is to help foster a fair and efficient European repo market by recommending practices which market experience suggests can help avoid uncertainty or disagreement about transactions, and consequent delay or disruption to repo trading and settlement. With the same purpose in mind, the Guide also codifies market conventions, where this has been thought to be helpful, usually in response to queries from market participants." 
	 * namingConvention "Seller"
	 *
	 * Provision ERCC Guide: Annex II  Glossary of repo terminology. The party to a repo who sells collateral for cash in the form of the Purchase Price on the Purchase Date and commits to buy back the same quantity of equivalent collateral on the Repurchase Date --- which will be a fixed maturity date or, in the case of open repo, on demand --- at an agreed or calculable Repurchase Price. The Seller is effectively borrowing cash. Cf Buyer.
	 *
	 */
	CounterpartyRoleEnum getPayer();
	/**
	 * Specifies the party that receives the payments corresponding to this structure.  The party is one of the two counterparties to the transaction.
	 *
	 * Body ICMA
	 * Corpus MasterAgreement GMRA Global Master Repurchase Agreement GMRA 2011 "The Global Master Repurchase Agreement (GMRA) is a model legal agreement designed for parties transacting repos and is published by the International Capital Market Association (ICMA)." 
	 * namingConvention "Buyer"
	 *
	 * Provision As defined in the GMRA, paragraph 1(a). The Buyer purchases Securities at the Purchase Price on the Purchase Date and agrees to sell Equivalent Securities to the Seller in exchange for the Repurchase Price on the Repurchase Date.
	 *
	 *
	 * Body ICMA
	 * Corpus Guidance ERCCBestPractice ERCC Guide to Best Practice in the European Repo Market ERCC Guide to Best Practice in the European Repo Market "The ERCC Guide to Best Practice in the European Repo Market is published by ICMAs European Repo and Collateral Council (ERCC). Its purpose is to help foster a fair and efficient European repo market by recommending practices which market experience suggests can help avoid uncertainty or disagreement about transactions, and consequent delay or disruption to repo trading and settlement. With the same purpose in mind, the Guide also codifies market conventions, where this has been thought to be helpful, usually in response to queries from market participants." 
	 * namingConvention "Buyer"
	 *
	 * Provision ERCC Guide: Annex II  Glossary of repo terminology. The party to a repo who buys collateral at the Purchase Price on the Purchase Date and commits to sell back the same quantity of equivalent collateral on the Repurchase Date --- which will be a fixed maturity date or, in the case of open repo, on demand --- at an agreed or calculable Repurchase Price. The Buyer is effectively a lender of cash and is said to be doing a reverse repo.
	 *
	 */
	CounterpartyRoleEnum getReceiver();

	/*********************** Build Methods  ***********************/
	PayerReceiver build();
	
	PayerReceiver.PayerReceiverBuilder toBuilder();
	
	static PayerReceiver.PayerReceiverBuilder builder() {
		return new PayerReceiver.PayerReceiverBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PayerReceiver> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PayerReceiver> getType() {
		return PayerReceiver.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("payer"), CounterpartyRoleEnum.class, getPayer(), this);
		processor.processBasic(path.newSubPath("receiver"), CounterpartyRoleEnum.class, getReceiver(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PayerReceiverBuilder extends PayerReceiver, RosettaModelObjectBuilder {
		PayerReceiver.PayerReceiverBuilder setPayer(CounterpartyRoleEnum payer);
		PayerReceiver.PayerReceiverBuilder setReceiver(CounterpartyRoleEnum receiver);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("payer"), CounterpartyRoleEnum.class, getPayer(), this);
			processor.processBasic(path.newSubPath("receiver"), CounterpartyRoleEnum.class, getReceiver(), this);
		}
		

		PayerReceiver.PayerReceiverBuilder prune();
	}

	/*********************** Immutable Implementation of PayerReceiver  ***********************/
	class PayerReceiverImpl implements PayerReceiver {
		private final CounterpartyRoleEnum payer;
		private final CounterpartyRoleEnum receiver;
		
		protected PayerReceiverImpl(PayerReceiver.PayerReceiverBuilder builder) {
			this.payer = builder.getPayer();
			this.receiver = builder.getReceiver();
		}
		
		@Override
		@RosettaAttribute("payer")
		public CounterpartyRoleEnum getPayer() {
			return payer;
		}
		
		@Override
		@RosettaAttribute("receiver")
		public CounterpartyRoleEnum getReceiver() {
			return receiver;
		}
		
		@Override
		public PayerReceiver build() {
			return this;
		}
		
		@Override
		public PayerReceiver.PayerReceiverBuilder toBuilder() {
			PayerReceiver.PayerReceiverBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PayerReceiver.PayerReceiverBuilder builder) {
			ofNullable(getPayer()).ifPresent(builder::setPayer);
			ofNullable(getReceiver()).ifPresent(builder::setReceiver);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PayerReceiver _that = getType().cast(o);
		
			if (!Objects.equals(payer, _that.getPayer())) return false;
			if (!Objects.equals(receiver, _that.getReceiver())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (payer != null ? payer.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (receiver != null ? receiver.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PayerReceiver {" +
				"payer=" + this.payer + ", " +
				"receiver=" + this.receiver +
			'}';
		}
	}

	/*********************** Builder Implementation of PayerReceiver  ***********************/
	class PayerReceiverBuilderImpl implements PayerReceiver.PayerReceiverBuilder {
	
		protected CounterpartyRoleEnum payer;
		protected CounterpartyRoleEnum receiver;
	
		public PayerReceiverBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("payer")
		public CounterpartyRoleEnum getPayer() {
			return payer;
		}
		
		@Override
		@RosettaAttribute("receiver")
		public CounterpartyRoleEnum getReceiver() {
			return receiver;
		}
		
	
		@Override
		@RosettaAttribute("payer")
		public PayerReceiver.PayerReceiverBuilder setPayer(CounterpartyRoleEnum payer) {
			this.payer = payer==null?null:payer;
			return this;
		}
		@Override
		@RosettaAttribute("receiver")
		public PayerReceiver.PayerReceiverBuilder setReceiver(CounterpartyRoleEnum receiver) {
			this.receiver = receiver==null?null:receiver;
			return this;
		}
		
		@Override
		public PayerReceiver build() {
			return new PayerReceiver.PayerReceiverImpl(this);
		}
		
		@Override
		public PayerReceiver.PayerReceiverBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PayerReceiver.PayerReceiverBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPayer()!=null) return true;
			if (getReceiver()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PayerReceiver.PayerReceiverBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PayerReceiver.PayerReceiverBuilder o = (PayerReceiver.PayerReceiverBuilder) other;
			
			
			merger.mergeBasic(getPayer(), o.getPayer(), this::setPayer);
			merger.mergeBasic(getReceiver(), o.getReceiver(), this::setReceiver);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PayerReceiver _that = getType().cast(o);
		
			if (!Objects.equals(payer, _that.getPayer())) return false;
			if (!Objects.equals(receiver, _that.getReceiver())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (payer != null ? payer.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (receiver != null ? receiver.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PayerReceiverBuilder {" +
				"payer=" + this.payer + ", " +
				"receiver=" + this.receiver +
			'}';
		}
	}
}
