package cdm.product.template;

import cdm.product.common.schedule.ObservationTerms;
import cdm.product.template.AveragingCalculation;
import cdm.product.template.AveragingStrikeFeature;
import cdm.product.template.AveragingStrikeFeature.AveragingStrikeFeatureBuilder;
import cdm.product.template.AveragingStrikeFeature.AveragingStrikeFeatureBuilderImpl;
import cdm.product.template.AveragingStrikeFeature.AveragingStrikeFeatureImpl;
import cdm.product.template.meta.AveragingStrikeFeatureMeta;
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
 * Defines the terms required to calculate the average observations associated with an averaging strike.
 * @version ${project.version}
 */
@RosettaDataType(value="AveragingStrikeFeature", builder=AveragingStrikeFeature.AveragingStrikeFeatureBuilderImpl.class, version="${project.version}")
public interface AveragingStrikeFeature extends RosettaModelObject {

	AveragingStrikeFeatureMeta metaData = new AveragingStrikeFeatureMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Defines parameters for use in cases when a valuation or other term is based on an average of market observations.
	 */
	AveragingCalculation getAveragingCalculation();
	/**
	 * Class containing terms that are associated with observing a price/benchmark/index across either single or multple observations. 
	 */
	ObservationTerms getObservationTerms();

	/*********************** Build Methods  ***********************/
	AveragingStrikeFeature build();
	
	AveragingStrikeFeature.AveragingStrikeFeatureBuilder toBuilder();
	
	static AveragingStrikeFeature.AveragingStrikeFeatureBuilder builder() {
		return new AveragingStrikeFeature.AveragingStrikeFeatureBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends AveragingStrikeFeature> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends AveragingStrikeFeature> getType() {
		return AveragingStrikeFeature.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("averagingCalculation"), processor, AveragingCalculation.class, getAveragingCalculation());
		processRosetta(path.newSubPath("observationTerms"), processor, ObservationTerms.class, getObservationTerms());
	}
	

