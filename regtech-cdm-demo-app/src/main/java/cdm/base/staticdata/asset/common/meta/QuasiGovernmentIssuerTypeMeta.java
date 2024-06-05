package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.QuasiGovernmentIssuerType;
import cdm.base.staticdata.asset.common.validation.QuasiGovernmentIssuerTypeTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.QuasiGovernmentIssuerTypeValidator;
import cdm.base.staticdata.asset.common.validation.exists.QuasiGovernmentIssuerTypeOnlyExistsValidator;
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
@RosettaMeta(model=QuasiGovernmentIssuerType.class)
public class QuasiGovernmentIssuerTypeMeta implements RosettaMetaData<QuasiGovernmentIssuerType> {

	@Override
	public List<Validator<? super QuasiGovernmentIssuerType>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.staticdata.asset.common.validation.datarule.QuasiGovernmentIssuerTypeNonSovereignEntityRecourse.class)
		);
	}
	
	@Override
	public List<Function<? super QuasiGovernmentIssuerType, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super QuasiGovernmentIssuerType> validator() {
		return new QuasiGovernmentIssuerTypeValidator();
	}

	@Override
	public Validator<? super QuasiGovernmentIssuerType> typeFormatValidator() {
		return new QuasiGovernmentIssuerTypeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super QuasiGovernmentIssuerType, Set<String>> onlyExistsValidator() {
		return new QuasiGovernmentIssuerTypeOnlyExistsValidator();
	}
}
