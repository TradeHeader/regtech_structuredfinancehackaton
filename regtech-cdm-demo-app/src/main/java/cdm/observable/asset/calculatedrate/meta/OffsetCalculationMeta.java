package cdm.observable.asset.calculatedrate.meta;

import cdm.observable.asset.calculatedrate.OffsetCalculation;
import cdm.observable.asset.calculatedrate.validation.OffsetCalculationTypeFormatValidator;
import cdm.observable.asset.calculatedrate.validation.OffsetCalculationValidator;
import cdm.observable.asset.calculatedrate.validation.exists.OffsetCalculationOnlyExistsValidator;
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
@RosettaMeta(model=OffsetCalculation.class)
public class OffsetCalculationMeta implements RosettaMetaData<OffsetCalculation> {

	@Override
	public List<Validator<? super OffsetCalculation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super OffsetCalculation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super OffsetCalculation> validator() {
		return new OffsetCalculationValidator();
	}

	@Override
	public Validator<? super OffsetCalculation> typeFormatValidator() {
		return new OffsetCalculationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super OffsetCalculation, Set<String>> onlyExistsValidator() {
		return new OffsetCalculationOnlyExistsValidator();
	}
}
