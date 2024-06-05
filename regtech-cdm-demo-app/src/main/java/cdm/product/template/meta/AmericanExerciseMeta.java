package cdm.product.template.meta;

import cdm.product.template.AmericanExercise;
import cdm.product.template.validation.AmericanExerciseTypeFormatValidator;
import cdm.product.template.validation.AmericanExerciseValidator;
import cdm.product.template.validation.exists.AmericanExerciseOnlyExistsValidator;
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
@RosettaMeta(model=AmericanExercise.class)
public class AmericanExerciseMeta implements RosettaMetaData<AmericanExercise> {

	@Override
	public List<Validator<? super AmericanExercise>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AmericanExercise, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AmericanExercise> validator() {
		return new AmericanExerciseValidator();
	}

	@Override
	public Validator<? super AmericanExercise> typeFormatValidator() {
		return new AmericanExerciseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AmericanExercise, Set<String>> onlyExistsValidator() {
		return new AmericanExerciseOnlyExistsValidator();
	}
}
