package cdm.product.common.settlement;

import cdm.base.datetime.Offset;
import cdm.product.asset.RollSourceCalendarEnum;
import cdm.product.common.settlement.RollFeature;
import cdm.product.common.settlement.RollFeature.RollFeatureBuilder;
import cdm.product.common.settlement.RollFeature.RollFeatureBuilderImpl;
import cdm.product.common.settlement.RollFeature.RollFeatureImpl;
import cdm.product.common.settlement.meta.RollFeatureMeta;
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
 * Used in conjunction with an exchange-based pricing source. Identifies a way in which the futures contracts referenced will roll between periods. 
 * @version ${project.version}
 */
@RosettaDataType(value="RollFeature", builder=RollFeature.RollFeatureBuilderImpl.class, version="${project.version}")
public interface RollFeature extends RosettaModelObject {

	RollFeatureMeta metaData = new RollFeatureMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Used in conjunction with an exchange-based pricing source. Identifies a date source calendar from which the pricing dates and thus roll to the next contract will be based off (e.g. pricing is based on the NYMEX WTI First Nearby Futures Contract, if Future is chosen, the pricing will roll to the next futures contract on expiration, if ListedOption is chosen, the pricing will roll to the next futures contract on the Option expiration date which is three business days before the expiration of the NYMEX WTI futures contract.) Omitting this element will result in the default behavior expected with the pricing source described within the commodity element.
	 */
	RollSourceCalendarEnum getRollSourceCalendar();
	/**
	 * Specifies, for a Commodity Transaction that references a delivery date for a listed future, the day on which the specified future will roll to the next nearby month prior to the expiration of the referenced future. If the future will not roll at all - i.e. the price will be taken from the expiring contract, 0 should be specified here. If the future will roll to the next nearby on the last trading day - i.e. the price will be taken from the next nearby on the last trading day, then 1 should be specified and so on.
	 */
	Offset getDeliveryDateRollConvention();

	/*********************** Build Methods  ***********************/
	RollFeature build();
	
	RollFeature.RollFeatureBuilder toBuilder();
	
	static RollFeature.RollFeatureBuilder builder() {
		return new RollFeature.RollFeatureBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends RollFeature> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends RollFeature> getType() {
		return RollFeature.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("rollSourceCalendar"), RollSourceCalendarEnum.class, getRollSourceCalendar(), this);
		processRosetta(path.newSubPath("deliveryDateRollConvention"), processor, Offset.class, getDeliveryDateRollConvention());
	}
	

