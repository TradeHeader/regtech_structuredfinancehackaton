package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.Counterparty;
import cdm.base.staticdata.party.validation.CounterpartyTypeFormatValidator;
import cdm.base.staticdata.party.validation.CounterpartyValidator;
import cdm.base.staticdata.party.validation.exists.CounterpartyOnlyExistsValidator;
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
@RosettaMeta(model=Counterparty.class)
public class CounterpartyMeta implements RosettaMetaData<Counterparty> {

	@Override
	public List<Validator<? super Counterparty>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super Counterparty, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super Counterparty> validator() {
		return new CounterpartyValidator();
	}

	@Override
	public Validator<? super Counterparty> typeFormatValidator() {
		return new CounterpartyTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super Counterparty, Set<String>> onlyExistsValidator() {
		return new CounterpartyOnlyExistsValidator();
	}
}
