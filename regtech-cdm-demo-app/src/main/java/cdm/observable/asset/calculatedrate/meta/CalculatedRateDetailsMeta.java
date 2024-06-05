package cdm.observable.asset.calculatedrate.meta;

import cdm.observable.asset.calculatedrate.CalculatedRateDetails;
import cdm.observable.asset.calculatedrate.validation.CalculatedRateDetailsTypeFormatValidator;
import cdm.observable.asset.calculatedrate.validation.CalculatedRateDetailsValidator;
import cdm.observable.asset.calculatedrate.validation.exists.CalculatedRateDetailsOnlyExistsValidator;
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
@RosettaMeta(model=CalculatedRateDetails.class)
public class CalculatedRateDetailsMeta implements RosettaMetaData<CalculatedRateDetails> {

	@Override
	public List<Validator<? super CalculatedRateDetails>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CalculatedRateDetails, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalculatedRateDetails> validator() {
		return new CalculatedRateDetailsValidator();
	}

	@Override
	public Validator<? super CalculatedRateDetails> typeFormatValidator() {
		return new CalculatedRateDetailsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalculatedRateDetails, Set<String>> onlyExistsValidator() {
		return new CalculatedRateDetailsOnlyExistsValidator();
	}
}
