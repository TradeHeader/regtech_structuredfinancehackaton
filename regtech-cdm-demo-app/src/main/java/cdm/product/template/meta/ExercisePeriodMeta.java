package cdm.product.template.meta;

import cdm.product.template.ExercisePeriod;
import cdm.product.template.validation.ExercisePeriodTypeFormatValidator;
import cdm.product.template.validation.ExercisePeriodValidator;
import cdm.product.template.validation.exists.ExercisePeriodOnlyExistsValidator;
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
@RosettaMeta(model=ExercisePeriod.class)
public class ExercisePeriodMeta implements RosettaMetaData<ExercisePeriod> {

	@Override
	public List<Validator<? super ExercisePeriod>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ExercisePeriod, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ExercisePeriod> validator() {
		return new ExercisePeriodValidator();
	}

	@Override
	public Validator<? super ExercisePeriod> typeFormatValidator() {
		return new ExercisePeriodTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ExercisePeriod, Set<String>> onlyExistsValidator() {
		return new ExercisePeriodOnlyExistsValidator();
	}
}
