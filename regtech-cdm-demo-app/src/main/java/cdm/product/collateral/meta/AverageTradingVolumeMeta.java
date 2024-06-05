package cdm.product.collateral.meta;

import cdm.product.collateral.AverageTradingVolume;
import cdm.product.collateral.validation.AverageTradingVolumeTypeFormatValidator;
import cdm.product.collateral.validation.AverageTradingVolumeValidator;
import cdm.product.collateral.validation.exists.AverageTradingVolumeOnlyExistsValidator;
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
@RosettaMeta(model=AverageTradingVolume.class)
public class AverageTradingVolumeMeta implements RosettaMetaData<AverageTradingVolume> {

	@Override
	public List<Validator<? super AverageTradingVolume>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AverageTradingVolume, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AverageTradingVolume> validator() {
		return new AverageTradingVolumeValidator();
	}

	@Override
	public Validator<? super AverageTradingVolume> typeFormatValidator() {
		return new AverageTradingVolumeTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AverageTradingVolume, Set<String>> onlyExistsValidator() {
		return new AverageTradingVolumeOnlyExistsValidator();
	}
}
