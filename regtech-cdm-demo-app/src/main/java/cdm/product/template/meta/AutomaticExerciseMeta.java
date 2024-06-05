package cdm.product.template.meta;

import cdm.product.template.AutomaticExercise;
import cdm.product.template.validation.AutomaticExerciseTypeFormatValidator;
import cdm.product.template.validation.AutomaticExerciseValidator;
import cdm.product.template.validation.exists.AutomaticExerciseOnlyExistsValidator;
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
@RosettaMeta(model=AutomaticExercise.class)
public class AutomaticExerciseMeta implements RosettaMetaData<AutomaticExercise> {

	@Override
	public List<Validator<? super AutomaticExercise>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AutomaticExercise, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AutomaticExercise> validator() {
		return new AutomaticExerciseValidator();
	}

	@Override
	public Validator<? super AutomaticExercise> typeFormatValidator() {
		return new AutomaticExerciseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AutomaticExercise, Set<String>> onlyExistsValidator() {
		return new AutomaticExerciseOnlyExistsValidator();
	}
}
