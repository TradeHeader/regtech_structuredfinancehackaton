package cdm.observable.asset.meta;

import cdm.observable.asset.PerformanceValuationDates;
import cdm.observable.asset.validation.PerformanceValuationDatesTypeFormatValidator;
import cdm.observable.asset.validation.PerformanceValuationDatesValidator;
import cdm.observable.asset.validation.exists.PerformanceValuationDatesOnlyExistsValidator;
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
@RosettaMeta(model=PerformanceValuationDates.class)
public class PerformanceValuationDatesMeta implements RosettaMetaData<PerformanceValuationDates> {

	@Override
	public List<Validator<? super PerformanceValuationDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PerformanceValuationDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PerformanceValuationDates> validator() {
		return new PerformanceValuationDatesValidator();
	}

	@Override
	public Validator<? super PerformanceValuationDates> typeFormatValidator() {
		return new PerformanceValuationDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PerformanceValuationDates, Set<String>> onlyExistsValidator() {
		return new PerformanceValuationDatesOnlyExistsValidator();
	}
}
