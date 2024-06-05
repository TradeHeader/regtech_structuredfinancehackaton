package cdm.observable.asset.meta;

import cdm.observable.asset.CrossRate;
import cdm.observable.asset.validation.CrossRateTypeFormatValidator;
import cdm.observable.asset.validation.CrossRateValidator;
import cdm.observable.asset.validation.exists.CrossRateOnlyExistsValidator;
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
@RosettaMeta(model=CrossRate.class)
public class CrossRateMeta implements RosettaMetaData<CrossRate> {

	@Override
	public List<Validator<? super CrossRate>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.asset.validation.datarule.CrossRateCrossRate.class)
		);
	}
	
	@Override
	public List<Function<? super CrossRate, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CrossRate> validator() {
		return new CrossRateValidator();
	}

	@Override
	public Validator<? super CrossRate> typeFormatValidator() {
		return new CrossRateTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CrossRate, Set<String>> onlyExistsValidator() {
		return new CrossRateOnlyExistsValidator();
	}
}
