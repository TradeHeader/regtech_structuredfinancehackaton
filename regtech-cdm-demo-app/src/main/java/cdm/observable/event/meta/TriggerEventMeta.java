package cdm.observable.event.meta;

import cdm.observable.event.TriggerEvent;
import cdm.observable.event.validation.TriggerEventTypeFormatValidator;
import cdm.observable.event.validation.TriggerEventValidator;
import cdm.observable.event.validation.exists.TriggerEventOnlyExistsValidator;
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
@RosettaMeta(model=TriggerEvent.class)
public class TriggerEventMeta implements RosettaMetaData<TriggerEvent> {

	@Override
	public List<Validator<? super TriggerEvent>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super TriggerEvent, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TriggerEvent> validator() {
		return new TriggerEventValidator();
	}

	@Override
	public Validator<? super TriggerEvent> typeFormatValidator() {
		return new TriggerEventTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TriggerEvent, Set<String>> onlyExistsValidator() {
		return new TriggerEventOnlyExistsValidator();
	}
}
