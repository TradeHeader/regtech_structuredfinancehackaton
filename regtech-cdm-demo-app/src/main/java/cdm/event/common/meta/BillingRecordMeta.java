package cdm.event.common.meta;

import cdm.event.common.BillingRecord;
import cdm.event.common.validation.BillingRecordTypeFormatValidator;
import cdm.event.common.validation.BillingRecordValidator;
import cdm.event.common.validation.exists.BillingRecordOnlyExistsValidator;
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
@RosettaMeta(model=BillingRecord.class)
public class BillingRecordMeta implements RosettaMetaData<BillingRecord> {

	@Override
	public List<Validator<? super BillingRecord>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super BillingRecord, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BillingRecord> validator() {
		return new BillingRecordValidator();
	}

	@Override
	public Validator<? super BillingRecord> typeFormatValidator() {
		return new BillingRecordTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BillingRecord, Set<String>> onlyExistsValidator() {
		return new BillingRecordOnlyExistsValidator();
	}
}
