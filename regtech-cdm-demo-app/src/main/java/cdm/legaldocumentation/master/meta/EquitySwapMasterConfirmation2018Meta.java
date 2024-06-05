package cdm.legaldocumentation.master.meta;

import cdm.legaldocumentation.master.EquitySwapMasterConfirmation2018;
import cdm.legaldocumentation.master.validation.EquitySwapMasterConfirmation2018TypeFormatValidator;
import cdm.legaldocumentation.master.validation.EquitySwapMasterConfirmation2018Validator;
import cdm.legaldocumentation.master.validation.exists.EquitySwapMasterConfirmation2018OnlyExistsValidator;
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
@RosettaMeta(model=EquitySwapMasterConfirmation2018.class)
public class EquitySwapMasterConfirmation2018Meta implements RosettaMetaData<EquitySwapMasterConfirmation2018> {

	@Override
	public List<Validator<? super EquitySwapMasterConfirmation2018>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super EquitySwapMasterConfirmation2018, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super EquitySwapMasterConfirmation2018> validator() {
		return new EquitySwapMasterConfirmation2018Validator();
	}

	@Override
	public Validator<? super EquitySwapMasterConfirmation2018> typeFormatValidator() {
		return new EquitySwapMasterConfirmation2018TypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super EquitySwapMasterConfirmation2018, Set<String>> onlyExistsValidator() {
		return new EquitySwapMasterConfirmation2018OnlyExistsValidator();
	}
}
