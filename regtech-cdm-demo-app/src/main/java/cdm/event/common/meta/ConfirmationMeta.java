package cdm.event.common.meta;

import cdm.event.common.Confirmation;
import cdm.event.common.validation.ConfirmationTypeFormatValidator;
import cdm.event.common.validation.ConfirmationValidator;
import cdm.event.common.validation.exists.ConfirmationOnlyExistsValidator;
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
@RosettaMeta(model=Confirmation.class)
public class ConfirmationMeta implements RosettaMetaData<Confirmation> {

	@Override
	public List<Validator<? super Confirmation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.ConfirmationBothBuyerAndSellerPartyRolesMustExist.class)
		);
	}
	
	@Override
	public List<Function<? super Confirmation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Confirmation> validator() {
		return new ConfirmationValidator();
	}

	@Override
	public Validator<? super Confirmation> typeFormatValidator() {
		return new ConfirmationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Confirmation, Set<String>> onlyExistsValidator() {
		return new ConfirmationOnlyExistsValidator();
	}
}
