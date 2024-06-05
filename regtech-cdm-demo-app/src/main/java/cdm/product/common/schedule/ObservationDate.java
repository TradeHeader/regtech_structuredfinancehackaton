package cdm.product.common.schedule;

import cdm.product.common.schedule.ObservationDate;
import cdm.product.common.schedule.ObservationDate.ObservationDateBuilder;
import cdm.product.common.schedule.ObservationDate.ObservationDateBuilderImpl;
import cdm.product.common.schedule.ObservationDate.ObservationDateImpl;
import cdm.product.common.schedule.meta.ObservationDateMeta;
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
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.MetaFields;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Specifies a single date on which market observations take place and specifies optional associated weighting.
 * @version ${project.version}
 */
@RosettaDataType(value="ObservationDate", builder=ObservationDate.ObservationDateBuilderImpl.class, version="${project.version}")
public interface ObservationDate extends RosettaModelObject, GlobalKey {

	ObservationDateMeta metaData = new ObservationDateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A date subject to adjustment.
	 */
	Date getUnadjustedDate();
	/**
	 * The date once the adjustment has been performed. (Note that this date may change if the business center holidays change).
	 */
	Date getAdjustedDate();
	/**
	 * Specifies the degree of importance of the observation.
	 */
	BigDecimal getWeight();
	/**
	 * Specifies an identification key for the market observation. This attribute can be used as a reference to assign weights to a series of dates defined in a parametricSchedule.
	 */
	String getObservationReference();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	ObservationDate build();
	
	ObservationDate.ObservationDateBuilder toBuilder();
	
	static ObservationDate.ObservationDateBuilder builder() {
		return new ObservationDate.ObservationDateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ObservationDate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ObservationDate> getType() {
		return ObservationDate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("unadjustedDate"), Date.class, getUnadjustedDate(), this);
		processor.processBasic(path.newSubPath("adjustedDate"), Date.class, getAdjustedDate(), this);
		processor.processBasic(path.newSubPath("weight"), BigDecimal.class, getWeight(), this);
		processor.processBasic(path.newSubPath("observationReference"), String.class, getObservationReference(), this);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ObservationDateBuilder extends ObservationDate, RosettaModelObjectBuilder {
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		ObservationDate.ObservationDateBuilder setUnadjustedDate(Date unadjustedDate);
		ObservationDate.ObservationDateBuilder setAdjustedDate(Date adjustedDate);
		ObservationDate.ObservationDateBuilder setWeight(BigDecimal weight);
		ObservationDate.ObservationDateBuilder setObservationReference(String observationReference);
		ObservationDate.ObservationDateBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("unadjustedDate"), Date.class, getUnadjustedDate(), this);
			processor.processBasic(path.newSubPath("adjustedDate"), Date.class, getAdjustedDate(), this);
			processor.processBasic(path.newSubPath("weight"), BigDecimal.class, getWeight(), this);
			processor.processBasic(path.newSubPath("observationReference"), String.class, getObservationReference(), this);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		ObservationDate.ObservationDateBuilder prune();
	}

	/*********************** Immutable Implementation of ObservationDate  ***********************/
	class ObservationDateImpl implements ObservationDate {
		private final Date unadjustedDate;
		private final Date adjustedDate;
		private final BigDecimal weight;
		private final String observationReference;
		private final MetaFields meta;
		
