package cdm.product.common.schedule;

import cdm.observable.asset.PriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule;
import cdm.observable.asset.metafields.ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder;
import cdm.product.common.schedule.RateSchedule;
import cdm.product.common.schedule.RateSchedule.RateScheduleBuilder;
import cdm.product.common.schedule.RateSchedule.RateScheduleBuilderImpl;
import cdm.product.common.schedule.RateSchedule.RateScheduleImpl;
import cdm.product.common.schedule.meta.RateScheduleMeta;
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
 * A class defining a schedule of rates or amounts in terms of an initial value and then a series of step date and value pairs. On each step date the rate or amount changes to the new step value. The series of step date and value pairs are optional. If not specified, this implies that the initial value remains unchanged over time.
 * @version ${project.version}
 */
@RosettaDataType(value="RateSchedule", builder=RateSchedule.RateScheduleBuilderImpl.class, version="${project.version}")
public interface RateSchedule extends RosettaModelObject {

	RateScheduleMeta metaData = new RateScheduleMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The initial rate. An initial rate of 5% would be represented as 0.05.
	 */
	ReferenceWithMetaPriceSchedule getPrice();

	/*********************** Build Methods  ***********************/
	RateSchedule build();
	
	RateSchedule.RateScheduleBuilder toBuilder();
	
	static RateSchedule.RateScheduleBuilder builder() {
		return new RateSchedule.RateScheduleBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends RateSchedule> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends RateSchedule> getType() {
		return RateSchedule.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("price"), processor, ReferenceWithMetaPriceSchedule.class, getPrice());
	}
	

	/*********************** Builder Interface  ***********************/
	interface RateScheduleBuilder extends RateSchedule, RosettaModelObjectBuilder {
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getOrCreatePrice();
		ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder getPrice();
		RateSchedule.RateScheduleBuilder setPrice(ReferenceWithMetaPriceSchedule price0);
		RateSchedule.RateScheduleBuilder setPriceValue(PriceSchedule price1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("price"), processor, ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder.class, getPrice());
		}
		

		RateSchedule.RateScheduleBuilder prune();
	}

	/*********************** Immutable Implementation of RateSchedule  ***********************/
	class RateScheduleImpl implements RateSchedule {
		private final ReferenceWithMetaPriceSchedule price;
		
		protected RateScheduleImpl(RateSchedule.RateScheduleBuilder builder) {
			this.price = ofNullable(builder.getPrice()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("price")
		public ReferenceWithMetaPriceSchedule getPrice() {
			return price;
		}
		
		@Override
		public RateSchedule build() {
			return this;
		}
		
		@Override
		public RateSchedule.RateScheduleBuilder toBuilder() {
			RateSchedule.RateScheduleBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(RateSchedule.RateScheduleBuilder builder) {
			ofNullable(getPrice()).ifPresent(builder::setPrice);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RateSchedule _that = getType().cast(o);
		
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
			return "RateSchedule {" +
				"price=" + this.price +
			'}';
		}
	}

	/*********************** Builder Implementation of RateSchedule  ***********************/
	class RateScheduleBuilderImpl implements RateSchedule.RateScheduleBuilder {
	
		protected ReferenceWithMetaPriceSchedule.ReferenceWithMetaPriceScheduleBuilder price;
	
		public RateScheduleBuilderImpl() {
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
		public RateSchedule.RateScheduleBuilder setPrice(ReferenceWithMetaPriceSchedule price) {
			this.price = price==null?null:price.toBuilder();
			return this;
		}
		@Override
		public RateSchedule.RateScheduleBuilder setPriceValue(PriceSchedule price) {
			this.getOrCreatePrice().setValue(price);
			return this;
		}
		
		@Override
		public RateSchedule build() {
			return new RateSchedule.RateScheduleImpl(this);
		}
		
		@Override
		public RateSchedule.RateScheduleBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RateSchedule.RateScheduleBuilder prune() {
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
		public RateSchedule.RateScheduleBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			RateSchedule.RateScheduleBuilder o = (RateSchedule.RateScheduleBuilder) other;
			
			merger.mergeRosetta(getPrice(), o.getPrice(), this::setPrice);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RateSchedule _that = getType().cast(o);
		
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
			return "RateScheduleBuilder {" +
				"price=" + this.price +
			'}';
		}
	}
}
