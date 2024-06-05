package cdm.product.collateral.meta;

import cdm.product.collateral.CollateralInterestParameters;
import cdm.product.collateral.validation.CollateralInterestParametersTypeFormatValidator;
import cdm.product.collateral.validation.CollateralInterestParametersValidator;
import cdm.product.collateral.validation.exists.CollateralInterestParametersOnlyExistsValidator;
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
@RosettaMeta(model=CollateralInterestParameters.class)
public class CollateralInterestParametersMeta implements RosettaMetaData<CollateralInterestParameters> {

	@Override
	public List<Validator<? super CollateralInterestParameters>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CollateralInterestParameters, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralInterestParameters> validator() {
		return new CollateralInterestParametersValidator();
	}

	@Override
	public Validator<? super CollateralInterestParameters> typeFormatValidator() {
		return new CollateralInterestParametersTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralInterestParameters, Set<String>> onlyExistsValidator() {
		return new CollateralInterestParametersOnlyExistsValidator();
	}
}
