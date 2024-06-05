package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.PaymentRule;
import cdm.product.common.settlement.validation.PaymentRuleTypeFormatValidator;
import cdm.product.common.settlement.validation.PaymentRuleValidator;
import cdm.product.common.settlement.validation.exists.PaymentRuleOnlyExistsValidator;
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
@RosettaMeta(model=PaymentRule.class)
public class PaymentRuleMeta implements RosettaMetaData<PaymentRule> {

	@Override
	public List<Validator<? super PaymentRule>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PaymentRule, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PaymentRule> validator() {
		return new PaymentRuleValidator();
	}

	@Override
	public Validator<? super PaymentRule> typeFormatValidator() {
		return new PaymentRuleTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PaymentRule, Set<String>> onlyExistsValidator() {
		return new PaymentRuleOnlyExistsValidator();
	}
}
