package cdm.observable.asset.calculatedrate;

import cdm.base.staticdata.asset.rates.FloatingRateIndexEnum;
import cdm.observable.asset.calculatedrate.FallbackRateParameters;
import cdm.observable.asset.calculatedrate.FallbackRateParameters.FallbackRateParametersBuilder;
import cdm.observable.asset.calculatedrate.FallbackRateParameters.FallbackRateParametersBuilderImpl;
import cdm.observable.asset.calculatedrate.FallbackRateParameters.FallbackRateParametersImpl;
import cdm.observable.asset.calculatedrate.FloatingRateCalculationParameters;
import cdm.observable.asset.calculatedrate.meta.FallbackRateParametersMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.lib.records.Date;
import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * Defines the structure needed to represent fallback rate parameters. This type is used to represent modular computed rates in interestRatePayouts.
 * @version ${project.version}
 */
@RosettaDataType(value="FallbackRateParameters", builder=FallbackRateParameters.FallbackRateParametersBuilderImpl.class, version="${project.version}")
public interface FallbackRateParameters extends RosettaModelObject {

	FallbackRateParametersMeta metaData = new FallbackRateParametersMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The floating rate index that is used as the basis of the fallback rate.
	 */
	FloatingRateIndexEnum getFloatingRateIndex();
	/**
	 * The date the fallback rate takes effect.
	 */
	Date getEffectiveDate();
	/**
	 * Support for modular calculated rates, such such as lockout compound calculations.
	 */
	FloatingRateCalculationParameters getCalculationParameters();
	/**
	 * The economic spread applied to the underlying fallback rate to replicate the original risky rate.
	 */
	BigDecimal getSpreadAdjustment();

	/*********************** Build Methods  ***********************/
	FallbackRateParameters build();
	
	FallbackRateParameters.FallbackRateParametersBuilder toBuilder();
	
