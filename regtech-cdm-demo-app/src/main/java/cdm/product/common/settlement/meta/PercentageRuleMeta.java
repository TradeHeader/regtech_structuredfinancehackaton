package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.PercentageRule;
import cdm.product.common.settlement.validation.PercentageRuleTypeFormatValidator;
import cdm.product.common.settlement.validation.PercentageRuleValidator;
import cdm.product.common.settlement.validation.exists.PercentageRuleOnlyExistsValidator;
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
@RosettaMeta(model=PercentageRule.class)
public class PercentageRuleMeta implements RosettaMetaData<PercentageRule> {

	@Override
	public List<Validator<? super PercentageRule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PercentageRule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PercentageRule> validator() {
		return new PercentageRuleValidator();
	}

	@Override
	public Validator<? super PercentageRule> typeFormatValidator() {
		return new PercentageRuleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PercentageRule, Set<String>> onlyExistsValidator() {
		return new PercentageRuleOnlyExistsValidator();
	}
}
