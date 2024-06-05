package cdm.observable.asset.meta;

import cdm.observable.asset.QuotedCurrencyPair;
import cdm.observable.asset.validation.QuotedCurrencyPairTypeFormatValidator;
import cdm.observable.asset.validation.QuotedCurrencyPairValidator;
import cdm.observable.asset.validation.exists.QuotedCurrencyPairOnlyExistsValidator;
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
@RosettaMeta(model=QuotedCurrencyPair.class)
public class QuotedCurrencyPairMeta implements RosettaMetaData<QuotedCurrencyPair> {

	@Override
	public List<Validator<? super QuotedCurrencyPair>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super QuotedCurrencyPair, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super QuotedCurrencyPair> validator() {
		return new QuotedCurrencyPairValidator();
	}

	@Override
	public Validator<? super QuotedCurrencyPair> typeFormatValidator() {
		return new QuotedCurrencyPairTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super QuotedCurrencyPair, Set<String>> onlyExistsValidator() {
		return new QuotedCurrencyPairOnlyExistsValidator();
	}
}
