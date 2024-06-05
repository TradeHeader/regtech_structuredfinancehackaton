package cdm.product.asset;

import cdm.base.datetime.DayOfWeekEnum;
import cdm.base.math.Quantity;
import cdm.observable.asset.Price;
import cdm.product.asset.AssetDeliveryProfileBlock;
import cdm.product.asset.AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder;
import cdm.product.asset.AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilderImpl;
import cdm.product.asset.AssetDeliveryProfileBlock.AssetDeliveryProfileBlockImpl;
import cdm.product.asset.meta.AssetDeliveryProfileBlockMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.util.ListEquals;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Defines a delivery profile block, including start and end time, days of the week, duration, delivery capacity and price time interval quantity.
 * @version ${project.version}
 */
@RosettaDataType(value="AssetDeliveryProfileBlock", builder=AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilderImpl.class, version="${project.version}")
public interface AssetDeliveryProfileBlock extends RosettaModelObject {

	AssetDeliveryProfileBlockMeta metaData = new AssetDeliveryProfileBlockMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The start time of the delivery interval for each block or shape.
	 */
	LocalTime getStartTime();
	/**
	 * The end time of the delivery interval for each block or shape.
	 */
	LocalTime getEndTime();
	/**
	 * The days of the week of the delivery.
	 */
	List<DayOfWeekEnum> getDayOfWeek();
	/**
	 * The number of units included in the transaction for each delivery interval
	 */
	Quantity getDeliveryCapacity();
	/**
	 * Price per quantity per delivery time interval.
	 */
	Price getPriceTimeIntervalQuantity();

	/*********************** Build Methods  ***********************/
	AssetDeliveryProfileBlock build();
	
	AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder toBuilder();
	
	static AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder builder() {
		return new AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AssetDeliveryProfileBlock> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AssetDeliveryProfileBlock> getType() {
		return AssetDeliveryProfileBlock.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("startTime"), LocalTime.class, getStartTime(), this);
		processor.processBasic(path.newSubPath("endTime"), LocalTime.class, getEndTime(), this);
		processor.processBasic(path.newSubPath("dayOfWeek"), DayOfWeekEnum.class, getDayOfWeek(), this);
		processRosetta(path.newSubPath("deliveryCapacity"), processor, Quantity.class, getDeliveryCapacity());
		processRosetta(path.newSubPath("priceTimeIntervalQuantity"), processor, Price.class, getPriceTimeIntervalQuantity());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AssetDeliveryProfileBlockBuilder extends AssetDeliveryProfileBlock, RosettaModelObjectBuilder {
		Quantity.QuantityBuilder getOrCreateDeliveryCapacity();
		Quantity.QuantityBuilder getDeliveryCapacity();
		Price.PriceBuilder getOrCreatePriceTimeIntervalQuantity();
		Price.PriceBuilder getPriceTimeIntervalQuantity();
		AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder setStartTime(LocalTime startTime);
		AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder setEndTime(LocalTime endTime);
		AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder addDayOfWeek(DayOfWeekEnum dayOfWeek0);
		AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder addDayOfWeek(DayOfWeekEnum dayOfWeek1, int _idx);
		AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder addDayOfWeek(List<? extends DayOfWeekEnum> dayOfWeek2);
		AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder setDayOfWeek(List<? extends DayOfWeekEnum> dayOfWeek3);
		AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder setDeliveryCapacity(Quantity deliveryCapacity);
		AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder setPriceTimeIntervalQuantity(Price priceTimeIntervalQuantity);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("startTime"), LocalTime.class, getStartTime(), this);
			processor.processBasic(path.newSubPath("endTime"), LocalTime.class, getEndTime(), this);
			processor.processBasic(path.newSubPath("dayOfWeek"), DayOfWeekEnum.class, getDayOfWeek(), this);
			processRosetta(path.newSubPath("deliveryCapacity"), processor, Quantity.QuantityBuilder.class, getDeliveryCapacity());
			processRosetta(path.newSubPath("priceTimeIntervalQuantity"), processor, Price.PriceBuilder.class, getPriceTimeIntervalQuantity());
		}
		

		AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder prune();
	}

	/*********************** Immutable Implementation of AssetDeliveryProfileBlock  ***********************/
	class AssetDeliveryProfileBlockImpl implements AssetDeliveryProfileBlock {
		private final LocalTime startTime;
		private final LocalTime endTime;
		private final List<DayOfWeekEnum> dayOfWeek;
		private final Quantity deliveryCapacity;
		private final Price priceTimeIntervalQuantity;
		
		protected AssetDeliveryProfileBlockImpl(AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder builder) {
			this.startTime = builder.getStartTime();
			this.endTime = builder.getEndTime();
			this.dayOfWeek = ofNullable(builder.getDayOfWeek()).filter(_l->!_l.isEmpty()).map(ImmutableList::copyOf).orElse(null);
			this.deliveryCapacity = ofNullable(builder.getDeliveryCapacity()).map(f->f.build()).orElse(null);
			this.priceTimeIntervalQuantity = ofNullable(builder.getPriceTimeIntervalQuantity()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("startTime")
		public LocalTime getStartTime() {
			return startTime;
		}
		
		@Override
		@RosettaAttribute("endTime")
		public LocalTime getEndTime() {
			return endTime;
		}
		
		@Override
		@RosettaAttribute("dayOfWeek")
		public List<DayOfWeekEnum> getDayOfWeek() {
			return dayOfWeek;
		}
		
		@Override
		@RosettaAttribute("deliveryCapacity")
		public Quantity getDeliveryCapacity() {
			return deliveryCapacity;
		}
		
		@Override
		@RosettaAttribute("priceTimeIntervalQuantity")
		public Price getPriceTimeIntervalQuantity() {
			return priceTimeIntervalQuantity;
		}
		
		@Override
		public AssetDeliveryProfileBlock build() {
			return this;
		}
		
		@Override
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder toBuilder() {
			AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder builder) {
			ofNullable(getStartTime()).ifPresent(builder::setStartTime);
			ofNullable(getEndTime()).ifPresent(builder::setEndTime);
			ofNullable(getDayOfWeek()).ifPresent(builder::setDayOfWeek);
			ofNullable(getDeliveryCapacity()).ifPresent(builder::setDeliveryCapacity);
			ofNullable(getPriceTimeIntervalQuantity()).ifPresent(builder::setPriceTimeIntervalQuantity);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetDeliveryProfileBlock _that = getType().cast(o);
		
			if (!Objects.equals(startTime, _that.getStartTime())) return false;
			if (!Objects.equals(endTime, _that.getEndTime())) return false;
			if (!ListEquals.listEquals(dayOfWeek, _that.getDayOfWeek())) return false;
			if (!Objects.equals(deliveryCapacity, _that.getDeliveryCapacity())) return false;
			if (!Objects.equals(priceTimeIntervalQuantity, _that.getPriceTimeIntervalQuantity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startTime != null ? startTime.hashCode() : 0);
			_result = 31 * _result + (endTime != null ? endTime.hashCode() : 0);
			_result = 31 * _result + (dayOfWeek != null ? dayOfWeek.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (deliveryCapacity != null ? deliveryCapacity.hashCode() : 0);
			_result = 31 * _result + (priceTimeIntervalQuantity != null ? priceTimeIntervalQuantity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetDeliveryProfileBlock {" +
				"startTime=" + this.startTime + ", " +
				"endTime=" + this.endTime + ", " +
				"dayOfWeek=" + this.dayOfWeek + ", " +
				"deliveryCapacity=" + this.deliveryCapacity + ", " +
				"priceTimeIntervalQuantity=" + this.priceTimeIntervalQuantity +
			'}';
		}
	}

	/*********************** Builder Implementation of AssetDeliveryProfileBlock  ***********************/
	class AssetDeliveryProfileBlockBuilderImpl implements AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder {
	
		protected LocalTime startTime;
		protected LocalTime endTime;
		protected List<DayOfWeekEnum> dayOfWeek = new ArrayList<>();
		protected Quantity.QuantityBuilder deliveryCapacity;
		protected Price.PriceBuilder priceTimeIntervalQuantity;
	
		public AssetDeliveryProfileBlockBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("startTime")
		public LocalTime getStartTime() {
			return startTime;
		}
		
		@Override
		@RosettaAttribute("endTime")
		public LocalTime getEndTime() {
			return endTime;
		}
		
		@Override
		@RosettaAttribute("dayOfWeek")
		public List<DayOfWeekEnum> getDayOfWeek() {
			return dayOfWeek;
		}
		
		@Override
		@RosettaAttribute("deliveryCapacity")
		public Quantity.QuantityBuilder getDeliveryCapacity() {
			return deliveryCapacity;
		}
		
		@Override
		public Quantity.QuantityBuilder getOrCreateDeliveryCapacity() {
			Quantity.QuantityBuilder result;
			if (deliveryCapacity!=null) {
				result = deliveryCapacity;
			}
			else {
				result = deliveryCapacity = Quantity.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("priceTimeIntervalQuantity")
		public Price.PriceBuilder getPriceTimeIntervalQuantity() {
			return priceTimeIntervalQuantity;
		}
		
		@Override
		public Price.PriceBuilder getOrCreatePriceTimeIntervalQuantity() {
			Price.PriceBuilder result;
			if (priceTimeIntervalQuantity!=null) {
				result = priceTimeIntervalQuantity;
			}
			else {
				result = priceTimeIntervalQuantity = Price.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("startTime")
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder setStartTime(LocalTime startTime) {
			this.startTime = startTime==null?null:startTime;
			return this;
		}
		@Override
		@RosettaAttribute("endTime")
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder setEndTime(LocalTime endTime) {
			this.endTime = endTime==null?null:endTime;
			return this;
		}
		@Override
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder addDayOfWeek(DayOfWeekEnum dayOfWeek) {
			if (dayOfWeek!=null) this.dayOfWeek.add(dayOfWeek);
			return this;
		}
		
		@Override
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder addDayOfWeek(DayOfWeekEnum dayOfWeek, int _idx) {
			getIndex(this.dayOfWeek, _idx, () -> dayOfWeek);
			return this;
		}
		@Override 
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder addDayOfWeek(List<? extends DayOfWeekEnum> dayOfWeeks) {
			if (dayOfWeeks != null) {
				for (DayOfWeekEnum toAdd : dayOfWeeks) {
					this.dayOfWeek.add(toAdd);
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("dayOfWeek")
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder setDayOfWeek(List<? extends DayOfWeekEnum> dayOfWeeks) {
			if (dayOfWeeks == null)  {
				this.dayOfWeek = new ArrayList<>();
			}
			else {
				this.dayOfWeek = dayOfWeeks.stream()
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("deliveryCapacity")
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder setDeliveryCapacity(Quantity deliveryCapacity) {
			this.deliveryCapacity = deliveryCapacity==null?null:deliveryCapacity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("priceTimeIntervalQuantity")
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder setPriceTimeIntervalQuantity(Price priceTimeIntervalQuantity) {
			this.priceTimeIntervalQuantity = priceTimeIntervalQuantity==null?null:priceTimeIntervalQuantity.toBuilder();
			return this;
		}
		
		@Override
		public AssetDeliveryProfileBlock build() {
			return new AssetDeliveryProfileBlock.AssetDeliveryProfileBlockImpl(this);
		}
		
		@Override
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder prune() {
			if (deliveryCapacity!=null && !deliveryCapacity.prune().hasData()) deliveryCapacity = null;
			if (priceTimeIntervalQuantity!=null && !priceTimeIntervalQuantity.prune().hasData()) priceTimeIntervalQuantity = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getStartTime()!=null) return true;
			if (getEndTime()!=null) return true;
			if (getDayOfWeek()!=null && !getDayOfWeek().isEmpty()) return true;
			if (getDeliveryCapacity()!=null && getDeliveryCapacity().hasData()) return true;
			if (getPriceTimeIntervalQuantity()!=null && getPriceTimeIntervalQuantity().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder o = (AssetDeliveryProfileBlock.AssetDeliveryProfileBlockBuilder) other;
			
			merger.mergeRosetta(getDeliveryCapacity(), o.getDeliveryCapacity(), this::setDeliveryCapacity);
			merger.mergeRosetta(getPriceTimeIntervalQuantity(), o.getPriceTimeIntervalQuantity(), this::setPriceTimeIntervalQuantity);
			
			merger.mergeBasic(getStartTime(), o.getStartTime(), this::setStartTime);
			merger.mergeBasic(getEndTime(), o.getEndTime(), this::setEndTime);
			merger.mergeBasic(getDayOfWeek(), o.getDayOfWeek(), (Consumer<DayOfWeekEnum>) this::addDayOfWeek);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AssetDeliveryProfileBlock _that = getType().cast(o);
		
			if (!Objects.equals(startTime, _that.getStartTime())) return false;
			if (!Objects.equals(endTime, _that.getEndTime())) return false;
			if (!ListEquals.listEquals(dayOfWeek, _that.getDayOfWeek())) return false;
			if (!Objects.equals(deliveryCapacity, _that.getDeliveryCapacity())) return false;
			if (!Objects.equals(priceTimeIntervalQuantity, _that.getPriceTimeIntervalQuantity())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (startTime != null ? startTime.hashCode() : 0);
			_result = 31 * _result + (endTime != null ? endTime.hashCode() : 0);
			_result = 31 * _result + (dayOfWeek != null ? dayOfWeek.stream().map(Object::getClass).map(Class::getName).mapToInt(String::hashCode).sum() : 0);
			_result = 31 * _result + (deliveryCapacity != null ? deliveryCapacity.hashCode() : 0);
			_result = 31 * _result + (priceTimeIntervalQuantity != null ? priceTimeIntervalQuantity.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AssetDeliveryProfileBlockBuilder {" +
				"startTime=" + this.startTime + ", " +
				"endTime=" + this.endTime + ", " +
				"dayOfWeek=" + this.dayOfWeek + ", " +
				"deliveryCapacity=" + this.deliveryCapacity + ", " +
				"priceTimeIntervalQuantity=" + this.priceTimeIntervalQuantity +
			'}';
		}
	}
}
