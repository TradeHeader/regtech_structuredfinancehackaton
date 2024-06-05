package cdm.event.workflow.meta;

import cdm.event.workflow.LimitApplicableExtended;
import cdm.event.workflow.validation.LimitApplicableExtendedTypeFormatValidator;
import cdm.event.workflow.validation.LimitApplicableExtendedValidator;
import cdm.event.workflow.validation.exists.LimitApplicableExtendedOnlyExistsValidator;
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
@RosettaMeta(model=LimitApplicableExtended.class)
public class LimitApplicableExtendedMeta implements RosettaMetaData<LimitApplicableExtended> {

	@Override
	public List<Validator<? super LimitApplicableExtended>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.workflow.validation.datarule.LimitApplicableLimitApplicableChoice.class)
		);
	}
	
	@Override
	public List<Function<? super LimitApplicableExtended, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super LimitApplicableExtended> validator() {
		return new LimitApplicableExtendedValidator();
	}

	@Override
	public Validator<? super LimitApplicableExtended> typeFormatValidator() {
		return new LimitApplicableExtendedTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super LimitApplicableExtended, Set<String>> onlyExistsValidator() {
		return new LimitApplicableExtendedOnlyExistsValidator();
	}
}
