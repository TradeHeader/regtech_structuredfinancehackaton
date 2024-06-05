package cdm.product.common.settlement.meta;

import cdm.product.common.settlement.PrincipalPayments;
import cdm.product.common.settlement.validation.PrincipalPaymentsTypeFormatValidator;
import cdm.product.common.settlement.validation.PrincipalPaymentsValidator;
import cdm.product.common.settlement.validation.exists.PrincipalPaymentsOnlyExistsValidator;
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
@RosettaMeta(model=PrincipalPayments.class)
public class PrincipalPaymentsMeta implements RosettaMetaData<PrincipalPayments> {

	@Override
	public List<Validator<? super PrincipalPayments>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PrincipalPayments, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PrincipalPayments> validator() {
		return new PrincipalPaymentsValidator();
	}

	@Override
	public Validator<? super PrincipalPayments> typeFormatValidator() {
		return new PrincipalPaymentsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PrincipalPayments, Set<String>> onlyExistsValidator() {
		return new PrincipalPaymentsOnlyExistsValidator();
	}
}
