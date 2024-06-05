package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.AdditionalDisruptionEvents;
import cdm.legaldocumentation.master.validation.AdditionalDisruptionEventsTypeFormatValidator;
import cdm.legaldocumentation.master.validation.AdditionalDisruptionEventsValidator;
import cdm.legaldocumentation.master.validation.exists.AdditionalDisruptionEventsOnlyExistsValidator;
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
@RosettaMeta(model=AdditionalDisruptionEvents.class)
public class AdditionalDisruptionEventsMeta implements RosettaMetaData<AdditionalDisruptionEvents> {

	@Override
	public List<Validator<? super AdditionalDisruptionEvents>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.legaldocumentation.master.validation.datarule.AdditionalDisruptionEventsMaximumStockLoanRate.class),
			factory.create(cdm.legaldocumentation.master.validation.datarule.AdditionalDisruptionEventsInitialStockLoanRate.class),
			factory.create(cdm.legaldocumentation.master.validation.datarule.AdditionalDisruptionEventsDisruptionEventsDeterminingParty.class)
		);
	}
	
	@Override
	public List<Function<? super AdditionalDisruptionEvents, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AdditionalDisruptionEvents> validator() {
		return new AdditionalDisruptionEventsValidator();
	}

	@Override
	public Validator<? super AdditionalDisruptionEvents> typeFormatValidator() {
		return new AdditionalDisruptionEventsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AdditionalDisruptionEvents, Set<String>> onlyExistsValidator() {
		return new AdditionalDisruptionEventsOnlyExistsValidator();
	}
}
