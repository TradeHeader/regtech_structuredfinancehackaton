package cdm.product.asset.meta;

import cdm.product.asset.AssetDeliveryInformation;
import cdm.product.asset.validation.AssetDeliveryInformationTypeFormatValidator;
import cdm.product.asset.validation.AssetDeliveryInformationValidator;
import cdm.product.asset.validation.exists.AssetDeliveryInformationOnlyExistsValidator;
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
@RosettaMeta(model=AssetDeliveryInformation.class)
public class AssetDeliveryInformationMeta implements RosettaMetaData<AssetDeliveryInformation> {

	@Override
	public List<Validator<? super AssetDeliveryInformation>> dataRules(ValidatorFactory factory) {
		return Arrays.asList(
		);
	}
	
	@Override
	public List<Function<? super AssetDeliveryInformation, QualifyResult>> getQualifyFunctions(QualifyFunctionFactory factory) {
		return Collections.emptyList();
	}

	@Override
	public Validator<? super AssetDeliveryInformation> validator() {
		return new AssetDeliveryInformationValidator();
	}

	@Override
	public Validator<? super AssetDeliveryInformation> typeFormatValidator() {
		return new AssetDeliveryInformationTypeFormatValidator();
	}
	
	@Override
	public ValidatorWithArg<? super AssetDeliveryInformation, Set<String>> onlyExistsValidator() {
		return new AssetDeliveryInformationOnlyExistsValidator();
	}
}
