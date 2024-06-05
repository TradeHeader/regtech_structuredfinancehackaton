package cdm.observable.asset.calculatedrate.meta;

import cdm.observable.asset.calculatedrate.ObservationShiftCalculation;
import cdm.observable.asset.calculatedrate.validation.ObservationShiftCalculationTypeFormatValidator;
import cdm.observable.asset.calculatedrate.validation.ObservationShiftCalculationValidator;
import cdm.observable.asset.calculatedrate.validation.exists.ObservationShiftCalculationOnlyExistsValidator;
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
@RosettaMeta(model=ObservationShiftCalculation.class)
public class ObservationShiftCalculationMeta implements RosettaMetaData<ObservationShiftCalculation> {

	@Override
	public List<Validator<? super ObservationShiftCalculation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ObservationShiftCalculation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ObservationShiftCalculation> validator() {
		return new ObservationShiftCalculationValidator();
	}

	@Override
	public Validator<? super ObservationShiftCalculation> typeFormatValidator() {
		return new ObservationShiftCalculationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ObservationShiftCalculation, Set<String>> onlyExistsValidator() {
		return new ObservationShiftCalculationOnlyExistsValidator();
	}
}
