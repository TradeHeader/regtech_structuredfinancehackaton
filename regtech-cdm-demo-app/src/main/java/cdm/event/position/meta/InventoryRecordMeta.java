package cdm.event.position.meta;

import cdm.event.position.InventoryRecord;
import cdm.event.position.validation.InventoryRecordTypeFormatValidator;
import cdm.event.position.validation.InventoryRecordValidator;
import cdm.event.position.validation.exists.InventoryRecordOnlyExistsValidator;
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
@RosettaMeta(model=InventoryRecord.class)
public class InventoryRecordMeta implements RosettaMetaData<InventoryRecord> {

	@Override
	public List<Validator<? super InventoryRecord>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super InventoryRecord, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super InventoryRecord> validator() {
		return new InventoryRecordValidator();
	}

	@Override
	public Validator<? super InventoryRecord> typeFormatValidator() {
		return new InventoryRecordTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super InventoryRecord, Set<String>> onlyExistsValidator() {
		return new InventoryRecordOnlyExistsValidator();
	}
}