	/*********************** Builder Interface  ***********************/
	interface AveragingStrikeFeatureBuilder extends AveragingStrikeFeature, RosettaModelObjectBuilder {
		AveragingCalculation.AveragingCalculationBuilder getOrCreateAveragingCalculation();
		AveragingCalculation.AveragingCalculationBuilder getAveragingCalculation();
		ObservationTerms.ObservationTermsBuilder getOrCreateObservationTerms();
		ObservationTerms.ObservationTermsBuilder getObservationTerms();
		AveragingStrikeFeature.AveragingStrikeFeatureBuilder setAveragingCalculation(AveragingCalculation averagingCalculation);
		AveragingStrikeFeature.AveragingStrikeFeatureBuilder setObservationTerms(ObservationTerms observationTerms);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("averagingCalculation"), processor, AveragingCalculation.AveragingCalculationBuilder.class, getAveragingCalculation());
			processRosetta(path.newSubPath("observationTerms"), processor, ObservationTerms.ObservationTermsBuilder.class, getObservationTerms());
		}
		

		AveragingStrikeFeature.AveragingStrikeFeatureBuilder prune();
	}

	/*********************** Immutable Implementation of AveragingStrikeFeature  ***********************/
	class AveragingStrikeFeatureImpl implements AveragingStrikeFeature {
		private final AveragingCalculation averagingCalculation;
		private final ObservationTerms observationTerms;
		
		protected AveragingStrikeFeatureImpl(AveragingStrikeFeature.AveragingStrikeFeatureBuilder builder) {
			this.averagingCalculation = ofNullable(builder.getAveragingCalculation()).map(f->f.build()).orElse(null);
			this.observationTerms = ofNullable(builder.getObservationTerms()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("averagingCalculation")
		public AveragingCalculation getAveragingCalculation() {
			return averagingCalculation;
		}
		
		@Override
		@RosettaAttribute("observationTerms")
		public ObservationTerms getObservationTerms() {
			return observationTerms;
		}
		
		@Override
		public AveragingStrikeFeature build() {
			return this;
		}
		
		@Override
		public AveragingStrikeFeature.AveragingStrikeFeatureBuilder toBuilder() {
			AveragingStrikeFeature.AveragingStrikeFeatureBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(AveragingStrikeFeature.AveragingStrikeFeatureBuilder builder) {
			ofNullable(getAveragingCalculation()).ifPresent(builder::setAveragingCalculation);
			ofNullable(getObservationTerms()).ifPresent(builder::setObservationTerms);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AveragingStrikeFeature _that = getType().cast(o);
		
			if (!Objects.equals(averagingCalculation, _that.getAveragingCalculation())) return false;
			if (!Objects.equals(observationTerms, _that.getObservationTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (averagingCalculation != null ? averagingCalculation.hashCode() : 0);
			_result = 31 * _result + (observationTerms != null ? observationTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AveragingStrikeFeature {" +
				"averagingCalculation=" + this.averagingCalculation + ", " +
				"observationTerms=" + this.observationTerms +
			'}';
		}
	}

	/*********************** Builder Implementation of AveragingStrikeFeature  ***********************/
	class AveragingStrikeFeatureBuilderImpl implements AveragingStrikeFeature.AveragingStrikeFeatureBuilder {
	
		protected AveragingCalculation.AveragingCalculationBuilder averagingCalculation;
		protected ObservationTerms.ObservationTermsBuilder observationTerms;
	
		public AveragingStrikeFeatureBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("averagingCalculation")
		public AveragingCalculation.AveragingCalculationBuilder getAveragingCalculation() {
			return averagingCalculation;
		}
		
		@Override
		public AveragingCalculation.AveragingCalculationBuilder getOrCreateAveragingCalculation() {
			AveragingCalculation.AveragingCalculationBuilder result;
			if (averagingCalculation!=null) {
				result = averagingCalculation;
			}
			else {
				result = averagingCalculation = AveragingCalculation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("observationTerms")
		public ObservationTerms.ObservationTermsBuilder getObservationTerms() {
			return observationTerms;
		}
		
		@Override
		public ObservationTerms.ObservationTermsBuilder getOrCreateObservationTerms() {
			ObservationTerms.ObservationTermsBuilder result;
			if (observationTerms!=null) {
				result = observationTerms;
			}
			else {
				result = observationTerms = ObservationTerms.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("averagingCalculation")
		public AveragingStrikeFeature.AveragingStrikeFeatureBuilder setAveragingCalculation(AveragingCalculation averagingCalculation) {
			this.averagingCalculation = averagingCalculation==null?null:averagingCalculation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("observationTerms")
		public AveragingStrikeFeature.AveragingStrikeFeatureBuilder setObservationTerms(ObservationTerms observationTerms) {
			this.observationTerms = observationTerms==null?null:observationTerms.toBuilder();
			return this;
		}
		
		@Override
		public AveragingStrikeFeature build() {
			return new AveragingStrikeFeature.AveragingStrikeFeatureImpl(this);
		}
		
		@Override
		public AveragingStrikeFeature.AveragingStrikeFeatureBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AveragingStrikeFeature.AveragingStrikeFeatureBuilder prune() {
			if (averagingCalculation!=null && !averagingCalculation.prune().hasData()) averagingCalculation = null;
			if (observationTerms!=null && !observationTerms.prune().hasData()) observationTerms = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getAveragingCalculation()!=null && getAveragingCalculation().hasData()) return true;
			if (getObservationTerms()!=null && getObservationTerms().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public AveragingStrikeFeature.AveragingStrikeFeatureBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			AveragingStrikeFeature.AveragingStrikeFeatureBuilder o = (AveragingStrikeFeature.AveragingStrikeFeatureBuilder) other;
			
			merger.mergeRosetta(getAveragingCalculation(), o.getAveragingCalculation(), this::setAveragingCalculation);
			merger.mergeRosetta(getObservationTerms(), o.getObservationTerms(), this::setObservationTerms);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			AveragingStrikeFeature _that = getType().cast(o);
		
			if (!Objects.equals(averagingCalculation, _that.getAveragingCalculation())) return false;
			if (!Objects.equals(observationTerms, _that.getObservationTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (averagingCalculation != null ? averagingCalculation.hashCode() : 0);
			_result = 31 * _result + (observationTerms != null ? observationTerms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "AveragingStrikeFeatureBuilder {" +
				"averagingCalculation=" + this.averagingCalculation + ", " +
				"observationTerms=" + this.observationTerms +
			'}';
		}
	}
}
