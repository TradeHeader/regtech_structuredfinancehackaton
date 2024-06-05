package cdm.product.asset.floatingrate;

import cdm.product.asset.floatingrate.FloatingRateProcessingDetails;
import cdm.product.asset.floatingrate.FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder;
import cdm.product.asset.floatingrate.FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilderImpl;
import cdm.product.asset.floatingrate.FloatingRateProcessingDetails.FloatingRateProcessingDetailsImpl;
import cdm.product.asset.floatingrate.FloatingRateProcessingParameters;
import cdm.product.asset.floatingrate.meta.FloatingRateProcessingDetailsMeta;
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
 * Type for reporting the details of the rate treatment.  This could potentially be replaced by the existing FloatingRateDefinition type , but this is slightly more detailed.
 * @version ${project.version}
 */
@RosettaDataType(value="FloatingRateProcessingDetails", builder=FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilderImpl.class, version="${project.version}")
public interface FloatingRateProcessingDetails extends RosettaModelObject {

	FloatingRateProcessingDetailsMeta metaData = new FloatingRateProcessingDetailsMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The raw or untreated rate, prior to any of the rate treatments.
	 */
	BigDecimal getRawRate();
	FloatingRateProcessingParameters getProcessingParameters();
	/**
	 * The value of the rate after processing.
	 */
	BigDecimal getProcessedRate();
	/**
	 * The value of the processed rate without the spread applied, for subsequent compounding, etc.
	 */
	BigDecimal getSpreadExclusiveRate();

	/*********************** Build Methods  ***********************/
	FloatingRateProcessingDetails build();
	
	FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder toBuilder();
	
