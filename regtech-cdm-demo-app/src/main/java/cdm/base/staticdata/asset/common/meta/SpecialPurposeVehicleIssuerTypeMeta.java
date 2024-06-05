package cdm.base.staticdata.asset.common.meta;

import cdm.base.staticdata.asset.common.SpecialPurposeVehicleIssuerType;
import cdm.base.staticdata.asset.common.validation.SpecialPurposeVehicleIssuerTypeTypeFormatValidator;
import cdm.base.staticdata.asset.common.validation.SpecialPurposeVehicleIssuerTypeValidator;
import cdm.base.staticdata.asset.common.validation.exists.SpecialPurposeVehicleIssuerTypeOnlyExistsValidator;
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
@RosettaMeta(model=SpecialPurposeVehicleIssuerType.class)
public class SpecialPurposeVehicleIssuerTypeMeta implements RosettaMetaData<SpecialPurposeVehicleIssuerType> {

	@Override
	public List<Validator<? super SpecialPurposeVehicleIssuerType>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super SpecialPurposeVehicleIssuerType, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super SpecialPurposeVehicleIssuerType> validator() {
		return new SpecialPurposeVehicleIssuerTypeValidator();
	}

	@Override
	public Validator<? super SpecialPurposeVehicleIssuerType> typeFormatValidator() {
		return new SpecialPurposeVehicleIssuerTypeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super SpecialPurposeVehicleIssuerType, Set<String>> onlyExistsValidator() {
		return new SpecialPurposeVehicleIssuerTypeOnlyExistsValidator();
	}
}
