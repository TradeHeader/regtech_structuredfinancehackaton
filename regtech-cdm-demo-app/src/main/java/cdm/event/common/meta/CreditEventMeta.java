package cdm.event.common.meta;

import cdm.event.common.CreditEvent;
import cdm.event.common.validation.CreditEventTypeFormatValidator;
import cdm.event.common.validation.CreditEventValidator;
import cdm.event.common.validation.exists.CreditEventOnlyExistsValidator;
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
@RosettaMeta(model=CreditEvent.class)
public class CreditEventMeta implements RosettaMetaData<CreditEvent> {

	@Override
	public List<Validator<? super CreditEvent>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CreditEvent, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CreditEvent> validator() {
		return new CreditEventValidator();
	}

	@Override
	public Validator<? super CreditEvent> typeFormatValidator() {
		return new CreditEventTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CreditEvent, Set<String>> onlyExistsValidator() {
		return new CreditEventOnlyExistsValidator();
	}
}
