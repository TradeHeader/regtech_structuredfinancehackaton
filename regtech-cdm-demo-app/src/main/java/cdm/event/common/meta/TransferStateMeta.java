package cdm.event.common.meta;

import cdm.event.common.TransferState;
import cdm.event.common.validation.TransferStateTypeFormatValidator;
import cdm.event.common.validation.TransferStateValidator;
import cdm.event.common.validation.exists.TransferStateOnlyExistsValidator;
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
@RosettaMeta(model=TransferState.class)
public class TransferStateMeta implements RosettaMetaData<TransferState> {

	@Override
	public List<Validator<? super TransferState>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super TransferState, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TransferState> validator() {
		return new TransferStateValidator();
	}

	@Override
	public Validator<? super TransferState> typeFormatValidator() {
		return new TransferStateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TransferState, Set<String>> onlyExistsValidator() {
		return new TransferStateOnlyExistsValidator();
	}
}