	static FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder builder() {
		return new FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends FloatingRateProcessingDetails> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends FloatingRateProcessingDetails> getType() {
		return FloatingRateProcessingDetails.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processor.processBasic(path.newSubPath("rawRate"), BigDecimal.class, getRawRate(), this);
		processRosetta(path.newSubPath("processingParameters"), processor, FloatingRateProcessingParameters.class, getProcessingParameters());
		processor.processBasic(path.newSubPath("processedRate"), BigDecimal.class, getProcessedRate(), this);
		processor.processBasic(path.newSubPath("spreadExclusiveRate"), BigDecimal.class, getSpreadExclusiveRate(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface FloatingRateProcessingDetailsBuilder extends FloatingRateProcessingDetails, RosettaModelObjectBuilder {
		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder getOrCreateProcessingParameters();
		FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder getProcessingParameters();
		FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder setRawRate(BigDecimal rawRate);
		FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder setProcessingParameters(FloatingRateProcessingParameters processingParameters);
		FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder setProcessedRate(BigDecimal processedRate);
		FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder setSpreadExclusiveRate(BigDecimal spreadExclusiveRate);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processor.processBasic(path.newSubPath("rawRate"), BigDecimal.class, getRawRate(), this);
			processRosetta(path.newSubPath("processingParameters"), processor, FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder.class, getProcessingParameters());
			processor.processBasic(path.newSubPath("processedRate"), BigDecimal.class, getProcessedRate(), this);
			processor.processBasic(path.newSubPath("spreadExclusiveRate"), BigDecimal.class, getSpreadExclusiveRate(), this);
		}
		

		FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder prune();
	}

	/*********************** Immutable Implementation of FloatingRateProcessingDetails  ***********************/
	class FloatingRateProcessingDetailsImpl implements FloatingRateProcessingDetails {
		private final BigDecimal rawRate;
		private final FloatingRateProcessingParameters processingParameters;
		private final BigDecimal processedRate;
		private final BigDecimal spreadExclusiveRate;
		
		protected FloatingRateProcessingDetailsImpl(FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder builder) {
			this.rawRate = builder.getRawRate();
			this.processingParameters = ofNullable(builder.getProcessingParameters()).map(f->f.build()).orElse(null);
			this.processedRate = builder.getProcessedRate();
			this.spreadExclusiveRate = builder.getSpreadExclusiveRate();
		}
		
		@Override
		@RosettaAttribute("rawRate")
		public BigDecimal getRawRate() {
			return rawRate;
		}
		
		@Override
		@RosettaAttribute("processingParameters")
		public FloatingRateProcessingParameters getProcessingParameters() {
			return processingParameters;
		}
		
		@Override
		@RosettaAttribute("processedRate")
		public BigDecimal getProcessedRate() {
			return processedRate;
		}
		
		@Override
		@RosettaAttribute("spreadExclusiveRate")
		public BigDecimal getSpreadExclusiveRate() {
			return spreadExclusiveRate;
		}
		
		@Override
		public FloatingRateProcessingDetails build() {
			return this;
		}
		
		@Override
		public FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder toBuilder() {
			FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder builder) {
			ofNullable(getRawRate()).ifPresent(builder::setRawRate);
			ofNullable(getProcessingParameters()).ifPresent(builder::setProcessingParameters);
			ofNullable(getProcessedRate()).ifPresent(builder::setProcessedRate);
			ofNullable(getSpreadExclusiveRate()).ifPresent(builder::setSpreadExclusiveRate);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateProcessingDetails _that = getType().cast(o);
		
			if (!Objects.equals(rawRate, _that.getRawRate())) return false;
			if (!Objects.equals(processingParameters, _that.getProcessingParameters())) return false;
			if (!Objects.equals(processedRate, _that.getProcessedRate())) return false;
			if (!Objects.equals(spreadExclusiveRate, _that.getSpreadExclusiveRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (rawRate != null ? rawRate.hashCode() : 0);
			_result = 31 * _result + (processingParameters != null ? processingParameters.hashCode() : 0);
			_result = 31 * _result + (processedRate != null ? processedRate.hashCode() : 0);
			_result = 31 * _result + (spreadExclusiveRate != null ? spreadExclusiveRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateProcessingDetails {" +
				"rawRate=" + this.rawRate + ", " +
				"processingParameters=" + this.processingParameters + ", " +
				"processedRate=" + this.processedRate + ", " +
				"spreadExclusiveRate=" + this.spreadExclusiveRate +
			'}';
		}
	}

	/*********************** Builder Implementation of FloatingRateProcessingDetails  ***********************/
	class FloatingRateProcessingDetailsBuilderImpl implements FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder {
	
		protected BigDecimal rawRate;
		protected FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder processingParameters;
		protected BigDecimal processedRate;
		protected BigDecimal spreadExclusiveRate;
	
		public FloatingRateProcessingDetailsBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("rawRate")
		public BigDecimal getRawRate() {
			return rawRate;
		}
		
		@Override
		@RosettaAttribute("processingParameters")
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder getProcessingParameters() {
			return processingParameters;
		}
		
		@Override
		public FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder getOrCreateProcessingParameters() {
			FloatingRateProcessingParameters.FloatingRateProcessingParametersBuilder result;
			if (processingParameters!=null) {
				result = processingParameters;
			}
			else {
				result = processingParameters = FloatingRateProcessingParameters.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("processedRate")
		public BigDecimal getProcessedRate() {
			return processedRate;
		}
		
		@Override
		@RosettaAttribute("spreadExclusiveRate")
		public BigDecimal getSpreadExclusiveRate() {
			return spreadExclusiveRate;
		}
		
	
		@Override
		@RosettaAttribute("rawRate")
		public FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder setRawRate(BigDecimal rawRate) {
			this.rawRate = rawRate==null?null:rawRate;
			return this;
		}
		@Override
		@RosettaAttribute("processingParameters")
		public FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder setProcessingParameters(FloatingRateProcessingParameters processingParameters) {
			this.processingParameters = processingParameters==null?null:processingParameters.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("processedRate")
		public FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder setProcessedRate(BigDecimal processedRate) {
			this.processedRate = processedRate==null?null:processedRate;
			return this;
		}
		@Override
		@RosettaAttribute("spreadExclusiveRate")
		public FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder setSpreadExclusiveRate(BigDecimal spreadExclusiveRate) {
			this.spreadExclusiveRate = spreadExclusiveRate==null?null:spreadExclusiveRate;
			return this;
		}
		
		@Override
		public FloatingRateProcessingDetails build() {
			return new FloatingRateProcessingDetails.FloatingRateProcessingDetailsImpl(this);
		}
		
		@Override
		public FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder prune() {
			if (processingParameters!=null && !processingParameters.prune().hasData()) processingParameters = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getRawRate()!=null) return true;
			if (getProcessingParameters()!=null && getProcessingParameters().hasData()) return true;
			if (getProcessedRate()!=null) return true;
			if (getSpreadExclusiveRate()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder o = (FloatingRateProcessingDetails.FloatingRateProcessingDetailsBuilder) other;
			
			merger.mergeRosetta(getProcessingParameters(), o.getProcessingParameters(), this::setProcessingParameters);
			
			merger.mergeBasic(getRawRate(), o.getRawRate(), this::setRawRate);
			merger.mergeBasic(getProcessedRate(), o.getProcessedRate(), this::setProcessedRate);
			merger.mergeBasic(getSpreadExclusiveRate(), o.getSpreadExclusiveRate(), this::setSpreadExclusiveRate);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			FloatingRateProcessingDetails _that = getType().cast(o);
		
			if (!Objects.equals(rawRate, _that.getRawRate())) return false;
			if (!Objects.equals(processingParameters, _that.getProcessingParameters())) return false;
			if (!Objects.equals(processedRate, _that.getProcessedRate())) return false;
			if (!Objects.equals(spreadExclusiveRate, _that.getSpreadExclusiveRate())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (rawRate != null ? rawRate.hashCode() : 0);
			_result = 31 * _result + (processingParameters != null ? processingParameters.hashCode() : 0);
			_result = 31 * _result + (processedRate != null ? processedRate.hashCode() : 0);
			_result = 31 * _result + (spreadExclusiveRate != null ? spreadExclusiveRate.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "FloatingRateProcessingDetailsBuilder {" +
				"rawRate=" + this.rawRate + ", " +
				"processingParameters=" + this.processingParameters + ", " +
				"processedRate=" + this.processedRate + ", " +
				"spreadExclusiveRate=" + this.spreadExclusiveRate +
			'}';
		}
	}
}
