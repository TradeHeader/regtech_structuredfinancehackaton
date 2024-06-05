package cdm.product.collateral.meta;

import cdm.product.collateral.CollateralInterestHandlingParameters;
import cdm.product.collateral.validation.CollateralInterestHandlingParametersTypeFormatValidator;
import cdm.product.collateral.validation.CollateralInterestHandlingParametersValidator;
import cdm.product.collateral.validation.exists.CollateralInterestHandlingParametersOnlyExistsValidator;
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
@RosettaMeta(model=CollateralInterestHandlingParameters.class)
public class CollateralInterestHandlingParametersMeta implements RosettaMetaData<CollateralInterestHandlingParameters> {

	@Override
	public List<Validator<? super CollateralInterestHandlingParameters>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.collateral.validation.datarule.CollateralInterestHandlingParametersAlternative.class),
			factory.create(cdm.product.collateral.validation.datarule.CollateralInterestHandlingParametersAlternative2.class)
		);
	}
	
	@Override
	public List<Function<? super CollateralInterestHandlingParameters, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralInterestHandlingParameters> validator() {
		return new CollateralInterestHandlingParametersValidator();
	}

	@Override
	public Validator<? super CollateralInterestHandlingParameters> typeFormatValidator() {
		return new CollateralInterestHandlingParametersTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralInterestHandlingParameters, Set<String>> onlyExistsValidator() {
		return new CollateralInterestHandlingParametersOnlyExistsValidator();
	}
}
