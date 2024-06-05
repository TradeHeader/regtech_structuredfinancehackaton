package cdm.observable.asset.meta;

import cdm.observable.asset.InterestRateCurve;
import cdm.observable.asset.validation.InterestRateCurveTypeFormatValidator;
import cdm.observable.asset.validation.InterestRateCurveValidator;
import cdm.observable.asset.validation.exists.InterestRateCurveOnlyExistsValidator;
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
@RosettaMeta(model=InterestRateCurve.class)
public class InterestRateCurveMeta implements RosettaMetaData<InterestRateCurve> {

	@Override
	public List<Validator<? super InterestRateCurve>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super InterestRateCurve, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super InterestRateCurve> validator() {
		return new InterestRateCurveValidator();
	}

	@Override
	public Validator<? super InterestRateCurve> typeFormatValidator() {
		return new InterestRateCurveTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super InterestRateCurve, Set<String>> onlyExistsValidator() {
		return new InterestRateCurveOnlyExistsValidator();
	}
}
