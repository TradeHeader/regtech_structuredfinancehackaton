package cdm.product.asset.floatingrate.meta;

import cdm.product.asset.floatingrate.FloatingRateProcessingDetails;
import cdm.product.asset.floatingrate.validation.FloatingRateProcessingDetailsTypeFormatValidator;
import cdm.product.asset.floatingrate.validation.FloatingRateProcessingDetailsValidator;
import cdm.product.asset.floatingrate.validation.exists.FloatingRateProcessingDetailsOnlyExistsValidator;
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
@RosettaMeta(model=FloatingRateProcessingDetails.class)
public class FloatingRateProcessingDetailsMeta implements RosettaMetaData<FloatingRateProcessingDetails> {

	@Override
	public List<Validator<? super FloatingRateProcessingDetails>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingRateProcessingDetails, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingRateProcessingDetails> validator() {
		return new FloatingRateProcessingDetailsValidator();
	}

	@Override
	public Validator<? super FloatingRateProcessingDetails> typeFormatValidator() {
		return new FloatingRateProcessingDetailsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingRateProcessingDetails, Set<String>> onlyExistsValidator() {
		return new FloatingRateProcessingDetailsOnlyExistsValidator();
	}
}
