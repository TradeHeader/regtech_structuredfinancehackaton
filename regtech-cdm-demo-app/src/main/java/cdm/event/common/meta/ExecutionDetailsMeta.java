package cdm.event.common.meta;

import cdm.event.common.ExecutionDetails;
import cdm.event.common.validation.ExecutionDetailsTypeFormatValidator;
import cdm.event.common.validation.ExecutionDetailsValidator;
import cdm.event.common.validation.exists.ExecutionDetailsOnlyExistsValidator;
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
@RosettaMeta(model=ExecutionDetails.class)
public class ExecutionDetailsMeta implements RosettaMetaData<ExecutionDetails> {

	@Override
	public List<Validator<? super ExecutionDetails>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.ExecutionDetailsExecutionVenue.class)
		);
	}
	
	@Override
	public List<Function<? super ExecutionDetails, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ExecutionDetails> validator() {
		return new ExecutionDetailsValidator();
	}

	@Override
	public Validator<? super ExecutionDetails> typeFormatValidator() {
		return new ExecutionDetailsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ExecutionDetails, Set<String>> onlyExistsValidator() {
		return new ExecutionDetailsOnlyExistsValidator();
	}
}
