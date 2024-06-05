package cdm.product.collateral.meta;

import cdm.product.collateral.CollateralValuationTreatment;
import cdm.product.collateral.validation.CollateralValuationTreatmentTypeFormatValidator;
import cdm.product.collateral.validation.CollateralValuationTreatmentValidator;
import cdm.product.collateral.validation.exists.CollateralValuationTreatmentOnlyExistsValidator;
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
@RosettaMeta(model=CollateralValuationTreatment.class)
public class CollateralValuationTreatmentMeta implements RosettaMetaData<CollateralValuationTreatment> {

	@Override
	public List<Validator<? super CollateralValuationTreatment>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.collateral.validation.datarule.CollateralValuationTreatmentHaircutPercentage.class),
			factory.create(cdm.product.collateral.validation.datarule.CollateralValuationTreatmentMarginPercentage.class),
			factory.create(cdm.product.collateral.validation.datarule.CollateralValuationTreatmentFxHaircutPercentage.class),
			factory.create(cdm.product.collateral.validation.datarule.CollateralValuationTreatmentAdditionalHaircutPercentage.class),
			factory.create(cdm.product.collateral.validation.datarule.CollateralValuationTreatmentHaircutPercentageOrMarginPercentage.class)
		);
	}
	
	@Override
	public List<Function<? super CollateralValuationTreatment, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralValuationTreatment> validator() {
		return new CollateralValuationTreatmentValidator();
	}

	@Override
	public Validator<? super CollateralValuationTreatment> typeFormatValidator() {
		return new CollateralValuationTreatmentTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralValuationTreatment, Set<String>> onlyExistsValidator() {
		return new CollateralValuationTreatmentOnlyExistsValidator();
	}
}
