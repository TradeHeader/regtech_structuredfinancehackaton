package cdm.event.common.meta;

import cdm.event.common.ScheduledTransfer;
import cdm.event.common.validation.ScheduledTransferTypeFormatValidator;
import cdm.event.common.validation.ScheduledTransferValidator;
import cdm.event.common.validation.exists.ScheduledTransferOnlyExistsValidator;
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
@RosettaMeta(model=ScheduledTransfer.class)
public class ScheduledTransferMeta implements RosettaMetaData<ScheduledTransfer> {

	@Override
	public List<Validator<? super ScheduledTransfer>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.common.validation.datarule.ScheduledTransferCorporateActionTransferTypeExists.class)
		);
	}
	
	@Override
	public List<Function<? super ScheduledTransfer, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ScheduledTransfer> validator() {
		return new ScheduledTransferValidator();
	}

	@Override
	public Validator<? super ScheduledTransfer> typeFormatValidator() {
		return new ScheduledTransferTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ScheduledTransfer, Set<String>> onlyExistsValidator() {
		return new ScheduledTransferOnlyExistsValidator();
	}
}
