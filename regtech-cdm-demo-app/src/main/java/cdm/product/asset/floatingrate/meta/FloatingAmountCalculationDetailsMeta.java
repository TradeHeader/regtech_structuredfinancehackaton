package cdm.product.asset.floatingrate.meta;

import cdm.product.asset.floatingrate.FloatingAmountCalculationDetails;
import cdm.product.asset.floatingrate.validation.FloatingAmountCalculationDetailsTypeFormatValidator;
import cdm.product.asset.floatingrate.validation.FloatingAmountCalculationDetailsValidator;
import cdm.product.asset.floatingrate.validation.exists.FloatingAmountCalculationDetailsOnlyExistsValidator;
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
@RosettaMeta(model=FloatingAmountCalculationDetails.class)
public class FloatingAmountCalculationDetailsMeta implements RosettaMetaData<FloatingAmountCalculationDetails> {

	@Override
	public List<Validator<? super FloatingAmountCalculationDetails>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingAmountCalculationDetails, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingAmountCalculationDetails> validator() {
		return new FloatingAmountCalculationDetailsValidator();
	}

	@Override
	public Validator<? super FloatingAmountCalculationDetails> typeFormatValidator() {
		return new FloatingAmountCalculationDetailsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingAmountCalculationDetails, Set<String>> onlyExistsValidator() {
		return new FloatingAmountCalculationDetailsOnlyExistsValidator();
	}
}
