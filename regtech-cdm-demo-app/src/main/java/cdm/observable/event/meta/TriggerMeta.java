package cdm.observable.event.meta;

import cdm.observable.event.Trigger;
import cdm.observable.event.validation.TriggerTypeFormatValidator;
import cdm.observable.event.validation.TriggerValidator;
import cdm.observable.event.validation.exists.TriggerOnlyExistsValidator;
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
@RosettaMeta(model=Trigger.class)
public class TriggerMeta implements RosettaMetaData<Trigger> {

	@Override
	public List<Validator<? super Trigger>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.observable.event.validation.datarule.TriggerChoice1.class)
		);
	}
	
	@Override
	public List<Function<? super Trigger, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Trigger> validator() {
		return new TriggerValidator();
	}

	@Override
	public Validator<? super Trigger> typeFormatValidator() {
		return new TriggerTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Trigger, Set<String>> onlyExistsValidator() {
		return new TriggerOnlyExistsValidator();
	}
}
