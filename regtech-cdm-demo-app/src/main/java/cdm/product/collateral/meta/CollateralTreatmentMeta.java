package cdm.product.collateral.meta;

import cdm.product.collateral.CollateralTreatment;
import cdm.product.collateral.validation.CollateralTreatmentTypeFormatValidator;
import cdm.product.collateral.validation.CollateralTreatmentValidator;
import cdm.product.collateral.validation.exists.CollateralTreatmentOnlyExistsValidator;
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
@RosettaMeta(model=CollateralTreatment.class)
public class CollateralTreatmentMeta implements RosettaMetaData<CollateralTreatment> {

	@Override
	public List<Validator<? super CollateralTreatment>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CollateralTreatment, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CollateralTreatment> validator() {
		return new CollateralTreatmentValidator();
	}

	@Override
	public Validator<? super CollateralTreatment> typeFormatValidator() {
		return new CollateralTreatmentTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CollateralTreatment, Set<String>> onlyExistsValidator() {
		return new CollateralTreatmentOnlyExistsValidator();
	}
}
