package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.ReferenceBank;
import cdm.base.staticdata.party.validation.ReferenceBankTypeFormatValidator;
import cdm.base.staticdata.party.validation.ReferenceBankValidator;
import cdm.base.staticdata.party.validation.exists.ReferenceBankOnlyExistsValidator;
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
@RosettaMeta(model=ReferenceBank.class)
public class ReferenceBankMeta implements RosettaMetaData<ReferenceBank> {

	@Override
	public List<Validator<? super ReferenceBank>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super ReferenceBank, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super ReferenceBank> validator() {
		return new ReferenceBankValidator();
	}

	@Override
	public Validator<? super ReferenceBank> typeFormatValidator() {
		return new ReferenceBankTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super ReferenceBank, Set<String>> onlyExistsValidator() {
		return new ReferenceBankOnlyExistsValidator();
	}
}
