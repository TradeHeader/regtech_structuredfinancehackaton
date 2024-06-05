package cdm.product.asset.meta;

import cdm.product.asset.AssetDeliveryProfileBlock;
import cdm.product.asset.validation.AssetDeliveryProfileBlockTypeFormatValidator;
import cdm.product.asset.validation.AssetDeliveryProfileBlockValidator;
import cdm.product.asset.validation.exists.AssetDeliveryProfileBlockOnlyExistsValidator;
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
@RosettaMeta(model=AssetDeliveryProfileBlock.class)
public class AssetDeliveryProfileBlockMeta implements RosettaMetaData<AssetDeliveryProfileBlock> {

	@Override
	public List<Validator<? super AssetDeliveryProfileBlock>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AssetDeliveryProfileBlock, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AssetDeliveryProfileBlock> validator() {
		return new AssetDeliveryProfileBlockValidator();
	}

	@Override
	public Validator<? super AssetDeliveryProfileBlock> typeFormatValidator() {
		return new AssetDeliveryProfileBlockTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AssetDeliveryProfileBlock, Set<String>> onlyExistsValidator() {
		return new AssetDeliveryProfileBlockOnlyExistsValidator();
	}
}
