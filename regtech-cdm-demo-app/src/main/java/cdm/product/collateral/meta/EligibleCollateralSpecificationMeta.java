package cdm.product.collateral.meta;

import cdm.product.collateral.EligibleCollateralSpecification;
import cdm.product.collateral.validation.EligibleCollateralSpecificationTypeFormatValidator;
import cdm.product.collateral.validation.EligibleCollateralSpecificationValidator;
import cdm.product.collateral.validation.exists.EligibleCollateralSpecificationOnlyExistsValidator;
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
@RosettaMeta(model=EligibleCollateralSpecification.class)
public class EligibleCollateralSpecificationMeta implements RosettaMetaData<EligibleCollateralSpecification> {

	@Override
	public List<Validator<? super EligibleCollateralSpecification>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super EligibleCollateralSpecification, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EligibleCollateralSpecification> validator() {
		return new EligibleCollateralSpecificationValidator();
	}

	@Override
	public Validator<? super EligibleCollateralSpecification> typeFormatValidator() {
		return new EligibleCollateralSpecificationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EligibleCollateralSpecification, Set<String>> onlyExistsValidator() {
		return new EligibleCollateralSpecificationOnlyExistsValidator();
	}
}
