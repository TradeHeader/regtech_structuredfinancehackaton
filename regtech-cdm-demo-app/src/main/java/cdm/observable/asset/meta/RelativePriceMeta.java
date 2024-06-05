package cdm.observable.asset.meta;

import cdm.observable.asset.RelativePrice;
import cdm.observable.asset.validation.RelativePriceTypeFormatValidator;
import cdm.observable.asset.validation.RelativePriceValidator;
import cdm.observable.asset.validation.exists.RelativePriceOnlyExistsValidator;
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
@RosettaMeta(model=RelativePrice.class)
public class RelativePriceMeta implements RosettaMetaData<RelativePrice> {

	@Override
	public List<Validator<? super RelativePrice>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super RelativePrice, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super RelativePrice> validator() {
		return new RelativePriceValidator();
	}

	@Override
	public Validator<? super RelativePrice> typeFormatValidator() {
		return new RelativePriceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super RelativePrice, Set<String>> onlyExistsValidator() {
		return new RelativePriceOnlyExistsValidator();
	}
}
