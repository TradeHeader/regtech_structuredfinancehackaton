package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.PartyReferencePayerReceiver;
import cdm.base.staticdata.party.validation.PartyReferencePayerReceiverTypeFormatValidator;
import cdm.base.staticdata.party.validation.PartyReferencePayerReceiverValidator;
import cdm.base.staticdata.party.validation.exists.PartyReferencePayerReceiverOnlyExistsValidator;
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
@RosettaMeta(model=PartyReferencePayerReceiver.class)
public class PartyReferencePayerReceiverMeta implements RosettaMetaData<PartyReferencePayerReceiver> {

	@Override
	public List<Validator<? super PartyReferencePayerReceiver>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super PartyReferencePayerReceiver, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super PartyReferencePayerReceiver> validator() {
		return new PartyReferencePayerReceiverValidator();
	}

	@Override
	public Validator<? super PartyReferencePayerReceiver> typeFormatValidator() {
		return new PartyReferencePayerReceiverTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super PartyReferencePayerReceiver, Set<String>> onlyExistsValidator() {
		return new PartyReferencePayerReceiverOnlyExistsValidator();
	}
}
