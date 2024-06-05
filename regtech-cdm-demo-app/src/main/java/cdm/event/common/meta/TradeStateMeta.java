package cdm.event.common.meta;

import cdm.event.common.TradeState;
import cdm.event.common.validation.TradeStateTypeFormatValidator;
import cdm.event.common.validation.TradeStateValidator;
import cdm.event.common.validation.exists.TradeStateOnlyExistsValidator;
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
@RosettaMeta(model=TradeState.class)
public class TradeStateMeta implements RosettaMetaData<TradeState> {

	@Override
	public List<Validator<? super TradeState>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super TradeState, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TradeState> validator() {
		return new TradeStateValidator();
	}

	@Override
	public Validator<? super TradeState> typeFormatValidator() {
		return new TradeStateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TradeState, Set<String>> onlyExistsValidator() {
		return new TradeStateOnlyExistsValidator();
	}
}
