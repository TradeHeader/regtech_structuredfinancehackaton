package cdm.base.math.meta;

import cdm.base.math.NonNegativeStep;
import cdm.base.math.validation.NonNegativeStepTypeFormatValidator;
import cdm.base.math.validation.NonNegativeStepValidator;
import cdm.base.math.validation.exists.NonNegativeStepOnlyExistsValidator;
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
@RosettaMeta(model=NonNegativeStep.class)
public class NonNegativeStepMeta implements RosettaMetaData<NonNegativeStep> {

	@Override
	public List<Validator<? super NonNegativeStep>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.math.validation.datarule.NonNegativeStepStepValue.class)
		);
	}
	
	@Override
	public List<Function<? super NonNegativeStep, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super NonNegativeStep> validator() {
		return new NonNegativeStepValidator();
	}

	@Override
	public Validator<? super NonNegativeStep> typeFormatValidator() {
		return new NonNegativeStepTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super NonNegativeStep, Set<String>> onlyExistsValidator() {
		return new NonNegativeStepOnlyExistsValidator();
	}
}
