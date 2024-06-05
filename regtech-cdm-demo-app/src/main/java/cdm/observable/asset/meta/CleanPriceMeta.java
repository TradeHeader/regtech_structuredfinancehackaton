package cdm.observable.asset.meta;

import cdm.observable.asset.CleanPrice;
import cdm.observable.asset.validation.CleanPriceTypeFormatValidator;
import cdm.observable.asset.validation.CleanPriceValidator;
import cdm.observable.asset.validation.exists.CleanPriceOnlyExistsValidator;
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
@RosettaMeta(model=CleanPrice.class)
public class CleanPriceMeta implements RosettaMetaData<CleanPrice> {

	@Override
	public List<Validator<? super CleanPrice>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CleanPrice, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CleanPrice> validator() {
		return new CleanPriceValidator();
	}

	@Override
	public Validator<? super CleanPrice> typeFormatValidator() {
		return new CleanPriceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CleanPrice, Set<String>> onlyExistsValidator() {
		return new CleanPriceOnlyExistsValidator();
	}
}
