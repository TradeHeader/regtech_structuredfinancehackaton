package cdm.product.common.schedule;

import cdm.product.asset.StubValue;
import cdm.product.common.schedule.CalculationPeriodDates;
import cdm.product.common.schedule.StubPeriod;
import cdm.product.common.schedule.StubPeriod.StubPeriodBuilder;
import cdm.product.common.schedule.StubPeriod.StubPeriodBuilderImpl;
import cdm.product.common.schedule.StubPeriod.StubPeriodImpl;
import cdm.product.common.schedule.meta.StubPeriodMeta;
import cdm.product.common.schedule.metafields.ReferenceWithMetaCalculationPeriodDates;
import cdm.product.common.schedule.metafields.ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder;
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
 *  A class defining how the initial or final stub calculation period amounts is calculated. For example, the rate to be applied to the initial or final stub calculation period may be the linear interpolation of two different tenors for the floating rate index specified in the calculation period amount component, e.g. A two month stub period may used the linear interpolation of a one month and three month floating rate. The different rate tenors would be specified in this component. Note that a maximum of two rate tenors can be specified. If a stub period uses a single index tenor and this is the same as that specified in the calculation period amount component then the initial stub or final stub component, as the case may be, must not be included.
 * @version ${project.version}
 */
@RosettaDataType(value="StubPeriod", builder=StubPeriod.StubPeriodBuilderImpl.class, version="${project.version}")
public interface StubPeriod extends RosettaModelObject {

	StubPeriodMeta metaData = new StubPeriodMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A pointer style reference to the associated calculation period dates component defined elsewhere in the document.
	 */
	ReferenceWithMetaCalculationPeriodDates getCalculationPeriodDatesReference();
	/**
	 * Specifies how the initial stub amount is calculated. A single floating rate tenor different to that used for the regular part of the calculation periods schedule may be specified, or two floating tenors may be specified. If two floating rate tenors are specified then Linear Interpolation (in accordance with the 2000 ISDA Definitions, Section 8.3. Interpolation) is assumed to apply. Alternatively, an actual known stub rate or stub amount may be specified.
	 */
	StubValue getInitialStub();
	/**
	 * Specifies how the final stub amount is calculated. A single floating rate tenor different to that used for the regular part of the calculation periods schedule may be specified, or two floating tenors may be specified. If two floating rate tenors are specified then Linear Interpolation (in accordance with the 2000 ISDA Definitions, Section 8.3. Interpolation) is assumed to apply. Alternatively, an actual known stub rate or stub amount may be specified.
	 */
	StubValue getFinalStub();

	/*********************** Build Methods  ***********************/
	StubPeriod build();
	
	StubPeriod.StubPeriodBuilder toBuilder();
	
	static StubPeriod.StubPeriodBuilder builder() {
		return new StubPeriod.StubPeriodBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends StubPeriod> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends StubPeriod> getType() {
		return StubPeriod.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("calculationPeriodDatesReference"), processor, ReferenceWithMetaCalculationPeriodDates.class, getCalculationPeriodDatesReference());
		processRosetta(path.newSubPath("initialStub"), processor, StubValue.class, getInitialStub());
		processRosetta(path.newSubPath("finalStub"), processor, StubValue.class, getFinalStub());
	}
	

