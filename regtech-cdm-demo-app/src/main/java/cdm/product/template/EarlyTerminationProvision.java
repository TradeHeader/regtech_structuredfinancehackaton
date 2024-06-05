package cdm.product.template;

import cdm.base.datetime.Period;
import cdm.product.template.EarlyTerminationProvision;
import cdm.product.template.EarlyTerminationProvision.EarlyTerminationProvisionBuilder;
import cdm.product.template.EarlyTerminationProvision.EarlyTerminationProvisionBuilderImpl;
import cdm.product.template.EarlyTerminationProvision.EarlyTerminationProvisionImpl;
import cdm.product.template.ExercisePeriod;
import cdm.product.template.MandatoryEarlyTermination;
import cdm.product.template.OptionalEarlyTermination;
import cdm.product.template.meta.EarlyTerminationProvisionMeta;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.MetaFields;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * A data defining:  an early termination provision for a swap. This early termination is at fair value, i.e. on termination the fair value of the product must be settled between the parties.
 * @version ${project.version}
 */
@RosettaDataType(value="EarlyTerminationProvision", builder=EarlyTerminationProvision.EarlyTerminationProvisionBuilderImpl.class, version="${project.version}")
public interface EarlyTerminationProvision extends RosettaModelObject, GlobalKey {

	EarlyTerminationProvisionMeta metaData = new EarlyTerminationProvisionMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A mandatory early termination provision to terminate the swap at fair value.
	 */
	MandatoryEarlyTermination getMandatoryEarlyTermination();
	/**
	 * Period after trade date of the mandatory early termination date.
	 */
	Period getMandatoryEarlyTerminationDateTenor();
	/**
	 * An option for either or both parties to terminate the swap at fair value.
	 */
	OptionalEarlyTermination getOptionalEarlyTermination();
	/**
	 * Definition of the first early termination date and the frequency of the termination dates subsequent to that. American exercise is defined by having a frequency of one day.
	 */
	ExercisePeriod getOptionalEarlyTerminationParameters();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	EarlyTerminationProvision build();
	
	EarlyTerminationProvision.EarlyTerminationProvisionBuilder toBuilder();
	
	static EarlyTerminationProvision.EarlyTerminationProvisionBuilder builder() {
		return new EarlyTerminationProvision.EarlyTerminationProvisionBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends EarlyTerminationProvision> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends EarlyTerminationProvision> getType() {
		return EarlyTerminationProvision.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("mandatoryEarlyTermination"), processor, MandatoryEarlyTermination.class, getMandatoryEarlyTermination());
		processRosetta(path.newSubPath("mandatoryEarlyTerminationDateTenor"), processor, Period.class, getMandatoryEarlyTerminationDateTenor());
		processRosetta(path.newSubPath("optionalEarlyTermination"), processor, OptionalEarlyTermination.class, getOptionalEarlyTermination());
		processRosetta(path.newSubPath("optionalEarlyTerminationParameters"), processor, ExercisePeriod.class, getOptionalEarlyTerminationParameters());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface EarlyTerminationProvisionBuilder extends EarlyTerminationProvision, RosettaModelObjectBuilder {
		MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder getOrCreateMandatoryEarlyTermination();
		MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder getMandatoryEarlyTermination();
		Period.PeriodBuilder getOrCreateMandatoryEarlyTerminationDateTenor();
		Period.PeriodBuilder getMandatoryEarlyTerminationDateTenor();
		OptionalEarlyTermination.OptionalEarlyTerminationBuilder getOrCreateOptionalEarlyTermination();
		OptionalEarlyTermination.OptionalEarlyTerminationBuilder getOptionalEarlyTermination();
		ExercisePeriod.ExercisePeriodBuilder getOrCreateOptionalEarlyTerminationParameters();
		ExercisePeriod.ExercisePeriodBuilder getOptionalEarlyTerminationParameters();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		EarlyTerminationProvision.EarlyTerminationProvisionBuilder setMandatoryEarlyTermination(MandatoryEarlyTermination mandatoryEarlyTermination);
		EarlyTerminationProvision.EarlyTerminationProvisionBuilder setMandatoryEarlyTerminationDateTenor(Period mandatoryEarlyTerminationDateTenor);
		EarlyTerminationProvision.EarlyTerminationProvisionBuilder setOptionalEarlyTermination(OptionalEarlyTermination optionalEarlyTermination);
		EarlyTerminationProvision.EarlyTerminationProvisionBuilder setOptionalEarlyTerminationParameters(ExercisePeriod optionalEarlyTerminationParameters);
		EarlyTerminationProvision.EarlyTerminationProvisionBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("mandatoryEarlyTermination"), processor, MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder.class, getMandatoryEarlyTermination());
			processRosetta(path.newSubPath("mandatoryEarlyTerminationDateTenor"), processor, Period.PeriodBuilder.class, getMandatoryEarlyTerminationDateTenor());
			processRosetta(path.newSubPath("optionalEarlyTermination"), processor, OptionalEarlyTermination.OptionalEarlyTerminationBuilder.class, getOptionalEarlyTermination());
			processRosetta(path.newSubPath("optionalEarlyTerminationParameters"), processor, ExercisePeriod.ExercisePeriodBuilder.class, getOptionalEarlyTerminationParameters());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		EarlyTerminationProvision.EarlyTerminationProvisionBuilder prune();
	}

