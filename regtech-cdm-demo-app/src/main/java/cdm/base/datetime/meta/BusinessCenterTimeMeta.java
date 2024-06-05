package cdm.base.datetime.meta;

import cdm.base.datetime.BusinessCenterTime;
import cdm.base.datetime.validation.BusinessCenterTimeTypeFormatValidator;
import cdm.base.datetime.validation.BusinessCenterTimeValidator;
import cdm.base.datetime.validation.exists.BusinessCenterTimeOnlyExistsValidator;
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
@RosettaMeta(model=BusinessCenterTime.class)
public class BusinessCenterTimeMeta implements RosettaMetaData<BusinessCenterTime> {

	@Override
	public List<Validator<? super BusinessCenterTime>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super BusinessCenterTime, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BusinessCenterTime> validator() {
		return new BusinessCenterTimeValidator();
	}

	@Override
	public Validator<? super BusinessCenterTime> typeFormatValidator() {
		return new BusinessCenterTimeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BusinessCenterTime, Set<String>> onlyExistsValidator() {
		return new BusinessCenterTimeOnlyExistsValidator();
	}
}
