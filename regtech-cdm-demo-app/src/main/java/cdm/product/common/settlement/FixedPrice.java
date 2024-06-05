package cdm.product.common.settlement;

import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder;
import cdm.product.common.settlement.FixedPrice;
import cdm.product.common.settlement.FixedPrice.FixedPriceBuilder;
import cdm.product.common.settlement.FixedPrice.FixedPriceBuilderImpl;
import cdm.product.common.settlement.FixedPrice.FixedPriceImpl;
import cdm.product.common.settlement.meta.FixedPriceMeta;
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
 * A predefined price accorded by the counterparties.
 * @version ${project.version}
 */
@RosettaDataType(value="FixedPrice", builder=FixedPrice.FixedPriceBuilderImpl.class, version="${project.version}")
public interface FixedPrice extends RosettaModelObject {

	FixedPriceMeta metaData = new FixedPriceMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Fixed price step schedule, including an initial price specified as an absolute number.
	 */
	ReferenceWithMetaPriceSchedule getPrice();

	/*********************** Build Methods  ***********************/
	FixedPrice build();
	
	FixedPrice.FixedPriceBuilder toBuilder();
	
	static FixedPrice.FixedPriceBuilder builder() {
		return new FixedPrice.FixedPriceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FixedPrice> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FixedPrice> getType() {
		return FixedPrice.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("price"), processor, ReferenceWithMetaPriceSchedule.class, getPrice());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FixedPriceBuilder extends FixedPrice, RosettaModelObjectBuilder {
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreatePrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getPrice();
		FixedPrice.FixedPriceBuilder setPrice(ReferenceWithMetaPriceSchedule price0);
		FixedPrice.FixedPriceBuilder setPriceValue(PriceSchedule price1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("price"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getPrice());
		}
		

		FixedPrice.FixedPriceBuilder prune();
	}

	/*********************** Immutable Implementation of FixedPrice  ***********************/
	class FixedPriceImpl implements FixedPrice {
		private final ReferenceWithMetaPriceSchedule price;
		
		protected FixedPriceImpl(FixedPrice.FixedPriceBuilder builder) {
			this.price = ofNullable(builder.getPrice()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("price")
		public ReferenceWithMetaPriceSchedule getPrice() {
			return price;
		}
		
		@Override
		public FixedPrice build() {
			return this;
		}
		
		@Override
		public FixedPrice.FixedPriceBuilder toBuilder() {
			FixedPrice.FixedPriceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FixedPrice.FixedPriceBuilder builder) {
			ofNullable(getPrice()).ifPresent(builder::setPrice);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FixedPrice _that = getType().cast(o);
		
			if (!Objects.equals(price, _that.getPrice())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (price != null ? price.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FixedPrice {" +
				"price=" + this.price +
			'}';
		}
	}

	/*********************** Builder Implementation of FixedPrice  ***********************/
	class FixedPriceBuilderImpl implements FixedPrice.FixedPriceBuilder {
	
		protected ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder price;
	
		public FixedPriceBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("price")
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getPrice() {
			return price;
		}
		
		@Override
		public ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreatePrice() {
			ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder result;
			if (price!=null) {
				result = price;
			}
			else {
				result = price = ReferenceWithMetaPriceSchedule.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("price")
		public FixedPrice.FixedPriceBuilder setPrice(ReferenceWithMetaPriceSchedule price) {
			this.price = price==null?null:price.toBuilder();
			return this;
		}
		@Override
		public FixedPrice.FixedPriceBuilder setPriceValue(PriceSchedule price) {
			this.getOrCreatePrice().setValue(price);
			return this;
		}
		
		@Override
		public FixedPrice build() {
			return new FixedPrice.FixedPriceImpl(this);
		}
		
		@Override
		public FixedPrice.FixedPriceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FixedPrice.FixedPriceBuilder prune() {
			if (price!=null && !price.prune().hasData()) price = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPrice()!=null && getPrice().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FixedPrice.FixedPriceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FixedPrice.FixedPriceBuilder o = (FixedPrice.FixedPriceBuilder) other;
			
			merger.mergeRosetta(getPrice(), o.getPrice(), this::setPrice);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FixedPrice _that = getType().cast(o);
		
			if (!Objects.equals(price, _that.getPrice())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (price != null ? price.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FixedPriceBuilder {" +
				"price=" + this.price +
			'}';
		}
	}
}
