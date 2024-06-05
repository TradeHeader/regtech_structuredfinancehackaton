package cdm.base.datetime;

import cdm.base.datetime.AdjustableOrAdjustedDate;
import cdm.base.datetime.AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder;
import cdm.base.datetime.AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilderImpl;
import cdm.base.datetime.AdjustableOrAdjustedDate.AdjustableOrAdjustedDateImpl;
import cdm.base.datetime.BusinessDayAdjustments;
import cdm.base.datetime.meta.AdjustableOrAdjustedDateMeta;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.AttributeMeta;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import com.rosetta.model.metafields.FieldWithMetaDate;
import com.rosetta.model.metafields.FieldWithMetaDate.FieldWithMetaDateBuilder;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class for defining a date that shall be subject to adjustment if it would otherwise fall on a day that is not a business day in the specified business centers, together with the convention for adjusting the date.
 * @version ${project.version}
 */
@RosettaDataType(value="AdjustableOrAdjustedDate", builder=AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilderImpl.class, version="${project.version}")
public interface AdjustableOrAdjustedDate extends RosettaModelObject, GlobalKey {

	AdjustableOrAdjustedDateMeta metaData = new AdjustableOrAdjustedDateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A date subject to adjustment.
	 */
	Date getUnadjustedDate();
	/**
	 * The business day convention and financial business centers used for adjusting the date if it would otherwise fall on a day that is not a business date in the specified business centers.
	 */
	BusinessDayAdjustments getDateAdjustments();
	/**
	 * The date once the adjustment has been performed. (Note that this date may change if the business center holidays change).
	 */
	FieldWithMetaDate getAdjustedDate();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	AdjustableOrAdjustedDate build();
	
	AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder toBuilder();
	
	static AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder builder() {
		return new AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AdjustableOrAdjustedDate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AdjustableOrAdjustedDate> getType() {
		return AdjustableOrAdjustedDate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("unadjustedDate"), Date.class, getUnadjustedDate(), this);
		processRosetta(path.newSubPath("dateAdjustments"), processor, BusinessDayAdjustments.class, getDateAdjustments());
		processRosetta(path.newSubPath("adjustedDate"), processor, FieldWithMetaDate.class, getAdjustedDate(), AttributeMeta.GLOBAL_KEY_FIELD);
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AdjustableOrAdjustedDateBuilder extends AdjustableOrAdjustedDate, RosettaModelObjectBuilder {
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreateDateAdjustments();
		BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getDateAdjustments();
		FieldWithMetaDate.FieldWithMetaDateBuilder getOrCreateAdjustedDate();
		FieldWithMetaDate.FieldWithMetaDateBuilder getAdjustedDate();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder setUnadjustedDate(Date unadjustedDate);
		AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder setDateAdjustments(BusinessDayAdjustments dateAdjustments);
		AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder setAdjustedDate(FieldWithMetaDate adjustedDate0);
		AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder setAdjustedDateValue(Date adjustedDate1);
		AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("unadjustedDate"), Date.class, getUnadjustedDate(), this);
			processRosetta(path.newSubPath("dateAdjustments"), processor, BusinessDayAdjustments.BusinessDayAdjustmentsBuilder.class, getDateAdjustments());
			processRosetta(path.newSubPath("adjustedDate"), processor, FieldWithMetaDate.FieldWithMetaDateBuilder.class, getAdjustedDate(), AttributeMeta.GLOBAL_KEY_FIELD);
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder prune();
	}

	/*********************** Immutable Implementation of AdjustableOrAdjustedDate  ***********************/
	class AdjustableOrAdjustedDateImpl implements AdjustableOrAdjustedDate {
		private final Date unadjustedDate;
		private final BusinessDayAdjustments dateAdjustments;
		private final FieldWithMetaDate adjustedDate;
		private final MetaFields meta;
		