	static FallbackRateParameters.FallbackRateParametersBuilder builder() {
		return new FallbackRateParameters.FallbackRateParametersBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FallbackRateParameters> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FallbackRateParameters> getType() {
		return FallbackRateParameters.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("floatingRateIndex"), FloatingRateIndexEnum.class, getFloatingRateIndex(), this);
		processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
		processRosetta(path.newSubPath("calculationParameters"), processor, FloatingRateCalculationParameters.class, getCalculationParameters());
		processor.processBasic(path.newSubPath("spreadAdjustment"), BigDecimal.class, getSpreadAdjustment(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface FallbackRateParametersBuilder extends FallbackRateParameters, RosettaModelObjectBuilder {
		FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder getOrCreateCalculationParameters();
		FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder getCalculationParameters();
		FallbackRateParameters.FallbackRateParametersBuilder setFloatingRateIndex(FloatingRateIndexEnum floatingRateIndex);
		FallbackRateParameters.FallbackRateParametersBuilder setEffectiveDate(Date effectiveDate);
		FallbackRateParameters.FallbackRateParametersBuilder setCalculationParameters(FloatingRateCalculationParameters calculationParameters);
		FallbackRateParameters.FallbackRateParametersBuilder setSpreadAdjustment(BigDecimal spreadAdjustment);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("floatingRateIndex"), FloatingRateIndexEnum.class, getFloatingRateIndex(), this);
			processor.processBasic(path.newSubPath("effectiveDate"), Date.class, getEffectiveDate(), this);
			processRosetta(path.newSubPath("calculationParameters"), processor, FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder.class, getCalculationParameters());
			processor.processBasic(path.newSubPath("spreadAdjustment"), BigDecimal.class, getSpreadAdjustment(), this);
		}
		

		FallbackRateParameters.FallbackRateParametersBuilder prune();
	}

	/*********************** Immutable Implementation of FallbackRateParameters  ***********************/
	class FallbackRateParametersImpl implements FallbackRateParameters {
		private final FloatingRateIndexEnum floatingRateIndex;
		private final Date effectiveDate;
		private final FloatingRateCalculationParameters calculationParameters;
		private final BigDecimal spreadAdjustment;
		
		protected FallbackRateParametersImpl(FallbackRateParameters.FallbackRateParametersBuilder builder) {
			this.floatingRateIndex = builder.getFloatingRateIndex();
			this.effectiveDate = builder.getEffectiveDate();
			this.calculationParameters = ofNullable(builder.getCalculationParameters()).map(f->f.build()).orElse(null);
			this.spreadAdjustment = builder.getSpreadAdjustment();
		}
		
		@Override
		@RosettaAttribute("floatingRateIndex")
		public FloatingRateIndexEnum getFloatingRateIndex() {
			return floatingRateIndex;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		@RosettaAttribute("calculationParameters")
		public FloatingRateCalculationParameters getCalculationParameters() {
			return calculationParameters;
		}
		
		@Override
		@RosettaAttribute("spreadAdjustment")
		public BigDecimal getSpreadAdjustment() {
			return spreadAdjustment;
		}
		
		@Override
		public FallbackRateParameters build() {
			return this;
		}
		
		@Override
		public FallbackRateParameters.FallbackRateParametersBuilder toBuilder() {
			FallbackRateParameters.FallbackRateParametersBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FallbackRateParameters.FallbackRateParametersBuilder builder) {
			ofNullable(getFloatingRateIndex()).ifPresent(builder::setFloatingRateIndex);
			ofNullable(getEffectiveDate()).ifPresent(builder::setEffectiveDate);
			ofNullable(getCalculationParameters()).ifPresent(builder::setCalculationParameters);
			ofNullable(getSpreadAdjustment()).ifPresent(builder::setSpreadAdjustment);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FallbackRateParameters _that = getType().cast(o);
		
			if (!Objects.equals(floatingRateIndex, _that.getFloatingRateIndex())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!Objects.equals(calculationParameters, _that.getCalculationParameters())) return false;
			if (!Objects.equals(spreadAdjustment, _that.getSpreadAdjustment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (floatingRateIndex != null ? floatingRateIndex.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (calculationParameters != null ? calculationParameters.hashCode() : 0);
			_result = 31 * _result + (spreadAdjustment != null ? spreadAdjustment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FallbackRateParameters {" +
				"floatingRateIndex=" + this.floatingRateIndex + ", " +
				"effectiveDate=" + this.effectiveDate + ", " +
				"calculationParameters=" + this.calculationParameters + ", " +
				"spreadAdjustment=" + this.spreadAdjustment +
			'}';
		}
	}

	/*********************** Builder Implementation of FallbackRateParameters  ***********************/
	class FallbackRateParametersBuilderImpl implements FallbackRateParameters.FallbackRateParametersBuilder {
	
		protected FloatingRateIndexEnum floatingRateIndex;
		protected Date effectiveDate;
		protected FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder calculationParameters;
		protected BigDecimal spreadAdjustment;
	
		public FallbackRateParametersBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("floatingRateIndex")
		public FloatingRateIndexEnum getFloatingRateIndex() {
			return floatingRateIndex;
		}
		
		@Override
		@RosettaAttribute("effectiveDate")
		public Date getEffectiveDate() {
			return effectiveDate;
		}
		
		@Override
		@RosettaAttribute("calculationParameters")
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder getCalculationParameters() {
			return calculationParameters;
		}
		
		@Override
		public FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder getOrCreateCalculationParameters() {
			FloatingRateCalculationParameters.FloatingRateCalculationParametersBuilder result;
			if (calculationParameters!=null) {
				result = calculationParameters;
			}
			else {
				result = calculationParameters = FloatingRateCalculationParameters.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("spreadAdjustment")
		public BigDecimal getSpreadAdjustment() {
			return spreadAdjustment;
		}
		
	
		@Override
		@RosettaAttribute("floatingRateIndex")
		public FallbackRateParameters.FallbackRateParametersBuilder setFloatingRateIndex(FloatingRateIndexEnum floatingRateIndex) {
			this.floatingRateIndex = floatingRateIndex==null?null:floatingRateIndex;
			return this;
		}
		@Override
		@RosettaAttribute("effectiveDate")
		public FallbackRateParameters.FallbackRateParametersBuilder setEffectiveDate(Date effectiveDate) {
			this.effectiveDate = effectiveDate==null?null:effectiveDate;
			return this;
		}
		@Override
		@RosettaAttribute("calculationParameters")
		public FallbackRateParameters.FallbackRateParametersBuilder setCalculationParameters(FloatingRateCalculationParameters calculationParameters) {
			this.calculationParameters = calculationParameters==null?null:calculationParameters.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("spreadAdjustment")
		public FallbackRateParameters.FallbackRateParametersBuilder setSpreadAdjustment(BigDecimal spreadAdjustment) {
			this.spreadAdjustment = spreadAdjustment==null?null:spreadAdjustment;
			return this;
		}
		
		@Override
		public FallbackRateParameters build() {
			return new FallbackRateParameters.FallbackRateParametersImpl(this);
		}
		
		@Override
		public FallbackRateParameters.FallbackRateParametersBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FallbackRateParameters.FallbackRateParametersBuilder prune() {
			if (calculationParameters!=null && !calculationParameters.prune().hasData()) calculationParameters = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getFloatingRateIndex()!=null) return true;
			if (getEffectiveDate()!=null) return true;
			if (getCalculationParameters()!=null && getCalculationParameters().hasData()) return true;
			if (getSpreadAdjustment()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FallbackRateParameters.FallbackRateParametersBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FallbackRateParameters.FallbackRateParametersBuilder o = (FallbackRateParameters.FallbackRateParametersBuilder) other;
			
			merger.mergeRosetta(getCalculationParameters(), o.getCalculationParameters(), this::setCalculationParameters);
			
			merger.mergeBasic(getFloatingRateIndex(), o.getFloatingRateIndex(), this::setFloatingRateIndex);
			merger.mergeBasic(getEffectiveDate(), o.getEffectiveDate(), this::setEffectiveDate);
			merger.mergeBasic(getSpreadAdjustment(), o.getSpreadAdjustment(), this::setSpreadAdjustment);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FallbackRateParameters _that = getType().cast(o);
		
			if (!Objects.equals(floatingRateIndex, _that.getFloatingRateIndex())) return false;
			if (!Objects.equals(effectiveDate, _that.getEffectiveDate())) return false;
			if (!Objects.equals(calculationParameters, _that.getCalculationParameters())) return false;
			if (!Objects.equals(spreadAdjustment, _that.getSpreadAdjustment())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (floatingRateIndex != null ? floatingRateIndex.getClass().getName().hashCode() : 0);
			_result = 31 * _result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
			_result = 31 * _result + (calculationParameters != null ? calculationParameters.hashCode() : 0);
			_result = 31 * _result + (spreadAdjustment != null ? spreadAdjustment.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FallbackRateParametersBuilder {" +
				"floatingRateIndex=" + this.floatingRateIndex + ", " +
				"effectiveDate=" + this.effectiveDate + ", " +
				"calculationParameters=" + this.calculationParameters + ", " +
				"spreadAdjustment=" + this.spreadAdjustment +
			'}';
		}
	}
}
