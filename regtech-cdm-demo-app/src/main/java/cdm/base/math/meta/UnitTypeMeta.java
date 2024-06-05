package cdm.base.math.meta;

import cdm.base.math.UnitType;
import cdm.base.math.validation.UnitTypeTypeFormatValidator;
import cdm.base.math.validation.UnitTypeValidator;
import cdm.base.math.validation.exists.UnitTypeOnlyExistsValidator;
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
@RosettaMeta(model=UnitType.class)
public class UnitTypeMeta implements RosettaMetaData<UnitType> {

	@Override
	public List<Validator<? super UnitType>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.base.math.validation.datarule.UnitTypeUnitType.class)
		);
	}
	
	@Override
	public List<Function<? super UnitType, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super UnitType> validator() {
		return new UnitTypeValidator();
	}

	@Override
	public Validator<? super UnitType> typeFormatValidator() {
		return new UnitTypeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super UnitType, Set<String>> onlyExistsValidator() {
		return new UnitTypeOnlyExistsValidator();
	}
}
