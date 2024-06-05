package cdm.base.datetime;

import cdm.base.datetime.AdjustableDate;
import cdm.base.datetime.AdjustableOrRelativeDate;
import cdm.base.datetime.AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder;
import cdm.base.datetime.AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilderImpl;
import cdm.base.datetime.AdjustableOrRelativeDate.AdjustableOrRelativeDateImpl;
import cdm.base.datetime.AdjustedRelativeDateOffset;
import cdm.base.datetime.meta.AdjustableOrRelativeDateMeta;
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
 * A class giving the choice between defining a date as an explicit date together with applicable adjustments or as relative to some other (anchor) date.
 * @version ${project.version}
 */
@RosettaDataType(value="AdjustableOrRelativeDate", builder=AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilderImpl.class, version="${project.version}")
public interface AdjustableOrRelativeDate extends RosettaModelObject, GlobalKey {

	AdjustableOrRelativeDateMeta metaData = new AdjustableOrRelativeDateMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A date that shall be subject to adjustment if it would otherwise fall on a day that is not a business day in the specified business centers, together with the convention for adjusting the date.
	 */
	AdjustableDate getAdjustableDate();
	/**
	 * A date specified as some offset to another date (the anchor date).
	 */
	AdjustedRelativeDateOffset getRelativeDate();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	AdjustableOrRelativeDate build();
	
	AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder toBuilder();
	
	static AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder builder() {
		return new AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AdjustableOrRelativeDate> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AdjustableOrRelativeDate> getType() {
		return AdjustableOrRelativeDate.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("adjustableDate"), processor, AdjustableDate.class, getAdjustableDate());
		processRosetta(path.newSubPath("relativeDate"), processor, AdjustedRelativeDateOffset.class, getRelativeDate());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AdjustableOrRelativeDateBuilder extends AdjustableOrRelativeDate, RosettaModelObjectBuilder {
		AdjustableDate.AdjustableDateBuilder getOrCreateAdjustableDate();
		AdjustableDate.AdjustableDateBuilder getAdjustableDate();
		AdjustedRelativeDateOffset.AdjustedRelativeDateOffsetBuilder getOrCreateRelativeDate();
		AdjustedRelativeDateOffset.AdjustedRelativeDateOffsetBuilder getRelativeDate();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder setAdjustableDate(AdjustableDate adjustableDate);
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder setRelativeDate(AdjustedRelativeDateOffset relativeDate);
		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("adjustableDate"), processor, AdjustableDate.AdjustableDateBuilder.class, getAdjustableDate());
			processRosetta(path.newSubPath("relativeDate"), processor, AdjustedRelativeDateOffset.AdjustedRelativeDateOffsetBuilder.class, getRelativeDate());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder prune();
	}

	/*********************** Immutable Implementation of AdjustableOrRelativeDate  ***********************/
	class AdjustableOrRelativeDateImpl implements AdjustableOrRelativeDate {
		private final AdjustableDate adjustableDate;
		private final AdjustedRelativeDateOffset relativeDate;
		private final MetaFields meta;
		
		protected AdjustableOrRelativeDateImpl(AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder builder) {
			this.adjustableDate = ofNullable(builder.getAdjustableDate()).map(f->f.build()).orElse(null);
			this.relativeDate = ofNullable(builder.getRelativeDate()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("adjustableDate")
		public AdjustableDate getAdjustableDate() {
			return adjustableDate;
		}
		
		@Override
		@RosettaAttribute("relativeDate")
		public AdjustedRelativeDateOffset getRelativeDate() {
			return relativeDate;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public AdjustableOrRelativeDate build() {
			return this;
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder toBuilder() {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder builder) {
			ofNullable(getAdjustableDate()).ifPresent(builder::setAdjustableDate);
			ofNullable(getRelativeDate()).ifPresent(builder::setRelativeDate);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdjustableOrRelativeDate _that = getType().cast(o);
		
			if (!Objects.equals(adjustableDate, _that.getAdjustableDate())) return false;
			if (!Objects.equals(relativeDate, _that.getRelativeDate())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (adjustableDate != null ? adjustableDate.hashCode() : 0);
			_result = 31 * _result + (relativeDate != null ? relativeDate.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdjustableOrRelativeDate {" +
				"adjustableDate=" + this.adjustableDate + ", " +
				"relativeDate=" + this.relativeDate + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of AdjustableOrRelativeDate  ***********************/
	class AdjustableOrRelativeDateBuilderImpl implements AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder, GlobalKeyBuilder {
	
		protected AdjustableDate.AdjustableDateBuilder adjustableDate;
		protected AdjustedRelativeDateOffset.AdjustedRelativeDateOffsetBuilder relativeDate;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public AdjustableOrRelativeDateBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("adjustableDate")
		public AdjustableDate.AdjustableDateBuilder getAdjustableDate() {
			return adjustableDate;
		}
		
		@Override
		public AdjustableDate.AdjustableDateBuilder getOrCreateAdjustableDate() {
			AdjustableDate.AdjustableDateBuilder result;
			if (adjustableDate!=null) {
				result = adjustableDate;
			}
			else {
				result = adjustableDate = AdjustableDate.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("relativeDate")
		public AdjustedRelativeDateOffset.AdjustedRelativeDateOffsetBuilder getRelativeDate() {
			return relativeDate;
		}
		
		@Override
		public AdjustedRelativeDateOffset.AdjustedRelativeDateOffsetBuilder getOrCreateRelativeDate() {
			AdjustedRelativeDateOffset.AdjustedRelativeDateOffsetBuilder result;
			if (relativeDate!=null) {
				result = relativeDate;
			}
			else {
				result = relativeDate = AdjustedRelativeDateOffset.builder();
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
		@RosettaAttribute("adjustableDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder setAdjustableDate(AdjustableDate adjustableDate) {
			this.adjustableDate = adjustableDate==null?null:adjustableDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("relativeDate")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder setRelativeDate(AdjustedRelativeDateOffset relativeDate) {
			this.relativeDate = relativeDate==null?null:relativeDate.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public AdjustableOrRelativeDate build() {
			return new AdjustableOrRelativeDate.AdjustableOrRelativeDateImpl(this);
		}
		
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder prune() {
			if (adjustableDate!=null && !adjustableDate.prune().hasData()) adjustableDate = null;
			if (relativeDate!=null && !relativeDate.prune().hasData()) relativeDate = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAdjustableDate()!=null && getAdjustableDate().hasData()) return true;
			if (getRelativeDate()!=null && getRelativeDate().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder o = (AdjustableOrRelativeDate.AdjustableOrRelativeDateBuilder) other;
			
			merger.mergeRosetta(getAdjustableDate(), o.getAdjustableDate(), this::setAdjustableDate);
			merger.mergeRosetta(getRelativeDate(), o.getRelativeDate(), this::setRelativeDate);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdjustableOrRelativeDate _that = getType().cast(o);
		
			if (!Objects.equals(adjustableDate, _that.getAdjustableDate())) return false;
			if (!Objects.equals(relativeDate, _that.getRelativeDate())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (adjustableDate != null ? adjustableDate.hashCode() : 0);
			_result = 31 * _result + (relativeDate != null ? relativeDate.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdjustableOrRelativeDateBuilder {" +
				"adjustableDate=" + this.adjustableDate + ", " +
				"relativeDate=" + this.relativeDate + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
