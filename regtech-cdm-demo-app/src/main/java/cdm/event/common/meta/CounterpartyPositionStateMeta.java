package cdm.event.common.meta;

import cdm.event.common.CounterpartyPositionState;
import cdm.event.common.validation.CounterpartyPositionStateTypeFormatValidator;
import cdm.event.common.validation.CounterpartyPositionStateValidator;
import cdm.event.common.validation.exists.CounterpartyPositionStateOnlyExistsValidator;
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
@RosettaMeta(model=CounterpartyPositionState.class)
public class CounterpartyPositionStateMeta implements RosettaMetaData<CounterpartyPositionState> {

	@Override
	public List<Validator<? super CounterpartyPositionState>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CounterpartyPositionState, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CounterpartyPositionState> validator() {
		return new CounterpartyPositionStateValidator();
	}

	@Override
	public Validator<? super CounterpartyPositionState> typeFormatValidator() {
		return new CounterpartyPositionStateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CounterpartyPositionState, Set<String>> onlyExistsValidator() {
		return new CounterpartyPositionStateOnlyExistsValidator();
	}
}
