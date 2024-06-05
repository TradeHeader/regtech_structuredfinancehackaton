package cdm.product.template;

import cdm.product.template.ExerciseProcedure;
import cdm.product.template.OptionExercise;
import cdm.product.template.OptionExercise.OptionExerciseBuilder;
import cdm.product.template.OptionExercise.OptionExerciseBuilderImpl;
import cdm.product.template.OptionExercise.OptionExerciseImpl;
import cdm.product.template.OptionStrike;
import cdm.product.template.OptionStyle;
import cdm.product.template.meta.OptionExerciseMeta;
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
 *  A class to represent the applicable terms to qualify an option exercise: the option style (e.g. American style option), the exercise procedure (e.g. manual exercise) and the settlement terms (e.g. physical vs. cash).
 * @version ${project.version}
 */
@RosettaDataType(value="OptionExercise", builder=OptionExercise.OptionExerciseBuilderImpl.class, version="${project.version}")
public interface OptionExercise extends RosettaModelObject {

	OptionExerciseMeta metaData = new OptionExerciseMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The option exercise can be of American style, Bermuda style or European style. The FpML implementation makes use of a substitution group.
	 */
	OptionStyle getOptionStyle();
	/**
	 * Specifies the strike of the option on credit default swap.
	 */
	OptionStrike getStrike();
	/**
	 * The set of parameters defining the procedure associated with the exercise, e.g. manual exercise.
	 */
	ExerciseProcedure getExerciseProcedure();

	/*********************** Build Methods  ***********************/
	OptionExercise build();
	
	OptionExercise.OptionExerciseBuilder toBuilder();
	
	static OptionExercise.OptionExerciseBuilder builder() {
		return new OptionExercise.OptionExerciseBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends OptionExercise> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends OptionExercise> getType() {
		return OptionExercise.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("optionStyle"), processor, OptionStyle.class, getOptionStyle());
		processRosetta(path.newSubPath("strike"), processor, OptionStrike.class, getStrike());
		processRosetta(path.newSubPath("exerciseProcedure"), processor, ExerciseProcedure.class, getExerciseProcedure());
	}
	

