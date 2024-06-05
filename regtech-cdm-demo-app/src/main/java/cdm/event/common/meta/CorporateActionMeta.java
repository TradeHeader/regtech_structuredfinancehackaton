package cdm.event.common.meta;

import cdm.event.common.CorporateAction;
import cdm.event.common.validation.CorporateActionTypeFormatValidator;
import cdm.event.common.validation.CorporateActionValidator;
import cdm.event.common.validation.exists.CorporateActionOnlyExistsValidator;
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
@RosettaMeta(model=CorporateAction.class)
public class CorporateActionMeta implements RosettaMetaData<CorporateAction> {

	@Override
	public List<Validator<? super CorporateAction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CorporateAction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CorporateAction> validator() {
		return new CorporateActionValidator();
	}

	@Override
	public Validator<? super CorporateAction> typeFormatValidator() {
		return new CorporateActionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CorporateAction, Set<String>> onlyExistsValidator() {
		return new CorporateActionOnlyExistsValidator();
	}
}
