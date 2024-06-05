package cdm.product.collateral.meta;

import cdm.product.collateral.DistributionAndInterestPayment;
import cdm.product.collateral.validation.DistributionAndInterestPaymentTypeFormatValidator;
import cdm.product.collateral.validation.DistributionAndInterestPaymentValidator;
import cdm.product.collateral.validation.exists.DistributionAndInterestPaymentOnlyExistsValidator;
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
@RosettaMeta(model=DistributionAndInterestPayment.class)
public class DistributionAndInterestPaymentMeta implements RosettaMetaData<DistributionAndInterestPayment> {

	@Override
	public List<Validator<? super DistributionAndInterestPayment>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DistributionAndInterestPayment, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DistributionAndInterestPayment> validator() {
		return new DistributionAndInterestPaymentValidator();
	}

	@Override
	public Validator<? super DistributionAndInterestPayment> typeFormatValidator() {
		return new DistributionAndInterestPaymentTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DistributionAndInterestPayment, Set<String>> onlyExistsValidator() {
		return new DistributionAndInterestPaymentOnlyExistsValidator();
	}
}
