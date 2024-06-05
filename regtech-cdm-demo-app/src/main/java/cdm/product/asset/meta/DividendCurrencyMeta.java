package cdm.product.asset.meta;

import cdm.product.asset.DividendCurrency;
import cdm.product.asset.validation.DividendCurrencyTypeFormatValidator;
import cdm.product.asset.validation.DividendCurrencyValidator;
import cdm.product.asset.validation.exists.DividendCurrencyOnlyExistsValidator;
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
@RosettaMeta(model=DividendCurrency.class)
public class DividendCurrencyMeta implements RosettaMetaData<DividendCurrency> {

	@Override
	public List<Validator<? super DividendCurrency>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.DividendCurrencyOneOf0.class)
		);
	}
	
	@Override
	public List<Function<? super DividendCurrency, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DividendCurrency> validator() {
		return new DividendCurrencyValidator();
	}

	@Override
	public Validator<? super DividendCurrency> typeFormatValidator() {
		return new DividendCurrencyTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DividendCurrency, Set<String>> onlyExistsValidator() {
		return new DividendCurrencyOnlyExistsValidator();
	}
}
