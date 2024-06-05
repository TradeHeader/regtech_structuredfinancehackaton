package cdm.base.datetime;

import cdm.base.datetime.PeriodBound;
import cdm.base.datetime.PeriodRange;
import cdm.base.datetime.PeriodRange.PeriodRangeBuilder;
import cdm.base.datetime.PeriodRange.PeriodRangeBuilderImpl;
import cdm.base.datetime.PeriodRange.PeriodRangeImpl;
import cdm.base.datetime.meta.PeriodRangeMeta;
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
 * Indicates The period range defined as either a lower and upper period bound, or both.
 * @version ${project.version}
 */
@RosettaDataType(value="PeriodRange", builder=PeriodRange.PeriodRangeBuilderImpl.class, version="${project.version}")
public interface PeriodRange extends RosettaModelObject {

	PeriodRangeMeta metaData = new PeriodRangeMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Specifies the lower bound of a period range, e.g. greater than or equal to 5Y.
	 */
	PeriodBound getLowerBound();
	/**
	 * Specifies the upper bound of a period range, e.g. less than to 10Y.
	 */
	PeriodBound getUpperBound();

	/*********************** Build Methods  ***********************/
	PeriodRange build();
	
	PeriodRange.PeriodRangeBuilder toBuilder();
	
	static PeriodRange.PeriodRangeBuilder builder() {
		return new PeriodRange.PeriodRangeBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PeriodRange> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends PeriodRange> getType() {
		return PeriodRange.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("lowerBound"), processor, PeriodBound.class, getLowerBound());
		processRosetta(path.newSubPath("upperBound"), processor, PeriodBound.class, getUpperBound());
	}
	

	/*********************** Builder Interface  ***********************/
	interface PeriodRangeBuilder extends PeriodRange, RosettaModelObjectBuilder {
		PeriodBound.PeriodBoundBuilder getOrCreateLowerBound();
		PeriodBound.PeriodBoundBuilder getLowerBound();
		PeriodBound.PeriodBoundBuilder getOrCreateUpperBound();
		PeriodBound.PeriodBoundBuilder getUpperBound();
		PeriodRange.PeriodRangeBuilder setLowerBound(PeriodBound lowerBound);
		PeriodRange.PeriodRangeBuilder setUpperBound(PeriodBound upperBound);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("lowerBound"), processor, PeriodBound.PeriodBoundBuilder.class, getLowerBound());
			processRosetta(path.newSubPath("upperBound"), processor, PeriodBound.PeriodBoundBuilder.class, getUpperBound());
		}
		

		PeriodRange.PeriodRangeBuilder prune();
	}

	/*********************** Immutable Implementation of PeriodRange  ***********************/
	class PeriodRangeImpl implements PeriodRange {
		private final PeriodBound lowerBound;
		private final PeriodBound upperBound;
		
		protected PeriodRangeImpl(PeriodRange.PeriodRangeBuilder builder) {
			this.lowerBound = ofNullable(builder.getLowerBound()).map(f->f.build()).orElse(null);
			this.upperBound = ofNullable(builder.getUpperBound()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("lowerBound")
		public PeriodBound getLowerBound() {
			return lowerBound;
		}
		
		@Override
		@RosettaAttribute("upperBound")
		public PeriodBound getUpperBound() {
			return upperBound;
		}
		
		@Override
		public PeriodRange build() {
			return this;
		}
		
		@Override
		public PeriodRange.PeriodRangeBuilder toBuilder() {
			PeriodRange.PeriodRangeBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PeriodRange.PeriodRangeBuilder builder) {
			ofNullable(getLowerBound()).ifPresent(builder::setLowerBound);
			ofNullable(getUpperBound()).ifPresent(builder::setUpperBound);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PeriodRange _that = getType().cast(o);
		
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
			return "PeriodRange {" +
				"lowerBound=" + this.lowerBound + ", " +
				"upperBound=" + this.upperBound +
			'}';
		}
	}

	/*********************** Builder Implementation of PeriodRange  ***********************/
	class PeriodRangeBuilderImpl implements PeriodRange.PeriodRangeBuilder {
	
		protected PeriodBound.PeriodBoundBuilder lowerBound;
		protected PeriodBound.PeriodBoundBuilder upperBound;
	
		public PeriodRangeBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("lowerBound")
		public PeriodBound.PeriodBoundBuilder getLowerBound() {
			return lowerBound;
		}
		
		@Override
		public PeriodBound.PeriodBoundBuilder getOrCreateLowerBound() {
			PeriodBound.PeriodBoundBuilder result;
			if (lowerBound!=null) {
				result = lowerBound;
			}
			else {
				result = lowerBound = PeriodBound.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("upperBound")
		public PeriodBound.PeriodBoundBuilder getUpperBound() {
			return upperBound;
		}
		
		@Override
		public PeriodBound.PeriodBoundBuilder getOrCreateUpperBound() {
			PeriodBound.PeriodBoundBuilder result;
			if (upperBound!=null) {
				result = upperBound;
			}
			else {
				result = upperBound = PeriodBound.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("lowerBound")
		public PeriodRange.PeriodRangeBuilder setLowerBound(PeriodBound lowerBound) {
			this.lowerBound = lowerBound==null?null:lowerBound.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("upperBound")
		public PeriodRange.PeriodRangeBuilder setUpperBound(PeriodBound upperBound) {
			this.upperBound = upperBound==null?null:upperBound.toBuilder();
			return this;
		}
		
		@Override
		public PeriodRange build() {
			return new PeriodRange.PeriodRangeImpl(this);
		}
		
		@Override
		public PeriodRange.PeriodRangeBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PeriodRange.PeriodRangeBuilder prune() {
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
		public PeriodRange.PeriodRangeBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PeriodRange.PeriodRangeBuilder o = (PeriodRange.PeriodRangeBuilder) other;
			
			merger.mergeRosetta(getLowerBound(), o.getLowerBound(), this::setLowerBound);
			merger.mergeRosetta(getUpperBound(), o.getUpperBound(), this::setUpperBound);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PeriodRange _that = getType().cast(o);
		
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
			return "PeriodRangeBuilder {" +
				"lowerBound=" + this.lowerBound + ", " +
				"upperBound=" + this.upperBound +
			'}';
		}
	}
}
