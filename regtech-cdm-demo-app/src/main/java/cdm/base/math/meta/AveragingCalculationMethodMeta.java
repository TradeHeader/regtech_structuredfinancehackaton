package cdm.base.math.meta;

import cdm.base.math.AveragingCalculationMethod;
import cdm.base.math.validation.AveragingCalculationMethodTypeFormatValidator;
import cdm.base.math.validation.AveragingCalculationMethodValidator;
import cdm.base.math.validation.exists.AveragingCalculationMethodOnlyExistsValidator;
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
@RosettaMeta(model=AveragingCalculationMethod.class)
public class AveragingCalculationMethodMeta implements RosettaMetaData<AveragingCalculationMethod> {

	@Override
	public List<Validator<? super AveragingCalculationMethod>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AveragingCalculationMethod, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AveragingCalculationMethod> validator() {
		return new AveragingCalculationMethodValidator();
	}

	@Override
	public Validator<? super AveragingCalculationMethod> typeFormatValidator() {
		return new AveragingCalculationMethodTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AveragingCalculationMethod, Set<String>> onlyExistsValidator() {
		return new AveragingCalculationMethodOnlyExistsValidator();
	}
}
