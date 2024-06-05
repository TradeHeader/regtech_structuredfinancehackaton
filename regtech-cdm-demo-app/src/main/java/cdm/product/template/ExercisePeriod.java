package cdm.product.template;

import cdm.base.datetime.Period;
import cdm.product.template.ExercisePeriod;
import cdm.product.template.ExercisePeriod.ExercisePeriodBuilder;
import cdm.product.template.ExercisePeriod.ExercisePeriodBuilderImpl;
import cdm.product.template.ExercisePeriod.ExercisePeriodImpl;
import cdm.product.template.meta.ExercisePeriodMeta;
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
 * This defines the time interval to the start of the exercise period, i.e. the earliest exercise date, and the frequency of subsequent exercise dates (if any).
 * @version ${project.version}
 */
@RosettaDataType(value="ExercisePeriod", builder=ExercisePeriod.ExercisePeriodBuilderImpl.class, version="${project.version}")
public interface ExercisePeriod extends RosettaModelObject, GlobalKey {

	ExercisePeriodMeta metaData = new ExercisePeriodMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The time interval to the first (and possibly only) exercise date in the exercise period.
	 */
	Period getEarliestExerciseDateTenor();
	/**
	 * The frequency of subsequent exercise dates in the exercise period following the earliest exercise date. An interval of 1 day should be used to indicate an American style exercise period.
	 */
	Period getExerciseFrequency();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	ExercisePeriod build();
	
	ExercisePeriod.ExercisePeriodBuilder toBuilder();
	
	static ExercisePeriod.ExercisePeriodBuilder builder() {
		return new ExercisePeriod.ExercisePeriodBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ExercisePeriod> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ExercisePeriod> getType() {
		return ExercisePeriod.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("earliestExerciseDateTenor"), processor, Period.class, getEarliestExerciseDateTenor());
		processRosetta(path.newSubPath("exerciseFrequency"), processor, Period.class, getExerciseFrequency());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ExercisePeriodBuilder extends ExercisePeriod, RosettaModelObjectBuilder {
		Period.PeriodBuilder getOrCreateEarliestExerciseDateTenor();
		Period.PeriodBuilder getEarliestExerciseDateTenor();
		Period.PeriodBuilder getOrCreateExerciseFrequency();
		Period.PeriodBuilder getExerciseFrequency();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		ExercisePeriod.ExercisePeriodBuilder setEarliestExerciseDateTenor(Period earliestExerciseDateTenor);
		ExercisePeriod.ExercisePeriodBuilder setExerciseFrequency(Period exerciseFrequency);
		ExercisePeriod.ExercisePeriodBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("earliestExerciseDateTenor"), processor, Period.PeriodBuilder.class, getEarliestExerciseDateTenor());
			processRosetta(path.newSubPath("exerciseFrequency"), processor, Period.PeriodBuilder.class, getExerciseFrequency());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		ExercisePeriod.ExercisePeriodBuilder prune();
	}

	/*********************** Immutable Implementation of ExercisePeriod  ***********************/
	class ExercisePeriodImpl implements ExercisePeriod {
		private final Period earliestExerciseDateTenor;
		private final Period exerciseFrequency;
		private final MetaFields meta;
		
