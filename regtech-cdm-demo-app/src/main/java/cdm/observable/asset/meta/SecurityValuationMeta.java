package cdm.observable.asset.meta;

import cdm.observable.asset.SecurityValuation;
import cdm.observable.asset.validation.SecurityValuationTypeFormatValidator;
import cdm.observable.asset.validation.SecurityValuationValidator;
import cdm.observable.asset.validation.exists.SecurityValuationOnlyExistsValidator;
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
@RosettaMeta(model=SecurityValuation.class)
public class SecurityValuationMeta implements RosettaMetaData<SecurityValuation> {

	@Override
	public List<Validator<? super SecurityValuation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SecurityValuation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SecurityValuation> validator() {
		return new SecurityValuationValidator();
	}

	@Override
	public Validator<? super SecurityValuation> typeFormatValidator() {
		return new SecurityValuationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SecurityValuation, Set<String>> onlyExistsValidator() {
		return new SecurityValuationOnlyExistsValidator();
	}
}
