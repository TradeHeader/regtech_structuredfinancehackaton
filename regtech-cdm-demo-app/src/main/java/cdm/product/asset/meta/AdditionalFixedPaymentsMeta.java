package cdm.product.asset.meta;

import cdm.product.asset.AdditionalFixedPayments;
import cdm.product.asset.validation.AdditionalFixedPaymentsTypeFormatValidator;
import cdm.product.asset.validation.AdditionalFixedPaymentsValidator;
import cdm.product.asset.validation.exists.AdditionalFixedPaymentsOnlyExistsValidator;
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
@RosettaMeta(model=AdditionalFixedPayments.class)
public class AdditionalFixedPaymentsMeta implements RosettaMetaData<AdditionalFixedPayments> {

	@Override
	public List<Validator<? super AdditionalFixedPayments>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AdditionalFixedPayments, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AdditionalFixedPayments> validator() {
		return new AdditionalFixedPaymentsValidator();
	}

	@Override
	public Validator<? super AdditionalFixedPayments> typeFormatValidator() {
		return new AdditionalFixedPaymentsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AdditionalFixedPayments, Set<String>> onlyExistsValidator() {
		return new AdditionalFixedPaymentsOnlyExistsValidator();
	}
}
