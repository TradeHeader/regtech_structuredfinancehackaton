package cdm.observable.asset.calculatedrate.meta;

import cdm.observable.asset.calculatedrate.CalculatedRateObservations;
import cdm.observable.asset.calculatedrate.validation.CalculatedRateObservationsTypeFormatValidator;
import cdm.observable.asset.calculatedrate.validation.CalculatedRateObservationsValidator;
import cdm.observable.asset.calculatedrate.validation.exists.CalculatedRateObservationsOnlyExistsValidator;
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
@RosettaMeta(model=CalculatedRateObservations.class)
public class CalculatedRateObservationsMeta implements RosettaMetaData<CalculatedRateObservations> {

	@Override
	public List<Validator<? super CalculatedRateObservations>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CalculatedRateObservations, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CalculatedRateObservations> validator() {
		return new CalculatedRateObservationsValidator();
	}

	@Override
	public Validator<? super CalculatedRateObservations> typeFormatValidator() {
		return new CalculatedRateObservationsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CalculatedRateObservations, Set<String>> onlyExistsValidator() {
		return new CalculatedRateObservationsOnlyExistsValidator();
	}
}
