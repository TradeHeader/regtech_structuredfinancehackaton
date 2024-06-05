package cdm.base.math;

import cdm.base.math.NumberBound;
import cdm.base.math.NumberBound.NumberBoundBuilder;
import cdm.base.math.NumberBound.NumberBoundBuilderImpl;
import cdm.base.math.NumberBound.NumberBoundImpl;
import cdm.base.math.meta.NumberBoundMeta;
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
 * The number bound is defined as a number and whether the bound is inclusive.
 * @version ${project.version}
 */
@RosettaDataType(value="NumberBound", builder=NumberBound.NumberBoundBuilderImpl.class, version="${project.version}")
public interface NumberBound extends RosettaModelObject {

	NumberBoundMeta metaData = new NumberBoundMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The number to be used as the bound, e.g. 5.
	 */
	BigDecimal getNumber();
	/**
	 * Whether the number bound is inclusive, e.g. for a lower bound, false would indicate greater than, whereas true would indicate greater than or equal to.
	 */
	Boolean getInclusive();

	/*********************** Build Methods  ***********************/
	NumberBound build();
	
	NumberBound.NumberBoundBuilder toBuilder();
	
	static NumberBound.NumberBoundBuilder builder() {
		return new NumberBound.NumberBoundBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends NumberBound> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends NumberBound> getType() {
		return NumberBound.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("number"), BigDecimal.class, getNumber(), this);
		processor.processBasic(path.newSubPath("inclusive"), Boolean.class, getInclusive(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface NumberBoundBuilder extends NumberBound, RosettaModelObjectBuilder {
		NumberBound.NumberBoundBuilder setNumber(BigDecimal number);
		NumberBound.NumberBoundBuilder setInclusive(Boolean inclusive);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("number"), BigDecimal.class, getNumber(), this);
			processor.processBasic(path.newSubPath("inclusive"), Boolean.class, getInclusive(), this);
		}
		

		NumberBound.NumberBoundBuilder prune();
	}

	/*********************** Immutable Implementation of NumberBound  ***********************/
	class NumberBoundImpl implements NumberBound {
		private final BigDecimal number;
		private final Boolean inclusive;
		
		protected NumberBoundImpl(NumberBound.NumberBoundBuilder builder) {
			this.number = builder.getNumber();
			this.inclusive = builder.getInclusive();
		}
		
		@Override
		@RosettaAttribute("number")
		public BigDecimal getNumber() {
			return number;
		}
		
		@Override
		@RosettaAttribute("inclusive")
		public Boolean getInclusive() {
			return inclusive;
		}
		
		@Override
		public NumberBound build() {
			return this;
		}
		
		@Override
		public NumberBound.NumberBoundBuilder toBuilder() {
			NumberBound.NumberBoundBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(NumberBound.NumberBoundBuilder builder) {
			ofNullable(getNumber()).ifPresent(builder::setNumber);
			ofNullable(getInclusive()).ifPresent(builder::setInclusive);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NumberBound _that = getType().cast(o);
		
			if (!Objects.equals(number, _that.getNumber())) return false;
			if (!Objects.equals(inclusive, _that.getInclusive())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (number != null ? number.hashCode() : 0);
			_result = 31 * _result + (inclusive != null ? inclusive.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "NumberBound {" +
				"number=" + this.number + ", " +
				"inclusive=" + this.inclusive +
			'}';
		}
	}

	/*********************** Builder Implementation of NumberBound  ***********************/
	class NumberBoundBuilderImpl implements NumberBound.NumberBoundBuilder {
	
		protected BigDecimal number;
		protected Boolean inclusive;
	
		public NumberBoundBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("number")
		public BigDecimal getNumber() {
			return number;
		}
		
		@Override
		@RosettaAttribute("inclusive")
		public Boolean getInclusive() {
			return inclusive;
		}
		
	
		@Override
		@RosettaAttribute("number")
		public NumberBound.NumberBoundBuilder setNumber(BigDecimal number) {
			this.number = number==null?null:number;
			return this;
		}
		@Override
		@RosettaAttribute("inclusive")
		public NumberBound.NumberBoundBuilder setInclusive(Boolean inclusive) {
			this.inclusive = inclusive==null?null:inclusive;
			return this;
		}
		
		@Override
		public NumberBound build() {
			return new NumberBound.NumberBoundImpl(this);
		}
		
		@Override
		public NumberBound.NumberBoundBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NumberBound.NumberBoundBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getNumber()!=null) return true;
			if (getInclusive()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public NumberBound.NumberBoundBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			NumberBound.NumberBoundBuilder o = (NumberBound.NumberBoundBuilder) other;
			
			
			merger.mergeBasic(getNumber(), o.getNumber(), this::setNumber);
			merger.mergeBasic(getInclusive(), o.getInclusive(), this::setInclusive);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			NumberBound _that = getType().cast(o);
		
			if (!Objects.equals(number, _that.getNumber())) return false;
			if (!Objects.equals(inclusive, _that.getInclusive())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (number != null ? number.hashCode() : 0);
			_result = 31 * _result + (inclusive != null ? inclusive.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "NumberBoundBuilder {" +
				"number=" + this.number + ", " +
				"inclusive=" + this.inclusive +
			'}';
		}
	}
}
