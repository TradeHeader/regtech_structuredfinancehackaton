package cdm.product.common.schedule.meta;

import cdm.product.common.schedule.ObservationDates;
import cdm.product.common.schedule.validation.ObservationDatesTypeFormatValidator;
import cdm.product.common.schedule.validation.ObservationDatesValidator;
import cdm.product.common.schedule.validation.exists.ObservationDatesOnlyExistsValidator;
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
@RosettaMeta(model=ObservationDates.class)
public class ObservationDatesMeta implements RosettaMetaData<ObservationDates> {

	@Override
	public List<Validator<? super ObservationDates>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ObservationDates, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ObservationDates> validator() {
		return new ObservationDatesValidator();
	}

	@Override
	public Validator<? super ObservationDates> typeFormatValidator() {
		return new ObservationDatesTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ObservationDates, Set<String>> onlyExistsValidator() {
		return new ObservationDatesOnlyExistsValidator();
	}
}
