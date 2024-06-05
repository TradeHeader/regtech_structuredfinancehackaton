package cdm.event.common.meta;

import cdm.event.common.CounterpartyPositionBusinessEvent;
import cdm.event.common.validation.CounterpartyPositionBusinessEventTypeFormatValidator;
import cdm.event.common.validation.CounterpartyPositionBusinessEventValidator;
import cdm.event.common.validation.exists.CounterpartyPositionBusinessEventOnlyExistsValidator;
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
@RosettaMeta(model=CounterpartyPositionBusinessEvent.class)
public class CounterpartyPositionBusinessEventMeta implements RosettaMetaData<CounterpartyPositionBusinessEvent> {

	@Override
	public List<Validator<? super CounterpartyPositionBusinessEvent>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CounterpartyPositionBusinessEvent, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CounterpartyPositionBusinessEvent> validator() {
		return new CounterpartyPositionBusinessEventValidator();
	}

	@Override
	public Validator<? super CounterpartyPositionBusinessEvent> typeFormatValidator() {
		return new CounterpartyPositionBusinessEventTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CounterpartyPositionBusinessEvent, Set<String>> onlyExistsValidator() {
		return new CounterpartyPositionBusinessEventOnlyExistsValidator();
	}
}
