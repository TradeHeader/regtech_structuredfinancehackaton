package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.PayerReceiver;
import cdm.base.staticdata.party.validation.PayerReceiverTypeFormatValidator;
import cdm.base.staticdata.party.validation.PayerReceiverValidator;
import cdm.base.staticdata.party.validation.exists.PayerReceiverOnlyExistsValidator;
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
@RosettaMeta(model=PayerReceiver.class)
public class PayerReceiverMeta implements RosettaMetaData<PayerReceiver> {

	@Override
	public List<Validator<? super PayerReceiver>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PayerReceiver, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PayerReceiver> validator() {
		return new PayerReceiverValidator();
	}

	@Override
	public Validator<? super PayerReceiver> typeFormatValidator() {
		return new PayerReceiverTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PayerReceiver, Set<String>> onlyExistsValidator() {
		return new PayerReceiverOnlyExistsValidator();
	}
}
