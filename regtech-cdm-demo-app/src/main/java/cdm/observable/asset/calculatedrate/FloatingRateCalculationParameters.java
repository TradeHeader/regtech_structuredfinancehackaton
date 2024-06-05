package cdm.observable.asset.calculatedrate;

import cdm.base.datetime.BusinessCenters;
import cdm.observable.asset.calculatedrate.CalculationMethodEnum;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilderImpl;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters.FloatingRateCalculationParametersImpl;
import cdm.observable.asset.calculatedrate.ObservationParameters;
import cdm.observable.asset.calculatedrate.ObservationShiftCalculation;
import cdm.observable.asset.calculatedrate.OffsetCalculation;
import cdm.observable.asset.calculatedrate.meta.FloatingRateCalculationParametersMeta;
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
 * Defines the structures needed to represent the calculation parameters for daily averaged and compounded modular rates as defined in the 2021 ISDA Definitions in Section 7. This type is used to represent modular computed rates in interestRatePayouts.
 * @version ${project.version}
 */
@RosettaDataType(value="FloatingRateCalculationParameters", builder=FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilderImpl.class, version="${project.version}")
public interface FloatingRateCalculationParameters extends RosettaModelObject {

	FloatingRateCalculationParametersMeta metaData = new FloatingRateCalculationParametersMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * calculation type (averaging or compounding).
	 */
	CalculationMethodEnum getCalculationMethod();
	/**
	 * any obervation shift parameters if applicable.
	 */
	ObservationShiftCalculation getObservationShiftCalculation();
	/**
	 * any lookback  parameters if applicable.
	 */
	OffsetCalculation getLookbackCalculation();
	/**
	 * any lockout  parameters if applicable.
	 */
	OffsetCalculation getLockoutCalculation();
	/**
	 * the business days that are applicable for the calculation.
	 */
	BusinessCenters getApplicableBusinessDays();
	/**
	 *  any applicable observation parameters, such as daily caps or floors.
	 */
	ObservationParameters getObservationParameters();

	/*********************** Build Methods  ***********************/
	FloatingRateCalculationParameters build();
	
	FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder toBuilder();
	
	static FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder builder() {
		return new FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FloatingRateCalculationParameters> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FloatingRateCalculationParameters> getType() {
		return FloatingRateCalculationParameters.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("calculationMethod"), CalculationMethodEnum.class, getCalculationMethod(), this);
		processRosetta(path.newSubPath("observationShiftCalculation"), processor, ObservationShiftCalculation.class, getObservationShiftCalculation());
		processRosetta(path.newSubPath("lookbackCalculation"), processor, OffsetCalculation.class, getLookbackCalculation());
		processRosetta(path.newSubPath("lockoutCalculation"), processor, OffsetCalculation.class, getLockoutCalculation());
		processRosetta(path.newSubPath("applicableBusinessDays"), processor, BusinessCenters.class, getApplicableBusinessDays());
		processRosetta(path.newSubPath("observationParameters"), processor, ObservationParameters.class, getObservationParameters());
	}
	

