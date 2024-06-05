package cdm.event.workflow.meta;

import cdm.event.workflow.EventTimestamp;
import cdm.event.workflow.validation.EventTimestampTypeFormatValidator;
import cdm.event.workflow.validation.EventTimestampValidator;
import cdm.event.workflow.validation.exists.EventTimestampOnlyExistsValidator;
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
@RosettaMeta(model=EventTimestamp.class)
public class EventTimestampMeta implements RosettaMetaData<EventTimestamp> {

	@Override
	public List<Validator<? super EventTimestamp>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super EventTimestamp, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EventTimestamp> validator() {
		return new EventTimestampValidator();
	}

	@Override
	public Validator<? super EventTimestamp> typeFormatValidator() {
		return new EventTimestampTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EventTimestamp, Set<String>> onlyExistsValidator() {
		return new EventTimestampOnlyExistsValidator();
	}
}
