package cdm.product.collateral.meta;

import cdm.product.collateral.EligibleCollateralSpecificationInstruction;
import cdm.product.collateral.validation.EligibleCollateralSpecificationInstructionTypeFormatValidator;
import cdm.product.collateral.validation.EligibleCollateralSpecificationInstructionValidator;
import cdm.product.collateral.validation.exists.EligibleCollateralSpecificationInstructionOnlyExistsValidator;
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
@RosettaMeta(model=EligibleCollateralSpecificationInstruction.class)
public class EligibleCollateralSpecificationInstructionMeta implements RosettaMetaData<EligibleCollateralSpecificationInstruction> {

	@Override
	public List<Validator<? super EligibleCollateralSpecificationInstruction>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super EligibleCollateralSpecificationInstruction, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EligibleCollateralSpecificationInstruction> validator() {
		return new EligibleCollateralSpecificationInstructionValidator();
	}

	@Override
	public Validator<? super EligibleCollateralSpecificationInstruction> typeFormatValidator() {
		return new EligibleCollateralSpecificationInstructionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EligibleCollateralSpecificationInstruction, Set<String>> onlyExistsValidator() {
		return new EligibleCollateralSpecificationInstructionOnlyExistsValidator();
	}
}
