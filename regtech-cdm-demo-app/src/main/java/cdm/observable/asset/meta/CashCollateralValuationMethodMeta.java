package cdm.observable.asset.meta;

import cdm.observable.asset.CashCollateralValuationMethod;
import cdm.observable.asset.validation.CashCollateralValuationMethodTypeFormatValidator;
import cdm.observable.asset.validation.CashCollateralValuationMethodValidator;
import cdm.observable.asset.validation.exists.CashCollateralValuationMethodOnlyExistsValidator;
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
@RosettaMeta(model=CashCollateralValuationMethod.class)
public class CashCollateralValuationMethodMeta implements RosettaMetaData<CashCollateralValuationMethod> {

	@Override
	public List<Validator<? super CashCollateralValuationMethod>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CashCollateralValuationMethod, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CashCollateralValuationMethod> validator() {
		return new CashCollateralValuationMethodValidator();
	}

	@Override
	public Validator<? super CashCollateralValuationMethod> typeFormatValidator() {
		return new CashCollateralValuationMethodTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CashCollateralValuationMethod, Set<String>> onlyExistsValidator() {
		return new CashCollateralValuationMethodOnlyExistsValidator();
	}
}