	/*********************** Builder Interface  ***********************/
	interface RollFeatureBuilder extends RollFeature, RosettaModelObjectBuilder {
		Offset.OffsetBuilder getOrCreateDeliveryDateRollConvention();
		Offset.OffsetBuilder getDeliveryDateRollConvention();
		RollFeature.RollFeatureBuilder setRollSourceCalendar(RollSourceCalendarEnum rollSourceCalendar);
		RollFeature.RollFeatureBuilder setDeliveryDateRollConvention(Offset deliveryDateRollConvention);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("rollSourceCalendar"), RollSourceCalendarEnum.class, getRollSourceCalendar(), this);
			processRosetta(path.newSubPath("deliveryDateRollConvention"), processor, Offset.OffsetBuilder.class, getDeliveryDateRollConvention());
		}
		

		RollFeature.RollFeatureBuilder prune();
	}

	/*********************** Immutable Implementation of RollFeature  ***********************/
	class RollFeatureImpl implements RollFeature {
		private final RollSourceCalendarEnum rollSourceCalendar;
		private final Offset deliveryDateRollConvention;
		
		protected RollFeatureImpl(RollFeature.RollFeatureBuilder builder) {
			this.rollSourceCalendar = builder.getRollSourceCalendar();
			this.deliveryDateRollConvention = ofNullable(builder.getDeliveryDateRollConvention()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("rollSourceCalendar")
		public RollSourceCalendarEnum getRollSourceCalendar() {
			return rollSourceCalendar;
		}
		
		@Override
		@RosettaAttribute("deliveryDateRollConvention")
		public Offset getDeliveryDateRollConvention() {
			return deliveryDateRollConvention;
		}
		
		@Override
		public RollFeature build() {
			return this;
		}
		
		@Override
		public RollFeature.RollFeatureBuilder toBuilder() {
			RollFeature.RollFeatureBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(RollFeature.RollFeatureBuilder builder) {
			ofNullable(getRollSourceCalendar()).ifPresent(builder::setRollSourceCalendar);
			ofNullable(getDeliveryDateRollConvention()).ifPresent(builder::setDeliveryDateRollConvention);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RollFeature _that = getType().cast(o);
		
			if (!Objects.equals(rollSourceCalendar, _that.getRollSourceCalendar())) return false;
			if (!Objects.equals(deliveryDateRollConvention, _that.getDeliveryDateRollConvention())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (rollSourceCalendar != null ? rollSourceCalendar.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (deliveryDateRollConvention != null ? deliveryDateRollConvention.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RollFeature {" +
				"rollSourceCalendar=" + this.rollSourceCalendar + ", " +
				"deliveryDateRollConvention=" + this.deliveryDateRollConvention +
			'}';
		}
	}

	/*********************** Builder Implementation of RollFeature  ***********************/
	class RollFeatureBuilderImpl implements RollFeature.RollFeatureBuilder {
	
		protected RollSourceCalendarEnum rollSourceCalendar;
		protected Offset.OffsetBuilder deliveryDateRollConvention;
	
		public RollFeatureBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("rollSourceCalendar")
		public RollSourceCalendarEnum getRollSourceCalendar() {
			return rollSourceCalendar;
		}
		
		@Override
		@RosettaAttribute("deliveryDateRollConvention")
		public Offset.OffsetBuilder getDeliveryDateRollConvention() {
			return deliveryDateRollConvention;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreateDeliveryDateRollConvention() {
			Offset.OffsetBuilder result;
			if (deliveryDateRollConvention!=null) {
				result = deliveryDateRollConvention;
			}
			else {
				result = deliveryDateRollConvention = Offset.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("rollSourceCalendar")
		public RollFeature.RollFeatureBuilder setRollSourceCalendar(RollSourceCalendarEnum rollSourceCalendar) {
			this.rollSourceCalendar = rollSourceCalendar==null?null:rollSourceCalendar;
			return this;
		}
		@Override
		@RosettaAttribute("deliveryDateRollConvention")
		public RollFeature.RollFeatureBuilder setDeliveryDateRollConvention(Offset deliveryDateRollConvention) {
			this.deliveryDateRollConvention = deliveryDateRollConvention==null?null:deliveryDateRollConvention.toBuilder();
			return this;
		}
		
		@Override
		public RollFeature build() {
			return new RollFeature.RollFeatureImpl(this);
		}
		
		@Override
		public RollFeature.RollFeatureBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RollFeature.RollFeatureBuilder prune() {
			if (deliveryDateRollConvention!=null && !deliveryDateRollConvention.prune().hasData()) deliveryDateRollConvention = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getRollSourceCalendar()!=null) return true;
			if (getDeliveryDateRollConvention()!=null && getDeliveryDateRollConvention().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public RollFeature.RollFeatureBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			RollFeature.RollFeatureBuilder o = (RollFeature.RollFeatureBuilder) other;
			
			merger.mergeRosetta(getDeliveryDateRollConvention(), o.getDeliveryDateRollConvention(), this::setDeliveryDateRollConvention);
			
			merger.mergeBasic(getRollSourceCalendar(), o.getRollSourceCalendar(), this::setRollSourceCalendar);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			RollFeature _that = getType().cast(o);
		
			if (!Objects.equals(rollSourceCalendar, _that.getRollSourceCalendar())) return false;
			if (!Objects.equals(deliveryDateRollConvention, _that.getDeliveryDateRollConvention())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (rollSourceCalendar != null ? rollSourceCalendar.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (deliveryDateRollConvention != null ? deliveryDateRollConvention.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "RollFeatureBuilder {" +
				"rollSourceCalendar=" + this.rollSourceCalendar + ", " +
				"deliveryDateRollConvention=" + this.deliveryDateRollConvention +
			'}';
		}
	}
}
