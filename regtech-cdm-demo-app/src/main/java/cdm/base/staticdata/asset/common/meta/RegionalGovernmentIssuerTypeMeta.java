package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.RegionalGovernmentIssuerType;
import cdm.base.staticdata.asset.common.validation.RegionalGovernmentIssuerTypeTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.RegionalGovernmentIssuerTypeValidator;
import cdm.base.staticdata.asset.common.validation.exists.RegionalGovernmentIssuerTypeOnlyExistsValidator;
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
@RosettaMeta(model=RegionalGovernmentIssuerType.class)
public class RegionalGovernmentIssuerTypeMeta implements RosettaMetaData<RegionalGovernmentIssuerType> {

	@Override
	public List<Validator<? super RegionalGovernmentIssuerType>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super RegionalGovernmentIssuerType, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super RegionalGovernmentIssuerType> validator() {
		return new RegionalGovernmentIssuerTypeValidator();
	}

	@Override
	public Validator<? super RegionalGovernmentIssuerType> typeFormatValidator() {
		return new RegionalGovernmentIssuerTypeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super RegionalGovernmentIssuerType, Set<String>> onlyExistsValidator() {
		return new RegionalGovernmentIssuerTypeOnlyExistsValidator();
	}
}
