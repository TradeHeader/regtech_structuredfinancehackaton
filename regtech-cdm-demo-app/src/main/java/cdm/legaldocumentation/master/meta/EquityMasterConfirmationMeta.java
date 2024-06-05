package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.EquityMasterConfirmation;
import cdm.legaldocumentation.master.validation.EquityMasterConfirmationTypeFormatValidator;
import cdm.legaldocumentation.master.validation.EquityMasterConfirmationValidator;
import cdm.legaldocumentation.master.validation.exists.EquityMasterConfirmationOnlyExistsValidator;
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
@RosettaMeta(model=EquityMasterConfirmation.class)
public class EquityMasterConfirmationMeta implements RosettaMetaData<EquityMasterConfirmation> {

	@Override
	public List<Validator<? super EquityMasterConfirmation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super EquityMasterConfirmation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EquityMasterConfirmation> validator() {
		return new EquityMasterConfirmationValidator();
	}

	@Override
	public Validator<? super EquityMasterConfirmation> typeFormatValidator() {
		return new EquityMasterConfirmationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EquityMasterConfirmation, Set<String>> onlyExistsValidator() {
		return new EquityMasterConfirmationOnlyExistsValidator();
	}
}
