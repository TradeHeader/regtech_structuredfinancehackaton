package cdm.event.common.meta;

import cdm.event.common.Affirmation;
import cdm.event.common.validation.AffirmationTypeFormatValidator;
import cdm.event.common.validation.AffirmationValidator;
import cdm.event.common.validation.exists.AffirmationOnlyExistsValidator;
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
@RosettaMeta(model=Affirmation.class)
public class AffirmationMeta implements RosettaMetaData<Affirmation> {

	@Override
	public List<Validator<? super Affirmation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.AffirmationBothBuyerAndSellerPartyRolesMustExist.class)
		);
	}
	
	@Override
	public List<Function<? super Affirmation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Affirmation> validator() {
		return new AffirmationValidator();
	}

	@Override
	public Validator<? super Affirmation> typeFormatValidator() {
		return new AffirmationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Affirmation, Set<String>> onlyExistsValidator() {
		return new AffirmationOnlyExistsValidator();
	}
}
