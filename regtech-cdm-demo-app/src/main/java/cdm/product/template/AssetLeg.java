package cdm.product.template;

import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.product.common.settlement.DeliveryMethodEnum;
import cdm.product.template.AssetLeg;
import cdm.product.template.AssetLeg.AssetLegBuilder;
import cdm.product.template.AssetLeg.AssetLegBuilderImpl;
import cdm.product.template.AssetLeg.AssetLegImpl;
import cdm.product.template.meta.AssetLegMeta;
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
 * Defines each asset movement of an asset payout.
 * @version ${project.version}
 */
@RosettaDataType(value="AssetLeg", builder=AssetLeg.AssetLegBuilderImpl.class, version="${project.version}")
public interface AssetLeg extends RosettaModelObject {

	AssetLegMeta metaData = new AssetLegMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the settlement date of securities.  In a repo transaction the purchase date would always be the effective date as specified under Economic Terms, the repurchase date would always be the termination date as specified under Economic Terms.
	 */
	AdjustableOrRelativeDate getSettlementDate();
	/**
	 * Specifies a delivery method for the security transaction.
	 */
	DeliveryMethodEnum getDeliveryMethod();

	/*********************** Build Methods  ***********************/
	AssetLeg build();
	
	AssetLeg.AssetLegBuilder toBuilder();
	
	static AssetLeg.AssetLegBuilder builder() {
		return new AssetLeg.AssetLegBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AssetLeg> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AssetLeg> getType() {
		return AssetLeg.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("settlementDate"), processor, AdjustableOrRelativeDate.class, getSettlementDate());
		processor.processBasic(path.newSubPath("deliveryMethod"), DeliveryMethodEnum.class, getDeliveryMethod(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface AssetLegBuilder extends AssetLeg, RosettaModelObjectBuilder {
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateSettlementDate();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getSettlementDate();
		AssetLeg.AssetLegBuilder setSettlementDate(AdjustableOrRelativeDate settlementDate);
		AssetLeg.AssetLegBuilder setDeliveryMethod(DeliveryMethodEnum deliveryMethod);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("settlementDate"), processor, AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder.class, getSettlementDate());
			processor.processBasic(path.newSubPath("deliveryMethod"), DeliveryMethodEnum.class, getDeliveryMethod(), this);
		}
		

		AssetLeg.AssetLegBuilder prune();
	}

	/*********************** Immutable Implementation of AssetLeg  ***********************/
	class AssetLegImpl implements AssetLeg {
		private final AdjustableOrRelativeDate settlementDate;
		private final DeliveryMethodEnum deliveryMethod;
		
		protected AssetLegImpl(AssetLeg.AssetLegBuilder builder) {
			this.settlementDate = ofNullable(builder.getSettlementDate()).map(f->f.build()).orElse(null);
			this.deliveryMethod = builder.getDeliveryMethod();
		}
		
		@Override
		@RosettaAttribute("settlementDate")
		public AdjustableOrRelativeDate getSettlementDate() {
			return settlementDate;
		}
		
		@Override
		@RosettaAttribute("deliveryMethod")
		public DeliveryMethodEnum getDeliveryMethod() {
			return deliveryMethod;
		}
		
		@Override
		public AssetLeg build() {
			return this;
		}
		
		@Override
		public AssetLeg.AssetLegBuilder toBuilder() {
			AssetLeg.AssetLegBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AssetLeg.AssetLegBuilder builder) {
			ofNullable(getSettlementDate()).ifPresent(builder::setSettlementDate);
			ofNullable(getDeliveryMethod()).ifPresent(builder::setDeliveryMethod);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetLeg _that = getType().cast(o);
		
			if (!Objects.equals(settlementDate, _that.getSettlementDate())) return false;
			if (!Objects.equals(deliveryMethod, _that.getDeliveryMethod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (settlementDate != null ? settlementDate.hashCode() : 0);
			_result = 31 * _result + (deliveryMethod != null ? deliveryMethod.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetLeg {" +
				"settlementDate=" + this.settlementDate + ", " +
				"deliveryMethod=" + this.deliveryMethod +
			'}';
		}
	}

	/*********************** Builder Implementation of AssetLeg  ***********************/
	class AssetLegBuilderImpl implements AssetLeg.AssetLegBuilder {
	
		protected AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder settlementDate;
		protected DeliveryMethodEnum deliveryMethod;
	
		public AssetLegBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("settlementDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getSettlementDate() {
			return settlementDate;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder getOrCreateSettlementDate() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder result;
			if (settlementDate!=null) {
				result = settlementDate;
			}
			else {
				result = settlementDate = AdjustableOrRelativeDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("deliveryMethod")
		public DeliveryMethodEnum getDeliveryMethod() {
			return deliveryMethod;
		}
		
	
		@Override
		@RosettaAttribute("settlementDate")
		public AssetLeg.AssetLegBuilder setSettlementDate(AdjustableOrRelativeDate settlementDate) {
			this.settlementDate = settlementDate==null?null:settlementDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("deliveryMethod")
		public AssetLeg.AssetLegBuilder setDeliveryMethod(DeliveryMethodEnum deliveryMethod) {
			this.deliveryMethod = deliveryMethod==null?null:deliveryMethod;
			return this;
		}
		
		@Override
		public AssetLeg build() {
			return new AssetLeg.AssetLegImpl(this);
		}
		
		@Override
		public AssetLeg.AssetLegBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetLeg.AssetLegBuilder prune() {
			if (settlementDate!=null && !settlementDate.prune().hasData()) settlementDate = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getSettlementDate()!=null && getSettlementDate().hasData()) return true;
			if (getDeliveryMethod()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetLeg.AssetLegBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AssetLeg.AssetLegBuilder o = (AssetLeg.AssetLegBuilder) other;
			
			merger.mergeRosetta(getSettlementDate(), o.getSettlementDate(), this::setSettlementDate);
			
			merger.mergeBasic(getDeliveryMethod(), o.getDeliveryMethod(), this::setDeliveryMethod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetLeg _that = getType().cast(o);
		
			if (!Objects.equals(settlementDate, _that.getSettlementDate())) return false;
			if (!Objects.equals(deliveryMethod, _that.getDeliveryMethod())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (settlementDate != null ? settlementDate.hashCode() : 0);
			_result = 31 * _result + (deliveryMethod != null ? deliveryMethod.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetLegBuilder {" +
				"settlementDate=" + this.settlementDate + ", " +
				"deliveryMethod=" + this.deliveryMethod +
			'}';
		}
	}
}