	/*********************** Immutable Implementation of EarlyTerminationProvision  ***********************/
	class EarlyTerminationProvisionImpl implements EarlyTerminationProvision {
		private final MandatoryEarlyTermination mandatoryEarlyTermination;
		private final Period mandatoryEarlyTerminationDateTenor;
		private final OptionalEarlyTermination optionalEarlyTermination;
		private final ExercisePeriod optionalEarlyTerminationParameters;
		private final MetaFields meta;
		
		protected EarlyTerminationProvisionImpl(EarlyTerminationProvision.EarlyTerminationProvisionBuilder builder) {
			this.mandatoryEarlyTermination = ofNullable(builder.getMandatoryEarlyTermination()).map(f->f.build()).orElse(null);
			this.mandatoryEarlyTerminationDateTenor = ofNullable(builder.getMandatoryEarlyTerminationDateTenor()).map(f->f.build()).orElse(null);
			this.optionalEarlyTermination = ofNullable(builder.getOptionalEarlyTermination()).map(f->f.build()).orElse(null);
			this.optionalEarlyTerminationParameters = ofNullable(builder.getOptionalEarlyTerminationParameters()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("mandatoryEarlyTermination")
		public MandatoryEarlyTermination getMandatoryEarlyTermination() {
			return mandatoryEarlyTermination;
		}
		
		@Override
		@RosettaAttribute("mandatoryEarlyTerminationDateTenor")
		public Period getMandatoryEarlyTerminationDateTenor() {
			return mandatoryEarlyTerminationDateTenor;
		}
		
		@Override
		@RosettaAttribute("optionalEarlyTermination")
		public OptionalEarlyTermination getOptionalEarlyTermination() {
			return optionalEarlyTermination;
		}
		
		@Override
		@RosettaAttribute("optionalEarlyTerminationParameters")
		public ExercisePeriod getOptionalEarlyTerminationParameters() {
			return optionalEarlyTerminationParameters;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public EarlyTerminationProvision build() {
			return this;
		}
		
		@Override
		public EarlyTerminationProvision.EarlyTerminationProvisionBuilder toBuilder() {
			EarlyTerminationProvision.EarlyTerminationProvisionBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(EarlyTerminationProvision.EarlyTerminationProvisionBuilder builder) {
			ofNullable(getMandatoryEarlyTermination()).ifPresent(builder::setMandatoryEarlyTermination);
			ofNullable(getMandatoryEarlyTerminationDateTenor()).ifPresent(builder::setMandatoryEarlyTerminationDateTenor);
			ofNullable(getOptionalEarlyTermination()).ifPresent(builder::setOptionalEarlyTermination);
			ofNullable(getOptionalEarlyTerminationParameters()).ifPresent(builder::setOptionalEarlyTerminationParameters);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EarlyTerminationProvision _that = getType().cast(o);
		
			if (!Objects.equals(mandatoryEarlyTermination, _that.getMandatoryEarlyTermination())) return false;
			if (!Objects.equals(mandatoryEarlyTerminationDateTenor, _that.getMandatoryEarlyTerminationDateTenor())) return false;
			if (!Objects.equals(optionalEarlyTermination, _that.getOptionalEarlyTermination())) return false;
			if (!Objects.equals(optionalEarlyTerminationParameters, _that.getOptionalEarlyTerminationParameters())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (mandatoryEarlyTermination != null ? mandatoryEarlyTermination.hashCode() : 0);
			_result = 31 * _result + (mandatoryEarlyTerminationDateTenor != null ? mandatoryEarlyTerminationDateTenor.hashCode() : 0);
			_result = 31 * _result + (optionalEarlyTermination != null ? optionalEarlyTermination.hashCode() : 0);
			_result = 31 * _result + (optionalEarlyTerminationParameters != null ? optionalEarlyTerminationParameters.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EarlyTerminationProvision {" +
				"mandatoryEarlyTermination=" + this.mandatoryEarlyTermination + ", " +
				"mandatoryEarlyTerminationDateTenor=" + this.mandatoryEarlyTerminationDateTenor + ", " +
				"optionalEarlyTermination=" + this.optionalEarlyTermination + ", " +
				"optionalEarlyTerminationParameters=" + this.optionalEarlyTerminationParameters + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of EarlyTerminationProvision  ***********************/
	class EarlyTerminationProvisionBuilderImpl implements EarlyTerminationProvision.EarlyTerminationProvisionBuilder, GlobalKeyBuilder {
	
		protected MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder mandatoryEarlyTermination;
		protected Period.PeriodBuilder mandatoryEarlyTerminationDateTenor;
		protected OptionalEarlyTermination.OptionalEarlyTerminationBuilder optionalEarlyTermination;
		protected ExercisePeriod.ExercisePeriodBuilder optionalEarlyTerminationParameters;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public EarlyTerminationProvisionBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("mandatoryEarlyTermination")
		public MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder getMandatoryEarlyTermination() {
			return mandatoryEarlyTermination;
		}
		
		@Override
		public MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder getOrCreateMandatoryEarlyTermination() {
			MandatoryEarlyTermination.MandatoryEarlyTerminationBuilder result;
			if (mandatoryEarlyTermination!=null) {
				result = mandatoryEarlyTermination;
			}
			else {
				result = mandatoryEarlyTermination = MandatoryEarlyTermination.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("mandatoryEarlyTerminationDateTenor")
		public Period.PeriodBuilder getMandatoryEarlyTerminationDateTenor() {
			return mandatoryEarlyTerminationDateTenor;
		}
		
		@Override
		public Period.PeriodBuilder getOrCreateMandatoryEarlyTerminationDateTenor() {
			Period.PeriodBuilder result;
			if (mandatoryEarlyTerminationDateTenor!=null) {
				result = mandatoryEarlyTerminationDateTenor;
			}
			else {
				result = mandatoryEarlyTerminationDateTenor = Period.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("optionalEarlyTermination")
		public OptionalEarlyTermination.OptionalEarlyTerminationBuilder getOptionalEarlyTermination() {
			return optionalEarlyTermination;
		}
		
		@Override
		public OptionalEarlyTermination.OptionalEarlyTerminationBuilder getOrCreateOptionalEarlyTermination() {
			OptionalEarlyTermination.OptionalEarlyTerminationBuilder result;
			if (optionalEarlyTermination!=null) {
				result = optionalEarlyTermination;
			}
			else {
				result = optionalEarlyTermination = OptionalEarlyTermination.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("optionalEarlyTerminationParameters")
		public ExercisePeriod.ExercisePeriodBuilder getOptionalEarlyTerminationParameters() {
			return optionalEarlyTerminationParameters;
		}
		
		@Override
		public ExercisePeriod.ExercisePeriodBuilder getOrCreateOptionalEarlyTerminationParameters() {
			ExercisePeriod.ExercisePeriodBuilder result;
			if (optionalEarlyTerminationParameters!=null) {
				result = optionalEarlyTerminationParameters;
			}
			else {
				result = optionalEarlyTerminationParameters = ExercisePeriod.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("mandatoryEarlyTermination")
		public EarlyTerminationProvision.EarlyTerminationProvisionBuilder setMandatoryEarlyTermination(MandatoryEarlyTermination mandatoryEarlyTermination) {
			this.mandatoryEarlyTermination = mandatoryEarlyTermination==null?null:mandatoryEarlyTermination.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("mandatoryEarlyTerminationDateTenor")
		public EarlyTerminationProvision.EarlyTerminationProvisionBuilder setMandatoryEarlyTerminationDateTenor(Period mandatoryEarlyTerminationDateTenor) {
			this.mandatoryEarlyTerminationDateTenor = mandatoryEarlyTerminationDateTenor==null?null:mandatoryEarlyTerminationDateTenor.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("optionalEarlyTermination")
		public EarlyTerminationProvision.EarlyTerminationProvisionBuilder setOptionalEarlyTermination(OptionalEarlyTermination optionalEarlyTermination) {
			this.optionalEarlyTermination = optionalEarlyTermination==null?null:optionalEarlyTermination.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("optionalEarlyTerminationParameters")
		public EarlyTerminationProvision.EarlyTerminationProvisionBuilder setOptionalEarlyTerminationParameters(ExercisePeriod optionalEarlyTerminationParameters) {
			this.optionalEarlyTerminationParameters = optionalEarlyTerminationParameters==null?null:optionalEarlyTerminationParameters.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public EarlyTerminationProvision.EarlyTerminationProvisionBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public EarlyTerminationProvision build() {
			return new EarlyTerminationProvision.EarlyTerminationProvisionImpl(this);
		}
		
		@Override
		public EarlyTerminationProvision.EarlyTerminationProvisionBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EarlyTerminationProvision.EarlyTerminationProvisionBuilder prune() {
			if (mandatoryEarlyTermination!=null && !mandatoryEarlyTermination.prune().hasData()) mandatoryEarlyTermination = null;
			if (mandatoryEarlyTerminationDateTenor!=null && !mandatoryEarlyTerminationDateTenor.prune().hasData()) mandatoryEarlyTerminationDateTenor = null;
			if (optionalEarlyTermination!=null && !optionalEarlyTermination.prune().hasData()) optionalEarlyTermination = null;
			if (optionalEarlyTerminationParameters!=null && !optionalEarlyTerminationParameters.prune().hasData()) optionalEarlyTerminationParameters = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getMandatoryEarlyTermination()!=null && getMandatoryEarlyTermination().hasData()) return true;
			if (getMandatoryEarlyTerminationDateTenor()!=null && getMandatoryEarlyTerminationDateTenor().hasData()) return true;
			if (getOptionalEarlyTermination()!=null && getOptionalEarlyTermination().hasData()) return true;
			if (getOptionalEarlyTerminationParameters()!=null && getOptionalEarlyTerminationParameters().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public EarlyTerminationProvision.EarlyTerminationProvisionBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			EarlyTerminationProvision.EarlyTerminationProvisionBuilder o = (EarlyTerminationProvision.EarlyTerminationProvisionBuilder) other;
			
			merger.mergeRosetta(getMandatoryEarlyTermination(), o.getMandatoryEarlyTermination(), this::setMandatoryEarlyTermination);
			merger.mergeRosetta(getMandatoryEarlyTerminationDateTenor(), o.getMandatoryEarlyTerminationDateTenor(), this::setMandatoryEarlyTerminationDateTenor);
			merger.mergeRosetta(getOptionalEarlyTermination(), o.getOptionalEarlyTermination(), this::setOptionalEarlyTermination);
			merger.mergeRosetta(getOptionalEarlyTerminationParameters(), o.getOptionalEarlyTerminationParameters(), this::setOptionalEarlyTerminationParameters);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			EarlyTerminationProvision _that = getType().cast(o);
		
			if (!Objects.equals(mandatoryEarlyTermination, _that.getMandatoryEarlyTermination())) return false;
			if (!Objects.equals(mandatoryEarlyTerminationDateTenor, _that.getMandatoryEarlyTerminationDateTenor())) return false;
			if (!Objects.equals(optionalEarlyTermination, _that.getOptionalEarlyTermination())) return false;
			if (!Objects.equals(optionalEarlyTerminationParameters, _that.getOptionalEarlyTerminationParameters())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (mandatoryEarlyTermination != null ? mandatoryEarlyTermination.hashCode() : 0);
			_result = 31 * _result + (mandatoryEarlyTerminationDateTenor != null ? mandatoryEarlyTerminationDateTenor.hashCode() : 0);
			_result = 31 * _result + (optionalEarlyTermination != null ? optionalEarlyTermination.hashCode() : 0);
			_result = 31 * _result + (optionalEarlyTerminationParameters != null ? optionalEarlyTerminationParameters.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "EarlyTerminationProvisionBuilder {" +
				"mandatoryEarlyTermination=" + this.mandatoryEarlyTermination + ", " +
				"mandatoryEarlyTerminationDateTenor=" + this.mandatoryEarlyTerminationDateTenor + ", " +
				"optionalEarlyTermination=" + this.optionalEarlyTermination + ", " +
				"optionalEarlyTerminationParameters=" + this.optionalEarlyTerminationParameters + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
