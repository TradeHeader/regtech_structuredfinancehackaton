package cdm.product.collateral.meta;

import cdm.product.collateral.Collateral;
import cdm.product.collateral.validation.CollateralTypeFormatValidator;
import cdm.product.collateral.validation.CollateralValidator;
import cdm.product.collateral.validation.exists.CollateralOnlyExistsValidator;
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
@RosettaMeta(model=Collateral.class)
public class CollateralMeta implements RosettaMetaData<Collateral> {

	@Override
	public List<Validator<? super Collateral>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.collateral.validation.datarule.CollateralCollateralExists.class),
			factory.create(cdm.product.collateral.validation.datarule.CollateralCollateralProvisions.class),
			factory.create(cdm.product.collateral.validation.datarule.CollateralCollateralchoice.class)
		);
	}
	
	@Override
	public List<Function<? super Collateral, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Collateral> validator() {
		return new CollateralValidator();
	}

	@Override
	public Validator<? super Collateral> typeFormatValidator() {
		return new CollateralTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Collateral, Set<String>> onlyExistsValidator() {
		return new CollateralOnlyExistsValidator();
	}
}
