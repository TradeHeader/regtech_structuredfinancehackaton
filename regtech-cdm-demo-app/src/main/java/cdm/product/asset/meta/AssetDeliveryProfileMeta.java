package cdm.product.asset.meta;

import cdm.product.asset.AssetDeliveryProfile;
import cdm.product.asset.validation.AssetDeliveryProfileTypeFormatValidator;
import cdm.product.asset.validation.AssetDeliveryProfileValidator;
import cdm.product.asset.validation.exists.AssetDeliveryProfileOnlyExistsValidator;
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
@RosettaMeta(model=AssetDeliveryProfile.class)
public class AssetDeliveryProfileMeta implements RosettaMetaData<AssetDeliveryProfile> {

	@Override
	public List<Validator<? super AssetDeliveryProfile>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AssetDeliveryProfile, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AssetDeliveryProfile> validator() {
		return new AssetDeliveryProfileValidator();
	}

	@Override
	public Validator<? super AssetDeliveryProfile> typeFormatValidator() {
		return new AssetDeliveryProfileTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AssetDeliveryProfile, Set<String>> onlyExistsValidator() {
		return new AssetDeliveryProfileOnlyExistsValidator();
	}
}
