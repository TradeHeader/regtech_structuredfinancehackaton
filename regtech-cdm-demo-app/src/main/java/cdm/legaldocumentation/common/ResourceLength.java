package cdm.legaldocumentation.common;

import cdm.legaldocumentation.common.LengthUnitEnum;
import cdm.legaldocumentation.common.ResourceLength;
import cdm.legaldocumentation.common.ResourceLength.ResourceLengthBuilder;
import cdm.legaldocumentation.common.ResourceLength.ResourceLengthBuilderImpl;
import cdm.legaldocumentation.common.ResourceLength.ResourceLengthImpl;
import cdm.legaldocumentation.common.meta.ResourceLengthMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A class to indicate the length of the resource.
 * @version ${project.version}
 */
@RosettaDataType(value="ResourceLength", builder=ResourceLength.ResourceLengthBuilderImpl.class, version="${project.version}")
public interface ResourceLength extends RosettaModelObject {

	ResourceLengthMeta metaData = new ResourceLengthMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The length unit of the resource. For example, pages (pdf, text documents) or time (audio, video files).
	 */
	LengthUnitEnum getLengthUnit();
	/**
	 * The length value of the resource.
	 */
	BigDecimal getLengthValue();

	/*********************** Build Methods  ***********************/
	ResourceLength build();
	
	ResourceLength.ResourceLengthBuilder toBuilder();
	
	static ResourceLength.ResourceLengthBuilder builder() {
		return new ResourceLength.ResourceLengthBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ResourceLength> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ResourceLength> getType() {
		return ResourceLength.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("lengthUnit"), LengthUnitEnum.class, getLengthUnit(), this);
		processor.processBasic(path.newSubPath("lengthValue"), BigDecimal.class, getLengthValue(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ResourceLengthBuilder extends ResourceLength, RosettaModelObjectBuilder {
		ResourceLength.ResourceLengthBuilder setLengthUnit(LengthUnitEnum lengthUnit);
		ResourceLength.ResourceLengthBuilder setLengthValue(BigDecimal lengthValue);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("lengthUnit"), LengthUnitEnum.class, getLengthUnit(), this);
			processor.processBasic(path.newSubPath("lengthValue"), BigDecimal.class, getLengthValue(), this);
		}
		

		ResourceLength.ResourceLengthBuilder prune();
	}

	/*********************** Immutable Implementation of ResourceLength  ***********************/
	class ResourceLengthImpl implements ResourceLength {
		private final LengthUnitEnum lengthUnit;
		private final BigDecimal lengthValue;
		
		protected ResourceLengthImpl(ResourceLength.ResourceLengthBuilder builder) {
			this.lengthUnit = builder.getLengthUnit();
			this.lengthValue = builder.getLengthValue();
		}
		
		@Override
		@RosettaAttribute("lengthUnit")
		public LengthUnitEnum getLengthUnit() {
			return lengthUnit;
		}
		
		@Override
		@RosettaAttribute("lengthValue")
		public BigDecimal getLengthValue() {
			return lengthValue;
		}
		
		@Override
		public ResourceLength build() {
			return this;
		}
		
		@Override
		public ResourceLength.ResourceLengthBuilder toBuilder() {
			ResourceLength.ResourceLengthBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ResourceLength.ResourceLengthBuilder builder) {
			ofNullable(getLengthUnit()).ifPresent(builder::setLengthUnit);
			ofNullable(getLengthValue()).ifPresent(builder::setLengthValue);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ResourceLength _that = getType().cast(o);
		
			if (!Objects.equals(lengthUnit, _that.getLengthUnit())) return false;
			if (!Objects.equals(lengthValue, _that.getLengthValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (lengthUnit != null ? lengthUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (lengthValue != null ? lengthValue.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ResourceLength {" +
				"lengthUnit=" + this.lengthUnit + ", " +
				"lengthValue=" + this.lengthValue +
			'}';
		}
	}

	/*********************** Builder Implementation of ResourceLength  ***********************/
	class ResourceLengthBuilderImpl implements ResourceLength.ResourceLengthBuilder {
	
		protected LengthUnitEnum lengthUnit;
		protected BigDecimal lengthValue;
	
		public ResourceLengthBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("lengthUnit")
		public LengthUnitEnum getLengthUnit() {
			return lengthUnit;
		}
		
		@Override
		@RosettaAttribute("lengthValue")
		public BigDecimal getLengthValue() {
			return lengthValue;
		}
		
	
		@Override
		@RosettaAttribute("lengthUnit")
		public ResourceLength.ResourceLengthBuilder setLengthUnit(LengthUnitEnum lengthUnit) {
			this.lengthUnit = lengthUnit==null?null:lengthUnit;
			return this;
		}
		@Override
		@RosettaAttribute("lengthValue")
		public ResourceLength.ResourceLengthBuilder setLengthValue(BigDecimal lengthValue) {
			this.lengthValue = lengthValue==null?null:lengthValue;
			return this;
		}
		
		@Override
		public ResourceLength build() {
			return new ResourceLength.ResourceLengthImpl(this);
		}
		
		@Override
		public ResourceLength.ResourceLengthBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ResourceLength.ResourceLengthBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getLengthUnit()!=null) return true;
			if (getLengthValue()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ResourceLength.ResourceLengthBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ResourceLength.ResourceLengthBuilder o = (ResourceLength.ResourceLengthBuilder) other;
			
			
			merger.mergeBasic(getLengthUnit(), o.getLengthUnit(), this::setLengthUnit);
			merger.mergeBasic(getLengthValue(), o.getLengthValue(), this::setLengthValue);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ResourceLength _that = getType().cast(o);
		
			if (!Objects.equals(lengthUnit, _that.getLengthUnit())) return false;
			if (!Objects.equals(lengthValue, _that.getLengthValue())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (lengthUnit != null ? lengthUnit.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (lengthValue != null ? lengthValue.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ResourceLengthBuilder {" +
				"lengthUnit=" + this.lengthUnit + ", " +
				"lengthValue=" + this.lengthValue +
			'}';
		}
	}
}
