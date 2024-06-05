package cdm.product.template.meta;

import cdm.product.template.OptionalEarlyTermination;
import cdm.product.template.validation.OptionalEarlyTerminationTypeFormatValidator;
import cdm.product.template.validation.OptionalEarlyTerminationValidator;
import cdm.product.template.validation.exists.OptionalEarlyTerminationOnlyExistsValidator;
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
@RosettaMeta(model=OptionalEarlyTermination.class)
public class OptionalEarlyTerminationMeta implements RosettaMetaData<OptionalEarlyTermination> {

	@Override
	public List<Validator<? super OptionalEarlyTermination>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.OptionalEarlyTerminationExerciseChoice.class),
			factory.create(cdm.product.template.validation.datarule.OptionalEarlyTerminationOptionalEarlyTerminationExerciseNoticeReceiverParty.class),
			factory.create(cdm.product.template.validation.datarule.OptionalEarlyTerminationMandatoryEarlyTerminationCalculationAgent.class)
		);
	}
	
	@Override
	public List<Function<? super OptionalEarlyTermination, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super OptionalEarlyTermination> validator() {
		return new OptionalEarlyTerminationValidator();
	}

	@Override
	public Validator<? super OptionalEarlyTermination> typeFormatValidator() {
		return new OptionalEarlyTerminationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super OptionalEarlyTermination, Set<String>> onlyExistsValidator() {
		return new OptionalEarlyTerminationOnlyExistsValidator();
	}
}
