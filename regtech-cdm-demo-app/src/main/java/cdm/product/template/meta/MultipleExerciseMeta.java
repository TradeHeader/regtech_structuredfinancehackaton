package cdm.product.template.meta;

import cdm.product.template.MultipleExercise;
import cdm.product.template.validation.MultipleExerciseTypeFormatValidator;
import cdm.product.template.validation.MultipleExerciseValidator;
import cdm.product.template.validation.exists.MultipleExerciseOnlyExistsValidator;
import com.rosetta.model.lib.annotations.RosettaMeta;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.qualify.QualifyFunctionFactory;
import com.rosetta.model.lib.qualify.QualifyResult;
import com.rosetta.model.lib.validation.Validator;
import com.rosetta.model.lib.validation.ValidatorFactory;
import com.rosetta.model.lib.validation.ValidatorWithArg;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;


/**
 * @version ${project.version}
 */
@RosettaMeta(model=MultipleExercise.class)
public class MultipleExerciseMeta implements RosettaMetaData<MultipleExercise> {

	@Override
	public List<Validator<? super MultipleExercise>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.MultipleExerciseMaximumChoice.class),
			factory.create(cdm.product.template.validation.datarule.MultipleExerciseMaximumNumberOfOptions.class),
			factory.create(cdm.product.template.validation.datarule.MultipleExerciseMinimumNumberOfOptions.class),
			factory.create(cdm.product.template.validation.datarule.PartialExerciseMinimumChoice.class)
		);
	}
	
	@Override
	public List<Function<? super MultipleExercise, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MultipleExercise> validator() {
		return new MultipleExerciseValidator();
	}

	@Override
	public Validator<? super MultipleExercise> typeFormatValidator() {
		return new MultipleExerciseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MultipleExercise, Set<String>> onlyExistsValidator() {
		return new MultipleExerciseOnlyExistsValidator();
	}
}
