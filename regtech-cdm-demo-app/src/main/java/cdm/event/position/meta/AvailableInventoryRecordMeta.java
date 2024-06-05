package cdm.event.position.meta;

import cdm.event.position.AvailableInventoryRecord;
import cdm.event.position.validation.AvailableInventoryRecordTypeFormatValidator;
import cdm.event.position.validation.AvailableInventoryRecordValidator;
import cdm.event.position.validation.exists.AvailableInventoryRecordOnlyExistsValidator;
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
@RosettaMeta(model=AvailableInventoryRecord.class)
public class AvailableInventoryRecordMeta implements RosettaMetaData<AvailableInventoryRecord> {

	@Override
	public List<Validator<? super AvailableInventoryRecord>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.event.position.validation.datarule.AvailableInventoryRecordInterestRate.class),
			factory.create(cdm.event.position.validation.datarule.AvailableInventoryRecordValidPartyRole.class)
		);
	}
	
	@Override
	public List<Function<? super AvailableInventoryRecord, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AvailableInventoryRecord> validator() {
		return new AvailableInventoryRecordValidator();
	}

	@Override
	public Validator<? super AvailableInventoryRecord> typeFormatValidator() {
		return new AvailableInventoryRecordTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AvailableInventoryRecord, Set<String>> onlyExistsValidator() {
		return new AvailableInventoryRecordOnlyExistsValidator();
	}
}
