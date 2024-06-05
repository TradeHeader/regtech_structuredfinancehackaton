package cdm.event.common.meta;

import cdm.event.common.TransferBase;
import cdm.event.common.validation.TransferBaseTypeFormatValidator;
import cdm.event.common.validation.TransferBaseValidator;
import cdm.event.common.validation.exists.TransferBaseOnlyExistsValidator;
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
@RosettaMeta(model=TransferBase.class)
public class TransferBaseMeta implements RosettaMetaData<TransferBase> {

	@Override
	public List<Validator<? super TransferBase>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.TransferBaseFinancialUnitExists.class)
		);
	}
	
	@Override
	public List<Function<? super TransferBase, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super TransferBase> validator() {
		return new TransferBaseValidator();
	}

	@Override
	public Validator<? super TransferBase> typeFormatValidator() {
		return new TransferBaseTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super TransferBase, Set<String>> onlyExistsValidator() {
		return new TransferBaseOnlyExistsValidator();
	}
}
