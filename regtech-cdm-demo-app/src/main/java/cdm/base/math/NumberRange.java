package cdm.base.math;

import cdm.base.math.NumberBound;
import cdm.base.math.NumberRange;
import cdm.base.math.NumberRange.NumberRangeBuilder;
import cdm.base.math.NumberRange.NumberRangeBuilderImpl;
import cdm.base.math.NumberRange.NumberRangeImpl;
import cdm.base.math.meta.NumberRangeMeta;
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
 * The number range defined as either a lower and upper number bound, or both.
 * @version ${project.version}
 */
@RosettaDataType(value="NumberRange", builder=NumberRange.NumberRangeBuilderImpl.class, version="${project.version}")
public interface NumberRange extends RosettaModelObject {

	NumberRangeMeta metaData = new NumberRangeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The lower bound of a number range, e.g. greater than or equal to 5.
	 */
	NumberBound getLowerBound();
	/**
	 * The upper bound of a number range, e.g. less than 10.
	 */
	NumberBound getUpperBound();

	/*********************** Build Methods  ***********************/
	NumberRange build();
	
	NumberRange.NumberRangeBuilder toBuilder();
	
	static NumberRange.NumberRangeBuilder builder() {
		return new NumberRange.NumberRangeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends NumberRange> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends NumberRange> getType() {
		return NumberRange.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("lowerBound"), processor, NumberBound.class, getLowerBound());
		processRosetta(path.newSubPath("upperBound"), processor, NumberBound.class, getUpperBound());
	}
	

	/*********************** Builder Interface  ***********************/
	interface NumberRangeBuilder extends NumberRange, RosettaModelObjectBuilder {
		NumberBound.NumberBoundBuilder getOrCreateLowerBound();
		NumberBound.NumberBoundBuilder getLowerBound();
		NumberBound.NumberBoundBuilder getOrCreateUpperBound();
		NumberBound.NumberBoundBuilder getUpperBound();
		NumberRange.NumberRangeBuilder setLowerBound(NumberBound lowerBound);
		NumberRange.NumberRangeBuilder setUpperBound(NumberBound upperBound);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("lowerBound"), processor, NumberBound.NumberBoundBuilder.class, getLowerBound());
			processRosetta(path.newSubPath("upperBound"), processor, NumberBound.NumberBoundBuilder.class, getUpperBound());
		}
		

		NumberRange.NumberRangeBuilder prune();
	}

	/*********************** Immutable Implementation of NumberRange  ***********************/
	class NumberRangeImpl implements NumberRange {
		private final NumberBound lowerBound;
		private final NumberBound upperBound;
		
		protected NumberRangeImpl(NumberRange.NumberRangeBuilder builder) {
			this.lowerBound = ofNullable(builder.getLowerBound()).map(f->f.build()).orElse(null);
			this.upperBound = ofNullable(builder.getUpperBound()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("lowerBound")
		public NumberBound getLowerBound() {
			return lowerBound;
		}
		
		@Override
		@RosettaAttribute("upperBound")
		public NumberBound getUpperBound() {
			return upperBound;
		}
		
		@Override
		public NumberRange build() {
			return this;
		}
		
		@Override
		public NumberRange.NumberRangeBuilder toBuilder() {
			NumberRange.NumberRangeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(NumberRange.NumberRangeBuilder builder) {
			ofNullable(getLowerBound()).ifPresent(builder::setLowerBound);
			ofNullable(getUpperBound()).ifPresent(builder::setUpperBound);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NumberRange _that = getType().cast(o);
		
			if (!Objects.equals(lowerBound, _that.getLowerBound())) return false;
			if (!Objects.equals(upperBound, _that.getUpperBound())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (lowerBound != null ? lowerBound.hashCode() : 0);
			_result = 31 * _result + (upperBound != null ? upperBound.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "NumberRange {" +
				"lowerBound=" + this.lowerBound + ", " +
				"upperBound=" + this.upperBound +
			'}';
		}
	}

	/*********************** Builder Implementation of NumberRange  ***********************/
	class NumberRangeBuilderImpl implements NumberRange.NumberRangeBuilder {
	
		protected NumberBound.NumberBoundBuilder lowerBound;
		protected NumberBound.NumberBoundBuilder upperBound;
	
		public NumberRangeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("lowerBound")
		public NumberBound.NumberBoundBuilder getLowerBound() {
			return lowerBound;
		}
		
		@Override
		public NumberBound.NumberBoundBuilder getOrCreateLowerBound() {
			NumberBound.NumberBoundBuilder result;
			if (lowerBound!=null) {
				result = lowerBound;
			}
			else {
				result = lowerBound = NumberBound.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("upperBound")
		public NumberBound.NumberBoundBuilder getUpperBound() {
			return upperBound;
		}
		
		@Override
		public NumberBound.NumberBoundBuilder getOrCreateUpperBound() {
			NumberBound.NumberBoundBuilder result;
			if (upperBound!=null) {
				result = upperBound;
			}
			else {
				result = upperBound = NumberBound.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("lowerBound")
		public NumberRange.NumberRangeBuilder setLowerBound(NumberBound lowerBound) {
			this.lowerBound = lowerBound==null?null:lowerBound.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("upperBound")
		public NumberRange.NumberRangeBuilder setUpperBound(NumberBound upperBound) {
			this.upperBound = upperBound==null?null:upperBound.toBuilder();
			return this;
		}
		
		@Override
		public NumberRange build() {
			return new NumberRange.NumberRangeImpl(this);
		}
		
		@Override
		public NumberRange.NumberRangeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NumberRange.NumberRangeBuilder prune() {
			if (lowerBound!=null && !lowerBound.prune().hasData()) lowerBound = null;
			if (upperBound!=null && !upperBound.prune().hasData()) upperBound = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getLowerBound()!=null && getLowerBound().hasData()) return true;
			if (getUpperBound()!=null && getUpperBound().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NumberRange.NumberRangeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			NumberRange.NumberRangeBuilder o = (NumberRange.NumberRangeBuilder) other;
			
			merger.mergeRosetta(getLowerBound(), o.getLowerBound(), this::setLowerBound);
			merger.mergeRosetta(getUpperBound(), o.getUpperBound(), this::setUpperBound);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NumberRange _that = getType().cast(o);
		
			if (!Objects.equals(lowerBound, _that.getLowerBound())) return false;
			if (!Objects.equals(upperBound, _that.getUpperBound())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (lowerBound != null ? lowerBound.hashCode() : 0);
			_result = 31 * _result + (upperBound != null ? upperBound.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "NumberRangeBuilder {" +
				"lowerBound=" + this.lowerBound + ", " +
				"upperBound=" + this.upperBound +
			'}';
		}
	}
}
