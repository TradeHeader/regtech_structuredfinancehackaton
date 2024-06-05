package cdm.product.template;

import cdm.product.template.ExerciseNotice;
import cdm.product.template.ManualExercise;
import cdm.product.template.ManualExercise.ManualExerciseBuilder;
import cdm.product.template.ManualExercise.ManualExerciseBuilderImpl;
import cdm.product.template.ManualExercise.ManualExerciseImpl;
import cdm.product.template.meta.ManualExerciseMeta;
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
 * A class defining manual exercise, i.e. that the option buyer counterparty must give notice to the option seller of exercise.
 * @version ${project.version}
 */
@RosettaDataType(value="ManualExercise", builder=ManualExercise.ManualExerciseBuilderImpl.class, version="${project.version}")
public interface ManualExercise extends RosettaModelObject {

	ManualExerciseMeta metaData = new ManualExerciseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * Definition of the party to whom notice of exercise should be given.
	 */
	ExerciseNotice getExerciseNotice();
	/**
	 * If fallback exercise is specified then the notional amount of the underlying swap, not previously exercised under the swaption, will be automatically exercised at the expiration time on the expiration date if at such time the buyer is in-the-money, provided that the difference between the settlement rate and the fixed rate under the relevant underlying swap is not less than one tenth of a percentage point (0.10% or 0.001). The term in-the-money is assumed to have the meaning defined in the 2000 ISDA Definitions, Section 17.4. In-the-money.
	 */
	Boolean getFallbackExercise();

	/*********************** Build Methods  ***********************/
	ManualExercise build();
	
	ManualExercise.ManualExerciseBuilder toBuilder();
	
	static ManualExercise.ManualExerciseBuilder builder() {
		return new ManualExercise.ManualExerciseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ManualExercise> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ManualExercise> getType() {
		return ManualExercise.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("exerciseNotice"), processor, ExerciseNotice.class, getExerciseNotice());
		processor.processBasic(path.newSubPath("fallbackExercise"), Boolean.class, getFallbackExercise(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface ManualExerciseBuilder extends ManualExercise, RosettaModelObjectBuilder {
		ExerciseNotice.ExerciseNoticeBuilder getOrCreateExerciseNotice();
		ExerciseNotice.ExerciseNoticeBuilder getExerciseNotice();
		ManualExercise.ManualExerciseBuilder setExerciseNotice(ExerciseNotice exerciseNotice);
		ManualExercise.ManualExerciseBuilder setFallbackExercise(Boolean fallbackExercise);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("exerciseNotice"), processor, ExerciseNotice.ExerciseNoticeBuilder.class, getExerciseNotice());
			processor.processBasic(path.newSubPath("fallbackExercise"), Boolean.class, getFallbackExercise(), this);
		}
		

		ManualExercise.ManualExerciseBuilder prune();
	}

	/*********************** Immutable Implementation of ManualExercise  ***********************/
	class ManualExerciseImpl implements ManualExercise {
		private final ExerciseNotice exerciseNotice;
		private final Boolean fallbackExercise;
		
		protected ManualExerciseImpl(ManualExercise.ManualExerciseBuilder builder) {
			this.exerciseNotice = ofNullable(builder.getExerciseNotice()).map(f->f.build()).orElse(null);
			this.fallbackExercise = builder.getFallbackExercise();
		}
		
		@Override
		@RosettaAttribute("exerciseNotice")
		public ExerciseNotice getExerciseNotice() {
			return exerciseNotice;
		}
		
		@Override
		@RosettaAttribute("fallbackExercise")
		public Boolean getFallbackExercise() {
			return fallbackExercise;
		}
		
		@Override
		public ManualExercise build() {
			return this;
		}
		
		@Override
		public ManualExercise.ManualExerciseBuilder toBuilder() {
			ManualExercise.ManualExerciseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ManualExercise.ManualExerciseBuilder builder) {
			ofNullable(getExerciseNotice()).ifPresent(builder::setExerciseNotice);
			ofNullable(getFallbackExercise()).ifPresent(builder::setFallbackExercise);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ManualExercise _that = getType().cast(o);
		
			if (!Objects.equals(exerciseNotice, _that.getExerciseNotice())) return false;
			if (!Objects.equals(fallbackExercise, _that.getFallbackExercise())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (exerciseNotice != null ? exerciseNotice.hashCode() : 0);
			_result = 31 * _result + (fallbackExercise != null ? fallbackExercise.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ManualExercise {" +
				"exerciseNotice=" + this.exerciseNotice + ", " +
				"fallbackExercise=" + this.fallbackExercise +
			'}';
		}
	}

	/*********************** Builder Implementation of ManualExercise  ***********************/
	class ManualExerciseBuilderImpl implements ManualExercise.ManualExerciseBuilder {
	
		protected ExerciseNotice.ExerciseNoticeBuilder exerciseNotice;
		protected Boolean fallbackExercise;
	
		public ManualExerciseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("exerciseNotice")
		public ExerciseNotice.ExerciseNoticeBuilder getExerciseNotice() {
			return exerciseNotice;
		}
		
		@Override
		public ExerciseNotice.ExerciseNoticeBuilder getOrCreateExerciseNotice() {
			ExerciseNotice.ExerciseNoticeBuilder result;
			if (exerciseNotice!=null) {
				result = exerciseNotice;
			}
			else {
				result = exerciseNotice = ExerciseNotice.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("fallbackExercise")
		public Boolean getFallbackExercise() {
			return fallbackExercise;
		}
		
	
		@Override
		@RosettaAttribute("exerciseNotice")
		public ManualExercise.ManualExerciseBuilder setExerciseNotice(ExerciseNotice exerciseNotice) {
			this.exerciseNotice = exerciseNotice==null?null:exerciseNotice.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("fallbackExercise")
		public ManualExercise.ManualExerciseBuilder setFallbackExercise(Boolean fallbackExercise) {
			this.fallbackExercise = fallbackExercise==null?null:fallbackExercise;
			return this;
		}
		
		@Override
		public ManualExercise build() {
			return new ManualExercise.ManualExerciseImpl(this);
		}
		
		@Override
		public ManualExercise.ManualExerciseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ManualExercise.ManualExerciseBuilder prune() {
			if (exerciseNotice!=null && !exerciseNotice.prune().hasData()) exerciseNotice = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getExerciseNotice()!=null && getExerciseNotice().hasData()) return true;
			if (getFallbackExercise()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ManualExercise.ManualExerciseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ManualExercise.ManualExerciseBuilder o = (ManualExercise.ManualExerciseBuilder) other;
			
			merger.mergeRosetta(getExerciseNotice(), o.getExerciseNotice(), this::setExerciseNotice);
			
			merger.mergeBasic(getFallbackExercise(), o.getFallbackExercise(), this::setFallbackExercise);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ManualExercise _that = getType().cast(o);
		
			if (!Objects.equals(exerciseNotice, _that.getExerciseNotice())) return false;
			if (!Objects.equals(fallbackExercise, _that.getFallbackExercise())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (exerciseNotice != null ? exerciseNotice.hashCode() : 0);
			_result = 31 * _result + (fallbackExercise != null ? fallbackExercise.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ManualExerciseBuilder {" +
				"exerciseNotice=" + this.exerciseNotice + ", " +
				"fallbackExercise=" + this.fallbackExercise +
			'}';
		}
	}
}
