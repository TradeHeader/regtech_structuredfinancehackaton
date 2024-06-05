package cdm.base.datetime;

import cdm.base.datetime.CustomisableOffset;
import cdm.base.datetime.CustomisableOffset.CustomisableOffsetBuilder;
import cdm.base.datetime.CustomisableOffset.CustomisableOffsetBuilderImpl;
import cdm.base.datetime.CustomisableOffset.CustomisableOffsetImpl;
import cdm.base.datetime.Offset;
import cdm.base.datetime.meta.CustomisableOffsetMeta;
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
 * A class to specify an offset either as a normalized [multiplier, period, dayType] or as a custom provision of type string.
 * @version ${project.version}
 */
@RosettaDataType(value="CustomisableOffset", builder=CustomisableOffset.CustomisableOffsetBuilderImpl.class, version="${project.version}")
public interface CustomisableOffset extends RosettaModelObject {

	CustomisableOffsetMeta metaData = new CustomisableOffsetMeta();

	/*********************** Getter Methods  ***********************/
	Offset getOffset();
	String getCustomProvision();

	/*********************** Build Methods  ***********************/
	CustomisableOffset build();
	
	CustomisableOffset.CustomisableOffsetBuilder toBuilder();
	
	static CustomisableOffset.CustomisableOffsetBuilder builder() {
		return new CustomisableOffset.CustomisableOffsetBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends CustomisableOffset> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends CustomisableOffset> getType() {
		return CustomisableOffset.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("offset"), processor, Offset.class, getOffset());
		processor.processBasic(path.newSubPath("customProvision"), String.class, getCustomProvision(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface CustomisableOffsetBuilder extends CustomisableOffset, RosettaModelObjectBuilder {
		Offset.OffsetBuilder getOrCreateOffset();
		Offset.OffsetBuilder getOffset();
		CustomisableOffset.CustomisableOffsetBuilder setOffset(Offset offset);
		CustomisableOffset.CustomisableOffsetBuilder setCustomProvision(String customProvision);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("offset"), processor, Offset.OffsetBuilder.class, getOffset());
			processor.processBasic(path.newSubPath("customProvision"), String.class, getCustomProvision(), this);
		}
		

		CustomisableOffset.CustomisableOffsetBuilder prune();
	}

	/*********************** Immutable Implementation of CustomisableOffset  ***********************/
	class CustomisableOffsetImpl implements CustomisableOffset {
		private final Offset offset;
		private final String customProvision;
		
		protected CustomisableOffsetImpl(CustomisableOffset.CustomisableOffsetBuilder builder) {
			this.offset = ofNullable(builder.getOffset()).map(f->f.build()).orElse(null);
			this.customProvision = builder.getCustomProvision();
		}
		
		@Override
		@RosettaAttribute("offset")
		public Offset getOffset() {
			return offset;
		}
		
		@Override
		@RosettaAttribute("customProvision")
		public String getCustomProvision() {
			return customProvision;
		}
		
		@Override
		public CustomisableOffset build() {
			return this;
		}
		
		@Override
		public CustomisableOffset.CustomisableOffsetBuilder toBuilder() {
			CustomisableOffset.CustomisableOffsetBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(CustomisableOffset.CustomisableOffsetBuilder builder) {
			ofNullable(getOffset()).ifPresent(builder::setOffset);
			ofNullable(getCustomProvision()).ifPresent(builder::setCustomProvision);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CustomisableOffset _that = getType().cast(o);
		
			if (!Objects.equals(offset, _that.getOffset())) return false;
			if (!Objects.equals(customProvision, _that.getCustomProvision())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (offset != null ? offset.hashCode() : 0);
			_result = 31 * _result + (customProvision != null ? customProvision.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CustomisableOffset {" +
				"offset=" + this.offset + ", " +
				"customProvision=" + this.customProvision +
			'}';
		}
	}

	/*********************** Builder Implementation of CustomisableOffset  ***********************/
	class CustomisableOffsetBuilderImpl implements CustomisableOffset.CustomisableOffsetBuilder {
	
		protected Offset.OffsetBuilder offset;
		protected String customProvision;
	
		public CustomisableOffsetBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("offset")
		public Offset.OffsetBuilder getOffset() {
			return offset;
		}
		
		@Override
		public Offset.OffsetBuilder getOrCreateOffset() {
			Offset.OffsetBuilder result;
			if (offset!=null) {
				result = offset;
			}
			else {
				result = offset = Offset.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("customProvision")
		public String getCustomProvision() {
			return customProvision;
		}
		
	
		@Override
		@RosettaAttribute("offset")
		public CustomisableOffset.CustomisableOffsetBuilder setOffset(Offset offset) {
			this.offset = offset==null?null:offset.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("customProvision")
		public CustomisableOffset.CustomisableOffsetBuilder setCustomProvision(String customProvision) {
			this.customProvision = customProvision==null?null:customProvision;
			return this;
		}
		
		@Override
		public CustomisableOffset build() {
			return new CustomisableOffset.CustomisableOffsetImpl(this);
		}
		
		@Override
		public CustomisableOffset.CustomisableOffsetBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CustomisableOffset.CustomisableOffsetBuilder prune() {
			if (offset!=null && !offset.prune().hasData()) offset = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getOffset()!=null && getOffset().hasData()) return true;
			if (getCustomProvision()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public CustomisableOffset.CustomisableOffsetBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			CustomisableOffset.CustomisableOffsetBuilder o = (CustomisableOffset.CustomisableOffsetBuilder) other;
			
			merger.mergeRosetta(getOffset(), o.getOffset(), this::setOffset);
			
			merger.mergeBasic(getCustomProvision(), o.getCustomProvision(), this::setCustomProvision);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			CustomisableOffset _that = getType().cast(o);
		
			if (!Objects.equals(offset, _that.getOffset())) return false;
			if (!Objects.equals(customProvision, _that.getCustomProvision())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (offset != null ? offset.hashCode() : 0);
			_result = 31 * _result + (customProvision != null ? customProvision.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "CustomisableOffsetBuilder {" +
				"offset=" + this.offset + ", " +
				"customProvision=" + this.customProvision +
			'}';
		}
	}
}
