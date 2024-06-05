package cdm.observable.asset.meta;

import cdm.observable.asset.FxRate;
import cdm.observable.asset.validation.FxRateTypeFormatValidator;
import cdm.observable.asset.validation.FxRateValidator;
import cdm.observable.asset.validation.exists.FxRateOnlyExistsValidator;
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
@RosettaMeta(model=FxRate.class)
public class FxRateMeta implements RosettaMetaData<FxRate> {

	@Override
	public List<Validator<? super FxRate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FxRate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FxRate> validator() {
		return new FxRateValidator();
	}

	@Override
	public Validator<? super FxRate> typeFormatValidator() {
		return new FxRateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FxRate, Set<String>> onlyExistsValidator() {
		return new FxRateOnlyExistsValidator();
	}
}
