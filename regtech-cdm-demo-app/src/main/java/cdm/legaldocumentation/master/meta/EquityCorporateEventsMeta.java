package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.EquityCorporateEvents;
import cdm.legaldocumentation.master.validation.EquityCorporateEventsTypeFormatValidator;
import cdm.legaldocumentation.master.validation.EquityCorporateEventsValidator;
import cdm.legaldocumentation.master.validation.exists.EquityCorporateEventsOnlyExistsValidator;
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
@RosettaMeta(model=EquityCorporateEvents.class)
public class EquityCorporateEventsMeta implements RosettaMetaData<EquityCorporateEvents> {

	@Override
	public List<Validator<? super EquityCorporateEvents>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super EquityCorporateEvents, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EquityCorporateEvents> validator() {
		return new EquityCorporateEventsValidator();
	}

	@Override
	public Validator<? super EquityCorporateEvents> typeFormatValidator() {
		return new EquityCorporateEventsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EquityCorporateEvents, Set<String>> onlyExistsValidator() {
		return new EquityCorporateEventsOnlyExistsValidator();
	}
}