	/*********************** Builder Interface  ***********************/
	interface OptionExerciseBuilder extends OptionExercise, RosettaModelObjectBuilder {
		OptionStyle.OptionStyleBuilder getOrCreateOptionStyle();
		OptionStyle.OptionStyleBuilder getOptionStyle();
		OptionStrike.OptionStrikeBuilder getOrCreateStrike();
		OptionStrike.OptionStrikeBuilder getStrike();
		ExerciseProcedure.ExerciseProcedureBuilder getOrCreateExerciseProcedure();
		ExerciseProcedure.ExerciseProcedureBuilder getExerciseProcedure();
		OptionExercise.OptionExerciseBuilder setOptionStyle(OptionStyle optionStyle);
		OptionExercise.OptionExerciseBuilder setStrike(OptionStrike strike);
		OptionExercise.OptionExerciseBuilder setExerciseProcedure(ExerciseProcedure exerciseProcedure);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("optionStyle"), processor, OptionStyle.OptionStyleBuilder.class, getOptionStyle());
			processRosetta(path.newSubPath("strike"), processor, OptionStrike.OptionStrikeBuilder.class, getStrike());
			processRosetta(path.newSubPath("exerciseProcedure"), processor, ExerciseProcedure.ExerciseProcedureBuilder.class, getExerciseProcedure());
		}
		

		OptionExercise.OptionExerciseBuilder prune();
	}

	/*********************** Immutable Implementation of OptionExercise  ***********************/
	class OptionExerciseImpl implements OptionExercise {
		private final OptionStyle optionStyle;
		private final OptionStrike strike;
		private final ExerciseProcedure exerciseProcedure;
		
		protected OptionExerciseImpl(OptionExercise.OptionExerciseBuilder builder) {
			this.optionStyle = ofNullable(builder.getOptionStyle()).map(f->f.build()).orElse(null);
			this.strike = ofNullable(builder.getStrike()).map(f->f.build()).orElse(null);
			this.exerciseProcedure = ofNullable(builder.getExerciseProcedure()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("optionStyle")
		public OptionStyle getOptionStyle() {
			return optionStyle;
		}
		
		@Override
		@RosettaAttribute("strike")
		public OptionStrike getStrike() {
			return strike;
		}
		
		@Override
		@RosettaAttribute("exerciseProcedure")
		public ExerciseProcedure getExerciseProcedure() {
			return exerciseProcedure;
		}
		
		@Override
		public OptionExercise build() {
			return this;
		}
		
		@Override
		public OptionExercise.OptionExerciseBuilder toBuilder() {
			OptionExercise.OptionExerciseBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(OptionExercise.OptionExerciseBuilder builder) {
			ofNullable(getOptionStyle()).ifPresent(builder::setOptionStyle);
			ofNullable(getStrike()).ifPresent(builder::setStrike);
			ofNullable(getExerciseProcedure()).ifPresent(builder::setExerciseProcedure);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OptionExercise _that = getType().cast(o);
		
			if (!Objects.equals(optionStyle, _that.getOptionStyle())) return false;
			if (!Objects.equals(strike, _that.getStrike())) return false;
			if (!Objects.equals(exerciseProcedure, _that.getExerciseProcedure())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (optionStyle != null ? optionStyle.hashCode() : 0);
			_result = 31 * _result + (strike != null ? strike.hashCode() : 0);
			_result = 31 * _result + (exerciseProcedure != null ? exerciseProcedure.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OptionExercise {" +
				"optionStyle=" + this.optionStyle + ", " +
				"strike=" + this.strike + ", " +
				"exerciseProcedure=" + this.exerciseProcedure +
			'}';
		}
	}

	/*********************** Builder Implementation of OptionExercise  ***********************/
	class OptionExerciseBuilderImpl implements OptionExercise.OptionExerciseBuilder {
	
		protected OptionStyle.OptionStyleBuilder optionStyle;
		protected OptionStrike.OptionStrikeBuilder strike;
		protected ExerciseProcedure.ExerciseProcedureBuilder exerciseProcedure;
	
		public OptionExerciseBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("optionStyle")
		public OptionStyle.OptionStyleBuilder getOptionStyle() {
			return optionStyle;
		}
		
		@Override
		public OptionStyle.OptionStyleBuilder getOrCreateOptionStyle() {
			OptionStyle.OptionStyleBuilder result;
			if (optionStyle!=null) {
				result = optionStyle;
			}
			else {
				result = optionStyle = OptionStyle.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("strike")
		public OptionStrike.OptionStrikeBuilder getStrike() {
			return strike;
		}
		
		@Override
		public OptionStrike.OptionStrikeBuilder getOrCreateStrike() {
			OptionStrike.OptionStrikeBuilder result;
			if (strike!=null) {
				result = strike;
			}
			else {
				result = strike = OptionStrike.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("exerciseProcedure")
		public ExerciseProcedure.ExerciseProcedureBuilder getExerciseProcedure() {
			return exerciseProcedure;
		}
		
		@Override
		public ExerciseProcedure.ExerciseProcedureBuilder getOrCreateExerciseProcedure() {
			ExerciseProcedure.ExerciseProcedureBuilder result;
			if (exerciseProcedure!=null) {
				result = exerciseProcedure;
			}
			else {
				result = exerciseProcedure = ExerciseProcedure.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("optionStyle")
		public OptionExercise.OptionExerciseBuilder setOptionStyle(OptionStyle optionStyle) {
			this.optionStyle = optionStyle==null?null:optionStyle.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("strike")
		public OptionExercise.OptionExerciseBuilder setStrike(OptionStrike strike) {
			this.strike = strike==null?null:strike.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("exerciseProcedure")
		public OptionExercise.OptionExerciseBuilder setExerciseProcedure(ExerciseProcedure exerciseProcedure) {
			this.exerciseProcedure = exerciseProcedure==null?null:exerciseProcedure.toBuilder();
			return this;
		}
		
		@Override
		public OptionExercise build() {
			return new OptionExercise.OptionExerciseImpl(this);
		}
		
		@Override
		public OptionExercise.OptionExerciseBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OptionExercise.OptionExerciseBuilder prune() {
			if (optionStyle!=null && !optionStyle.prune().hasData()) optionStyle = null;
			if (strike!=null && !strike.prune().hasData()) strike = null;
			if (exerciseProcedure!=null && !exerciseProcedure.prune().hasData()) exerciseProcedure = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getOptionStyle()!=null && getOptionStyle().hasData()) return true;
			if (getStrike()!=null && getStrike().hasData()) return true;
			if (getExerciseProcedure()!=null && getExerciseProcedure().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public OptionExercise.OptionExerciseBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			OptionExercise.OptionExerciseBuilder o = (OptionExercise.OptionExerciseBuilder) other;
			
			merger.mergeRosetta(getOptionStyle(), o.getOptionStyle(), this::setOptionStyle);
			merger.mergeRosetta(getStrike(), o.getStrike(), this::setStrike);
			merger.mergeRosetta(getExerciseProcedure(), o.getExerciseProcedure(), this::setExerciseProcedure);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			OptionExercise _that = getType().cast(o);
		
			if (!Objects.equals(optionStyle, _that.getOptionStyle())) return false;
			if (!Objects.equals(strike, _that.getStrike())) return false;
			if (!Objects.equals(exerciseProcedure, _that.getExerciseProcedure())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (optionStyle != null ? optionStyle.hashCode() : 0);
			_result = 31 * _result + (strike != null ? strike.hashCode() : 0);
			_result = 31 * _result + (exerciseProcedure != null ? exerciseProcedure.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "OptionExerciseBuilder {" +
				"optionStyle=" + this.optionStyle + ", " +
				"strike=" + this.strike + ", " +
				"exerciseProcedure=" + this.exerciseProcedure +
			'}';
		}
	}
}