	/*********************** Builder Interface  ***********************/
	interface FloatingRateCalculationParametersBuilder extends FloatingRateCalculationParameters, RosettaModelObjectBuilder {
		ObservationShiftCalculation.ObservationShiftCalculationBuilder getOrCreateObservationShiftCalculation();
		ObservationShiftCalculation.ObservationShiftCalculationBuilder getObservationShiftCalculation();
		OffsetCalculation.OffsetCalculationBuilder getOrCreateLookbackCalculation();
		OffsetCalculation.OffsetCalculationBuilder getLookbackCalculation();
		OffsetCalculation.OffsetCalculationBuilder getOrCreateLockoutCalculation();
		OffsetCalculation.OffsetCalculationBuilder getLockoutCalculation();
		BusinessCenters.BusinessCentersBuilder getOrCreateApplicableBusinessDays();
		BusinessCenters.BusinessCentersBuilder getApplicableBusinessDays();
		ObservationParameters.ObservationParametersBuilder getOrCreateObservationParameters();
		ObservationParameters.ObservationParametersBuilder getObservationParameters();
		FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setCalculationMethod(CalculationMethodEnum calculationMethod);
		FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setObservationShiftCalculation(ObservationShiftCalculation observationShiftCalculation);
		FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setLookbackCalculation(OffsetCalculation lookbackCalculation);
		FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setLockoutCalculation(OffsetCalculation lockoutCalculation);
		FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setApplicableBusinessDays(BusinessCenters applicableBusinessDays);
		FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setObservationParameters(ObservationParameters observationParameters);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("calculationMethod"), CalculationMethodEnum.class, getCalculationMethod(), this);
			processRosetta(path.newSubPath("observationShiftCalculation"), processor, ObservationShiftCalculation.ObservationShiftCalculationBuilder.class, getObservationShiftCalculation());
			processRosetta(path.newSubPath("lookbackCalculation"), processor, OffsetCalculation.OffsetCalculationBuilder.class, getLookbackCalculation());
			processRosetta(path.newSubPath("lockoutCalculation"), processor, OffsetCalculation.OffsetCalculationBuilder.class, getLockoutCalculation());
			processRosetta(path.newSubPath("applicableBusinessDays"), processor, BusinessCenters.BusinessCentersBuilder.class, getApplicableBusinessDays());
			processRosetta(path.newSubPath("observationParameters"), processor, ObservationParameters.ObservationParametersBuilder.class, getObservationParameters());
		}
		

		FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder prune();
	}

	/*********************** Immutable Implementation of FloatingRateCalculationParameters  ***********************/
	class FloatingRateCalculationParametersImpl implements FloatingRateCalculationParameters {
		private final CalculationMethodEnum calculationMethod;
		private final ObservationShiftCalculation observationShiftCalculation;
		private final OffsetCalculation lookbackCalculation;
		private final OffsetCalculation lockoutCalculation;
		private final BusinessCenters applicableBusinessDays;
		private final ObservationParameters observationParameters;
		
		protected FloatingRateCalculationParametersImpl(FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder builder) {
			this.calculationMethod = builder.getCalculationMethod();
			this.observationShiftCalculation = ofNullable(builder.getObservationShiftCalculation()).map(f->f.build()).orElse(null);
			this.lookbackCalculation = ofNullable(builder.getLookbackCalculation()).map(f->f.build()).orElse(null);
			this.lockoutCalculation = ofNullable(builder.getLockoutCalculation()).map(f->f.build()).orElse(null);
			this.applicableBusinessDays = ofNullable(builder.getApplicableBusinessDays()).map(f->f.build()).orElse(null);
			this.observationParameters = ofNullable(builder.getObservationParameters()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("calculationMethod")
		public CalculationMethodEnum getCalculationMethod() {
			return calculationMethod;
		}
		
		@Override
		@RosettaAttribute("observationShiftCalculation")
		public ObservationShiftCalculation getObservationShiftCalculation() {
			return observationShiftCalculation;
		}
		
		@Override
		@RosettaAttribute("lookbackCalculation")
		public OffsetCalculation getLookbackCalculation() {
			return lookbackCalculation;
		}
		
		@Override
		@RosettaAttribute("lockoutCalculation")
		public OffsetCalculation getLockoutCalculation() {
			return lockoutCalculation;
		}
		
		@Override
		@RosettaAttribute("applicableBusinessDays")
		public BusinessCenters getApplicableBusinessDays() {
			return applicableBusinessDays;
		}
		
		@Override
		@RosettaAttribute("observationParameters")
		public ObservationParameters getObservationParameters() {
			return observationParameters;
		}
		
		@Override
		public FloatingRateCalculationParameters build() {
			return this;
		}
		
		@Override
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder toBuilder() {
			FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder builder) {
			ofNullable(getCalculationMethod()).ifPresent(builder::setCalculationMethod);
			ofNullable(getObservationShiftCalculation()).ifPresent(builder::setObservationShiftCalculation);
			ofNullable(getLookbackCalculation()).ifPresent(builder::setLookbackCalculation);
			ofNullable(getLockoutCalculation()).ifPresent(builder::setLockoutCalculation);
			ofNullable(getApplicableBusinessDays()).ifPresent(builder::setApplicableBusinessDays);
			ofNullable(getObservationParameters()).ifPresent(builder::setObservationParameters);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateCalculationParameters _that = getType().cast(o);
		
			if (!Objects.equals(calculationMethod, _that.getCalculationMethod())) return false;
			if (!Objects.equals(observationShiftCalculation, _that.getObservationShiftCalculation())) return false;
			if (!Objects.equals(lookbackCalculation, _that.getLookbackCalculation())) return false;
			if (!Objects.equals(lockoutCalculation, _that.getLockoutCalculation())) return false;
			if (!Objects.equals(applicableBusinessDays, _that.getApplicableBusinessDays())) return false;
			if (!Objects.equals(observationParameters, _that.getObservationParameters())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationMethod != null ? calculationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (observationShiftCalculation != null ? observationShiftCalculation.hashCode() : 0);
			_result = 31 * _result + (lookbackCalculation != null ? lookbackCalculation.hashCode() : 0);
			_result = 31 * _result + (lockoutCalculation != null ? lockoutCalculation.hashCode() : 0);
			_result = 31 * _result + (applicableBusinessDays != null ? applicableBusinessDays.hashCode() : 0);
			_result = 31 * _result + (observationParameters != null ? observationParameters.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateCalculationParameters {" +
				"calculationMethod=" + this.calculationMethod + ", " +
				"observationShiftCalculation=" + this.observationShiftCalculation + ", " +
				"lookbackCalculation=" + this.lookbackCalculation + ", " +
				"lockoutCalculation=" + this.lockoutCalculation + ", " +
				"applicableBusinessDays=" + this.applicableBusinessDays + ", " +
				"observationParameters=" + this.observationParameters +
			'}';
		}
	}

	/*********************** Builder Implementation of FloatingRateCalculationParameters  ***********************/
	class FloatingRateCalculationParametersBuilderImpl implements FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder {
	
		protected CalculationMethodEnum calculationMethod;
		protected ObservationShiftCalculation.ObservationShiftCalculationBuilder observationShiftCalculation;
		protected OffsetCalculation.OffsetCalculationBuilder lookbackCalculation;
		protected OffsetCalculation.OffsetCalculationBuilder lockoutCalculation;
		protected BusinessCenters.BusinessCentersBuilder applicableBusinessDays;
		protected ObservationParameters.ObservationParametersBuilder observationParameters;
	
		public FloatingRateCalculationParametersBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("calculationMethod")
		public CalculationMethodEnum getCalculationMethod() {
			return calculationMethod;
		}
		
		@Override
		@RosettaAttribute("observationShiftCalculation")
		public ObservationShiftCalculation.ObservationShiftCalculationBuilder getObservationShiftCalculation() {
			return observationShiftCalculation;
		}
		
		@Override
		public ObservationShiftCalculation.ObservationShiftCalculationBuilder getOrCreateObservationShiftCalculation() {
			ObservationShiftCalculation.ObservationShiftCalculationBuilder result;
			if (observationShiftCalculation!=null) {
				result = observationShiftCalculation;
			}
			else {
				result = observationShiftCalculation = ObservationShiftCalculation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("lookbackCalculation")
		public OffsetCalculation.OffsetCalculationBuilder getLookbackCalculation() {
			return lookbackCalculation;
		}
		
		@Override
		public OffsetCalculation.OffsetCalculationBuilder getOrCreateLookbackCalculation() {
			OffsetCalculation.OffsetCalculationBuilder result;
			if (lookbackCalculation!=null) {
				result = lookbackCalculation;
			}
			else {
				result = lookbackCalculation = OffsetCalculation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("lockoutCalculation")
		public OffsetCalculation.OffsetCalculationBuilder getLockoutCalculation() {
			return lockoutCalculation;
		}
		
		@Override
		public OffsetCalculation.OffsetCalculationBuilder getOrCreateLockoutCalculation() {
			OffsetCalculation.OffsetCalculationBuilder result;
			if (lockoutCalculation!=null) {
				result = lockoutCalculation;
			}
			else {
				result = lockoutCalculation = OffsetCalculation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("applicableBusinessDays")
		public BusinessCenters.BusinessCentersBuilder getApplicableBusinessDays() {
			return applicableBusinessDays;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder getOrCreateApplicableBusinessDays() {
			BusinessCenters.BusinessCentersBuilder result;
			if (applicableBusinessDays!=null) {
				result = applicableBusinessDays;
			}
			else {
				result = applicableBusinessDays = BusinessCenters.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("observationParameters")
		public ObservationParameters.ObservationParametersBuilder getObservationParameters() {
			return observationParameters;
		}
		
		@Override
		public ObservationParameters.ObservationParametersBuilder getOrCreateObservationParameters() {
			ObservationParameters.ObservationParametersBuilder result;
			if (observationParameters!=null) {
				result = observationParameters;
			}
			else {
				result = observationParameters = ObservationParameters.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("calculationMethod")
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setCalculationMethod(CalculationMethodEnum calculationMethod) {
			this.calculationMethod = calculationMethod==null?null:calculationMethod;
			return this;
		}
		@Override
		@RosettaAttribute("observationShiftCalculation")
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setObservationShiftCalculation(ObservationShiftCalculation observationShiftCalculation) {
			this.observationShiftCalculation = observationShiftCalculation==null?null:observationShiftCalculation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("lookbackCalculation")
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setLookbackCalculation(OffsetCalculation lookbackCalculation) {
			this.lookbackCalculation = lookbackCalculation==null?null:lookbackCalculation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("lockoutCalculation")
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setLockoutCalculation(OffsetCalculation lockoutCalculation) {
			this.lockoutCalculation = lockoutCalculation==null?null:lockoutCalculation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("applicableBusinessDays")
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setApplicableBusinessDays(BusinessCenters applicableBusinessDays) {
			this.applicableBusinessDays = applicableBusinessDays==null?null:applicableBusinessDays.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("observationParameters")
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder setObservationParameters(ObservationParameters observationParameters) {
			this.observationParameters = observationParameters==null?null:observationParameters.toBuilder();
			return this;
		}
		
		@Override
		public FloatingRateCalculationParameters build() {
			return new FloatingRateCalculationParameters.FloatingRateCalculationParametersImpl(this);
		}
		
		@Override
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder prune() {
			if (observationShiftCalculation!=null && !observationShiftCalculation.prune().hasData()) observationShiftCalculation = null;
			if (lookbackCalculation!=null && !lookbackCalculation.prune().hasData()) lookbackCalculation = null;
			if (lockoutCalculation!=null && !lockoutCalculation.prune().hasData()) lockoutCalculation = null;
			if (applicableBusinessDays!=null && !applicableBusinessDays.prune().hasData()) applicableBusinessDays = null;
			if (observationParameters!=null && !observationParameters.prune().hasData()) observationParameters = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getCalculationMethod()!=null) return true;
			if (getObservationShiftCalculation()!=null && getObservationShiftCalculation().hasData()) return true;
			if (getLookbackCalculation()!=null && getLookbackCalculation().hasData()) return true;
			if (getLockoutCalculation()!=null && getLockoutCalculation().hasData()) return true;
			if (getApplicableBusinessDays()!=null && getApplicableBusinessDays().hasData()) return true;
			if (getObservationParameters()!=null && getObservationParameters().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder o = (FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder) other;
			
			merger.mergeRosetta(getObservationShiftCalculation(), o.getObservationShiftCalculation(), this::setObservationShiftCalculation);
			merger.mergeRosetta(getLookbackCalculation(), o.getLookbackCalculation(), this::setLookbackCalculation);
			merger.mergeRosetta(getLockoutCalculation(), o.getLockoutCalculation(), this::setLockoutCalculation);
			merger.mergeRosetta(getApplicableBusinessDays(), o.getApplicableBusinessDays(), this::setApplicableBusinessDays);
			merger.mergeRosetta(getObservationParameters(), o.getObservationParameters(), this::setObservationParameters);
			
			merger.mergeBasic(getCalculationMethod(), o.getCalculationMethod(), this::setCalculationMethod);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateCalculationParameters _that = getType().cast(o);
		
			if (!Objects.equals(calculationMethod, _that.getCalculationMethod())) return false;
			if (!Objects.equals(observationShiftCalculation, _that.getObservationShiftCalculation())) return false;
			if (!Objects.equals(lookbackCalculation, _that.getLookbackCalculation())) return false;
			if (!Objects.equals(lockoutCalculation, _that.getLockoutCalculation())) return false;
			if (!Objects.equals(applicableBusinessDays, _that.getApplicableBusinessDays())) return false;
			if (!Objects.equals(observationParameters, _that.getObservationParameters())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (calculationMethod != null ? calculationMethod.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (observationShiftCalculation != null ? observationShiftCalculation.hashCode() : 0);
			_result = 31 * _result + (lookbackCalculation != null ? lookbackCalculation.hashCode() : 0);
			_result = 31 * _result + (lockoutCalculation != null ? lockoutCalculation.hashCode() : 0);
			_result = 31 * _result + (applicableBusinessDays != null ? applicableBusinessDays.hashCode() : 0);
			_result = 31 * _result + (observationParameters != null ? observationParameters.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateCalculationParametersBuilder {" +
				"calculationMethod=" + this.calculationMethod + ", " +
				"observationShiftCalculation=" + this.observationShiftCalculation + ", " +
				"lookbackCalculation=" + this.lookbackCalculation + ", " +
				"lockoutCalculation=" + this.lockoutCalculation + ", " +
				"applicableBusinessDays=" + this.applicableBusinessDays + ", " +
				"observationParameters=" + this.observationParameters +
			'}';
		}
	}
}
