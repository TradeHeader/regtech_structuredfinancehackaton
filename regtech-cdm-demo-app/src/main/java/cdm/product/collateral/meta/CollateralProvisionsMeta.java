package cdm.product.collateral.meta;

import cdm.product.collateral.CollateralProvisions;
import cdm.product.collateral.validation.CollateralProvisionsTypeFormatValidator;
import cdm.product.collateral.validation.CollateralProvisionsValidator;
import cdm.product.collateral.validation.exists.CollateralProvisionsOnlyExistsValidator;
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
@RosettaMeta(model=CollateralProvisions.class)
public class CollateralProvisionsMeta implements RosettaMetaData<CollateralProvisions> {

	@Override
	public List<Validator<? super CollateralProvisions>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CollateralProvisions, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralProvisions> validator() {
		return new CollateralProvisionsValidator();
	}

	@Override
	public Validator<? super CollateralProvisions> typeFormatValidator() {
		return new CollateralProvisionsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralProvisions, Set<String>> onlyExistsValidator() {
		return new CollateralProvisionsOnlyExistsValidator();
	}
}
