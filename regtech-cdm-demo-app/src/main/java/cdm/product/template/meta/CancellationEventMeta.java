package cdm.product.template.meta;

import cdm.product.template.CancellationEvent;
import cdm.product.template.validation.CancellationEventTypeFormatValidator;
import cdm.product.template.validation.CancellationEventValidator;
import cdm.product.template.validation.exists.CancellationEventOnlyExistsValidator;
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
@RosettaMeta(model=CancellationEvent.class)
public class CancellationEventMeta implements RosettaMetaData<CancellationEvent> {

	@Override
	public List<Validator<? super CancellationEvent>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CancellationEvent, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CancellationEvent> validator() {
		return new CancellationEventValidator();
	}

	@Override
	public Validator<? super CancellationEvent> typeFormatValidator() {
		return new CancellationEventTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CancellationEvent, Set<String>> onlyExistsValidator() {
		return new CancellationEventOnlyExistsValidator();
	}
}
