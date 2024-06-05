package cdm.product.asset.meta;

import cdm.product.asset.FloatingAmountEvents;
import cdm.product.asset.validation.FloatingAmountEventsTypeFormatValidator;
import cdm.product.asset.validation.FloatingAmountEventsValidator;
import cdm.product.asset.validation.exists.FloatingAmountEventsOnlyExistsValidator;
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
@RosettaMeta(model=FloatingAmountEvents.class)
public class FloatingAmountEventsMeta implements RosettaMetaData<FloatingAmountEvents> {

	@Override
	public List<Validator<? super FloatingAmountEvents>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super FloatingAmountEvents, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super FloatingAmountEvents> validator() {
		return new FloatingAmountEventsValidator();
	}

	@Override
	public Validator<? super FloatingAmountEvents> typeFormatValidator() {
		return new FloatingAmountEventsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super FloatingAmountEvents, Set<String>> onlyExistsValidator() {
		return new FloatingAmountEventsOnlyExistsValidator();
	}
}
