package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.PaymentDiscounting;
import cdm.product.common.settlement.validation.PaymentDiscountingTypeFormatValidator;
import cdm.product.common.settlement.validation.PaymentDiscountingValidator;
import cdm.product.common.settlement.validation.exists.PaymentDiscountingOnlyExistsValidator;
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
@RosettaMeta(model=PaymentDiscounting.class)
public class PaymentDiscountingMeta implements RosettaMetaData<PaymentDiscounting> {

	@Override
	public List<Validator<? super PaymentDiscounting>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PaymentDiscounting, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PaymentDiscounting> validator() {
		return new PaymentDiscountingValidator();
	}

	@Override
	public Validator<? super PaymentDiscounting> typeFormatValidator() {
		return new PaymentDiscountingTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PaymentDiscounting, Set<String>> onlyExistsValidator() {
		return new PaymentDiscountingOnlyExistsValidator();
	}
}
