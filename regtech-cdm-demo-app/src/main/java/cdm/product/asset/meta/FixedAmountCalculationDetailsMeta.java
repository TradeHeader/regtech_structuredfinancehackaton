package cdm.product.asset.meta;

import cdm.product.asset.FixedAmountCalculationDetails;
import cdm.product.asset.validation.FixedAmountCalculationDetailsTypeFormatValidator;
import cdm.product.asset.validation.FixedAmountCalculationDetailsValidator;
import cdm.product.asset.validation.exists.FixedAmountCalculationDetailsOnlyExistsValidator;
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
@RosettaMeta(model=FixedAmountCalculationDetails.class)
public class FixedAmountCalculationDetailsMeta implements RosettaMetaData<FixedAmountCalculationDetails> {

	@Override
	public List<Validator<? super FixedAmountCalculationDetails>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FixedAmountCalculationDetails, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FixedAmountCalculationDetails> validator() {
		return new FixedAmountCalculationDetailsValidator();
	}

	@Override
	public Validator<? super FixedAmountCalculationDetails> typeFormatValidator() {
		return new FixedAmountCalculationDetailsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FixedAmountCalculationDetails, Set<String>> onlyExistsValidator() {
		return new FixedAmountCalculationDetailsOnlyExistsValidator();
	}
}
