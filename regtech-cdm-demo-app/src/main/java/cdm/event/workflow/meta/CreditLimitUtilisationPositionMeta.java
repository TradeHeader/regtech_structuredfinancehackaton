package cdm.event.workflow.meta;

import cdm.event.workflow.CreditLimitUtilisationPosition;
import cdm.event.workflow.validation.CreditLimitUtilisationPositionTypeFormatValidator;
import cdm.event.workflow.validation.CreditLimitUtilisationPositionValidator;
import cdm.event.workflow.validation.exists.CreditLimitUtilisationPositionOnlyExistsValidator;
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
@RosettaMeta(model=CreditLimitUtilisationPosition.class)
public class CreditLimitUtilisationPositionMeta implements RosettaMetaData<CreditLimitUtilisationPosition> {

	@Override
	public List<Validator<? super CreditLimitUtilisationPosition>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super CreditLimitUtilisationPosition, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super CreditLimitUtilisationPosition> validator() {
		return new CreditLimitUtilisationPositionValidator();
	}

	@Override
	public Validator<? super CreditLimitUtilisationPosition> typeFormatValidator() {
		return new CreditLimitUtilisationPositionTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super CreditLimitUtilisationPosition, Set<String>> onlyExistsValidator() {
		return new CreditLimitUtilisationPositionOnlyExistsValidator();
	}
}
