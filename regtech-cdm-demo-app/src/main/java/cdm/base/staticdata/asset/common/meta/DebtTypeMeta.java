package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.DebtType;
import cdm.base.staticdata.asset.common.validation.DebtTypeTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.DebtTypeValidator;
import cdm.base.staticdata.asset.common.validation.exists.DebtTypeOnlyExistsValidator;
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
@RosettaMeta(model=DebtType.class)
public class DebtTypeMeta implements RosettaMetaData<DebtType> {

	@Override
	public List<Validator<? super DebtType>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super DebtType, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super DebtType> validator() {
		return new DebtTypeValidator();
	}

	@Override
	public Validator<? super DebtType> typeFormatValidator() {
		return new DebtTypeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super DebtType, Set<String>> onlyExistsValidator() {
		return new DebtTypeOnlyExistsValidator();
	}
}
