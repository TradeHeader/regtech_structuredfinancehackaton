package cdm.observable.asset.meta;

import cdm.observable.asset.ExchangeRate;
import cdm.observable.asset.validation.ExchangeRateTypeFormatValidator;
import cdm.observable.asset.validation.ExchangeRateValidator;
import cdm.observable.asset.validation.exists.ExchangeRateOnlyExistsValidator;
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
@RosettaMeta(model=ExchangeRate.class)
public class ExchangeRateMeta implements RosettaMetaData<ExchangeRate> {

	@Override
	public List<Validator<? super ExchangeRate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ExchangeRate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ExchangeRate> validator() {
		return new ExchangeRateValidator();
	}

	@Override
	public Validator<? super ExchangeRate> typeFormatValidator() {
		return new ExchangeRateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ExchangeRate, Set<String>> onlyExistsValidator() {
		return new ExchangeRateOnlyExistsValidator();
	}
}
