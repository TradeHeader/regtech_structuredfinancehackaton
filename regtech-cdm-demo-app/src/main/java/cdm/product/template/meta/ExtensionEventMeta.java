package cdm.product.template.meta;

import cdm.product.template.ExtensionEvent;
import cdm.product.template.validation.ExtensionEventTypeFormatValidator;
import cdm.product.template.validation.ExtensionEventValidator;
import cdm.product.template.validation.exists.ExtensionEventOnlyExistsValidator;
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
@RosettaMeta(model=ExtensionEvent.class)
public class ExtensionEventMeta implements RosettaMetaData<ExtensionEvent> {

	@Override
	public List<Validator<? super ExtensionEvent>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.template.validation.datarule.ExtensionEventFpMLIrd42.class)
		);
	}
	
	@Override
	public List<Function<? super ExtensionEvent, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ExtensionEvent> validator() {
		return new ExtensionEventValidator();
	}

	@Override
	public Validator<? super ExtensionEvent> typeFormatValidator() {
		return new ExtensionEventTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ExtensionEvent, Set<String>> onlyExistsValidator() {
		return new ExtensionEventOnlyExistsValidator();
	}
}
