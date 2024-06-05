package cdm.observable.event.meta;

import cdm.observable.event.CreditEvents;
import cdm.observable.event.validation.CreditEventsTypeFormatValidator;
import cdm.observable.event.validation.CreditEventsValidator;
import cdm.observable.event.validation.exists.CreditEventsOnlyExistsValidator;
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
@RosettaMeta(model=CreditEvents.class)
public class CreditEventsMeta implements RosettaMetaData<CreditEvents> {

	@Override
	public List<Validator<? super CreditEvents>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CreditEvents, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CreditEvents> validator() {
		return new CreditEventsValidator();
	}

	@Override
	public Validator<? super CreditEvents> typeFormatValidator() {
		return new CreditEventsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CreditEvents, Set<String>> onlyExistsValidator() {
		return new CreditEventsOnlyExistsValidator();
	}
}
