package cdm.base.staticdata.party.meta;

import cdm.base.staticdata.party.BusinessUnit;
import cdm.base.staticdata.party.validation.BusinessUnitTypeFormatValidator;
import cdm.base.staticdata.party.validation.BusinessUnitValidator;
import cdm.base.staticdata.party.validation.exists.BusinessUnitOnlyExistsValidator;
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
@RosettaMeta(model=BusinessUnit.class)
public class BusinessUnitMeta implements RosettaMetaData<BusinessUnit> {

	@Override
	public List<Validator<? super BusinessUnit>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super BusinessUnit, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BusinessUnit> validator() {
		return new BusinessUnitValidator();
	}

	@Override
	public Validator<? super BusinessUnit> typeFormatValidator() {
		return new BusinessUnitTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BusinessUnit, Set<String>> onlyExistsValidator() {
		return new BusinessUnitOnlyExistsValidator();
	}
}
