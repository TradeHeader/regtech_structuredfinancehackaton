package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.Address;
import cdm.base.staticdata.party.validation.AddressTypeFormatValidator;
import cdm.base.staticdata.party.validation.AddressValidator;
import cdm.base.staticdata.party.validation.exists.AddressOnlyExistsValidator;
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
@RosettaMeta(model=Address.class)
public class AddressMeta implements RosettaMetaData<Address> {

	@Override
	public List<Validator<? super Address>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Address, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Address> validator() {
		return new AddressValidator();
	}

	@Override
	public Validator<? super Address> typeFormatValidator() {
		return new AddressTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Address, Set<String>> onlyExistsValidator() {
		return new AddressOnlyExistsValidator();
	}
}
