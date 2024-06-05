package cdm.event.common.meta;

import cdm.event.common.State;
import cdm.event.common.validation.StateTypeFormatValidator;
import cdm.event.common.validation.StateValidator;
import cdm.event.common.validation.exists.StateOnlyExistsValidator;
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
@RosettaMeta(model=State.class)
public class StateMeta implements RosettaMetaData<State> {

	@Override
	public List<Validator<? super State>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.StateClosedStateExists.class)
		);
	}
	
	@Override
	public List<Function<? super State, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super State> validator() {
		return new StateValidator();
	}

	@Override
	public Validator<? super State> typeFormatValidator() {
		return new StateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super State, Set<String>> onlyExistsValidator() {
		return new StateOnlyExistsValidator();
	}
}
