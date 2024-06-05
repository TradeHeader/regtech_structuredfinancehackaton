package cdm.observable.asset.calculatedrate;

import cdm.base.datetime.BusinessCenters;
import cdm.observable.asset.calculatedrate.ObservationPeriodDatesEnum;
import cdm.observable.asset.calculatedrate.ObservationShiftCalculation;
import cdm.observable.asset.calculatedrate.ObservationShiftCalculation.ObservationShiftCalculationBuilder;
import cdm.observable.asset.calculatedrate.ObservationShiftCalculation.ObservationShiftCalculationBuilderImpl;
import cdm.observable.asset.calculatedrate.ObservationShiftCalculation.ObservationShiftCalculationImpl;
import cdm.observable.asset.calculatedrate.meta.ObservationShiftCalculationMeta;
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
 * Parameters to describe the observation shift for a daily compounded or averaged floating rate. This type is used to represent modular computed rates in interestRatePayouts.
 * @version ${project.version}
 */
@RosettaDataType(value="ObservationShiftCalculation", builder=ObservationShiftCalculation.ObservationShiftCalculationBuilderImpl.class, version="${project.version}")
public interface ObservationShiftCalculation extends RosettaModelObject {

	ObservationShiftCalculationMeta metaData = new ObservationShiftCalculationMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The number of days of observation shift.
	 */
	Integer getOffsetDays();
	/**
	 * Whether the rate is calculated in advance, in arrears, or relative to a reset date.
	 */
	ObservationPeriodDatesEnum getCalculationBase();
	/**
	 * Any additional business days that be applicable.
	 */
	BusinessCenters getAdditionalBusinessDays();

	/*********************** Build Methods  ***********************/
	ObservationShiftCalculation build();
	
	ObservationShiftCalculation.ObservationShiftCalculationBuilder toBuilder();
	
	static ObservationShiftCalculation.ObservationShiftCalculationBuilder builder() {
		return new ObservationShiftCalculation.ObservationShiftCalculationBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ObservationShiftCalculation> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ObservationShiftCalculation> getType() {
		return ObservationShiftCalculation.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("offsetDays"), Integer.class, getOffsetDays(), this);
		processor.processBasic(path.newSubPath("calculationBase"), ObservationPeriodDatesEnum.class, getCalculationBase(), this);
		processRosetta(path.newSubPath("additionalBusinessDays"), processor, BusinessCenters.class, getAdditionalBusinessDays());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ObservationShiftCalculationBuilder extends ObservationShiftCalculation, RosettaModelObjectBuilder {
		BusinessCenters.BusinessCentersBuilder getOrCreateAdditionalBusinessDays();
		BusinessCenters.BusinessCentersBuilder getAdditionalBusinessDays();
		ObservationShiftCalculation.ObservationShiftCalculationBuilder setOffsetDays(Integer offsetDays);
		ObservationShiftCalculation.ObservationShiftCalculationBuilder setCalculationBase(ObservationPeriodDatesEnum calculationBase);
		ObservationShiftCalculation.ObservationShiftCalculationBuilder setAdditionalBusinessDays(BusinessCenters additionalBusinessDays);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("offsetDays"), Integer.class, getOffsetDays(), this);
			processor.processBasic(path.newSubPath("calculationBase"), ObservationPeriodDatesEnum.class, getCalculationBase(), this);
			processRosetta(path.newSubPath("additionalBusinessDays"), processor, BusinessCenters.BusinessCentersBuilder.class, getAdditionalBusinessDays());
		}
		

