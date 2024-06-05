package cdm.legaldocumentation.common.meta;

import cdm.legaldocumentation.common.ClosedState;
import cdm.legaldocumentation.common.validation.ClosedStateTypeFormatValidator;
import cdm.legaldocumentation.common.validation.ClosedStateValidator;
import cdm.legaldocumentation.common.validation.exists.ClosedStateOnlyExistsValidator;
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
@RosettaMeta(model=ClosedState.class)
public class ClosedStateMeta implements RosettaMetaData<ClosedState> {

	@Override
	public List<Validator<? super ClosedState>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ClosedState, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ClosedState> validator() {
		return new ClosedStateValidator();
	}

	@Override
	public Validator<? super ClosedState> typeFormatValidator() {
		return new ClosedStateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ClosedState, Set<String>> onlyExistsValidator() {
		return new ClosedStateOnlyExistsValidator();
	}
}