		protected ExercisePeriodImpl(ExercisePeriod.ExercisePeriodBuilder builder) {
			this.earliestExerciseDateTenor = ofNullable(builder.getEarliestExerciseDateTenor()).map(f->f.build()).orElse(null);
			this.exerciseFrequency = ofNullable(builder.getExerciseFrequency()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("earliestExerciseDateTenor")
		public Period getEarliestExerciseDateTenor() {
			return earliestExerciseDateTenor;
		}
		
		@Override
		@RosettaAttribute("exerciseFrequency")
		public Period getExerciseFrequency() {
			return exerciseFrequency;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public ExercisePeriod build() {
			return this;
		}
		
		@Override
		public ExercisePeriod.ExercisePeriodBuilder toBuilder() {
			ExercisePeriod.ExercisePeriodBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ExercisePeriod.ExercisePeriodBuilder builder) {
			ofNullable(getEarliestExerciseDateTenor()).ifPresent(builder::setEarliestExerciseDateTenor);
			ofNullable(getExerciseFrequency()).ifPresent(builder::setExerciseFrequency);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExercisePeriod _that = getType().cast(o);
		
			if (!Objects.equals(earliestExerciseDateTenor, _that.getEarliestExerciseDateTenor())) return false;
			if (!Objects.equals(exerciseFrequency, _that.getExerciseFrequency())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (earliestExerciseDateTenor != null ? earliestExerciseDateTenor.hashCode() : 0);
			_result = 31 * _result + (exerciseFrequency != null ? exerciseFrequency.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExercisePeriod {" +
				"earliestExerciseDateTenor=" + this.earliestExerciseDateTenor + ", " +
				"exerciseFrequency=" + this.exerciseFrequency + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of ExercisePeriod  ***********************/
	class ExercisePeriodBuilderImpl implements ExercisePeriod.ExercisePeriodBuilder, GlobalKeyBuilder {
	
		protected Period.PeriodBuilder earliestExerciseDateTenor;
		protected Period.PeriodBuilder exerciseFrequency;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public ExercisePeriodBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("earliestExerciseDateTenor")
		public Period.PeriodBuilder getEarliestExerciseDateTenor() {
			return earliestExerciseDateTenor;
		}
		
		@Override
		public Period.PeriodBuilder getOrCreateEarliestExerciseDateTenor() {
			Period.PeriodBuilder result;
			if (earliestExerciseDateTenor!=null) {
				result = earliestExerciseDateTenor;
			}
			else {
				result = earliestExerciseDateTenor = Period.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("exerciseFrequency")
		public Period.PeriodBuilder getExerciseFrequency() {
			return exerciseFrequency;
		}
		
		@Override
		public Period.PeriodBuilder getOrCreateExerciseFrequency() {
			Period.PeriodBuilder result;
			if (exerciseFrequency!=null) {
				result = exerciseFrequency;
			}
			else {
				result = exerciseFrequency = Period.builder();
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
		@RosettaAttribute("earliestExerciseDateTenor")
		public ExercisePeriod.ExercisePeriodBuilder setEarliestExerciseDateTenor(Period earliestExerciseDateTenor) {
			this.earliestExerciseDateTenor = earliestExerciseDateTenor==null?null:earliestExerciseDateTenor.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("exerciseFrequency")
		public ExercisePeriod.ExercisePeriodBuilder setExerciseFrequency(Period exerciseFrequency) {
			this.exerciseFrequency = exerciseFrequency==null?null:exerciseFrequency.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public ExercisePeriod.ExercisePeriodBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public ExercisePeriod build() {
			return new ExercisePeriod.ExercisePeriodImpl(this);
		}
		
		@Override
		public ExercisePeriod.ExercisePeriodBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExercisePeriod.ExercisePeriodBuilder prune() {
			if (earliestExerciseDateTenor!=null && !earliestExerciseDateTenor.prune().hasData()) earliestExerciseDateTenor = null;
			if (exerciseFrequency!=null && !exerciseFrequency.prune().hasData()) exerciseFrequency = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getEarliestExerciseDateTenor()!=null && getEarliestExerciseDateTenor().hasData()) return true;
			if (getExerciseFrequency()!=null && getExerciseFrequency().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ExercisePeriod.ExercisePeriodBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ExercisePeriod.ExercisePeriodBuilder o = (ExercisePeriod.ExercisePeriodBuilder) other;
			
			merger.mergeRosetta(getEarliestExerciseDateTenor(), o.getEarliestExerciseDateTenor(), this::setEarliestExerciseDateTenor);
			merger.mergeRosetta(getExerciseFrequency(), o.getExerciseFrequency(), this::setExerciseFrequency);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ExercisePeriod _that = getType().cast(o);
		
			if (!Objects.equals(earliestExerciseDateTenor, _that.getEarliestExerciseDateTenor())) return false;
			if (!Objects.equals(exerciseFrequency, _that.getExerciseFrequency())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (earliestExerciseDateTenor != null ? earliestExerciseDateTenor.hashCode() : 0);
			_result = 31 * _result + (exerciseFrequency != null ? exerciseFrequency.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ExercisePeriodBuilder {" +
				"earliestExerciseDateTenor=" + this.earliestExerciseDateTenor + ", " +
				"exerciseFrequency=" + this.exerciseFrequency + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