		ObservationShiftCalculation.ObservationShiftCalculationBuilder prune();
	}

	/*********************** Immutable Implementation of ObservationShiftCalculation  ***********************/
	class ObservationShiftCalculationImpl implements ObservationShiftCalculation {
		private final Integer offsetDays;
		private final ObservationPeriodDatesEnum calculationBase;
		private final BusinessCenters additionalBusinessDays;
		
		protected ObservationShiftCalculationImpl(ObservationShiftCalculation.ObservationShiftCalculationBuilder builder) {
			this.offsetDays = builder.getOffsetDays();
			this.calculationBase = builder.getCalculationBase();
			this.additionalBusinessDays = ofNullable(builder.getAdditionalBusinessDays()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("offsetDays")
		public Integer getOffsetDays() {
			return offsetDays;
		}
		
		@Override
		@RosettaAttribute("calculationBase")
		public ObservationPeriodDatesEnum getCalculationBase() {
			return calculationBase;
		}
		
		@Override
		@RosettaAttribute("additionalBusinessDays")
		public BusinessCenters getAdditionalBusinessDays() {
			return additionalBusinessDays;
		}
		
		@Override
		public ObservationShiftCalculation build() {
			return this;
		}
		
		@Override
		public ObservationShiftCalculation.ObservationShiftCalculationBuilder toBuilder() {
			ObservationShiftCalculation.ObservationShiftCalculationBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ObservationShiftCalculation.ObservationShiftCalculationBuilder builder) {
			ofNullable(getOffsetDays()).ifPresent(builder::setOffsetDays);
			ofNullable(getCalculationBase()).ifPresent(builder::setCalculationBase);
			ofNullable(getAdditionalBusinessDays()).ifPresent(builder::setAdditionalBusinessDays);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationShiftCalculation _that = getType().cast(o);
		
			if (!Objects.equals(offsetDays, _that.getOffsetDays())) return false;
			if (!Objects.equals(calculationBase, _that.getCalculationBase())) return false;
			if (!Objects.equals(additionalBusinessDays, _that.getAdditionalBusinessDays())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (offsetDays != null ? offsetDays.hashCode() : 0);
			_result = 31 * _result + (calculationBase != null ? calculationBase.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (additionalBusinessDays != null ? additionalBusinessDays.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationShiftCalculation {" +
				"offsetDays=" + this.offsetDays + ", " +
				"calculationBase=" + this.calculationBase + ", " +
				"additionalBusinessDays=" + this.additionalBusinessDays +
			'}';
		}
	}

	/*********************** Builder Implementation of ObservationShiftCalculation  ***********************/
	class ObservationShiftCalculationBuilderImpl implements ObservationShiftCalculation.ObservationShiftCalculationBuilder {
	
		protected Integer offsetDays;
		protected ObservationPeriodDatesEnum calculationBase;
		protected BusinessCenters.BusinessCentersBuilder additionalBusinessDays;
	
		public ObservationShiftCalculationBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("offsetDays")
		public Integer getOffsetDays() {
			return offsetDays;
		}
		
		@Override
		@RosettaAttribute("calculationBase")
		public ObservationPeriodDatesEnum getCalculationBase() {
			return calculationBase;
		}
		
		@Override
		@RosettaAttribute("additionalBusinessDays")
		public BusinessCenters.BusinessCentersBuilder getAdditionalBusinessDays() {
			return additionalBusinessDays;
		}
		
		@Override
		public BusinessCenters.BusinessCentersBuilder getOrCreateAdditionalBusinessDays() {
			BusinessCenters.BusinessCentersBuilder result;
			if (additionalBusinessDays!=null) {
				result = additionalBusinessDays;
			}
			else {
				result = additionalBusinessDays = BusinessCenters.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("offsetDays")
		public ObservationShiftCalculation.ObservationShiftCalculationBuilder setOffsetDays(Integer offsetDays) {
			this.offsetDays = offsetDays==null?null:offsetDays;
			return this;
		}
		@Override
		@RosettaAttribute("calculationBase")
		public ObservationShiftCalculation.ObservationShiftCalculationBuilder setCalculationBase(ObservationPeriodDatesEnum calculationBase) {
			this.calculationBase = calculationBase==null?null:calculationBase;
			return this;
		}
		@Override
		@RosettaAttribute("additionalBusinessDays")
		public ObservationShiftCalculation.ObservationShiftCalculationBuilder setAdditionalBusinessDays(BusinessCenters additionalBusinessDays) {
			this.additionalBusinessDays = additionalBusinessDays==null?null:additionalBusinessDays.toBuilder();
			return this;
		}
		
		@Override
		public ObservationShiftCalculation build() {
			return new ObservationShiftCalculation.ObservationShiftCalculationImpl(this);
		}
		
		@Override
		public ObservationShiftCalculation.ObservationShiftCalculationBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationShiftCalculation.ObservationShiftCalculationBuilder prune() {
			if (additionalBusinessDays!=null && !additionalBusinessDays.prune().hasData()) additionalBusinessDays = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getOffsetDays()!=null) return true;
			if (getCalculationBase()!=null) return true;
			if (getAdditionalBusinessDays()!=null && getAdditionalBusinessDays().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ObservationShiftCalculation.ObservationShiftCalculationBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ObservationShiftCalculation.ObservationShiftCalculationBuilder o = (ObservationShiftCalculation.ObservationShiftCalculationBuilder) other;
			
			merger.mergeRosetta(getAdditionalBusinessDays(), o.getAdditionalBusinessDays(), this::setAdditionalBusinessDays);
			
			merger.mergeBasic(getOffsetDays(), o.getOffsetDays(), this::setOffsetDays);
			merger.mergeBasic(getCalculationBase(), o.getCalculationBase(), this::setCalculationBase);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ObservationShiftCalculation _that = getType().cast(o);
		
			if (!Objects.equals(offsetDays, _that.getOffsetDays())) return false;
			if (!Objects.equals(calculationBase, _that.getCalculationBase())) return false;
			if (!Objects.equals(additionalBusinessDays, _that.getAdditionalBusinessDays())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (offsetDays != null ? offsetDays.hashCode() : 0);
			_result = 31 * _result + (calculationBase != null ? calculationBase.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (additionalBusinessDays != null ? additionalBusinessDays.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ObservationShiftCalculationBuilder {" +
				"offsetDays=" + this.offsetDays + ", " +
				"calculationBase=" + this.calculationBase + ", " +
				"additionalBusinessDays=" + this.additionalBusinessDays +
			'}';
		}
	}
}
