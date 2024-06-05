package cdm.product.asset.meta;

import cdm.product.asset.VolatilityCapFloor;
import cdm.product.asset.validation.VolatilityCapFloorTypeFormatValidator;
import cdm.product.asset.validation.VolatilityCapFloorValidator;
import cdm.product.asset.validation.exists.VolatilityCapFloorOnlyExistsValidator;
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
@RosettaMeta(model=VolatilityCapFloor.class)
public class VolatilityCapFloorMeta implements RosettaMetaData<VolatilityCapFloor> {

	@Override
	public List<Validator<? super VolatilityCapFloor>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.VolatilityCapFloorCapFloorApplicability.class),
			factory.create(cdm.product.asset.validation.datarule.VolatilityCapFloorPositiveCaps.class)
		);
	}
	
	@Override
	public List<Function<? super VolatilityCapFloor, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super VolatilityCapFloor> validator() {
		return new VolatilityCapFloorValidator();
	}

	@Override
	public Validator<? super VolatilityCapFloor> typeFormatValidator() {
		return new VolatilityCapFloorTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super VolatilityCapFloor, Set<String>> onlyExistsValidator() {
		return new VolatilityCapFloorOnlyExistsValidator();
	}
}
