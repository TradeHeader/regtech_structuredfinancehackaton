package cdm.observable.asset.calculatedrate;

import cdm.observable.asset.calculatedrate.OffsetCalculation;
import cdm.observable.asset.calculatedrate.OffsetCalculation.OffsetCalculationBuilder;
import cdm.observable.asset.calculatedrate.OffsetCalculation.OffsetCalculationBuilderImpl;
import cdm.observable.asset.calculatedrate.OffsetCalculation.OffsetCalculationImpl;
import cdm.observable.asset.calculatedrate.meta.OffsetCalculationMeta;
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
 * Defines business day shifts for daily componded or averaged rates.  This type is used for lookback and lockout rates. This type is used to represent modular computed rates in interestRatePayouts.
 * @version ${project.version}
 */
@RosettaDataType(value="OffsetCalculation", builder=OffsetCalculation.OffsetCalculationBuilderImpl.class, version="${project.version}")
public interface OffsetCalculation extends RosettaModelObject {

	OffsetCalculationMeta metaData = new OffsetCalculationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The number of business days offset.
	 */
	Integer getOffsetDays();

	/*********************** Build Methods  ***********************/
	OffsetCalculation build();
	
	OffsetCalculation.OffsetCalculationBuilder toBuilder();
	
	static OffsetCalculation.OffsetCalculationBuilder builder() {
		return new OffsetCalculation.OffsetCalculationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends OffsetCalculation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends OffsetCalculation> getType() {
		return OffsetCalculation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("offsetDays"), Integer.class, getOffsetDays(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface OffsetCalculationBuilder extends OffsetCalculation, RosettaModelObjectBuilder {
		OffsetCalculation.OffsetCalculationBuilder setOffsetDays(Integer offsetDays);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("offsetDays"), Integer.class, getOffsetDays(), this);
		}
		

		OffsetCalculation.OffsetCalculationBuilder prune();
	}

	/*********************** Immutable Implementation of OffsetCalculation  ***********************/
	class OffsetCalculationImpl implements OffsetCalculation {
		private final Integer offsetDays;
		
		protected OffsetCalculationImpl(OffsetCalculation.OffsetCalculationBuilder builder) {
			this.offsetDays = builder.getOffsetDays();
		}
		
		@Override
		@RosettaAttribute("offsetDays")
		public Integer getOffsetDays() {
			return offsetDays;
		}
		
		@Override
		public OffsetCalculation build() {
			return this;
		}
		
		@Override
		public OffsetCalculation.OffsetCalculationBuilder toBuilder() {
			OffsetCalculation.OffsetCalculationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(OffsetCalculation.OffsetCalculationBuilder builder) {
			ofNullable(getOffsetDays()).ifPresent(builder::setOffsetDays);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OffsetCalculation _that = getType().cast(o);
		
			if (!Objects.equals(offsetDays, _that.getOffsetDays())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (offsetDays != null ? offsetDays.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OffsetCalculation {" +
				"offsetDays=" + this.offsetDays +
			'}';
		}
	}

	/*********************** Builder Implementation of OffsetCalculation  ***********************/
	class OffsetCalculationBuilderImpl implements OffsetCalculation.OffsetCalculationBuilder {
	
		protected Integer offsetDays;
	
		public OffsetCalculationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("offsetDays")
		public Integer getOffsetDays() {
			return offsetDays;
		}
		
	
		@Override
		@RosettaAttribute("offsetDays")
		public OffsetCalculation.OffsetCalculationBuilder setOffsetDays(Integer offsetDays) {
			this.offsetDays = offsetDays==null?null:offsetDays;
			return this;
		}
		
		@Override
		public OffsetCalculation build() {
			return new OffsetCalculation.OffsetCalculationImpl(this);
		}
		
		@Override
		public OffsetCalculation.OffsetCalculationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OffsetCalculation.OffsetCalculationBuilder prune() {
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getOffsetDays()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OffsetCalculation.OffsetCalculationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			OffsetCalculation.OffsetCalculationBuilder o = (OffsetCalculation.OffsetCalculationBuilder) other;
			
			
			merger.mergeBasic(getOffsetDays(), o.getOffsetDays(), this::setOffsetDays);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OffsetCalculation _that = getType().cast(o);
		
			if (!Objects.equals(offsetDays, _that.getOffsetDays())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (offsetDays != null ? offsetDays.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OffsetCalculationBuilder {" +
				"offsetDays=" + this.offsetDays +
			'}';
		}
	}
}
