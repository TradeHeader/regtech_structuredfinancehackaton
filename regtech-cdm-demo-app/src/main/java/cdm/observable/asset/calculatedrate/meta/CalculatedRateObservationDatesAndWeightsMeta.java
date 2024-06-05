package cdm.observable.asset.calculatedrate.meta;

import cdm.observable.asset.calculatedrate.CalculatedRateObservationDatesAndWeights;
import cdm.observable.asset.calculatedrate.validation.CalculatedRateObservationDatesAndWeightsTypeFormatValidator;
import cdm.observable.asset.calculatedrate.validation.CalculatedRateObservationDatesAndWeightsValidator;
import cdm.observable.asset.calculatedrate.validation.exists.CalculatedRateObservationDatesAndWeightsOnlyExistsValidator;
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
@RosettaMeta(model=CalculatedRateObservationDatesAndWeights.class)
public class CalculatedRateObservationDatesAndWeightsMeta implements RosettaMetaData<CalculatedRateObservationDatesAndWeights> {

	@Override
	public List<Validator<? super CalculatedRateObservationDatesAndWeights>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CalculatedRateObservationDatesAndWeights, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalculatedRateObservationDatesAndWeights> validator() {
		return new CalculatedRateObservationDatesAndWeightsValidator();
	}

	@Override
	public Validator<? super CalculatedRateObservationDatesAndWeights> typeFormatValidator() {
		return new CalculatedRateObservationDatesAndWeightsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalculatedRateObservationDatesAndWeights, Set<String>> onlyExistsValidator() {
		return new CalculatedRateObservationDatesAndWeightsOnlyExistsValidator();
	}
}
