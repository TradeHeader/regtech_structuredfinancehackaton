package cdm.product.template.meta;

import cdm.product.template.ExerciseProcedure;
import cdm.product.template.validation.ExerciseProcedureTypeFormatValidator;
import cdm.product.template.validation.ExerciseProcedureValidator;
import cdm.product.template.validation.exists.ExerciseProcedureOnlyExistsValidator;
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
@RosettaMeta(model=ExerciseProcedure.class)
public class ExerciseProcedureMeta implements RosettaMetaData<ExerciseProcedure> {

	@Override
	public List<Validator<? super ExerciseProcedure>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.ExerciseProcedureExerciseProcedureChoice.class)
		);
	}
	
	@Override
	public List<Function<? super ExerciseProcedure, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ExerciseProcedure> validator() {
		return new ExerciseProcedureValidator();
	}

	@Override
	public Validator<? super ExerciseProcedure> typeFormatValidator() {
		return new ExerciseProcedureTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ExerciseProcedure, Set<String>> onlyExistsValidator() {
		return new ExerciseProcedureOnlyExistsValidator();
	}
}
