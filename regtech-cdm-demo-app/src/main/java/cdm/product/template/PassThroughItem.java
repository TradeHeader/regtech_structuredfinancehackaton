package cdm.product.template;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.product.template.PassThroughItem;
import cdm.product.template.PassThroughItem.PassThroughItemBuilder;
import cdm.product.template.PassThroughItem.PassThroughItemBuilderImpl;
import cdm.product.template.PassThroughItem.PassThroughItemImpl;
import cdm.product.template.meta.PassThroughItemMeta;
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
 * Class to represent a single pass through payment.
 * @version ${project.version}
 */
@RosettaDataType(value="PassThroughItem", builder=PassThroughItem.PassThroughItemBuilderImpl.class, version="${project.version}")
public interface PassThroughItem extends RosettaModelObject {

	PassThroughItemMeta metaData = new PassThroughItemMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * This attribute doesn&#39;t exists in the FpML construct, which makes use of the PayerReceiver.model group.
	 */
	PayerReceiver getPayerReceiver();
	/**
	 * Percentage of payments from the underlier which are passed through.
	 */
	BigDecimal getPassThroughPercentage();

	/*********************** Build Methods  ***********************/
	PassThroughItem build();
	
	PassThroughItem.PassThroughItemBuilder toBuilder();
	
	static PassThroughItem.PassThroughItemBuilder builder() {
		return new PassThroughItem.PassThroughItemBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PassThroughItem> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PassThroughItem> getType() {
		return PassThroughItem.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.class, getPayerReceiver());
		processor.processBasic(path.newSubPath("passThroughPercentage"), BigDecimal.class, getPassThroughPercentage(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface PassThroughItemBuilder extends PassThroughItem, RosettaModelObjectBuilder {
		PayerReceiver.PayerReceiverBuilder getOrCreatePayerReceiver();
		PayerReceiver.PayerReceiverBuilder getPayerReceiver();
		PassThroughItem.PassThroughItemBuilder setPayerReceiver(PayerReceiver payerReceiver);
		PassThroughItem.PassThroughItemBuilder setPassThroughPercentage(BigDecimal passThroughPercentage);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("payerReceiver"), processor, PayerReceiver.PayerReceiverBuilder.class, getPayerReceiver());
			processor.processBasic(path.newSubPath("passThroughPercentage"), BigDecimal.class, getPassThroughPercentage(), this);
		}
		

		PassThroughItem.PassThroughItemBuilder prune();
	}

	/*********************** Immutable Implementation of PassThroughItem  ***********************/
	class PassThroughItemImpl implements PassThroughItem {
		private final PayerReceiver payerReceiver;
		private final BigDecimal passThroughPercentage;
		
		protected PassThroughItemImpl(PassThroughItem.PassThroughItemBuilder builder) {
			this.payerReceiver = ofNullable(builder.getPayerReceiver()).map(f->f.build()).orElse(null);
			this.passThroughPercentage = builder.getPassThroughPercentage();
		}
		
		@Override
		@RosettaAttribute("payerReceiver")
		public PayerReceiver getPayerReceiver() {
			return payerReceiver;
		}
		
		@Override
		@RosettaAttribute("passThroughPercentage")
		public BigDecimal getPassThroughPercentage() {
			return passThroughPercentage;
		}
		
		@Override
		public PassThroughItem build() {
			return this;
		}
		
		@Override
		public PassThroughItem.PassThroughItemBuilder toBuilder() {
			PassThroughItem.PassThroughItemBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PassThroughItem.PassThroughItemBuilder builder) {
			ofNullable(getPayerReceiver()).ifPresent(builder::setPayerReceiver);
			ofNullable(getPassThroughPercentage()).ifPresent(builder::setPassThroughPercentage);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PassThroughItem _that = getType().cast(o);
		
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(passThroughPercentage, _that.getPassThroughPercentage())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (passThroughPercentage != null ? passThroughPercentage.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PassThroughItem {" +
				"payerReceiver=" + this.payerReceiver + ", " +
				"passThroughPercentage=" + this.passThroughPercentage +
			'}';
		}
	}

	/*********************** Builder Implementation of PassThroughItem  ***********************/
	class PassThroughItemBuilderImpl implements PassThroughItem.PassThroughItemBuilder {
	
		protected PayerReceiver.PayerReceiverBuilder payerReceiver;
		protected BigDecimal passThroughPercentage;
	
		public PassThroughItemBuilderImpl() {
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
		@RosettaAttribute("passThroughPercentage")
		public BigDecimal getPassThroughPercentage() {
			return passThroughPercentage;
		}
		
	
		@Override
		@RosettaAttribute("payerReceiver")
		public PassThroughItem.PassThroughItemBuilder setPayerReceiver(PayerReceiver payerReceiver) {
			this.payerReceiver = payerReceiver==null?null:payerReceiver.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("passThroughPercentage")
		public PassThroughItem.PassThroughItemBuilder setPassThroughPercentage(BigDecimal passThroughPercentage) {
			this.passThroughPercentage = passThroughPercentage==null?null:passThroughPercentage;
			return this;
		}
		
		@Override
		public PassThroughItem build() {
			return new PassThroughItem.PassThroughItemImpl(this);
		}
		
		@Override
		public PassThroughItem.PassThroughItemBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PassThroughItem.PassThroughItemBuilder prune() {
			if (payerReceiver!=null && !payerReceiver.prune().hasData()) payerReceiver = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPayerReceiver()!=null && getPayerReceiver().hasData()) return true;
			if (getPassThroughPercentage()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PassThroughItem.PassThroughItemBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PassThroughItem.PassThroughItemBuilder o = (PassThroughItem.PassThroughItemBuilder) other;
			
			merger.mergeRosetta(getPayerReceiver(), o.getPayerReceiver(), this::setPayerReceiver);
			
			merger.mergeBasic(getPassThroughPercentage(), o.getPassThroughPercentage(), this::setPassThroughPercentage);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PassThroughItem _that = getType().cast(o);
		
			if (!Objects.equals(payerReceiver, _that.getPayerReceiver())) return false;
			if (!Objects.equals(passThroughPercentage, _that.getPassThroughPercentage())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (payerReceiver != null ? payerReceiver.hashCode() : 0);
			_result = 31 * _result + (passThroughPercentage != null ? passThroughPercentage.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PassThroughItemBuilder {" +
				"payerReceiver=" + this.payerReceiver + ", " +
				"passThroughPercentage=" + this.passThroughPercentage +
			'}';
		}
	}
}
