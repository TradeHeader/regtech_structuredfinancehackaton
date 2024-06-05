package cdm.product.collateral.meta;

import cdm.product.collateral.CollateralAgreementFloatingRate;
import cdm.product.collateral.validation.CollateralAgreementFloatingRateTypeFormatValidator;
import cdm.product.collateral.validation.CollateralAgreementFloatingRateValidator;
import cdm.product.collateral.validation.exists.CollateralAgreementFloatingRateOnlyExistsValidator;
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
@RosettaMeta(model=CollateralAgreementFloatingRate.class)
public class CollateralAgreementFloatingRateMeta implements RosettaMetaData<CollateralAgreementFloatingRate> {

	@Override
	public List<Validator<? super CollateralAgreementFloatingRate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CollateralAgreementFloatingRate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralAgreementFloatingRate> validator() {
		return new CollateralAgreementFloatingRateValidator();
	}

	@Override
	public Validator<? super CollateralAgreementFloatingRate> typeFormatValidator() {
		return new CollateralAgreementFloatingRateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralAgreementFloatingRate, Set<String>> onlyExistsValidator() {
		return new CollateralAgreementFloatingRateOnlyExistsValidator();
	}
}