	/*********************** Builder Interface  ***********************/
	interface StubPeriodBuilder extends StubPeriod, RosettaModelObjectBuilder {
		ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder getOrCreateCalculationPeriodDatesReference();
		ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder getCalculationPeriodDatesReference();
		StubValue.StubValueBuilder getOrCreateInitialStub();
		StubValue.StubValueBuilder getInitialStub();
		StubValue.StubValueBuilder getOrCreateFinalStub();
		StubValue.StubValueBuilder getFinalStub();
		StubPeriod.StubPeriodBuilder setCalculationPeriodDatesReference(ReferenceWithMetaCalculationPeriodDates calculationPeriodDatesReference0);
		StubPeriod.StubPeriodBuilder setCalculationPeriodDatesReferenceValue(CalculationPeriodDates calculationPeriodDatesReference1);
		StubPeriod.StubPeriodBuilder setInitialStub(StubValue initialStub);
		StubPeriod.StubPeriodBuilder setFinalStub(StubValue finalStub);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("calculationPeriodDatesReference"), processor, ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder.class, getCalculationPeriodDatesReference());
			processRosetta(path.newSubPath("initialStub"), processor, StubValue.StubValueBuilder.class, getInitialStub());
			processRosetta(path.newSubPath("finalStub"), processor, StubValue.StubValueBuilder.class, getFinalStub());
		}
		

		StubPeriod.StubPeriodBuilder prune();
	}

	/*********************** Immutable Implementation of StubPeriod  ***********************/
	class StubPeriodImpl implements StubPeriod {
		private final ReferenceWithMetaCalculationPeriodDates calculationPeriodDatesReference;
		private final StubValue initialStub;
		private final StubValue finalStub;
		
		protected StubPeriodImpl(StubPeriod.StubPeriodBuilder builder) {
			this.calculationPeriodDatesReference = ofNullable(builder.getCalculationPeriodDatesReference()).map(f->f.build()).orElse(null);
			this.initialStub = ofNullable(builder.getInitialStub()).map(f->f.build()).orElse(null);
			this.finalStub = ofNullable(builder.getFinalStub()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("calculationPeriodDatesReference")
		public ReferenceWithMetaCalculationPeriodDates getCalculationPeriodDatesReference() {
			return calculationPeriodDatesReference;
		}
		
		@Override
		@RosettaAttribute("initialStub")
		public StubValue getInitialStub() {
			return initialStub;
		}
		
		@Override
		@RosettaAttribute("finalStub")
		public StubValue getFinalStub() {
			return finalStub;
		}
		
		@Override
		public StubPeriod build() {
			return this;
		}
		
		@Override
		public StubPeriod.StubPeriodBuilder toBuilder() {
			StubPeriod.StubPeriodBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(StubPeriod.StubPeriodBuilder builder) {
			ofNullable(getCalculationPeriodDatesReference()).ifPresent(builder::setCalculationPeriodDatesReference);
			ofNullable(getInitialStub()).ifPresent(builder::setInitialStub);
			ofNullable(getFinalStub()).ifPresent(builder::setFinalStub);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			StubPeriod _that = getType().cast(o);
		
			if (!Objects.equals(calculationPeriodDatesReference, _that.getCalculationPeriodDatesReference())) return false;
			if (!Objects.equals(initialStub, _that.getInitialStub())) return false;
			if (!Objects.equals(finalStub, _that.getFinalStub())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationPeriodDatesReference != null ? calculationPeriodDatesReference.hashCode() : 0);
			_result = 31 * _result + (initialStub != null ? initialStub.hashCode() : 0);
			_result = 31 * _result + (finalStub != null ? finalStub.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StubPeriod {" +
				"calculationPeriodDatesReference=" + this.calculationPeriodDatesReference + ", " +
				"initialStub=" + this.initialStub + ", " +
				"finalStub=" + this.finalStub +
			'}';
		}
	}

	/*********************** Builder Implementation of StubPeriod  ***********************/
	class StubPeriodBuilderImpl implements StubPeriod.StubPeriodBuilder {
	
		protected ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder calculationPeriodDatesReference;
		protected StubValue.StubValueBuilder initialStub;
		protected StubValue.StubValueBuilder finalStub;
	
		public StubPeriodBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("calculationPeriodDatesReference")
		public ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder getCalculationPeriodDatesReference() {
			return calculationPeriodDatesReference;
		}
		
		@Override
		public ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder getOrCreateCalculationPeriodDatesReference() {
			ReferenceWithMetaCalculationPeriodDates.ReferenceWithMetaCalculationPeriodDatesBuilder result;
			if (calculationPeriodDatesReference!=null) {
				result = calculationPeriodDatesReference;
			}
			else {
				result = calculationPeriodDatesReference = ReferenceWithMetaCalculationPeriodDates.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("initialStub")
		public StubValue.StubValueBuilder getInitialStub() {
			return initialStub;
		}
		
		@Override
		public StubValue.StubValueBuilder getOrCreateInitialStub() {
			StubValue.StubValueBuilder result;
			if (initialStub!=null) {
				result = initialStub;
			}
			else {
				result = initialStub = StubValue.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("finalStub")
		public StubValue.StubValueBuilder getFinalStub() {
			return finalStub;
		}
		
		@Override
		public StubValue.StubValueBuilder getOrCreateFinalStub() {
			StubValue.StubValueBuilder result;
			if (finalStub!=null) {
				result = finalStub;
			}
			else {
				result = finalStub = StubValue.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("calculationPeriodDatesReference")
		public StubPeriod.StubPeriodBuilder setCalculationPeriodDatesReference(ReferenceWithMetaCalculationPeriodDates calculationPeriodDatesReference) {
			this.calculationPeriodDatesReference = calculationPeriodDatesReference==null?null:calculationPeriodDatesReference.toBuilder();
			return this;
		}
		@Override
		public StubPeriod.StubPeriodBuilder setCalculationPeriodDatesReferenceValue(CalculationPeriodDates calculationPeriodDatesReference) {
			this.getOrCreateCalculationPeriodDatesReference().setValue(calculationPeriodDatesReference);
			return this;
		}
		@Override
		@RosettaAttribute("initialStub")
		public StubPeriod.StubPeriodBuilder setInitialStub(StubValue initialStub) {
			this.initialStub = initialStub==null?null:initialStub.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("finalStub")
		public StubPeriod.StubPeriodBuilder setFinalStub(StubValue finalStub) {
			this.finalStub = finalStub==null?null:finalStub.toBuilder();
			return this;
		}
		
		@Override
		public StubPeriod build() {
			return new StubPeriod.StubPeriodImpl(this);
		}
		
		@Override
		public StubPeriod.StubPeriodBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StubPeriod.StubPeriodBuilder prune() {
			if (calculationPeriodDatesReference!=null && !calculationPeriodDatesReference.prune().hasData()) calculationPeriodDatesReference = null;
			if (initialStub!=null && !initialStub.prune().hasData()) initialStub = null;
			if (finalStub!=null && !finalStub.prune().hasData()) finalStub = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCalculationPeriodDatesReference()!=null && getCalculationPeriodDatesReference().hasData()) return true;
			if (getInitialStub()!=null && getInitialStub().hasData()) return true;
			if (getFinalStub()!=null && getFinalStub().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public StubPeriod.StubPeriodBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			StubPeriod.StubPeriodBuilder o = (StubPeriod.StubPeriodBuilder) other;
			
			merger.mergeRosetta(getCalculationPeriodDatesReference(), o.getCalculationPeriodDatesReference(), this::setCalculationPeriodDatesReference);
			merger.mergeRosetta(getInitialStub(), o.getInitialStub(), this::setInitialStub);
			merger.mergeRosetta(getFinalStub(), o.getFinalStub(), this::setFinalStub);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			StubPeriod _that = getType().cast(o);
		
			if (!Objects.equals(calculationPeriodDatesReference, _that.getCalculationPeriodDatesReference())) return false;
			if (!Objects.equals(initialStub, _that.getInitialStub())) return false;
			if (!Objects.equals(finalStub, _that.getFinalStub())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationPeriodDatesReference != null ? calculationPeriodDatesReference.hashCode() : 0);
			_result = 31 * _result + (initialStub != null ? initialStub.hashCode() : 0);
			_result = 31 * _result + (finalStub != null ? finalStub.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "StubPeriodBuilder {" +
				"calculationPeriodDatesReference=" + this.calculationPeriodDatesReference + ", " +
				"initialStub=" + this.initialStub + ", " +
				"finalStub=" + this.finalStub +
			'}';
		}
	}
}
