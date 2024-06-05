package cdm.product.asset.meta;

import cdm.product.asset.VarianceCapFloor;
import cdm.product.asset.validation.VarianceCapFloorTypeFormatValidator;
import cdm.product.asset.validation.VarianceCapFloorValidator;
import cdm.product.asset.validation.exists.VarianceCapFloorOnlyExistsValidator;
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
@RosettaMeta(model=VarianceCapFloor.class)
public class VarianceCapFloorMeta implements RosettaMetaData<VarianceCapFloor> {

	@Override
	public List<Validator<? super VarianceCapFloor>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
			factory.create(cdm.product.asset.validation.datarule.VarianceCapFloorPositiveUnadjustedVarianceCap.class),
			factory.create(cdm.product.asset.validation.datarule.VarianceCapFloorCapFloorApplicability.class)
		);
	}
	
	@Override
	public List<Function<? super VarianceCapFloor, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super VarianceCapFloor> validator() {
		return new VarianceCapFloorValidator();
	}

	@Override
	public Validator<? super VarianceCapFloor> typeFormatValidator() {
		return new VarianceCapFloorTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super VarianceCapFloor, Set<String>> onlyExistsValidator() {
		return new VarianceCapFloorOnlyExistsValidator();
	}
}
