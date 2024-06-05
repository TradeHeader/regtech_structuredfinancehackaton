package cdm.product.template.meta;

import cdm.product.template.EuropeanExercise;
import cdm.product.template.validation.EuropeanExerciseTypeFormatValidator;
import cdm.product.template.validation.EuropeanExerciseValidator;
import cdm.product.template.validation.exists.EuropeanExerciseOnlyExistsValidator;
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
@RosettaMeta(model=EuropeanExercise.class)
public class EuropeanExerciseMeta implements RosettaMetaData<EuropeanExercise> {

	@Override
	public List<Validator<? super EuropeanExercise>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super EuropeanExercise, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EuropeanExercise> validator() {
		return new EuropeanExerciseValidator();
	}

	@Override
	public Validator<? super EuropeanExercise> typeFormatValidator() {
		return new EuropeanExerciseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EuropeanExercise, Set<String>> onlyExistsValidator() {
		return new EuropeanExerciseOnlyExistsValidator();
	}
}
