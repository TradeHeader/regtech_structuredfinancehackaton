package cdm.product.collateral.meta;

import cdm.product.collateral.CheckEligibilityResult;
import cdm.product.collateral.validation.CheckEligibilityResultTypeFormatValidator;
import cdm.product.collateral.validation.CheckEligibilityResultValidator;
import cdm.product.collateral.validation.exists.CheckEligibilityResultOnlyExistsValidator;
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
@RosettaMeta(model=CheckEligibilityResult.class)
public class CheckEligibilityResultMeta implements RosettaMetaData<CheckEligibilityResult> {

	@Override
	public List<Validator<? super CheckEligibilityResult>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CheckEligibilityResult, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CheckEligibilityResult> validator() {
		return new CheckEligibilityResultValidator();
	}

	@Override
	public Validator<? super CheckEligibilityResult> typeFormatValidator() {
		return new CheckEligibilityResultTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CheckEligibilityResult, Set<String>> onlyExistsValidator() {
		return new CheckEligibilityResultOnlyExistsValidator();
	}
}
