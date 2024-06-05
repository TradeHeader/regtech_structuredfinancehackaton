package cdm.observable.asset.meta;

import cdm.observable.asset.TransactedPrice;
import cdm.observable.asset.validation.TransactedPriceTypeFormatValidator;
import cdm.observable.asset.validation.TransactedPriceValidator;
import cdm.observable.asset.validation.exists.TransactedPriceOnlyExistsValidator;
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
@RosettaMeta(model=TransactedPrice.class)
public class TransactedPriceMeta implements RosettaMetaData<TransactedPrice> {

	@Override
	public List<Validator<? super TransactedPrice>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super TransactedPrice, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TransactedPrice> validator() {
		return new TransactedPriceValidator();
	}

	@Override
	public Validator<? super TransactedPrice> typeFormatValidator() {
		return new TransactedPriceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TransactedPrice, Set<String>> onlyExistsValidator() {
		return new TransactedPriceOnlyExistsValidator();
	}
}
