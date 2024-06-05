package cdm.product.asset;

import cdm.product.asset.FixedRateSpecification;
import cdm.product.asset.FixedRateSpecification.FixedRateSpecificationBuilder;
import cdm.product.asset.FixedRateSpecification.FixedRateSpecificationBuilderImpl;
import cdm.product.asset.FixedRateSpecification.FixedRateSpecificationImpl;
import cdm.product.asset.meta.FixedRateSpecificationMeta;
import cdm.product.common.schedule.RateSchedule;
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
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Type defining the specification for a fixed rate.
 * @version ${project.version}
 */
@RosettaDataType(value="FixedRateSpecification", builder=FixedRateSpecification.FixedRateSpecificationBuilderImpl.class, version="${project.version}")
public interface FixedRateSpecification extends RosettaModelObject, GlobalKey {

	FixedRateSpecificationMeta metaData = new FixedRateSpecificationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The fixed rate or fixed rate schedule expressed as explicit fixed rates and dates. In the case of a schedule, the step dates may be subject to adjustment in accordance with any adjustments specified in calculationPeriodDatesAdjustments.
	 */
	RateSchedule getRateSchedule();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	FixedRateSpecification build();
	
	FixedRateSpecification.FixedRateSpecificationBuilder toBuilder();
	
	static FixedRateSpecification.FixedRateSpecificationBuilder builder() {
		return new FixedRateSpecification.FixedRateSpecificationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FixedRateSpecification> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FixedRateSpecification> getType() {
		return FixedRateSpecification.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("rateSchedule"), processor, RateSchedule.class, getRateSchedule());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FixedRateSpecificationBuilder extends FixedRateSpecification, RosettaModelObjectBuilder {
		RateSchedule.RateScheduleBuilder getOrCreateRateSchedule();
		RateSchedule.RateScheduleBuilder getRateSchedule();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		FixedRateSpecification.FixedRateSpecificationBuilder setRateSchedule(RateSchedule rateSchedule);
		FixedRateSpecification.FixedRateSpecificationBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("rateSchedule"), processor, RateSchedule.RateScheduleBuilder.class, getRateSchedule());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		FixedRateSpecification.FixedRateSpecificationBuilder prune();
	}

	/*********************** Immutable Implementation of FixedRateSpecification  ***********************/
	class FixedRateSpecificationImpl implements FixedRateSpecification {
		private final RateSchedule rateSchedule;
		private final MetaFields meta;
		
		protected FixedRateSpecificationImpl(FixedRateSpecification.FixedRateSpecificationBuilder builder) {
			this.rateSchedule = ofNullable(builder.getRateSchedule()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("rateSchedule")
		public RateSchedule getRateSchedule() {
			return rateSchedule;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public FixedRateSpecification build() {
			return this;
		}
		
		@Override
		public FixedRateSpecification.FixedRateSpecificationBuilder toBuilder() {
			FixedRateSpecification.FixedRateSpecificationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FixedRateSpecification.FixedRateSpecificationBuilder builder) {
			ofNullable(getRateSchedule()).ifPresent(builder::setRateSchedule);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FixedRateSpecification _that = getType().cast(o);
		
			if (!Objects.equals(rateSchedule, _that.getRateSchedule())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (rateSchedule != null ? rateSchedule.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FixedRateSpecification {" +
				"rateSchedule=" + this.rateSchedule + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of FixedRateSpecification  ***********************/
	class FixedRateSpecificationBuilderImpl implements FixedRateSpecification.FixedRateSpecificationBuilder, GlobalKeyBuilder {
	
		protected RateSchedule.RateScheduleBuilder rateSchedule;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public FixedRateSpecificationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("rateSchedule")
		public RateSchedule.RateScheduleBuilder getRateSchedule() {
			return rateSchedule;
		}
		
		@Override
		public RateSchedule.RateScheduleBuilder getOrCreateRateSchedule() {
			RateSchedule.RateScheduleBuilder result;
			if (rateSchedule!=null) {
				result = rateSchedule;
			}
			else {
				result = rateSchedule = RateSchedule.builder();
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
		@RosettaAttribute("rateSchedule")
		public FixedRateSpecification.FixedRateSpecificationBuilder setRateSchedule(RateSchedule rateSchedule) {
			this.rateSchedule = rateSchedule==null?null:rateSchedule.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public FixedRateSpecification.FixedRateSpecificationBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public FixedRateSpecification build() {
			return new FixedRateSpecification.FixedRateSpecificationImpl(this);
		}
		
		@Override
		public FixedRateSpecification.FixedRateSpecificationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FixedRateSpecification.FixedRateSpecificationBuilder prune() {
			if (rateSchedule!=null && !rateSchedule.prune().hasData()) rateSchedule = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getRateSchedule()!=null && getRateSchedule().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FixedRateSpecification.FixedRateSpecificationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FixedRateSpecification.FixedRateSpecificationBuilder o = (FixedRateSpecification.FixedRateSpecificationBuilder) other;
			
			merger.mergeRosetta(getRateSchedule(), o.getRateSchedule(), this::setRateSchedule);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FixedRateSpecification _that = getType().cast(o);
		
			if (!Objects.equals(rateSchedule, _that.getRateSchedule())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (rateSchedule != null ? rateSchedule.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FixedRateSpecificationBuilder {" +
				"rateSchedule=" + this.rateSchedule + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
