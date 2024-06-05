package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.IndexAdjustmentEvents;
import cdm.legaldocumentation.master.validation.IndexAdjustmentEventsTypeFormatValidator;
import cdm.legaldocumentation.master.validation.IndexAdjustmentEventsValidator;
import cdm.legaldocumentation.master.validation.exists.IndexAdjustmentEventsOnlyExistsValidator;
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
@RosettaMeta(model=IndexAdjustmentEvents.class)
public class IndexAdjustmentEventsMeta implements RosettaMetaData<IndexAdjustmentEvents> {

	@Override
	public List<Validator<? super IndexAdjustmentEvents>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super IndexAdjustmentEvents, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super IndexAdjustmentEvents> validator() {
		return new IndexAdjustmentEventsValidator();
	}

	@Override
	public Validator<? super IndexAdjustmentEvents> typeFormatValidator() {
		return new IndexAdjustmentEventsTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super IndexAdjustmentEvents, Set<String>> onlyExistsValidator() {
		return new IndexAdjustmentEventsOnlyExistsValidator();
	}
}
