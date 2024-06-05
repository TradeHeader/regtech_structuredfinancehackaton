package cdm.observable.asset.meta;

import cdm.observable.asset.ValuationDates;
import cdm.observable.asset.validation.ValuationDatesTypeFormatValidator;
import cdm.observable.asset.validation.ValuationDatesValidator;
import cdm.observable.asset.validation.exists.ValuationDatesOnlyExistsValidator;
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
@RosettaMeta(model=ValuationDates.class)
public class ValuationDatesMeta implements RosettaMetaData<ValuationDates> {

	@Override
	public List<Validator<? super ValuationDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ValuationDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ValuationDates> validator() {
		return new ValuationDatesValidator();
	}

	@Override
	public Validator<? super ValuationDates> typeFormatValidator() {
		return new ValuationDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ValuationDates, Set<String>> onlyExistsValidator() {
		return new ValuationDatesOnlyExistsValidator();
	}
}
