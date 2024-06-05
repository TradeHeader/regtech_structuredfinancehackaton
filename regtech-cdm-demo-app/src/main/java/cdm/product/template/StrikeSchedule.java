package cdm.product.template;

import cdm.base.staticdata.party.PayerReceiverEnum;
import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder;
import cdm.product.common.schedule.RateSchedule;
import cdm.product.common.schedule.RateSchedule.RateScheduleBuilder;
import cdm.product.common.schedule.RateSchedule.RateScheduleBuilderImpl;
import cdm.product.common.schedule.RateSchedule.RateScheduleImpl;
import cdm.product.template.StrikeSchedule;
import cdm.product.template.StrikeSchedule.StrikeScheduleBuilder;
import cdm.product.template.StrikeSchedule.StrikeScheduleBuilderImpl;
import cdm.product.template.StrikeSchedule.StrikeScheduleImpl;
import cdm.product.template.meta.StrikeScheduleMeta;
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
 * A class describing a schedule of cap or floor rates.
 * @version ${project.version}
 */
@RosettaDataType(value="StrikeSchedule", builder=StrikeSchedule.StrikeScheduleBuilderImpl.class, version="${project.version}")
public interface StrikeSchedule extends RateSchedule {

	StrikeScheduleMeta metaData = new StrikeScheduleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The buyer of the option.
	 */
	PayerReceiverEnum getBuyer();
	/**
	 * The party that has sold.
	 */
	PayerReceiverEnum getSeller();

	/*********************** Build Methods  ***********************/
	StrikeSchedule build();
	
	StrikeSchedule.StrikeScheduleBuilder toBuilder();
	
	static StrikeSchedule.StrikeScheduleBuilder builder() {
		return new StrikeSchedule.StrikeScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends StrikeSchedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends StrikeSchedule> getType() {
		return StrikeSchedule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("price"), processor, ReferenceWithMetaPriceSchedule.class, getPrice());
		processor.processBasic(path.newSubPath("buyer"), PayerReceiverEnum.class, getBuyer(), this);
		processor.processBasic(path.newSubPath("seller"), PayerReceiverEnum.class, getSeller(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface StrikeScheduleBuilder extends StrikeSchedule, RateSchedule.RateScheduleBuilder, RosettaModelObjectBuilder {
		StrikeSchedule.StrikeScheduleBuilder setPrice(ReferenceWithMetaPriceSchedule price0);
		StrikeSchedule.StrikeScheduleBuilder setPriceValue(PriceSchedule price1);
		StrikeSchedule.StrikeScheduleBuilder setBuyer(PayerReceiverEnum buyer);
		StrikeSchedule.StrikeScheduleBuilder setSeller(PayerReceiverEnum seller);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("price"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getPrice());
			processor.processBasic(path.newSubPath("buyer"), PayerReceiverEnum.class, getBuyer(), this);
			processor.processBasic(path.newSubPath("seller"), PayerReceiverEnum.class, getSeller(), this);
		}
		

		StrikeSchedule.StrikeScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of StrikeSchedule  ***********************/
	class StrikeScheduleImpl extends RateSchedule.RateScheduleImpl implements StrikeSchedule {
		private final PayerReceiverEnum buyer;
		private final PayerReceiverEnum seller;
		
		protected StrikeScheduleImpl(StrikeSchedule.StrikeScheduleBuilder builder) {
			super(builder);
			this.buyer = builder.getBuyer();
			this.seller = builder.getSeller();
		}
		
		@Override
		@RosettaAttribute("buyer")
		public PayerReceiverEnum getBuyer() {
			return buyer;
		}
		
		@Override
		@RosettaAttribute("seller")
		public PayerReceiverEnum getSeller() {
			return seller;
		}
		
		@Override
		public StrikeSchedule build() {
			return this;
		}
		
		@Override
		public StrikeSchedule.StrikeScheduleBuilder toBuilder() {
			StrikeSchedule.StrikeScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(StrikeSchedule.StrikeScheduleBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getBuyer()).ifPresent(builder::setBuyer);
			ofNullable(getSeller()).ifPresent(builder::setSeller);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			StrikeSchedule _that = getType().cast(o);
		
			if (!Objects.equals(buyer, _that.getBuyer())) return false;
			if (!Objects.equals(seller, _that.getSeller())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (buyer != null ? buyer.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (seller != null ? seller.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StrikeSchedule {" +
				"buyer=" + this.buyer + ", " +
				"seller=" + this.seller +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of StrikeSchedule  ***********************/
	class StrikeScheduleBuilderImpl extends RateSchedule.RateScheduleBuilderImpl  implements StrikeSchedule.StrikeScheduleBuilder {
	
		protected PayerReceiverEnum buyer;
		protected PayerReceiverEnum seller;
	
		public StrikeScheduleBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("buyer")
		public PayerReceiverEnum getBuyer() {
			return buyer;
		}
		
		@Override
		@RosettaAttribute("seller")
		public PayerReceiverEnum getSeller() {
			return seller;
		}
		
	
		@Override
		@RosettaAttribute("price")
		public StrikeSchedule.StrikeScheduleBuilder setPrice(ReferenceWithMetaPriceSchedule price) {
			this.price = price==null?null:price.toBuilder();
			return this;
		}
		@Override
		public StrikeSchedule.StrikeScheduleBuilder setPriceValue(PriceSchedule price) {
			this.getOrCreatePrice().setValue(price);
			return this;
		}
		@Override
		@RosettaAttribute("buyer")
		public StrikeSchedule.StrikeScheduleBuilder setBuyer(PayerReceiverEnum buyer) {
			this.buyer = buyer==null?null:buyer;
			return this;
		}
		@Override
		@RosettaAttribute("seller")
		public StrikeSchedule.StrikeScheduleBuilder setSeller(PayerReceiverEnum seller) {
			this.seller = seller==null?null:seller;
			return this;
		}
		
		@Override
		public StrikeSchedule build() {
			return new StrikeSchedule.StrikeScheduleImpl(this);
		}
		
		@Override
		public StrikeSchedule.StrikeScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StrikeSchedule.StrikeScheduleBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getBuyer()!=null) return true;
			if (getSeller()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StrikeSchedule.StrikeScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			StrikeSchedule.StrikeScheduleBuilder o = (StrikeSchedule.StrikeScheduleBuilder) other;
			
			
			merger.mergeBasic(getBuyer(), o.getBuyer(), this::setBuyer);
			merger.mergeBasic(getSeller(), o.getSeller(), this::setSeller);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			StrikeSchedule _that = getType().cast(o);
		
			if (!Objects.equals(buyer, _that.getBuyer())) return false;
			if (!Objects.equals(seller, _that.getSeller())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (buyer != null ? buyer.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (seller != null ? seller.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StrikeScheduleBuilder {" +
				"buyer=" + this.buyer + ", " +
				"seller=" + this.seller +
			'}' + " " + super.toString();
		}
	}
}
