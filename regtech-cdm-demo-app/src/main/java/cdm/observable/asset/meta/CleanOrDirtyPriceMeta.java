package cdm.observable.asset.meta;

import cdm.observable.asset.CleanOrDirtyPrice;
import cdm.observable.asset.validation.CleanOrDirtyPriceTypeFormatValidator;
import cdm.observable.asset.validation.CleanOrDirtyPriceValidator;
import cdm.observable.asset.validation.exists.CleanOrDirtyPriceOnlyExistsValidator;
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
@RosettaMeta(model=CleanOrDirtyPrice.class)
public class CleanOrDirtyPriceMeta implements RosettaMetaData<CleanOrDirtyPrice> {

	@Override
	public List<Validator<? super CleanOrDirtyPrice>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CleanOrDirtyPrice, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CleanOrDirtyPrice> validator() {
		return new CleanOrDirtyPriceValidator();
	}

	@Override
	public Validator<? super CleanOrDirtyPrice> typeFormatValidator() {
		return new CleanOrDirtyPriceTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CleanOrDirtyPrice, Set<String>> onlyExistsValidator() {
		return new CleanOrDirtyPriceOnlyExistsValidator();
	}
}
