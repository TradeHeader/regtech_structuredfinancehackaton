package cdm.event.common.meta;

import cdm.event.common.MarginCallResponseAction;
import cdm.event.common.validation.MarginCallResponseActionTypeFormatValidator;
import cdm.event.common.validation.MarginCallResponseActionValidator;
import cdm.event.common.validation.exists.MarginCallResponseActionOnlyExistsValidator;
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
@RosettaMeta(model=MarginCallResponseAction.class)
public class MarginCallResponseActionMeta implements RosettaMetaData<MarginCallResponseAction> {

	@Override
	public List<Validator<? super MarginCallResponseAction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super MarginCallResponseAction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super MarginCallResponseAction> validator() {
		return new MarginCallResponseActionValidator();
	}

	@Override
	public Validator<? super MarginCallResponseAction> typeFormatValidator() {
		return new MarginCallResponseActionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super MarginCallResponseAction, Set<String>> onlyExistsValidator() {
		return new MarginCallResponseActionOnlyExistsValidator();
	}
}
