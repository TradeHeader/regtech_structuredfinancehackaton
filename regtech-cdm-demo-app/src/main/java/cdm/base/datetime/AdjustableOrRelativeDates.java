package cdm.base.datetime;

import cdm.base.datetime.AdjustableDates;
import cdm.base.datetime.AdjustableOrRelativeDates;
import cdm.base.datetime.AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder;
import cdm.base.datetime.AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilderImpl;
import cdm.base.datetime.AdjustableOrRelativeDates.AdjustableOrRelativeDatesImpl;
import cdm.base.datetime.RelativeDates;
import cdm.base.datetime.meta.AdjustableOrRelativeDatesMeta;
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
 * A class giving the choice between defining a series of dates as an explicit list of dates together with applicable adjustments or as relative to some other series of (anchor) dates.
 * @version ${project.version}
 */
@RosettaDataType(value="AdjustableOrRelativeDates", builder=AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilderImpl.class, version="${project.version}")
public interface AdjustableOrRelativeDates extends RosettaModelObject, GlobalKey {

	AdjustableOrRelativeDatesMeta metaData = new AdjustableOrRelativeDatesMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A series of dates that shall be subject to adjustment if they would otherwise fall on a day that is not a business day in the specified business centers, together with the convention for adjusting the date.
	 */
	AdjustableDates getAdjustableDates();
	/**
	 * A series of dates specified as some offset to another series of dates (the anchor dates).
	 */
	RelativeDates getRelativeDates();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	AdjustableOrRelativeDates build();
	
	AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder toBuilder();
	
	static AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder builder() {
		return new AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AdjustableOrRelativeDates> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AdjustableOrRelativeDates> getType() {
		return AdjustableOrRelativeDates.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("adjustableDates"), processor, AdjustableDates.class, getAdjustableDates());
		processRosetta(path.newSubPath("relativeDates"), processor, RelativeDates.class, getRelativeDates());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AdjustableOrRelativeDatesBuilder extends AdjustableOrRelativeDates, RosettaModelObjectBuilder {
		AdjustableDates.AdjustableDatesBuilder getOrCreateAdjustableDates();
		AdjustableDates.AdjustableDatesBuilder getAdjustableDates();
		RelativeDates.RelativeDatesBuilder getOrCreateRelativeDates();
		RelativeDates.RelativeDatesBuilder getRelativeDates();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder setAdjustableDates(AdjustableDates adjustableDates);
		AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder setRelativeDates(RelativeDates relativeDates);
		AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("adjustableDates"), processor, AdjustableDates.AdjustableDatesBuilder.class, getAdjustableDates());
			processRosetta(path.newSubPath("relativeDates"), processor, RelativeDates.RelativeDatesBuilder.class, getRelativeDates());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder prune();
	}

	/*********************** Immutable Implementation of AdjustableOrRelativeDates  ***********************/
	class AdjustableOrRelativeDatesImpl implements AdjustableOrRelativeDates {
		private final AdjustableDates adjustableDates;
		private final RelativeDates relativeDates;
		private final MetaFields meta;
		
		protected AdjustableOrRelativeDatesImpl(AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder builder) {
			this.adjustableDates = ofNullable(builder.getAdjustableDates()).map(f->f.build()).orElse(null);
			this.relativeDates = ofNullable(builder.getRelativeDates()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("adjustableDates")
		public AdjustableDates getAdjustableDates() {
			return adjustableDates;
		}
		
		@Override
		@RosettaAttribute("relativeDates")
		public RelativeDates getRelativeDates() {
			return relativeDates;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public AdjustableOrRelativeDates build() {
			return this;
		}
		
		@Override
		public AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder toBuilder() {
			AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder builder) {
			ofNullable(getAdjustableDates()).ifPresent(builder::setAdjustableDates);
			ofNullable(getRelativeDates()).ifPresent(builder::setRelativeDates);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdjustableOrRelativeDates _that = getType().cast(o);
		
			if (!Objects.equals(adjustableDates, _that.getAdjustableDates())) return false;
			if (!Objects.equals(relativeDates, _that.getRelativeDates())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (adjustableDates != null ? adjustableDates.hashCode() : 0);
			_result = 31 * _result + (relativeDates != null ? relativeDates.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdjustableOrRelativeDates {" +
				"adjustableDates=" + this.adjustableDates + ", " +
				"relativeDates=" + this.relativeDates + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of AdjustableOrRelativeDates  ***********************/
	class AdjustableOrRelativeDatesBuilderImpl implements AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder, GlobalKeyBuilder {
	
		protected AdjustableDates.AdjustableDatesBuilder adjustableDates;
		protected RelativeDates.RelativeDatesBuilder relativeDates;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public AdjustableOrRelativeDatesBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("adjustableDates")
		public AdjustableDates.AdjustableDatesBuilder getAdjustableDates() {
			return adjustableDates;
		}
		
		@Override
		public AdjustableDates.AdjustableDatesBuilder getOrCreateAdjustableDates() {
			AdjustableDates.AdjustableDatesBuilder result;
			if (adjustableDates!=null) {
				result = adjustableDates;
			}
			else {
				result = adjustableDates = AdjustableDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("relativeDates")
		public RelativeDates.RelativeDatesBuilder getRelativeDates() {
			return relativeDates;
		}
		
		@Override
		public RelativeDates.RelativeDatesBuilder getOrCreateRelativeDates() {
			RelativeDates.RelativeDatesBuilder result;
			if (relativeDates!=null) {
				result = relativeDates;
			}
			else {
				result = relativeDates = RelativeDates.builder();
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
		@RosettaAttribute("adjustableDates")
		public AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder setAdjustableDates(AdjustableDates adjustableDates) {
			this.adjustableDates = adjustableDates==null?null:adjustableDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("relativeDates")
		public AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder setRelativeDates(RelativeDates relativeDates) {
			this.relativeDates = relativeDates==null?null:relativeDates.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public AdjustableOrRelativeDates build() {
			return new AdjustableOrRelativeDates.AdjustableOrRelativeDatesImpl(this);
		}
		
		@Override
		public AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder prune() {
			if (adjustableDates!=null && !adjustableDates.prune().hasData()) adjustableDates = null;
			if (relativeDates!=null && !relativeDates.prune().hasData()) relativeDates = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAdjustableDates()!=null && getAdjustableDates().hasData()) return true;
			if (getRelativeDates()!=null && getRelativeDates().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder o = (AdjustableOrRelativeDates.AdjustableOrRelativeDatesBuilder) other;
			
			merger.mergeRosetta(getAdjustableDates(), o.getAdjustableDates(), this::setAdjustableDates);
			merger.mergeRosetta(getRelativeDates(), o.getRelativeDates(), this::setRelativeDates);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AdjustableOrRelativeDates _that = getType().cast(o);
		
			if (!Objects.equals(adjustableDates, _that.getAdjustableDates())) return false;
			if (!Objects.equals(relativeDates, _that.getRelativeDates())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (adjustableDates != null ? adjustableDates.hashCode() : 0);
			_result = 31 * _result + (relativeDates != null ? relativeDates.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AdjustableOrRelativeDatesBuilder {" +
				"adjustableDates=" + this.adjustableDates + ", " +
				"relativeDates=" + this.relativeDates + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
