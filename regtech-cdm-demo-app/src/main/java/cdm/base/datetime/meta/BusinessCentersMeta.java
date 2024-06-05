package cdm.base.datetime.meta;

import cdm.base.datetime.BusinessCenters;
import cdm.base.datetime.validation.BusinessCentersTypeFormatValidator;
import cdm.base.datetime.validation.BusinessCentersValidator;
import cdm.base.datetime.validation.exists.BusinessCentersOnlyExistsValidator;
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
@RosettaMeta(model=BusinessCenters.class)
public class BusinessCentersMeta implements RosettaMetaData<BusinessCenters> {

	@Override
	public List<Validator<? super BusinessCenters>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.datetime.validation.datarule.BusinessCentersBusinessCentersChoice.class)
		);
	}
	
	@Override
	public List<Function<? super BusinessCenters, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super BusinessCenters> validator() {
		return new BusinessCentersValidator();
	}

	@Override
	public Validator<? super BusinessCenters> typeFormatValidator() {
		return new BusinessCentersTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super BusinessCenters, Set<String>> onlyExistsValidator() {
		return new BusinessCentersOnlyExistsValidator();
	}
}