		protected ObservationDateImpl(ObservationDate.ObservationDateBuilder builder) {
			this.unadjustedDate = builder.getUnadjustedDate();
			this.adjustedDate = builder.getAdjustedDate();
			this.weight = builder.getWeight();
			this.observationReference = builder.getObservationReference();
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("unadjustedDate")
		public Date getUnadjustedDate() {
			return unadjustedDate;
		}
		
		@Override
		@RosettaAttribute("adjustedDate")
		public Date getAdjustedDate() {
			return adjustedDate;
		}
		
		@Override
		@RosettaAttribute("weight")
		public BigDecimal getWeight() {
			return weight;
		}
		
		@Override
		@RosettaAttribute("observationReference")
		public String getObservationReference() {
			return observationReference;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public ObservationDate build() {
			return this;
		}
		
		@Override
		public ObservationDate.ObservationDateBuilder toBuilder() {
			ObservationDate.ObservationDateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ObservationDate.ObservationDateBuilder builder) {
			ofNullable(getUnadjustedDate()).ifPresent(builder::setUnadjustedDate);
			ofNullable(getAdjustedDate()).ifPresent(builder::setAdjustedDate);
			ofNullable(getWeight()).ifPresent(builder::setWeight);
			ofNullable(getObservationReference()).ifPresent(builder::setObservationReference);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationDate _that = getType().cast(o);
		
			if (!Objects.equals(unadjustedDate, _that.getUnadjustedDate())) return false;
			if (!Objects.equals(adjustedDate, _that.getAdjustedDate())) return false;
			if (!Objects.equals(weight, _that.getWeight())) return false;
			if (!Objects.equals(observationReference, _that.getObservationReference())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (unadjustedDate != null ? unadjustedDate.hashCode() : 0);
			_result = 31 * _result + (adjustedDate != null ? adjustedDate.hashCode() : 0);
			_result = 31 * _result + (weight != null ? weight.hashCode() : 0);
			_result = 31 * _result + (observationReference != null ? observationReference.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationDate {" +
				"unadjustedDate=" + this.unadjustedDate + ", " +
				"adjustedDate=" + this.adjustedDate + ", " +
				"weight=" + this.weight + ", " +
				"observationReference=" + this.observationReference + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of ObservationDate  ***********************/
	class ObservationDateBuilderImpl implements ObservationDate.ObservationDateBuilder, GlobalKeyBuilder {
	
		protected Date unadjustedDate;
		protected Date adjustedDate;
		protected BigDecimal weight;
		protected String observationReference;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public ObservationDateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("unadjustedDate")
		public Date getUnadjustedDate() {
			return unadjustedDate;
		}
		
		@Override
		@RosettaAttribute("adjustedDate")
		public Date getAdjustedDate() {
			return adjustedDate;
		}
		
		@Override
		@RosettaAttribute("weight")
		public BigDecimal getWeight() {
			return weight;
		}
		
		@Override
		@RosettaAttribute("observationReference")
		public String getObservationReference() {
			return observationReference;
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
		@RosettaAttribute("unadjustedDate")
		public ObservationDate.ObservationDateBuilder setUnadjustedDate(Date unadjustedDate) {
			this.unadjustedDate = unadjustedDate==null?null:unadjustedDate;
			return this;
		}
		@Override
		@RosettaAttribute("adjustedDate")
		public ObservationDate.ObservationDateBuilder setAdjustedDate(Date adjustedDate) {
			this.adjustedDate = adjustedDate==null?null:adjustedDate;
			return this;
		}
		@Override
		@RosettaAttribute("weight")
		public ObservationDate.ObservationDateBuilder setWeight(BigDecimal weight) {
			this.weight = weight==null?null:weight;
			return this;
		}
		@Override
		@RosettaAttribute("observationReference")
		public ObservationDate.ObservationDateBuilder setObservationReference(String observationReference) {
			this.observationReference = observationReference==null?null:observationReference;
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public ObservationDate.ObservationDateBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public ObservationDate build() {
			return new ObservationDate.ObservationDateImpl(this);
		}
		
		@Override
		public ObservationDate.ObservationDateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationDate.ObservationDateBuilder prune() {
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getUnadjustedDate()!=null) return true;
			if (getAdjustedDate()!=null) return true;
			if (getWeight()!=null) return true;
			if (getObservationReference()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationDate.ObservationDateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ObservationDate.ObservationDateBuilder o = (ObservationDate.ObservationDateBuilder) other;
			
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getUnadjustedDate(), o.getUnadjustedDate(), this::setUnadjustedDate);
			merger.mergeBasic(getAdjustedDate(), o.getAdjustedDate(), this::setAdjustedDate);
			merger.mergeBasic(getWeight(), o.getWeight(), this::setWeight);
			merger.mergeBasic(getObservationReference(), o.getObservationReference(), this::setObservationReference);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationDate _that = getType().cast(o);
		
			if (!Objects.equals(unadjustedDate, _that.getUnadjustedDate())) return false;
			if (!Objects.equals(adjustedDate, _that.getAdjustedDate())) return false;
			if (!Objects.equals(weight, _that.getWeight())) return false;
			if (!Objects.equals(observationReference, _that.getObservationReference())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (unadjustedDate != null ? unadjustedDate.hashCode() : 0);
			_result = 31 * _result + (adjustedDate != null ? adjustedDate.hashCode() : 0);
			_result = 31 * _result + (weight != null ? weight.hashCode() : 0);
			_result = 31 * _result + (observationReference != null ? observationReference.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationDateBuilder {" +
				"unadjustedDate=" + this.unadjustedDate + ", " +
				"adjustedDate=" + this.adjustedDate + ", " +
				"weight=" + this.weight + ", " +
				"observationReference=" + this.observationReference + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