		protected AdjustableOrAdjustedDateImpl(AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder builder) {
			this.unadjustedDate = builder.getUnadjustedDate();
			this.dateAdjustments = ofNullable(builder.getDateAdjustments()).map(f->f.build()).orElse(null);
			this.adjustedDate = ofNullable(builder.getAdjustedDate()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("unadjustedDate")
		public Date getUnadjustedDate() {
			return unadjustedDate;
		}
		
		@Override
		@RosettaAttribute("dateAdjustments")
		public BusinessDayAdjustments getDateAdjustments() {
			return dateAdjustments;
		}
		
		@Override
		@RosettaAttribute("adjustedDate")
		public FieldWithMetaDate getAdjustedDate() {
			return adjustedDate;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public AdjustableOrAdjustedDate build() {
			return this;
		}
		
		@Override
		public AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder toBuilder() {
			AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder builder) {
			ofNullable(getUnadjustedDate()).ifPresent(builder::setUnadjustedDate);
			ofNullable(getDateAdjustments()).ifPresent(builder::setDateAdjustments);
			ofNullable(getAdjustedDate()).ifPresent(builder::setAdjustedDate);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdjustableOrAdjustedDate _that = getType().cast(o);
		
			if (!Objects.equals(unadjustedDate, _that.getUnadjustedDate())) return false;
			if (!Objects.equals(dateAdjustments, _that.getDateAdjustments())) return false;
			if (!Objects.equals(adjustedDate, _that.getAdjustedDate())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (unadjustedDate != null ? unadjustedDate.hashCode() : 0);
			_result = 31 * _result + (dateAdjustments != null ? dateAdjustments.hashCode() : 0);
			_result = 31 * _result + (adjustedDate != null ? adjustedDate.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdjustableOrAdjustedDate {" +
				"unadjustedDate=" + this.unadjustedDate + ", " +
				"dateAdjustments=" + this.dateAdjustments + ", " +
				"adjustedDate=" + this.adjustedDate + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of AdjustableOrAdjustedDate  ***********************/
	class AdjustableOrAdjustedDateBuilderImpl implements AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder, GlobalKeyBuilder {
	
		protected Date unadjustedDate;
		protected BusinessDayAdjustments.BusinessDayAdjustmentsBuilder dateAdjustments;
		protected FieldWithMetaDate.FieldWithMetaDateBuilder adjustedDate;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public AdjustableOrAdjustedDateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("unadjustedDate")
		public Date getUnadjustedDate() {
			return unadjustedDate;
		}
		
		@Override
		@RosettaAttribute("dateAdjustments")
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getDateAdjustments() {
			return dateAdjustments;
		}
		
		@Override
		public BusinessDayAdjustments.BusinessDayAdjustmentsBuilder getOrCreateDateAdjustments() {
			BusinessDayAdjustments.BusinessDayAdjustmentsBuilder result;
			if (dateAdjustments!=null) {
				result = dateAdjustments;
			}
			else {
				result = dateAdjustments = BusinessDayAdjustments.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("adjustedDate")
		public FieldWithMetaDate.FieldWithMetaDateBuilder getAdjustedDate() {
			return adjustedDate;
		}
		
		@Override
		public FieldWithMetaDate.FieldWithMetaDateBuilder getOrCreateAdjustedDate() {
			FieldWithMetaDate.FieldWithMetaDateBuilder result;
			if (adjustedDate!=null) {
				result = adjustedDate;
			}
			else {
				result = adjustedDate = FieldWithMetaDate.builder();
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
		@RosettaAttribute("unadjustedDate")
		public AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder setUnadjustedDate(Date unadjustedDate) {
			this.unadjustedDate = unadjustedDate==null?null:unadjustedDate;
			return this;
		}
		@Override
		@RosettaAttribute("dateAdjustments")
		public AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder setDateAdjustments(BusinessDayAdjustments dateAdjustments) {
			this.dateAdjustments = dateAdjustments==null?null:dateAdjustments.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("adjustedDate")
		public AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder setAdjustedDate(FieldWithMetaDate adjustedDate) {
			this.adjustedDate = adjustedDate==null?null:adjustedDate.toBuilder();
			return this;
		}
		@Override
		public AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder setAdjustedDateValue(Date adjustedDate) {
			this.getOrCreateAdjustedDate().setValue(adjustedDate);
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public AdjustableOrAdjustedDate build() {
			return new AdjustableOrAdjustedDate.AdjustableOrAdjustedDateImpl(this);
		}
		
		@Override
		public AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder prune() {
			if (dateAdjustments!=null && !dateAdjustments.prune().hasData()) dateAdjustments = null;
			if (adjustedDate!=null && !adjustedDate.prune().hasData()) adjustedDate = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getUnadjustedDate()!=null) return true;
			if (getDateAdjustments()!=null && getDateAdjustments().hasData()) return true;
			if (getAdjustedDate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder o = (AdjustableOrAdjustedDate.AdjustableOrAdjustedDateBuilder) other;
			
			merger.mergeRosetta(getDateAdjustments(), o.getDateAdjustments(), this::setDateAdjustments);
			merger.mergeRosetta(getAdjustedDate(), o.getAdjustedDate(), this::setAdjustedDate);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			merger.mergeBasic(getUnadjustedDate(), o.getUnadjustedDate(), this::setUnadjustedDate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdjustableOrAdjustedDate _that = getType().cast(o);
		
			if (!Objects.equals(unadjustedDate, _that.getUnadjustedDate())) return false;
			if (!Objects.equals(dateAdjustments, _that.getDateAdjustments())) return false;
			if (!Objects.equals(adjustedDate, _that.getAdjustedDate())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (unadjustedDate != null ? unadjustedDate.hashCode() : 0);
			_result = 31 * _result + (dateAdjustments != null ? dateAdjustments.hashCode() : 0);
			_result = 31 * _result + (adjustedDate != null ? adjustedDate.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdjustableOrAdjustedDateBuilder {" +
				"unadjustedDate=" + this.unadjustedDate + ", " +
				"dateAdjustments=" + this.dateAdjustments + ", " +
				"adjustedDate=" + this.adjustedDate + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
